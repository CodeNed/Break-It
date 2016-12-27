package com.noah.breakit.sound.music;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Jukebox {
	
	public static List<String> playfieldlist = new ArrayList<>();
	private static Map<String, Song> album = new HashMap<>();
	
	@SuppressWarnings("unused")
	private static final Jukebox JUKEBOX = new Jukebox();
	
	private static boolean standby = false;
	private static Song currSong = null;
	private static String currSongName = null;
	private static boolean playing = false;
	private static boolean looping = false;
	private static double volume = 1.0;
	
	public static final double DEFAULT_VOLUME = Song.DEFAULT_VOLUME;
	public static final double MENU_VOLUME = 0.2;
	
	private static int playfieldlistIndex = 0;
	
	private Jukebox() {
		album.put("briefingsong", Song.BRIEFING_SONG);
		album.put("franticsong", Song.FRANTIC_SONG);
		album.put("gameoversong", Song.GAME_OVER_SONG);
		album.put("happysong", Song.HAPPY_SONG);
		album.put("metalsong", Song.METAL_SONG);
		album.put("swingingsong", Song.SWINGING_SONG);
		album.put("titlesong", Song.TITLE_SONG);
		album.put("voidsong", Song.VOID_SONG);
		
		playfieldlist.add("franticsong");
		playfieldlist.add("metalsong");
		playfieldlist.add("swingingsong");
		playfieldlist.add("happysong");
	}
	
	public static void play(String key, boolean loop) {
		
		looping = loop;
		
		if(currSong != null) // If a song is already loaded and playing, return. Else, set Jukebox.playing to false.
			if(currSong.playing())
				return;
			else
				playing = false;
		
		setCurrSong(key);
		
		if(currSong == null) { // If no song is loaded, the hash map failed to return a value due to invalid key. 
							   //Print error message and return.
			System.err.println("Error: from Jukebox.play(loop): No song loaded. Check song name!");
			return;
		}
		
		if(!standby && !playing) { // If Jukebox is not in standby mode and is not playing a song, set song volume to 
								   //jukebox volume, rewind song, play song, and set Jukebox.playing to true.
			currSong.setVolume(volume); 
			currSong.rewind();
			currSong.play(looping);
			playing = true;
		}
	}
	
	public static void pause() {
		if(currSong == null) {
			System.err.println("Error: from Jukebox.pause(): No song loaded!");
			return;
		}
		currSong.pause();
	}
	
	public static void stop() {
		if(currSong == null) {
			System.err.println("Error: from Jukebox.stop(): No song loaded!");
			return;
		}
		currSong.stop();
		looping = false;
	}
	
	public static boolean done() {
		if(currSong == null) {
			System.err.println("Error: from Jukebox.done(): No song loaded!");
			return false;
		}
		return currSong.done();
	}
	
	public static void setVolume(double val) {
		if(currSong == null) {
			System.err.println("Error: from Jukebox.adjustVolume(): No song loaded!");
			return;
		}
		volume = val;
		currSong.setVolume(val);
	}
	
	public static boolean fadeToBlack() {
		if(currSong == null) {
			System.err.println("Error: from Jukebox.fadeToBlack(): No song loaded!");
			return true;
		}
		return currSong.fadeToBlack();
	}
	
	public static boolean currSongIs(String name) {
		if(name == null) {
			System.err.println("Error: from Jukebox.currSongIs(name): No song loaded!");
			return false;
		}
		if(name.equals(currSongName))
			return true;
		return false;
	}
	
	public static boolean isOnStandby() {
		return standby;
	}
	
	public static void setStandbyMode(boolean b) {
		standby = b;
	}
	
	private static void setCurrSong(String key) {
		currSongName = key;
		currSong = album.get(key);
	}
	
	public static int getNextPlayfieldSong() {
		if(++playfieldlistIndex > playfieldlist.size() - 1)
			playfieldlistIndex = 0;
		return playfieldlistIndex;
	}
}