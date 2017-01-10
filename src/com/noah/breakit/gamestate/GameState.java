package com.noah.breakit.gamestate;

import com.noah.breakit.graphics.Screen;

public interface GameState {
	
	public void update();
	
	public void render(Screen screen);
	
	public void renderScreenCap(Screen screen);
	
	public void captureScreen();
	
	public void loadNextGameState();
	
	public GameState getNextGameState();
	
	public void setNextGameState(GameState gs);
	
	public boolean isFinished();
	
	public void setFinished(boolean b);
}
