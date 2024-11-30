package com.nali.small.entity;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityMath
{
//	public static boolean isTooClose(Entity entity0, Entity entity1, double minimum_distance)
//	{
//		return entity0.getDistanceSq(entity1) < getClose(entity0, entity1, minimum_distance);
//	}
//
//	public static double getClose(Entity entity0, Entity entity1, double minimum_distance)
//	{
//		double width = (entity0.width + entity1.width) / 2.0D;
//		double height = (entity0.height + entity1.height) / 2.0D;
//		return (width + height + minimum_distance) * (width + height + minimum_distance);
//	}

	public static boolean isInArea(double x, double y, double z, BlockPos blockpos, double minimum_distance)
	{
		double d0 = x - blockpos.getX() + 0.5;
		double d1 = y - blockpos.getY() + 0.5;
		double d2 = z - blockpos.getZ() + 0.5;
		return d0 * d0 + d1 * d1 + d2 * d2 < minimum_distance;
	}

	public static boolean isInArea(BlockPos main_blockpos, BlockPos blockpos, double minimum_distance)
	{
		return main_blockpos.distanceSq(blockpos.getX() + 0.5, blockpos.getY() + 0.5, blockpos.getZ() + 0.5) < minimum_distance;
	}

	public static boolean isInArea(Entity entity, BlockPos blockpos, double minimum_distance)
	{
		return entity.getDistanceSq(blockpos.getX() + 0.5, blockpos.getY() + 0.5, blockpos.getZ() + 0.5) < minimum_distance;
//		return entity.getDistanceSq(blockpos.getX() + 0.5, blockpos.getY() + 0.5, blockpos.getZ() + 0.5) < getClose(entity, minimum_distance);
	}

	public static double getDistanceAABBToAABB(Entity entity_a, Entity entity_b)
	{
		return getDistanceAABBToAABB(entity_a.getEntityBoundingBox(), entity_b.getEntityBoundingBox());
	}

	public static double getDistanceAABBToAABB(AxisAlignedBB entity_a_aabb, AxisAlignedBB entity_b_aabb)
	{
//		if
//		(
//			entity_a_aabb.minX < entity_b_aabb.maxX &&
//			entity_a_aabb.minY < entity_b_aabb.maxY &&
//			entity_a_aabb.minZ < entity_b_aabb.maxZ &&
//			entity_a_aabb.maxX > entity_b_aabb.minX &&
//			entity_a_aabb.maxY > entity_b_aabb.minY &&
//			entity_a_aabb.maxZ > entity_b_aabb.minZ
//		)

		double dx = getDistanceAxis(entity_a_aabb.minX, entity_a_aabb.maxX, entity_b_aabb.minX, entity_b_aabb.maxX);
		double dy = getDistanceAxis(entity_a_aabb.minY, entity_a_aabb.maxY, entity_b_aabb.minY, entity_b_aabb.maxY);
		double dz = getDistanceAxis(entity_a_aabb.minZ, entity_a_aabb.maxZ, entity_b_aabb.minZ, entity_b_aabb.maxZ);

		return dx * dx + dy * dy + dz * dz;
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

	@SideOnly(Side.CLIENT)
	public static boolean ray(AxisAlignedBB box_axisalignedbb, Vec3d stand_vec3d, Vec3d look_vec3d)
	{
		//s0-box
		//min a (x y z)
		//max b (x y z)
		//a point to b
		//scalar = b - a

		//scalar can use as center between a b but need / 2 and + to a
		double box_scalar_x = box_axisalignedbb.maxX - box_axisalignedbb.minX;
		double box_scalar_y = box_axisalignedbb.maxY - box_axisalignedbb.minY;
		double box_scalar_z = box_axisalignedbb.maxZ - box_axisalignedbb.minZ;

		double box_center_x = box_axisalignedbb.minX + box_scalar_x / 2;
		double box_center_y = box_axisalignedbb.minY + box_scalar_y / 2;
		double box_center_z = box_axisalignedbb.minZ + box_scalar_z / 2;
		//e0-box

		//s0-player
		//player_double_array
		//pos (x y z)
		//look (x y z)
//		double player_to_look_scalar_x = player_double_array[3];
//		double player_to_look_scalar_y = player_double_array[4];
//		double player_to_look_scalar_z = player_double_array[5];

		double player_to_box_center_scalar_x = box_center_x - stand_vec3d.x;
		double player_to_box_center_scalar_y = box_center_y - stand_vec3d.y;
		double player_to_box_center_scalar_z = box_center_z - stand_vec3d.z;

		double player_to_box_center_scalar_length = Math.sqrt(player_to_box_center_scalar_x * player_to_box_center_scalar_x + player_to_box_center_scalar_y * player_to_box_center_scalar_y + player_to_box_center_scalar_z * player_to_box_center_scalar_z);
		double player_to_look_scalar_length = Math.sqrt(look_vec3d.x * look_vec3d.x + look_vec3d.y * look_vec3d.y + look_vec3d.z * look_vec3d.z);

		double player_scale = player_to_box_center_scalar_length - player_to_look_scalar_length;
		//e0-player

		double look_to_center_x = stand_vec3d.x + look_vec3d.x * player_scale;
		double look_to_center_y = stand_vec3d.y + look_vec3d.y * player_scale;
		double look_to_center_z = stand_vec3d.z + look_vec3d.z * player_scale;

		double final_scalar_x = box_center_x - look_to_center_x;
		double final_scalar_y = box_center_y - look_to_center_y;
		double final_scalar_z = box_center_z - look_to_center_z;

//		Minecraft.getMinecraft().world.spawnParticle(EnumParticleTypes.BARRIER, look_to_center_x, look_to_center_y, look_to_center_z, 0.0D, 0.0D, 0.0D, new int[0]);

		return
			final_scalar_x >= -1 && final_scalar_x <= 1 &&
			final_scalar_y >= -1 && final_scalar_y <= 1 &&
			final_scalar_z >= -1 && final_scalar_z <= 1;
//		return look_to_center_x >= box_double_array[0] && look_to_center_x <= box_double_array[3] &&
//			look_to_center_y >= box_double_array[1] && look_to_center_y <= box_double_array[4] &&
//			look_to_center_z >= box_double_array[2] && look_to_center_z <= box_double_array[5];
	}

	public static float normalize(float pitch, float degrees)
	{
		float half_degrees = degrees / 2.0F;
		pitch = pitch % degrees;

		if (pitch >= half_degrees)
		{
			pitch -= degrees;
		}

		if (pitch < -half_degrees)
		{
			pitch += degrees;
		}

		return pitch;
	}
//	public static float normalize(float yaw)
//	{
//		yaw = yaw % 360;
//
//		if (yaw >= 180)
//		{
//			yaw -= 360;
//		}
//
//		if (yaw < -180)
//		{
//			yaw += 360;
//		}
//
//		return yaw;
//	}

	public static float interpolateRotation(float old_yaw, float yaw, float partial_ticks)
	{
		float f = yaw - old_yaw;

		while (f < -180.0F)
		{
			f += 360.0F;
		}

		while (f >= 180.0F)
		{
			f -= 360.0F;
		}

		return old_yaw + partial_ticks * f;
	}
}
