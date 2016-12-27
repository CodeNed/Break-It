package com.noah.breakit.entity.mob.ball;

import com.noah.breakit.entity.mob.Mob;
import com.noah.breakit.entity.spawner.ParticleSpawner;
import com.noah.breakit.entity.state.State;
import com.noah.breakit.game.Game;
import com.noah.breakit.graphics.Screen;
import com.noah.breakit.sound.SoundFX;
import com.noah.breakit.util.Util;

public class Ball extends Mob {

	boolean released = false;
	private int multiplier = 1;
	
	static final int PORTAL_SICKNESS_TIME = 1 * 30;
	int portalSicknessTimer = PORTAL_SICKNESS_TIME;

	public Ball(int x, int y) {
		this(x, y, new BallNormalState());
	}
	
	public Ball(int x, int y, int xa, int ya) {
		this(x, y, new BallNormalState());
		this.xa = xa;
		this.ya = ya;
		this.xdir = xa;
		this.ydir = ya;
		released = true;
	}
		
	public Ball(int x, int y, State state) {
		super(x, y, state);
		
		width = 4;
		height = 4;
		col = 0xff00ff;
		xdir = 0;
		ydir = 0;
		xspeed = 1;
		yspeed = 2;
		xa = xspeed * xdir;
		ya = yspeed * ydir;
	}

	public Ball(Ball b) {
		super(b.getx(), b.gety(), b.getState());
		
		width = b.getWidth();
		height = b.getHeight();
		
		xdir = b.getxdir();
		ydir = b.getydir();
		
		xspeed = b.getxspeed();
		yspeed = b.getyspeed();
		
		xa = xspeed * xdir;
		ya = yspeed * ydir;
		
		released = true;
		col = 0xff00ff;
	}

	public void update() {
		state.update();
	}

	public void render(Screen screen) {
		state.render(screen);
	}

	public void processCollision(Mob m) {
		state.processCollision(m);
	}
	
	protected void processWallCollision() {
		int xstep = 0;
		if (xa != 0) xstep = Util.clamp(xa, -1, 1);

		for (int xi = 0; xi != xa + xstep; xi += xstep) {
			int l = x + xi;
			int r = x + xi + width;

			if (l < 1 || r > Game.WIDTH) {
				xdir *= -1;
				xa *= -1;
				SoundFX.LO_BOUNCE.play();
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
				SoundFX.LO_BOUNCE.play();
				break;
			}
			if (b > Game.HEIGHT) {
				remove();
				playfield.addSpawner(new ParticleSpawner(x + width / 2, y + height / 2, 100));
				SoundFX.EXPLODE_2.play();
				break;
			}
		}
	}
	
	public boolean isReleased() {
		return released;
	}
	
	public void setReleased(boolean b) {
		released = b;
	}
	
	public int getMultiplier() {
		return multiplier;
	}
	
	public void setMultiplier(int m) {
		multiplier = m;
	}
}