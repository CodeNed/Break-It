package com.noah.breakit.gamestate;

import com.noah.breakit.game.Game;
import com.noah.breakit.graphics.Screen;
import com.noah.breakit.graphics.Sprite;
import com.noah.breakit.input.Keyboard;
import com.noah.breakit.transition.PixelSpatter;

public class CreditScreen extends GameState{
	
	private Keyboard key = null;
	
	private Sprite bitBurgerSprite = new Sprite(50, 50, 32, 32, "bit_burger.png");
	private Sprite littleRobotSprite = new Sprite(0, 100, 160, 46, "little_robot.png");
	
	private int count = 0;
	
	public CreditScreen(Keyboard key){
		this.key = key;
	}
	
	public void updateGS() {
		
		key.update();
		
		bitBurgerSprite.setX(10);
		bitBurgerSprite.setY(30);
		
		littleRobotSprite.setY(100);
		
		if(count++ == 60 * 15 || key.enter){
			setTransitioning(true, new PixelSpatter(0x00ffff));
			captureScreen();
		}
	}

	public void renderGS(Screen screen) {
		
		screen.renderCol(0x559955);
		
		String str = "a bitburger";
		screen.renderString8x8(50, 30, 0x000000, str);
		str =  "production";
		screen.renderString8x8(50, 40, 0x000000, str);
		
		str = "sfx courtesy of";
		screen.renderString8x8(Game.WIDTH / 2  - (str.length() * 8) / 2, 92, 0x000000, str);
		bitBurgerSprite.render(screen);
		littleRobotSprite.render(screen);
		
		str = "music by";
		screen.renderString8x8(Game.WIDTH / 2  - (str.length() * 8) / 2, 162, 0x000000, str);
		
		str = "sketchylogic";
		screen.renderString8x8(Game.WIDTH / 2  - (str.length() * 8) / 2, 172, 0x0000ff, str);
		
		str = "programmed by";
		screen.renderString8x8(Game.WIDTH / 2  - (str.length() * 8) / 2, 202, 0x000000, str);
		
		str = "daytripperid";
		screen.renderString8x8(Game.WIDTH / 2  - (str.length() * 8) / 2, 212, 0xff0000, str);
	}

	public void updateTX() {
		transition.update(pixels);		
		finished = transition.isFinished();
		if(finished) loadNextGameState();
	}

	public void renderTX(Screen screen) {
		renderScreenCap(screen);
	}

	protected void loadNextGameState() {
		ngs = new TitleScreen(key);
	}
}
