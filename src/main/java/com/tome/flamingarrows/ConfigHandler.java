package com.tome.flamingarrows;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLPaths;

public class ConfigHandler {

	private static CommentedFileConfig cfg;
	private static final ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
	private static Map<String, ConfigValue<?>> values = new HashMap<String, ConfigValue<?>>();
	private static final String CATEGORY_GENERAL = "general";

	public static boolean removeFlame;

	public ConfigHandler() {
		cfg = CommentedFileConfig
				.builder(new File(FMLPaths.CONFIGDIR.get().toString(), FlamingArrows.MODID + "-common.toml")).sync()
				.autosave().build();
		cfg.load();
		initConfig();
		ForgeConfigSpec spec = builder.build();
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, spec, cfg.getFile().getName());
		spec.setConfig(cfg);
		loadConfig();
	}

	private void initConfig() {
		builder.comment("The general configuration for this mod").push(CATEGORY_GENERAL).pop();
		getBoolean("removeFlame", CATEGORY_GENERAL, true,
				"If this is set to true the flame enchantment will be disabled by setting its max level to 0.");
	}

	private void loadConfig() {
		removeFlame = getBoolean("removeFlame", CATEGORY_GENERAL);
	}

	public static void getBoolean(String name, String category, boolean defaultValue, String comment) {
		String path = category + "." + name;
		BooleanValue value = builder.comment(comment, "Default: " + defaultValue).define(path, defaultValue);
		values.put(path, value);
	}

	private static boolean getBoolean(String name, String category) {
		String path = category + "." + name;
		if (values.containsKey(path)) {
			ConfigValue<?> value = values.get(path);
			if (value instanceof BooleanValue) {
				return ((BooleanValue) value).get();
			}
		}
		return false;
	}

}
