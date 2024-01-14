package com.nali.list.capabilitiesserializations;

import com.nali.list.capabilitiestypes.SmallSakuraTypes;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class SmallSakuraSerializations implements ICapabilitySerializable<NBTBase>
{
    @CapabilityInject(SmallSakuraTypes.class)
    public static Capability<SmallSakuraTypes> SMALLSAKURATYPES_CAPABILITY;

    public SmallSakuraTypes smallsakuratypes = SMALLSAKURATYPES_CAPABILITY.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing enumfacing)
    {
        return capability == SMALLSAKURATYPES_CAPABILITY;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing enumfacing)
    {
        return capability == SMALLSAKURATYPES_CAPABILITY ? SMALLSAKURATYPES_CAPABILITY.<T> cast(this.smallsakuratypes) : null;
    }

    @Override
    public NBTBase serializeNBT()
    {
        return SMALLSAKURATYPES_CAPABILITY.getStorage().writeNBT(SMALLSAKURATYPES_CAPABILITY, this.smallsakuratypes, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbtbase)
    {
        SMALLSAKURATYPES_CAPABILITY.getStorage().readNBT(SMALLSAKURATYPES_CAPABILITY, this.smallsakuratypes, null, nbtbase);
    }
}
