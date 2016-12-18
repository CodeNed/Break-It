package com.noah.breakit.sound.music;

import kuusisto.tinysound.Music;
import kuusisto.tinysound.TinySound;

 class Song {
	// music credit to sketchylogic
	static final Song titlesong = new Song(TinySound.loadMusic("songs/titlesong.wav"));
	static final Song playfieldintro = new Song(TinySound.loadMusic("songs/playfieldsongintro.wav"));
	static final Song playfieldbody = new Song(TinySound.loadMusic("songs/playfieldsongbody.wav"));
	static final Song briefingsong = new Song(TinySound.loadMusic("songs/briefingsong.wav"));
	static final Song gameoversong = new Song(TinySound.loadMusic("songs/gameoversong.wav"));
	static final Song voidsong = new Song(TinySound.loadMusic("voidsound.mp3"));
	
	private Music song;
	private double volume;
	final double DEFAULT_VOLUME = 1.0;
		
	 private Song(Music song) {
		this.song = song;
		volume  = DEFAULT_VOLUME;
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