package com.noah.breakit.gamestate;
import com.noah.breakit.entity.mob.player.Player;
import com.noah.breakit.gamestate.outro.Outro;
import com.noah.breakit.graphics.Screen;
import com.noah.breakit.util.Config;

public abstract class BreakitGameState implements GameState {
	
	protected BreakitGameState pgs = null; // parent game state for "reaching back" into stack after a vertical gamestate transition
	protected BreakitGameState ngs = null; // reference next game state for horizontal gamestate transition
	
	protected Outro transition = null;
	
	protected int[] pixels = new int[Config.WINDOW_WIDTH * Config.WINDOW_HEIGHT];

	protected boolean finished = false;
	
	protected String currSong = null;

	public void addPlayer(Player player) {
	}

	public void captureScreen() {
		for (int i = 0; i < pixels.length; i++)
			pixels[i] = Screen.pixels[i];
	}

	public void renderScreenCap(Screen screen) {
		for (int i = 0; i < pixels.length; i++) {
			screen.renderPixel(pixels[i], i);
		}
	}
	
	public final void setNextGameState(GameState gs) {
		ngs = (BreakitGameState) gs;
	}
	
	public final GameState getNextGameState() {
		return ngs;
	}
	
	public final GameState getParentGameState() {
		return pgs;
	}
	
	public String getCurrSong() {
		return currSong;
	}
	
	public final int[] getPixels() {
		return pixels;
	}
	
	public boolean isFinished() {
		return finished;
	}
	
	public void setFinished(boolean b) {
		finished = b;
	}
}