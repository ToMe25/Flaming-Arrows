package com.tome.flamingarrows;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class FlamingArrowItem extends ArrowItem {

	public FlamingArrowItem(ItemGroup group, String registryName) {
		super(new Properties().group(group));
		setRegistryName(FlamingArrows.MODID, registryName);
	}

	@Override
	public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {
		FlamingArrowEntity arrow = new FlamingArrowEntity(worldIn, shooter);
		arrow.setFire(100);
		return arrow;
	}

}
