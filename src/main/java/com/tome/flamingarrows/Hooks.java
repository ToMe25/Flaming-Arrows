package com.tome.flamingarrows;

public class Hooks {

	public static int getFlameMaxLevel() {
		if (ConfigHandler.removeFlame == null || ConfigHandler.removeFlame.get()) {
			return 0;
		} else {
			return 1;
		}
	}

}
