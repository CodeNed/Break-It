package com.noah.breakit.entity.mob.powerup;

import java.util.HashMap;
import java.util.Map;

public class PowerupSpawner {
		
	private static Map<Integer, Powerup> powerups = new HashMap<>();
	
	@SuppressWarnings("unused")
	private static final PowerupSpawner POWERUP_SPAWNER = new PowerupSpawner();
	
	private PowerupSpawner() {
		powerups.put(0, Powerup.FORCE_FIELD_POWERUP);
		powerups.put(1, Powerup.MULTI_BALL_POWERUP);
		powerups.put(2, Powerup.POWER_BALL_POWERUP);
		powerups.put(3, Powerup.SHOOTING_POWERUP);
		powerups.put(4, Powerup.WIDTH_POWERUP);
	}
	
	public static Powerup spawnPowerup(int key, int x, int y) {
		return powerups.get(key).spawnPowerup(x, y);
	}
}