package com.nali.list.capability.serializable;

import com.nali.list.capability.type.SmallSakuraType;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class SmallSakuraSerializable implements ICapabilitySerializable<NBTBase>
{
    @CapabilityInject(SmallSakuraType.class)
    public static Capability<SmallSakuraType> SMALLSAKURATYPES_CAPABILITY;

    public SmallSakuraType smallsakuratypes = SMALLSAKURATYPES_CAPABILITY.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing enumfacing)
    {
        return capability == SMALLSAKURATYPES_CAPABILITY;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing enumfacing)
    {
        return capability == SMALLSAKURATYPES_CAPABILITY ? SMALLSAKURATYPES_CAPABILITY.cast(this.smallsakuratypes) : null;
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
