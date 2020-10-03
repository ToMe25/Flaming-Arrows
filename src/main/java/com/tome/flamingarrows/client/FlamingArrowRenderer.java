package com.tome.flamingarrows.client;

import com.tome.flamingarrows.FlamingArrowEntity;
import com.tome.flamingarrows.FlamingArrows;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FlamingArrowRenderer<T extends FlamingArrowEntity> extends ArrowRenderer<T> {

	public static final ResourceLocation RES_FLAMING_ARROW = new ResourceLocation(FlamingArrows.MODID, "textures/entity/projectiles/flaming_arrow.png");

	public FlamingArrowRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn);
	}

	@Override
	public ResourceLocation getEntityTexture(FlamingArrowEntity entity) {
		return RES_FLAMING_ARROW;
	}

}
