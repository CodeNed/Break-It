package com.noah.breakit.sound.music;

import kuusisto.tinysound.Music;
import kuusisto.tinysound.TinySound;

 class Song {
	// music credit to sketchylogic
	 static final Song BRIEFING_SONG = new Song(TinySound.loadMusic("songs/briefingsong.wav"));
	static final Song FRANTIC_SONG = new Song(TinySound.loadMusic("songs/franticsong.wav"), 1.615);
	static final Song GAME_OVER_SONG = new Song(TinySound.loadMusic("songs/gameoversong.wav"));
	static final Song HAPPY_SONG = new Song(TinySound.loadMusic("songs/happysong.wav"));
	static final Song METAL_SONG = new Song(TinySound.loadMusic("songs/metalsong.wav"), 8.05);
	static final Song SWINGING_SONG = new Song(TinySound.loadMusic("songs/swingingsong.wav"));
	static final Song TITLE_SONG = new Song(TinySound.loadMusic("songs/titlesong.wav"));
	static final Song VOID_SONG = new Song(TinySound.loadMusic("voidsound.mp3"));
	
	private Music song = null;
	private double volume = 0;
	static final double DEFAULT_VOLUME = 1.0;
		
	 private Song(Music song) {
		this.song = song;
		volume  = DEFAULT_VOLUME;
	}
	 
	private Song(Music song, double loopPos) {
		this(song);
		song.setLoopPositionBySeconds(loopPos);
	}
		
	void play(boolean loop) {
		song.play(loop);
	}
	
	void pause() {
		song.pause();
	}
		
	void stop() {
		song.stop();
	}
	
	void rewind() {
		song.rewind();
	}
		
	double getVolume() {
		return volume;
	}
	
	void setVolume(double val) {
		volume = val;
		song.setVolume(volume);
	}
		
	void adjustVolume(double val) {
		volume += val;
		song.setVolume(volume);
	}
		
	void resetVolume() {
		volume = DEFAULT_VOLUME;
		adjustVolume(-0.0001); // this was necessary because after fading to black then 
		// setting volume back to DEFAULT_VOLUME, songs were playing at 0 volume, only 
		// to be heard again after fading to black (i.e. adjusting the volume)
	}
		
	boolean playing() {
		return song.playing();
	}
	
	boolean done() {
		return song.done();
	}
	
	boolean fadeToBlack() {
		double val = -0.009D;
		
		adjustVolume(val);
		if(getVolume() <= 0) {
			stop();
			resetVolume();
			return true;
		}
		return false;
	}
}