package com.ChaseHQ.Statistician.Listeners;

import org.bukkit.entity.Boat;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Pig;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.ChaseHQ.Statistician.EventDataHandlers.EDHPlayer;

public class StatisticianPlayerListener extends PlayerListener {
	private EDHPlayer edhPlayer;
	
	public StatisticianPlayerListener(EDHPlayer passedEDH) {
		edhPlayer = passedEDH;
	}
	
	@Override
	public void onPlayerJoin(PlayerJoinEvent event) {
		edhPlayer.PlayerJoin(event.getPlayer());
	}
	
	@Override
	public void onPlayerQuit(PlayerQuitEvent event) {
		edhPlayer.PlayerQuit(event.getPlayer());
	}
	
	@Override
	public void onPlayerMove(PlayerMoveEvent event) {
		boolean isInMinecart = false;
		boolean isOnPig = false;
		boolean isInBoat = false;
		
		if (event.getPlayer().isInsideVehicle())
			if (event.getPlayer().getVehicle() != null) {
				if (event.getPlayer().getVehicle() instanceof Minecart)
					isInMinecart = true;
				if (event.getPlayer().getVehicle() instanceof Pig)
					isOnPig = true;
				if (event.getPlayer().getVehicle() instanceof Boat)
					isInBoat = true;
			}

		edhPlayer.PlayerMove(event.getPlayer(), isInMinecart, isOnPig, isInBoat);
	}
	
	@Override
	public void onPlayerPickupItem(PlayerPickupItemEvent event) {
		edhPlayer.PlayerPickedUpItem(event.getPlayer(), event.getItem().getItemStack().getTypeId(), event.getItem().getItemStack().getAmount());
	}
	
	@Override
	public void onPlayerDropItem(PlayerDropItemEvent event) {
		edhPlayer.PlayerDroppedItem(event.getPlayer(), event.getItemDrop().getItemStack().getTypeId(), event.getItemDrop().getItemStack().getAmount());
	}
	
}
