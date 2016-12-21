package com.noah.breakit.entity.mob.powerup;

import com.noah.breakit.entity.mob.Mob;
import com.noah.breakit.entity.spawner.ParticleSpawner;
import com.noah.breakit.game.Game;
import com.noah.breakit.graphics.Screen;
import com.noah.breakit.sound.SoundFX;
import com.noah.breakit.util.ColorFlasher;
import com.noah.breakit.util.Util;

public abstract class Powerup extends Mob {

	static final Powerup BALL_POWERUP = new BallPowerup(0, 0);
	static final Powerup WIDTH_POWERUP = new WidthPowerup(0, 0);
	static final Powerup POWER_BALL_POWERUP = new PowerBallPowerup(0, 0);

	Powerup(int x, int y) {
		super(x, y);

		width = 16;
		height = 16;
		xdir = 0;
		ydir = 1;
		xspeed = 0;
		yspeed = 1;
		xa = 0;
		ya = ydir * yspeed;
	}

	public final void update() {

		if (!playField.getPlayer().isAlive()) return;

		int ystep = 0;
		ystep = Util.clamp(ya, -1, 1);

		for (int yi = 0; yi != ya + ystep; yi += ystep) {

			int b = y + yi + height;

			if (b > Game.HEIGHT) {
				remove();
				playField.addSpawner(new ParticleSpawner(x + width / 2, y + height / 2, 100));
				SoundFX.DEATH.play();
				break;
			}
		}

		ya = yspeed * ydir;

		y += ya;
	}

	public void render(Screen screen) {
		screen.fillRect(x, y, width, height, 0x000000);
		screen.drawRect(x, y, width, height, ColorFlasher.col);
	}

	public void processCollision() {
		SoundFX.POWER_UP.play();
		trigger();
		remove();
	}

	abstract Powerup spawnPowerup(int x, int y);

	abstract void trigger();
}