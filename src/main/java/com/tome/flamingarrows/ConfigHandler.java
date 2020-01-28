package com.tome.flamingarrows;

import java.io.File;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLPaths;

public class ConfigHandler {

	private static CommentedFileConfig cfg;
	private static final ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
	private static final String CATEGORY_GENERAL = "general";

	public static BooleanValue removeFlame;
	public static BooleanValue addFlameParticles;

	public ConfigHandler() {
		cfg = CommentedFileConfig
				.builder(new File(FMLPaths.CONFIGDIR.get().toString(), FlamingArrows.MODID + "-common.toml")).sync()
				.autosave().build();
		cfg.load();
		initConfig();
		ForgeConfigSpec spec = builder.build();
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, spec, cfg.getFile().getName());
		spec.setConfig(cfg);
	}

	private void initConfig() {
		builder.comment("The general configuration for this mod").push(CATEGORY_GENERAL).pop();
		removeFlame = getBoolean("removeFlame", CATEGORY_GENERAL, true,
				"If this is set to true the flame enchantment will be disabled by setting its max level to 0.");
		addFlameParticles = getBoolean("addFlameParticles", CATEGORY_GENERAL, true,
				"If this is set to true Flaming Arrows will spawn a few flame particles while flying."
						+ "(a similiar amount to the particles spawned by Spectral Arrows)");
	}

	public static BooleanValue getBoolean(String name, String category, boolean defaultValue, String comment) {
		String path = category + "." + name;
		return builder.comment(comment, "Default: " + defaultValue).define(path, defaultValue);
	}

}
