package com.nali.small.entity.memo.server.si;

import com.nali.da.IBothDaE;
import com.nali.list.entity.si.SIELook;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerLe;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.EntityDataManager;

public class SILeLook
<
	BD extends IBothDaE,
	E extends EntityLe,
	I extends IMixE<BD, E>,
	S extends ServerLe<BD, E, I, MS>,
	MS extends MixSIE<BD, E, I, S>
> extends SIELook<BD, E, I, S, MS>
{
	public SILeLook(S s)
	{
		super(s);
	}

	@Override
	public void syncLook(E e)
	{
		//need support other yaw later
//		e.rotationPitch = EntityMath.normalize(e.rotationPitch, 180.0F);
		e.rotationPitch = ((e.rotationPitch + 180) % 360 + 360) % 360 - 180;
//		float difference = e.rotation_yaw_head - e.rotationYaw;

//		float new_difference = (e.rotation_yaw_head + 360) % 360 - (e.rotationYaw + 360) % 360;
//		if (Math.abs(new_difference) < Math.abs(difference))
//		{
//			difference = ((new_difference + 180) % 360 + 360) % 360 - 180;
//		}
//		if (difference > 45.0F)
//		{
//			difference = 45.0F;
//		}
//		else if (difference < -45.0F)
//		{
//			difference = -45.0F;
//		}

//		e.rotation_yaw_head = EntityMath.normalize(e.rotationYaw + difference, 360.0F);
//		e.rotationYaw = EntityMath.normalize(e.rotationYaw, 360.0F);
//		e.rotation_yaw_head = ((e.rotationYaw + difference + 180) % 360 + 360) % 360 - 180;
		e.rotation_yaw_head = ((e.rotationYaw + 180) % 360 + 360) % 360 - 180;
		e.rotationYaw = ((e.rotationYaw + 180) % 360 + 360) % 360 - 180;

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
