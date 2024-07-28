package com.nali.small.gui.page;

import com.nali.list.data.SmallData;
import com.nali.render.RenderO;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.client.ClientE;
import com.nali.small.gui.mouse.MouseArmy;
import com.nali.system.opengl.memo.client.MemoS;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.*;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.*;

import static com.nali.list.container.gui.SmallGui.*;
import static com.nali.small.entity.memo.client.ClientE.C_MAP;
import static com.nali.small.gui.mouse.MouseArmy.Y;
import static com.nali.small.gui.mouse.MouseArmy.Y_STAR;
import static com.nali.system.ClientLoader.S_LIST;
import static com.nali.system.opengl.memo.client.MemoA1.genBuffer;
import static com.nali.system.opengl.memo.client.MemoC.*;

@SideOnly(Side.CLIENT)
public class PageArmy extends Page
{
    public static List<Integer>
    TEXTURE_INTEGER_LIST = new ArrayList(),
    ARRAY_BUFFER_INTEGER_LIST = new ArrayList();
    public static int SIZE,
    OFFSET_CUTOFF_ARRAY_BUFFER = -1;
//    public static float i;

    public static float MAX_Y, MAX_Y_STAR;
//    public static byte[] MOVE_BYTE_ARRAY;
    public static byte PAGE;

    public float[][] vec2_2d_float_array;
    public float[][] color_vec4_2d_float_array;

    public PageArmy(byte page)
    {
        PAGE = page;
        this.vec2_2d_float_array = new float[4][2];
        this.color_vec4_2d_float_array = new float[2][4];
        this.color_vec4_2d_float_array[0] = new float[]{1.0F, 1.0F, 1.0F, 1.0F};

        this.color_vec4_2d_float_array[1] = new float[]{1.0F/255.0F, PAGE/255.0F, 0.0F, 1.0F};
    }

    @Override
    public void init()
    {
        super.init();
//        NetworkRegistry.I.sendToServer(new ServerMessage(new byte[]{SSToC.ID}));
        this.initC();
    }

//    @Override
//    public void preDraw()
//    {
//        if (SIZE != C_MAP.size())
//        {
//            SMALLGUI.state |= 8;
//        }
////        super.preDraw();
//    }

    @Override
    public void draw()
    {
        if (SIZE != C_MAP.size())
        {
            FLAG |= 1;
//            SMALLGUI.state |= 8;
        }
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
            Minecraft minecraft = SMALLGUI.mc;
            int
            display_width = minecraft.displayWidth,
            display_height = minecraft.displayHeight;
            float offset = H + 4.0F * 0.005F * display_height;
            int h = (int)(display_height - offset);

            MemoS rs = S_LIST.get(SmallData.SHADER_STEP + 3);
            OpenGlHelper.glUseProgram(rs.program);
            int v = rs.attriblocation_int_array[0];
            GL20.glEnableVertexAttribArray(v);

            int size = ARRAY_BUFFER_INTEGER_LIST.size();

            int index = ARRAY_BUFFER_INTEGER_LIST.size() - 1;
            this.drawQuadVUv(rs, this.vec2_2d_float_array[1], this.color_vec4_2d_float_array[0], ARRAY_BUFFER_INTEGER_LIST.get(index), TEXTURE_INTEGER_LIST.get(index));

//            if (y - < display_height)
//            {
//                break;
//            }

            float w = LEFT + H + 6.0F * 0.005F * display_width;
            OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, OFFSET_FRAMEBUFFER);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, OFFSET_FRAMEBUFFER_TEXTURE);
//            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, display_width, h, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (IntBuffer)null);
            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, (int)(display_width - w), h, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (IntBuffer)null);
            OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, OFFSET_FRAMEBUFFER_TEXTURE, 0);
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
//            OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, OFFSET_CUTOFF_FRAMEBUFFER);

