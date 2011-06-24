package com.ChaseHQ.Statistician.Stats;

import com.ChaseHQ.Statistician.Database.DataValues.StatDBStaticValue_Creatures;
import com.ChaseHQ.Statistician.Database.DataValues.StatDBStaticValue_KillTypes;
import com.ChaseHQ.Statistician.Database.DataValues.StatDBStaticValue_Projectiles;

public class KillTag {
	public StatDBStaticValue_Creatures Killed = StatDBStaticValue_Creatures.NONE;
	public StatDBStaticValue_Creatures KilledBy = StatDBStaticValue_Creatures.NONE;
	public StatDBStaticValue_KillTypes KillType = StatDBStaticValue_KillTypes.NONE;
	public Integer KilledUsing = -1;
	public StatDBStaticValue_Projectiles KillProjectile = StatDBStaticValue_Projectiles.NONE;
	public String KilledBy_UUID = "";
	public String Killed_UUID = "";
}
