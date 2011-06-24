package com.ChaseHQ.Statistician.Database.DataValues;

import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public enum StatDBStaticValue_KillTypes implements IStaticValue {
	NONE(0),
	BLOCK_EXPLOSION(1),
	DROWNING(2),
	ENTITY_EXPLOSION(3),
	FALL(4),
	FIRE(5),
	FIRE_TICK(6),
	VOID(7),
	SUFFOCATION(8),
	LIGHTENING(9),
	LAVA(10),
	CONTACT(11),
	ENTITY_ATTACK(12),
	CUSTOM(13);

	private final Integer _id;
	
	private StatDBStaticValue_KillTypes(Integer id) {
		_id = id;
	}
	
	@Override
	public Integer getID() {
		return _id;
	}
	
	public static StatDBStaticValue_KillTypes mapDamageCause(DamageCause cause) {
		
		switch (cause) {
		case BLOCK_EXPLOSION:
			return StatDBStaticValue_KillTypes.BLOCK_EXPLOSION;
		case DROWNING:
			return StatDBStaticValue_KillTypes.DROWNING;
		case ENTITY_EXPLOSION:
			return StatDBStaticValue_KillTypes.ENTITY_EXPLOSION;
		case FALL:
			return StatDBStaticValue_KillTypes.FALL;
		case FIRE:
			return StatDBStaticValue_KillTypes.FIRE;
		case FIRE_TICK:
			return StatDBStaticValue_KillTypes.FIRE_TICK;
		case VOID:
			return StatDBStaticValue_KillTypes.VOID;
		case SUFFOCATION:
			return StatDBStaticValue_KillTypes.SUFFOCATION;
		case LIGHTNING:
			return StatDBStaticValue_KillTypes.LIGHTENING;
		case LAVA:
			return StatDBStaticValue_KillTypes.LAVA;
		case CONTACT:
			return StatDBStaticValue_KillTypes.CONTACT;
		case ENTITY_ATTACK:
			return StatDBStaticValue_KillTypes.ENTITY_ATTACK;
		case CUSTOM:
			return StatDBStaticValue_KillTypes.CUSTOM;
		}
		
		return StatDBStaticValue_KillTypes.NONE;
	}
}
