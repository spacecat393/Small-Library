package com.nali.small.entity;

import com.nali.da.IBothDaE;
import com.nali.small.entity.memo.IBothE;
import net.minecraft.entity.Entity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IMixE
<
	BD extends IBothDaE,
	E extends Entity
>
{
	default void Einit(E e, World world)
	{
		if (world.isRemote)
		{
			this.newC();

			BD bd = this.getBD();
			float scale = bd.E_Scale();
			e.width = bd.E_Width() * scale;
			e.height = bd.E_Height() * scale;
		}
		else
		{
			this.newS();

			e.width = 0.5F;
			e.height = 0.5F;
		}
	}

	default void EentityInit()
	{
		EntityDataManager entitydatamanager = this.getE().getDataManager();
		DataParameter<Byte>[] byte_dataparameter_array = this.getByteDataParameterArray();
		for (DataParameter<Byte> byte_dataparameter : byte_dataparameter_array)
		{
			entitydatamanager.register(byte_dataparameter, (byte)0);
		}

//		DataParameter<Integer>[] integer_dataparameter_array = this.getIntegerDataParameterArray();
//		for (DataParameter<Integer> integer_dataparameter : integer_dataparameter_array)
//		{
//			entitydatamanager.register(integer_dataparameter, 0);
//		}
//
//		DataParameter<Float>[] float_dataparameter_array = this.getFloatDataParameterArray();
//		for (DataParameter<Float> float_dataparameter : float_dataparameter_array)
//		{
//			entitydatamanager.register(float_dataparameter, 0.0F);
//		}
	}

//	default void EwriteEntityToNBT(NBTTagCompound nbttagcompound)
//	{
//		EntityDataManager entitydatamanager = this.getE().getDataManager();
//
//		int i = 0;
//
//		DataParameter<Byte>[] byte_dataparameter_array = this.getByteDataParameterArray();
//		for (DataParameter<Byte> byte_dataparameter : byte_dataparameter_array)
//		{
//			nbttagcompound.setInteger("byte_" + i++, entitydatamanager.get(byte_dataparameter));
//		}
//		i = 0;
//
//		DataParameter<Integer>[] integer_dataparameter_array = this.getIntegerDataParameterArray();
//		for (DataParameter<Integer> integer_dataparameter : integer_dataparameter_array)
//		{
//			nbttagcompound.setInteger("int_" + i++, entitydatamanager.get(integer_dataparameter));
//		}
//		i = 0;
//
//		DataParameter<Float>[] float_dataparameter_array = this.getFloatDataParameterArray();
//		for (DataParameter<Float> float_dataparameter : float_dataparameter_array)
//		{
//			nbttagcompound.setFloat("float_" + i++, entitydatamanager.get(float_dataparameter));
//		}
//	}
//
//	default void EreadEntityFromNBT(NBTTagCompound nbttagcompound)
//	{
//		EntityDataManager entitydatamanager = this.getE().getDataManager();
//
//		int i = 0;
//
//		DataParameter<Byte>[] byte_dataparameter_array = this.getByteDataParameterArray();
//		for (DataParameter<Byte> byte_dataparameter : byte_dataparameter_array)
//		{
//			entitydatamanager.set(byte_dataparameter, nbttagcompound.getByte("byte_" + i++));
//		}
//		i = 0;
//
//		DataParameter<Integer>[] integer_dataparameter_array = this.getIntegerDataParameterArray();
//		for (DataParameter<Integer> integer_dataparameter : integer_dataparameter_array)
//		{
//			entitydatamanager.set(integer_dataparameter, nbttagcompound.getInteger("int_" + i++));
//		}
//		i = 0;
//
//		DataParameter<Float>[] float_dataparameter_array = this.getFloatDataParameterArray();
//		for (DataParameter<Float> float_dataparameter : float_dataparameter_array)
//		{
//			entitydatamanager.set(float_dataparameter, nbttagcompound.getFloat("float_" + i++));
//		}
//	}
//	void initID();

	byte[] getCI();
	byte[] getSI();

	DataParameter<Byte>[] getByteDataParameterArray();
//	DataParameter<Integer>[] getIntegerDataParameterArray();
//	DataParameter<Float>[] getFloatDataParameterArray();

	@SideOnly(Side.CLIENT)
	void newC();

	void newS();

	BD getBD();
	E getE();
	IBothE getB();
}
