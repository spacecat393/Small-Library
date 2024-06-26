package com.nali.list.render;

import com.nali.data.BothDataS;
import com.nali.data.client.ClientDataO;
import com.nali.data.client.ClientDataS;
import com.nali.small.data.both.TPBaseBothDataS;
import com.nali.small.data.client.TPBaseClient;
import com.nali.small.render.SmallRenderS;
import com.nali.system.opengl.memo.MemoGs;
import com.nali.system.opengl.memo.MemoSs;
import com.nali.system.opengl.store.StoreS;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.Map;

import static com.nali.Nali.I;

@SideOnly(Side.CLIENT)
public class TPBaseRender<B extends BothDataS, G extends MemoGs, S extends MemoSs, ST extends StoreS<G, S>, C extends ClientDataS> extends SmallRenderS<B, G, S, ST, C>
{
    public static C c = new TPBaseClient();
    public static B b = new TPBaseBothDataS();
    public static Map<Integer, Integer> TEXTURE_MAP = new HashMap();

    public TPBaseRender()
    {
        super(null, BOTHDATA, CLIENTDATA);
    }

    public static void setTextureMap()
    {
        int diamond_block = com.nali.render.RenderHelper.getTextureBuffer(new ResourceLocation("textures/blocks/diamond_block.png"));
        TEXTURE_MAP.put(I.clientloader.stores.g_list.get(CLIENTDATA.StartPart()).element_array_buffer, com.nali.render.RenderHelper.getTextureBuffer(new ResourceLocation("textures/blocks/obsidian.png")));
        TEXTURE_MAP.put(I.clientloader.stores.g_list.get(CLIENTDATA.StartPart() + 1).element_array_buffer, diamond_block);
        TEXTURE_MAP.put(I.clientloader.stores.g_list.get(CLIENTDATA.StartPart() + 2).element_array_buffer, diamond_block);
    }

    @Override
    public int getTextureBuffer(G g)
    {
        return TEXTURE_MAP.get(g.element_array_buffer);
    }
}
