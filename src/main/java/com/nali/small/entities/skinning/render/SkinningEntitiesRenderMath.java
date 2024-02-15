package com.nali.small.entities.skinning.render;

import com.nali.small.entities.skinning.SkinningEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.Vec3d;
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

    public static Vec3d lookAt(Vec3d source, Vec3d target)
    {
        double dX = target.x - source.x;
        double dY = target.y - source.y;
        double dZ = target.z - source.z;
        double distance = Math.sqrt(dX * dX + dY * dY + dZ * dZ);
        double pitch = Math.asin(dY / distance);
        double yaw = Math.atan2(dZ, dX);
        return new Vec3d(Math.toDegrees(yaw), Math.toDegrees(pitch), 0);
    }
}
