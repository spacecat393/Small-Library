package com.nali.list.block;

import com.nali.draw.DrawScreen;
import com.nali.list.render.StorageRender;
import com.nali.render.ObjectRender;
import com.nali.small.SmallTab;
import com.nali.small.block.IMixBlock;
import com.nali.small.item.MixBlockItem;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class SmallStorage extends Block implements IMixBlock, ITileEntityProvider
{
    public static int ID;

    @SideOnly(Side.CLIENT)
    public StorageRender storagerender;
    @SideOnly(Side.CLIENT)
    public static DrawScreen DRAWSCREEN;

    public SmallStorage(String[] string_array)
    {
        super(Material.ROCK);
        this.init(this, string_array[0], string_array[1], SmallTab.TAB);
        if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
        {
            this.renderInit();
        }
        this.setResistance(2000.0F);
        this.setHardness(50.0F);
        this.setSoundType(SoundType.STONE);
        this.setLightLevel(0.1F);
    }

    @SideOnly(Side.CLIENT)
    public void renderInit()
    {
        this.storagerender = new StorageRender();
        DRAWSCREEN = new DrawScreen();
        DRAWSCREEN.scale(0.25F);
        DRAWSCREEN.z = 0.0F;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new com.nali.list.block.tile.SmallStorage();
    }

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
        return this.storagerender;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public DrawScreen getDrawScreen()
    {
        return DRAWSCREEN;
    }

    @Override
    public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face)
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
}
