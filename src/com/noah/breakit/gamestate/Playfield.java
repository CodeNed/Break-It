package com.noah.breakit.gamestate;

import java.util.ArrayList;
import java.util.List;

import com.noah.breakit.entity.mob.ball.Ball;
import com.noah.breakit.entity.mob.decoration.FloatingText;
import com.noah.breakit.entity.mob.decoration.Particle;
import com.noah.breakit.entity.mob.player.Player;
import com.noah.breakit.entity.mob.powerup.Powerup;
import com.noah.breakit.entity.mob.projectile.Projectile;
import com.noah.breakit.entity.mob.stationary.ForceField;
import com.noah.breakit.entity.mob.stationary.Target;
import com.noah.breakit.entity.spawner.ParticleSpawner;
import com.noah.breakit.game.Game;
import com.noah.breakit.graphics.Screen;
import com.noah.breakit.sound.music.Jukebox;
import com.noah.breakit.stagepatterns.StagePattern;
import com.noah.breakit.transition.PixelDrip;
import com.noah.breakit.transition.PixelSpatter;

public class Playfield extends GameState {

	private int stage = 0;
	private char[] stagePattern = null;
	
	private Player player = null;
	private ForceField forceField = null;
	
	private List<Ball> balls = new ArrayList<>();
	private List<Target> targets = new ArrayList<>();
	private List<Particle> particles = new ArrayList<>();
	private List<ParticleSpawner> spawners = new ArrayList<>();
	private List<FloatingText> floatingScores = new ArrayList<>();
	private List<Powerup> powerups = new ArrayList<>();
	private List<Projectile> projectiles = new ArrayList<>();
	
	private PixelSpatter pixelSpatter = new PixelSpatter();
	private PixelDrip pixelDrip = new PixelDrip();

	private int width = Game.WIDTH;
	private int height = Game.HEIGHT;

	private boolean levelUp = false;
	private int count = 0;

	public Playfield(Player player, char[] stagePattern, int stage) {
		this(player);

		this.stagePattern = stagePattern;
		this.stage = stage;
		generateTargets();
	}

	public Playfield(Player player, int stage) {
		this(player);

		this.stage = stage;

		stagePattern = new char[StagePattern.getStagePattern(stage).length];
		for (int i = 0; i < StagePattern.getStagePattern(stage).length; i++)
			stagePattern[i] = StagePattern.getStagePattern(stage)[i];

		generateTargets();
	}

	private Playfield(Player player) {
		this.player = player;
		this.player.init(this);
		addBall(new Ball(player.getx() + (player.getWidth() >> 1), player.gety() - player.getHeight()));
	}

	public void updateGS() {
		
		Jukebox.playSongWithIntro("playfieldintro", "playfieldbody");
		
		if (!levelUp) {
			player.update();
			
			if(forceField != null)
				forceField.update();
			
			for(int i = 0; i < balls.size(); i++)
				balls.get(i).update();
		} else
			if (count++ == 60 * 3) transition = true;

		for (int i = 0; i < targets.size(); i++)
			targets.get(i).update();

		for (int i = 0; i < particles.size(); i++)
			particles.get(i).update();

		for (int i = 0; i < spawners.size(); i++)
			spawners.get(i).update();
		
		for(int i = 0; i < floatingScores.size(); i++)
			floatingScores.get(i).update();
		
		for(int i = 0; i < powerups.size(); i++)
			powerups.get(i).update();
		
		for(int i = 0; i < projectiles.size(); i++)
			projectiles.get(i).update();

		checkMobCollision();
		removeTargets();
		removeParticles();
		removeSpawners();
		removeFloatingScores();
		removeBalls();
		removePowerups();
		removeProjectiles();
		removeForceField();

		if (balls.size() == 0) {
			if(player.isAlive())
				addSpawner(new ParticleSpawner(player.getx() + player.getWidth() / 2,
						player.gety() + player.getHeight() / 2, 100));
			player.setIsAlive(false);
		}
		
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

		for (FloatingText f : floatingScores)
			f.render(screen);
		
		for (Particle p : particles)
			p.render(screen);
		
		for (Ball b : balls)
			b.render(screen);
		
		for(Projectile p: projectiles)
			p.render(screen);
		
		for (Powerup p: powerups)
			p.render(screen);

		player.render(screen);
		
		if(forceField != null)
			forceField.render(screen);
	}

