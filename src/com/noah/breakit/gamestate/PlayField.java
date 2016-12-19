package com.noah.breakit.gamestate;

import java.util.ArrayList;
import java.util.List;

import com.noah.breakit.entity.mob.Ball;
import com.noah.breakit.entity.mob.FloatingScore;
import com.noah.breakit.entity.mob.Particle;
import com.noah.breakit.entity.mob.Player;
import com.noah.breakit.entity.mob.Target;
import com.noah.breakit.entity.spawner.Spawner;
import com.noah.breakit.graphics.Screen;
import com.noah.breakit.sound.music.Jukebox;
import com.noah.breakit.stagepatterns.StagePattern;
import com.noah.breakit.transition.PixelDrip;
import com.noah.breakit.transition.PixelSpatter;

public class PlayField extends GameState {

	private int stage;
	private char[] stagePattern;
	
	private Player player;
	private Ball ball;
	private List<Target> targets = new ArrayList<Target>();
	private List<Particle> particles = new ArrayList<Particle>();
	private List<Spawner> spawners = new ArrayList<Spawner>();
	private List<FloatingScore> floatingScores = new ArrayList<FloatingScore>();
	
	private PixelSpatter pixelSpatter = new PixelSpatter();
	private PixelDrip pixelDrip = new PixelDrip();

	private int height;
	private int width;

	private boolean levelUp;
	private int count;

	public PlayField(int width, int height, Player player, char[] stagePattern, int stage) {
		this(width, height, player);

		this.stagePattern = stagePattern;
		this.stage = stage;
		generateTargets();
	}

	public PlayField(int width, int height, Player player, int stage) {
		this(width, height, player);

		this.stage = stage;

		stagePattern = new char[StagePattern.getStagePattern(stage).length];
		for (int i = 0; i < StagePattern.getStagePattern(stage).length; i++)
			stagePattern[i] = StagePattern.getStagePattern(stage)[i];

		generateTargets();
	}

	private PlayField(int width, int height, Player player) {
		this.width = width;
		this.height = height;
		this.player = player;
		this.player.init(this);
		ball = new Ball(player.getx() + (player.getWidth() >> 1), player.gety() - player.getHeight(), player.getKey());
		ball.init(this);
	}

	public void updateGS() {
		
		if(!Jukebox.playing() && !Jukebox.songNameIs("playfieldintro"))
			Jukebox.play("playfieldintro", false);
		else if(!Jukebox.playing()){
			Jukebox.rewind();
			Jukebox.play("playfieldbody", true);
		}
		
		if (!levelUp) {
			player.update();
			ball.update();
		} else
			if (count++ == 60 * 3) transition = true;

		for (Target t : targets)
			t.update();

		for (Particle p : particles)
			p.update();

		for (Spawner s : spawners)
			s.update();
		
		for(FloatingScore f : floatingScores)
			f.update();

		checkMobCollision();
		removeTargets();
		removeParticles();
		removeSpawners();
		removeFloatingScores();

		if (targets.size() == 0) {
			captureScreen();
			levelUp = true;
		}
	}

	public void updateTX() {
		
		if (levelUp){
			pixelSpatter.pixelSpatter(0x00ffff, pixels);
			finished = Jukebox.fadeToBlack() && pixelSpatter.isFinished();
			
		} else {
			pixelDrip.pixelDrip(0xff00ff, pixels);
			finished = Jukebox.fadeToBlack() && pixelDrip.isFinished();
		}
		
		if(finished) loadNextGameState(); 
		
	}

	public void renderGS(Screen screen) {

		for (int i = 0; i < 32; i++) {
			screen.fillRect(random.nextInt(screen.getWidth()), random.nextInt(screen.getHeight()), 1, 1,
					random.nextInt(0xffffff));
		}

		for (Target t : targets)
			t.render(screen);

		for (FloatingScore f : floatingScores)
			f.render(screen);
		
		for (Particle p : particles)
			p.render(screen);
		

		ball.render(screen);
		player.render(screen);
	}

	public void renderTX(Screen screen) {
		renderScreenCap(screen);
	}

	public void addParticle(Particle p) {
		particles.add(p);
		p.init(this);
	}

	public void addSpawner(Spawner s) {
		spawners.add(s);
		s.init(this);
	}

	public void addTarget(Target t) {
		targets.add(t);
		t.init(this);
	}
	
	public void addFloatingScore(FloatingScore f) {
		floatingScores.add(f);
		f.init(this);
	}

	private void generateTargets() {

		for (int y = 0; y < stagePattern.length / 10; y++) {
			for (int x = 0; x < 10; x++)
				if (stagePattern[x + y * 10] == '#') addTarget(new Target(16 * x + 1, 8 * (y + 2)));
		}
	}

	private void checkMobCollision() {
		if(!player.isAlive() || !ball.isAlive())
			return;
		
		if (ball.collidesWith(player)) ball.processCollision(player);
		for (int i = 0; i < targets.size(); i++) {
			if (ball.collidesWith(targets.get(i))) {
				targets.get(i).processCollision();
				ball.processCollision(targets.get(i));
			}
		}
	}

	private void removeTargets() {
		for (int i = 0; i < targets.size(); i++) {
			if (targets.get(i).isRemoved()) targets.remove(i);
		}
	}

	private void removeParticles() {
		for (int i = 0; i < particles.size(); i++)
			if (particles.get(i).isRemoved()) particles.remove(i);
	}

	private void removeSpawners() {
		for (int i = 0; i < spawners.size(); i++)
			if (spawners.get(i).isRemoved()) spawners.remove(i);
	}
	
	private void removeFloatingScores() {
		for(int i = 0; i < floatingScores.size(); i++)
			if(floatingScores.get(i).isRemoved()) floatingScores.remove(i);
	}

	public Ball getBall() {
		return ball;
	}

	public Player getPlayer() {
		return player;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getStage() {
		return stage;
	}

	public void setStagePattern(int index, char value) {
		stagePattern[index] = value;
	}
	
	protected void loadNextGameState(){
		if(nextGameState != null)
			return;
		
		if (player.getLives() > 0) {
			if(player.isAlive()) {
				if(++stage > 29)
					stage = 0;
				nextGameState = new PlayField(width, height, player.setCoordinates(width / 2, height - 8), stage);
			} else {
				player.setIsAlive(true);
				nextGameState = new PlayField(width, height, player.setCoordinates(width / 2, height - 8),
						stagePattern, stage);
			}
		} else
			nextGameState = new GameOver(player.getKey(), player.getRank());
	}
}