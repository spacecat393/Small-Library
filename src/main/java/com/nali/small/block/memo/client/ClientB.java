package com.nali.small.block.memo.client;

import com.nali.draw.DrawScreen;
import com.nali.render.ObjectRender;
import com.nali.small.block.memo.IBothB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientB<R extends ObjectRender> implements IBothB
{
    public R r;
    public DrawScreen<R> drawscreen;

    public void render()
    {
        this.drawscreen.render(this.r);
    }
}
