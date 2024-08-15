package com.nali.small.gui.page;

import com.nali.list.data.SmallData;
import com.nali.small.gui.key.KeyMenuMe;
import com.nali.small.gui.mouse.MouseMe;
import com.nali.small.gui.mouse.MouseSmall;
import com.nali.system.opengl.memo.client.MemoS;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL20;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.nali.list.container.gui.SmallGui.*;
import static com.nali.small.gui.key.Key.KEY;
import static com.nali.small.gui.mouse.Mouse.HIT;
import static com.nali.small.gui.mouse.Mouse.MOUSE;
import static com.nali.system.ClientLoader.S_LIST;

@SideOnly(Side.CLIENT)
public class PageMe extends Page
{
    public static List<Integer>
    TEXTURE_INTEGER_LIST = new ArrayList(),
    ARRAY_BUFFER_INTEGER_LIST = new ArrayList();

    public float[][] vec2_2d_float_array;
    public float[][] color_vec4_2d_float_array;

    public static UUID UUID;

    public static byte PAGE,
    BYTE = 1;

    public PageMe(byte page)
    {
        PAGE = page;
        this.vec2_2d_float_array = new float[1][2];
        this.color_vec4_2d_float_array = new float[6][4];
        this.color_vec4_2d_float_array[0] = new float[]{1.0F, 1.0F, 1.0F, 1.0F};
        this.color_vec4_2d_float_array[1] = new float[]{0.5F, 1.0F, 0.5F, 1.0F};

        this.color_vec4_2d_float_array[2] = new float[]{0.0F, 0.0F, 0.0F, 1.0F};

        this.color_vec4_2d_float_array[3] = new float[]{1.0F/255.0F, PAGE/255.0F, 0.0F, 1.0F};
        this.color_vec4_2d_float_array[4] = new float[]{2.0F/255.0F, PAGE/255.0F, 0.0F, 1.0F};
        this.color_vec4_2d_float_array[5] = new float[]{3.0F/255.0F, PAGE/255.0F, 0.0F, 1.0F};
    }

    public static void openPageMe()
    {
        PageMenu.BYTE |= 1;
        PAGE_ARRAY = new Page[]
        {
            new PageBlur(),
            new PageMenu(STRING_ARRAY[14] + ((KeyMenuMe.ME & 1) == 1 ? "|" + STRING_ARRAY[0] : "") + "|" + PageMe.UUID),
            new PageSakura(),
            new PageKey(),
            new PageMe((byte)1)
        };
        KEY = new KeyMenuMe();
        MOUSE = new MouseMe();
//        FLAG |= 1;
        addSet();
    }

    @Override
    public void init()
    {
        if ((BYTE & 1) == 1)
        {
            this.clear(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST);
            Minecraft minecraft = SMALLGUI.mc;
            FontRenderer fontrenderer = minecraft.fontRenderer;
            int display_width = minecraft.displayWidth;
            int display_height = minecraft.displayHeight;

            String[] string_array = new String[]
            {
                STRING_ARRAY[1],
                STRING_ARRAY[2],
                STRING_ARRAY[5]
            };

//            int size = string_array.length;
//            int[] int_array = new int[size];
//            for (int i = 0; i < size; ++i)
            int max = 0;
            for (String s : string_array)
            {
                int new_max = (int)(fontrenderer.getStringWidth(s) * SCALE);
                if (max < new_max)
                {
                    max = new_max;
                }
            }

            String string = string_array[0];
//            int i = (int)(fontrenderer.getStringWidth(string) * SCALE);
            this.initTextHorizontal(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, string, max, H, display_width / 2.0F - max / 2.0F, display_height / 2.0F, SCALE);

            string = string_array[1];
//            i = (int)(fontrenderer.getStringWidth(string) * SCALE);
            this.initTextHorizontal(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, string, max, H, display_width / 2.0F - max / 2.0F, display_height / 2.0F - H - 2.0F * 0.005F * display_height, SCALE);

            string = string_array[2];
//            i = (int)(fontrenderer.getStringWidth(string) * SCALE);
            this.initTextHorizontal(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, string, max, H, display_width / 2.0F - max / 2.0F, display_height / 2.0F - H * 2.0F - 2.0F * 2.0F * 0.005F * display_height, SCALE);

            BYTE &= 255-1;
        }
    }

