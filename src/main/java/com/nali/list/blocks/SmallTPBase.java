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
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class SmallTPBase extends Block implements IMixBlocks, ITileEntityProvider
{
    public static int ID;
    public static final AxisAlignedBB AXISALIGNEDBB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D);

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
            OpenGLAnimationMemory openglanimationmemory = (OpenGLAnimationMemory)ClientLoader.OBJECT_LIST.get(((SkinningClientData)this.tpbaserender.clientdata).AnimationID());
            this.tpbaserender.initSkinning(openglanimationmemory);
            this.tpbaserender.setSkinning(openglanimationmemory);
            DRAWSCREEN = new DrawScreen();
            DRAWSCREEN.scale(0.25F);
            DRAWSCREEN.z = 0.0F;
        }
        this.fullBlock = false;
        this.setResistance(2000.0F);
        this.setHardness(50.0F);
        this.setSoundType(SoundType.STONE);
        this.setLightLevel(1.0F);
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

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return AXISALIGNEDBB;
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }

    @Override
    public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
}
