package com.noah.breakit.gamestate;

import com.noah.breakit.gamestate.outro.Outro;
import com.noah.breakit.gamestate.outro.PixelSpatter;
import com.noah.breakit.graphics.Screen;
import com.noah.breakit.graphics.Sprite;
import com.noah.breakit.input.Keyboard;
import com.noah.breakit.util.Config;

public class CreditScreen extends BreakitGameState{
	
	private Keyboard key = null;
	
	private Sprite bitBurgerSprite = new Sprite(50, 50, 32, 32, "bit_burger.png");
	private Sprite littleRobotSprite = new Sprite(0, 100, 160, 46, "little_robot.png");
	
	private int count = 0;
	
	public CreditScreen(Keyboard key){
		this.key = key;
		bitBurgerSprite.setX(10);
		bitBurgerSprite.setY(30);
		littleRobotSprite.setY(100);
	}
	
	public void update() {
		
		key.update();
		
		if(count++ == 60 * 15 || key.enter) {
			loadNextGameState();
		}
	}
	
	public void render(Screen screen) {
		screen.renderCol(0x00bbbb);
		
		String str = "a bitburger";
		screen.renderString8x8(50, 38, 0x000000, str);
		str =  "production";
		screen.renderString8x8(50, 48, 0x000000, str);
		
		str = "sfx courtesy of";
		screen.renderString8x8(Config.WINDOW_WIDTH / 2  - (str.length() * 8) / 2, 92, 0x000000, str);
		bitBurgerSprite.render(screen);
		littleRobotSprite.render(screen);
		
		str = "music by";
		screen.renderString8x8(Config.WINDOW_WIDTH / 2  - (str.length() * 8) / 2, 162, 0x000000, str);
		
		str = "sketchylogic";
		screen.renderString8x8(Config.WINDOW_WIDTH / 2  - (str.length() * 8) / 2, 172, 0x0000ff, str);
		
		str = "programmed by";
		screen.renderString8x8(Config.WINDOW_WIDTH / 2  - (str.length() * 8) / 2, 202, 0x000000, str);
		
		str = "daytripperid";
		screen.renderString8x8(Config.WINDOW_WIDTH / 2  - (str.length() * 8) / 2, 212, 0xff0000, str);
	}

	public void loadNextGameState() {
		Outro o = new PixelSpatter(0x00ffff, new TitleScreen(key));
		o.captureScreen();
		ngs = o;
		finished = true;
	}
}