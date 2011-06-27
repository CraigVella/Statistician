package com.ChaseHQ.Statistician.Config;

import java.io.File;
import java.io.IOException;

import org.bukkit.util.config.Configuration;

import com.ChaseHQ.Statistician.Log;


public class Config {
	public static final String StatisticianVersion = "1.2.1";
	public static final Integer DBVersion = 2;
	public static final String LogPrefix = "[Statistician]";
	public static final String MainDirectory = "plugins/Statistician/";
	private static File _configFile = new File(MainDirectory + "DBConfig.yml");
	
	private org.bukkit.util.config.Configuration _config;
	
	private String _dbAddress = "localhost";
	private int _dbPort = 3306;
	private String _dbName = "mc_statistician";
	private String _dbUsername = "mc_statistician";
	private String _dbPassword = "mc_statistician";
	private int _databaseUpdateTime = 120;
	
	private static Config _internalConfig = null;
	
	public static Config getConfig() {
		if (_internalConfig == null) {
			// Instantiate
			try {
				new Config();
			} catch (IOException e) {
				Log.ConsoleLog("Could not load/write Database Config file. Fatal error.");
				return null;
			}
		}
		
		return _internalConfig;
	}
	
	public Config() throws IOException {
		
		if (_internalConfig != null)
			return;
		
		if (!_configFile.exists()) {
			// No config file exists create
			new File(MainDirectory).mkdir();
			
			_configFile.createNewFile();
	
			// Created New File
			_config = new Configuration(_configFile);
			_config.load();
			
			_config.setProperty("database_address", _dbAddress);
			_config.setProperty("database_port", _dbPort);
			_config.setProperty("database_name", _dbName);
			_config.setProperty("database_username", _dbUsername);
			_config.setProperty("database_password", _dbPassword);
			_config.setProperty("database_update_time", _databaseUpdateTime);
			
			_config.save();
			
		} else {	
			_config = new Configuration(_configFile);
			_config.load();
		}
		
		_dbAddress  = (String)_config.getProperty("database_address");
		_dbPort     = (Integer)_config.getProperty("database_port");
		_dbName     = (String)_config.getProperty("database_name");
		_dbUsername = (String)_config.getProperty("database_username");
		_dbPassword = (String)_config.getProperty("database_password");
		_databaseUpdateTime = (Integer) _config.getProperty("database_update_time");
		
		_internalConfig = this;
		
	}

	/**
	 * @return the _dbAddress
	 */
	public String get_dbAddress() {
		return _dbAddress;
	}

	/**
	 * @return the _dbPort
	 */
	public int get_dbPort() {
		return _dbPort;
	}
	
	/**
	 * @return the _databaseUpdateTime
	 */
	public int get_databaseUpdateTime() {
		return _databaseUpdateTime;
	}

	/**
	 * @return the _dbName
	 */
	public String get_dbName() {
		return _dbName;
	}

	/**
	 * @return the _dbUsername
	 */
	public String get_dbUsername() {
		return _dbUsername;
	}

	/**
	 * @return the _dbPassword
	 */
	public String get_dbPassword() {
		return _dbPassword;
	}
	
}
