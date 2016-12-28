package com.noah.breakit.gamestate;

import com.noah.breakit.entity.mob.player.Player;
import com.noah.breakit.entity.mob.player.StatePrimaryPlayerAlive;
import com.noah.breakit.game.Game;
import com.noah.breakit.graphics.Screen;
import com.noah.breakit.input.Keyboard;
import com.noah.breakit.sound.SoundFX;
import com.noah.breakit.sound.music.Jukebox;
import com.noah.breakit.transition.PixelSpatter;
import com.noah.breakit.util.ColorFlasher;
import com.noah.breakit.util.Hud;
import com.noah.breakit.util.Util;

public class TitleScreen extends GameState {

	private Keyboard key;

	private String hiScoreStr = "";

	private final int[] TITLE = { '#','#', 0 , 0 ,'#','#', 0 , 0 ,'#','#','#', 0 , 0, '#', 0 , 0 ,'#', 0 ,'#', 0 , 0 , 0 , 0 , 0 ,'#','#','#', 0 ,'#','#','#',
			                      '#', 0 ,'#', 0 ,'#', 0 ,'#', 0 ,'#', 0 , 0 , 0 ,'#', 0, '#', 0 ,'#', 0 ,'#', 0 , 0 , 0 , 0 , 0 , 0 ,'#', 0 , 0 , 0 ,'#', 0 ,
			                      '#','#', 0 , 0 ,'#','#', 0 , 0, '#','#', 0 , 0 ,'#','#','#', 0 ,'#','#', 0 , 0 ,'#','#','#', 0 , 0 ,'#', 0 , 0 , 0, '#', 0 ,
			                      '#', 0 ,'#', 0 ,'#', 0 ,'#', 0, '#', 0 , 0 , 0 ,'#', 0 ,'#', 0 ,'#', 0 ,'#', 0 , 0 , 0 , 0 , 0 , 0 ,'#', 0 , 0 , 0, '#', 0 ,
			                      '#','#', 0 , 0 ,'#', 0, '#', 0, '#','#','#', 0 ,'#', 0, '#', 0 ,'#', 0 ,'#', 0 , 0 , 0 , 0 , 0 ,'#','#','#', 0 , 0, '#', 0 };

	private int titleHeight = 5;
	private int titleWidth = 31;

	private int count = 0;
	private boolean startGame = false;

	public TitleScreen(Keyboard key) {
		this.key = key;
		hiScoreStr = Hud.parseScore(Game.HI_SCORES.get(0));
	}

	public void updateGS() {
		
		Jukebox.play("titlesong", true);

		key.update();
		if (key.enter && !key.enterLast) {
			captureScreen();
			startGame = true;
			setTransitioning(true, new PixelSpatter(0xff00ff));
			SoundFX.SELECT.play();
		}

		if (count++ == 60 * 15) {
			captureScreen();
			setTransitioning(true, new PixelSpatter(0x00ffff));
		}
	}

	public void updateTX() {
		transition.update(pixels);
		finished = Jukebox.fadeToBlack() && transition.isFinished();
		if(finished) loadNextGameState();
	}

	public void renderGS(Screen screen) {

		for (int i = 0; i < 128; i++)
			screen.fillRect(Util.random.nextInt(screen.getWidth()), Util.random.nextInt(screen.getHeight()), 1, 1,
					Util.random.nextInt(0xffffff));

		int start = 0;
		for (int y = start; y < Game.HEIGHT; y += 4) {
			for (int x = start; x < Game.WIDTH; x += 4) {
				if (x == start || x == Game.WIDTH - 4 || y == start || y == Game.HEIGHT - 4)
					screen.fillRect(x, y, 4, 4, ColorFlasher.col);
			}
		}

		for (int y = 0; y < titleHeight; y++) {
			for (int x = 0; x < titleWidth; x++) {
				if (TITLE[x + y * titleWidth] == '#') screen.drawRect((x << 2) + 16, (y << 2) + 80, 4, 4, ~ColorFlasher.col);
			}
		}

		int hudx = (screen.getWidth() >> 1) - ((7 << 3) >> 1) + (1 << 3);
		int hudy = 8;
		screen.renderString8x8(hudx - (3 << 3), hudy, ~ColorFlasher.col, "hi:");
		Hud.renderScore(screen, hudx, hudy, ~ColorFlasher.col, hiScoreStr);

		screen.renderString8x8(30, 125, ~ColorFlasher.col, "press enter!");
	}

	public void renderTX(Screen screen) {
		renderScreenCap(screen);
	}
	
	protected void loadNextGameState() {
		if (startGame) {
			Player p = new Player(Game.WIDTH / 2, Game.HEIGHT - 12 , key, new StatePrimaryPlayerAlive());
			Playfield pf = new Playfield(p, 0, null, Jukebox.playfieldlist.get(0)); 
			pf.init();
			ngs = pf;
		} else
			ngs = new Briefing(key);
	}
}