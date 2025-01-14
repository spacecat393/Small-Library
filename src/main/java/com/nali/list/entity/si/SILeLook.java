package com.nali.list.entity.si;

import com.nali.da.IBothDaE;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.EntityMath;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.small.entity.memo.server.si.SI;
import com.nali.small.entity.memo.server.si.SIData;
import com.nali.system.bytes.ByteReader;
import net.minecraft.entity.Entity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.MathHelper;

public class SILeLook
<
	BD extends IBothDaE,
	E extends EntityLe,
	I extends IMixE<BD, E>,
	S extends ServerLe<BD, E, I, MS>,
	MS extends MixSIE<BD, E, I, S>
> extends SI<BD, E, I, S, MS>
{
	public static byte ID;

//	public static float EPSILON = 1e-6f;
//	public float deltaLookYaw;
//	public float deltaLookPitch;
	public boolean looking;
	public double x;
	public double y;
	public double z;
	public float max;
	public boolean done;

	public SILeLook(S s)
	{
		super(s);
	}

	@Override
	public void init()
	{

	}

	@Override
	public void call()
	{
		byte[] byte_array = this.s.ms.byte_array;
		int id = (int)ByteReader.getFloat(byte_array, 1 + 8 + 1);
		float f = ByteReader.getFloat(byte_array, 1 + 8 + 1 + 4);

		E e = this.s.i.getE();
		if (id == 1)
		{
			e.rotationPitch = f;
		}
		else if (id == 0)
		{
			e.rotationYaw = f;
		}
	}

//	public void setLookPositionWithEntity(Entity entityIn, float deltaYaw, float deltaPitch)
//	{
//		this.posX = entityIn.posX;
//
//		if (entityIn instanceof EntityLivingBase)
//		{
//			this.posY = entityIn.posY + (double)entityIn.getEyeHeight();
//		}
//		else
//		{
//			this.posY = (entityIn.getEntityBoundingBox().minY + entityIn.getEntityBoundingBox().maxY) / 2.0D;
//		}
//
//		this.posZ = entityIn.posZ;
//		this.deltaLookYaw = deltaYaw;
//		this.deltaLookPitch = deltaPitch;
//		this.looking = true;
//	}

//	public void setLookPosition(double x, double y, double z, float deltaYaw, float deltaPitch)
//	{
//		this.posX = x;
//		this.posY = y;
//		this.posZ = z;
//		this.deltaLookYaw = deltaYaw;
//		this.deltaLookPitch = deltaPitch;
//		this.looking = true;
//	}

	public void set(double x, double y, double z, float max)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.max = max;
		this.looking = true;
		this.done = false;
//		this.onUpdate();//need sync done
		this.s.ms.state &= 255-2;
	}

	@Override
	public void onUpdate()
	{
		E e = this.s.i.getE();
		if (this.s.isMove(e))
		{
	//		e.rotationPitch = 0.0F;
	////		e.renderYawOffset = e.rotationYaw;
	////		{
	////		e.renderYawOffset = this.updateRotation(e.rotationYawHead, e.renderYawOffset, 10.0F);
	////		}
	//
			if (this.looking)
			{
				int yaw/*, pitch*/;

				this.looking = false;
				double d0 = this.x - e.posX;
				double d1 = this.y - e.posY;
				double d2 = this.z - e.posZ;
				double d3 = MathHelper.sqrt(d0 * d0 + d2 * d2);
				float f = (float)(MathHelper.atan2(d2, d0) * (180.0D / Math.PI)) - 90.0F;
				float f1 = (float)(-(MathHelper.atan2(d1, d3) * (180.0D / Math.PI)));
	//			e.rotationPitch = this.updateRotation(e.rotationPitch, f1, this.deltaLookPitch);
	//			e.rotationYawHead = this.updateRotation(e.rotationYawHead, f, this.deltaLookYaw);

				Entity riding_entity = e.getRidingEntity();
				if (riding_entity != null)
	//		for (Entity entity : e.getPassengers())
				{
					yaw = (int)riding_entity.rotationYaw;
	//				pitch = riding_entity.rotationPitch;
					riding_entity.rotationYaw = this.limitAngle(riding_entity.rotationYaw, f, this.max);
					riding_entity.rotationPitch = this.limitAngle(riding_entity.rotationPitch, f1, this.max);
	//			entity.rotationYaw = e.rotationYaw;
					riding_entity.setRotationYawHead(riding_entity.rotationYaw);
					e.rotationYaw = riding_entity.rotationYaw;
	//				e.rotationYawHead = riding_entity.rotationYaw;

	//				if (yaw * yaw - riding_entity.rotationYaw * riding_entity.rotationYaw < 0.000001F && pitch * pitch - riding_entity.rotationPitch * riding_entity.rotationPitch < 0.000001F)
					if (yaw == (int)riding_entity.rotationYaw/* && pitch == riding_entity.rotationPitch*/)
	//				if (Math.abs(yaw - riding_entity.rotationYaw) < EPSILON && Math.abs(pitch - riding_entity.rotationPitch) < EPSILON)
					{
						this.done = true;
					}
				}
				else
				{
					yaw = (int)e.rotationYaw;
	//				pitch = e.rotationPitch;
					e.rotationYaw = this.limitAngle(e.rotationYaw, f, this.max);
					e.rotationPitch = this.limitAngle(e.rotationPitch, f1, this.max);
		//		e.prevRotationYaw = e.rotationYaw;
		//		e.renderYawOffset += 22.5F;
	//				e.rotationYawHead = e.rotationYaw;
		//		e.prevRotationYawHead = e.rotationYaw;
		//		e.renderYawOffset = e.rotationYaw;
		//		e.prevRenderYawOffset = e.rotationYaw;

	//				if (yaw * yaw - e.renderYawOffset * e.renderYawOffset < 0.000001F && pitch * pitch - e.rotationPitch * e.rotationPitch < 0.000001F)
					if (yaw == (int)e.rotationYaw/* && pitch == e.rotationPitch*/)
	//				if (Math.abs(yaw - e.rotationYaw) < EPSILON && Math.abs(pitch - e.rotationPitch) < EPSILON)
					{
						this.done = true;
					}
				}
			}
	//		e.skinningentitiesbody.onUpdate();//.updateRenderAngles();
	//		e.rotationYawHead = e.rotationYaw;
	//		e.renderYawOffset = e.rotationYaw;
	//		else
	//		{
	//			e.rotationYawHead = this.updateRotation(e.rotationYawHead, e.renderYawOffset, 10.0F);
	//		}
	//
	////		float f2 = MathHelper.wrapDegrees(e.rotationYawHead - e.renderYawOffset);
	//
	////		if (!e.getNavigator().noPath())
	////		{
	////			if (f2 < -75.0F)
	////			{
	////				e.rotationYawHead = e.renderYawOffset - 75.0F;
	////			}
	////
	////			if (f2 > 75.0F)
	////			{
	////				e.rotationYawHead = e.renderYawOffset + 75.0F;
	////			}
	////		}

//			E e = this.i.getE();
//		++e.rotationYaw;
//		if (this.fix_yaw_head != 0)
//		{
//			this.rotation_yaw_head = this.fix_yaw_head;
//			e.prev_rotation_yaw_head = this.fix_yaw_head;
//		}
			//normal -180 180 -90 90
			//yaw move/body
			//yawhead head
			e.rotationPitch = EntityMath.normalize(e.rotationPitch, 180.0F);
//		this.rotation_yaw_head = EntityMath.normalize(this.rotation_yaw_head, 360.0F);

//		float difference = e.rotationYaw - this.rotation_yaw_head - 45.0F;
			float difference = e.rotation_yaw_head - e.rotationYaw;

			float new_difference = (e.rotation_yaw_head + 360) % 360 - (e.rotationYaw + 360) % 360;
			if (Math.abs(new_difference) < Math.abs(difference))
			{
				difference = ((new_difference + 180) % 360 + 360) % 360 - 180;
			}
			if (difference > 45.0F)
			{
				difference = 45.0F;
			}
			else if (difference < -45.0F)
			{
				difference = -45.0F;
			}

			//rotationYawHead not sync in correct
			//create new rotationYawHead
			e.rotation_yaw_head = EntityMath.normalize(e.rotationYaw + difference, 360.0F);
//		this.rotation_yaw_head = EntityMath.normalize(this.rotation_yaw_head + difference, 360.0F);
			e.rotationYaw = EntityMath.normalize(e.rotationYaw, 360.0F);
//		e.prev_rotation_yaw_head = this.rotation_yaw_head;
//		e.prevRotationYaw = e.rotationYaw;
//		Nali.warn("YH " + this.rotation_yaw_head);

			int rotation_yaw_head_int = Float.floatToIntBits(e.rotation_yaw_head);
			I i = this.s.i;
			EntityDataManager entitydatamanager = i.getE().getDataManager();
			DataParameter<Byte>[] byte_dataparameter_array = i.getByteDataParameterArray();

			entitydatamanager.set(byte_dataparameter_array[4], (byte)(rotation_yaw_head_int & 0xFF));
			entitydatamanager.set(byte_dataparameter_array[5], (byte)((rotation_yaw_head_int >> 8) & 0xFF));
			entitydatamanager.set(byte_dataparameter_array[6], (byte)((rotation_yaw_head_int >> 8*2) & 0xFF));
			entitydatamanager.set(byte_dataparameter_array[7], (byte)((rotation_yaw_head_int >> 8*3) & 0xFF));
		}
	}

	@Override
	public void writeFile(SIData sidata)
	{
	}

	@Override
	public void readFile(SIData sidata)
	{
	}

	@Override
	public int size()
	{
		return 0;
	}

	public float limitAngle(float source_angle, float target_angle, float maximum_change)
	{
		float f = MathHelper.wrapDegrees(target_angle - source_angle);

		if (f > maximum_change)
		{
			f = maximum_change;
		}

		if (f < -maximum_change)
		{
			f = -maximum_change;
		}

//		float f1 = source_angle + f;

//		if (f1 < 0.0F)
//		{
//			f1 += 360.0F;
//		}
//		else if (f1 > 360.0F)
//		{
//			f1 -= 360.0F;
//		}

//		return f1;
		return source_angle + f;
	}
}
