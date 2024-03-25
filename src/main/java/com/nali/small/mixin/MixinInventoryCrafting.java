package com.nali.small.mixin;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(InventoryCrafting.class)
public interface MixinInventoryCrafting
{
    @Accessor("eventHandler")
    @Mutable
    Container eventHandler();

    @Accessor("eventHandler")
    @Mutable
    void eventHandler(Container container);

    @Accessor("stackList")
    @Mutable
    NonNullList<ItemStack> stackList();
}
