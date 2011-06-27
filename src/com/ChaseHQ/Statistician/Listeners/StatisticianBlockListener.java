package com.ChaseHQ.Statistician.Listeners;

import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.ChaseHQ.Statistician.EventDataHandlers.EDHPlayer;

public class StatisticianBlockListener extends BlockListener {
	private EDHPlayer edhPlayer;
	
	public StatisticianBlockListener(EDHPlayer passedEDH) {
		edhPlayer = passedEDH;
	}
	
	@Override
	public void onBlockBreak(BlockBreakEvent event) {
		edhPlayer.PlayerBlockBreak(event.getPlayer(), event.getBlock().getTypeId());
	}
	
	@Override
	public void onBlockPlace(BlockPlaceEvent event) {
		if (event.getBlock().getType() != Material.AIR)
			edhPlayer.PlayerBlockPlace(event.getPlayer(), event.getBlock().getTypeId());
	}
	
}
