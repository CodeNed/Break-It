package com.noah.breakit.entity.mob;

import com.noah.breakit.entity.Entity;
import com.noah.breakit.util.Util;

public abstract class Mob extends Entity {

	protected int xa = 0;
	protected int ya = 0;

	protected int xdir = 0;
	protected int ydir = 0;

	protected int xspeed = 0;
	protected int yspeed = 0;
	
	protected int width = 0;
	protected int height = 0;

	protected int col = 0x000000;
	
	public Mob(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getx() {
		return x;
	}

	public int gety() {
		return y;
	}

	public int getxa() {
		return xa;
	}

	public int getya() {
		return ya;
	}
	
	public int getxdir() {
		return xdir;
	}
	
	public int getydir() {
		return ydir;
	}
	
	public int getxspeed() {
		return xspeed;
	}
	
	public int getyspeed() {
		return yspeed;
	}

	public  int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean collidesWith(Mob m) {

		int ml = m.getx();
		int mr = m.getx() + m.getWidth();
		int mt = m.gety();
		int mb = m.gety() + m.getHeight();
		
		int ystep = 1;
		if(ya != 0) ystep = Util.clamp(ya, -1, 1);
		
		int xstep = 1;
		if(xa != 0) xstep = Util.clamp(xa, -1, 1);
		
		for (int yi = 0; yi != ya + ystep; yi += ystep) {
			int t = y + yi;
			int b = y + yi + height;
			for (int xi = 0; xi != xa + xstep; xi += xstep) {
				int l = x + xi;
				int r = x + xi + width;
				if (l <= mr && r >= ml) {
					if (b >= mt && t <= mb){
						x += xi;
						y += yi;
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public void processCollision() {
	}

	public abstract void update();
}