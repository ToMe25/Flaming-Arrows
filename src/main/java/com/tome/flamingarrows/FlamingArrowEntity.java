package com.tome.flamingarrows;

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
		super(FlamingArrows.FlamingArrowEntityType, message.getPosX(), message.getPosY(), message.getPosZ(), world);
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
		if (this.world.isRemote && !this.inGround && this.isBurning()) {
			this.world.addParticle(ParticleTypes.FLAME, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
		}

	}

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

}
