package com.noah.breakit.gamestate;

import java.util.List;

import com.noah.breakit.game.Game;
import com.noah.breakit.game.Hud;
import com.noah.breakit.graphics.Screen;
import com.noah.breakit.input.Keyboard;
import com.noah.breakit.sound.Song;
import com.noah.breakit.transition.PixelSpatter;

public class GameOver extends GameState {

	private Keyboard key;

	private List<Integer> hiScores;
	private String[] hiScoreStr = new String[10];
	private int rank;
	private int col = 0x0000ff;
	private int fade = 1;
	
	private int count;
	
	private PixelSpatter pixelSpatter = new PixelSpatter();

	public GameOver(Keyboard key, List<Integer> hiScores, int rank) {
		this.key = key;
		this.hiScores = hiScores;
		this.rank = rank;

		for (int i = 0; i < 10; i++) {
			hiScoreStr[i] = "";
			hiScoreStr[i] = Hud.parseScore(hiScores.get(i));
		}
	}

	public void updateGS() {
		
		if(!songStarted){
			Song.gameoversong.play(true);
			songStarted = true;
		}

		int r = (col & 0xff0000) >> 16;
		int g = (col & 0xff00) >> 8;
		int b = col & 0xff;

		if (r >= 0 && r <= 255) r += 5 * fade;
		b += 5 * fade * -1;

		col = (r << 16) | (g << 8) | b;

		if (r >= 255) {
			fade = -1;
			r = 254;
		}

		if (r <= 0) {
			fade = 1;
			r = 1;
		}

		key.update();
		if (key.enter || count++ == 60 * 15){
			captureScreen();
			transition = true;
		}
	}

	public void updateTX() {
		pixelSpatter.pixelSpatter(0xff00ff, pixels);
		finished = Song.gameoversong.fadeToBlack() &&  pixelSpatter.isFinished();
	}

	public void renderGS(Screen screen) {

		for (int i = 0; i < 128; i++)
			screen.fillRect(random.nextInt(screen.getWidth()), random.nextInt(screen.getHeight()), 1, 1,
					random.nextInt(0xffffff));

		String string = "game over";
		screen.renderString((screen.getWidth() >> 1) - ((string.length() << 3) >> 1), (screen.getHeight() >> 3) + 4,
				col, string);

		String title = "-high scores-";
		int titlex = (screen.getWidth() >> 1) - ((title.length() << 3) >> 1);
		int titley = ((screen.getHeight() >> 1) - 4) - ((hiScoreStr.length >> 1) << 3) - 4;
		screen.renderString(titlex, titley - 8 - 4, ~col, title);

		int hudx = (screen.getWidth() >> 1) - ((hiScoreStr[0].length() << 3) >> 1);
		int hudy = ((screen.getHeight() >> 1) - 4) - ((hiScoreStr.length >> 1) << 3);

		int ofs = 0;
		for (int i = 0; i < hiScoreStr.length; i++) {
			int col = 0xffffff;
			if (i == rank){
				col = this.col;
				screen.renderString(hudx - 8, hudy +(i<<3) + ofs, ~col, "@");
				screen.renderString(hudx + (hiScoreStr[i].length() << 3) + 1, hudy +(i<<3) + ofs, ~col, "@");
			}
			Hud.renderScore(screen, hudx, hudy + (i << 3) + ofs, col, hiScoreStr[i]);
			ofs += 4;
		}
	}

	public void renderTX(Screen screen) {
		renderScreenCap(screen);
	}

	public GameState getNextGameState() {
		return new TitleScreen(Game.key, hiScores);
	}
}