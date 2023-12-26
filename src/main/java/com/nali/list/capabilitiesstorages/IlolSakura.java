package com.nali.list.capabilitiesstorages;

import com.nali.list.capabilitiestypes.IlolSakuraTypes;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class IlolSakura implements Capability.IStorage<IlolSakuraTypes>
{
    @Override
    public NBTBase writeNBT(Capability<IlolSakuraTypes> capability, IlolSakuraTypes instance, EnumFacing side)
    {
        return new NBTTagInt(instance.get());
    }

    @Override
    public void readNBT(Capability<IlolSakuraTypes> capability, IlolSakuraTypes instance, EnumFacing side, NBTBase nbt)
    {
        instance.set(((NBTTagInt)nbt).getInt());
    }
}
