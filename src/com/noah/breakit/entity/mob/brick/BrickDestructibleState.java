package com.noah.breakit.entity.mob.brick;

import com.noah.breakit.entity.mob.Mob;
import com.noah.breakit.entity.mob.ball.Ball;
import com.noah.breakit.entity.mob.decoration.FloatingText;
import com.noah.breakit.entity.mob.projectile.Projectile;
import com.noah.breakit.entity.spawner.ParticleSpawner;
import com.noah.breakit.entity.state.State;
import com.noah.breakit.graphics.Screen;
import com.noah.breakit.sound.SoundFX;
import com.noah.breakit.util.Util;

public class BrickDestructibleState implements State {
	
	private Brick b = null;
	
	int life = 1;

	public State init(Mob m) {		
		b = (Brick) m;
		int[] colors = { 0xff0000, 0xff00ff, 0x0000ff, 0x00ffff, 0x00ff00, 0xffff00 };
		b.setCol(colors[(b.gety() / 8 - 2) % 6]);
		return this;
	}
	
	public State update() {
		return this;
	}

	public void render(Screen s) {		
		if (life == 2)
			s.fillRect(b.getx(), b.gety(), b.getWidth(), b.getHeight(), b.getCol());
		else
			s.drawRect(b.getx(), b.gety(), b.getWidth(), b.getHeight(), b.getCol());
	}
	
	public void processCollision(Mob m) {
		if(m instanceof Ball)
			processCollision((Ball) m);
		else if(m instanceof Projectile)
			processCollision((Projectile) m);
	}
	
	public void processCollision(Ball b) {
		if (--life == 0) {
			int points = 100 * b.getMultiplier();
			b.setMultiplier(b.getMultiplier() + 1);
			processDeath(points);
		}
	}
	
	public void processCollision(Projectile p) {
		if (--life == 0) {
			int points = 100;
			processDeath(points);
		}
	}
	
	private void processDeath(int points) {
		b.remove();
		b.getPlayfield().setStagePattern(((b.getx() - 1) / 16) + ((b.gety() / 8) - 2) * 10, '0');
		b.getPlayfield().addSpawner(new ParticleSpawner(b.getx() + (b.getWidth() / 2), b.gety() + (b.getWidth() / 2), 50));
		b.getPlayfield().addDecoration(new FloatingText(b.getx(), b.gety() + 1, points));
		b.getPlayfield().getPlayer().addToScore(points);
		if(Util.random.nextInt(10) == 0)
			b.getPlayfield().addPowerup(b.getx(), b.gety());
		SoundFX.EXPLODE_1.play();
	}
	
	public Mob getMob() {
		return b;
	}
}