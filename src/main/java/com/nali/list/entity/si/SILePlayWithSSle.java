package com.nali.list.entity.si;

import com.nali.da.IBothDaE;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;
import net.minecraft.entity.EntityLivingBase;

public class SILePlayWithSSle
<
	R2 extends SIEPlayWithRSe<S, BD, E, I, MS, BD2, E2, I2, S2, A2>,
	S2 extends ServerLe<BD2, E2, I2, A2>,
	BD2 extends IBothDaE,
	E2 extends EntityLivingBase,
	I2 extends IMixE<BD2, E2>,
	A2 extends MixSIE<BD2, E2, I2, S2>,
	BD extends IBothDaE,
	E extends EntityLivingBase,
	I extends IMixE<BD, E>,
	S extends ServerLe<BD, E, I, MS>,
	MS extends MixSIE<BD, E, I, S>
> extends SILePlayWithSSe<R2, S2, BD2, E2, I2, A2, BD, E, I, S, MS>
{
	public static byte ID;

	public SILePlayWithSSle(S s)
	{
		super(s);
	}

	public void onPlay()
	{
//		I i = this.s.i;
//		E e = i.getE();
//		I2 i2 = this.s2.i;
//		E2 e2 = i2.getE();
		super.onPlay();
//		this.s.i.getE().renderYawOffset = this.s2.i.getE().renderYawOffset;
		this.s.i.getE().rotationYawHead = this.s2.i.getE().rotationYawHead;
//		e.renderYawOffset = e2.renderYawOffset;
//		DataParameter<Byte> byte_dataparameter = i.getByteDataParameterArray()[0];
//		EntityDataManager entitydatamanager = e.getDataManager();
//		byte[] sync_byte_array = this.s.sync_byte_array;
//		sync_byte_array[0] = entitydatamanager.get(byte_dataparameter);
//		if ((sync_byte_array[0] & 128) == 0)
//		{
//			sync_byte_array[0] ^= (byte)128;
//			entitydatamanager.set(byte_dataparameter, sync_byte_array[0]);
//		}
//		entitydatamanager.set(i.getIntegerDataParameterArray()[1], e2.getDataManager().get(i2.getIntegerDataParameterArray()[0]));
//		entitydatamanager.set(i.getFloatDataParameterArray()[1], e2.getDataManager().get(i2.getFloatDataParameterArray()[0]));
	}
}
