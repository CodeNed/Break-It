package com.noah.breakit.hiscore;

public class HiScore implements Comparable<HiScore> {
	
	private String initials;
	private int score = 0;
	
	public HiScore(String initials, int score) {
		this.initials = initials;		
		if(this.initials == null)
			this.initials = "aaa";		
		this.score = score;
	}
	
	public String getInitials() {
		return initials;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setInitials(String initials) {
		this.initials = initials;
	}
	
	public void setScore(int score) {
		this.score = score;
	}

	public int compareTo(HiScore s) {
		if(score > s.getScore())
			return 1;
		if(score < s.getScore())
			return -1;
		return 0;
	}
}