	public void renderTX(Screen screen) {
		renderScreenCap(screen);
	}

	public void addParticle(Particle p) {
		particles.add(p);
		p.init(this);
	}

	public void addSpawner(ParticleSpawner s) {
		spawners.add(s);
		s.init(this);
	}

	public void addTarget(Target t) {
		targets.add(t);
		t.init(this);
	}
	
	public void addFloatingScore(FloatingText f) {
		floatingScores.add(f);
		f.init(this);
	}
	
	public void addBall(Ball b) {
		balls.add(b);
		b.init(this);
	}
	
	public void addPowerup(Powerup p) {
		powerups.add(p);
		p.init(this);
	}
	
	public void addProjectile(Projectile p) {
		projectiles.add(p);
		p.init(this);
	}
	
	public void addForceField(ForceField f) {
		forceField = f;
		forceField.init(this);
	}

	private void generateTargets() {

		for (int y = 0; y < stagePattern.length / 10; y++) {
			for (int x = 0; x < 10; x++)
				if (stagePattern[x + y * 10] == '#') addTarget(new Target(16 * x + 1, 8 * (y + 2)));
		}
	}

	private void checkMobCollision() {
		
		for(Powerup p : powerups) {
			if (p.collidesWith(player))
				p.processCollision();
		}
		
		for(Ball b : balls) {
			
			if (b.collidesWith(player)) b.processCollision(player);
			
			for (int i = 0; i < targets.size(); i++) {
				if (b.collidesWith(targets.get(i))) {
					targets.get(i).processCollision(b);
					b.processCollision(targets.get(i));
				}
			}
			
			if(forceField != null)
				if(b.collidesWith(forceField)) {
					forceField.processCollision(b);
					b.processCollision(forceField);
				}
		}
		
		for(Projectile p : projectiles) {
			for(int i = 0; i < targets.size(); i++) {
				if(p.collidesWith(targets.get(i))) {
					targets.get(i).processCollision(p);
					p.processCollision();
				}
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
	
	private void removeBalls() {
		for(int i = 0; i < balls.size(); i++)
			if(balls.get(i).isRemoved()) balls.remove(i);
		
	}
	
	private void removePowerups() {
		for(int i = 0; i < powerups.size(); i++)
			if(powerups.get(i).isRemoved()) powerups.remove(i);
	}
	
	private void removeProjectiles() {
		for(int i = 0; i < projectiles.size(); i++)
			if(projectiles.get(i).isRemoved()) projectiles.remove(i);
	}
	
	private void removeForceField() {
		if(forceField != null)
			if(forceField.isRemoved())
				forceField = null;
	}

	public List<Ball> getBalls() {
		return balls;
	}

	public Player getPlayer() {
		return player;
	}

	public int getStage() {
		return stage;
	}
	
	public void setStagePattern(int index, char val) {
		stagePattern[index] = val;
	}
	
	protected void loadNextGameState(){
		if(nextGameState != null)
			return;
		
		if (player.getLives() > 0) {
			if(player.isAlive()) {
				if(++stage > 29)
					stage = 0;
				nextGameState = new Playfield(player.setCoordinates(width / 2, height - 8), stage);
			} else {
				player.setIsAlive(true);
				nextGameState = new Playfield(player.setCoordinates(width / 2, height - 8),
						stagePattern, stage);
			}
		} else
			nextGameState = new GameOver(player.getKey(), player.getRank());
	}
}