package com.nali.small.mixin;

import net.minecraft.entity.item.EntityXPOrb;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(EntityXPOrb.class)
public interface IMixinEntityXPOrb
{
	@Invoker("roundAverage")
	static int GOroundAverage(float value)
	{
		return 0;
	}
}
