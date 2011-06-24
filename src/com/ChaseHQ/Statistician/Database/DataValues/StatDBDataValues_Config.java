package com.ChaseHQ.Statistician.Database.DataValues;

import java.util.List;
import java.util.Map;

import com.ChaseHQ.Statistician.Config.Config;
import com.ChaseHQ.Statistician.Database.StatDBSynchDataGetSet;

public enum StatDBDataValues_Config implements IDataValues {
	DATABASE_VERSION ("dbVersion"),
	SHOW_FIRSTJOIN_WELCOME ("show_firstjoin_welcome"),
	FIRSTJOIN_WELCOME_MSG ("firstjoin_welcome_msg"),
	SHOW_LASTJOIN_WELCOME ("show_lastjoin_welcome"),
	LASTJOIN_WELCOME_MSG ("lastjoin_welcome_message"),
	DATE_FORMAT("date_format");
	
	private final String columnName;
	private String configValue;

	private StatDBDataValues_Config(String ColName) {
		this.columnName = ColName;
	}
	
	public String getColumnName() {
		return columnName;
	}
	
	public String getConfigValueString() {
		return configValue;
	}
	
	public Boolean getConfigValueBoolean() {
		if (configValue.equalsIgnoreCase("Y"))
			return true;
		return false;
	}
	
	public Integer getConfigValueInteger() {
		try {
			return Integer.parseInt(configValue);
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	
	public void __internalSetConfigVal(String value) {
		this.configValue = value;
	}
	
	public static void refreshConfigValues() {
		List<Map<String,String>> results = StatDBSynchDataGetSet.getValues(StatDBDataStores.CONFIGURATION, StatDBDataValues_Config.DATABASE_VERSION, Config.DBVersion.toString());
		for (int x = 0; x < StatDBDataValues_Config.values().length; ++x) {
			StatDBDataValues_Config.values()[x].__internalSetConfigVal(results.get(0).get(StatDBDataValues_Config.values()[x].columnName));
		}
	}

	@Override
	public StatDBDataStores belongsToStore() {
		return StatDBDataStores.CONFIGURATION;
	}
}
