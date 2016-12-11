package com.noah.breakit.sound;

import kuusisto.tinysound.TinySound;

public class Jukebox {
	
	// music credit to sketchylogic
		public static final Song titlesong = new Song(TinySound.loadMusic("songs/titlesong.wav"));
		public static final Song playfieldsong = new Song(TinySound.loadMusic("songs/playfieldsongbody.wav"));
		public static final Song briefingsong = new Song(TinySound.loadMusic("songs/briefingsong.wav"));
		public static final Song gameoversong = new Song(TinySound.loadMusic("songs/gameoversong.wav"));
		
		public static boolean fadeToBlack(Song song) {
			double val = -0.009D;
			
			song.adjustVolume(val);
			if(song.getVolume() <= 0) {
				song.stop();
				song.resetVolume();
				return true;
			}
			return false;
		}
		
		

}
