package com.nali.list.key;

import com.nali.Nali;
import com.nali.key.Key;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import java.util.List;

import static com.nali.small.entity.EntityMath.getDistanceAABBToAABB;

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
		if (Minecraft.getMinecraft().currentScreen == null && world != null && Keyboard.isKeyDown(Keyboard.KEY_T))
		{
			List<Entity> entity_list = world.loadedEntityList;
			EntityPlayerSP entityplayersp = minecraft.player;

			Vec3d player_vec3d = entityplayersp.getPositionEyes(1.0f);
			Vec3d look_vec3d = entityplayersp.getLookVec();

			Vec3d end_vec3d = player_vec3d.add(look_vec3d.scale(32.0D));

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

				AxisAlignedBB axisalignedbb = entity.getEntityBoundingBox();
				if (axisalignedbb.calculateIntercept(player_vec3d, end_vec3d) != null)
				{
					double new_max = getDistanceAABBToAABB(entityplayersp, entity);
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
