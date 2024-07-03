package com.nali.list.block;

import com.nali.data.client.IClientDaS;
import com.nali.draw.DrawScreen;
import com.nali.list.render.s.TPBaseRender;
import com.nali.small.mix.block.BlockB;
import com.nali.small.mix.item.ItemB;
import com.nali.small.mix.memo.IBothN;
import com.nali.small.mix.memo.client.ClientTPBase;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

import static com.nali.Nali.I;

public class SmallTPBase extends BlockB implements ITileEntityProvider
{
    public static int ID;
    public static final AxisAlignedBB AXISALIGNEDBB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D);

//    @SideOnly(Side.CLIENT)
//    public TPBaseRender tpbaserender;
//    @SideOnly(Side.CLIENT)
//    public static DrawScreen DRAWSCREEN;

    public SmallTPBase(String[] string_array)
    {
        super(string_array, Material.ROCK);
//        this.ibothb.init(this, string_array[0], string_array[1], SmallTab.TAB);
//        if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
//        {
//            this.renderInit();
//        }
        this.setResistance(2000.0F);
        this.setHardness(50.0F);
        this.setSoundType(SoundType.STONE);
        this.setLightLevel(0.1F);
    }

//    @SideOnly(Side.CLIENT)
//    public void renderInit()
//    {
////        this.tpbaserender = new TPBaseRender();
////        OpenGLAnimationMemo openglanimationmemo = (OpenGLAnimationMemo)Nali.I.clientloader.object_list.get(((SkinningClientData)this.tpbaserender.clientdata).AnimationID());
////        this.tpbaserender.initSkinning(openglanimationmemo);
////        this.tpbaserender.setSkinning(openglanimationmemo);
////        DRAWSCREEN = new DrawScreen();
////        DRAWSCREEN.scale(0.25F);
////        DRAWSCREEN.z = 0.0F;
//    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new com.nali.list.block.tile.SmallTPBase();
    }

//    @Override
//    public Item getNewItem(Block block)
    public Item getNewItem()
    {
        return new ItemB(this);
    }

//    @Override
//    @SideOnly(Side.CLIENT)
//    public ObjectRender getObjectRender()
//    {
////        if (this.tpbaserender == null)
////        {
////            this.tpbaserender = new TPBaseRender(this);
////        }
//        return this.tpbaserender;
//    }

//    @Override
//    @SideOnly(Side.CLIENT)
//    public DrawScreen getDrawScreen()
//    {
//        return DRAWSCREEN;
//    }

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

    @SideOnly(Side.CLIENT)
    @Override
    public void newC()
    {
        IClientDaS iclientdas = TPBaseRender.ICLIENTDAS;
        TPBaseRender tpbaserender = new TPBaseRender(I.clientloader.stores, iclientdas, TPBaseRender.IBOTHDASN);
        DrawScreen drawscreen = new DrawScreen(tpbaserender);
        drawscreen.scale(0.25F);
        drawscreen.z = 0.0F;
//        MemoAnimation memoanimation = I.clientloader.stores.memoanimation_list.get(iclientdas.AnimationID());
        tpbaserender.initSkinning(/*memoanimation*/);
        tpbaserender.setSkinning(/*memoanimation*/);
        this.ibothb = new ClientTPBase(tpbaserender, drawscreen, this);
    }

    @Override
    public IBothN getB()
    {
        return this.ibothb;
    }

    @Override
    public Object getE()
    {
        return this;
    }
}
