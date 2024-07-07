package com.nali.small.entity;

import com.nali.da.IBothDaNe;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public interface IMixLe<SD, BD extends IBothDaNe, E extends EntityLivingBase> extends IMixE<SD, BD, E>
{
    @Override
    default void Einit(E e, World world)
    {
        IMixE.super.Einit(e, world);
    }

//    SD getSD();

//    ByteActionLe getByteActionLe();
//    ByteStateLe getByteStateLe();
//    SoundLe getSoundLe();

//    @Override
//    IBothLe<E, ?> getBoth();
}
