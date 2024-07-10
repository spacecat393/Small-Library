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

    void initFrame();
    FrameS[][] getFrameS2DArray();
    byte[] getFrameByteArray();
    int[][] getFrame2DIntArray();
}
