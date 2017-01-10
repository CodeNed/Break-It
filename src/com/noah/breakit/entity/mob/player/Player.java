package com.noah.breakit.entity.mob.player;

import java.util.ArrayList;
import java.util.List;

import com.noah.breakit.entity.mob.Mob;
import com.noah.breakit.entity.state.State;
import com.noah.breakit.gamestate.Playfield;
import com.noah.breakit.graphics.Screen;
import com.noah.breakit.input.Keyboard;

public class Player extends Mob {

	List<State> secondaryStates = new ArrayList<>();
	
	Keyboard key = null;

	int score = 0;
	int rank = -1;
	String scoreStr = "";
	
	int toNext1UP = 10000;
	
	int lives = 3;

	public Player(int x, int y, Keyboard key, State state) {
		super(x, y, state);
		this.key = key;

		xspeed = 4;

		width = 32;
		height = 4;
		col = 0x00ffff;
	}
	
	public void init(Playfield playfield) {
		super.init(playfield);
		if(secondaryStates != null) {
			for(State s : secondaryStates)
				s.init(this);
		}
	}

	public void update() {
		state.update();

		for(int i = 0; i < secondaryStates.size(); i++)
			secondaryStates.get(i).update();
	}

	public void render(Screen screen) {
		state.render(screen);
		
		for(int i = 0; i < secondaryStates.size(); i++)
			secondaryStates.get(i).render(screen);
	}



	public Player setCoordinates(int x, int y) {
		this.x = x;
		this.y = y;
		return this;
	}

	public void addToScore(int add) {
		score += add;
	}

	public int getScore() {
		return score;
	}

	public void addToLives(int add) {
		lives += add;
	}

	public int getLives() {
		return lives;
	}

	public int getRank() {
		return rank;
	}
	
	public Keyboard getKey() {
		return key;
	}
	
	public State getPrimaryState() {
		return state;
	}
	
	public List<State> getSecondaryStates() {
		return secondaryStates;
	}
	
	public void addStateSecondaryPlayerShooting() {
		for(int i = 0; i < secondaryStates.size(); i++) {
			if(secondaryStates.get(i) instanceof StateSecondaryPlayerShooting) {
				secondaryStates.set(i, new StateSecondaryPlayerShooting());
				secondaryStates.get(i).init(this);
				return;
			}
		}
		StateSecondaryPlayerShooting s = new StateSecondaryPlayerShooting();
		secondaryStates.add(s);
		s.init(this);
	}
	
	public void addStateSecondaryPlayerWide() {
		for(int i = 0; i < secondaryStates.size(); i++) {
			if(secondaryStates.get(i) instanceof StateSecondaryPlayerWide) {
				secondaryStates.set(i, new StateSecondaryPlayerWide());
				secondaryStates.get(i).init(this);
				return;
			}
		}
		StateSecondaryPlayerWide s = new StateSecondaryPlayerWide();
		secondaryStates.add(s);
		s.init(this);		
	}
	
	public void removeSecondaryState(State s) {
		secondaryStates.remove(s);
	}
	
	public void clearSecondaryStates() {
		secondaryStates.clear();
	}
}