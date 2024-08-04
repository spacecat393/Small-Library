package com.nali.small.gui.page;

import com.nali.list.data.SmallData;
import com.nali.system.opengl.memo.client.MemoS;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL20;

import java.util.ArrayList;
import java.util.List;

import static com.nali.list.container.gui.SmallGui.SCALE;
import static com.nali.list.container.gui.SmallGui.SMALLGUI;
import static com.nali.system.ClientLoader.S_LIST;

@SideOnly(Side.CLIENT)
public class PageSakura extends Page
{
    public static List<Integer>
    TEXTURE_INTEGER_LIST = new ArrayList(),
    ARRAY_BUFFER_INTEGER_LIST = new ArrayList();

    public static byte
    BYTE = 1,
    SAKURA = -1;

    public static float
    LEFT;

    public float[][] vec2_2d_float_array;
    public float[][] color_vec4_2d_float_array;

    public PageSakura()
    {
        this.vec2_2d_float_array = new float[1][2];
        this.color_vec4_2d_float_array = new float[2][4];
        this.color_vec4_2d_float_array[0] = new float[]{1.0F, 1.0F, 1.0F, 1.0F};

        this.color_vec4_2d_float_array[1] = new float[]{0.0F, 0.0F, 0.0F, 1.0F};
    }

    @Override
    public void init()
    {
        byte sakura = SMALLGUI.mc.player.getEntityData().getByte("Nali_sakura");
        if (SAKURA != sakura)
        {
//            FLAG |= 1;
//            this.state |= 1;
//            BYTE |= 1;
//            this.setByte((byte)(this.getByte() | 1));
            BYTE |= 1;
        }

//        byte b = this.getByte();
//        if ((b & 1) == 1)
        if ((BYTE & 1) == 1)
        {
            LEFT = 0;
//            super.init();
            this.clear(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST);
            this.initSakura(sakura);
//            this.setByte((byte)(b & 255-1));
            BYTE &= 255-1;
        }
    }

    @Override
    public void draw()
    {
        this.draw(null);
    }

    @Override
    public void preDraw()
    {
        this.draw(this.color_vec4_2d_float_array[1]);
    }

    @Override
    public void detect()
    {

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

    public void draw(float[] color_float_array)
    {
        MemoS rs = S_LIST.get(SmallData.SHADER_STEP + 3);
        OpenGlHelper.glUseProgram(rs.program);
        int v = rs.attriblocation_int_array[0];
        GL20.glEnableVertexAttribArray(v);

        this.drawQuadVUv(rs, this.vec2_2d_float_array[0], color_float_array == null ? this.color_vec4_2d_float_array[0] : color_float_array, ARRAY_BUFFER_INTEGER_LIST.get(0), TEXTURE_INTEGER_LIST.get(0));

        GL20.glDisableVertexAttribArray(v);
    }

    public void initSakura(byte sakura/*, int index*/)
    {
        Minecraft minecraft = SMALLGUI.mc;
        int display_width = minecraft.displayWidth;
        int display_height = minecraft.displayHeight;

        SAKURA = sakura;//(byte)this.mc.player.getEntityData().getInteger("Nali_sakura");

        String string = STRING_ARRAY[11] + " " + SAKURA;
//        String string = STRING;
//        string += " > ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int i = (int)(minecraft.fontRenderer.getStringWidth(string) * SCALE);
//        box_width = i - display_width;
//        if (i < display_width)
//        {
//            box_width = i;
//        }
//        this.initTextHorizontal(string, false, index, (int)(box_width * SCALE), H, H, this.mc.displayHeight - H, SCALE);
//        LEFT = LEFT * 4 + H;
//        LEFT += H + 4.0F * 0.005F * display_width;
//        TOP += display_height - H - 2.0F * 0.005F * display_height;
//        this.initTextHorizontal(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, string/*, true*//*, index*/, box_width, H, H + 4.0F * 0.005F * display_width, display_height - H - 2.0F * 0.005F * display_height, SCALE);
        this.initTextHorizontal(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, string/*, true*//*, index*/, i, H, H + 4.0F * 0.005F * display_width, display_height - H - 2.0F * 0.005F * display_height, SCALE);
//        this.initTextHorizontal(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, string/*, true*/, index, -box_width + i, H, H + 4.0F * 0.005F * display_width, display_height - H - 2.0F * 0.005F * display_height, SCALE);
//        this.initTextHorizontal(string, false, index, (int)(box_width * SCALE * 1.1F), H, H + -(SCALE * 1.1F)/* / 2.0F*/, this.mc.displayHeight - H, SCALE * 1.1F);
//        LEFT += box_width;
        LEFT += i;
    }
}
