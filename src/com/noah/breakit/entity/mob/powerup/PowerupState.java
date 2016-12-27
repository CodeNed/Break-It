package com.noah.breakit.entity.mob.powerup;

import com.noah.breakit.entity.mob.Mob;
import com.noah.breakit.entity.spawner.ParticleSpawner;
import com.noah.breakit.entity.state.State;
import com.noah.breakit.game.Game;
import com.noah.breakit.graphics.Font8x8;
import com.noah.breakit.graphics.Screen;
import com.noah.breakit.sound.SoundFX;
import com.noah.breakit.util.ColorFlasher;
import com.noah.breakit.util.Util;

public class PowerupState implements State {
	
	protected Powerup p;
	protected Trigger t;
	protected char c;
	
	public PowerupState(Trigger t, char c) {
		this.t = t;
		this.c = c;
	}
	
	public void init(Mob m) {
		p = (Powerup) m;
	}

	public final void update() {
		int ystep = 0;
		ystep = Util.clamp(p.getya(), -1, 1);
		for (int yi = 0; yi != p.getya() + ystep; yi += ystep) {

			int b = p.gety() + yi + p.getHeight();

			if (b > Game.HEIGHT) {
				p.remove();
				p.getPlayfield().addSpawner(new ParticleSpawner(p.getx() + p.getWidth() / 2, p.gety() + p.getHeight() / 2, 100));
				SoundFX.EXPLODE_2.play();
				break;
			}
		}
		p.setya(p.getyspeed() * p.getydir());
		p.sety(p.gety() + p.getya());	
	}

	public void render(Screen s) {
		s.fillRect(p.getx(), p.gety(), p.getWidth(), p.getHeight(), 0x000000);
		s.drawRect(p.getx(), p.gety(), p.getWidth(), p.getHeight(), ColorFlasher.col);
		s.renderChar8x8(p.getx() + (p.getWidth() >> 1) - 4, p.gety() + (p.getHeight() >> 1) - 4, ~ColorFlasher.col, Font8x8.getChar(c));
	}
	
	protected final void renderChar(Screen s, char c) {
		s.renderChar8x8(p.getx() + (p.getWidth() >> 1) - 4, p.gety() + (p.getHeight() >> 1) - 4, ~ColorFlasher.col, Font8x8.getChar(c));
	}

	public void processCollision(Mob m) {
		SoundFX.POWER_UP.play();
		t.trigger();
		p.remove();		
	}

	public Mob getMob() {
		return null;
	}
}