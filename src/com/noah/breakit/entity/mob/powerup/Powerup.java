package com.noah.breakit.entity.mob.powerup;

import java.util.List;

import com.noah.breakit.entity.mob.Mob;
import com.noah.breakit.entity.mob.ball.Ball;
import com.noah.breakit.entity.mob.ball.BallPowerState;
import com.noah.breakit.entity.mob.forcefield.ForceField;
import com.noah.breakit.entity.mob.player.PlayerShootingState;
import com.noah.breakit.entity.mob.player.PlayerWideState;
import com.noah.breakit.entity.state.State;
import com.noah.breakit.game.Game;
import com.noah.breakit.graphics.Font8x8;
import com.noah.breakit.graphics.Screen;
import com.noah.breakit.util.ColorFlasher;

public class Powerup extends Mob {
	
	
	public Powerup(int x, int y, State state) {
		super(x, y, state);

		width = 16;
		height = 16;
		xdir = 0;
		ydir = 1;
		xspeed = 0;
		yspeed = 1;
		xa = 0;
		ya = ydir * yspeed;
	}

	public final void update() {
		state.update();
	}

	public void render(Screen screen) {
		state.render(screen);
	}
	
	protected final void renderChar(Screen screen, char c) {
		screen.renderChar8x8(x + (width >> 1) - 4, y + (height >> 1) - 4, ~ColorFlasher.col, Font8x8.getChar(c));
	}

	public void processCollision() {
		state.processCollision(null);
	}

	Powerup spawnPowerup(int x, int y) {
		return null;
	}
	
	private void forceField() {
		playfield.addForceField(new ForceField(0, Game.HEIGHT - 4));
	}
	
	private void multiBall() {
		playfield.addBall(new Ball(x + width / 2, y + width / 2, -1, -1));
		playfield.addBall(new Ball(x + width / 2, y + width / 2,  1, -1));
	}
	
	private void powerBall() {
		List<Ball> balls = playfield.getBalls();
		for (Ball b : balls) {
			b.setState(new BallPowerState());
			b.getState().init(b);
		}
	}
	
	private void shooting() {
		playfield.getPlayer().setState(new PlayerShootingState());
		playfield.getPlayer().getState().init(playfield.getPlayer());	
	}
	
	private void width() {
		playfield.getPlayer().setState(new PlayerWideState());
		playfield.getPlayer().getState().init(playfield.getPlayer());
	}
	
	public Powerup spawn(int x, int y, int n) {
		
		Trigger t = null;
		char c = '?';
		
		switch(n) {
		case (0):
			t = () -> forceField();
			c = 'f';
			break;
		case (1):
			t = () -> multiBall();
			c = 'm';
			break;
		case (2):
			t = () -> powerBall();
			c = 'p';
			break;
		case (3): 
			t = () -> shooting();
			c = 's';
			break;
		case (4):
			t = () -> width();
			c = 'w';
			break;
		}

		return new Powerup(x, y, new PowerupState(t, c));
	}
}