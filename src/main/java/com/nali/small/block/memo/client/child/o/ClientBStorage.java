package com.nali.small.block.memo.client.child.o;

import com.nali.data.client.IClientDaO;
import com.nali.draw.DrawScreen;
import com.nali.render.RenderO;
import com.nali.small.block.memo.client.ClientB;
import com.nali.system.opengl.memo.MemoGo;
import com.nali.system.opengl.memo.MemoSo;
import com.nali.system.opengl.store.StoreO;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientBStorage<RG extends MemoGo, RS extends MemoSo, RC extends IClientDaO, RST extends StoreO<RG, RS>, R extends RenderO<RG, RS, RST, RC>> extends ClientB<RG, RS, RC, RST, R>
{
    public ClientBStorage(R r)
    {
        super(r);
    }

    @Override
    public DrawScreen<RG, RS, RST, RC, R> createDrawScreen()
    {
        DrawScreen<RG, RS, RST, RC, R> drawscreen = new DrawScreen(this.r);
        drawscreen.scale(0.25F);
        drawscreen.z = 0.0F;
        return drawscreen;
    }
}
