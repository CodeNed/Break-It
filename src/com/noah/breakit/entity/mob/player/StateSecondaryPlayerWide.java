package com.noah.breakit.entity.mob.player;

import com.noah.breakit.entity.mob.Mob;
import com.noah.breakit.entity.state.State;
import com.noah.breakit.graphics.Screen;
import com.noah.breakit.sound.SoundFX;

public class StateSecondaryPlayerWide implements State {
	
	private Player p = null;
	
	private static final int TTL = 15 * 60;
	private int count = TTL;
	
	public State init(Mob m) {
		p = (Player) m;
		p.setWidth(48);
		p.setCol(0x00ff00);
		return this;
	}

	public State update() {
		
		if(count-- == 5 * 60)
			p.setCol(0xffff00);
		
		if(count < 5 * 60) {
			if(count % (1 * 15) == 0)
				p.setCol(p.getCol() ^ 0x00ff00);
		}
		
		if(count == 0) {
			p.removeSecondaryState(this);
			p.getPrimaryState().init(p);
			SoundFX.POWER_DOWN.play();
		}
		
		return this;
	}

	public void render(Screen s) {
	}

	public void processCollision(Mob m) {		
	}

	public Mob getMob() {
		return p;
	}
}