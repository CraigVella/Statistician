package com.ChaseHQ.Statistician.Database;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.ChaseHQ.Statistician.Database.DataValues.IDataValues;
import com.ChaseHQ.Statistician.Database.DataValues.StatDBDataStores;
import com.ChaseHQ.Statistician.Database.DataValues.StatDBDataValues_Players;
import com.ChaseHQ.Statistician.Stats.KillTag;

public abstract class StatDBSynchDataGetSet {
	
	public static String getValue(StatDBDataStores dataStore, IDataValues dataStoreValue, IDataValues controlStoreValue, String valueEquals) {
		List<Map<String,String>> results =
		StatDB.getDB().executeSynchQuery("SELECT " + dataStoreValue.getColumnName() + " FROM " + 
				dataStore.getTableName() + " WHERE " + controlStoreValue.getColumnName() + 
				" = '" + valueEquals + "' LIMIT 1");
		if (results != null) {
			if (results.size() > 0) {
				if (results.get(0).containsKey(dataStoreValue.getColumnName())) {
					return results.get(0).get(dataStoreValue.getColumnName());
				} 
			}
		}
		return null;
	}
	
	public static List<String> getValues(StatDBDataStores dataStore, IDataValues dataStoreValue, IDataValues controlStoreValue, String valueEquals) {
		List<Map<String,String>> results =
			StatDB.getDB().executeSynchQuery("SELECT " + dataStoreValue.getColumnName() + " FROM " + 
					dataStore.getTableName() + " WHERE " + controlStoreValue.getColumnName() + 
					" = '" + valueEquals + "'");
		
		if (results != null) {
			if (results.size() > 0) {
				if (results.get(0).containsKey(dataStoreValue.getColumnName())) {
					List<String> returnString = new ArrayList<String>();
					Iterator<Map<String,String>> itr = results.iterator();
					while (itr.hasNext()) {
						returnString.add(itr.next().get(dataStoreValue.getColumnName()));
					}
					return returnString;
				} 
			}
		}
		return null;
	}
	
	public static List<Map<String,String>> getValues(StatDBDataStores dataStore, IDataValues controlStoreValue, String valueEquals) {
		return StatDB.getDB().executeSynchQuery("SELECT * FROM " + dataStore.getTableName() + 
				" WHERE " + controlStoreValue.getColumnName() + " = '" + valueEquals + "'");
	}
	
	public static List<Map<String,String>> customQuery(String customQuery) {
		return StatDB.getDB().executeSynchQuery(customQuery);
	}
	
	public static boolean customUpdateQuery(String customQuery) {
		return StatDB.getDB().executeSynchUpdate(customQuery);
	}
	
	public static boolean setValue(StatDBDataStores dataStore,IDataValues storeValue, String value, IDataValues controlStoreValue, String valueEquals) {
		return StatDB.getDB().executeSynchUpdate("UPDATE " + dataStore.getTableName() + " SET " + storeValue.getColumnName()
				+ " = '" + value + "' WHERE " + controlStoreValue.getColumnName() + " = '" + valueEquals + "'");
	}
	
	public static boolean incrementValue(StatDBDataStores dataStore,IDataValues storeValue, Integer byVal, IDataValues controlStoreValue, String valueEquals, IDataValues controlStoreValue2, String valueEquals2) {
		return StatDB.getDB().executeSynchUpdate("UPDATE " + dataStore.getTableName() + " SET " + storeValue.getColumnName()
				+ " = " + storeValue.getColumnName() + " + " + byVal + " WHERE " + controlStoreValue.getColumnName() + " = '" + valueEquals + "'" + " AND " + controlStoreValue2.getColumnName() + " = '" + valueEquals2 + "'");
	}
	
	public static boolean incrementValue(StatDBDataStores dataStore,IDataValues storeValue, Integer byVal, IDataValues controlStoreValue, String valueEquals) {
		return StatDB.getDB().executeSynchUpdate("UPDATE " + dataStore.getTableName() + " SET " + storeValue.getColumnName()
				+ " = " + storeValue.getColumnName() + " + " + byVal + " WHERE " + controlStoreValue.getColumnName() + " = '" + valueEquals + "'");
	}
	
	public static boolean incrementValue(StatDBDataStores dataStore,IDataValues storeValue, IDataValues controlStoreValue, String valueEquals) {
		return incrementValue(dataStore,storeValue,1,controlStoreValue,valueEquals);
	}
	
	public static boolean incrementValue(StatDBDataStores dataStore,IDataValues storeValue, IDataValues controlStoreValue, String valueEquals, IDataValues controlStoreValue2, String valueEquals2) {
		return incrementValue(dataStore,storeValue,1,controlStoreValue,valueEquals,controlStoreValue2,valueEquals2);
	}
	
	public static boolean decrementValue(StatDBDataStores dataStore,IDataValues storeValue, Integer byVal, IDataValues controlStoreValue, String valueEquals) {
		return StatDB.getDB().executeSynchUpdate("UPDATE " + dataStore.getTableName() + " SET " + storeValue.getColumnName()
				+ " = " + storeValue.getColumnName() + " - " + byVal + " WHERE " + controlStoreValue.getColumnName() + " = '" + valueEquals + "'");
	}
	
	public static boolean decrementValue(StatDBDataStores dataStore,IDataValues storeValue, Integer byVal, IDataValues controlStoreValue, String valueEquals, IDataValues controlStoreValue2, String valueEquals2) {
		return StatDB.getDB().executeSynchUpdate("UPDATE " + dataStore.getTableName() + " SET " + storeValue.getColumnName()
				+ " = " + storeValue.getColumnName() + " - " + byVal + " WHERE " + controlStoreValue.getColumnName() + " = '" + valueEquals + "'" + " AND " + controlStoreValue2.getColumnName() + " = '" + valueEquals2 + "'");
	}
	
