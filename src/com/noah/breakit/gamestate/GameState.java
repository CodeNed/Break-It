package com.noah.breakit.gamestate;

import java.util.Random;

import com.noah.breakit.entity.mob.Player;
import com.noah.breakit.game.Game;
import com.noah.breakit.graphics.Screen;

public abstract class GameState {

	protected static Random random = new Random();
	protected int[] pixels = new int[Game.width * Game.height];

	public boolean transition;
	public boolean finished;

	public void addPlayer(Player player) {
	}

	public void captureScreen() {
		for (int i = 0; i < pixels.length; i++)
			pixels[i] = Screen.pixels[i];
	}


	public final void update() {
		if (!transition)
			updateGS();
		else
			updateTX();
	}

	public final void render(Screen screen) {
		if (!transition)
			renderGS(screen);
		else
			renderTX(screen);
	}

	public void renderScreenCap(Screen screen) {
		for (int i = 0; i < pixels.length; i++) {
			screen.renderPixel(pixels[i], i);
		}
	}
	
	public abstract void updateGS();

	public abstract void renderGS(Screen screen);

	public abstract void updateTX();

	public abstract void renderTX(Screen screen);
	
	public abstract GameState getNextGameState();
}