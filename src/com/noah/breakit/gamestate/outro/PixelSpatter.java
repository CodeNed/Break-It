package com.noah.breakit.gamestate.outro;

import java.util.ArrayList;
import java.util.List;

import com.noah.breakit.gamestate.BreakitGameState;
import com.noah.breakit.sound.music.Jukebox;
import com.noah.breakit.util.Config;
import com.noah.breakit.util.Util;

public class PixelSpatter extends Outro {

	private List<Integer> n = new ArrayList<Integer>();

	public PixelSpatter(int col, BreakitGameState ngs) {
		super(col, ngs);
		for (int i = 0; i < Config.WINDOW_WIDTH * Config.WINDOW_HEIGHT; i++)
			n.add(i, i);
	}

	public void update() {
		for (int i = 0; i < 512; i++) {
			if (n.size() > 0) {
				int rand = n.remove(Util.random.nextInt(n.size()));
				pixels[rand] = col;
			}
		}
		
		boolean musicFade= Jukebox.fadeToBlack();
		boolean pixelFade = fadeToBlack(4, pixels);
		if(musicFade && pixelFade)
			finished = true;
	}

	public void loadNextGameState() {
	}
}