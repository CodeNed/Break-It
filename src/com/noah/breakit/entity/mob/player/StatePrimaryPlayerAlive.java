package com.noah.breakit.entity.mob.player;

import com.noah.breakit.entity.mob.Mob;
import com.noah.breakit.entity.mob.ball.Ball;
import com.noah.breakit.entity.state.State;
import com.noah.breakit.game.Game;
import com.noah.breakit.gamestate.PauseMenu;
import com.noah.breakit.graphics.Screen;
import com.noah.breakit.sound.SoundFX;
import com.noah.breakit.sound.music.Jukebox;
import com.noah.breakit.util.Hud;

public class StatePrimaryPlayerAlive implements State {
	
	protected Player p = null;
	
	public StatePrimaryPlayerAlive() {
	}
	
	public void init(Mob m) {
		p = (Player) m;
		p.setWidth(32);
		p.setCol(0x00ffff);
	}

	public void update() {
		                 
		p.key.update();

		p.setxdir(0);

		if (p.key.left && p.getx() > 0 + p.getxspeed()) p.setxdir(-1);

		if (p.key.right && p.getx() < Game.WIDTH - p.getWidth() - p.getxspeed()) p.setxdir(1);
		
		if((p.key.esc && !p.key.escLast) || (p.key.enter && !p.key.enterLast)) {
			Jukebox.setVolume(Jukebox.MENU_VOLUME);
			SoundFX.PAUSE.play();
			p.getPlayfield().captureScreen();
			PauseMenu pm = new PauseMenu(p.key, p.getPlayfield());
			pm.init();
			Game.GSM.push(pm);
		}
		
		if (p.key.space && !p.getPlayfield().getBalls().get(0).isReleased()) {
			Ball b = p.getPlayfield().getBalls().get(0);
			b.setReleased(true);
			b.setxdir(p.getxdir());
			b.setxspeed(p.getxspeed());
			b.setydir(-1);
			SoundFX.LAUNCH.play();
		}

		p.updatexa();
		p.movex();

		p.scoreStr = Hud.parseScore(p.score);
		if (p.score >= p.toNext1UP) {
			p.lives++;
			p.toNext1UP *= 2;
			SoundFX.ONE_UP.play();
		}
	}

	public void render(Screen s) {
		s.fillRect(p.getx(), p.gety(), p.getWidth(), p.getHeight(), p.getCol());

		Hud.renderScore(s, 4, 4, 0xffffff, p.scoreStr);
		Hud.renderLives(s, p.lives);
	}

	public void processCollision(Mob m) {
	}

	public Mob getMob() {
		return p;
	}
}