package com.nali.small.render;

import com.nali.data.client.IClientDaO;
import com.nali.render.RenderO;
import com.nali.system.opengl.memo.client.MemoGo;
import com.nali.system.opengl.memo.client.MemoSo;
import com.nali.system.opengl.memo.client.store.StoreO;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.nali.list.data.SmallData.SHADER_O_STEP;
import static com.nali.list.data.SmallData.TEXTURE_STEP;

@SideOnly(Side.CLIENT)
public class SmallRenderO<RG extends MemoGo, RS extends MemoSo, RST extends StoreO<RG, RS>, RC extends IClientDaO> extends RenderO<RG, RS, RST, RC>
{
    public SmallRenderO(RST rst, RC rc)
    {
        super(rst, rc);
    }

    @Override
    public int getTextureID(RG rg)
    {
        return TEXTURE_STEP + super.getTextureID(rg);
    }

    @Override
    public int getShaderID(RG rg)
    {
        return SHADER_O_STEP + super.getShaderID(rg);
    }
}
