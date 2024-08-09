package com.nali.list.render.s;

import com.nali.da.IBothDaSn;
import com.nali.da.client.IClientDaS;
import com.nali.small.da.both.TPBaseBothDaS;
import com.nali.small.da.client.TPBaseClient;
import com.nali.small.render.SmallRenderS;
import com.nali.system.opengl.memo.client.MemoG;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.Map;

import static com.nali.system.ClientLoader.G_LIST;

@SideOnly(Side.CLIENT)
public class RenderTPBase<BD extends IBothDaSn, RC extends IClientDaS> extends SmallRenderS<BD, RC>
{
    public static IClientDaS ICLIENTDAS = new TPBaseClient();
    public static IBothDaSn IBOTHDASN = new TPBaseBothDaS();

    public static Map<Integer, Integer> TEXTURE_MAP = new HashMap();

    public RenderTPBase(RC rc, BD bd)
    {
        super(rc, bd);
    }

    public static void setTextureMap()
    {
        int diamond_block = com.nali.render.RenderHelper.getTextureBuffer(new ResourceLocation("textures/blocks/diamond_block.png"));
        TEXTURE_MAP.put(G_LIST.get(ICLIENTDAS.StartPart()).ebo, com.nali.render.RenderHelper.getTextureBuffer(new ResourceLocation("textures/blocks/obsidian.png")));
        TEXTURE_MAP.put(G_LIST.get(ICLIENTDAS.StartPart() + 1).ebo, diamond_block);
        TEXTURE_MAP.put(G_LIST.get(ICLIENTDAS.StartPart() + 2).ebo, diamond_block);
    }

    @Override
    public int getTextureBuffer(MemoG rg)
    {
        return TEXTURE_MAP.get(rg.ebo);
    }
}
