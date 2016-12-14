package com.noah.breakit.sound.music;

import java.util.HashMap;

public class Jukebox {
	
	private static HashMap<String, Song> playList = new HashMap<String, Song>();
	
	public static Jukebox songList = new Jukebox();
	
	private static Song currSong = null;
	private static String currSongName = null;
	
	private Jukebox() {
		playList.put("titlesong", Song.titlesong);
		playList.put("playfieldintro", Song.playfieldintro);
		playList.put("playfieldbody", Song.playfieldbody);
		playList.put("briefingsong", Song.briefingsong);
		playList.put("gameoversong", Song.gameoversong);
	}
	
	public static void play(String key, boolean loop) {
		setCurrSong(key);
		play(loop);
	}
	
	public static void play(boolean loop) {
		if(currSong == null)
			System.err.println("Error: from Jukebox.play(loop): Song not loaded!");
		else 
			currSong.play(loop);
	}
	
	public static void stop() {
		if(currSong == null)
			System.err.println("Error: from Jukebox.stop(): Song not loaded!");
		else	
			currSong.stop();
	}
	
	public static void rewind() {
		if(currSong == null)
			System.err.println("Error: from Jukebox.rewind(): Song not loaded!");
		else
			currSong.rewind();
	}
	
	public static boolean playing() {
		if(currSong == null)
			return false;
		return currSong.playing();
	}
	
	public static boolean done() {
		if(currSong == null) {
			System.err.println("Error: from Jukebox.done(): Song not loaded!");
			return false;
		}
		return currSong.done();
	}
	
	public static boolean fadeToBlack() {
		if(currSong == null)
			return true;
		return currSong.fadeToBlack();
	}
	
	public static String getSongName() {
		return currSongName;
	}
	
	public static void setCurrSong(String key) {
		currSongName = key;
		currSong = playList.get(key);
	}
	
	public static boolean songNameIs(String name) {
		if(name == null)
			return false;
		if(name.equals(currSongName))
			return true;
		return false;
	}
}