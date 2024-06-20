package com.nali.small.entity;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class EntityMath
{
//    public static boolean isTooClose(Entity entity0, Entity entity1, double minimum_distance)
//    {
//        return entity0.getDistanceSq(entity1) < getClose(entity0, entity1, minimum_distance);
//    }
//
//    public static double getClose(Entity entity0, Entity entity1, double minimum_distance)
//    {
//        double width = (entity0.width + entity1.width) / 2.0D;
//        double height = (entity0.height + entity1.height) / 2.0D;
//        return (width + height + minimum_distance) * (width + height + minimum_distance);
//    }

    public static boolean isInArea(double x, double y, double z, BlockPos blockpos, double minimum_distance)
    {
        double d0 = x - blockpos.getX() + 0.5;
        double d1 = y - blockpos.getY() + 0.5;
        double d2 = z - blockpos.getZ() + 0.5;
        return (d0 * d0 + d1 * d1 + d2 * d2) < minimum_distance;
    }

    public static boolean isInArea(BlockPos main_blockpos, BlockPos blockpos, double minimum_distance)
    {
        return main_blockpos.distanceSq(blockpos.getX() + 0.5, blockpos.getY() + 0.5, blockpos.getZ() + 0.5) < minimum_distance;
    }

    public static boolean isInArea(Entity entity, BlockPos blockpos, double minimum_distance)
    {
        return entity.getDistanceSq(blockpos.getX() + 0.5, blockpos.getY() + 0.5, blockpos.getZ() + 0.5) < minimum_distance;
//        return entity.getDistanceSq(blockpos.getX() + 0.5, blockpos.getY() + 0.5, blockpos.getZ() + 0.5) < getClose(entity, minimum_distance);
    }

    public static double getDistanceAABBToAABB(Entity entity_a, Entity entity_b)
    {
        return getDistanceAABBToAABB(entity_a.getEntityBoundingBox(), entity_b.getEntityBoundingBox());
    }

    public static double getDistanceAABBToAABB(AxisAlignedBB entity_a_aabb, AxisAlignedBB entity_b_aabb)
    {
//        AxisAlignedBB entity_a_aabb = entity_a.getEntityBoundingBox();
//        AxisAlignedBB entity_b_aabb = entity_b.getEntityBoundingBox();

        double dx = getDistanceAxis(entity_a_aabb.minX, entity_a_aabb.maxX, entity_b_aabb.minX, entity_b_aabb.maxX);
        double dy = getDistanceAxis(entity_a_aabb.minY, entity_a_aabb.maxY, entity_b_aabb.minY, entity_b_aabb.maxY);
        double dz = getDistanceAxis(entity_a_aabb.minZ, entity_a_aabb.maxZ, entity_b_aabb.minZ, entity_b_aabb.maxZ);

        return /*Math.sqrt(*/dx * dx + dy * dy + dz * dz/*)*/;
    }

    public static double getDistanceAxis(double min1, double max1, double min2, double max2)
    {
        if (max1 < min2)
        {
            return min2 - max1;
        }
        else if (max2 < min1)
        {
            return min1 - max2;
        }
        else
        {
            return 0;
        }
    }

//    public static boolean isTooClose(Entity entity, BlockPos blockpos, double minimum_distance)
//    {
//        return entity.getDistanceSq(blockpos.getX() + 0.5, blockpos.getY() + 0.5, blockpos.getZ() + 0.5) < getClose(entity, minimum_distance);
//    }
//
//    public static double getClose(Entity entity, double minimum_distance)
//    {
//        double width = entity.width / 2.0D;
//        double height = entity.height / 2.0D;
//        return (width + height + minimum_distance) * (width + height + minimum_distance);
//    }

//    @SideOnly(Side.CLIENT)
//    public static int rayAllTargetsView(Entity player, AxisAlignedBB[] axisalignedbb_array, byte max)
//    {
//        byte step = 0;
//        Vec3d view_vec3d = player.getLookVec();
//        Vec3d start_vec3d = new Vec3d(player.posX, player.posY + player.getEyeHeight(), player.posZ);
//        view_vec3d = view_vec3d.scale(0.05);
//
//        for (Vec3d end_vec3d = start_vec3d.add(view_vec3d); step < max; ++step)
//        {
//            for (int i = 0; i < axisalignedbb_array.length; ++i)
//            {
//                if (axisalignedbb_array[i].contains(end_vec3d))
//                {
//                    return i;
//                }
//            }
//            end_vec3d = end_vec3d.add(view_vec3d);
//        }
//
//        return -1;
//    }
}