    @Override
    public void draw()
    {
//        ClientE c = C_MAP.get(UUID);
//        if (c != null)
//        {
//            Nali.LOGGER.info("S");
////            GL11.glMatrixMode(GL11.GL_MODELVIEW);
////            GL11.glPushMatrix();
////            Minecraft minecraft = Minecraft.getMinecraft();
////            float scale = -75.0F;
////            float p = -scale / 2.0F;
////            float fx = (SMALLGUI.width / (float)minecraft.displayWidth);
////            float fy = (SMALLGUI.height / (float)minecraft.displayHeight);
////            GL11.glTranslatef(p / fx, (p - (scale / 4.0F)) / fy, 0);
////            GL11.glScalef((scale - (scale / 2.5F)) / fx, (scale - (scale / 2.5F)) / fy, scale);
////            GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
////            RenderO r = c.r;
////            r.lig_b = -1.0F;
////            c.initFakeFrame();//
////            r.draw();
////            GL11.glPopMatrix();
//        }
        MemoS rs = S_LIST.get(SmallData.SHADER_STEP + 3);
        OpenGlHelper.glUseProgram(rs.program);
        int v = rs.attriblocation_int_array[0];
        GL20.glEnableVertexAttribArray(v);

        this.drawQuadVUv(rs, this.vec2_2d_float_array[0], MouseSmall.E_PAGE == PAGE && HIT == 1 ? this.color_vec4_2d_float_array[1] : this.color_vec4_2d_float_array[0], ARRAY_BUFFER_INTEGER_LIST.get(0), TEXTURE_INTEGER_LIST.get(0));
        this.drawQuadVUv(rs, this.vec2_2d_float_array[0], MouseSmall.E_PAGE == PAGE && HIT == 2 ? this.color_vec4_2d_float_array[1] : this.color_vec4_2d_float_array[0], ARRAY_BUFFER_INTEGER_LIST.get(1), TEXTURE_INTEGER_LIST.get(1));
        this.drawQuadVUv(rs, this.vec2_2d_float_array[0], MouseSmall.E_PAGE == PAGE && HIT == 3 ? this.color_vec4_2d_float_array[1] : this.color_vec4_2d_float_array[0], ARRAY_BUFFER_INTEGER_LIST.get(2), TEXTURE_INTEGER_LIST.get(2));

        GL20.glDisableVertexAttribArray(v);
    }

    @Override
    public void preDraw()
    {
        MemoS rs = S_LIST.get(SmallData.SHADER_STEP + 3);
        OpenGlHelper.glUseProgram(rs.program);
        int v = rs.attriblocation_int_array[0];
        GL20.glEnableVertexAttribArray(v);

        this.drawQuadVUv(rs, this.vec2_2d_float_array[0], MouseSmall.E_PAGE == PAGE && HIT == 1 ? this.color_vec4_2d_float_array[1] : this.color_vec4_2d_float_array[2], ARRAY_BUFFER_INTEGER_LIST.get(0), TEXTURE_INTEGER_LIST.get(0));
        this.drawQuadVUv(rs, this.vec2_2d_float_array[0], MouseSmall.E_PAGE == PAGE && HIT == 2 ? this.color_vec4_2d_float_array[1] : this.color_vec4_2d_float_array[2], ARRAY_BUFFER_INTEGER_LIST.get(1), TEXTURE_INTEGER_LIST.get(1));
        this.drawQuadVUv(rs, this.vec2_2d_float_array[0], MouseSmall.E_PAGE == PAGE && HIT == 3 ? this.color_vec4_2d_float_array[1] : this.color_vec4_2d_float_array[2], ARRAY_BUFFER_INTEGER_LIST.get(2), TEXTURE_INTEGER_LIST.get(2));

        GL20.glDisableVertexAttribArray(v);
    }

    @Override
    public void detect()
    {
        MemoS rs = S_LIST.get(SmallData.SHADER_STEP + 4);

        OpenGlHelper.glUseProgram(rs.program);
        int v = rs.attriblocation_int_array[0];
        GL20.glEnableVertexAttribArray(v);

        this.drawQuadVUv(rs, this.vec2_2d_float_array[0], this.color_vec4_2d_float_array[3], ARRAY_BUFFER_INTEGER_LIST.get(0), TEXTURE_INTEGER_LIST.get(0));
        this.drawQuadVUv(rs, this.vec2_2d_float_array[0], this.color_vec4_2d_float_array[4], ARRAY_BUFFER_INTEGER_LIST.get(1), TEXTURE_INTEGER_LIST.get(1));
        this.drawQuadVUv(rs, this.vec2_2d_float_array[0], this.color_vec4_2d_float_array[5], ARRAY_BUFFER_INTEGER_LIST.get(2), TEXTURE_INTEGER_LIST.get(2));

        GL20.glDisableVertexAttribArray(v);
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
//
//    @Override
//    public List<Integer> getArrayBufferIntegerList()
//    {
//        return Collections.emptyList();
//    }
//
//    @Override
//    public List<Integer> getTextureIntegerList()
//    {
//        return Collections.emptyList();
//    }
}
