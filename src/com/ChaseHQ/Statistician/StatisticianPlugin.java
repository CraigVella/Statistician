package com.ChaseHQ.Statistician;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkitcontrib.BukkitContrib;

import com.ChaseHQ.Statistician.Config.Config;
import com.ChaseHQ.Statistician.Config.PackedDependencyVersions;
import com.ChaseHQ.Statistician.Database.StatDB;
import com.ChaseHQ.Statistician.EventDataHandlers.EDHPlayer;
import com.ChaseHQ.Statistician.Listeners.StatisticianBlockListener;
import com.ChaseHQ.Statistician.Listeners.StatisticianEntityListener;
import com.ChaseHQ.Statistician.Listeners.StatisticianPlayerListener;
import com.ChaseHQ.Statistician.Stats.PlayerData;
import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

public class StatisticianPlugin extends JavaPlugin { 
	
	private static StatisticianPlugin _singleton = null;
	private ExecutorService executor;
	private DataProcessor _dprocessor;
	private PlayerData _playerData;
	private EDHPlayer eventDataHandlerPlayer;
	private PermissionHandler permissions;
	private BukkitContrib bukkitContrib;

	@Override
	public void onDisable() {
		Log.ConsoleLog("Shutting down...");
		
		if (eventDataHandlerPlayer != null)
			for (Player player : getServer().getOnlinePlayers()) {	
				eventDataHandlerPlayer.PlayerQuit(player);
			}
		
		if (_playerData != null)
			_playerData._processData();
		
		StatDB.getDB().callStoredProcedure("pluginShutdown", null);
		
		_singleton = null;
		
		executor.shutdown();
	}

	@Override
	public void onEnable() {
		if (_singleton != null) {
			return;
		}
		
		_singleton = this;
		
		setNaggable(false);
		
		executor = Executors.newCachedThreadPool();
		
		Log.ConsoleLog("Version " + Config.StatisticianVersion + " By ChaseHQ Starting Up...");
		
		// Make sure the configuration is accessible
		if (Config.getConfig() == null) {
			getPluginLoader().disablePlugin(this);
			return;
		}
		
		// Check mySQL Dependency
		if (StatDB.getDB() == null) {
			getPluginLoader().disablePlugin(this);
			return;
		}
		
		// Load Permissions if available
		if (this.getServer().getPluginManager().getPlugin("Permissions") != null) {
			Log.ConsoleLog("Found Permissions Plugin");
			permissions = ((Permissions)this.getServer().getPluginManager().getPlugin("Permissions")).getHandler();
			if (permissions != null) 
				Log.ConsoleLog("Succesfully Hooked Into Permissions");
			else 
				Log.ConsoleLog("Failed Hooking Into Permissions - All stats will be recorded");
		} else {
			Log.ConsoleLog("Permissions Plugin Not Found - All stats will be recorded");
		}
		
		if (!loadBukkitContrib()) {
			getPluginLoader().disablePlugin(this);
			return;
		}
		
		StatDB.getDB().callStoredProcedure("pluginStartup", null);
		
		eventDataHandlerPlayer = new EDHPlayer();
		
		// Setup Listeners
		StatisticianPlayerListener _pl = new StatisticianPlayerListener(eventDataHandlerPlayer);
		StatisticianBlockListener _bl = new StatisticianBlockListener(eventDataHandlerPlayer);
		StatisticianEntityListener _el = new StatisticianEntityListener(eventDataHandlerPlayer);
		
		PluginManager pm = getServer().getPluginManager();
		
		// Release Build Register only used events
		// Block Listeners
		pm.registerEvent(Event.Type.BLOCK_BREAK, _bl, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.BLOCK_PLACE, _bl, Event.Priority.Normal, this);
		// Entity Listeners
		pm.registerEvent(Event.Type.ENTITY_DEATH, _el, Event.Priority.Normal, this);
		// Player Listeners
		pm.registerEvent(Event.Type.PLAYER_JOIN, _pl, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.PLAYER_QUIT, _pl, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.PLAYER_MOVE, _pl, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.PLAYER_PICKUP_ITEM, _pl, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.PLAYER_DROP_ITEM, _pl, Event.Priority.Normal, this);
		
		_playerData = new PlayerData();
		
		_dprocessor = new DataProcessor();
		_dprocessor.addProcessable(_playerData);
		
		new Timer(true).scheduleAtFixedRate(_dprocessor, Config.getConfig().get_databaseUpdateTime() * 1000, Config.getConfig().get_databaseUpdateTime() * 1000);
		
		// This could be a reload so see if people are logged in
		for (Player player : getServer().getOnlinePlayers()) {
			eventDataHandlerPlayer.PlayerJoin(player);
		}
	}
	
