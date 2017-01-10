package com.noah.breakit.util;

import java.util.ArrayList;
import java.util.List;

import com.noah.breakit.hiscore.HiScore;

public class Config {
	//constant literals (to eventually be put in XML)
		public static final int WINDOW_WIDTH = 160;
		public static final int WINDOW_HEIGHT = 260;
		public static final int SCALE = 3;
		public static final int NUM_HI_SCORES = 10;
		public static final int TO_FIRST_1UP = 20000;
		public static final int STARTING_LIVES = 3;
		
		public static final List<HiScore> HI_SCORES = new ArrayList<HiScore>() {
			
			private static final long serialVersionUID = 1L;

			{
				add(new HiScore("aaa", 50000));
				add(new HiScore("aaa", 30000));
				add(new HiScore("aaa", 20000));
				add(new HiScore("aaa", 15000));
				add(new HiScore("aaa", 12000));
				add(new HiScore("aaa", 10000));
				add(new HiScore("aaa", 9000));
				add(new HiScore("aaa", 8000));
				add(new HiScore("aaa", 7000));
				add(new HiScore("aaa", 6000));
			}
		};
		
		private Config() {
		}
}
