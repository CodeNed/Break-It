package com.noah.breakit.entity.mob.player;

import com.noah.breakit.entity.mob.Mob;
import com.noah.breakit.sound.SoundFX;

public class PlayerWideState extends PlayerNormalState {
	
	private final int ttl = 15 * 60;
	private int count = ttl;
	
	public void init(Mob m) {
		p = (Player) m;
		p.setWidth(48);
		p.setCol(0x00ff00);
	}

	public void update() {
		super.update();
		
		if(count-- == 5 * 60)
			p.setCol(0xffff00);
		
		if(--count < 5 * 60) {
			if(count % (1 * 15) == 0)
				p.setCol(p.getCol() ^ 0x00ff00);
		}
		
		if(count == 0) {
			p.setState(new PlayerNormalState());
			p.getState().init(p);
			SoundFX.POWER_DOWN.play();
		}
	}
}
