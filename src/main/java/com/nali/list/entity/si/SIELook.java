package com.nali.list.entity.si;

import com.nali.da.IBothDaE;
import com.nali.small.entity.EntityMath;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.small.entity.memo.server.si.SI;
import com.nali.small.entity.memo.server.si.SIData;
import com.nali.system.bytes.ByteReader;
import net.minecraft.entity.Entity;

public class SIELook
<
	BD extends IBothDaE,
	E extends Entity,
	I extends IMixE<BD, E>,
	S extends ServerE<BD, E, I, MS>,
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
//	public float max;
	public byte max;
//	public boolean done;

	public SIELook(S s)
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

	public void set(double x, double y, double z, byte max)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.max = max;
		this.looking = true;
//		this.done = false;
//		this.onUpdate();//need sync done
		this.s.ms.state &= 255-2;
	}

	@Override
	public void onUpdate()
	{
		E e = this.s.i.getE();
		if (this.s.isMove(e))
		{
//			if (this.max > 0)
//			{
////				int yaw;
//
//				this.looking = false;
////				double xx = this.x - e.posX;
////				double yy = this.y - e.posY;
////				double zz = this.z - e.posZ;
////				double xx = this.x + e.posX;
////				double yy = this.y + e.posY;
////				double zz = this.z + e.posZ;
////				float new_yaw = (float)Math.toDegrees(Math.atan2(zz, xx));
////				float new_yaw = (float)Math.toDegrees(Math.atan2(xx, zz));
//				float new_yaw;
////				float new_yaw = (float)Math.toDegrees(Math.atan2(this.z, this.x));
////				float new_pitch = (float)Math.toDegrees(Math.asin(yy));
//				float new_pitch;
//				if (this.x == 0 && this.z == 0)
//				{
//					new_yaw = -90;
//				}
//				else
//				{
//					new_yaw = ((float)Math.toDegrees(Math.atan2(this.z, this.x)) - 90) / this.max;
//				}
//
//				if (this.y == 0)
//				{
//					new_pitch = 0;
//				}
//				else
//				{
//					new_pitch = (float)Math.toDegrees(Math.asin(this.y)) / this.max;
//				}
//
////				Nali.warn("new_yaw " + new_yaw);
////				Nali.warn("new_pitch " + new_pitch);
////				double d3 = MathHelper.sqrt(d0 * d0 + d2 * d2);
////				float f = (float)(MathHelper.atan2(d2, d0) * (180.0D / Math.PI)) - 90.0F;
////				float f1 = (float)(-(MathHelper.atan2(d1, d3) * (180.0D / Math.PI)));
//
//				Entity riding_entity = e.getRidingEntity();
//				if (riding_entity != null)
//				{
////					yaw = (int)riding_entity.rotationYaw;
//////					riding_entity.rotationYaw = this.limitAngle(riding_entity.rotationYaw, f, this.max);
//////					riding_entity.rotationPitch = this.limitAngle(riding_entity.rotationPitch, f1, this.max);
//					riding_entity.setRotationYawHead(riding_entity.rotationYaw);
//					e.rotationYaw = riding_entity.rotationYaw;
//
////					if (yaw == (int)riding_entity.rotationYaw)
////					{
////						this.done = true;
////					}
//				}
//				else
//				{
////					yaw = (int)e.rotationYaw;
//////					e.rotationYaw = this.limitAngle(e.rotationYaw, f, this.max);
//////					e.rotationPitch = this.limitAngle(e.rotationPitch, f1, this.max);
//
//					e.rotationYaw = new_yaw;
//					e.rotationPitch = new_pitch;
//
////					if (yaw == (int)e.rotationYaw)
////					{
////						this.done = true;
////					}
//				}
//				--this.max;
//			}
		}

		this.syncLook(e);
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

//	public float limitAngle(float source_angle, float target_angle, float maximum_change)
//	{
//		float f = MathHelper.wrapDegrees(target_angle - source_angle);
//
//		if (f > maximum_change)
//		{
//			f = maximum_change;
//		}
//
//		if (f < -maximum_change)
//		{
//			f = -maximum_change;
//		}
//
////		float f1 = source_angle + f;
//
////		if (f1 < 0.0F)
////		{
////			f1 += 360.0F;
////		}
////		else if (f1 > 360.0F)
////		{
////			f1 -= 360.0F;
////		}
//
////		return f1;
//		return source_angle + f;
//	}

	public void syncLook(E e)
	{
		e.rotationPitch = EntityMath.normalize(e.rotationPitch, 180.0F);
		e.rotationYaw = EntityMath.normalize(e.rotationYaw, 360.0F);
	}
}