	public static boolean decrementValue(StatDBDataStores dataStore,IDataValues storeValue, IDataValues controlStoreValue, String valueEquals) {
		return decrementValue(dataStore,storeValue,1,controlStoreValue,valueEquals);
	}
	
	public static boolean decrementValue(StatDBDataStores dataStore,IDataValues storeValue, IDataValues controlStoreValue, String valueEquals, IDataValues controlStoreValue2, String valueEquals2) {
		return decrementValue(dataStore,storeValue,1,controlStoreValue,valueEquals,controlStoreValue2,valueEquals2);
	}
	
	public static boolean batchSetValue(StatDBDataStores dataStore,IDataValues storeValue, List<Map<IDataValues,String>> batchValues, IDataValues controlStoreValue, String valueEquals) {
		// Build Query
		String Query = "UPDATE " + dataStore.getTableName() + " SET ";
		
		Iterator<Map<IDataValues, String>> itr = batchValues.iterator();
		while (itr.hasNext()) {
			Map<IDataValues,String> thisMap = itr.next();
			Entry<IDataValues,String> entry = thisMap.entrySet().iterator().next();
			Query += entry.getKey().getColumnName() + " = '" + entry.getValue() + "' ,";
		}
		Query = Query.substring(0, Query.length() - 1);
		
		Query += " WHERE " + controlStoreValue.getColumnName() + " = '" + valueEquals + "'";
		
		return StatDB.getDB().executeSynchUpdate(Query);
	}
	
	public static boolean batchIncrementDecrement(StatDBDataStores dataStore,IDataValues storeValue, List<Map<IDataValues,Integer>> batchValues, IDataValues controlStoreValue, String valueEquals) {
		// Build Query
		String Query = "UPDATE " + dataStore.getTableName() + " SET ";
		
		Iterator<Map<IDataValues, Integer>> itr = batchValues.iterator();
		while (itr.hasNext()) {
			Map<IDataValues,Integer> thisMap = itr.next();
			Entry<IDataValues,Integer> entry = thisMap.entrySet().iterator().next();
			if (entry.getValue() > 0) {
				Query += entry.getKey().getColumnName() + " = " + entry.getKey().getColumnName() + " + " + entry.getValue() + " ,";
			} else {
				Query += entry.getKey().getColumnName() + " = " + entry.getKey().getColumnName() + " - " + (entry.getValue() * -1) + " ,";
			}
		}
		Query = Query.substring(0, Query.length() - 1);
		
		Query += " WHERE " + controlStoreValue.getColumnName() + " = '" + valueEquals + "'";
		
		return StatDB.getDB().executeSynchUpdate(Query);
	}
	
	public static boolean isPlayerInDB(String UUID) {
		if (getValue(StatDBDataStores.PLAYER, StatDBDataValues_Players.PLAYER_NAME, StatDBDataValues_Players.UUID, UUID) != null)
			return true;
		return false;
	}
	
	public static boolean incrementBlockDestroy(String UUID, Integer blockID, Integer numDestroyed) {
		List<String> vars = new ArrayList<String>();
		vars.add(UUID);
		vars.add(blockID.toString());
		vars.add(numDestroyed.toString());
		return StatDB.getDB().callStoredProcedure("incrementBlockDestroy", vars);
	}
	
	public static boolean incrementBlockPlaced(String UUID, Integer blockID, Integer numPlaced) {
		List<String> vars = new ArrayList<String>();
		vars.add(UUID);
		vars.add(blockID.toString());
		vars.add(numPlaced.toString());
		return StatDB.getDB().callStoredProcedure("incrementBlockPlaced", vars);
	}
	
	public static boolean playerCreate(String UUID, String PlayerName) {
		// This will prob get called synch allot, so it's created as a stored proc, offloaded to the DB
		List<String> vars = new ArrayList<String>();
		vars.add(UUID);
		vars.add(PlayerName);
		return StatDB.getDB().callStoredProcedure("newPlayer", vars);
	}
	
	public static boolean playerLogin(String UUID) {
		List<String>vars = new ArrayList<String>();
		vars.add(UUID);
		return StatDB.getDB().callStoredProcedure("loginPlayer", vars);
	}
	
	public static boolean playerLogout(String UUID) {
		List<String>vars = new ArrayList<String>();
		vars.add(UUID);
		return StatDB.getDB().callStoredProcedure("logoutPlayer", vars);
	}
	
	public static boolean newKill (KillTag kt) {
		List<String>vars = new ArrayList<String>();
		vars.add(kt.Killed.getID().toString());
		vars.add(kt.KilledBy.getID().toString());
		vars.add(kt.KillType.getID().toString());
		vars.add(kt.KilledUsing.toString());
		vars.add(kt.KillProjectile.getID().toString());
		vars.add(kt.KilledBy_UUID);
		vars.add(kt.Killed_UUID);
		return StatDB.getDB().callStoredProcedure("newKill", vars);
	}
	
	public static boolean incrementItemPickup(String UUID, Integer itemID, Integer numPickedUp) {
		List<String>vars = new ArrayList<String>();
		vars.add(UUID);
		vars.add(itemID.toString());
		vars.add(numPickedUp.toString());
		return StatDB.getDB().callStoredProcedure("incrementPickedup", vars);
	}
	
	public static boolean incrementItemDrop(String UUID, Integer itemID, Integer numDropped) {
		List<String>vars = new ArrayList<String>();
		vars.add(UUID);
		vars.add(itemID.toString());
		vars.add(numDropped.toString());
		return StatDB.getDB().callStoredProcedure("incrementDropped", vars);
	}
}
