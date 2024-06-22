package com.nali.small.render;

import com.nali.data.client.ClientDataO;
import com.nali.render.ObjectRender;
import com.nali.system.opengl.memo.MemoGo;
import com.nali.system.opengl.memo.MemoSo;
import com.nali.system.opengl.store.StoreO;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.nali.list.data.SmallData.SHADER_O_STEP;
import static com.nali.list.data.SmallData.TEXTURE_STEP;

@SideOnly(Side.CLIENT)
public class RenderO<G extends MemoGo, S extends MemoSo, ST extends StoreO<G, S>, C extends ClientDataO> extends ObjectRender<G, S, ST, C>
{
    public RenderO(ST st, C c)
    {
        super(st, c);
    }

    @Override
    public int getTextureID(G g)
    {
        return TEXTURE_STEP + super.getTextureID(g);
    }

    @Override
    public int getShaderID(G g)
    {
        return SHADER_O_STEP + super.getShaderID(g);
    }
}
