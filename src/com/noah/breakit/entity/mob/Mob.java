package com.noah.breakit.entity.mob;

import com.noah.breakit.entity.Entity;
import com.noah.breakit.entity.state.State;
import com.noah.breakit.gamestate.Playfield;
import com.noah.breakit.util.Util;

public abstract class Mob extends Entity {
	
	protected State state = null;
	
	protected int xa = 0;
	protected int ya = 0;

	protected int xdir = 0;
	protected int ydir = 0;

	protected int xspeed = 0;
	protected int yspeed = 0;
	
	protected int width = 0;
	protected int height = 0;

	protected int col = 0x000000;
	
	public Mob(int x, int y, State state) {
		this.state = state;
		this.x = x;
		this.y = y;
	}
	
	public final void init(Playfield playfield) {
		super.init(playfield);
		if(state != null)
			state.init(this);
	}
	
	public abstract void update();
	
	public final void moveX() {
		x += xa;
	}
	
	public final void moveY() {
		y += ya;
	}
	
	public final void updateXa() {
		xa = xspeed * xdir;
	}
	
	public final void updateYa() {
		ya = yspeed * ydir;
	}

	public final boolean collidesWith(Mob m) {

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
	
	public final State getState() {
		return state;
	}
	
	public final int getxa() {
		return xa;
	}

	public final int getya() {
		return ya;
	}
	
	public final int getxdir() {
		return xdir;
	}
	
	public final int getydir() {
		return ydir;
	}
	
	public final int getxspeed() {
		return xspeed;
	}
	
	public final int getyspeed() {
		return yspeed;
	}

	public final int getWidth() {
		return width;
	}

	public final int getHeight() {
		return height;
	}
	
	public final int getCol() {
		return col;
	}
	
	public final void setState(State s) {
		state = s;
	} 

	public final void setxa(int xa) {
		this.xa =xa;
	}

	public final void setya(int ya) {
		this.ya = ya;
	}
	
	public final void setxdir(int xdir) {
		this.xdir = xdir;
	}
	
	public final void setydir(int ydir) {
		this.ydir = ydir;
	}
	
	public final void setxspeed(int xspeed) {
		this.xspeed = xspeed;
	}
	
	public final void setyspeed(int yspeed) {
		this.yspeed = yspeed;
	}

	public final void setWidth(int width) {
		this.width = width;
	}

	public final void setHeight(int height) {
		this.height = height;
	}
	
	public final void setCol(int col) {
		this.col = col;
	}
}