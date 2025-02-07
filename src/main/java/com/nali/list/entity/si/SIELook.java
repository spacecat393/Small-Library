package com.nali.list.entity.si;

import com.nali.da.IBothDaE;
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

	public double x, y, z;
	public byte max;
	public float yaw, pitch;

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

	public void set(double x, double y, double z, byte max)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.max = max;
		this.s.ms.state &= 255-2;

		E e = this.s.i.getE();
//		float yaw = (float)Math.toDegrees(Math.atan2(-this.x, this.z)) - ((EntityLe)e).rotation_yaw_head;
		float yaw = (float)Math.toDegrees(Math.atan2(-this.x, this.z)) - e.rotationYaw;
		float delta = ((yaw + 180) % 360 + 360) % 360 - 180;
		if (delta > 180)
		{
			delta -= 360;
		}
		this.yaw = delta / this.max;
//		this.yaw = ((float)Math.toDegrees(Math.atan2(-this.x, this.z)) - ((EntityLe)e).rotation_yaw_head) / this.max;
		this.pitch = ((float)Math.toDegrees(Math.atan2(-this.y, Math.sqrt(this.x * this.x + this.z * this.z))) - e.rotationPitch) / this.max;
	}

	@Override
	public void onUpdate()
	{
		E e = this.s.i.getE();
		if (this.s.isMove(e))
		{
			if (this.max > 0)
			{
				Entity riding_entity = e.getRidingEntity();
				if (riding_entity != null)
				{
					riding_entity.setRotationYawHead(riding_entity.rotationYaw);
					e.rotationYaw = riding_entity.rotationYaw;
				}
				else
				{
//					Nali.warn("this.yaw " + this.yaw);
//					Nali.warn("((EntityLe)e).rotation_yaw_head " + ((EntityLe)e).rotation_yaw_head);
//					((EntityLe)e).rotation_yaw_head += this.yaw;
//					((EntityLe)e).rotation_yaw_head = (float)Math.toDegrees(Math.atan2(-this.x, this.z));
					e.rotationYaw += this.yaw;
					e.rotationPitch += this.pitch;
				}
				--this.max;
			}
		}

		this.syncLook(e);
//		Nali.warn("e.rotationYaw " + e.rotationYaw);
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

	public void syncLook(E e)
	{
//		((EntityLe)e).rotation_yaw_head = ((((EntityLe)e).rotation_yaw_head + 180) % 360 + 360) % 360 - 180;
//		e.rotationYaw = ((EntityLe)e).rotation_yaw_head;
		e.rotationPitch = ((e.rotationPitch + 180) % 360 + 360) % 360 - 180;
		e.rotationYaw = ((e.rotationYaw + 180) % 360 + 360) % 360 - 180;
	}
}
