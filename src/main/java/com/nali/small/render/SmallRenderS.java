package com.nali.small.render;

import com.nali.data.BothDataS;
import com.nali.data.client.ClientDataS;
import com.nali.render.SkinningRender;
import com.nali.system.opengl.memo.MemoGs;
import com.nali.system.opengl.memo.MemoSs;
import com.nali.system.opengl.store.StoreS;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.nali.list.data.SmallData.SHADER_S_STEP;
import static com.nali.list.data.SmallData.TEXTURE_STEP;

@SideOnly(Side.CLIENT)
public class SmallRenderS<B extends BothDataS, G extends MemoGs, S extends MemoSs, ST extends StoreS<G, S>, C extends ClientDataS> extends SkinningRender<B, G, S, ST, C>
{
    public SmallRenderS(ST st, C c, B b)
    {
        super(st, c, b);
    }

    @Override
    public int getTextureID(G g)
    {
        return TEXTURE_STEP + super.getTextureID(g);
    }

    @Override
    public int getShaderID(G g)
    {
        return SHADER_S_STEP + super.getShaderID(g);
    }
}
