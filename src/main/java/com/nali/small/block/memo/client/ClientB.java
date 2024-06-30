package com.nali.small.block.memo.client;

import com.nali.data.client.IClientDaO;
import com.nali.draw.DrawScreen;
import com.nali.render.RenderO;
import com.nali.small.block.memo.IBothB;
import com.nali.system.opengl.memo.MemoGo;
import com.nali.system.opengl.memo.MemoSo;
import com.nali.system.opengl.store.StoreO;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class ClientB<RG extends MemoGo, RS extends MemoSo, RC extends IClientDaO, RST extends StoreO<RG, RS>, R extends RenderO<RG, RS, RST, RC>> implements IBothB
{
    public R r;
    public DrawScreen<RG, RS, RST, RC, R> drawscreen;

    public ClientB(R r)
    {
        this.r = r;
        this.drawscreen = this.createDrawScreen();
    }

    public void render()
    {
        this.drawscreen.render();
    }

    public abstract DrawScreen<RG, RS, RST, RC, R> createDrawScreen();
}
