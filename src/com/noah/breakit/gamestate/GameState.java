package com.noah.breakit.gamestate;

import com.noah.breakit.graphics.Screen;

public interface GameState {
	
	public void update();
	
	public void render(Screen screen);
	
	public void captureScreen();
	
	public void renderScreenCap(Screen screen);
	
	public void loadNextGameState();
	
	public void setNextGameState(GameState gs);
	
	public GameState getNextGameState();
	
	public GameState getParentGameState();
	
	public String getCurrSong();
	
	public boolean isFinished();
}
