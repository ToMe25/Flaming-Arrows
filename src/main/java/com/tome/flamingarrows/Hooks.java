package com.tome.flamingarrows;

public class Hooks {

	public static int getFlameMaxLevel() {
		return ConfigHandler.removeFlame.get() ? 0 : 1;
	}

}
