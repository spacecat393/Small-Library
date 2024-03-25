package com.nali.small.mixin;

import net.minecraft.entity.monster.EntityCreeper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(EntityCreeper.class)
public interface IMixinEntityCreeper
{
    @Accessor("timeSinceIgnited")
    @Mutable
    void timeSinceIgnited(int i);
}
