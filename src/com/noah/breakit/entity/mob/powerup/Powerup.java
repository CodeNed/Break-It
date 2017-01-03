package com.noah.breakit.entity.mob.powerup;

import java.util.List;

import com.noah.breakit.entity.mob.Mob;
import com.noah.breakit.entity.mob.ball.Ball;
import com.noah.breakit.entity.mob.ball.BallGiantState;
import com.noah.breakit.entity.mob.ball.BallPowerState;
import com.noah.breakit.entity.mob.forcefield.ForceField;
import com.noah.breakit.entity.state.State;
import com.noah.breakit.game.Game;
import com.noah.breakit.graphics.Font8x8;
import com.noah.breakit.graphics.Screen;
import com.noah.breakit.util.ColorFlasher;

public class Powerup extends Mob {
	
	public static final int NUM_TYPES = 6;
	
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
	
	private void forceField() {
		playfield.addForceField(new ForceField(0, Game.HEIGHT - 4));
	}
	
	private void giantBall() {
		List<Ball> balls = playfield.getBalls();
		for (Ball b : balls) {
			b.setState(new BallGiantState());
			b.getState().init(b);
		}
	}
	
	private void multiBall() {
		int x = playfield.getPlayer().getx();
		int y = playfield.getPlayer().gety();
		int w = playfield.getPlayer().getWidth();
		int h = playfield.getPlayer().getHeight();
		
		playfield.addBall(new Ball(x + w / 2, y - (h + 1), -1, -1));
		playfield.addBall(new Ball(x + w / 2, y - (h + 1),  1, -1));
	}
	
	private void powerBall() {
		List<Ball> balls = playfield.getBalls();
		for (Ball b : balls) {
			b.setState(new BallPowerState());
			b.getState().init(b);
		}
	}
	
	private void shooting() {
		playfield.getPlayer().addStateSecondaryPlayerShooting();		
	}
	
	private void wide() {
		playfield.getPlayer().addStateSecondaryPlayerWide();                                		
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
			t = () -> giantBall();
			c = 'g';
			break;
		case (2):
			t = () -> multiBall();
			c = 'm';
			break;
		case (3):
			t = () -> powerBall();
			c = 'p';
			break;
		case (4): 
			t = () -> shooting();
			c = 's';
			break;
		case (5):
			t = () -> wide();
			c = 'w';
			break;
		}
		return new Powerup(x, y, new PowerupState(t, c));
	}
}