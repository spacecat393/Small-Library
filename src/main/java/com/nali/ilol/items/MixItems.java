package com.nali.ilol.items;

import com.nali.render.ObjectRender;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MixItems extends Item
{
    @SideOnly(Side.CLIENT)
    public ObjectRender objectrender;

    public MixItems(String name, String mod_id, CreativeTabs creativetabs)
    {
        this.setRegistryName(mod_id, name);
        this.setCreativeTab(creativetabs);
        this.setTranslationKey(name);
    }

    @SideOnly(Side.CLIENT)
    public void render()
    {
        this.objectrender.objectscreendraw.renderScreen(1.0F, 1.0F, 1.0F, 1.0F);
    }
}
