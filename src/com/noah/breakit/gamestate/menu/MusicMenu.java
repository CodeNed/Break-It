package com.noah.breakit.gamestate.menu;

import com.noah.breakit.component.Label;
import com.noah.breakit.component.Panel;
import com.noah.breakit.component.PushButton;
import com.noah.breakit.gamestate.BreakitGameState;
import com.noah.breakit.graphics.Screen;
import com.noah.breakit.input.Keyboard;
import com.noah.breakit.sound.SoundFX;
import com.noah.breakit.sound.music.Jukebox;
import com.noah.breakit.util.Config;

public class MusicMenu extends BreakitGameState
{	
	private BreakitGameState pgs = null; //parent game state
	
	private Keyboard key = null;
	private Panel panel = null;
	
	private int w = 50;
	private int h = 60;
	
	
	public MusicMenu(Keyboard key, BreakitGameState parentGameState) {
		this.key = key;
		
		this.pgs = parentGameState;
		
		int x = Config.WINDOW_WIDTH / 2 - w / 2;
		int y = Config.WINDOW_HEIGHT / 2 - h / 2;
		
		int x1 = x + w / 2 - "music".length() * 8 / 2;
		int y1 = y + 8;
		
		int x2 = x + w / 2 - "on".length() * 8 / 2;
		int y2 = y1 + 20;
		
		int x3 = x + w / 2 - "off".length() * 8 / 2;
		int y3 = y2 + 16;
		
		panel = new Panel(x, y, w, h, key, new Label(x1, y1, "music"),
								new PushButton(x2, y2, new Label(x2, y2, "on"), () -> musicOn()),
								new PushButton(x3, y3, new Label(x3, y3, "off"), () -> musicOff())
								);
	}
	
	public void update() {
		
		Jukebox.play(pgs.getCurrSong(), true);
		
		key.update();
		
		if(key.esc && ! key.escLast) {
			SoundFX.MENU_3.play();
			finished = true;
		}
		
		panel.update();
	}

	public void render(Screen screen) {
		renderScreenCap(screen);
		panel.render(screen);
		
	}
	
	private void musicOn() {
		if(Jukebox.isOnStandby() == false)
			return;
		Jukebox.setStandbyMode(false);
	}
	
	private void musicOff() {
		Jukebox.setStandbyMode(true);
		Jukebox.stop();
	}
	
	public void loadNextGameState() {
	}
}