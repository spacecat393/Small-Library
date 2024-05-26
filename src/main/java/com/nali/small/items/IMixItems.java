package com.nali.small.items;

import com.nali.small.render.IMixRender;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public interface IMixItems extends IMixRender
{
    default void init(Item item, String name, String mod_id, CreativeTabs creativetabs)
    {
        item.setRegistryName(mod_id, name);
        item.setCreativeTab(creativetabs);
        item.setTranslationKey(mod_id + '.' + name);
    }
}
