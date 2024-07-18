package com.nali.small.gui.page;

import com.nali.Nali;
import com.nali.list.data.SmallData;
import com.nali.system.opengl.memo.client.MemoSo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL20;

import java.util.ArrayList;
import java.util.List;

import static com.nali.list.container.gui.SmallGui.*;

@SideOnly(Side.CLIENT)
public class PageSmall extends Page
{
    public static List<Integer>
    TEXTURE_INTEGER_LIST = new ArrayList(),
    ARRAY_BUFFER_INTEGER_LIST = new ArrayList();

    public float[][] vec2_2d_float_array;
    public float[][] color_vec4_2d_float_array;

    public static byte PAGE;

    public PageSmall(byte page)
    {
        PAGE = page;
        this.vec2_2d_float_array = new float[1][2];
        this.color_vec4_2d_float_array = new float[4][4];
        this.color_vec4_2d_float_array[0] = new float[]{1.0F, 1.0F, 1.0F, 1.0F};
        this.color_vec4_2d_float_array[1] = new float[]{0.5F, 1.0F, 0.5F, 1.0F};

        this.color_vec4_2d_float_array[2] = new float[]{1.0F/255.0F, PAGE/255.0F, 0.0F, 1.0F};
        this.color_vec4_2d_float_array[3] = new float[]{2.0F/255.0F, PAGE/255.0F, 0.0F, 1.0F};
    }

    @Override
    public void init()
    {
        super.init();
        Minecraft minecraft = SMALLGUI.mc;
        FontRenderer fontrenderer = minecraft.fontRenderer;
        int display_width = minecraft.displayWidth;
        int display_height = minecraft.displayHeight;

        String string = STRING_ARRAY[0];
        int i = (int)(fontrenderer.getStringWidth(string) * SCALE),
        box_width = i - SMALLGUI.mc.displayWidth;
        if (i < SMALLGUI.mc.displayWidth)
        {
            box_width = i;
        }
        this.preDrawTextHorizontal(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, string/*, true*/, -1, box_width, H, display_width / 2.0F - box_width / 2.0F, display_height / 2.0F/* - H / 2.0F*/ + 2.0F * 0.005F * display_height, SCALE);

        string = STRING_ARRAY[3];
        i = (int)(fontrenderer.getStringWidth(string) * SCALE);
        box_width = i - SMALLGUI.mc.displayWidth;
        if (i < SMALLGUI.mc.displayWidth)
        {
            box_width = i;
        }
        this.preDrawTextHorizontal(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, string/*, true*/, -1, box_width, H, display_width / 2.0F - box_width / 2.0F, display_height / 2.0F - H/* / 2.0F*/ - 2.0F * 0.005F * display_height, SCALE);
    }

    @Override
    public void preDraw()
    {
//        super.preDraw();
    }

    @Override
    public void draw()
    {
        MemoSo rs = Nali.I.clientloader.storeo.rs_list.get(SmallData.SHADER_O_STEP + 3);
        OpenGlHelper.glUseProgram(rs.program);
        GL20.glEnableVertexAttribArray(0);

        this.drawQuadVUv(rs, this.vec2_2d_float_array[0], SMALLGUI.hit == 1 ? this.color_vec4_2d_float_array[1] : this.color_vec4_2d_float_array[0], ARRAY_BUFFER_INTEGER_LIST.get(0), TEXTURE_INTEGER_LIST.get(0));
        this.drawQuadVUv(rs, this.vec2_2d_float_array[0], SMALLGUI.hit == 2 ? this.color_vec4_2d_float_array[1] : this.color_vec4_2d_float_array[0], ARRAY_BUFFER_INTEGER_LIST.get(1), TEXTURE_INTEGER_LIST.get(1));

        GL20.glDisableVertexAttribArray(0);

        if (SMALLGUI.page == PAGE)
        {
            if (SMALLGUI.hit == 1)
            {
                PAGE_ARRAY = new Page[]
                {
                    new PageBack(),
                    new PageMenu(STRING_ARRAY[14] + "|" + STRING_ARRAY[0]),
                    new PageArmy()
                };
            }

            SMALLGUI.hit = 0;
            SMALLGUI.page = 0;
        }
    }

    @Override
    public void detect()
    {
        MemoSo rs = Nali.I.clientloader.storeo.rs_list.get(SmallData.SHADER_O_STEP + 4);

        OpenGlHelper.glUseProgram(rs.program);
        GL20.glEnableVertexAttribArray(0);

        this.drawQuadVUv(rs, this.vec2_2d_float_array[0], this.color_vec4_2d_float_array[2], ARRAY_BUFFER_INTEGER_LIST.get(0), TEXTURE_INTEGER_LIST.get(0));
        this.drawQuadVUv(rs, this.vec2_2d_float_array[0], this.color_vec4_2d_float_array[3], ARRAY_BUFFER_INTEGER_LIST.get(1), TEXTURE_INTEGER_LIST.get(1));

        GL20.glDisableVertexAttribArray(0);
    }

//    @Override
//    public void change()
//    {
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
}
