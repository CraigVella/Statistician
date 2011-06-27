package com.ChaseHQ.Statistician.Database;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ChaseHQ.Statistician.Log;
import com.ChaseHQ.Statistician.Config.Config;
import com.ChaseHQ.Statistician.Database.DataValues.StatDBDataValues_Config;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class StatDB {
	
	private static StatDB _singletonDB = null;
	
	private Connection connection = null;
	
	static public StatDB getDB() {
		if (_singletonDB == null) {
			try {
				new StatDB();
			} catch (ClassNotFoundException e) {
				Log.ConsoleLog("Critical Error, mySQL Driver not found. Is this the latest version of CraftBukkit?!");
			} catch (StatDBConnectFail e) {
				Log.ConsoleLog("Critical Error, could not connect to mySQL. Is the database Available? Check config file and try again.");
			}
		}
		
		return _singletonDB;
	}
	
	public StatDB() throws ClassNotFoundException, StatDBConnectFail {
		if (_singletonDB != null) return;
		
		// Connect To DB And Hold info
		Class.forName("com.mysql.jdbc.Driver");
		ConnectToDB();
		new StatDBConfig(connection);
		
		_singletonDB = this;
		
		StatDBDataValues_Config.refreshConfigValues();
	}
	
	private void ConnectToDB() throws StatDBConnectFail {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://" + Config.getConfig().get_dbAddress() + ":" + Config.getConfig().get_dbPort() + "/" + Config.getConfig().get_dbName(),
					Config.getConfig().get_dbUsername(), Config.getConfig().get_dbPassword());
		} catch (SQLException e) {
			throw new StatDBConnectFail();
		}
	}
	
	public boolean executeSynchUpdate(String theQuery) {
		int rowsChanged = 0;
		
		try {
			rowsChanged = connection.createStatement().executeUpdate(theQuery);
		} catch (SQLException e) {
			Log.ConsoleLog(theQuery + " :: Update Failed, Checking Connection");
			checkConnectionTryReconnect();
			return false;
		}
		
		if (rowsChanged > 0)
			return true;
		return false;
	}
	
	public List<Map<String,String>> executeSynchQuery(String theQuery) {
		List<Map<String,String>> ColData = new ArrayList<Map<String,String>>();
		
		try {
			Statement stmnt = connection.createStatement();
			ResultSet rs = stmnt.executeQuery(theQuery);
			int Row = 0;
			while (rs.next()) {
				HashMap<String,String> rowToAdd = new HashMap<String,String>();
				for (int x = 1; x <= rs.getMetaData().getColumnCount(); ++x) {
					rowToAdd.put(rs.getMetaData().getColumnName(x), rs.getString(x));
				}
				ColData.add(rowToAdd);
				++Row;
			}
		} catch (SQLException e) {
			Log.ConsoleLog(theQuery + " :: Query Failed, Checking Connection");
			checkConnectionTryReconnect();
			return null;
		}
		return ColData;
	}
	
	public boolean callStoredProcedure(String procName,List<String> variables) {
		String storedProcCall = "CALL " + Config.getConfig().get_dbName() + "." + procName + "(";
		if (variables != null) {
			Iterator<String> itr = variables.iterator();
			while (itr.hasNext()) {
				String thisVariable = itr.next();
				storedProcCall += "'" + thisVariable + "',";
			}
			storedProcCall = storedProcCall.substring(0, storedProcCall.length() - 1);
		}
		storedProcCall += ");";
		try {
			connection.createStatement().executeUpdate(storedProcCall);
		} catch (SQLException e) {
			Log.ConsoleLog(storedProcCall + " :: Stored Procedure Failed, Checking Connection");
			checkConnectionTryReconnect();
			return false;
		}
		
		return true;
	}
	
	private void checkConnectionTryReconnect() {
		try {
			if (connection.isValid(10)){
				Log.ConsoleLog("Connection is still present... It may of been a malformed Query ?");
			} else {
				Log.ConsoleLog("Connection has been lost with Database, Attempting Reconnect.");
				try {
					connection = DriverManager.getConnection("jdbc:mysql://" + Config.getConfig().get_dbAddress() + ":" + Config.getConfig().get_dbPort() + "/" + Config.getConfig().get_dbName(),
							Config.getConfig().get_dbUsername(), Config.getConfig().get_dbPassword());
					Log.ConsoleLog("Connection to the database re-established, We lost some stats though :(");
				} catch (SQLException connect_e) {
					Log.ConsoleLog("Could Not Reconnect :( Stats are going to be lost :(");
				}
			}
		} catch (SQLException e) {
			Log.ConsoleLog("Connection has been lost with Database, Attempting Reconnect.");
			try {
				connection = DriverManager.getConnection("jdbc:mysql://" + Config.getConfig().get_dbAddress() + ":" + Config.getConfig().get_dbPort() + "/" + Config.getConfig().get_dbName(),
						Config.getConfig().get_dbUsername(), Config.getConfig().get_dbPassword());
				Log.ConsoleLog("Connection to the database re-established, We lost some stats though :(");
			} catch (SQLException connect_e) {
				Log.ConsoleLog("Could Not Reconnect :( Stats are going to be lost :(");
			}
		}
	}
}
