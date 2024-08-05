package com.nali.small.gui.page;

import com.nali.list.data.SmallData;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.client.ClientE;
import com.nali.small.gui.key.KeyMenuArmy;
import com.nali.small.gui.mouse.MouseArmy;
import com.nali.system.opengl.memo.client.MemoS;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.*;

import static com.nali.list.container.gui.SmallGui.*;
import static com.nali.small.entity.memo.client.ClientE.C_MAP;
import static com.nali.small.gui.key.Key.KEY;
import static com.nali.small.gui.mouse.Mouse.MOUSE;
import static com.nali.small.gui.mouse.MouseArmy.Y;
import static com.nali.small.gui.mouse.MouseArmy.Y_STAR;
import static com.nali.small.gui.page.PageSakura.LEFT;
import static com.nali.small.gui.page.PageText.SEARCH_L;
import static com.nali.small.gui.page.PageText.STRINGBUFFER;
import static com.nali.system.ClientLoader.S_LIST;

@SideOnly(Side.CLIENT)
public class PageArmy extends Page
{
    public static List<Integer>
    TEXTURE_INTEGER_LIST = new ArrayList(),
    ARRAY_BUFFER_INTEGER_LIST = new ArrayList();
    public static int SIZE,
//    OFFSET_CUTOFF_ARRAY_BUFFER = -1;
//    public static float i;

    A_W, A_H;
    public static float MAX_Y, MAX_Y_OFFSET, MAX_Y_STAR;
    public static byte[] MOVE_BYTE_ARRAY;
//    public static byte[] ALL_MOVE_BYTE_ARRAY;
//    public static byte[] FINAL_MOVE_BYTE_ARRAY;
    public static List<Integer> INDEX_INTEGER_LIST = new ArrayList();
//    public static String[][] STRING_2D_ARRAY;
    public static UUID[] UUID_ARRAY;
//    public static UUID[] ALL_UUID_ARRAY;
//    public static UUID[] FINAL_UUID_ARRAY;
    public static byte PAGE, STATE,
    BYTE = 1;//init lock
    public static String STRING;

    public float[][] vec2_2d_float_array;
    public float[][] color_vec4_2d_float_array;

    public PageArmy(byte page)
    {
        PAGE = page;
//        STATE = 0;
        this.vec2_2d_float_array = new float[4][2];
        this.color_vec4_2d_float_array = new float[5][4];
        this.color_vec4_2d_float_array[0] = new float[]{1.0F, 1.0F, 1.0F, 1.0F};

        this.color_vec4_2d_float_array[1] = new float[]{1.0F/* / Integer.MAX_VALUE*//255.0F, PAGE/255.0F, 0.0F, 1.0F};
        this.color_vec4_2d_float_array[2] = new float[]{2.0F/* / Integer.MAX_VALUE*//255.0F, PAGE/255.0F, 0.0F, 1.0F};
//        this.color_vec4_2d_float_array[3] = new float[]{3.0F/255.0F, PAGE/255.0F, 0.0F, 1.0F};

        this.color_vec4_2d_float_array[3] = new float[]{0.0F, 0.0F, 0.0F, 1.0F};
        this.color_vec4_2d_float_array[4] = new float[]{0.5F, 1.0F, 0.5F, 1.0F};
    }

    public static void openPageArmy()
    {
        PageMenu.BYTE |= 1;
        if (PageKeyArmy.ARRAY_BUFFER_INTEGER_LIST.size() != 2)
        {
            PageKeyArmy.BYTE |= 1;
        }

        PAGE_ARRAY = new Page[]
        {
            new PageBlur(),
            new PageMenu(STRING_ARRAY[14] + "|" + STRING_ARRAY[0]),
            new PageSakura(),
            new PageKeyArmy(),
            new PageArmy((byte)1),
            new PageText()
        };
        KEY = new KeyMenuArmy();
        MOUSE = new MouseArmy();
//        FLAG |= 1;
        addSet();
    }

