package com.nali.list.entity.ci;

import com.nali.da.IBothDaE;
import com.nali.da.IBothDaO;
import com.nali.da.IBothDaS;
import com.nali.da.IBothDaSe;
import com.nali.render.RenderS;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.IMixES;
import com.nali.small.entity.memo.client.ClientE;
import com.nali.small.entity.memo.client.IClientS;
import com.nali.small.entity.memo.client.box.mix.MixBoxE;
import com.nali.small.entity.memo.client.ci.CI;
import com.nali.small.entity.memo.client.ci.MixCIE;
import com.nali.small.entity.memo.client.render.mix.MixRenderSe;
import com.nali.small.render.IRenderS;
import com.nali.system.bytes.ByteReader;
import net.minecraft.entity.Entity;

//@SideOnly(Side.CLIENT)
public class CIEFrame
<
	BD extends IBothDaE & IBothDaO & IBothDaS & IBothDaSe,
	R extends RenderS<BD> & IRenderS<BD, R>,
	E extends Entity,
	I extends IMixE<BD, E> & IMixES,
	MC extends MixCIE<BD, R, E, I, MB, MR, C>,
	MB extends MixBoxE<BD, R, E, I, MC, MR, C>,
	MR extends MixRenderSe<BD, R, E, I, MC, MB, C>,
	C extends ClientE<BD, R, E, I, MC, MB, MR> & IClientS
> extends CI<BD, R, E, I, MC, MB, MR, C>
{
	public static byte ID;

	public CIEFrame(C c)
	{
		super(c);
	}

//	@Override
//	public void init()
//	{
//	}

	@Override
	public void call()
	{
	}

	@Override
	public void onUpdate()
	{
//		if (!this.c.fake)
//		{
		I i = this.c.i;
		R r = this.c.r;
		BD bd = i.getBD();

//			EntityDataManager entitydatamanager = i.getE().getDataManager();
//			DataParameter<Byte>[] byte_dataparameter = i.getByteDataParameterArray();

//			int scale = 0;
//			for (byte b = 0; b < 4; ++b)
//			{
////				scale |= (entitydatamanager.get(byte_dataparameter[b]) & 0xFF) << (b * 8);
//				scale |= (this.c.sync_byte_array[b] & 0xFF) << (b * 8);
//			}
//			r.scale = scale;
		r.scale = ByteReader.getFloat(this.c.sync_byte_array, 0);
//		if (r.scale == 0)
//		{
//			r.scale = 1;
//		}

		byte[] action_byte_array = r.action_byte_array;
		short[] mix_key_short_array = r.mix_key_short_array;
		float[] line_float_array = r.line_float_array;
//			short key = 0;
		for (int x = 0; x < bd.S_MaxFrame(); ++x)
		{
//				byte b = (byte)(x % 2);
////				key |= (entitydatamanager.get(byte_dataparameter[bd.Se_SyncIndex() + x]) & 0xFF) << (b * 8);
//				key |= (this.c.sync_byte_array[bd.Se_SyncIndex() + x] & 0xFF) << (b * 8);
//
//				if (x != 0 && b == 0)
//				{
//					key_short_array[x] = key;
//					key = 0;
//				}
			int x2 = x * 2;
			int x2_1 = x2 + 1;
			int x3 = x * 3;
			int x4p1 = x * (4 + 1);
			action_byte_array[x3] = this.c.sync_byte_array[bd.Se_SyncIndex() + x4p1];

			line_float_array[x3] = ByteReader.getFloat(this.c.sync_byte_array, bd.Se_SyncIndex() + x4p1 + 1);
			byte action = action_byte_array[x3];
			short
				start = bd.S_FixKeyShortArray()[action],
				end = bd.S_FixKeyShortArray()[action + 1];
			mix_key_short_array[x2] = start;
			mix_key_short_array[x2_1] = end;
//			action_byte_array[x2_1] = (byte)(start < end ? 1 : 0);
//			action_byte_array[x2_1] = bd.S_ActionState()[action];
			action_byte_array[x3 + 2] = this.c.getActionStateByteArray()[action / 2];
		}
//		}
	}

//	@Override
//	public void onReadNBT()
//	{
////		if (this.c.mc.fake)
////		{
//		this.c.r.scale = this.c.i.getBD().E_Scale();
////		this.c.mr.updateSkinning();
////		}
//	}
}
