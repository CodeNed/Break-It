package com.noah.breakit.entity.mob.player;

import com.noah.breakit.entity.mob.Mob;
import com.noah.breakit.entity.mob.projectile.Projectile;
import com.noah.breakit.sound.SoundFX;

public class PlayerShootingState extends PlayerNormalState {
	
	private final int ttl = 15 * 60;
	private int count = ttl;
	private boolean toggle = false;
	
	public void init(Mob m) {
		p = (Player) m;
	}

	public void update() {
		super.update();
		
		if((count-- % (1 * 45)) == 0) {
			if(toggle)
				p.getPlayfield().addProjectile(new Projectile(p.getx(), p.gety()));
			else
				p.getPlayfield().addProjectile(new Projectile(p.getx() + p.getWidth(), p.gety()));
			
			SoundFX.SHOOT.play();
			toggle = !toggle;
		}
		
		if(count == 0) {
			p.setState(new PlayerNormalState());
			p.getState().init(p);
			SoundFX.POWER_DOWN.play();
		}
	}
}