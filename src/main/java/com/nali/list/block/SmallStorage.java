//package com.nali.list.block;
//
//import com.nali.da.IBothDaO;
//import com.nali.list.da.BothDaStorage;
//import com.nali.list.render.RenderStorage;
//import com.nali.small.mix.block.BlockB;
//import com.nali.small.mix.item.ItemB;
//import com.nali.small.mix.memo.IBothN;
//import com.nali.small.mix.memo.client.ClientB;
//import net.minecraft.block.ITileEntityProvider;
//import net.minecraft.block.SoundType;
//import net.minecraft.block.material.Material;
//import net.minecraft.block.state.IBlockState;
//import net.minecraft.item.Item;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.world.World;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//
//import javax.annotation.Nullable;
//
//public class SmallStorage extends BlockB implements ITileEntityProvider
//{
//	public static int ID;
//
////	@SideOnly(Side.CLIENT)
////	public StorageRender storagerender;
////	@SideOnly(Side.CLIENT)
////	public static DrawScreen DRAWSCREEN;
//
//	public SmallStorage(String[] string_array)
//	{
//		super(string_array, Material.ROCK);
////		this.ibothb.init(this, string_array[0], string_array[1], SmallTab.TAB);
//		this.setResistance(2000.0F);
//		this.setHardness(50.0F);
//		this.setSoundType(SoundType.STONE);
//		this.setLightLevel(0.1F);
//	}
//
////	@Override
////	@SideOnly(Side.CLIENT)
////	public void renderInit()
////	{
////		this.storagerender = new StorageRender();
////		DRAWSCREEN = new DrawScreen();
////		DRAWSCREEN.scale(0.25F);
////		DRAWSCREEN.z = 0.0F;
////	}
//
//	@Nullable
//	@Override
//	public TileEntity createNewTileEntity(World worldIn, int meta)
//	{
//		return new com.nali.list.block.tile.SmallStorage();
//	}
//
//	public Item getNewItem()
//	{
//		return new ItemB(this);
//	}
//
////	@Override
////	@SideOnly(Side.CLIENT)
////	public ObjectRender getObjectRender()
////	{
//////		if (this.tpbaserender == null)
//////		{
//////			this.tpbaserender = new TPBaseRender(this);
//////		}
////		return this.storagerender;
////	}
////
////	@Override
////	@SideOnly(Side.CLIENT)
////	public DrawScreen getDrawScreen()
////	{
////		return DRAWSCREEN;
////	}
//
//	@Override
//	public boolean isOpaqueCube(IBlockState state)
//	{
//		return false;
//	}
//
//	@Override
//	public boolean isFullCube(IBlockState state)
//	{
//		return false;
//	}
//
//	@SideOnly(Side.CLIENT)
//	@Override
//	public void newC()
//	{
//		RenderStorage r = new RenderStorage();
////		DrawScreen d = new DrawScreen(r);
////		d.scale(0.25F);
////		d.z = 0.0F;
//		this.ibothb = new ClientB(r/*, d*/, this);
//	}
//
//	@Override
//	public IBothDaO getBD()
//	{
//		return BothDaStorage.IDA;
//	}
//
//	@Override
//	public IBothN getB()
//	{
//		return this.ibothb;
//	}
//
//	@Override
//	public Object getE()
//	{
//		return this;
//	}
//}
