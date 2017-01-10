package com.noah.breakit.gamestate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.noah.breakit.entity.mob.ball.Ball;
import com.noah.breakit.entity.mob.brick.Brick;
import com.noah.breakit.entity.mob.brick.BrickDestructibleState;
import com.noah.breakit.entity.mob.brick.BrickPortalState;
import com.noah.breakit.entity.mob.brick.BrickSolidState;
import com.noah.breakit.entity.mob.decoration.Decoration;
import com.noah.breakit.entity.mob.forcefield.ForceField;
import com.noah.breakit.entity.mob.player.Player;
import com.noah.breakit.entity.mob.player.StatePrimaryPlayerAlive;
import com.noah.breakit.entity.mob.player.StatePrimaryPlayerDead;
import com.noah.breakit.entity.mob.powerup.Powerup;
import com.noah.breakit.entity.mob.projectile.Projectile;
import com.noah.breakit.entity.spawner.ParticleSpawner;
import com.noah.breakit.gamestate.menu.InitialsMenu;
import com.noah.breakit.gamestate.outro.Outro;
import com.noah.breakit.gamestate.outro.PixelDrip;
import com.noah.breakit.gamestate.outro.PixelSpatter;
import com.noah.breakit.graphics.Screen;
import com.noah.breakit.hiscore.HiScore;
import com.noah.breakit.sound.music.Jukebox;
import com.noah.breakit.stagepatterns.StagePattern;
import com.noah.breakit.util.Config;
import com.noah.breakit.util.FuzzRenderer;
import com.noah.breakit.util.Util;

public class Playfield extends BreakitGameState {

	private int stage = 0;
	private char[] stagePattern = null;

	private Player player = null;
	private ForceField forceField = null;

	private Powerup powerupSpawner = new Powerup(0, 0, null);

	private boolean levelUp = false;

	private int count = 0;

	private List<Ball> balls = new ArrayList<>();
	private List<Brick> bricks = new ArrayList<>();
	private List<Decoration> decorations = new ArrayList<>();
	private List<ParticleSpawner> spawners = new ArrayList<>();
	private List<Powerup> powerups = new ArrayList<>();
	private List<Projectile> projectiles = new ArrayList<>();

	public Playfield(Player player, char[] stagePattern, int stage, String currSong) {
		this(player, stage, currSong);
		this.stagePattern = stagePattern;
		generateBricks();
	}

	public Playfield(Player player, int stage, ForceField forceField, String currSong) {
		this(player, stage, currSong);
		this.forceField = forceField;
		stagePattern = new char[StagePattern.getStagePattern(stage).length];
		for (int i = 0; i < StagePattern.getStagePattern(stage).length; i++)
			stagePattern[i] = StagePattern.getStagePattern(stage)[i];
		generateBricks();
	}

	private Playfield(Player player, int stage, String currSong) {
		this.player = player;
		this.player.init(this);
		this.stage = stage;
		this.currSong = currSong;

		addBall(new Ball(player.getx() + (player.getWidth() / 2), player.gety() - player.getHeight()));
	}

	public Playfield init() {
		powerupSpawner.init(this);
		return this;
	}

	public void update() {
		Jukebox.play(currSong, true);

		for (int i = 0; i < spawners.size(); i++)
			spawners.get(i).update();
		
		for (int i = 0; i < decorations.size(); i++)
			decorations.get(i).update();

		removeSpawners();
		removeDecorations();
		
		if (levelUp) {
			if (count++ == 60 * 3)
				loadNextGameState();
			return;
		}
		
		if(player.getState() instanceof StatePrimaryPlayerDead) {
			if (count++ == 60 * 2)
				loadNextGameState();
			return;
		}

		player.update();

		if (forceField != null) forceField.update();

		for (int i = 0; i < balls.size(); i++)
			balls.get(i).update();

		int numBricks = 0;
		for (int i = 0; i < bricks.size(); i++) {
			bricks.get(i).update();
			if (bricks.get(i).getState() instanceof BrickDestructibleState) numBricks++;
		}
		if (numBricks == 0) levelUp = true;

		for (int i = 0; i < powerups.size(); i++)
			powerups.get(i).update();

		for (int i = 0; i < projectiles.size(); i++)
			projectiles.get(i).update();

		checkMobCollision();
		removeTargets();
		removeBalls();
		removePowerups();
		removeProjectiles();
		removeForceField();

		if (balls.size() == 0) {
			if (!(player.getState() instanceof StatePrimaryPlayerDead)) {
				player.setState(new StatePrimaryPlayerDead().init(player));
				addSpawner(new ParticleSpawner(player.getx() + player.getWidth() / 2,
						player.gety() + player.getHeight() / 2, 100));
			}
		}
	}

	public void render(Screen screen) {
		FuzzRenderer.render(screen, 32);

		for (Brick b : bricks)
			b.render(screen);

		for (Decoration d : decorations)
			d.render(screen);

		for (Ball b : balls)
			b.render(screen);

		for (Projectile p : projectiles)
			p.render(screen);

		for (Powerup p : powerups)
			p.render(screen);

		player.render(screen);

		if (forceField != null) forceField.render(screen);
	}

	public void addDecoration(Decoration d) {
		decorations.add(d);
		d.init(this);
	}

	public void addSpawner(ParticleSpawner s) {
		spawners.add(s);
		s.init(this);
	}

	public void addBrick(Brick b) {
		bricks.add(b);
		b.init(this);
	}

	public void addBall(Ball b) {
		balls.add(b);
		b.init(this);
	}

