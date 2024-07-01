package com.nali.list.render.o;

import com.nali.data.client.IClientDaO;
import com.nali.small.data.client.StorageClient;
import com.nali.small.render.SmallRenderO;
import com.nali.system.opengl.memo.client.MemoGo;
import com.nali.system.opengl.memo.client.MemoSo;
import com.nali.system.opengl.memo.client.store.StoreO;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.Map;

import static com.nali.Nali.I;

@SideOnly(Side.CLIENT)
public class StorageRender<RG extends MemoGo, RS extends MemoSo, RC extends IClientDaO, RST extends StoreO<RG, RS>> extends SmallRenderO<RG, RS, RST, RC>
{
    public static IClientDaO ICLIENTDAO = new StorageClient();
    public static Map<Integer, Integer> TEXTURE_MAP = new HashMap();

    public StorageRender(RST rst, RC rc)
    {
        super(rst, rc);
    }

    public static void setTextureMap()
    {
        TEXTURE_MAP.put((I.clientloader.storeo.rg_list.get(ICLIENTDAO.StartPart())).element_array_buffer, com.nali.render.RenderHelper.getTextureBuffer(new ResourceLocation("textures/blocks/obsidian.png")));
        TEXTURE_MAP.put((I.clientloader.storeo.rg_list.get(ICLIENTDAO.StartPart() + 1)).element_array_buffer, com.nali.render.RenderHelper.getTextureBuffer(new ResourceLocation("textures/blocks/diamond_block.png")));
    }

    @Override
    public int getTextureBuffer(RG rg)
    {
        return TEXTURE_MAP.get(rg.element_array_buffer);
    }
}