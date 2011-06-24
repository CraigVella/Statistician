package com.ChaseHQ.Statistician.Stats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Location;

//import com.ChaseHQ.Statistician.Log;
import com.ChaseHQ.Statistician.Database.StatDBSynchDataGetSet;
import com.ChaseHQ.Statistician.Database.DataValues.StatDBDataStores;
import com.ChaseHQ.Statistician.Database.DataValues.StatDBDataValues_Players;

public class PlayerData implements IProcessable {
	
	private HashMap<String,_InternalPlayer> _watchedPlayers = new HashMap<String,_InternalPlayer>();
	
	public synchronized void addPlayerToWatch(String UUID, Location loc) {
		// See if one still lives
		_InternalPlayer ip = _watchedPlayers.get(UUID);
		if (ip == null) {
			_watchedPlayers.put(UUID, new _InternalPlayer(UUID, loc));
		} else {
			ip.RenewMe = true;
			ip.RenewMeTime = System.currentTimeMillis() / 1000;
		}
	}
	
	public synchronized void removePlayerToWatch(String UUID) {
		_InternalPlayer ip = _watchedPlayers.get(UUID);
		Long logoutTime = System.currentTimeMillis() / 1000;
		if (ip != null) {
			if (ip.RenewMe) {
				ip.TimeAlteration += (logoutTime.intValue() - ip.LastUpdateTime.intValue()) - (logoutTime.intValue() - ip.RenewMeTime.intValue());
				ip.RenewMe = false;
				ip.LastUpdateTime = logoutTime;
			}
			ip.DestroyAndCleanup = true;
			ip.LogOutTime = logoutTime;
		}
	}

	public synchronized void addBlockBreak(String UUID, Integer blockID) {
		_InternalPlayer ip = _watchedPlayers.get(UUID);
		if (ip == null) {
			//Log.ConsoleLog("Tried to set data values on player that was not added to watch '" + UUID + "'");
			return;
		}
		Integer pbb = ip.BlockDestroyed.get(blockID);
		if (pbb == null)
			pbb = new Integer(0);

		pbb++;
		
		ip.BlockDestroyed.put(blockID, pbb);
	}
	
	public void incrementStepsTaken(String UUID, Location loc, boolean inMinecart, boolean onPig, boolean inBoat) {
		_InternalPlayer ip = _watchedPlayers.get(UUID);
		if (ip == null) {
			//Log.ConsoleLog("Tried to set data values on player that was not added to watch '" + UUID + "'");
			return;
		}
		try {
			int newDist = (int) ip.LastLocation.distance(loc);
			if (newDist > 0) {
				ip.Distance += newDist;
				if (inMinecart)
					ip.DistanceInMinecart += newDist;
				if (onPig)
					ip.DistanceOnPig += newDist;
				if (inBoat)
					ip.DistanceInBoat += newDist;
				ip.LastLocation = loc;
			}
		} catch (IllegalArgumentException e) {
			ip.LastLocation = loc;
		}
	}
	
	public synchronized void addBlockPlaced(String UUID, Integer blockID) {
		_InternalPlayer ip = _watchedPlayers.get(UUID);
		if (ip == null) {
			//Log.ConsoleLog("Tried to set data values on player that was not added to watch '" + UUID + "'");
			return;
		}
		Integer pbb = ip.BlockPlaced.get(blockID);
		if (pbb == null)
			pbb = new Integer(0);

		pbb++;
		
		ip.BlockPlaced.put(blockID, pbb);
	}
	
	public synchronized void addKillTag(String UUID, KillTag kt) {
		_InternalPlayer ip = _watchedPlayers.get(UUID);
		if (ip == null) {
			//Log.ConsoleLog("Tried to set data values on player that was not added to watch '" + UUID + "'");
			return;
		}
		ip.KillTags.add(kt);
	}
	
	public synchronized void addItemPickup(String UUID, Integer itemID, Integer amount) {
		_InternalPlayer ip = _watchedPlayers.get(UUID);
		if (ip == null) {
			//Log.ConsoleLog("Tried to set data values on player that was not added to watch '" + UUID + "'");
			return;
		}
		Integer itemStore = ip.ItemPickup.get(itemID);
		if (itemStore == null) {
			itemStore = new Integer(0);
		}
		itemStore += amount;
		ip.ItemPickup.put(itemID, itemStore);
	}
	