//            int y = (int)(-H - 4.0F * 0.005F * display_height), h = (int)(display_height - H - 4.0F * 0.005F * display_height);
//            GL11.glViewport(0, (int)(-offset), display_width, (int)(display_height - offset));
//            GL11.glViewport(0, 0, display_width, h);
//            GL11.glViewport(0, 0, display_width, display_height);
//            GL11.glViewport(0, y, display_width, h);
//            GL11.glViewport(0, H, display_width, display_height - H);
//            GL11.glMatrixMode(GL11.GL_PROJECTION);
//            GL11.glOrtho(0, display_width, h, y, 1000.0D, 3000.0D);
//            GL11.glLoadIdentity();
//            GL11.glOrtho(0, display_width, h, y, 1000.0D, 3000.0D);
//            GL11.glOrtho(0, display_width, -0.5D, 0.5D, 1000.0D, 3000.0D);

//            int debug = 0;
            float scale = 75.0F / ((float)SMALLGUI.height / display_height),
            h_offset = H + 4.0F * 0.005F * display_height,
//            y = MouseArmy.Y / 2.0F * display_height + (display_height - (h_offset * 2.0F)),
            py = display_height - (h_offset * 2.0F),
            y0 = scale + 2.0F * 0.005F * display_height,
            y = (MouseArmy.Y / 2.0F * display_height + py) / y0;
            float max_y = y + 1.0F, min_y = (y - py / y0 - 1.0F), id;

            this.vec2_2d_float_array[0][1] = Y;
            for (int i = 0; i < size - 3; ++i)
            {
                id = i / 7.0F;
//                float y1 = y0 * (i - 1),
//                y2 = y0 * /*(*/i/* + 3)*/;
//
//                if (y >= y1 && y <= y2)
                if (id >= min_y && id <= max_y)
                {
                    this.drawQuadVUv(rs, this.vec2_2d_float_array[0], this.color_vec4_2d_float_array[0], ARRAY_BUFFER_INTEGER_LIST.get(i), TEXTURE_INTEGER_LIST.get(i));
//                    ++debug;
                }
            }
//            Nali.LOGGER.info("debug " + debug);
//            Nali.LOGGER.info("max " + (size-3));

//            this.drawQuadVUv(rs, this.vec2_2d_float_array[1], this.color_vec4_2d_float_array[0], ARRAY_BUFFER_INTEGER_LIST.get(size - 3), TEXTURE_INTEGER_LIST.get(size - 3));
//            this.vec2_2d_float_array[2][1] = -Y_STAR;
//            this.drawQuadVUv(rs, this.vec2_2d_float_array[2], this.color_vec4_2d_float_array[0], ARRAY_BUFFER_INTEGER_LIST.get(size - 2), TEXTURE_INTEGER_LIST.get(size - 2));

//            GL11.glViewport(0, 0, display_width, h);
//            GL11.glViewport(0, 0, (int)(display_width + (LEFT + H + 6.0F * 0.005F * display_width)), h);
            GL11.glViewport(0, 0, (int)(display_width - w), h);
//            GL11.glViewport(0, 0, display_width, (int)(display_height + offset));
//            GL11.glViewport(0, 0, display_width, display_height);

            OFFSET_CUTOFF_ARRAY_BUFFER = genBuffer(createFloatByteBuffer(this.createQuadVUv(0, 0, minecraft.displayWidth, minecraft.displayHeight, minecraft.displayWidth, minecraft.displayHeight/*, 1.0F*//* / (this.mc.displayWidth / (float)this.width)*//*, 1.0F*/)/*, true*/));

            OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, GL_READ_FRAMEBUFFER_BINDING);
            OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, GL_DRAW_FRAMEBUFFER_BINDING);
