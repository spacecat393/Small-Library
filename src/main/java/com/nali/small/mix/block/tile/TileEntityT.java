//package com.nali.small.tile;
//
//import net.minecraft.tileentity.TileEntity;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//
//public abstract class TileEntityT extends TileEntity
//{
////	@Override
////	public void readFromNBT(NBTTagCompound compound)
////	{
////		super.readFromNBT(compound);
////	}
////
////	@Override
////	public NBTTagCompound writeToNBT(NBTTagCompound compound)
////	{
////		return super.writeToNBT(compound);
////	}
//
////	@Nullable
////	@Override
////	public SPacketUpdateTileEntity getUpdatePacket()
////	{
////		NBTTagCompound nbttagcompound = new NBTTagCompound();
////		this.writeToNBT(nbttagcompound);
////		return new SPacketUpdateTileEntity(this.pos, 1, nbttagcompound);
////	}
////
////	@Override
////	public void onDataPacket(NetworkManager networkmanager, SPacketUpdateTileEntity spacketupdatetileentity)
////	{
////		this.readFromNBT(spacketupdatetileentity.getNbtCompound());
////	}
//
//	@SideOnly(Side.CLIENT)
//	public double getMaxRenderDistanceSquared()
//	{
//		return Integer.MAX_VALUE;
//	}
//}
