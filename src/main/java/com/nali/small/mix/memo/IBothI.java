package com.nali.small.mix.memo;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public interface IBothI<E extends Item> extends IBothN
{
    default void init(E e, String name, String mod_id, CreativeTabs creativetabs)
    {
        e.setRegistryName(mod_id, name);
        e.setCreativeTab(creativetabs);
        e.setTranslationKey(mod_id + '.' + name);
    }
}