    @Override
    public void init()
    {
        String string = STRINGBUFFER.toString();
        if (!string.equals(STRING))
        {
            BYTE |= 1;
        }
        STRING = string;

//        byte b = this.getByte();
//        if ((b & 1) == 1)
        if ((BYTE & 1) == 1)
        {
            if ((BYTE & 2) == 0)
            {
                BYTE |= 2;
                this.clear(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST);
//            super.init();
//        NetworkRegistry.I.sendToServer(new ServerMessage(new byte[]{SSToC.ID}));
                this.initC();
//            this.setByte((byte)(b & 255-1));
                BYTE &= 255-1-2;
            }
        }
//        else if ((BYTE & 2) == 2)
//        {
//            BYTE &= 255-2;
//        }
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
//        if (SIZE != C_MAP.size())
//        {
//            FLAG |= 1;
////            SMALLGUI.state |= 8;
//        }
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

        if ((STATE & 1) == 1)
        {
            Minecraft minecraft = SMALLGUI.mc;
//            int
//            display_width = minecraft.displayWidth,
//            display_height = minecraft.displayHeight;
//            float offset = H + 4.0F * 0.005F * display_height;
//            int h = (int)(display_height - offset);

            MemoS rs = S_LIST.get(SmallData.SHADER_STEP + 3);
            OpenGlHelper.glUseProgram(rs.program);
            int v = rs.attriblocation_int_array[0];
            GL20.glEnableVertexAttribArray(v);
//
////            if (y - < display_height)
////            {
////                break;
////            }
//
////            int w = (int)(display_width - LEFT + H + 6.0F * 0.005F * display_width);
            OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, OFFSET_FRAMEBUFFER);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, OFFSET_FRAMEBUFFER_TEXTURE);
//            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, display_width, h, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (IntBuffer)null);
            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, A_W, A_H, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (IntBuffer)null);
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

////            int debug = 0;
//            float scale = 75.0F / ((float)SMALLGUI.height / display_height),
//            h_offset = H + 4.0F * 0.005F * display_height,
////            y = MouseArmy.Y / 2.0F * display_height + (display_height - (h_offset * 2.0F)),
//            py = display_height - (h_offset * 2.0F),
//            y0 = scale + 2.0F * 0.005F * display_height,
//            y = (MouseArmy.Y / 2.0F * display_height + py) / y0;
//            float max_y = y + 1.0F, min_y = (y - py / y0 - 1.0F), id;
//
//            this.vec2_2d_float_array[0][1] = Y;
//            for (int i = 0; i < size - 3; ++i)
//            {
//                id = i / 7.0F;
////                float y1 = y0 * (i - 1),
////                y2 = y0 * /*(*/i/* + 3)*/;
////
////                if (y >= y1 && y <= y2)
//                if (id >= min_y && id <= max_y)
//                {
//                    this.drawQuadVUv(rs, this.vec2_2d_float_array[0], this.color_vec4_2d_float_array[0], ARRAY_BUFFER_INTEGER_LIST.get(i), TEXTURE_INTEGER_LIST.get(i));
////                    ++debug;
//                }
//            }
////            Nali.LOGGER.info("debug " + debug);
////            Nali.LOGGER.info("max " + (size-3));
            for (int i : INDEX_INTEGER_LIST)
            {
                byte b = (byte)(i % 7);
                if (b != 0)
                {
                    this.drawQuadVUv(rs, this.vec2_2d_float_array[0], (b == 3 && MouseArmy.E_PAGE == PAGE && MouseArmy.HIT == (i / 7) % 62 + 62 + 3) || (b != 3 && MouseArmy.E_PAGE == PAGE && MouseArmy.HIT == (i / 7) % 62 + 3) ? this.color_vec4_2d_float_array[4] : this.color_vec4_2d_float_array[0], ARRAY_BUFFER_INTEGER_LIST.get(i), TEXTURE_INTEGER_LIST.get(i));
                }
            }

//            this.drawQuadVUv(rs, this.vec2_2d_float_array[1], this.color_vec4_2d_float_array[0], ARRAY_BUFFER_INTEGER_LIST.get(size - 3), TEXTURE_INTEGER_LIST.get(size - 3));
//            this.vec2_2d_float_array[2][1] = -Y_STAR;
//            this.drawQuadVUv(rs, this.vec2_2d_float_array[2], this.color_vec4_2d_float_array[0], ARRAY_BUFFER_INTEGER_LIST.get(size - 2), TEXTURE_INTEGER_LIST.get(size - 2));

//            GL11.glViewport(0, 0, display_width, h);
//            GL11.glViewport(0, 0, (int)(display_width + (LEFT + H + 6.0F * 0.005F * display_width)), h);
            GL11.glViewport(0, 0, A_W, A_H);
//            GL11.glViewport(0, 0, display_width, (int)(display_height + offset));
//            GL11.glViewport(0, 0, display_width, display_height);

