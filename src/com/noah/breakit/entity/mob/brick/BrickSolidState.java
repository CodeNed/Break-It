package com.noah.breakit.entity.mob.brick;

import com.noah.breakit.entity.mob.Mob;
import com.noah.breakit.entity.state.State;
import com.noah.breakit.graphics.Screen;
import com.noah.breakit.sound.SoundFX;

public class BrickSolidState implements State {
	
	private Brick b = null;
			
	public State init(Mob m) {
		b = (Brick) m;
		b.setCol(0x909090);
		return this;
	}
	
	public State update() {
		return this;
	}

	public void render(Screen s) {
		
		int c = b.getCol();
		int x = b.getx();
		int y = b.gety();
		int w = b.getWidth();
		int h = b.getHeight();
		
		for(int i = 0; i < b.getHeight() / 2; i++) {
			s.drawRect(x++, y++, w, h, c);
			w -= 2;
			h -= 2;
			c -= 0x202020;	
		}
	}

	public void processCollision(Mob m) {
		SoundFX.PING.play();
	}
	
	public Mob getMob() {
		return b;
	}
}