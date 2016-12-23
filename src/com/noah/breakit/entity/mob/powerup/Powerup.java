package com.noah.breakit.entity.mob.powerup;

import com.noah.breakit.entity.mob.Mob;
import com.noah.breakit.entity.spawner.ParticleSpawner;
import com.noah.breakit.game.Game;
import com.noah.breakit.graphics.Font8x8;
import com.noah.breakit.graphics.Screen;
import com.noah.breakit.sound.SoundFX;
import com.noah.breakit.util.ColorFlasher;
import com.noah.breakit.util.Util;

public abstract class Powerup extends Mob {

	static final Powerup FORCE_FIELD_POWERUP = new ForceFieldPowerup(0, 0);
	static final Powerup MULTI_BALL_POWERUP = new MultiBallPowerup(0, 0);
	static final Powerup POWER_BALL_POWERUP = new PowerBallPowerup(0, 0);
	static final Powerup SHOOTING_POWERUP = new ShootingPowerup(0,0);
	static final Powerup WIDTH_POWERUP = new WidthPowerup(0, 0);

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
				SoundFX.EXPLODE_2.play();
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
	
	protected final void renderChar(Screen screen, char c) {
		screen.renderChar8x8(x + (width >> 1) - 4, y + (height >> 1) - 4, ~ColorFlasher.col, Font8x8.getChar(c));
	}

	public void processCollision() {
		SoundFX.POWER_UP.play();
		trigger();
		remove();
	}

	abstract Powerup spawnPowerup(int x, int y);

	abstract void trigger();
}