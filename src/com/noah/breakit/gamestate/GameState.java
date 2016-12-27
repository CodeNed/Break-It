package com.noah.breakit.gamestate;

import java.util.Random;

import com.noah.breakit.entity.mob.player.Player;
import com.noah.breakit.game.Game;
import com.noah.breakit.graphics.Screen;
import com.noah.breakit.transition.Transition;
import com.noah.breakit.util.Pair;

public abstract class GameState {

	protected static Random random = new Random();
	
	protected GameState pgs = null; // parent game state for "reaching back" into stack after a vertical gamestate transition
	protected GameState ngs = null; // reference next game state for horizontal gamestate transition
	
	protected Transition transition = null;
	
	protected int[] pixels = new int[Game.WIDTH * Game.HEIGHT];

	protected boolean transitioning = false;
	protected boolean finished = false;
	
	protected Pair<String> currSong = null;

	public void addPlayer(Player player) {
	}

	public void captureScreen() {
		for (int i = 0; i < pixels.length; i++)
			pixels[i] = Screen.pixels[i];
	}

	public final void update() {
		if (!transitioning)
			updateGS();
		else
			updateTX();
	}

	public final void render(Screen screen) {
		if (!transitioning)
			renderGS(screen);
		else
			renderTX(screen);
	}

	protected void renderScreenCap(Screen screen) {
		for (int i = 0; i < pixels.length; i++) {
			screen.renderPixel(pixels[i], i);
		}
	}
	
	public final void setNextGameState(GameState gs) {
		ngs = gs;
	}
	
	public final GameState getNextGameState() {
		return ngs;
	}
	
	public boolean isFinished() {
		return finished;
	}
	
	public boolean isTransitioning() {
		return transitioning;
	}
	
	public void setTransitioning(boolean b, Transition t) {
		transitioning = b;
		transition = t;
	}
	
	protected abstract void loadNextGameState();
	
	public abstract void updateGS();

	public abstract void renderGS(Screen screen);

	public abstract void updateTX();

	public abstract void renderTX(Screen screen);
	
}