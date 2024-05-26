package com.nali.list.blocks;

import com.nali.draw.DrawScreen;
import com.nali.list.items.SmallBox;
import com.nali.render.ObjectRender;
import com.nali.small.blocks.IMixBlocks;
import com.nali.small.items.MixBlockItem;
import com.nali.small.items.Tabs;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class SmallTPBase extends Block implements IMixBlocks, ITileEntityProvider
{
    public static int ID;

    public SmallTPBase(String[] string_array)
    {
        super(Material.ROCK);
        this.init(this, string_array[0], string_array[1], Tabs.TABS);
    }

//    @Override
//    public Item getNewItem(Block block)
    public Item getNewItem()
    {
        return new MixBlockItem(this);
    }

    @Override
    public ObjectRender getObjectRender()
    {
        return SmallBox.OBJECTRENDER;
    }

    @Override
    public DrawScreen getDrawScreen()
    {
        return SmallBox.DRAWSCREEN;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new com.nali.list.tiles.SmallTPBase();
    }
}
