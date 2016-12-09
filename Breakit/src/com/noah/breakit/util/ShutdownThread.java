package com.noah.breakit.util;

import kuusisto.tinysound.TinySound;

public class ShutdownThread extends Thread{

	public void run() {
		System.out.println("Shutting down TinySound...");
		TinySound.shutdown();
		System.out.println("TinySound shutdown complete!");
	}
}
