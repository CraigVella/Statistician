package com.ChaseHQ.Statistician.Database.DataValues;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;

public enum StatDBStaticValue_Projectiles implements IStaticValue {
	NONE(0),
	ARROW(1);

	private final Integer _id;
	
	private StatDBStaticValue_Projectiles(Integer id) {
		_id = id;
	}
	
	@Override
	public Integer getID() {
		return _id;
	}
	
	public static StatDBStaticValue_Projectiles mapProjectile(Entity projectile) {
		
		if (projectile instanceof Arrow)
			return StatDBStaticValue_Projectiles.ARROW;
		
		return StatDBStaticValue_Projectiles.NONE;
	}

}
