package com.noah.breakit.entity.mob.brick;

import com.noah.breakit.entity.mob.ball.Ball;
import com.noah.breakit.entity.mob.decoration.FloatingText;
import com.noah.breakit.entity.mob.powerup.PowerupSpawner;
import com.noah.breakit.entity.mob.projectile.Projectile;
import com.noah.breakit.entity.spawner.ParticleSpawner;
import com.noah.breakit.graphics.Screen;
import com.noah.breakit.sound.SoundFX;

public class DestructibleBrick extends Brick {
	private int life = 1;

	public DestructibleBrick(int x, int y) {
		super(x, y);
		int[] colors = { 0xff0000, 0xff00ff, 0x0000ff, 0x00ffff, 0x00ff00, 0xffff00 };
		col = colors[(y / 8 - 2) % 6];
	}

	public void update() {
	}

	public void processCollision(Ball b) {
		if (--life == 0) {
			int points = 100 * b.multiplier++;
			processDeath(points);
		}
	}
	
	public void processCollision(Projectile p) {
		if (--life == 0) {
			int points = 100;
			processDeath(points);
		}
	}
	
	private void processDeath(int points) {
		remove();
		playField.setStagePattern(((x - 1) >> 4) + ((y >> 3) - 2) * 10, '0');
		playField.addSpawner(new ParticleSpawner(x + (width >> 1), y + (width >> 1), 50));
		playField.addDecoration(new FloatingText(x, y + 1, points));
		playField.getPlayer().addToScore(points);
		if(random.nextInt(10) == 0)
			playField.addPowerup(PowerupSpawner.spawnPowerup(random.nextInt(5), x, y));
		SoundFX.EXPLODE_1.play();
	}

	public void render(Screen screen) {
		if (life == 2)
			screen.fillRect(x, y, width, height, col);
		else
			screen.drawRect(x, y, width, height, col);
	}
}