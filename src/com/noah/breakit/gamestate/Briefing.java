package com.noah.breakit.gamestate;

import com.noah.breakit.game.Game;
import com.noah.breakit.graphics.Screen;
import com.noah.breakit.input.Keyboard;
import com.noah.breakit.sound.music.Jukebox;
import com.noah.breakit.transition.PixelDrip;
import com.noah.breakit.util.ColorFlasher;

public class Briefing extends GameState {

	private Keyboard key;
	
	private int count;
	
	private boolean toTitle;

	PixelDrip pixelDrip = new PixelDrip();

	public Briefing(Keyboard key) {
		this.key = key;
	}

	public void updateGS() {
		
		if(!Jukebox.playing())
			Jukebox.play("briefingsong", true);
		
		key.update();
		
		if (key.enter) {
			captureScreen();
			toTitle = true;
			transition = true;
			return;
		}
		
		if (count++ == 60 * 15) {
			captureScreen();
			transition = true;
			return;
		}
	}

	public void renderGS(Screen screen) {
		for (int i = 0; i < 128; i++)
			screen.fillRect(random.nextInt(screen.getWidth()), random.nextInt(screen.getHeight()), 1, 1,
					random.nextInt(0xffffff));

		int start = 0;
		for (int y = start; y < Game.height; y += 4) {
			for (int x = start; x < Game.width; x += 4) {
				if (x == start || x == Game.width - 4 || y == start || y == Game.height - 4)
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
		string = "first 1up at 20000";
		screen.renderString8x8((screen.getWidth() >> 1) - ((string.length() << 3) >> 1), height,
				~ColorFlasher.col, string);
		
		height +=10;
		string = "pts! additional";
		screen.renderString8x8((screen.getWidth() >> 1) - ((string.length() << 3) >> 1), height,
				~ColorFlasher.col, string);
		
		height +=10;
		string = "1ups each 30000";
		screen.renderString8x8((screen.getWidth() >> 1) - ((string.length() << 3) >> 1), height,
				~ColorFlasher.col, string);
		
		height +=10;
		string = "pts!";
		screen.renderString8x8((screen.getWidth() >> 1) - ((string.length() << 3) >> 1), height,
				~ColorFlasher.col, string);
		
		height +=20;
		string = "@good luck!@";
		screen.renderString8x8((screen.getWidth() >> 1) - ((string.length() << 3) >> 1), height,
				~ColorFlasher.col, string);
	}

	public void updateTX() {
		pixelDrip.pixelDrip(0x00ffff, pixels);
		finished = Jukebox.fadeToBlack() && pixelDrip.isFinished();
	}

	public void renderTX(Screen screen) {
		renderScreenCap(screen);
	}
	
	public GameState getNextGameState() {
		if(toTitle)
			return new TitleScreen(key, Game.hiScores);
		
		return new GameOver(key, Game.hiScores, -1);
	}
}