	public void addPowerup(int x, int y) {
		Powerup p = powerupSpawner.spawn(x, y, Util.random.nextInt(Powerup.NUM_TYPES));
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

	private void removeTargets() {
		for (int i = 0; i < bricks.size(); i++) {
			if (bricks.get(i).isRemoved()) bricks.remove(i);
		}
	}

	private void removeDecorations() {
		for (int i = 0; i < decorations.size(); i++)
			if (decorations.get(i).isRemoved()) decorations.remove(i);
	}

	private void removeSpawners() {
		for (int i = 0; i < spawners.size(); i++)
			if (spawners.get(i).isRemoved()) spawners.remove(i);
	}

	private void removeBalls() {
		for (int i = 0; i < balls.size(); i++)
			if (balls.get(i).isRemoved()) balls.remove(i);

	}

	private void removePowerups() {
		for (int i = 0; i < powerups.size(); i++)
			if (powerups.get(i).isRemoved()) powerups.remove(i);
	}

	private void removeProjectiles() {
		for (int i = 0; i < projectiles.size(); i++)
			if (projectiles.get(i).isRemoved()) projectiles.remove(i);
	}

	private void removeForceField() {
		if (forceField != null) if (forceField.isRemoved()) forceField = null;
	}

	private void checkMobCollision() {

		for (Powerup p : powerups) {
			if (p.collidesWith(this.player)) p.processCollision();
		}

		for (Ball b : balls) {
			if (b.collidesWith(player)) b.processCollision(player);
			if (forceField != null) if (b.collidesWith(forceField)) {
				forceField.processCollision(b);
				b.processCollision(forceField);
			}
			for (Brick r : bricks) {
				if (b.collidesWith(r)) {
					r.processCollision(b);
					b.processCollision(r);
				}
			}
		}

		for (Brick b : bricks) {
			for (Projectile p : projectiles) {
				if (b.collidesWith(p)) {
					b.processCollision(p);
					p.processCollision();
				}
			}
		}
	}

	private void generateBricks() {

		for (int y = 0; y < stagePattern.length / 10; y++) {
			for (int x = 0; x < 10; x++) {
				if (stagePattern[x + y * 10] == '#')
					addBrick(new Brick(16 * x + 1, 8 * (y + 2), new BrickDestructibleState()));
				else if (stagePattern[x + y * 10] == '@')
					addBrick(new Brick(16 * x + 1, 8 * (y + 2), new BrickSolidState()));
				else if (stagePattern[x + y * 10] == '*')
					addBrick(new Brick(16 * x + 1, 8 * (y + 2), new BrickPortalState()));
			}

			for (int i = 0; i < bricks.size(); i++) {
				if (bricks.get(i).getState() instanceof BrickPortalState) {
					BrickPortalState p = (BrickPortalState) bricks.get(i).getState();
					if (p.getMate() == null) {
						for (int k = i + 1; k < bricks.size(); k++) {
							if (bricks.get(k).getState() instanceof BrickPortalState) {
								BrickPortalState pp = (BrickPortalState) bricks.get(k).getState();
								p.setMate(pp);
								pp.setMate(p);
							}
						}
					}
				}
			}
		}
	}

	public void setStagePattern(int index, char val) {
		stagePattern[index] = val;
	}

	public void loadNextGameState() {
		
		Outro o = null;

		System.out.println(player.getLives());
		if (player.getLives() > 1) {
			if (player.getState() instanceof StatePrimaryPlayerAlive) {
				if (++stage > 29) stage = 0;

				Playfield p = new Playfield(player.setCoordinates(Config.WINDOW_WIDTH / 2, Config.WINDOW_HEIGHT - 16), stage, forceField,
						Jukebox.playfieldlist.get(Jukebox.getNextPlayfieldSong())).init();
				o = new PixelSpatter(0xff00ff, p);
			} else {
				player.addToLives(-1);
				player.setState(new StatePrimaryPlayerAlive());
				Playfield p = new Playfield(player.setCoordinates(Config.WINDOW_WIDTH / 2, Config.WINDOW_HEIGHT - 16), stagePattern,
						stage, currSong).init();
				o = new PixelDrip(0x00ffff, p);
			}
		} else {
			updateHiScores();
			if (getPlayerRank() != -1)
				o = new PixelDrip(0xff00ff, new InitialsMenu(player.getKey(), getPlayerRank()));
			else
				o = new PixelDrip(0xff00ff, new GameOver(player.getKey(), getPlayerRank()));
		}
		o.captureScreen();
		ngs = o;
		finished = true;
	}

	private void updateHiScores() {
		Config.HI_SCORES.add(new HiScore("", player.getScore()));
		Collections.sort(Config.HI_SCORES, Collections.reverseOrder());
		Config.HI_SCORES.remove(Config.HI_SCORES.size() - 1);
	}

	public Player getPlayer() {
		return player;
	}

	public ForceField getForceField() {
		return forceField;
	}

	public List<Ball> getBalls() {
		return balls;
	}

	public List<Brick> getBricks() {
		return bricks;
	}

	public List<Decoration> getDecorations() {
		return decorations;
	}

	public List<ParticleSpawner> getSpawners() {
		return spawners;
	}

	public List<Powerup> getPowerups() {
		return powerups;
	}

	public List<Projectile> getProjectiles() {
		return projectiles;
	}

	public int getPlayerRank() {
		int rank = -1;
		int count = 0;
		for (int i = 0; i < Config.HI_SCORES.size(); i++) {
			if (player.getScore() >= Config.HI_SCORES.get(i).getScore()) rank = Config.HI_SCORES.size() - ++count;
		}
		return rank;
	}

	public int getStage() {
		return stage;
	}

	public String getCurrSong() {
		return currSong;
	}
}