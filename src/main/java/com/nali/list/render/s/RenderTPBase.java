package com.nali.list.render.s;

import com.nali.data.IBothDaSn;
import com.nali.data.client.IClientDaS;
import com.nali.small.da.both.TPBaseBothDaS;
import com.nali.small.da.client.TPBaseClient;
import com.nali.small.render.SmallRenderS;
import com.nali.system.opengl.memo.client.MemoGs;
import com.nali.system.opengl.memo.client.MemoSs;
import com.nali.system.opengl.memo.client.store.StoreS;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.Map;

import static com.nali.Nali.I;

@SideOnly(Side.CLIENT)
public class RenderTPBase<BD extends IBothDaSn, RG extends MemoGs, RS extends MemoSs, RST extends StoreS<RG, RS>, RC extends IClientDaS> extends SmallRenderS<BD, RG, RS, RST, RC>
{
    public static IClientDaS ICLIENTDAS = new TPBaseClient();
    public static IBothDaSn IBOTHDASN = new TPBaseBothDaS();

    public static Map<Integer, Integer> TEXTURE_MAP = new HashMap();

    public RenderTPBase()
    {
        this((RST)I.clientloader.stores, (RC)ICLIENTDAS, (BD)IBOTHDASN);
    }

    public RenderTPBase(RST rst, RC rc, BD bd)
    {
        super(rst, rc, bd);
    }

    public static void setTextureMap()
    {
        int diamond_block = com.nali.render.RenderHelper.getTextureBuffer(new ResourceLocation("textures/blocks/diamond_block.png"));
        TEXTURE_MAP.put(I.clientloader.stores.rg_list.get(ICLIENTDAS.StartPart()).element_array_buffer, com.nali.render.RenderHelper.getTextureBuffer(new ResourceLocation("textures/blocks/obsidian.png")));
        TEXTURE_MAP.put(I.clientloader.stores.rg_list.get(ICLIENTDAS.StartPart() + 1).element_array_buffer, diamond_block);
        TEXTURE_MAP.put(I.clientloader.stores.rg_list.get(ICLIENTDAS.StartPart() + 2).element_array_buffer, diamond_block);
    }

    @Override
    public int getTextureBuffer(RG rg)
    {
        return TEXTURE_MAP.get(rg.element_array_buffer);
    }
}
