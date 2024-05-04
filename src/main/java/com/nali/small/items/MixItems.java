package com.nali.small.items;

import com.nali.draw.DrawScreen;
import com.nali.render.ObjectRender;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface MixItems
{
    @SideOnly(Side.CLIENT)
    ObjectRender getObjectRender();
    @SideOnly(Side.CLIENT)
    DrawScreen getDrawScreen();

    default void init(Item item, String name, String mod_id, CreativeTabs creativetabs)
    {
        item.setRegistryName(mod_id, name);
        item.setCreativeTab(creativetabs);
        item.setTranslationKey(mod_id + '.' + name);
    }

    @SideOnly(Side.CLIENT)
    default void render()
    {
        this.getDrawScreen().render(this.getObjectRender());
    }
}
