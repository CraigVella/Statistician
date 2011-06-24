package com.ChaseHQ.Statistician.Listeners;

import org.bukkit.entity.Creature;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageByProjectileEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityListener;

import com.ChaseHQ.Statistician.EventDataHandlers.EDHPlayer;

public class StatisticianEntityListener extends EntityListener{
	private EDHPlayer edhPlayer;
	
	public StatisticianEntityListener(EDHPlayer passedEDH) {
		edhPlayer = passedEDH;
	}
	
	@Override
	public void onEntityDeath(EntityDeathEvent event) {
		Player playerVictim = null;
		Player playerKiller = null;
		DamageCause cause = event.getEntity().getLastDamageCause().getCause();
		
		if (event.getEntity() instanceof Player) {
			playerVictim = (Player)event.getEntity();
			if (event.getEntity().getLastDamageCause() instanceof EntityDamageByProjectileEvent) {
				if (((EntityDamageByEntityEvent) event.getEntity().getLastDamageCause()).getDamager() instanceof Player) {
					playerKiller = (Player)((EntityDamageByEntityEvent) event.getEntity().getLastDamageCause()).getDamager();
					edhPlayer.PlayerKilledByPlayerProjectile(playerKiller, playerVictim, ((EntityDamageByProjectileEvent)event.getEntity().getLastDamageCause()).getProjectile(),cause);
				} else {
					edhPlayer.PlayerKilledByCreatureProjectile(playerVictim, (Creature) ((EntityDamageByEntityEvent) event.getEntity().getLastDamageCause()).getDamager(), ((EntityDamageByProjectileEvent)event.getEntity().getLastDamageCause()).getProjectile(),cause);
				}
			} else if (event.getEntity().getLastDamageCause() instanceof EntityDamageByEntityEvent) {
				if (((EntityDamageByEntityEvent) event.getEntity().getLastDamageCause()).getDamager() instanceof Player) {
					playerKiller = (Player)((EntityDamageByEntityEvent) event.getEntity().getLastDamageCause()).getDamager();
					edhPlayer.PlayerKilledByPlayer(playerKiller, playerVictim,cause);
				} else {
					edhPlayer.PlayerKilledByCreature(playerVictim, (Creature) ((EntityDamageByEntityEvent) event.getEntity().getLastDamageCause()).getDamager(),cause);
				}
			} else if (event.getEntity().getLastDamageCause() instanceof EntityDamageByBlockEvent) {
				edhPlayer.PlayerKilledByBlock(playerVictim, ((EntityDamageByBlockEvent)event.getEntity().getLastDamageCause()).getDamager(),cause);
			} else {
				edhPlayer.PlayerKilledByOtherCause(playerVictim, event.getEntity().getLastDamageCause().getCause());
			}
		} else {
			if (event.getEntity().getLastDamageCause() instanceof EntityDamageByProjectileEvent) {
				if (((EntityDamageByEntityEvent)event.getEntity().getLastDamageCause()).getDamager() instanceof Player) {
					playerKiller = (Player)((EntityDamageByEntityEvent) event.getEntity().getLastDamageCause()).getDamager();
					edhPlayer.PlayerKilledCreatureProjectile(playerKiller, (Creature) event.getEntity(),((EntityDamageByProjectileEvent)event.getEntity().getLastDamageCause()).getProjectile(),cause);
				} 
			} else if (event.getEntity().getLastDamageCause() instanceof EntityDamageByEntityEvent) {
				if (((EntityDamageByEntityEvent)event.getEntity().getLastDamageCause()).getDamager() instanceof Player) {
					playerKiller = (Player)((EntityDamageByEntityEvent) event.getEntity().getLastDamageCause()).getDamager();
					edhPlayer.PlayerKilledCreature(playerKiller, (Creature) event.getEntity(),cause);
				} 
			} 
		}
	}
}
