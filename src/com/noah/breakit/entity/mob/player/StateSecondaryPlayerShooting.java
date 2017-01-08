package com.noah.breakit.entity.mob.player;

import com.noah.breakit.entity.mob.Mob;
import com.noah.breakit.entity.mob.projectile.Projectile;
import com.noah.breakit.entity.state.State;
import com.noah.breakit.graphics.Screen;
import com.noah.breakit.sound.SoundFX;

public class StateSecondaryPlayerShooting implements State {
	
	private Player p = null;
	
	private static final int TTL = 15 * 60;
	private int count = TTL;
	private boolean toggle = false;
	
	public void init(Mob m) {
		p = (Player) m; 
	}
	
	public void update() {		
		if((count-- % (1 * 45)) == 0) {
			if(toggle)
				p.getPlayfield().addProjectile(new Projectile(p.getx(), p.gety()));
			else
				p.getPlayfield().addProjectile(new Projectile(p.getx() + p.getWidth(), p.gety()));
			
			SoundFX.SHOOT.play();
			toggle = !toggle;
		}
		
		if(count == 0) {
			p.removeSecondaryState(this);
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