package com.nali.list.key;

import com.nali.Nali;
import com.nali.key.Key;
import com.nali.small.entity.EntityMath;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import java.util.List;

@SideOnly(Side.CLIENT)
public class SmallRayEntity extends Key
{
//	public static byte ID;
	public static int SELECT_ENTITY;

	@Override
	public void run()
	{
		Minecraft minecraft = Minecraft.getMinecraft();
		World world = minecraft.world;
		if (minecraft.currentScreen == null && world != null && Keyboard.isKeyDown(Keyboard.KEY_T))
		{
			List<Entity> entity_list = world.loadedEntityList;
			EntityPlayerSP entityplayersp = minecraft.player;

			Vec3d player_vec3d = entityplayersp.getPositionEyes(1.0F);
			Vec3d look_vec3d = entityplayersp.getLookVec();

//			double[] player_double_array = new double[]
//			{
////				player_vec3d.x + look_vec3d.x/* * 32.0D*/,
////				player_vec3d.y + look_vec3d.y/* * 32.0D*/,
////				player_vec3d.z + look_vec3d.z/* * 32.0D*/,
//				player_vec3d.x, player_vec3d.y, player_vec3d.z,
//				look_vec3d.x, look_vec3d.y, look_vec3d.z
//			};

			int index = -1;
//			List<Double> far_double_list = new ArrayList();
//			List<Entity> new_entity_list = new ArrayList();
			double max = Double.MAX_VALUE;
			for (int i = 0; i < entity_list.size(); ++i)
			{
				Entity entity = entity_list.get(i);
				if (entityplayersp.equals(entity))
				{
					continue;
				}

//				if (axisalignedbb.calculateIntercept(player_vec3d, end_vec3d) != null)
				if (EntityMath.ray(entity.getEntityBoundingBox(), player_vec3d, look_vec3d))
				{
					double new_max = EntityMath.getDistanceAABBToAABB(entityplayersp, entity);
					if (new_max < max)
					{
						index = i;
						max = new_max;
					}
//					far_double_list.add(getDistanceAABBToAABB(entityplayersp, entity));
//					new_entity_list.add(entity);
				}
			}

//			double min = Double.MAX_VALUE;
//			Entity min_entity = null;
//			for (int i = 0; i < new_entity_list.size(); ++i)
//			{
//				Entity entity = new_entity_list.get(i);
//				double new_min = far_double_list.get(i);
//				if (min < new_min)
//				{
//					min_entity = entity;
//					min = new_min;
//				}
//			}

//			if (min_entity != null)
			if (index != -1)
			{
				Entity entity = entity_list.get(index);
				SELECT_ENTITY = entity.getEntityId();
				Nali.LOGGER.info(entity.getName());
			}
		}
	}
}
