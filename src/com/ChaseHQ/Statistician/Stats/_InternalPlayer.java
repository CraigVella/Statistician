package com.ChaseHQ.Statistician.Stats;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Location;

public class _InternalPlayer {
	public String UUID = new String();
	public HashMap<Integer, Integer> BlockDestroyed = new HashMap<Integer,Integer>();
	public HashMap<Integer, Integer> BlockPlaced = new HashMap<Integer,Integer>();
	public HashMap<Integer, Integer> ItemPickup = new HashMap<Integer,Integer>();
	public HashMap<Integer, Integer> ItemDropped = new HashMap<Integer,Integer>();
	public ArrayList<KillTag> KillTags = new ArrayList<KillTag>();
	Integer Distance = new Integer(0);
	Integer DistanceInMinecart = new Integer(0);
	Integer DistanceInBoat = new Integer(0);
	Integer DistanceOnPig = new Integer(0);
	Location LastLocation;
	public boolean DestroyAndCleanup = false;
	public boolean RenewMe = false;
	Long LastUpdateTime = System.currentTimeMillis() / 1000;
	int TimeAlteration = 0;
	Long RenewMeTime = 0L;
	Long LogOutTime = 0L;
	
	public _InternalPlayer(String UUID, Location location) {
		this.UUID = UUID;
		this.LastLocation = location;
	}
	
	public void _resetInternal() {
		BlockDestroyed.clear();
		BlockPlaced.clear();
		Distance = 0;
		DistanceInMinecart = 0;
		DistanceInBoat = 0;
		DistanceOnPig = 0;
		TimeAlteration = 0;
		KillTags.clear();
		ItemPickup.clear();
		ItemDropped.clear();
	}
}
