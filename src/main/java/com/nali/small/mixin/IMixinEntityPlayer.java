package com.nali.small.mixin;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(EntityPlayer.class)
public interface IMixinEntityPlayer
{
    @Accessor("spawnPos")
    BlockPos spawnPos();
}
