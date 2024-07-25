package com.nali.list.render.o;

import com.nali.da.client.IClientDaO;
import com.nali.small.da.client.SakuraClient;
import com.nali.small.render.SmallRenderO;
import com.nali.system.opengl.memo.client.MemoG;
import com.nali.system.opengl.memo.client.MemoS;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.Map;

import static com.nali.system.ClientLoader.G_LIST;
import static com.nali.system.opengl.memo.client.MemoC.OPENGL_FIXED_PIPE_FLOATBUFFER;

@SideOnly(Side.CLIENT)
public class RenderSakura<RC extends IClientDaO> extends SmallRenderO<RC>
{
//    public static int ID;
//    public static DataLoader DATALOADER = RenderHelper.DATALOADER;
    public static IClientDaO ICLIENTDAO = new SakuraClient();
    public Map<Integer, Integer> color_map = new HashMap();//element_array_buffer hex
    public byte extra_bit;

    public RenderSakura(RC rc)
    {
        super(rc);
//        float s = -5.0F;
//        this.objectscreendraw.sx = s;
//        this.objectscreendraw.sy = s;
//        this.objectscreendraw.sz = s;
        color_map.put((G_LIST.get(ICLIENTDAO.StartPart())).element_array_buffer, 0xFFFFACDF);//ffd4e9
//        color_map.put(((OpenGLObjectMemory)OBJECT_LIST.get(CLIENTDATA.StartPart() + 1)).element_array_buffer, 0xFFffacdf);
    }

    @Override
    public void setLightCoord(MemoS rs)
    {
    }

//    @Override
//    public void setLightMapUniform(OpenGLObjectShaderMemory openglobjectshadermemory)
//    {
//    }

    @Override
    public void setTextureUniform(MemoG rg, MemoS rs)
    {
        Integer integer = this.color_map.get(rg.element_array_buffer);
        if (integer == null)
        {
            this.extra_bit = 0;
            super.setTextureUniform(rg, rs);
        }
        else
        {
            this.extra_bit = 4;
            int color = this.getTextureID(rg);
            OPENGL_FIXED_PIPE_FLOATBUFFER.limit(3);
            OPENGL_FIXED_PIPE_FLOATBUFFER.clear();
            OPENGL_FIXED_PIPE_FLOATBUFFER.put(((color >> 16) & 0xFF) / 255.0F);
            OPENGL_FIXED_PIPE_FLOATBUFFER.put(((color >> 8) & 0xFF) / 255.0F);
            OPENGL_FIXED_PIPE_FLOATBUFFER.put((color & 0xFF) / 255.0F);
            OPENGL_FIXED_PIPE_FLOATBUFFER.put(((color >> 24) & 0xFF) / 255.0F);
            OPENGL_FIXED_PIPE_FLOATBUFFER.flip();
            OpenGlHelper.glUniform4(rs.uniformlocation_int_array[4], OPENGL_FIXED_PIPE_FLOATBUFFER);
        }
    }

    @Override
    public int getTextureID(MemoG rg)
    {
        Integer integer = this.color_map.get(rg.element_array_buffer);
        if (integer == null)
        {
            this.extra_bit = 0;
            return super.getTextureID(rg);
        }
        else
        {
            this.extra_bit = 4;
            return integer;
        }
    }

    @Override
    public byte getExtraBit(MemoG rg)
    {
        return this.extra_bit;
    }
}
