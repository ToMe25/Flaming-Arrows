package com.tome.flamingarrows;

import java.lang.reflect.Field;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

public class FlamingArrowEntity extends AbstractArrowEntity {

	public FlamingArrowEntity(EntityType<? extends FlamingArrowEntity> type, World world) {
		super(type, world);
	}

	public FlamingArrowEntity(World worldIn, LivingEntity shooter) {
		super(FlamingArrows.FlamingArrowEntityType, shooter, worldIn);
	}

	public FlamingArrowEntity(World worldIn, double x, double y, double z) {
		super(FlamingArrows.FlamingArrowEntityType, x, y, z, worldIn);
	}

	public FlamingArrowEntity(FMLPlayMessages.SpawnEntity message, World world) {
		super(FlamingArrows.FlamingArrowEntityType, getX(message), getY(message), getZ(message), world);
	}

	@Override
	protected ItemStack getArrowStack() {
		return new ItemStack(FlamingArrows.FlamingArrow);
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	public void tick() {
		super.tick();
		if (this.world.isRemote && !this.inGround && this.isBurning() && ConfigHandler.addFlameParticles.get()) {
			this.world.addParticle(ParticleTypes.FLAME, this.getPosX(), this.getPosY(), this.getPosZ(), 0.0D, 0.0D,
					0.0D);
		}

	}

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	/**
	 * This method is needed for 1.14.2 support, but only because the AT isn't
	 * working.
	 * 
	 * @param msg the message to get the x coordinate from
	 * @return
	 */
	private static double getX(FMLPlayMessages.SpawnEntity msg) {
		try {
			Field posX = FMLPlayMessages.SpawnEntity.class.getDeclaredField("posX");
			posX.setAccessible(true);
			return posX.getDouble(msg);
		} catch (Exception e) {
			e.printStackTrace();
			return 0.0;
		}
	}

	/**
	 * This method is needed for 1.14.2 support, but only because the AT isn't
	 * working.
	 * 
	 * @param msg the message to get the y coordinate from
	 * @return
	 */
	private static double getY(FMLPlayMessages.SpawnEntity msg) {
		try {
			Field posY = FMLPlayMessages.SpawnEntity.class.getDeclaredField("posY");
			posY.setAccessible(true);
			return posY.getDouble(msg);
		} catch (Exception e) {
			e.printStackTrace();
			return 0.0;
		}
	}

	/**
	 * This method is needed for 1.14.2 support, but only because the AT isn't
	 * working.
	 * 
	 * @param msg the message to get the z coordinate from
	 * @return
	 */
	private static double getZ(FMLPlayMessages.SpawnEntity msg) {
		try {
			Field posZ = FMLPlayMessages.SpawnEntity.class.getDeclaredField("posZ");
			posZ.setAccessible(true);
			return posZ.getDouble(msg);
		} catch (Exception e) {
			e.printStackTrace();
			return 0.0;
		}
	}

}
