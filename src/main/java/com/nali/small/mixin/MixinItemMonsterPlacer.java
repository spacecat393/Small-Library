package com.nali.small.mixin;

import com.nali.small.entity.IMixE;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemMonsterPlacer.class)
public abstract class MixinItemMonsterPlacer
{
	@Inject(method = "spawnCreature", at = @At(value = "RETURN"))
	private static void nali_small_spawnCreature(World worldIn, ResourceLocation entityID, double x, double y, double z, CallbackInfoReturnable<Entity> cir)
	{
		Entity entity = cir.getReturnValue();

		if (entity instanceof IMixE)
		{
			NBTTagCompound nbttagcompound = new NBTTagCompound();
			nbttagcompound = entity.writeToNBT(nbttagcompound);
			entity.readFromNBT(nbttagcompound);
			entity.setLocationAndAngles(x, y, z, MathHelper.wrapDegrees(worldIn.rand.nextFloat() * 360.0F), 0.0F);
			worldIn.spawnEntity(entity);
		}
	}
}
