package com.noah.breakit.entity.mob.brick;

import com.noah.breakit.entity.mob.Mob;
import com.noah.breakit.entity.state.State;
import com.noah.breakit.graphics.Screen;

public class BrickPortalState implements State {
	
	private Brick b = null;
	private BrickPortalState mate = null;
	
	public void init(Mob m) {
		b = (Brick) m;
		b.setCol(0x800080);
	}

	public void update() {		
	}

	public void render(Screen s) {		
		s.fillRect(b.getx(), b.gety(), b.getWidth(), b.getHeight(), b.getCol());
	}

	public void processCollision(Mob m) {
	}
	
	public BrickPortalState getMate() {
		return mate;
	}
	
	public void setMate(BrickPortalState p) {
		mate = p;
	}
	
	public Mob getMob() {
		return b;
	}
}