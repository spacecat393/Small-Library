package com.nali.small.block;

import com.nali.small.render.IMixRender;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

public interface IMixBlock extends IMixRender
{
//    @SideOnly(Side.CLIENT)
//    ObjectRender getObjectRender();
//    @SideOnly(Side.CLIENT)
//    DrawScreen getDrawScreen();

    default void init(Block block, String name, String mod_id, CreativeTabs creativetabs)
    {
        block.setRegistryName(mod_id, name);
        block.setCreativeTab(creativetabs);
        block.setTranslationKey(mod_id + '.' + name);
    }

//    @SideOnly(Side.CLIENT)
//    default void render()
//    {
//        this.getDrawScreen().render(this.getObjectRender());
//    }

//    default Item getNewItem(Block block)
//    {
//        return new ItemBlock(block);
//    }
}
