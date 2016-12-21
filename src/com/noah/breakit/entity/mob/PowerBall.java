package com.noah.breakit.entity.mob;

import com.noah.breakit.entity.spawner.ParticleSpawner;
import com.noah.breakit.game.Game;
import com.noah.breakit.graphics.Screen;
import com.noah.breakit.sound.SoundFX;
import com.noah.breakit.util.Util;

public class PowerBall extends Ball {
	
	private final int NUM_TRAILS = 6;
	private final int TIME_TO_LIVE = 7 * 60;
	
	private int[] xlast = new int[NUM_TRAILS];
	private int[] ylast = new int[NUM_TRAILS];
	
	private int count = 0;
	
	public PowerBall(int x, int y, int xdir, int ydir, int xspeed, int yspeed) {
		super(x, y);
		
		width = 4;
		height = 4;
		col = 0xff0000;
				
		this.xdir = xdir;
		this.ydir = ydir;
		this.xspeed = xspeed;
		this.yspeed = yspeed;
		
		xa = xspeed * xdir;
		ya = yspeed * ydir;
	}
	
	public void update() {

		int xstep = 0;
		if (xa != 0) xstep = Util.clamp(xa, -1, 1);

		for (int xi = 0; xi != xa + xstep; xi += xstep) {
			int l = x + xi;
			int r = x + xi + width;

			if (l < 1 || r > Game.WIDTH) {
				xdir *= -1;
				xa *= -1;
				SoundFX.LO_PING.play();
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
				SoundFX.LO_PING.play();
				break;
			}
			if (b > Game.HEIGHT) {
				remove();
				playField.addSpawner(new ParticleSpawner(x + width / 2, y + height / 2, 100));
				SoundFX.DEATH.play();
				break;
			}
		}

		xa = xspeed * xdir;
		ya = yspeed * ydir;

		for(int i = NUM_TRAILS - 1; i >= 1; i--) {
			xlast[i] = xlast[i - 1];
			ylast[i] = ylast[i - 1];
		}
		
		xlast[0] = x;
		ylast[0] = y;
		
		x += xa;
		y += ya;
		
		if(count++ % 7 == 0)
			col = random.nextInt(0xffffff);
		
		if(count == TIME_TO_LIVE)
			removeAndReplace();
	}
	
	public void render(Screen screen) {
		screen.drawRect(x, y, width, height, col);
		
		int c = ~col;
		for(int i = 0; i < NUM_TRAILS; i++) {
			screen.drawRect(xlast[i], ylast[i], width, height, c);
			c = ~c;
		}
	}

	public void processCollision(Mob m) {

		if (!(m instanceof Player))
			return;
		
		int xDist = (x + (width >> 1)) - (m.getx() + (m.getWidth() >> 1));
		xspeed = Math.abs(xDist >> 2);
		xdir = Util.clamp(xDist, -1, 1);

		ydir = -1;
		multiplier = 1;
		SoundFX.HI_PING.play();
	}
	
	private void removeAndReplace() {
		remove();
		playField.addBall(new Ball(x, y, xdir, ydir, xspeed, yspeed));
	}
}