//            OFFSET_CUTOFF_ARRAY_BUFFER = genBuffer(createFloatByteBuffer(this.createQuadVUv(0, 0, minecraft.displayWidth, minecraft.displayHeight, minecraft.displayWidth, minecraft.displayHeight/*, 1.0F*//* / (this.mc.displayWidth / (float)this.width)*//*, 1.0F*/)/*, true*/));

            OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, GL_READ_FRAMEBUFFER_BINDING);
            OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, GL_DRAW_FRAMEBUFFER_BINDING);
//            OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, OFFSET_CUTOFF_FRAMEBUFFER);
//            GL11.glBindTexture(GL11.GL_TEXTURE_2D, OFFSET_CUTOFF_FRAMEBUFFER_TEXTURE);
//            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, display_width, h, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (IntBuffer)null);
//            OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, OFFSET_CUTOFF_FRAMEBUFFER_TEXTURE, 0);
//            this.vec2_2d_float_array[3][1] = (H * 2.0F - 4.0F * 0.005F * display_height) / display_height;
//            this.drawQuadVUv(rs, this.vec2_2d_float_array[3], this.color_vec4_2d_float_array[0], OFFSET_CUTOFF_ARRAY_BUFFER, OFFSET_FRAMEBUFFER_TEXTURE);
            this.drawQuadVUv(rs, this.vec2_2d_float_array[3], this.color_vec4_2d_float_array[0], FULL_ARRAY_BUFFER, OFFSET_FRAMEBUFFER_TEXTURE);

//            GL11.glViewport(0, 0, display_width, display_height);
            GL11.glViewport(0, 0, minecraft.displayWidth, minecraft.displayHeight);

            int size = ARRAY_BUFFER_INTEGER_LIST.size();

            int index = size - 1;
            this.drawQuadVUv(rs, this.vec2_2d_float_array[1], this.color_vec4_2d_float_array[0], ARRAY_BUFFER_INTEGER_LIST.get(index), TEXTURE_INTEGER_LIST.get(index));

            index = size - 3;
            this.drawQuadVUv(rs, this.vec2_2d_float_array[1], this.color_vec4_2d_float_array[0], ARRAY_BUFFER_INTEGER_LIST.get(index), TEXTURE_INTEGER_LIST.get(index));
//            this.vec2_2d_float_array[2][1] = -Y_STAR;
            index = size - 2;
            this.drawQuadVUv(rs, this.vec2_2d_float_array[2], this.color_vec4_2d_float_array[0], ARRAY_BUFFER_INTEGER_LIST.get(index), TEXTURE_INTEGER_LIST.get(index));

            GL20.glDisableVertexAttribArray(v);

//            GL11.glLoadMatrix(OPENGL_PROJECTION_MATRIX_FLOATBUFFER);
//            GL11.glMatrixMode(GL_MATRIX_MODE);

//            OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, GL_READ_FRAMEBUFFER_BINDING);
//            OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, GL_DRAW_FRAMEBUFFER_BINDING);

//            GL11.glViewport(0, 0, display_width, display_height);

//            OpenGlHelper.glDeleteBuffers(OFFSET_CUTOFF_ARRAY_BUFFER);
        }