//            OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, OFFSET_CUTOFF_FRAMEBUFFER);
//            GL11.glBindTexture(GL11.GL_TEXTURE_2D, OFFSET_CUTOFF_FRAMEBUFFER_TEXTURE);
//            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, display_width, h, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (IntBuffer)null);
//            OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, OFFSET_CUTOFF_FRAMEBUFFER_TEXTURE, 0);
            this.vec2_2d_float_array[3][1] = (H * 2.0F - 4.0F * 0.005F * display_height) / display_height;
            this.drawQuadVUv(rs, this.vec2_2d_float_array[3], this.color_vec4_2d_float_array[0], OFFSET_CUTOFF_ARRAY_BUFFER, OFFSET_FRAMEBUFFER_TEXTURE);

            GL11.glViewport(0, 0, display_width, display_height);

            index = ARRAY_BUFFER_INTEGER_LIST.size() - 3;
            this.drawQuadVUv(rs, this.vec2_2d_float_array[1], this.color_vec4_2d_float_array[0], ARRAY_BUFFER_INTEGER_LIST.get(index), TEXTURE_INTEGER_LIST.get(index));
            this.vec2_2d_float_array[2][1] = -Y_STAR;
            index = ARRAY_BUFFER_INTEGER_LIST.size() - 2;
            this.drawQuadVUv(rs, this.vec2_2d_float_array[2], this.color_vec4_2d_float_array[0], ARRAY_BUFFER_INTEGER_LIST.get(index), TEXTURE_INTEGER_LIST.get(index));

            GL20.glDisableVertexAttribArray(v);

//            GL11.glLoadMatrix(OPENGL_PROJECTION_MATRIX_FLOATBUFFER);
//            GL11.glMatrixMode(GL_MATRIX_MODE);

//            OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, GL_READ_FRAMEBUFFER_BINDING);
//            OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, GL_DRAW_FRAMEBUFFER_BINDING);

//            GL11.glViewport(0, 0, display_width, display_height);

            OpenGlHelper.glDeleteBuffers(OFFSET_CUTOFF_ARRAY_BUFFER);
        }

