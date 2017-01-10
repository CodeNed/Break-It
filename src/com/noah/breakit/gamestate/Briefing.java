package com.noah.breakit.gamestate;

import com.noah.breakit.gamestate.outro.Outro;
import com.noah.breakit.gamestate.outro.PixelDrip;
import com.noah.breakit.graphics.Screen;
import com.noah.breakit.input.Keyboard;
import com.noah.breakit.sound.music.Jukebox;
import com.noah.breakit.util.ColorFlasher;
import com.noah.breakit.util.Config;
import com.noah.breakit.util.FuzzRenderer;

public class Briefing extends BreakitGameState {

	private Keyboard key = null;
	
	private int count = 0;
	
	private boolean toTitle = false;

	public Briefing(Keyboard key) {
		this.key = key;
	}
	
	public void update() {
		Jukebox.play("briefingsong", true);
		
		key.update();
		
		if (key.enter) {
			toTitle = true;
			loadNextGameState();
		}
		if (count++ == 60 * 15)
			loadNextGameState();
	}

	public void render(Screen screen) {
		FuzzRenderer.render(screen, 128);

		int start = 0;
		for (int y = start; y < Config.WINDOW_HEIGHT; y += 4) {
			for (int x = start; x < Config.WINDOW_WIDTH; x += 4) {
				if (x == start || x == Config.WINDOW_WIDTH - 4 || y == start || y == Config.WINDOW_HEIGHT - 4)
					screen.fillRect(x, y, 4, 4, ColorFlasher.col);
			}
		}
		
		int height = (screen.getHeight() >> 3) + 4;
		String string = "-how to play-";
		screen.renderString8x8((screen.getWidth() >> 1) - ((string.length() << 3) >> 1), height,
				ColorFlasher.col, string);
		
		height += 20;
		string = "use a and d";
		screen.renderString8x8((screen.getWidth() >> 1) - ((string.length() << 3) >> 1), height,
				~ColorFlasher.col, string);
		
		height += 10;
		string =  "or <- and ->";
		screen.renderString8x8((screen.getWidth() >> 1) - ((string.length() << 3) >> 1), height,
				~ColorFlasher.col, string);
		
		height +=10;
		string = "to intercept the";
		screen.renderString8x8((screen.getWidth() >> 1) - ((string.length() << 3) >> 1), height,
				~ColorFlasher.col, string);
		
		height +=10;
		string = "ball! score bonus";
		screen.renderString8x8((screen.getWidth() >> 1) - ((string.length() << 3) >> 1), height,
				~ColorFlasher.col, string);
		
		height +=10;
		string = "points by breaking";
		screen.renderString8x8((screen.getWidth() >> 1) - ((string.length() << 3) >> 1), height,
				~ColorFlasher.col, string);
		
		height +=10;
		string = "more than one";
		screen.renderString8x8((screen.getWidth() >> 1) - ((string.length() << 3) >> 1), height,
				~ColorFlasher.col, string);
		
		height +=10;
		string = "brick per pass!";
		screen.renderString8x8((screen.getWidth() >> 1) - ((string.length() << 3) >> 1), height,
				~ColorFlasher.col, string);
		
		height +=20;
		string = "first 1up at 10000";
		screen.renderString8x8((screen.getWidth() >> 1) - ((string.length() << 3) >> 1), height,
				~ColorFlasher.col, string);
		
		height +=10;
		string = "pts! additional";
		screen.renderString8x8((screen.getWidth() >> 1) - ((string.length() << 3) >> 1), height,
				~ColorFlasher.col, string);
		
		height +=10;
		string = "1ups each score";
		screen.renderString8x8((screen.getWidth() >> 1) - ((string.length() << 3) >> 1), height,
				~ColorFlasher.col, string);
		
		height +=10;
		string = "doubling!";
		screen.renderString8x8((screen.getWidth() >> 1) - ((string.length() << 3) >> 1), height,
				~ColorFlasher.col, string);
		
		height +=20;
		string = "@good luck!@";
		screen.renderString8x8((screen.getWidth() >> 1) - ((string.length() << 3) >> 1), height,
				~ColorFlasher.col, string);
	}
	
	public void loadNextGameState() {
		Outro o = null;
		if(toTitle) {
			o = new PixelDrip(0xff00ff, new TitleScreen(key));
			o.captureScreen();
		} else {
			o = new PixelDrip(0x00ffff, new GameOver(key, -1));
			o.captureScreen();
		}
		ngs = o;
		finished = true;
	}
}