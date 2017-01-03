package com.noah.breakit.entity.mob.player;

import com.noah.breakit.entity.mob.Mob;
import com.noah.breakit.entity.state.State;
import com.noah.breakit.graphics.Screen;
import com.noah.breakit.sound.SoundFX;

public class StateSecondaryPlayerWide implements State {
	
	private Player p = null;
	
	private static final int TTL = 15 * 60;
	private int count = TTL;
	
	public void init(Mob m) {
		p = (Player) m;
		p.setWidth(48);
		p.setCol(0x00ff00);
	}

	public void update() {
		
		if(count-- == 5 * 60)
			p.setCol(0xffff00);
		
		if(count < 5 * 60) {
			if(count % (1 * 15) == 0)
				p.setCol(p.getCol() ^ 0x00ff00);
		}
		
		if(count == 0) {
//			p.setState(new PlayerPrimaryState());
//			p.getState().init(p);
			p.removeSecondaryState(this);
			p.setCol(0x00ffff);
			p.setWidth(32);
			SoundFX.POWER_DOWN.play();
		}
	}

	public void render(Screen s) {
	}

	public void processCollision(Mob m) {		
	}

	public Mob getMob() {
		return p;
	}
}