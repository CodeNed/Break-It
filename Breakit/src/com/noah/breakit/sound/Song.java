package com.noah.breakit.sound;

import kuusisto.tinysound.Music;

public class Song {
	
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
}