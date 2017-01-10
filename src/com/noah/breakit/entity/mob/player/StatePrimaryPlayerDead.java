package com.noah.breakit.entity.mob.player;

import com.noah.breakit.entity.mob.Mob;
import com.noah.breakit.entity.state.State;
import com.noah.breakit.graphics.Screen;
import com.noah.breakit.util.Hud;

public class StatePrimaryPlayerDead implements State {
	
	private Player p = null;
	
	public void init(Mob m) {
		p = (Player) m;
		p.clearSecondaryStates();
	}

	public void update() {
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