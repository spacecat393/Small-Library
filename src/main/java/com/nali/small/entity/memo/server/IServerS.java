package com.nali.small.entity.memo.server;

import com.nali.small.entity.memo.server.ai.frame.FrameE;

public interface IServerS
{
    default void updateFrame()
    {
        for (FrameE framee : this.getFrameArray())
        {
            framee.onUpdate();
        }
    }

    FrameE[] getFrameArray();
    int[][] getFrame2DIntArray();
}
