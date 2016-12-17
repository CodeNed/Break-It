package com.noah.breakit.gamestate;

import java.util.List;

import com.noah.breakit.entity.mob.Player;
import com.noah.breakit.game.Game;
import com.noah.breakit.game.Hud;
import com.noah.breakit.graphics.Screen;
import com.noah.breakit.input.Keyboard;
import com.noah.breakit.sound.SoundFX;
import com.noah.breakit.sound.music.Jukebox;
import com.noah.breakit.transition.PixelSpatter;
import com.noah.breakit.util.ColorFlasher;

public class TitleScreen extends GameState {

	private Keyboard key;

	private List<Integer> hiScores;

	private String hiScoreStr = "";

	private final int[] title = { '#', '#', 0, 0, '#', '#', 0, 0, '#', '#', '#', 0, 0, '#', 0, 0, '#', 0, '#', 0, 0, 0,
			0, 0, '#', '#', '#', 0, '#', '#', '#', '#', 0, '#', 0, '#', 0, '#', 0, '#', 0, 0, 0, '#', 0, '#', 0, '#', 0,
			'#', 0, 0, 0, 0, 0, 0, '#', 0, 0, 0, '#', 0, '#', '#', 0, 0, '#', '#', 0, 0, '#', '#', 0, 0, '#', '#', '#',
			0, '#', '#', 0, 0, '#', '#', '#', 0, 0, '#', 0, 0, 0, '#', 0, '#', 0, '#', 0, '#', 0, '#', 0, '#', 0, 0, 0,
			'#', 0, '#', 0, '#', 0, '#', 0, 0, 0, 0, 0, 0, '#', 0, 0, 0, '#', 0, '#', '#', 0, 0, '#', 0, '#', 0, '#',
			'#', '#', 0, '#', 0, '#', 0, '#', 0, '#', 0, 0, 0, 0, 0, '#', '#', '#', 0, 0, '#', 0 };

	private int titleHeight = 5;
	private int titleWidth = 31;

	private ColorFlasher colorFlash = new ColorFlasher(0x0000ff);

	private int count;
	private boolean startGame;
	
	private PixelSpatter pixelSpatter = new PixelSpatter();

	public TitleScreen(Keyboard key, List<Integer> hiScores) {
		this.key = key;
		this.hiScores = hiScores;
		hiScoreStr = Hud.parseScore(hiScores.get(0));
	}

	public void updateGS() {
		
		if(!Jukebox.playing()){
			Jukebox.play("titlesong", true);
		}

		colorFlash.update();

		key.update();
		if (key.enter) {
			captureScreen();
			startGame = true;
			transition = true;
			SoundFX.select.play();
		}

		if (count++ == 60 * 15) {
			captureScreen();
			transition = true;
		}
	}

	public void updateTX() {
		pixelSpatter.pixelSpatter(0x00ffff, pixels);
		finished = Jukebox.fadeToBlack() && pixelSpatter.isFinished();
	}

	public void renderGS(Screen screen) {

		for (int i = 0; i < 128; i++)
			screen.fillRect(random.nextInt(screen.getWidth()), random.nextInt(screen.getHeight()), 1, 1,
					random.nextInt(0xffffff));

		int start = 0;
		for (int y = start; y < Game.height; y += 4) {
			for (int x = start; x < Game.width; x += 4) {
				if (x == start || x == Game.width - 4 || y == start || y == Game.height - 4)
					screen.fillRect(x, y, 4, 4, colorFlash.col);
			}
		}

		for (int y = 0; y < titleHeight; y++) {
			for (int x = 0; x < titleWidth; x++) {
				if (title[x + y * titleWidth] == '#') screen.drawRect((x << 2) + 16, (y << 2) + 80, 4, 4, ~colorFlash.col);
			}
		}

		int hudx = (screen.getWidth() >> 1) - ((7 << 3) >> 1) + (1 << 3);
		int hudy = 8;
		screen.renderString8x8(hudx - (3 << 3), hudy, ~colorFlash.col, "hi:");
		Hud.renderScore(screen, hudx, hudy, ~colorFlash.col, hiScoreStr);

		screen.renderString8x8(30, 125, ~colorFlash.col, "press enter!");
	}

	public void renderTX(Screen screen) {
		renderScreenCap(screen);
	}

	public GameState getNextGameState() {
		if (startGame) {
			Player player = new Player(Game.width / 2, Game.height - 8, Game.key);
			return new PlayField(Game.width, Game.height, hiScores, player, 0);
		}

		return new Briefing(key);
	}
}