//        if ((SMALLGUI.state & 4) == 4)
//        {
//            SMALLGUI.state &= 255-4;
//            SMALLGUI.defaultPage();
//        }
    }

    @Override
    public void preDraw()
    {
        if ((STATE & 1) == 1)
        {
            //shadow->blur->predraw
            Minecraft minecraft = SMALLGUI.mc;
            MemoS rs = S_LIST.get(SmallData.SHADER_STEP + 3);
            int v = rs.attriblocation_int_array[0];
            OpenGlHelper.glUseProgram(rs.program);
            GL20.glEnableVertexAttribArray(v);

            int size = ARRAY_BUFFER_INTEGER_LIST.size();
            int index = size - 1;
            this.drawQuadVUv(rs, this.vec2_2d_float_array[1], this.color_vec4_2d_float_array[3], ARRAY_BUFFER_INTEGER_LIST.get(index), TEXTURE_INTEGER_LIST.get(index));

    //        OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, OFFSET_FRAMEBUFFER_0);
            OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, OFFSET_FRAMEBUFFER);
    //        GL11.glBindTexture(GL11.GL_TEXTURE_2D, OFFSET_FRAMEBUFFER_TEXTURE_0);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, OFFSET_FRAMEBUFFER_TEXTURE);
            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, A_W, A_H, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (IntBuffer)null);
    //        OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, OFFSET_FRAMEBUFFER_TEXTURE_0, 0);
            OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, OFFSET_FRAMEBUFFER_TEXTURE, 0);
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
            for (int i : INDEX_INTEGER_LIST)
            {
                float[] color_float_array;
                byte b = (byte)(i % 7);
                if (b == 0)
                {
                    color_float_array = this.color_vec4_2d_float_array[0];
                }
                else
                {
                    color_float_array = (b == 3 && MouseArmy.E_PAGE == PAGE && MouseArmy.HIT == (i / 7) % 62 + 62 + 3) || (b != 3 && MouseArmy.E_PAGE == PAGE && MouseArmy.HIT == (i / 7) % 62 + 3) ? this.color_vec4_2d_float_array[4] : this.color_vec4_2d_float_array[3];
                }

                this.drawQuadVUv(rs, this.vec2_2d_float_array[0], color_float_array, ARRAY_BUFFER_INTEGER_LIST.get(i), TEXTURE_INTEGER_LIST.get(i));
            }
            GL11.glViewport(0, 0, A_W, A_H);
            OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, GL_READ_FRAMEBUFFER_BINDING);
            OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, GL_DRAW_FRAMEBUFFER_BINDING);
    //        this.drawQuadVUv(rs, this.vec2_2d_float_array[3], this.color_vec4_2d_float_array[0], FULL_ARRAY_BUFFER, OFFSET_FRAMEBUFFER_TEXTURE_0);
            this.drawQuadVUv(rs, this.vec2_2d_float_array[3], this.color_vec4_2d_float_array[0], FULL_ARRAY_BUFFER, OFFSET_FRAMEBUFFER_TEXTURE);
            GL11.glViewport(0, 0, minecraft.displayWidth, minecraft.displayHeight);

            index = size - 3;
            this.drawQuadVUv(rs, this.vec2_2d_float_array[1], this.color_vec4_2d_float_array[3], ARRAY_BUFFER_INTEGER_LIST.get(index), TEXTURE_INTEGER_LIST.get(index));
            this.vec2_2d_float_array[2][1] = -Y_STAR;
            index = size - 2;
            this.drawQuadVUv(rs, this.vec2_2d_float_array[2], this.color_vec4_2d_float_array[3], ARRAY_BUFFER_INTEGER_LIST.get(index), TEXTURE_INTEGER_LIST.get(index));
            GL20.glDisableVertexAttribArray(v);
            //shadow
        }
    }

    @Override
    public void detect()
    {
        if (SIZE != C_MAP.size())
        {
//            FLAG |= 1;
//            SMALLGUI.state |= 8;
//            this.state |= 1;
            BYTE |= 1;
        }

        if (SIZE > 0 && SIZE == C_MAP.size())
        {
            STATE |= 1;
        }

        if ((STATE & 1) == 1)
        {
//            MemoS rs = S_LIST.get(SmallData.SHADER_STEP + 4);
//
//            OpenGlHelper.glUseProgram(rs.program);
//            int v = rs.attriblocation_int_array[0];
//            GL20.glEnableVertexAttribArray(v);
            MemoS rs = S_LIST.get(SmallData.SHADER_STEP + 4);

            OpenGlHelper.glUseProgram(rs.program);
            int v = rs.attriblocation_int_array[0];
            GL20.glEnableVertexAttribArray(v);

            int size = ARRAY_BUFFER_INTEGER_LIST.size();

            Minecraft minecraft = SMALLGUI.mc;
            //            int debug = 0;
            float
            display_width = minecraft.displayWidth,
            display_height = SMALLGUI.mc.displayHeight,
            scale = 75.0F / ((float)SMALLGUI.height / display_height),
            h_offset = H + 4.0F * 0.005F * display_height,
//            y = MouseArmy.Y / 2.0F * display_height + (display_height - (h_offset * 2.0F)),
            py = display_height - (h_offset * 2.0F),
            y0 = scale + 2.0F * 0.005F * display_height,
            y = (MouseArmy.Y / 2.0F * display_height + py) / y0;
            float max_y = y + 1.0F, min_y = (y - py / y0 - 1.0F)/*, id = 0*/;

            float offset = H + 4.0F * 0.005F * display_height;
            A_H = (int)(display_height - offset);

            INDEX_INTEGER_LIST.clear();
            this.vec2_2d_float_array[0][1] = Y;
//            int check = 0;

            A_W = (int)(display_width - LEFT + H + 6.0F * 0.005F * display_width);
//            OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, GL_READ_FRAMEBUFFER_BINDING);
//            OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, GL_DRAW_FRAMEBUFFER_BINDING);
            OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, OFFSET_FRAMEBUFFER_0);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, OFFSET_FRAMEBUFFER_TEXTURE_0);
            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, A_W, A_H, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (IntBuffer)null);
