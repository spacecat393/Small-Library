package com.nali.list.entity.ai;

import com.nali.da.IBothDaNe;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.sound.ISoundDaLe;
import net.minecraft.entity.EntityLivingBase;

public abstract class AILePlayWithSSle<R2 extends AIEPlayWithRSe/*AILePlayWithSSle*/</*?, */S, SD, BD, E, I, A, SD2, BD2, E2, I2, S2, A2>, S2 extends ServerLe<SD2, BD2, E2, I2, A2>, SD2 extends ISoundDaLe, BD2 extends IBothDaNe, E2 extends EntityLivingBase, I2 extends IMixLe<SD2, BD2, E2>, A2 extends MixAIE<SD2, BD2, E2, I2, S2>, SD extends ISoundDaLe, BD extends IBothDaNe, E extends EntityLivingBase, I extends IMixLe<SD, BD, E>, S extends ServerLe<SD, BD, E, I, A>, A extends MixAIE<SD, BD, E, I, S>> extends AILePlayWithSSe<R2, S2, SD2, BD2, E2, I2, A2, SD, BD, E, I, S, A>
{
    public static byte ID;

    public AILePlayWithSSle(S s)
    {
        super(s);
    }

    public void onPlay()
    {
//        I i = this.s.i;
//        E e = i.getE();
//        I2 i2 = this.s2.i;
//        E2 e2 = i2.getE();
        super.onPlay();
        this.s.i.getE().renderYawOffset = this.s2.i.getE().renderYawOffset;
//        e.renderYawOffset = e2.renderYawOffset;
//        DataParameter<Byte> byte_dataparameter = i.getByteDataParameterArray()[0];
//        EntityDataManager entitydatamanager = e.getDataManager();
//        byte[] sync_byte_array = this.s.sync_byte_array;
//        sync_byte_array[0] = entitydatamanager.get(byte_dataparameter);
//        if ((sync_byte_array[0] & 128) == 0)
//        {
//            sync_byte_array[0] ^= (byte)128;
//            entitydatamanager.set(byte_dataparameter, sync_byte_array[0]);
//        }
//        entitydatamanager.set(i.getIntegerDataParameterArray()[1], e2.getDataManager().get(i2.getIntegerDataParameterArray()[0]));
//        entitydatamanager.set(i.getFloatDataParameterArray()[1], e2.getDataManager().get(i2.getFloatDataParameterArray()[0]));
    }
}
