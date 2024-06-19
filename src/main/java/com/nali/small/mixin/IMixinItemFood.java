package com.nali.small.mixin;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ItemFood.class)
public interface IMixinItemFood
{
    @Invoker("onFoodEaten")
    void GOonFoodEaten(ItemStack stack, World worldIn, EntityPlayer player);
}
