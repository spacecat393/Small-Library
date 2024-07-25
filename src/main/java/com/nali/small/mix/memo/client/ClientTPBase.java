package com.nali.small.mix.memo.client;

import com.nali.da.IBothDaSn;
import com.nali.da.client.IClientDaS;
import com.nali.render.RenderS;
import com.nali.small.mix.IMixN;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientTPBase</*SD, */BD extends IBothDaSn, RC extends IClientDaS, R extends RenderS<BD, RC>, I extends IMixN<?, E>, E extends Block, T extends TileEntity> extends ClientSb</*SD, */BD, RC, R, I, E, T>
{
    public ClientTPBase(R r, I i)
    {
        super(r, i);
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
