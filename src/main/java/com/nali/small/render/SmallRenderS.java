package com.nali.small.render;

import com.nali.data.IBothDaSn;
import com.nali.data.client.IClientDaS;
import com.nali.render.RenderS;
import com.nali.sound.ISoundN;
import com.nali.system.opengl.memo.client.MemoGs;
import com.nali.system.opengl.memo.client.MemoSs;
import com.nali.system.opengl.memo.client.store.StoreS;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.nali.list.data.SmallData.SHADER_S_STEP;
import static com.nali.list.data.SmallData.TEXTURE_STEP;

@SideOnly(Side.CLIENT)
public class SmallRenderS<SD extends ISoundN, BD extends IBothDaSn<SD>, RG extends MemoGs, RS extends MemoSs, RST extends StoreS<RG, RS>, RC extends IClientDaS> extends RenderS<SD, BD, RG, RS, RST, RC>
{
    public SmallRenderS(RST rst, RC rc, BD bd)
    {
        super(rst, rc, bd);
    }

    @Override
    public int getTextureID(RG rg)
    {
        return TEXTURE_STEP + super.getTextureID(rg);
    }

    @Override
    public int getShaderID(RG rg)
    {
        return SHADER_S_STEP + super.getShaderID(rg);
    }
}
