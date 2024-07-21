package com.nali.small.gui.page;

import com.nali.Nali;
import com.nali.list.data.SmallData;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.server.SSToC;
import com.nali.network.NetworkRegistry;
import com.nali.render.RenderO;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.client.ClientE;
import com.nali.system.opengl.OpenGLBuffer;
import com.nali.system.opengl.memo.client.MemoSo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL20;

import java.nio.IntBuffer;
import java.util.*;

import static com.nali.list.container.gui.SmallGui.*;
import static com.nali.small.entity.memo.client.ClientE.C_MAP;
import static com.nali.system.opengl.memo.client.MemoCurrent.*;

@SideOnly(Side.CLIENT)
public class PageArmy extends Page
{
    public static List<Integer>
    TEXTURE_INTEGER_LIST = new ArrayList(),
    ARRAY_BUFFER_INTEGER_LIST = new ArrayList();
    public static int SIZE;
//    public static float i;

    public float[][] vec2_2d_float_array;
    public float[][] color_vec4_2d_float_array;

    public PageArmy()
    {
        this.vec2_2d_float_array = new float[1][2];
        this.color_vec4_2d_float_array = new float[1][4];
        this.color_vec4_2d_float_array[0] = new float[]{1.0F, 1.0F, 1.0F, 1.0F};
    }

    @Override
    public void init()
    {
        super.init();
        NetworkRegistry.I.sendToServer(new ServerMessage(new byte[]{SSToC.ID}));
        this.initC();
    }

    @Override
    public void preDraw()
    {
        if (SIZE != C_MAP.size())
        {
            SMALLGUI.state |= 8;
        }
//        super.preDraw();
    }

    @Override
    public void draw()
    {
//        Set<UUID> keys_set = new HashSet(C_MAP.keySet());
////        GL20.glDisableVertexAttribArray(0);
//        for (UUID uuid : keys_set)
//        {
//            GL11.glMatrixMode(GL11.GL_PROJECTION);
//            GL11.glLoadIdentity();
//            GL11.glOrtho(0.0D, SMALLGUI.mc.displayWidth, SMALLGUI.mc.displayHeight, 0.0D, 1000.0D, 3000.0D);
//
//            GL11.glMatrixMode(GL11.GL_MODELVIEW);
//            ClientE c = C_MAP.get(uuid);
//            GL11.glPushMatrix();
////            GL11.glTranslatef(0.0F, 0.0F, 0.0F);
////            GL11.glTranslatef(SMALLGUI.getGuiLeft(), SMALLGUI.getGuiTop(), 50.0F);
////            GL11.glTranslatef(SMALLGUI.getGuiLeft(), SMALLGUI.getGuiTop(), 0.0F);
//            GL11.glTranslatef(200.0F, 200.0F, 200.0F);
//            GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
//            GL11.glScalef(-100.0F, 100.0F, 100.0F);//-
////            GL11.glScalef(25.0F, 25.0F, 25.0F);
////            GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
////            GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
////            GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
////            GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
//            //c.doRender();//init skinning?
//            RenderO r = c.r;
//            r.lig_b = -1.0F;
//            r.draw();//blend program -texture
//            GL11.glPopMatrix();
////            Nali.I.logger.info("C!");
//
//            GL11.glMatrixMode(GL11.GL_PROJECTION);
//            GL11.glLoadIdentity();
//            break;
//        }
////        GL20.glEnableVertexAttribArray(0);

        if (SIZE > 0 && SIZE == C_MAP.size())
        {
            MemoSo rs = Nali.I.clientloader.storeo.rs_list.get(SmallData.SHADER_O_STEP + 3);
            OpenGlHelper.glUseProgram(rs.program);
            GL20.glEnableVertexAttribArray(0);

            for (int i = 0; i < ARRAY_BUFFER_INTEGER_LIST.size(); ++i)
            {
                this.drawQuadVUv(rs, this.vec2_2d_float_array[0], this.color_vec4_2d_float_array[0], ARRAY_BUFFER_INTEGER_LIST.get(i), TEXTURE_INTEGER_LIST.get(i));
            }

            GL20.glDisableVertexAttribArray(0);
        }

        if ((SMALLGUI.state & 4) == 4)
        {
            SMALLGUI.state &= 255-4;
            SMALLGUI.defaultPage();
        }
    }

