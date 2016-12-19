package com.noah.breakit.gamestate;

import com.noah.breakit.component.Action;
import com.noah.breakit.component.Button;
import com.noah.breakit.component.Label;
import com.noah.breakit.component.Panel;
import com.noah.breakit.game.Game;
import com.noah.breakit.graphics.Screen;
import com.noah.breakit.input.Keyboard;

public class MusicMenu extends GameState
{
	private Keyboard key;
	private Panel panel;
	
	private int w = 50;
	private int h = 60;
	
	
	public MusicMenu(Keyboard key, GameState parentGameState) {
		this.key = key;
		this.pixels = parentGameState.pixels;
		
		int x = Game.width / 2 - w / 2;
		int y = Game.height / 2 - h / 2;
		
		int x1 = x + w / 2 - "music".length() * 8 / 2;
		int y1 = y + 8;
		
		int x2 = x + w / 2 - "on".length() * 8 / 2;
		int y2 = y1 + 20;
		
		int x3 = x + w / 2 - "off".length() * 8 / 2;
		int y3 = y2 + 16;
		
		panel = new Panel(x, y, w, h, key, new Label(x1, y1, "music"),
								new Button(x2, y2, new Label(x2, y2, "on"), (Action)() -> System.out.println("music on")),
								new Button(x3, y3, new Label(x3, y3, "off"), (Action)() -> System.out.println("music off")));
		panel.init();
	}
	
	public void updateGS() {
		key.update();
		
		if(key.esc && ! key.escLast)
			finished = true;
		
		panel.update();
	}

	public void renderGS(Screen screen) {
		renderScreenCap(screen);
		panel.render(screen);
		
	}

	public void updateTX() {
		// XXX Auto-generated method stub
		
	}

	public void renderTX(Screen screen) {
		// XXX Auto-generated method stub
		
	}
	
	protected void loadNextGameState() {
		// XXX Auto-generated method stub
		
	}

}
