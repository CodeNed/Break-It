package com.noah.breakit.sound;

import kuusisto.tinysound.Music;
import kuusisto.tinysound.TinySound;

public class Song {
	// music credit to sketchylogic
	public static final Song titlesong = new Song(TinySound.loadMusic("songs/titlesong.wav"));
	public static final Song playfieldintro = new Song(TinySound.loadMusic("songs/playfieldsongintro.wav"));
	public static final Song playfieldbody = new Song(TinySound.loadMusic("songs/playfieldsongbody.wav"));
	public static final Song briefingsong = new Song(TinySound.loadMusic("songs/briefingsong.wav"));
	public static final Song gameoversong = new Song(TinySound.loadMusic("songs/gameoversong.wav"));	
	
	private Music song;
	private double volume;
	public final double DEFAULT_VOLUME;
		
	public Song(Music song) {
		this.song = song;
			
		DEFAULT_VOLUME = song.getVolume();
		volume  = DEFAULT_VOLUME;
	}
		
	public void play(boolean loop) {
		song.play(loop);
	}
		
	public void stop() {
		song.stop();
	}
	
	public void rewind() {
		song.rewind();
	}
		
	public double getVolume() {
		return volume;
	}
		
	public void adjustVolume(double val) {
		volume += val;
		song.setVolume(volume);
	}
		
	public void resetVolume() {
		volume = DEFAULT_VOLUME;
		adjustVolume(-0.0001); // this was necessary because after fading to black then 
		// setting volume back to DEFAULT_VOLUME, songs were playing at 0 volume, only 
		// to be heard again after fading to black (i.e. adjusting the volume)
	}
		
	public boolean playing() {
		return song.playing();
	}
	
	public boolean done() {
		return song.done();
	}
	
	public boolean fadeToBlack() {
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