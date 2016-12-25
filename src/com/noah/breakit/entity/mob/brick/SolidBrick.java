package com.noah.breakit.entity.mob.brick;

import com.noah.breakit.entity.mob.ball.Ball;
import com.noah.breakit.entity.mob.projectile.Projectile;
import com.noah.breakit.graphics.Screen;
import com.noah.breakit.sound.SoundFX;

public class SolidBrick extends Brick {

	public SolidBrick(int x, int y) {
		super(x, y);
		col = 0x808080;
	}

	public void processCollision(Ball b) {
		SoundFX.PING.play();
	}

	public void processCollision(Projectile p) {
		// leave empty
	}

	public void update() {
		// leave empty
	}

	public void render(Screen screen) {
		int c = col;
		int x = this.x;
		int y = this.y;
		int w = width;
		int h = height;
		
		for(int i = 0; i < height; i++) {
			screen.drawRect(x++, y++, w, h, c);
			w-=2;
			h-=2;
			c -= 0x101010;	
		}
	}
}