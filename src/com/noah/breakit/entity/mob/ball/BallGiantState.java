package com.noah.breakit.entity.mob.ball;

import com.noah.breakit.entity.mob.Mob;
import com.noah.breakit.entity.mob.brick.Brick;
import com.noah.breakit.entity.mob.brick.BrickSolidState;
import com.noah.breakit.entity.mob.forcefield.ForceField;
import com.noah.breakit.entity.mob.player.Player;
import com.noah.breakit.game.Game;
import com.noah.breakit.util.Util;

public class BallGiantState extends BallNormalState {
	
	private static final int TTL = 7 * 60;
	
	private int count = 0;
	
	public void init(Mob m) {
		super.init(m);
		adjustSize(12);
		m.setx(Util.clamp(m.getx(), 0, Game.WIDTH));
		m.setCol(0xff00ff);
	}
	
	public void update() {
		super.update();
		
		if(count++ == TTL) {
			b.setState(new BallNormalState());
			b.getState().init(b);
		}
	}
	
	protected void processXCollision(Mob m) {
		
		int xDist = (b.getx() + (b.getWidth() / 3) / 2) - (m.getx() + m.getWidth() / 2);
		b.setxspeed(Math.abs(xDist / (4 * 2)));
		
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
			int yDist = (b.gety() + (b.getHeight() / 3) / 2) - (m.gety() + (m.getHeight() / 2));
			b.setyspeed(Util.min(Math.abs(yDist / 2), 2));
			if (b.getyspeed() == 0) b.setyspeed(1);
			b.setydir(Util.clamp(yDist, -1, 1));
			if (b.getydir() == 0) b.setydir(-1);
		} else
			b.setydir(-1);
	}
}