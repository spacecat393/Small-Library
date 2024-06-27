package com.nali.small.entity;

import com.nali.data.IBothDaE;
import com.nali.sound.ISoundN;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public interface IMixLe<SD extends ISoundN, BD extends IBothDaE, E extends EntityLivingBase> extends IMixE<SD, BD, E>
{
    @Override
    default void Einit(E e, World world)
    {
        IMixE.super.Einit(e, world);
    }

//    ByteActionLe getByteActionLe();
//    ByteStateLe getByteStateLe();
//    SoundLe getSoundLe();

//    @Override
//    IBothLe<E, ?> getBoth();
}