//        if ((SMALLGUI.state & 4) == 4)
//        {
//            SMALLGUI.state &= 255-4;
//            SMALLGUI.defaultPage();
//        }
    }

    @Override
    public void detect()
    {
        MemoS rs = S_LIST.get(SmallData.SHADER_STEP + 4);

        OpenGlHelper.glUseProgram(rs.program);
        int v = rs.attriblocation_int_array[0];
        GL20.glEnableVertexAttribArray(v);

        int index = ARRAY_BUFFER_INTEGER_LIST.size() - 3;
        this.drawQuadVUv(rs, this.vec2_2d_float_array[1], this.color_vec4_2d_float_array[1], ARRAY_BUFFER_INTEGER_LIST.get(index), TEXTURE_INTEGER_LIST.get(index));

        GL20.glDisableVertexAttribArray(v);
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
//        height = (int)(scale / (SMALLGUI.height / (float)display_height)),
        id = 0;
        float
        h2 = 2.0F * 0.005F * display_height,
        h_offset_y = H + 4.0F * 0.005F * display_height,
        height_f = scale / (SMALLGUI.height / (float)display_height),
        h_offset = H/* + h2*/ - h2,
        x = LEFT + H + 6.0F * 0.005F * display_width,
//        x = -(LEFT + H + 6.0F * 0.005F * display_width),
//        x = 0,
        x_offset = x + scale / (SMALLGUI.width / (float)display_width) + 2.0F * 0.005F * display_width,
        y = display_height - scale / (SMALLGUI.height / (float)display_height) - 4.0F * 0.005F * display_height - H - h_offset/* * 2.0F*//* - (8.0F * 0.005F * display_height) * 2.0F*/,
//        int max_l = (int)(display_width - y);
        h_offset_y2 = h_offset_y * 2.0F;

        MAX_Y = (((height_f + h2) * C_MAP.size() - (display_height - h_offset_y2)) / display_height) * 2.0F;
//        MAX_Y_STAR = ((display_height - h_offset_y - (H * 2.0F)/* + *//*h_offset*//*(h_offset * 2.0F)*/) / /*(*/display_height/* - h_offset * 2.0F)*/) * 2.0F;
        MAX_Y_STAR = ((display_height/* - h_offset_y*/ - h_offset_y2 - H) / display_height) * 2.0F;
//        MAX_Y_STAR = -WO / (SMALLGUI.width / (float)display_width) * 2.0F;

        int height = (int)height_f;

        SIZE = C_MAP.size();
        Set<UUID> keys_set = new HashSet(C_MAP.keySet());
        for (UUID uuid : keys_set)
        {
            ClientE c = C_MAP.get(uuid);
            if (c != null)
            {
    //            this.preDrawModel(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, c, -1, (int)(25 / (SMALLGUI.width / (float)display_width)), (int)(25 / (SMALLGUI.height / (float)display_height)), LEFT + H + 4.0F * 0.005F * display_width/* + 25.0F / (SMALLGUI.width / (float)display_width)*/, display_height - H * 2.0F - h2/* - 25.0F / (SMALLGUI.height / (float)display_height)*/, -25.0F/* / (SMALLGUI.height / (float)display_height)*/);
//                this.preDrawTextHorizontal(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, string, l, text_height, x, y, text_scale);
                ByteBuffer bytebuffer = ByteBuffer.allocateDirect(4).order(ByteOrder.nativeOrder());
                bytebuffer.put((byte)0);
                bytebuffer.put((byte)0);
                bytebuffer.put((byte)0);
                bytebuffer.put((byte)(255/4.0F));
                bytebuffer.flip();
//                this.createBox(x, y, (int)(width + display_width / 2.0F), (int)(height / 2.0F), bytebuffer);
                this.createBox(x, y, (int)(display_width - LEFT - H - 8.0F * 0.005F * display_width - x), (int)(height / 2.0F), bytebuffer);

                this.preDrawModel(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, c/*, -1*/, width, height, x, y, -scale);

                IMixE i = c.i;
                String statp_string = "";
                if ((c.state & 1) == 1)
                {
                    statp_string = " " + STRING_ARRAY[25];
                }

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

                    String string = id + " " + e.getName() + " ";
                    int l = (int)(minecraft.fontRenderer.getStringWidth(string) * SCALE);
                    this.preDrawTextHorizontal(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, string, l, H, x_offset, y + text_height * 2.0F + 4.0F * 0.005F * display_width, SCALE);

                    string = STRING_ARRAY[26];
//                    l = (int)(minecraft.fontRenderer.getStringWidth(string) * text_scale);
                    this.preDrawTextHorizontal(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, string, (int)(minecraft.fontRenderer.getStringWidth(string) * text_scale), text_height, x_offset + l + 2.0F * 0.005F * display_width, y + text_height * 2.0F + 4.0F * 0.005F * display_width, text_scale);

                    string = stat_string + statp_string;
                    l = (int)(minecraft.fontRenderer.getStringWidth(string) * SCALE);
                    this.preDrawTextHorizontal(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, string, l, H, x, y + height - H, SCALE);

//                    string = DimensionManager.getProviderType(e.dimension).getName();
//                    string = DimensionManager.getProviderType(e.world.getWorldType().getId()).getName();
                    string = e.world.provider.getDimensionType().getName();
                    l = (int)(minecraft.fontRenderer.getStringWidth(string) * text_scale);
                    this.preDrawTextHorizontal(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, string, l, text_height, x_offset, y + text_height + 2.0F * 0.005F * display_width, text_scale);

                    string = "X " + String.format("%.4f", e.posX) + " Y " + String.format("%.4f", e.posY) + " Z " + String.format("%.4f", e.posZ);
                    l = (int)(minecraft.fontRenderer.getStringWidth(string) * text_scale);
                    this.preDrawTextHorizontal(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, string, l, text_height, x_offset, y, text_scale);
                }
//                this.preDrawModel(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, c/*, -1*/, width, height, x, y, -scale);
    //            x += ;
                y -= scale / (SMALLGUI.height / (float)display_height) + h2;
                ++id;
            }
//            HO = y;
        }

        float ye = display_height - (H/* * 2.0F*/ + 4.0F * 0.005F * display_height) * 2.0F/*, ys = 0*/;
        StringBuilder stringbuilder = new StringBuilder();
//        stringbuilder.append("|");
//        while (ys < ye)
//        {
//            stringbuilder.append("|");
////            ys += MAX_TH * SCALE;
//        }
        int max_l = (int)(Math.ceil(ye / (MAX_TH * SCALE)))/* + 1*/;
        for (int i = 0; i < max_l; ++i)
        {
            stringbuilder.append("|");
        }