    @Override
    public void detect()
    {

    }

//    @Override
//    public void change()
//    {
//
//    }

    @Override
    public List<Integer> getArrayBufferIntegerList()
    {
        return ARRAY_BUFFER_INTEGER_LIST;
    }

    @Override
    public List<Integer> getTextureIntegerList()
    {
        return TEXTURE_INTEGER_LIST;
    }

    public void initC()
    {
        Minecraft minecraft = SMALLGUI.mc;
        float scale = 75.0F, text_scale = SCALE / 2.0F;
        int
        text_height = (int)(MAX_TH * text_scale),
        display_width = minecraft.displayWidth,
        display_height = minecraft.displayHeight,
        width = (int)(scale / (SMALLGUI.width / (float)display_width)),
        height = (int)(scale / (SMALLGUI.height / (float)display_height)),
        id = 0;
        float
        x = LEFT + H + 6.0F * 0.005F * display_width,
        x_offset = x + scale / (SMALLGUI.width / (float)display_width) + 2.0F * 0.005F * display_width,
        y = display_height - scale / (SMALLGUI.height / (float)display_height) - 4.0F * 0.005F * display_height - H;
//        int max_l = (int)(display_width - y);

        SIZE = C_MAP.size();
        Set<UUID> keys_set = new HashSet(C_MAP.keySet());
        for (UUID uuid : keys_set)
        {
//            if (y - < display_height)
//            {
//                break;
//            }

            ClientE c = C_MAP.get(uuid);
            if (c != null)
            {
    //            this.preDrawModel(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, c, -1, (int)(25 / (SMALLGUI.width / (float)display_width)), (int)(25 / (SMALLGUI.height / (float)display_height)), LEFT + H + 4.0F * 0.005F * display_width/* + 25.0F / (SMALLGUI.width / (float)display_width)*/, display_height - H * 2.0F - 2.0F * 0.005F * display_height/* - 25.0F / (SMALLGUI.height / (float)display_height)*/, -25.0F/* / (SMALLGUI.height / (float)display_height)*/);
                IMixE i = c.i;
                if (i == null)
                {
//                    DimensionManager.getProviderType(c.dimension).getName();
                }
                else
                {
                    Entity e = i.getE();
                    String stat_string;
                    if (e.isEntityAlive())
                    {
                        if (false)//negative
                        {
                            stat_string = STRING_ARRAY[21];
                        }
                        else if (false)//teamup
                        {
                            stat_string = STRING_ARRAY[17];
                        }
                        else
                        {
                            stat_string = STRING_ARRAY[22];
                        }
                    }
                    else
                    {
                        stat_string = STRING_ARRAY[20];
                    }
                    String string = id + " " + e.getName() + " " + stat_string;
                    int l = (int)(minecraft.fontRenderer.getStringWidth(string) * SCALE);
                    this.preDrawTextHorizontal(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, string, l, H, x_offset, y + text_height * 2.0F + 4.0F * 0.005F * display_width, SCALE);

                    string = DimensionManager.getProviderType(c.dimension).getName();
                    l = (int)(minecraft.fontRenderer.getStringWidth(string) * text_scale);
                    this.preDrawTextHorizontal(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, string, l, text_height, x_offset, y + text_height + 2.0F * 0.005F * display_width, text_scale);

                    string = "X " + String.format("%.4f", e.posX) + " Y " + String.format("%.4f", e.posY) + " Z " + String.format("%.4f", e.posZ);
                    l = (int)(minecraft.fontRenderer.getStringWidth(string) * text_scale);
                    this.preDrawTextHorizontal(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, string, l, text_height, x_offset, y, text_scale);
                }
                this.preDrawModel(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, c/*, -1*/, width, height, x, y, -scale);
    //            x += ;
                y -= scale / (SMALLGUI.height / (float)display_height) + 2.0F * 0.005F * display_height;
                ++id;
            }
        }
    }

