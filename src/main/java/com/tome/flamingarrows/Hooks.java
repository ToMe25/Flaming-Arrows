package com.tome.flamingarrows;

public class Hooks {

	public static int getFlameMaxLevel() {
		return ConfigHandler.removeFlame ? 0 : 1;
	}

}