//        String string = stringbuilder.toString();

//        int l = (int)(MAX_TH * string.length() * SCALE),
//        int box_height = (int)(ys - display_height);
//        if (ys < display_height)
//        {
//            box_height = (int)ys;
//        }
//        this.preDrawTextVertical(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, stringbuilder.toString(), H, (int)ys, display_width - H - RIGHT - 6.0F * 0.005F * display_width, display_height - H - 4.0F * 0.005F * display_height, SCALE);
//        this.preDrawTextVertical(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, stringbuilder.toString(), H, (int)ye, display_width - LEFT - H - 6.0F * 0.005F * display_width, display_height - H - 4.0F * 0.005F * display_height + h_offset, SCALE);
        WO = max_l * MAX_TH * SCALE;
        this.preDrawTextVertical(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, stringbuilder.toString(), H, (int)WO, display_width - LEFT - H - 6.0F * 0.005F * display_width, display_height - H - 4.0F * 0.005F * display_height/* + h_offset*/, SCALE);
//        this.preDrawTextVertical(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, stringbuilder.toString(), H, box_height, 0, display_height / 2.0F, SCALE);
//        this.preDrawTextVertical(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, STRING_ARRAY[7], H, H, display_width - LEFT - H - 6.0F * 0.005F * display_width, display_height - H/* * 3.0F */- 4.0F * 0.005F * display_height - h_offset/* + h_offset*/, SCALE);
        this.preDrawTextVertical(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, STRING_ARRAY[7], H, H, display_width - LEFT - H - 6.0F * 0.005F * display_width, display_height - H - 4.0F * 0.005F * display_height/* - H*/, SCALE);

        //search
        String string = "________" + STRING_ARRAY[28];
        int l = (int)(minecraft.fontRenderer.getStringWidth(string) * SCALE);
        this.preDrawTextHorizontal(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, string, l, H, display_width - l - 2.0F * 0.005F * display_width, display_height - H - h2, SCALE);
    }

    public void preDrawModel(List<Integer> array_buffer_integer_list, List<Integer> texture_integer_list, ClientE c/*, int texture_index*/, int width, int height, float x, float y, float scale)
    {
//        int render_buffer = OpenGlHelper.glGenRenderbuffers();
//        OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, render_buffer);

        Minecraft minecraft = SMALLGUI.mc;
        array_buffer_integer_list.add(genBuffer(createFloatByteBuffer(this.createQuadVUv(x, y, width + x, height + y, minecraft.displayWidth, minecraft.displayHeight/*, 1.0F, 1.0F*/)/*, true*/)));
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
        OpenGlHelper.glFramebufferRenderbuffer(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, OpenGlHelper.GL_RENDERBUFFER, 0);
        OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, 0);

//        OpenGlHelper.glDeleteRenderbuffers(render_buffer);
    }

    public void createBox(float x, float y, int width, int height, ByteBuffer bytebuffer)
    {
        Minecraft minecraft = SMALLGUI.mc;
        int texture = GL11.glGenTextures();
        ARRAY_BUFFER_INTEGER_LIST.add(genBuffer(createFloatByteBuffer(this.createQuadVUv(x, y, width + x, height + y, minecraft.displayWidth, minecraft.displayHeight))));
        TEXTURE_INTEGER_LIST.add(texture);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);

//        ByteBuffer bytebuffer = BufferUtils.createByteBuffer(4);
//        bytebuffer.put((byte)0);
//        bytebuffer.put((byte)0);
//        bytebuffer.put((byte)0);
//        bytebuffer.put((byte)(255/2.0F));
//        bytebuffer.flip();

        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, 1, 1, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, bytebuffer);
        GL11.glViewport(0, 0, width, height);
        OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, texture, 0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, GL_TEXTURE_BINDING_2D_0);
        OpenGlHelper.setActiveTexture(GL_ACTIVE_TEXTURE);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, GL_TEXTURE_BINDING_2D);
    }
}
