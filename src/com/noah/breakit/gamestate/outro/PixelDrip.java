package com.noah.breakit.gamestate.outro;

import com.noah.breakit.gamestate.BreakitGameState;
import com.noah.breakit.sound.music.Jukebox;
import com.noah.breakit.util.Config;
import com.noah.breakit.util.Util;

public class PixelDrip extends Outro {
	
	private int[] ylast = new int[Config.WINDOW_WIDTH];
	private int[] yrate = new int[Config.WINDOW_WIDTH];

	public PixelDrip(int col, BreakitGameState ngs){
		super(col, ngs);
		for (int i = 0; i < Config.WINDOW_WIDTH; i++)
			yrate[i] = Util.random.nextInt(10) + 4;
	}
	
	public void update() {
		for (int x = 0; x < Config.WINDOW_WIDTH; x++) {
			int max = Util.clamp(ylast[x] + yrate[x], 0, Config.WINDOW_HEIGHT);
			for (int y = ylast[x]; y < max; y++) {
				pixels[x + y * Config.WINDOW_WIDTH] = col;
			}
			ylast[x] = Util.clamp(ylast[x] + yrate[x], 0, Config.WINDOW_HEIGHT);
		}
		
		boolean musicFade= Jukebox.fadeToBlack();
		boolean pixelFade = fadeToBlack(4, pixels);
		if(musicFade && pixelFade)
			finished = true;
	}

	public void loadNextGameState() {
	}
}