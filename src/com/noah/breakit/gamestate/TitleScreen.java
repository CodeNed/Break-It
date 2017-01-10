package com.noah.breakit.gamestate;

import com.noah.breakit.entity.mob.player.Player;
import com.noah.breakit.entity.mob.player.StatePrimaryPlayerAlive;
import com.noah.breakit.gamestate.outro.Outro;
import com.noah.breakit.gamestate.outro.PixelSpatter;
import com.noah.breakit.graphics.Screen;
import com.noah.breakit.input.Keyboard;
import com.noah.breakit.sound.SoundFX;
import com.noah.breakit.sound.music.Jukebox;
import com.noah.breakit.util.ColorFlasher;
import com.noah.breakit.util.Config;
import com.noah.breakit.util.FuzzRenderer;
import com.noah.breakit.util.Hud;

public class TitleScreen extends BreakitGameState {

	private Keyboard key;
	
	private String hiScoreStr = Hud.parseScore(Config.HI_SCORES.get(0).getScore());
	
	private final int[] title = { '#','#', 0 , 0 ,'#','#', 0 , 0 ,'#','#','#', 0 , 0, '#', 0 , 0 ,'#', 0 ,'#', 0 , 0 , 0 , 0 , 0 ,'#','#','#', 0 ,'#','#','#',
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
	}
	
	public void update() {
		Jukebox.play("titlesong", true);

		key.update();
		
		if ((key.enter && !key.enterLast)) {
			startGame = true;
			SoundFX.SELECT.play();
		}
		
		if((count++ == 15 * 60) || startGame)
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

		for (int y = 0; y < titleHeight; y++) {
			for (int x = 0; x < titleWidth; x++) {
				if (title[x + y * titleWidth] == '#') screen.drawRect((x << 2) + 16, (y << 2) + 80, 4, 4, ~ColorFlasher.col);
			}
		}

		int hudx = (screen.getWidth() >> 1) - ((7 << 3) >> 1) + (1 << 3);
		int hudy = 8;
		screen.renderString8x8(hudx - (3 << 3), hudy, ~ColorFlasher.col, "hi:");
		Hud.renderScore(screen, hudx, hudy, ~ColorFlasher.col, hiScoreStr);

		screen.renderString8x8(30, 125, ~ColorFlasher.col, "press enter!");
	}
	
	public void loadNextGameState() {
		Outro o = null;
		if (startGame) {
			Player p = new Player(Config.WINDOW_WIDTH / 2, Config.WINDOW_HEIGHT - 16 , key, new StatePrimaryPlayerAlive());
			Playfield pf = new Playfield(p, 0, null, Jukebox.playfieldlist.get(0)).init(); 
			o = new PixelSpatter(0xff00ff, pf);
			o.captureScreen();
			SoundFX.SELECT.play();
		} else
			o = new PixelSpatter(0x00ffff, new Briefing(key));
		o.captureScreen();
		ngs = o;
		
		finished = true;
	}
}