package com.nali.small.entity.memo.server.ai.frame;

import com.nali.data.IBothDaE;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.sound.ISoundN;
import net.minecraft.entity.Entity;

public abstract class FrameE<SD extends ISoundN, BD extends IBothDaE, E extends Entity, I extends IMixE<SD, BD, E>, S extends ServerE<SD, BD, E, I, A>, A extends MixAIE<SD, BD, E, I, S>>
{
    public S s;

    public byte step = 1;
//    public int main_integer_index;
    public byte frame, index;
//    public int[][] int_2d_array; // start end
    public boolean lock;
//    public Supplier<Boolean>[] condition_boolean_supplier_array;

    public FrameE(S s/*, int main_integer_index*/, byte frame, byte index/*, int[][] int_2d_array*/)
    {
        this.s = s;
//        this.main_integer_index = main_integer_index;
        this.frame = frame;
        this.index = index;
//        this.int_2d_array = int_2d_array;
    }

//    @Override
//    public void onUpdate()
//    {
//        I i = this.s.getI();
////        Small.LOGGER.info("FRAME " + this.s.frame_int_array[this.integer_index]);
//
//        if (!this.lock)
//        {
//            for (Supplier<Boolean> boolean_supplier : this.condition_boolean_supplier_array)
//            {
//                if (boolean_supplier.get())
//                {
//                    i.getE().getDataManager().set(i.getIntegerDataParameterArray()[/*this.main_integer_index*/this.integer_index], this.s.frame_int_array[this.integer_index] + this.step);
//                    return;
//                }
//            }
//        }
//    }

    public void stepFrame()
    {
        I i = this.s.getI();
        i.getE().getDataManager().set(i.getIntegerDataParameterArray()[this.frame], this.s.frame_int_array[this.index] + this.step);
    }

    public abstract boolean onUpdate();
}
