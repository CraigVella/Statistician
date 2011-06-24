package com.ChaseHQ.Statistician.Database.DataValues;

public enum StatDBDataStores {
	CONFIGURATION ("config"),
	PLAYER ("players");
	
	private final String tableName;
	
	private StatDBDataStores(String tableName) {
		this.tableName = tableName;
	}
	
	public String getTableName() {
		return tableName;
	}
}