//            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, (int)display_width, (int)display_height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (IntBuffer)null);
            OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, OFFSET_FRAMEBUFFER_TEXTURE_0, 0);
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

//            int id = 0;
            for (int i = 0; i < size - 3; ++i)
            {
                float new_id = i / 7.0F;
//                int id_int =  i % 7;
//                if (id_int != id)
//                {
//                    check = 0;
//                }

//                id = id_int;
//                float id = new_id;
//                float y1 = y0 * (i - 1),
//                y2 = y0 * /*(*/i/* + 3)*/;
//
//                if (y >= y1 && y <= y2)
                if (new_id >= min_y && new_id <= max_y)
                {
                    float[] color_float_array;
//                    if (check == 3)
                    if (i % 7 == 3)
                    {
                        color_float_array = new float[]{((int)new_id % 62 + 62 + 3.0F) / 255.0F, PAGE/255.0F, 0.0F, 1.0F};
                    }
                    else
                    {
                        color_float_array = new float[]{((int)new_id % 62 + 3.0F) / 255.0F, PAGE/255.0F, 0.0F, 1.0F};
                    }

//                    this.drawQuadVUv(rs, this.vec2_2d_float_array[0], this.color_vec4_2d_float_array[id + 3], ARRAY_BUFFER_INTEGER_LIST.get(i), TEXTURE_INTEGER_LIST.get(i));
                    this.drawQuadVUv(rs, this.vec2_2d_float_array[0], color_float_array, ARRAY_BUFFER_INTEGER_LIST.get(i), TEXTURE_INTEGER_LIST.get(i));
//                    this.drawQuadVUv(rs, this.vec2_2d_float_array[0], this.color_vec4_2d_float_array[1], ARRAY_BUFFER_INTEGER_LIST.get(i), TEXTURE_INTEGER_LIST.get(i));
//                    this.drawQuadVUv(rs, this.vec2_2d_float_array[0], new float[]{10.0F/255.0F, PAGE/255.0F, 0, 1}, ARRAY_BUFFER_INTEGER_LIST.get(i), TEXTURE_INTEGER_LIST.get(i));
//                    this.drawQuadVUv(rs, this.vec2_2d_float_array[0], this.color_vec4_2d_float_array[3], ARRAY_BUFFER_INTEGER_LIST.get(i), TEXTURE_INTEGER_LIST.get(i));
                    INDEX_INTEGER_LIST.add(i);
//                    ++debug;
                }
//                ++check;
            }
//            Nali.LOGGER.info("debug " + debug);
//            Nali.LOGGER.info("max " + (size-3));

            int displayHeight = minecraft.displayHeight;
            int displayWidth = minecraft.displayWidth;
//            GL11.glBindTexture(GL11.GL_TEXTURE_2D, OFFSET_FRAMEBUFFER_TEXTURE);
////            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, displayWidth, displayHeight, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (IntBuffer)null);
//            OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, OFFSET_FRAMEBUFFER_TEXTURE, 0);

            GL11.glViewport(0, 0, A_W, A_H);

//            OFFSET_CUTOFF_ARRAY_BUFFER = genBuffer(createFloatByteBuffer(this.createQuadVUv(0, 0, display_width, display_height, display_width, display_height)));
//            OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, GL_READ_FRAMEBUFFER_BINDING);
//            OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, GL_DRAW_FRAMEBUFFER_BINDING);
            OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, OFFSET_FRAMEBUFFER);
            this.vec2_2d_float_array[3][1] = (H * 2.0F - 4.0F * 0.005F * display_height) / display_height;

            GL20.glDisableVertexAttribArray(v);
            rs = S_LIST.get(SmallData.SHADER_STEP + 3);
            OpenGlHelper.glUseProgram(rs.program);
            v = rs.attriblocation_int_array[0];
            GL20.glEnableVertexAttribArray(v);

            this.drawQuadVUv(rs, this.vec2_2d_float_array[3], this.color_vec4_2d_float_array[0], FULL_ARRAY_BUFFER, OFFSET_FRAMEBUFFER_TEXTURE_0);
            GL20.glDisableVertexAttribArray(v);

            GL11.glViewport(0, 0, displayWidth, displayHeight);
            rs = S_LIST.get(SmallData.SHADER_STEP + 4);

            OpenGlHelper.glUseProgram(rs.program);
            v = rs.attriblocation_int_array[0];
            GL20.glEnableVertexAttribArray(v);

            int index = size - 3;
            this.drawQuadVUv(rs, this.vec2_2d_float_array[1], this.color_vec4_2d_float_array[1], ARRAY_BUFFER_INTEGER_LIST.get(index), TEXTURE_INTEGER_LIST.get(index));

            index = size - 1;
            this.drawQuadVUv(rs, this.vec2_2d_float_array[1], this.color_vec4_2d_float_array[2], ARRAY_BUFFER_INTEGER_LIST.get(index), TEXTURE_INTEGER_LIST.get(index));

            GL20.glDisableVertexAttribArray(v);
        }
    }

