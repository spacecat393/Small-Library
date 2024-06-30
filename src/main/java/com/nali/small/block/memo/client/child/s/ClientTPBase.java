package com.nali.small.block.memo.client.child.s;

import com.nali.data.IBothDaSn;
import com.nali.data.client.IClientDaS;
import com.nali.draw.DrawScreen;
import com.nali.render.RenderS;
import com.nali.small.block.memo.client.ClientB;
import com.nali.sound.ISoundN;
import com.nali.system.opengl.memo.MemoGs;
import com.nali.system.opengl.memo.MemoSs;
import com.nali.system.opengl.store.StoreS;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientTPBase<SD extends ISoundN, BD extends IBothDaSn<SD>, RG extends MemoGs, RS extends MemoSs, RC extends IClientDaS, RST extends StoreS<RG, RS>, R extends RenderS<SD, BD, RG, RS, RST, RC>> extends ClientB<RG, RS, RC, RST, R>
{
    public ClientTPBase(R r)
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
