package com.noah.breakit.entity.mob;

import com.noah.breakit.entity.mob.powerup.PowerupSpawner;
import com.noah.breakit.entity.spawner.ParticleSpawner;
import com.noah.breakit.graphics.Screen;
import com.noah.breakit.sound.SoundFX;

public class Target extends Mob {
	private int life = 1;

	public Target(int x, int y) {
		super(x, y);
		width = 16;
		height = 8;

		int[] colors = { 0xff0000, 0xff00ff, 0x0000ff, 0x00ffff, 0x00ff00, 0xffff00 };
		col = colors[(y / 8 - 2) % 6];
	}

	public void update() {
	}

	public void processCollision(Ball b) {
		if (--life == 0) {
			int points = 100 * b.multiplier;
			remove();
			playField.setStagePattern(((x - 1) >> 4) + ((y >> 3) - 2) * 10, '0');
			playField.addSpawner(new ParticleSpawner(x + (width >> 1), y + (width >> 1), 50));
			playField.addFloatingScore(new FloatingScore(x, y + 1, points));
			playField.getPlayer().addToScore(points);
			b.multiplier++;
			if(random.nextInt(10) == 0)
				playField.addPowerup(PowerupSpawner.spawnPowerup(random.nextInt(3), x, y));
			SoundFX.EXPLODE.play();
		}
	}

	public void render(Screen screen) {
		if (life == 2)
			screen.fillRect(x, y, width, height, col);
		else
			screen.drawRect(x, y, width, height, col);
	}
}