//    @Override
//    public byte getByte()
//    {
//        return BYTE;
//    }
//
//    @Override
//    public void setByte(byte b)
//    {
//        BYTE = b;
//    }

//    @Override
//    public void change()
//    {
//
//    }

//    @Override
//    public List<Integer> getArrayBufferIntegerList()
//    {
//        return ARRAY_BUFFER_INTEGER_LIST;
//    }
//
//    @Override
//    public List<Integer> getTextureIntegerList()
//    {
//        return TEXTURE_INTEGER_LIST;
//    }

    public void initC()
    {
//        if (OFFSET_CUTOFF_ARRAY_BUFFER != -1)
//        {
//            OpenGlHelper.glDeleteBuffers(OFFSET_CUTOFF_ARRAY_BUFFER);
//        }
//        OFFSET_CUTOFF_ARRAY_BUFFER = genBuffer(createFloatByteBuffer(this.createQuadVUv(0, 0, 1, 1, 1, 1)));

        SIZE = C_MAP.size();
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
        h_offset_y2 = h_offset_y * 2.0F,
//        w_offset = display_width - (LEFT + H + 6.0F * 0.005F * display_width);
        w_offset = display_width - x;

        MAX_Y = (((height_f + h2) * SIZE - (display_height - h_offset_y2)) / display_height) * 2.0F;
        MAX_Y_OFFSET = (((height_f + h2) * 62 - (display_height - h_offset_y2)) / display_height) * 2.0F;
//        MAX_Y_STAR = ((display_height - h_offset_y - (H * 2.0F)/* + *//*h_offset*//*(h_offset * 2.0F)*/) / /*(*/display_height/* - h_offset * 2.0F)*/) * 2.0F;
        MAX_Y_STAR = ((display_height/* - h_offset_y*/ - h_offset_y2 - H) / display_height) * 2.0F;
//        MAX_Y_STAR = -WO / (SMALLGUI.width / (float)display_width) * 2.0F;

        int height = (int)height_f;

        MOVE_BYTE_ARRAY = new byte[(int)Math.ceil(SIZE * 4.0F / 8.0F)];//limit box
        UUID_ARRAY = new UUID[SIZE];

        Set<UUID> keys_set = new HashSet(C_MAP.keySet());
        int m = 0;
        for (UUID uuid : keys_set)
        {
            ClientE c = C_MAP.get(uuid);

            if (c != null)
            {
                String[] string_array = new String[4];
////            int[] int_array = new int[5];
////            int in_index = 0;
//
//    //            this.preDrawModel(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, c, -1, (int)(25 / (SMALLGUI.width / (float)display_width)), (int)(25 / (SMALLGUI.height / (float)display_height)), LEFT + H + 4.0F * 0.005F * display_width/* + 25.0F / (SMALLGUI.width / (float)display_width)*/, display_height - H * 2.0F - h2/* - 25.0F / (SMALLGUI.height / (float)display_height)*/, -25.0F/* / (SMALLGUI.height / (float)display_height)*/);
////                this.initTextHorizontal(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, string, l, text_height, x, y, text_scale);
//                ByteBuffer bytebuffer = ByteBuffer.allocateDirect(4).order(ByteOrder.nativeOrder());
//                bytebuffer.put((byte)0);
//                bytebuffer.put((byte)0);
//                bytebuffer.put((byte)0);
//                bytebuffer.put((byte)(255/4.0F));
//                bytebuffer.flip();
////                this.createBox(x, y, (int)(width + display_width / 2.0F), (int)(height / 2.0F), bytebuffer);
//                this.initBox(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, x, y, (int)(display_width - LEFT - H - 8.0F * 0.005F * display_width - x), (int)(height / 2.0F), bytebuffer);
//
//                this.initModel(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, c/*, -1*/, width, height, x, y, -scale);

                //state leak
//                //need to free lock
//                GlStateManager.bindTexture(0);

                IMixE i = c.i;
//                String statp_string = "";
                string_array[1] = "";

                if (i == null)
                {
                    string_array[0] = id + " " + c.name_string;

                    if (c.hp > 0)
                    {
                        //sync
                        if (false)//negative
                        {
                            string_array[1] += STRING_ARRAY[21];
                        }
                        else if (false)//teamup
                        {
                            string_array[1] += STRING_ARRAY[17];
                        }
                        else
                        {
                            string_array[1] += STRING_ARRAY[22];
                        }
                    }
                    else
                    {
                        string_array[1] += STRING_ARRAY[20];
                    }

                    if ((c.state & 1) == 1)
                    {
                        string_array[1] += " " + STRING_ARRAY[25];
                    }

                    string_array[2] = c.dimension == null ? ClientE.EMPTY_STRING : DimensionManager.getProviderType(c.dimension).getName();
                    string_array[3] = "X " + String.format("%.4f", c.x) + " Y " + String.format("%.4f", c.y) + " Z " + String.format("%.4f", c.z);
                }
                else
                {
                    Entity e = i.getE();
//                    String stat_string;
                    if (e.isEntityAlive())
                    {
                        //sync
                        if (false)//negative
                        {
                            string_array[1] += STRING_ARRAY[21];
                        }
                        else if (false)//teamup
                        {
                            string_array[1] += STRING_ARRAY[17];
                        }
                        else
                        {
                            string_array[1] += STRING_ARRAY[22];
                        }
                    }
                    else
                    {
                        string_array[1] += STRING_ARRAY[20];
                    }

                    if (false)
                    {
                        string_array[1] += " " + STRING_ARRAY[25];
                    }

                    string_array[0] = id + " " + e.getName()/* + " "*/;
//                    int l = (int)(minecraft.fontRenderer.getStringWidth(string) * SCALE);
//                    MOVE_BYTE_ARRAY[(int)(m++ * 7.0F / 8.0F)] |= l > w_offset ? 1 : 0;
//                    this.initTextHorizontal(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, string, l, H, x_offset, y + text_height * 2.0F + 4.0F * 0.005F * display_width, SCALE);

//                    string_array[1] = STRING_ARRAY[26];
////                    l = (int)(minecraft.fontRenderer.getStringWidth(string) * text_scale);
//                    this.initTextHorizontal(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, string, (int)(minecraft.fontRenderer.getStringWidth(string) * text_scale), text_height, x_offset + l + 2.0F * 0.005F * display_width, y + text_height * 2.0F + 4.0F * 0.005F * display_width, text_scale);

//                    string_array[1] = stat_string + statp_string;
//                    l = (int)(minecraft.fontRenderer.getStringWidth(string) * SCALE);
//                    this.initTextHorizontal(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, string, l, H, x, y + height - H, SCALE);

//                    string = DimensionManager.getProviderType(e.dimension).getName();
//                    string = DimensionManager.getProviderType(e.world.getWorldType().getId()).getName();
                    string_array[2] = e.world.provider.getDimensionType().getName();
//                    l = (int)(minecraft.fontRenderer.getStringWidth(string) * text_scale);
//                    this.initTextHorizontal(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, string, l, text_height, x_offset, y + text_height + 2.0F * 0.005F * display_width, text_scale);

                    string_array[3] = "X " + String.format("%.4f", e.posX) + " Y " + String.format("%.4f", e.posY) + " Z " + String.format("%.4f", e.posZ);
//                    l = (int)(minecraft.fontRenderer.getStringWidth(string) * text_scale);
//                    this.initTextHorizontal(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, string, l, text_height, x_offset, y, text_scale);
                }
////                this.preDrawModel(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, c/*, -1*/, width, height, x, y, -scale);
//    //            x += ;
//                y -= scale / (SMALLGUI.height / (float)display_height) + h2;

                UUID_ARRAY[id] = uuid;
                ++id;

                boolean out = false;
                for (String string : string_array)
                {
                    if (string.contains(STRINGBUFFER.toString()))
                    {
                        out = false;
                        break;
                    }
                    else
                    {
                        out = true;
                    }
                }
                if (out)
                {
                    continue;
                }

                ByteBuffer bytebuffer = ByteBuffer.allocateDirect(4).order(ByteOrder.nativeOrder());
                bytebuffer.put((byte)0);
                bytebuffer.put((byte)0);
                bytebuffer.put((byte)0);
                bytebuffer.put((byte)(255/4.0F));
                bytebuffer.flip();
                this.initBox(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, x, y, (int)(display_width - LEFT - H - 8.0F * 0.005F * display_width - x), (int)(height / 2.0F), bytebuffer);
                this.initModel(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, c/*, -1*/, width, height, x, y, -scale);

                String string = string_array[0];
                int l = (int)(minecraft.fontRenderer.getStringWidth(string) * SCALE);
                MOVE_BYTE_ARRAY[(int)(m++ / 8.0F)] |= l > w_offset ? 1 : 0;
                this.initTextHorizontal(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, string, l, H, x_offset, y + text_height * 2.0F + 4.0F * 0.005F * display_width, SCALE);

                string = STRING_ARRAY[26];//string_array[1];
//            this.initTextHorizontal(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, string, (int)(minecraft.fontRenderer.getStringWidth(string) * text_scale), text_height, x_offset + l + 2.0F * 0.005F * display_width, y + text_height * 2.0F + 4.0F * 0.005F * display_width, text_scale);
                l = (int)(minecraft.fontRenderer.getStringWidth(string) * text_scale);
                this.initTextHorizontal(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, string, l, text_height, x + width - l, y, text_scale);

                string = string_array[1];
                MOVE_BYTE_ARRAY[(int)(m++ / 8.0F)] |= l > w_offset ? 1 : 0;
                l = (int)(minecraft.fontRenderer.getStringWidth(string) * SCALE);
                this.initTextHorizontal(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, string, l, H, x, y + height - H, SCALE);

                string = string_array[2];
                MOVE_BYTE_ARRAY[(int)(m++ / 8.0F)] |= l > w_offset ? 1 : 0;
                l = (int)(minecraft.fontRenderer.getStringWidth(string) * text_scale);
                this.initTextHorizontal(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, string, l, text_height, x_offset, y + text_height + 2.0F * 0.005F * display_width, text_scale);

                string = string_array[3];
                MOVE_BYTE_ARRAY[(int)(m++ / 8.0F)] |= l > w_offset ? 1 : 0;
                l = (int)(minecraft.fontRenderer.getStringWidth(string) * text_scale);
                this.initTextHorizontal(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, string, l, text_height, x_offset, y, text_scale);
//            HO = y;
                y -= scale / (SMALLGUI.height / (float)display_height) + h2;
            }
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
//        this.initTextVertical(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, stringbuilder.toString(), H, (int)ys, display_width - H - RIGHT - 6.0F * 0.005F * display_width, display_height - H - 4.0F * 0.005F * display_height, SCALE);
//        this.initTextVertical(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, stringbuilder.toString(), H, (int)ye, display_width - LEFT - H - 6.0F * 0.005F * display_width, display_height - H - 4.0F * 0.005F * display_height + h_offset, SCALE);
//        WO = max_l * MAX_TH * SCALE;
        this.initTextVertical(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, stringbuilder.toString(), H, (int)(max_l * MAX_TH * SCALE), display_width - LEFT - H - 6.0F * 0.005F * display_width, display_height - H - 4.0F * 0.005F * display_height/* + h_offset*/, SCALE);
//        this.initTextVertical(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, stringbuilder.toString(), H, box_height, 0, display_height / 2.0F, SCALE);
//        this.initTextVertical(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, STRING_ARRAY[7], H, H, display_width - LEFT - H - 6.0F * 0.005F * display_width, display_height - H/* * 3.0F */- 4.0F * 0.005F * display_height - h_offset/* + h_offset*/, SCALE);
        this.initTextVertical(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, STRING_ARRAY[29], H, H, display_width - LEFT - H - 6.0F * 0.005F * display_width, display_height - H - 4.0F * 0.005F * display_height/* - H*/, SCALE);

        //search
        String string = "________________" + STRING_ARRAY[28];
        SEARCH_L = minecraft.fontRenderer.getStringWidth(string) * SCALE;
//        SEARCH_L = w_offset - 2.0F * 0.005F * display_width;
//        SEARCH_L = x;
//        stringbuilder = new StringBuilder();
//        max_l = (int)(Math.ceil((w_offset - 2.0F * 0.005F * display_width - LEFT) / SCALE));
//        for (int i = 0; i < max_l - 1; ++i)
//        {
//            stringbuilder.append("_");
//        }
//        stringbuilder.append(STRING_ARRAY[28]);
        this.initTextHorizontal(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, string, (int)SEARCH_L, H, display_width - SEARCH_L - 2.0F * 0.005F * display_width, display_height - H - h2, SCALE);
//        this.initTextHorizontal(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, stringbuilder.toString(), max_l, H, SEARCH_L, display_height - H - h2, SCALE);
    }
}
