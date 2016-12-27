package com.noah.breakit.entity.mob.forcefield;

import com.noah.breakit.entity.mob.Mob;
import com.noah.breakit.entity.mob.ball.Ball;
import com.noah.breakit.game.Game;
import com.noah.breakit.graphics.Screen;
import com.noah.breakit.sound.SoundFX;
import com.noah.breakit.util.ColorFlasher;

public class ForceField extends Mob {

	private int life = 3;
	
	public ForceField(int x, int y) {
		super(x, y, null);
		width = Game.WIDTH;
		height = life;
	}

	public void update() {
		if(life == 0) {
			SoundFX.POWER_DOWN.play();
			remove();
		}
	}
	
	public void render(Screen screen) {
		screen.fillRect(x, y, width, life, ~ColorFlasher.col);
	}
	
	public int getLife() {
		return life;
	}
	
	public void addLife(int life) {
		this.life += life;
	}
	
	public void processCollision(Mob m) {
		if(m instanceof Ball) {
			life--;
			SoundFX.EXPLODE_3.play();
		}
	}
}