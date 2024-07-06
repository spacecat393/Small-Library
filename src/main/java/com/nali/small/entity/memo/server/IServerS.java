package com.nali.small.entity.memo.server;

import com.nali.small.entity.memo.server.ai.frame.FrameS;

public interface IServerS
{
    default void updateFrame()
    {
        for (FrameS frames : this.getFrameSArray())
        {
            if (frames.onUpdate())
            {
                return;
            }
        }
    }

    void initFrame();
    FrameS[] getFrameSArray();
    byte[] getFrameByteArray();
    int[][] getFrame2DIntArray();
}
