package com.noah.breakit.gamestate;
import com.noah.breakit.graphics.Screen;
import com.noah.breakit.util.Config;

public abstract class BreakitGameState implements GameState {
	
	protected BreakitGameState ngs = null; // reference next game state for horizontal gamestate transition
		
	protected int[] pixels = new int[Config.WINDOW_WIDTH * Config.WINDOW_HEIGHT];

	protected boolean finished = false;
	
	protected String currSong = null;
	
	public void renderScreenCap(Screen screen) {
		for (int i = 0; i < pixels.length; i++)
			screen.renderPixel(pixels[i], i);
	}
	
	public void captureScreen() {
		for (int i = 0; i < pixels.length; i++)
			pixels[i] = Screen.pixels[i];
	}
	
	public final void setNextGameState(GameState gs) {
		ngs = (BreakitGameState) gs;
	}
	
	public final GameState getNextGameState() {
		return ngs;
	}
		
	public String getCurrSong() {
		return currSong;
	}
	
	public boolean isFinished() {
		return finished;
	}
	
	public void setFinished(boolean b) {
		finished = b;
	}
}