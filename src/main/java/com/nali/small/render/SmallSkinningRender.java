package com.nali.small.render;

import com.nali.data.BothDataS;
import com.nali.data.client.ClientDataS;
import com.nali.render.SkinningRender;
import com.nali.render.memo.RenderMemo;
import com.nali.system.opengl.memo.OpenGLObjectMemo;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.nali.list.data.SmallData.SHADER_STEP;
import static com.nali.list.data.SmallData.TEXTURE_STEP;

@SideOnly(Side.CLIENT)
public class SmallSkinningRender<M extends RenderMemo, C extends ClientDataS, B extends BothDataS> extends SkinningRender<M, C, B>
{
    public SmallSkinningRender(M m, C c, B b)
    {
        super(m, c, b);
    }

    @Override
    public int getTextureID(OpenGLObjectMemo openglobjectmemo)
    {
        return TEXTURE_STEP + super.getTextureID(openglobjectmemo);
    }

    @Override
    public int getShaderID(OpenGLObjectMemo openglobjectmemo)
    {
        return SHADER_STEP + super.getShaderID(openglobjectmemo);
    }
}
