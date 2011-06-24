package com.ChaseHQ.Statistician;

import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.ChaseHQ.Statistician.Config.Config;
import com.ChaseHQ.Statistician.Database.StatDB;
import com.ChaseHQ.Statistician.EventDataHandlers.EDHPlayer;
import com.ChaseHQ.Statistician.Listeners.StatisticianBlockListener;
import com.ChaseHQ.Statistician.Listeners.StatisticianEntityListener;
import com.ChaseHQ.Statistician.Listeners.StatisticianPlayerListener;
import com.ChaseHQ.Statistician.Stats.PlayerData;

public class StatisticianPlugin extends JavaPlugin { 
	
	private static StatisticianPlugin _singleton = null;
	private ExecutorService executor;
	private DataProcessor _dprocessor;
	private PlayerData _playerData;
	private EDHPlayer eventDataHandlerPlayer;

	@Override
	public void onDisable() {
		Log.ConsoleLog("Shutting down...");
		
		if (eventDataHandlerPlayer != null)
			for (Player player : getServer().getOnlinePlayers()) {	
				eventDataHandlerPlayer.PlayerQuit(player);
			}
		
		if (_playerData != null)
			_playerData._processData();
		
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
		
		// Download mySQL Dependency
		if (StatDB.getDB() == null) {
			getPluginLoader().disablePlugin(this);
			return;
		}
		
		eventDataHandlerPlayer = new EDHPlayer();
		
		// Setup Listeners
		StatisticianPlayerListener _pl = new StatisticianPlayerListener(eventDataHandlerPlayer);
		StatisticianBlockListener _bl = new StatisticianBlockListener(eventDataHandlerPlayer);
		StatisticianEntityListener _el = new StatisticianEntityListener(eventDataHandlerPlayer);
		
		PluginManager pm = getServer().getPluginManager();
		
		/*
		// For Debugging Register all events
		// All Player Actions
		for (int iEventTypes = 0; iEventTypes < Event.Type.values().length; ++iEventTypes) {
			if (Event.Type.values()[iEventTypes].getCategory() == Category.PLAYER) {
				// This is a Player Event Register It
				if (Event.Type.values()[iEventTypes] == Type.PLAYER_INVENTORY) continue; // Not Yet Supported
				pm.registerEvent(Event.Type.values()[iEventTypes], _pl, Event.Priority.Normal, this);
			}
			if (Event.Type.values()[iEventTypes].getCategory() == Category.BLOCK) {
				// Block Listnener Register
				pm.registerEvent(Event.Type.values()[iEventTypes], _bl, Event.Priority.Normal, this);
			}
			if (Event.Type.values()[iEventTypes].getCategory() == Category.ENTITY || Event.Type.values()[iEventTypes].getCategory() == Category.LIVING_ENTITY){
				pm.registerEvent(Event.Type.values()[iEventTypes], _el, Event.Priority.Normal, this);
			}
		}
		*/
		
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
		
	}
	
	public ExecutorService getExecutor() {
		return executor;
	}
	
	public static StatisticianPlugin getEnabledPlugin() {
		return _singleton;
	}
	
	public PlayerData getPlayerData() {
		return _playerData;
	}

}
