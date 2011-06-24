package com.ChaseHQ.Statistician.Database.DataValues;

import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Giant;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Pig;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Squid;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.Zombie;

public enum StatDBStaticValue_Creatures implements IStaticValue {
	NONE(0),
	PLAYER(999),
	CHICKEN(1),
	COW(2),
	CREEPER(3),
	ELECTRIFIED_CREEPER(4),
	GHAST(5),
	GIANT(6),
	MONSTER(7),
	PIG(8),
	PIG_ZOMBIE(9),
	SHEEP(10),
	SKELETON(11),
	SLIME(12),
	SPIDER(13),
	SQUID(14),
	WOLF(15),
	TAME_WOLF(16),
	SPIDER_JOCKY(17), 
	BLOCK(18),
	ZOMBIE(19);

	private final Integer _id;
	
	private StatDBStaticValue_Creatures(Integer id) {
		_id = id;
	}
	
	@Override
	public Integer getID() {
		return _id;
	}
	
	public static StatDBStaticValue_Creatures mapCreature(Creature creature) {
		
		if (creature instanceof Player)
			return StatDBStaticValue_Creatures.PLAYER;
		
		if (creature instanceof Chicken)
			return StatDBStaticValue_Creatures.CHICKEN;
		
		if (creature instanceof Cow)
			return StatDBStaticValue_Creatures.COW;
		
		if (creature instanceof Creeper) {
			if (((Creeper) creature).isPowered()) {
				return StatDBStaticValue_Creatures.ELECTRIFIED_CREEPER;
			}
			return StatDBStaticValue_Creatures.CREEPER;
		}
		
		if (creature instanceof Ghast)
			return StatDBStaticValue_Creatures.GHAST;
		
		if (creature instanceof Pig)
			return StatDBStaticValue_Creatures.PIG;
		
		if (creature instanceof PigZombie)
			return StatDBStaticValue_Creatures.PIG_ZOMBIE;
		
		if (creature instanceof Sheep) 
			return StatDBStaticValue_Creatures.SHEEP;
		
		if (creature instanceof Skeleton) {
			if (((Skeleton)creature).isInsideVehicle()) {
				return StatDBStaticValue_Creatures.SPIDER_JOCKY;
			}
			return StatDBStaticValue_Creatures.SKELETON;
		}
		
		if (creature instanceof Slime) 
			return StatDBStaticValue_Creatures.SLIME;
		
		if (creature instanceof Squid)
			return StatDBStaticValue_Creatures.SQUID;
		
		if (creature instanceof Wolf) {
			if (((Wolf)creature).isTamed()) {
				return StatDBStaticValue_Creatures.TAME_WOLF;
			}
			return StatDBStaticValue_Creatures.WOLF;
		}
		
		if (creature instanceof Zombie) {
			return StatDBStaticValue_Creatures.ZOMBIE;
		}
		
		if (creature instanceof Spider) {
			return StatDBStaticValue_Creatures.SPIDER;
		}
		
		if (creature instanceof Giant)
			return StatDBStaticValue_Creatures.GIANT;
		
		if (creature instanceof Monster)
			return StatDBStaticValue_Creatures.MONSTER;
		
		return StatDBStaticValue_Creatures.NONE;
	}

}
