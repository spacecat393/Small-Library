package com.nali.small.entity.memo.client.box.mix;

import com.nali.da.IBothDaE;
import com.nali.da.IBothDaO;
import com.nali.list.entity.si.SIEArea;
import com.nali.list.entity.si.SIESit;
import com.nali.render.RenderO;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.client.ClientE;
import com.nali.small.entity.memo.client.box.hit.HitE;
import com.nali.small.entity.memo.client.box.hit.HitESit;
import com.nali.small.entity.memo.client.ci.MixCIE;
import com.nali.small.entity.memo.client.render.mix.MixRenderE;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
public abstract class MixBoxE
<
	BD extends IBothDaE & IBothDaO,
	R extends RenderO<BD>,
	E extends Entity,
	I extends IMixE<BD, E>,
	MC extends MixCIE<BD, R, E, I, ?, MR, C>,
	MR extends MixRenderE<BD, R, E, I, MC, ?, C>,
	C extends ClientE<BD, R, E, I, MC, ?, MR>
>
{
	public long last_time;
	public C c;

	public List<HitE> hite_list = new ArrayList();

	public MixBoxE(C c)
	{
		this.c = c;
		this.init(c);
	}

	//s0-math
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
	//e0-math

//	public List<AxisAlignedBB> get()
//	{
//		List<AxisAlignedBB> axisalignedbb_list = new ArrayList();
////		axisalignedbb_list.add(this.getHeadAxisAlignedBB());
//		return axisalignedbb_list;
//	}

//	public void init(C c)
//	{
////		this.c = c;
////		this.sehit_list.add(new HitOlePat(this.c));
//	}

//	public void loop(SkinningEntities skinningentities)
//	{
//		this.axisalignedbb_array = this.get(skinningentities);
//	}

//	public void end()
//	{
////		byte i = this.c.bytele.SIT();
//		byte b = AIESit.ID;
//		byte[] byte_array = new byte[18];
//		byte_array[0] = SetWorkByte.ID;
//		ByteWriter.set(byte_array, this.c.uuid, 1);
//		byte_array[17] = b;
////		ByteWriter.set(byte_array, i, 17);
//		NetworkRegistry.I.sendToServer(new ServerMessage(byte_array));
//	}

	public HitE isOn(Entity entity, Vec3d stand_vec3d, Vec3d look_vec3d, boolean work)
	{
//		Vec3d player_vec3d = player_entity.getPositionEyes(1.0f);
//		Vec3d look_vec3d = player_entity.getLookVec();

		double max = Double.MAX_VALUE;
		int index = -1;
		List<AxisAlignedBB> axisalignedbb_list = this.getAxisAlignedBBList();
		for (int i = 0; i < axisalignedbb_list.size(); ++i)
		{
//			AxisAlignedBB axisalignedbb = box_list.get(i);
//			if (this.hite_list.get(i).should(player_entity, axisalignedbb) && axisalignedbb.calculateIntercept(player_vec3d, end_vec3d) != null)
			AxisAlignedBB axisalignedbb = axisalignedbb_list.get(i);
			if (this.hite_list.get(i).should(entity) && ray(axisalignedbb, stand_vec3d, look_vec3d))
			{
				double new_max = SIEArea.getDistanceAABBToAABB(entity.getEntityBoundingBox(), axisalignedbb);
				if (new_max < max)
				{
					index = i;
					max = new_max;
				}
			}
		}

		if (index != -1)
		{
			HitE hite = this.hite_list.get(index);
			if (work)
			{
				hite.run(entity);
				this.last_time = Minecraft.getSystemTime();
			}
//			return true;
			return hite;
		}

		long new_time = Minecraft.getSystemTime();
		if (new_time - this.last_time >= 1000)//1sec
		{
			if (work)
			{
				this.c.sendSSI(new byte[1 + 8 + 1], SIESit.ID);
				this.last_time = new_time;
			}

			return HitESit.HITESIT;
		}

		if (index != -1)
		{
			HitE hite = this.hite_list.get(index);
			return hite;
		}
//		this.c.sendPacketUUID(AIESit.ID);
//		return true;
		return null;
	}

	public abstract List<AxisAlignedBB> getAxisAlignedBBList();
	public abstract void init(C c);

//	public AxisAlignedBB getHeadAxisAlignedBB()
//	{
//		E e = this.c.i.getE();
//		double hw = e.width / 2.0F + 0.001F;
//		double y = e.posY + e.height / 1.125F;
//
//		return new AxisAlignedBB
//		(
//			e.posX - hw, y, e.posZ - hw,
//			e.posX + hw, e.posY + e.height + 0.001F, e.posZ + hw
//		);
//	}
}