    public void preDrawModel(List<Integer> array_buffer_integer_list, List<Integer> texture_integer_list, ClientE c/*, int texture_index*/, int width, int height, float x, float y, float scale)
    {
//        int render_buffer = OpenGlHelper.glGenRenderbuffers();
//        OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, render_buffer);

        Minecraft minecraft = SMALLGUI.mc;
        array_buffer_integer_list.add(OpenGLBuffer.loadFloatBuffer(OpenGLBuffer.createFloatByteBuffer(this.createQuadVUv(x, y, width + x, height + y, minecraft.displayWidth, minecraft.displayHeight/*, 1.0F, 1.0F*/), true)));
        OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, GL_ARRAY_BUFFER_BINDING);

        int texture = GL11.glGenTextures();
//        if (texture_index == -1)
//        {
        texture_integer_list.add(texture);
//        }
//        else
//        {
//            GL11.glDeleteTextures(texture_integer_list.get(texture_index));
//            texture_integer_list.set(texture_index, texture);
//        }

        GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
        GL_TEXTURE_BINDING_2D = OPENGL_INTBUFFER.get(0);

        GL11.glGetInteger(GL13.GL_ACTIVE_TEXTURE, OPENGL_INTBUFFER);
        GL_ACTIVE_TEXTURE = OPENGL_INTBUFFER.get(0);
        OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
        GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
        GL_TEXTURE_BINDING_2D_0 = OPENGL_INTBUFFER.get(0);

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (IntBuffer)null);
        GL11.glViewport(0, 0, width, height);
        OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, texture, 0);

        OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, OFFSET_RENDER_BUFFER);
//        OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, render_buffer);
        OpenGlHelper.glRenderbufferStorage(OpenGlHelper.GL_RENDERBUFFER, GL14.GL_DEPTH_COMPONENT24, width, height);
        OpenGlHelper.glFramebufferRenderbuffer(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, OpenGlHelper.GL_RENDERBUFFER, OFFSET_RENDER_BUFFER);
//        OpenGlHelper.glFramebufferRenderbuffer(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, OpenGlHelper.GL_RENDERBUFFER, render_buffer);

        GL11.glClear(/*GL11.GL_COLOR_BUFFER_BIT | */GL11.GL_DEPTH_BUFFER_BIT);

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, GL_TEXTURE_BINDING_2D_0);

        OpenGlHelper.setActiveTexture(GL_ACTIVE_TEXTURE);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, GL_TEXTURE_BINDING_2D);

        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0.0D, width, height, 0.0D, 1000.0D, 3000.0D);

//        GL11.glGetInteger(GL11.GL_CULL_FACE_MODE, OPENGL_INTBUFFER);
//        int gl_cull_face_mode = OPENGL_INTBUFFER.get(0);
//        GL11.glCullFace(GL11.GL_FRONT);
//        GL11.glFrontFace(GL11.GL_CW);
//        GL11.glCullFace(GL11.GL_BACK);
//        GL11.glEnable(GL11.GL_DEPTH_TEST);
//        GL11.glDepthFunc(GL11.GL_ALWAYS);

        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glPushMatrix();
        float p = -scale / 2.0F;
        float fx = (SMALLGUI.width / (float)minecraft.displayWidth);
        float fy = (SMALLGUI.height / (float)minecraft.displayHeight);
        GL11.glTranslatef(p / fx, (p - (scale / 4.0F)) / fy, /*-scale * 4.0F*/0);
//        GL11.glTranslatef(SMALLGUI.getGuiLeft(), SMALLGUI.getGuiTop(), 50.0F);
//            GL11.glTranslatef(SMALLGUI.getGuiLeft(), SMALLGUI.getGuiTop(), 0.0F);
//        GL11.glScalef(scale, scale, scale);
        GL11.glScalef((scale - (scale / 2.5F)) / fx, (scale - (scale / 2.5F)) / fy, scale);
//            GL11.glScalef(25.0F, 25.0F, 25.0F);
        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
//        GL11.glRotatef(i, 0.0F, 0.0F, 1.0F);
        RenderO r = c.r;
        r.lig_b = -1.0F;
        c.initFakeFrame();
        r.draw();//blend program -texture
        //draw to framebuffer need correct projection
        GL11.glPopMatrix();

//        GL11.glCullFace(gl_cull_face_mode);

//        GL11.glClear(/*GL11.GL_COLOR_BUFFER_BIT | */GL11.GL_DEPTH_BUFFER_BIT);
        OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, 0);

//        OpenGlHelper.glDeleteRenderbuffers(render_buffer);
    }
}