	public ExecutorService getExecutor() {
		return executor;
	}
	
	public static StatisticianPlugin getEnabledPlugin() {
		return _singleton;
	}
	
	private boolean loadBukkitContrib() {
		// Load BukkitContrib if available, If Not Unload our packaged version and load it
		if (this.getServer().getPluginManager().getPlugin("BukkitContrib") != null) {
			bukkitContrib = (BukkitContrib) getServer().getPluginManager().getPlugin("BukkitContrib");
			if (bukkitContrib.getDescription().getVersion().compareToIgnoreCase(PackedDependencyVersions.getVesionFor("BukkitContrib")) != 0) {
				Log.ConsoleLog("WARNING: Your Current ContribBukkit Version = " + bukkitContrib.getDescription().getVersion() 
						+ " Statistician has ContribBukkit Version = " + PackedDependencyVersions.getVesionFor("BukkitContrib") 
						+ " Packed with it");
				Log.ConsoleLog("WARNING: You are not using the same version of BukkitContrib that is Packaged With Statistician");
				Log.ConsoleLog("WARNING: if this is intended, ignore this warning. If not please delete BukkitContrib.jar from");
				Log.ConsoleLog("WARNING: your plugins directory and reload Statistician. It will unpack a compatible version of ");
				Log.ConsoleLog("WARNING: BukkitContrib and automatically install it.");
			}
		} else {
			Log.ConsoleLog("Could not find BukkitContrib Dependency, unpacking our own and installing...");
			try {
				InputStream is = getClass().getClassLoader().getResourceAsStream("com/ChaseHQ/Statistician/Dependency/BukkitContrib.jar");
				File f = new File("plugins/BukkitContrib.jar");
				OutputStream out = new FileOutputStream(f);
				byte buf[] = new byte[1024];
				int len;
				while ((len = is.read(buf)) > 0) {
					out.write(buf,0,len);
				}
				out.close();
				is.close();
				Log.ConsoleLog("BukkitContrib.jar Unloaded preparing to load plugin");
				getServer().getPluginManager().loadPlugin(f);
				bukkitContrib = (BukkitContrib) getServer().getPluginManager().getPlugin("BukkitContrib");
				getServer().getPluginManager().enablePlugin(bukkitContrib);
				if (bukkitContrib == null)
					throw new Exception("Could not load bukkitContrib Plugin");
			} catch (Exception e) {
				Log.ConsoleLog("Could not Unpackage or Load BukkitContrib Plugin, Fatal Error, Shutting down");
				return false;
			}
		}

		Log.ConsoleLog("BukkitContrib Loaded and Hooked");
		return true;
	}
	
	public PlayerData getPlayerData() {
		return _playerData;
	}
	
	public BukkitContrib getBukkitContrib() {
		return bukkitContrib;
	}
	
	public boolean permissionToRecordStat(Player player) {
		if (permissions != null) {
			if (permissions.has(player, "Statistician.ignoreOverride"))
				return true;
			if (permissions.has(player, "Statistician.ignore"))
				return false;
		}
		return true;
	}

}
