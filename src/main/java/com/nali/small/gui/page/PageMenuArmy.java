package com.nali.small.gui.page;

import com.nali.list.data.SmallData;
import com.nali.system.opengl.memo.client.MemoS;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL20;

import static com.nali.list.container.gui.SmallGui.*;
import static com.nali.system.ClientLoader.S_LIST;

@SideOnly(Side.CLIENT)
public class PageMenuArmy extends PageMenu
{
    public static float BT27;

    public PageMenuArmy(String string)
    {
        super(string);
    }

    @Override
    public void init()
    {
        super.init();
        Minecraft minecraft = SMALLGUI.mc;
        int display_width = minecraft.displayWidth,
        display_height = minecraft.displayHeight;

        float scale = SCALE * 0.5F;
        int h = (int)(MAX_TH * scale);
        String string = STRING_ARRAY[27];
        int i = (int)(minecraft.fontRenderer.getStringWidth(string) * scale);
//        box_width = i - display_width;
//        if (i < display_width)
//        {
//            box_width = i;
//        }
//        this.initTextHorizontal(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, string/*, true*//*, -1*/, box_width, h, display_width - box_width - RIGHT - 4.0F * 0.005F * display_width, 2.0F * 0.005F * display_height, scale);
        this.initTextHorizontal(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, string/*, true*//*, -1*/, i, h, display_width - i - RIGHT - 4.0F * 0.005F * display_width, 2.0F * 0.005F * display_height, scale);
//        RIGHT += box_width;
        RIGHT += i;
    }

    @Override
    public void draw()
    {
        super.draw();

        MemoS rs = S_LIST.get(SmallData.SHADER_STEP + 3);
        OpenGlHelper.glUseProgram(rs.program);
        int v = rs.attriblocation_int_array[0];
        GL20.glEnableVertexAttribArray(v);

        boolean bt = BT27 > 0.0F;
        this.drawQuadVUv(rs, this.vec2_2d_float_array[1], bt ? this.color_vec4_2d_float_array[2] : this.color_vec4_2d_float_array[1], ARRAY_BUFFER_INTEGER_LIST.get(3), TEXTURE_INTEGER_LIST.get(3));
        if (bt)
        {
            BT27 -= SMALLGUI.partial_ticks;
        }

        GL20.glDisableVertexAttribArray(v);
    }
}
