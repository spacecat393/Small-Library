package com.nali.list.capabilitiesstorages;

import com.nali.list.capabilitiestypes.SmallSakuraTypes;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class SmallSakura implements Capability.IStorage<SmallSakuraTypes>
{
    @Override
    public NBTBase writeNBT(Capability<SmallSakuraTypes> capability, SmallSakuraTypes instance, EnumFacing side)
    {
        return new NBTTagInt(instance.get());
    }

    @Override
    public void readNBT(Capability<SmallSakuraTypes> capability, SmallSakuraTypes instance, EnumFacing side, NBTBase nbt)
    {
        instance.set(((NBTTagInt)nbt).getInt());
    }
}
