package com.noah.breakit.entity.mob;

import com.noah.breakit.entity.spawner.Spawner;
import com.noah.breakit.graphics.Screen;
import com.noah.breakit.input.Keyboard;
import com.noah.breakit.sound.SoundFX;
import com.noah.breakit.util.Util;

public class Ball extends Mob {

	private Keyboard key;
	private boolean released;
	public int multiplier = 1;

	public Ball(int x, int y, Keyboard key) {
		super(x, y);
		this.key = key;

		width = 4;
		height = 4;
		col = 0xff00ff;
		xdir = 0;
		ydir = 0;
		xspeed = 1;
		yspeed = 2;
		xa = xdir * xspeed;
		ya = ydir * yspeed;
	}

	public void update() {

		if (!isAlive) return;

		key.update();

		if (key.space && !released) {
			released = true;
			xdir = playField.getPlayer().xdir;
			xspeed = playField.getPlayer().xspeed;
			ydir = -1;
			SoundFX.launch.play();
		}

		int xstep = 0;
		if (xa != 0) xstep = Util.clamp(xa, -1, 1);

		for (int xi = 0; xi != xa + xstep; xi += xstep) {
			int l = x + xi;
			int r = x + xi + width;

			if (l < 1 || r > playField.getWidth()) {
				xdir *= -1;
				xa *= -1;
				SoundFX.loping.play();
				break;
			}
		}

		int ystep = 0;
		if (ya != 0) ystep = Util.clamp(ya, -1, 1);

		for (int yi = 0; yi != ya + ystep; yi += ystep) {
			int t = y + yi;
			int b = y + yi + height;

			if (t < 1) {
				ya *= -1;
				ydir *= -1;
				SoundFX.loping.play();
				break;
			}
			if (b > playField.getHeight()) {
				isAlive = false;
				playField.getPlayer().isAlive = false;
				playField.addSpawner(new Spawner(x + width / 2, y + height / 2, 100));
				playField.addSpawner(new Spawner(playField.getPlayer().getx() + playField.getPlayer().width / 2,
						playField.getPlayer().gety() + playField.getPlayer().height / 2, 100));
				SoundFX.death.play();
				break;
			}
		}

		xa = xspeed * xdir;
		ya = yspeed * ydir;

		if (!released) xa = playField.getPlayer().xa;

		x += xa;
		y += ya;
	}

	public void render(Screen screen) {
		if (!released) {
			String s0 = "stage-" + (playField.getStage() + 1);
			String s1 = "press <space>";
			String s2 = "to launch!";
			int xofs = playField.getWidth() >> 1;
			int yofs = playField.getHeight() >> 1;

			screen.renderString8x8(xofs - (s0.length() * 8 >> 1), yofs - 10, 0xffffff, s0);
			screen.renderString8x8(xofs - (s1.length() * 8 >> 1), yofs, 0xffffff, s1);
			screen.renderString8x8(xofs - (s2.length() * 8 >> 1), yofs + 10, 0xffffff, s2);
		}

		if (isAlive) screen.fillRect(x, y, width, height, col);
	}

	public void processCollision(Mob m) {

		if (released) {

			int xDist = (x + (width >> 1)) - (m.getx() + (m.getWidth() >> 1));
			xspeed = Math.abs(xDist >> 2);
			xdir = Util.clamp(xDist, -1, 1);

			if (m instanceof Player) {
				ydir = -1;
				multiplier = 1;
				SoundFX.hiping.play();
				return;
			}

			int yDist = (y + (height >> 1)) - (m.gety() + (m.getHeight() >> 1));
			yspeed = Util.min(Math.abs(yDist >> 1), 2);
			if (yspeed == 0) yspeed = 1;
			ydir = Util.clamp(yDist, -1, 1);
			if (ydir == 0) ydir = -1;
		}
	}
}