package com.nali.small.block.memo;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

public interface IBothB
{
    default void init(Block block, String name, String mod_id, CreativeTabs creativetabs)
    {
        block.setRegistryName(mod_id, name);
        block.setCreativeTab(creativetabs);
        block.setTranslationKey(mod_id + '.' + name);
    }
}
