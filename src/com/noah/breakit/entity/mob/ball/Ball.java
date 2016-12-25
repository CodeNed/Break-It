package com.noah.breakit.entity.mob.ball;

import com.noah.breakit.entity.mob.Mob;
import com.noah.breakit.entity.mob.brick.Brick;
import com.noah.breakit.entity.mob.brick.PortalBrick;
import com.noah.breakit.entity.mob.brick.SolidBrick;
import com.noah.breakit.entity.mob.forcefield.ForceField;
import com.noah.breakit.entity.mob.player.Player;
import com.noah.breakit.entity.spawner.ParticleSpawner;
import com.noah.breakit.game.Game;
import com.noah.breakit.graphics.Screen;
import com.noah.breakit.sound.SoundFX;
import com.noah.breakit.util.Util;

public class Ball extends Mob {

	protected boolean released = false;
	public int multiplier = 1;
	
	private static final int PORTAL_SICKNESS_TIME = 1 * 30;
	private int portalSicknessTimer = PORTAL_SICKNESS_TIME;

	public Ball(int x, int y) {
		super(x, y);
		
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
	
	public Ball(int x, int y, int xa, int ya) {
		this(x,y);
		this.xa = xa;
		this.ya = ya;
		this.xdir = xa;
		this.ydir = ya;
		released = true;
	}
	
	public Ball(Ball b) {
		super(b.getx(), b.gety());
		
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
		
		processWallCollision();
		
		xa = xspeed * xdir;
		ya = yspeed * ydir;

		if (!released) xa = playField.getPlayer().getxa();

		x += xa;
		y += ya;
		
		portalSicknessTimer = Util.max(++portalSicknessTimer, PORTAL_SICKNESS_TIME);
	}

	public void render(Screen screen) {
		
		screen.fillRect(x, y, width, height, col);
		
		if (released) return;
		
		String s0 = "stage-" + (playField.getStage() + 1);
		String s1 = "press <space>";
		String s2 = "to launch!";
		int xofs = Game.WIDTH >> 1;
		int yofs = Game.HEIGHT >> 1;

		screen.renderString8x8(xofs - ((s0.length() << 3) >> 1), yofs - 10, 0xffffff, s0);
		screen.renderString8x8(xofs - ((s1.length() << 3) >> 1), yofs, 0xffffff, s1);
		screen.renderString8x8(xofs - ((s2.length() << 3) >> 1), yofs + 10, 0xffffff, s2);
	}

	public void processCollision(Mob m) {

		if (!released) return;
		
		if (m instanceof PortalBrick) {
			PortalBrick p = (PortalBrick) m;
			if(portalSicknessTimer == PORTAL_SICKNESS_TIME) {
				int xd = Math.abs(x + width / 2 - p.getx() - p.getWidth() / 2);
				int yd = Math.abs(y + height / 2 - p.gety() - p.getHeight() / 2);
				if (xd <= 8 && yd <= 4) {
					portalSicknessTimer = 0;
					x = p.getMate().getx() + p.getMate().getWidth() / 2;
					y = p.getMate().gety() + p.getMate().getHeight() / 2;
					ydir *= -1; 
					SoundFX.PORTAL.play();
				}
			}
			return;
		}
		
		processXCollision(m);
		processYCollision(m);
		
		if (m instanceof Player || m instanceof ForceField) {
			multiplier = 1;
			SoundFX.HI_BOUNCE.play();
		}
	}
	
	protected void processXCollision(Mob m) {
		
		int xDist = (x + (width >> 1)) - (m.getx() + m.getWidth() / 2);
		xspeed = Math.abs(xDist >> 2);
		
		if (m instanceof Player || m instanceof ForceField) {
			xdir = Util.clamp(xDist, -1, 1);
			if(m instanceof ForceField)
				xspeed = Util.clamp(xspeed, -3, 3);
		}
		
		else if (m instanceof SolidBrick) {
			if (xspeed == 0) xspeed = random.nextInt(2) > 0 ? -1 : 1;
			if (xdir == 0) xdir = random.nextInt(2) > 0 ? -1 : 1;
		}
	}
	
	protected void processYCollision(Mob m) {
		if(m instanceof Brick) {
			int yDist = (y + (height >> 1)) - (m.gety() + (m.getHeight() >> 1));
			yspeed = Util.min(Math.abs(yDist >> 1), 2);
			if (yspeed == 0) yspeed = 1;
			ydir = Util.clamp(yDist, -1, 1);
			if (ydir == 0) ydir = -1;
		} else
			ydir = -1;
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
				playField.addSpawner(new ParticleSpawner(x + width / 2, y + height / 2, 100));
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
}