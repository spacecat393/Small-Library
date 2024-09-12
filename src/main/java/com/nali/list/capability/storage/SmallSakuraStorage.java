package com.nali.list.capability.storage;

import com.nali.list.capability.type.SmallSakuraType;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class SmallSakuraStorage implements Capability.IStorage<SmallSakuraType>
{
	@Override
	public NBTBase writeNBT(Capability<SmallSakuraType> capability, SmallSakuraType instance, EnumFacing side)
	{
		return new NBTTagByte(instance.get());
	}

	@Override
	public void readNBT(Capability<SmallSakuraType> capability, SmallSakuraType instance, EnumFacing side, NBTBase nbt)
	{
		instance.set(((NBTTagByte)nbt).getByte());
	}
}
