package com.tome.flamingarrows;

import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.IPosition;
import net.minecraft.dispenser.ProjectileDispenseBehavior;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;

@Mod(FlamingArrows.MODID)
@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = FlamingArrows.MODID)
public class FlamingArrows {

	public static final String MODID = "flamingarrows";

	public static ConfigHandler config;

	public static Item FlamingArrow = new FlamingArrowItem(ItemGroup.COMBAT, "flaming_arrow");
	public static EntityType<FlamingArrowEntity> FlamingArrowEntityType = EntityType.Builder
			.<FlamingArrowEntity>create(FlamingArrowEntity::new, EntityClassification.MISC).size(0.5f, 0.5f)
			.setCustomClientFactory(FlamingArrowEntity::new).build("flaming_arrow");

	public FlamingArrows() {
		config = new ConfigHandler();
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
	}

	public void commonSetup(FMLCommonSetupEvent e) {
		DispenserBlock.registerDispenseBehavior(FlamingArrow, new ProjectileDispenseBehavior() {

			@Override
			protected ProjectileEntity getProjectileEntity(World worldIn, IPosition position, ItemStack stackIn) {
				FlamingArrowEntity arrowentity = new FlamingArrowEntity(worldIn, position.getX(), position.getY(),
						position.getZ());
				arrowentity.setFire(100);
				arrowentity.pickupStatus = AbstractArrowEntity.PickupStatus.ALLOWED;
				return arrowentity;
			}
		});
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> e) {
		final IForgeRegistry<Item> registry = e.getRegistry();
		registry.register(FlamingArrow);
	}

	@SubscribeEvent
	public static void registerEntities(RegistryEvent.Register<EntityType<?>> e) {
		final IForgeRegistry<EntityType<?>> registry = e.getRegistry();
		registry.register(FlamingArrowEntityType.setRegistryName(new ResourceLocation(MODID, "flaming_arrow")));
	}

}
