package com.nali.small.entity;

import com.nali.data.IBothDaSe;
import com.nali.small.entity.sound.SoundLe;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public interface IMixLe<BD extends IBothDaSe, E extends EntityLivingBase> extends IMixE<BD, E>
{
    @Override
    default void Einit(E e, World world)
    {
        IMixE.super.Einit(e, world);
    }

//    ByteActionLe getByteActionLe();
//    ByteStateLe getByteStateLe();
    SoundLe getSoundLe();

//    @Override
//    IBothLe<E, ?> getBoth();
}
