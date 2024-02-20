package com.nali.small.entities.skinning.render;

import com.nali.small.entities.skinning.SkinningEntities;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;

@SideOnly(Side.CLIENT)
public class SkinningEntitiesRenderMath
{
    public static float[] multiplyVec4Mat4(float[] vec4, float[] mat4)
    {
        float[] result = new float[4];
        for (int i = 0; i < 4; i++)
        {
            float sum = 0.0F;
            for (int j = 0; j < 4; j++)
            {
                sum += vec4[j] * mat4[i * 4 + j];
            }
            result[i] = sum;
        }
        return result;
    }

    public static float handleRotationFloat(SkinningEntities skinningentities, float partialTicks)
    {
        return (float)skinningentities.ticksExisted + partialTicks;
    }

    public static Color generateRainbowColor()
    {
        return Color.getHSBColor(Minecraft.getSystemTime()/*System.currentTimeMillis()*/ % 3600 / 3600.0F, 1.0F, 1.0F);
    }

//    public static float[] lookAt(float[] source, float[] target)
//    {
//        double d0 = target[0] - source[0];
//        double d1 = target[1] - source[1];
//        double d2 = target[2] - source[2];
//        double d3 = MathHelper.sqrt(d0 * d0 + d2 * d2);
//        float f = (float)(MathHelper.atan2(d2, d0) * (180.0D / Math.PI)) - 90.0F;
//        float f1 = (float)(-(MathHelper.atan2(d1, d3) * (180.0D / Math.PI)));
//        return new float[]{f, f1};
//    }
}
