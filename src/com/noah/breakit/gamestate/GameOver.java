package com.noah.breakit.gamestate;

import com.noah.breakit.gamestate.outro.Outro;
import com.noah.breakit.gamestate.outro.PixelSpatter;
import com.noah.breakit.graphics.Screen;
import com.noah.breakit.input.Keyboard;
import com.noah.breakit.sound.music.Jukebox;
import com.noah.breakit.util.ColorFlasher;
import com.noah.breakit.util.Config;
import com.noah.breakit.util.FuzzRenderer;
import com.noah.breakit.util.Hud;

public class GameOver extends BreakitGameState {

	private Keyboard key = null;
	
	private int count = 0;
	
	private String[] hiScoreStr = new String[Config.NUM_HI_SCORES];
	private int rank = -1;
	
	public GameOver(Keyboard key, int rank) {
		this.key = key;
		this.rank = rank;
		for (int i = 0; i < Config.NUM_HI_SCORES; i++) {
			hiScoreStr[i] = "";
			hiScoreStr[i] = Config.HI_SCORES.get(i).getInitials() 
					+ "-" + Hud.parseScore(Config.HI_SCORES.get(i).getScore());
		}
	}
	
	public void update() {
		Jukebox.play("gameoversong", true);

		key.update();
		if (key.enter || count++ == 60 * 15)
			loadNextGameState();		
	}

	public void render(Screen screen) {
		FuzzRenderer.render(screen, 128);

		String string = "game over";
		screen.renderString8x8((screen.getWidth() >> 1) - ((string.length() << 3) >> 1), (screen.getHeight() >> 3) + 4,
				ColorFlasher.col, string);

		String title = "-high scores-";
		int titlex = (screen.getWidth() >> 1) - ((title.length() << 3) >> 1);
		int titley = ((screen.getHeight() >> 1) - 4) - ((hiScoreStr.length >> 1) << 3) - 4;
		screen.renderString8x8(titlex, titley - 8 - 4, ~ColorFlasher.col, title);

		int hudx = (screen.getWidth() >> 1) - ((hiScoreStr[0].length() << 3) >> 1);
		int hudy = ((screen.getHeight() >> 1) - 4) - ((hiScoreStr.length >> 1) << 3);

		int ofs = 0;
		for (int i = 0; i < hiScoreStr.length; i++) {
			int col = 0xffffff;
			if (i == rank){
				col = ColorFlasher.col;
				screen.renderString8x8(hudx - 8, hudy +(i << 3) + ofs, ~col, "@");
				screen.renderString8x8(hudx + (hiScoreStr[i].length() << 3) + 1, hudy +(i << 3) + ofs, ~col, "@");
			}
			screen.renderString8x8(hudx, hudy + (i << 3) + ofs, col, hiScoreStr[i]);
			ofs += 4;
		}		
	}
	
	public void loadNextGameState() {
		Outro o = new PixelSpatter(0xff00ff, new TitleScreen(key));
		o.captureScreen();
		ngs = o;
		finished = true;
	}
}