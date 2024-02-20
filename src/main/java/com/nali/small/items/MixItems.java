package com.nali.small.items;

import com.nali.render.ObjectRender;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface MixItems
{
    @SideOnly(Side.CLIENT)
    ObjectRender getObjectRender();

    default void init(Item item, String name, String mod_id, CreativeTabs creativetabs)
    {
        item.setRegistryName(mod_id, name);
        item.setCreativeTab(creativetabs);
        item.setTranslationKey(name);
    }

    @SideOnly(Side.CLIENT)
    default void render()
    {
        this.getObjectRender().objectscreendraw.renderScreen();
    }
}
