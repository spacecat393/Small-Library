package com.nali.list.render;

import com.nali.data.client.ClientData;
import com.nali.mixin.IMixinEntityRenderer;
import com.nali.small.data.client.BoxClient;
import com.nali.small.render.SmallRenderO;
import com.nali.system.opengl.OpenGLBuffer;
import com.nali.system.opengl.memory.OpenGLObjectMemory;
import com.nali.system.opengl.memory.OpenGLObjectShaderMemory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL13;

import java.util.HashMap;
import java.util.Map;

import static com.nali.system.ClientLoader.OBJECT_LIST;
import static com.nali.system.opengl.memory.OpenGLCurrentMemory.OPENGL_FIXED_PIPE_FLOATBUFFER;

@SideOnly(Side.CLIENT)
public class BoxRender extends SmallRenderO
{
//    public static int ID;
//    public static DataLoader DATALOADER = RenderHelper.DATALOADER;
    public static ClientData CLIENTDATA = new BoxClient();
    public Map<Integer, Integer> color_map = new HashMap();//element_array_buffer hex
    public byte extra_bit;

    public BoxRender()
    {
        super(null, CLIENTDATA/*, RenderHelper.DATALOADER*/);
        color_map.put(((OpenGLObjectMemory)OBJECT_LIST.get(CLIENTDATA.StartPart() + 1)).element_array_buffer, 0xFFffc196);
        color_map.put(((OpenGLObjectMemory)OBJECT_LIST.get(CLIENTDATA.StartPart() + 2)).element_array_buffer, 0xFFffc196);
    }

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
    public void setLightMapUniform(OpenGLObjectShaderMemory openglobjectshadermemory)
    {
        if (this.extra_bit == 4)
        {
            OpenGlHelper.glUniform1i(openglobjectshadermemory.uniformlocation_int_array[5], 1);
            OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE1);
            OpenGLBuffer.setLightMapBuffer(((IMixinEntityRenderer) Minecraft.getMinecraft().entityRenderer).lightmapTexture().getGlTextureId());
        }
        else
        {
            super.setLightMapUniform(openglobjectshadermemory);
        }
    }

    @Override
    public byte getExtraBit(OpenGLObjectMemory openglobjectmemory)
    {
        return this.extra_bit;
    }
}