	public synchronized void addItemDropped(String UUID, Integer itemID, Integer amount) {
		_InternalPlayer ip = _watchedPlayers.get(UUID);
		if (ip == null) {
			//Log.ConsoleLog("Tried to set data values on player that was not added to watch '" + UUID + "'");
			return;
		}
		Integer itemStore = ip.ItemDropped.get(itemID);
		if (itemStore == null) {
			itemStore = new Integer(0);
		}
		itemStore += amount;
		ip.ItemDropped.put(itemID, itemStore);
	}
	
	@Override
	public synchronized void _processData() {
		List<_InternalPlayer> playersToRemove = new ArrayList<_InternalPlayer>();
		
		for (_InternalPlayer intP : _watchedPlayers.values()) {
			// Do work, insert into DB
			Long currentTimeStamp = System.currentTimeMillis() / 1000;
			int timeSpentOnSinceLast = 0;
			if (intP.DestroyAndCleanup) {
				timeSpentOnSinceLast = (currentTimeStamp.intValue() - intP.LastUpdateTime.intValue()) - (currentTimeStamp.intValue() - intP.LogOutTime.intValue());
				if (intP.RenewMe) {
					timeSpentOnSinceLast += (currentTimeStamp.intValue() - intP.RenewMeTime.intValue());
				}
			} else {
				timeSpentOnSinceLast = currentTimeStamp.intValue() - intP.LastUpdateTime.intValue();
			}
	
			timeSpentOnSinceLast += intP.TimeAlteration;
			
			// Database Calls
			
			StatDBSynchDataGetSet.incrementValue(StatDBDataStores.PLAYER, StatDBDataValues_Players.NUM_SECS_LOGGED, 
					timeSpentOnSinceLast, StatDBDataValues_Players.UUID, intP.UUID);
			
			if (intP.Distance > 0)
				StatDBSynchDataGetSet.incrementValue(StatDBDataStores.PLAYER, StatDBDataValues_Players.DISTANCE_TRAVELED,
					intP.Distance,StatDBDataValues_Players.UUID, intP.UUID);
			
			if (intP.DistanceInMinecart > 0)
				StatDBSynchDataGetSet.incrementValue(StatDBDataStores.PLAYER, StatDBDataValues_Players.DISTANCE_TRAVELED_IN_MINECART,
						intP.DistanceInMinecart,StatDBDataValues_Players.UUID, intP.UUID);
			
			if (intP.DistanceOnPig > 0)
				StatDBSynchDataGetSet.incrementValue(StatDBDataStores.PLAYER, StatDBDataValues_Players.DISTANCE_TRAVELED_ON_PIG,
						intP.DistanceOnPig,StatDBDataValues_Players.UUID, intP.UUID);
			
			if (intP.DistanceInBoat > 0) 
				StatDBSynchDataGetSet.incrementValue(StatDBDataStores.PLAYER, StatDBDataValues_Players.DISTANCE_TRAVELED_IN_BOAT,
						intP.DistanceInBoat,StatDBDataValues_Players.UUID, intP.UUID);
			
			for (Integer BlockID : intP.BlockDestroyed.keySet()) {
				Integer smashValue = intP.BlockDestroyed.get(BlockID);
				StatDBSynchDataGetSet.incrementBlockDestroy(intP.UUID, BlockID, smashValue);
			}
			
			for (Integer BlockID : intP.BlockPlaced.keySet()) {
				Integer putValue = intP.BlockPlaced.get(BlockID);
				StatDBSynchDataGetSet.incrementBlockPlaced(intP.UUID, BlockID, putValue);
			}
			
			for (KillTag kt : intP.KillTags) {
				StatDBSynchDataGetSet.newKill(kt);
			}
			
			for (Integer itemID : intP.ItemPickup.keySet()) {
				Integer pickedUp = intP.ItemPickup.get(itemID);
				StatDBSynchDataGetSet.incrementItemPickup(intP.UUID, itemID, pickedUp);
			}
			
			for (Integer itemID : intP.ItemDropped.keySet()) {
				Integer dropped = intP.ItemDropped.get(itemID);
				StatDBSynchDataGetSet.incrementItemDrop(intP.UUID, itemID, dropped);
			}
			
			// End of Database Calls

			intP.LastUpdateTime = currentTimeStamp;
			
			if (intP.DestroyAndCleanup) {
				if (intP.RenewMe) {
					intP.DestroyAndCleanup = false;
					intP.RenewMe = false;
				} else {
					playersToRemove.add(intP);
				}
			}
			
			intP._resetInternal(); // reset values
		}
		for (_InternalPlayer intP : playersToRemove) {
			_watchedPlayers.remove(intP.UUID);
		}
	}
}
