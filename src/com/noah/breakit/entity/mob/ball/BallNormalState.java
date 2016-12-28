package com.noah.breakit.entity.mob.ball;

import com.noah.breakit.entity.mob.Mob;
import com.noah.breakit.entity.mob.brick.Brick;
import com.noah.breakit.entity.mob.brick.BrickPortalState;
import com.noah.breakit.entity.mob.brick.BrickSolidState;
import com.noah.breakit.entity.mob.forcefield.ForceField;
import com.noah.breakit.entity.mob.player.Player;
import com.noah.breakit.entity.state.State;
import com.noah.breakit.game.Game;
import com.noah.breakit.graphics.Screen;
import com.noah.breakit.sound.SoundFX;
import com.noah.breakit.util.Util;

public class BallNormalState implements State {
	
	protected Ball b = null;
	
	public BallNormalState() {
	}
	
	public BallNormalState(Ball b) {
		b.setCol(0xff00ff);
	}
	
	public void init(Mob m) {
		b = (Ball) m;
	}
	
	public void update() {
		
		b.processWallCollision();
		
		b.updateXa();
		b.updateYa();

		if (!b.released) b.setxa(b.getPlayfield().getPlayer().getxa());

		b.moveX();
		b.moveY();
		
		b.portalSicknessTimer = Util.max(++b.portalSicknessTimer, Ball.PORTAL_SICKNESS_TIME);
	}

	public void render(Screen s) {
		
		s.fillRect(b.getx(), b.gety(), b.getWidth(), b.getHeight(), b.getCol());
		
		if (b.released) return;
		
		String s0 = "stage-" + (b.getPlayfield().getStage() + 1);
		String s1 = "press <space>";
		String s2 = "to launch!";
		int xofs = Game.WIDTH >> 1;
		int yofs = Game.HEIGHT >> 1;

		s.renderString8x8(xofs - ((s0.length() << 3) >> 1), yofs - 10, 0xffffff, s0);
		s.renderString8x8(xofs - ((s1.length() << 3) >> 1), yofs, 0xffffff, s1);
		s.renderString8x8(xofs - ((s2.length() << 3) >> 1), yofs + 10, 0xffffff, s2);
		
	}

	public void processCollision(Mob m) {

		if (!b.released) return;
		
		if (m instanceof Brick && m.getState() instanceof BrickPortalState) {
			
			BrickPortalState s = (BrickPortalState) m.getState();
			
			if(b.portalSicknessTimer == Ball.PORTAL_SICKNESS_TIME) {
				int xd = Math.abs(b.getx() + b.getWidth() / 2 - m.getx() - m.getWidth() / 2);
				int yd = Math.abs(b.gety() + b.getHeight() / 2 - m.gety() - m.getHeight() / 2);
				if (xd <= 8 && yd <= 4) {
					b.portalSicknessTimer = 0;
					b.setx(s.getMate().getMob().getx() + s.getMate().getMob().getWidth() / 2);
					b.sety(s.getMate().getMob().gety() + s.getMate().getMob().getHeight() / 2);
					b.setydir(b.getydir() * -1);
					SoundFX.PORTAL.play();
				}
			}
			return;
		}
			
		processXCollision(m);
		processYCollision(m);
			
		if (m instanceof Player || m instanceof ForceField) {
			b.setMultiplier(1);
			SoundFX.HI_BOUNCE.play();
		}
	}
	
	protected void processXCollision(Mob m) {
			
		int xDist = (b.getx() + b.getWidth() / 2) - (m.getx() + m.getWidth() / 2);
		b.setxspeed(Math.abs(xDist / 4));
		
		if (m instanceof Player || m instanceof ForceField) {
			b.setxdir(Util.clamp(xDist, -1, 1));
			if(m instanceof ForceField)
				b.setxspeed(Util.clamp(b.getxspeed(), -3, 3));
		}
		
		else if (m.getState() instanceof BrickSolidState) {
			if (b.getxspeed() == 0) b.setxspeed(Util.random.nextInt(2) > 0 ? -1 : 1);
			if (b.getxdir() == 0) b.setxdir(Util.random.nextInt(2) > 0 ? -1 : 1);
		}
	}
	
	protected void processYCollision(Mob m) {
		if(m instanceof Brick) {
			int yDist = (b.gety() + b.getHeight() / 2) - (m.gety() + (m.getHeight() / 2));
			b.setyspeed(Util.min(Math.abs(yDist / 2), 2));
			if (b.getyspeed() == 0) b.setyspeed(1);
			b.setydir(Util.clamp(yDist, -1, 1));
			if (b.getydir() == 0) b.setydir(-1);
		} else
			b.setydir(-1);
	}

	public Mob getMob() {
		return b;
	}
}