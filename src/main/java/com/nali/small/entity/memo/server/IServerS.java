package com.nali.small.entity.memo.server;

import com.nali.small.entity.memo.server.ai.frame.FrameS;

public interface IServerS
{
    default void updateFrame()
    {
        for (FrameS[] frames_array : this.getFrameS2DArray())
        {
            for (FrameS frames : frames_array)
            {
                if (frames.onUpdate())
                {
                    frames.stepFrame();
                    break;
                }
            }
        }
    }

//    default <E extends Entity, I extends IMixE<?, ?, E>> void syncFrame(I i)
//    {
//        E e = i.getE();
//        EntityDataManager entitydatamanager = e.getDataManager();
//        DataParameter<Integer>[] integer_dataparameter = i.getIntegerDataParameterArray();
//        int[] frame_int_array = this.getFrameIntArray();
//        for (int f = 0; f < frame_int_array.length; ++f)
//        {
//            frame_int_array[f] = entitydatamanager.get(integer_dataparameter[f]);
//        }
//    }

    void initFrame();
    FrameS[][] getFrameS2DArray();
    byte[] getFrameByteArray();
    int[][] getFrame2DIntArray();
    int[] getFrameIntArray();
}
