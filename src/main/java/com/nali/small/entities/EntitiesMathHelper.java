package com.nali.small.entities;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntitiesMathHelper
{
    public static double getClose(Entity entity0, Entity entity1, double minimum_distance)
    {
        double width = (entity0.width + entity1.width) / 2.0D;
        double height = (entity0.height + entity1.height) / 2.0D;
        return (width + height + minimum_distance) * (width + height + minimum_distance);
    }

    public static boolean isTooClose(Entity entity0, Entity entity1, double minimum_distance)
    {
        return entity0.getDistanceSq(entity1) < getClose(entity0, entity1, minimum_distance);
    }

    public static float interpolateRotation(float prevYawOffset, float yawOffset, float partialTicks)
    {
        float f;

        for (f = yawOffset - prevYawOffset; f < -180.0F; f += 360.0F)
        {
            ;
        }

        while (f >= 180.0F)
        {
            f -= 360.0F;
        }

        return prevYawOffset + partialTicks * f;
    }

    @SideOnly(Side.CLIENT)
    public static boolean rayTargetsView(Entity player, AxisAlignedBB axisalignedbb)
    {
        byte step = 0;
        boolean result = false;
        Vec3d view_vec3d = player.getLookVec();
        Vec3d start_vec3d = new Vec3d(player.posX, player.posY + player.getEyeHeight(), player.posZ);
        view_vec3d = view_vec3d.scale(0.05);

        for (Vec3d end_vec3d = start_vec3d.add(view_vec3d); !result && step < 50; ++step)
        {
            end_vec3d = end_vec3d.add(view_vec3d);
            result = axisalignedbb.contains(end_vec3d);
        }

        return result;
    }
}
