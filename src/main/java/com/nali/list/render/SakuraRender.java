package com.nali.list.render;

import com.nali.data.client.ClientData;
import com.nali.small.data.client.SakuraClient;
import com.nali.small.render.SmallRenderO;
import com.nali.system.opengl.memory.OpenGLObjectMemory;
import com.nali.system.opengl.memory.OpenGLObjectShaderMemory;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.Map;

import static com.nali.system.ClientLoader.OBJECT_LIST;
import static com.nali.system.opengl.memory.OpenGLCurrentMemory.OPENGL_FIXED_PIPE_FLOATBUFFER;

@SideOnly(Side.CLIENT)
public class SakuraRender extends SmallRenderO
{
//    public static int ID;
//    public static DataLoader DATALOADER = RenderHelper.DATALOADER;
    public static ClientData CLIENTDATA = new SakuraClient();
    public Map<Integer, Integer> color_map = new HashMap();//element_array_buffer hex
    public byte extra_bit;

    public SakuraRender()
    {
        super(null, CLIENTDATA/*, RenderHelper.DATALOADER*/);
//        float s = -5.0F;
//        this.objectscreendraw.sx = s;
//        this.objectscreendraw.sy = s;
//        this.objectscreendraw.sz = s;
        color_map.put(((OpenGLObjectMemory)OBJECT_LIST.get(CLIENTDATA.StartPart())).element_array_buffer, 0xFFFFACDF);//ffd4e9
//        color_map.put(((OpenGLObjectMemory)OBJECT_LIST.get(CLIENTDATA.StartPart() + 1)).element_array_buffer, 0xFFffacdf);
    }

    @Override
    public void setLightCoord(OpenGLObjectShaderMemory openglobjectshadermemory)
    {
    }

//    @Override
//    public void setLightMapUniform(OpenGLObjectShaderMemory openglobjectshadermemory)
//    {
//    }

    @Override
    public void setTextureUniform(OpenGLObjectMemory openglobjectmemory, OpenGLObjectShaderMemory openglobjectshadermemory)
    {
        Integer integer = this.color_map.get(openglobjectmemory.element_array_buffer);
        if (integer == null)
        {
            this.extra_bit = 0;
            super.setTextureUniform(openglobjectmemory, openglobjectshadermemory);
        }
        else
        {
            this.extra_bit = 4;
            int color = this.getTextureID(openglobjectmemory);
            OPENGL_FIXED_PIPE_FLOATBUFFER.limit(3);
            OPENGL_FIXED_PIPE_FLOATBUFFER.clear();
            OPENGL_FIXED_PIPE_FLOATBUFFER.put(((color >> 16) & 0xFF) / 255.0F);
            OPENGL_FIXED_PIPE_FLOATBUFFER.put(((color >> 8) & 0xFF) / 255.0F);
            OPENGL_FIXED_PIPE_FLOATBUFFER.put((color & 0xFF) / 255.0F);
            OPENGL_FIXED_PIPE_FLOATBUFFER.put(((color >> 24) & 0xFF) / 255.0F);
            OPENGL_FIXED_PIPE_FLOATBUFFER.flip();
            OpenGlHelper.glUniform4(openglobjectshadermemory.uniformlocation_int_array[4], OPENGL_FIXED_PIPE_FLOATBUFFER);
        }
    }

    @Override
    public int getTextureID(OpenGLObjectMemory openglobjectmemory)
    {
        Integer integer = this.color_map.get(openglobjectmemory.element_array_buffer);
        if (integer == null)
        {
            this.extra_bit = 0;
            return super.getTextureID(openglobjectmemory);
        }
        else
        {
            this.extra_bit = 4;
            return integer;
        }
    }

    @Override
    public byte getExtraBit(OpenGLObjectMemory openglobjectmemory)
    {
        return this.extra_bit;
    }
}
