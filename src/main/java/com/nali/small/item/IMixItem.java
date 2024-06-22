package com.nali.small.item;

import com.nali.small.block.memo.client.ClientB;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public interface IMixItem extends ClientB
{
    default void init(Item item, String name, String mod_id, CreativeTabs creativetabs)
    {
        item.setRegistryName(mod_id, name);
        item.setCreativeTab(creativetabs);
        item.setTranslationKey(mod_id + '.' + name);
    }
}
