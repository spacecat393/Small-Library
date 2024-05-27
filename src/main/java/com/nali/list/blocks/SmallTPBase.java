package com.nali.list.blocks;

import com.nali.data.client.SkinningClientData;
import com.nali.draw.DrawScreen;
import com.nali.list.render.TPBaseRender;
import com.nali.render.ObjectRender;
import com.nali.small.blocks.IMixBlocks;
import com.nali.small.items.MixBlockItem;
import com.nali.small.items.Tabs;
import com.nali.system.ClientLoader;
import com.nali.system.opengl.memory.OpenGLAnimationMemory;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class SmallTPBase extends Block implements IMixBlocks, ITileEntityProvider
{
    public static int ID;

    @SideOnly(Side.CLIENT)
    public TPBaseRender tpbaserender;
    @SideOnly(Side.CLIENT)
    public static DrawScreen DRAWSCREEN;

    public SmallTPBase(String[] string_array)
    {
        super(Material.ROCK);
        this.init(this, string_array[0], string_array[1], Tabs.TABS);
        if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
        {
            this.tpbaserender = new TPBaseRender(this);
            OpenGLAnimationMemory openglanimationmemory = (OpenGLAnimationMemory) ClientLoader.OBJECT_LIST.get(((SkinningClientData)this.tpbaserender.clientdata).AnimationID());
            this.tpbaserender.initSkinning(openglanimationmemory);
            this.tpbaserender.setSkinning(openglanimationmemory);
        }
    }

//    @Override
//    public Item getNewItem(Block block)
    public Item getNewItem()
    {
        return new MixBlockItem(this);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ObjectRender getObjectRender()
    {
//        if (this.tpbaserender == null)
//        {
//            this.tpbaserender = new TPBaseRender(this);
//        }
        return this.tpbaserender;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public DrawScreen getDrawScreen()
    {
        return DRAWSCREEN;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new com.nali.list.tiles.SmallTPBase();
    }
}
