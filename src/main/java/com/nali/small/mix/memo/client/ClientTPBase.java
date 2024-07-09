package com.nali.small.mix.memo.client;

import com.nali.da.IBothDaSn;
import com.nali.da.client.IClientDaS;
import com.nali.draw.DrawScreen;
import com.nali.render.RenderS;
import com.nali.small.mix.IMixN;
import com.nali.system.opengl.memo.client.MemoGs;
import com.nali.system.opengl.memo.client.MemoSs;
import com.nali.system.opengl.memo.client.store.StoreS;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientTPBase</*SD, */BD extends IBothDaSn, RG extends MemoGs, RS extends MemoSs, RC extends IClientDaS, RST extends StoreS<RG, RS>, R extends RenderS<BD, RG, RS, RST, RC>, D extends DrawScreen<RG, RS, RST, RC, R>, I extends IMixN<?, E>, E extends Block, T extends TileEntity> extends ClientSb</*SD, */BD, RG, RS, RC, RST, R, D, I, E, T>
{
    public ClientTPBase(R r, D d, I i)
    {
        super(r, d, i);
    }

    @Override
    public void updateFrame(R r)
    {
        if (r.frame_int_array[0] < 78)
        {
            ++r.frame_int_array[0];
        }
        else
        {
            r.frame_int_array[0] = 0;
        }
    }
}
