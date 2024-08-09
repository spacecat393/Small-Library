package com.nali.list.render.o;

import com.nali.da.client.IClientDaO;
import com.nali.small.da.client.StorageClient;
import com.nali.small.render.SmallRenderO;
import com.nali.system.opengl.memo.client.MemoG;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.Map;

import static com.nali.system.ClientLoader.G_LIST;

@SideOnly(Side.CLIENT)
public class RenderStorage<RC extends IClientDaO> extends SmallRenderO<RC>
{
    public static IClientDaO ICLIENTDAO = new StorageClient();
    public static Map<Integer, Integer> TEXTURE_MAP = new HashMap();

    public RenderStorage(RC rc)
    {
        super(rc);
    }

    public static void setTextureMap()
    {
        TEXTURE_MAP.put((G_LIST.get(ICLIENTDAO.StartPart())).ebo, com.nali.render.RenderHelper.getTextureBuffer(new ResourceLocation("textures/blocks/obsidian.png")));
        TEXTURE_MAP.put((G_LIST.get(ICLIENTDAO.StartPart() + 1)).ebo, com.nali.render.RenderHelper.getTextureBuffer(new ResourceLocation("textures/blocks/diamond_block.png")));
    }

    @Override
    public int getTextureBuffer(MemoG rg)
    {
        return TEXTURE_MAP.get(rg.ebo);
    }
}