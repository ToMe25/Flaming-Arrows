package com.tome.flamingarrows.client;

import com.tome.flamingarrows.FlamingArrows;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(value = Dist.CLIENT, modid = FlamingArrows.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModelHandler {

	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent e) {
		RenderingRegistry.registerEntityRenderingHandler(FlamingArrows.FlamingArrowEntityType,
				FlamingArrowRenderer::new);
	}

}
