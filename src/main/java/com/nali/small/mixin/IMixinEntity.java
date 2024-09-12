package com.nali.small.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Entity.class)
public interface IMixinEntity
{
	@Invoker("markVelocityChanged")
	void GOmarkVelocityChanged();
	@Invoker("applyEnchantments")
	void GOapplyEnchantments(EntityLivingBase entityLivingBaseIn, Entity entityIn);
}
