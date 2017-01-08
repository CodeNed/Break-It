package com.noah.breakit.entity.mob.player;

import com.noah.breakit.entity.mob.Mob;
import com.noah.breakit.entity.state.State;
import com.noah.breakit.graphics.Screen;
import com.noah.breakit.transition.PixelDrip;
import com.noah.breakit.util.Hud;

public class StatePrimaryPlayerDead implements State {
	
	private Player p = null;
	private static final int TTL = 60 * 2;
	private int count = TTL;
	
	public void init(Mob m) {
		p = (Player) m;
		p.clearSecondaryStates();
	}

	public void update() {
		if (count-- == 0) {
			p.addToLives(-1);
			p.getPlayfield().captureScreen();
			p.getPlayfield().setTransitioning(true, new PixelDrip(0xff00ff));     
		}
	}

	public void render(Screen s) {
		Hud.renderScore(s, 4, 4, 0xffffff, p.scoreStr);
		Hud.renderLives(s, p.lives);
	}

	public void processCollision(Mob m) {
	}

	public Mob getMob() {
		return p;
	}
}