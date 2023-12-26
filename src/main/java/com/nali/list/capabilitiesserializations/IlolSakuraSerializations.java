package com.nali.list.capabilitiesserializations;

import com.nali.list.capabilitiestypes.IlolSakuraTypes;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class IlolSakuraSerializations implements ICapabilitySerializable<NBTBase>
{
    @CapabilityInject(IlolSakuraTypes.class)
    public static Capability<IlolSakuraTypes> ILOLSAKURATYPES_CAPABILITY;

    public IlolSakuraTypes ilolsakuratypes = ILOLSAKURATYPES_CAPABILITY.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing enumfacing)
    {
        return capability == ILOLSAKURATYPES_CAPABILITY;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing enumfacing)
    {
        return capability == ILOLSAKURATYPES_CAPABILITY ? ILOLSAKURATYPES_CAPABILITY.<T> cast(this.ilolsakuratypes) : null;
    }

    @Override
    public NBTBase serializeNBT()
    {
        return ILOLSAKURATYPES_CAPABILITY.getStorage().writeNBT(ILOLSAKURATYPES_CAPABILITY, this.ilolsakuratypes, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbtbase)
    {
        ILOLSAKURATYPES_CAPABILITY.getStorage().readNBT(ILOLSAKURATYPES_CAPABILITY, this.ilolsakuratypes, null, nbtbase);
    }
}
