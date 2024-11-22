package com.nali.list.entity.ci;

import com.nali.da.IBothDaE;
import com.nali.da.IBothDaO;
import com.nali.da.IBothDaS;
import com.nali.render.RenderS;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.IMixES;
import com.nali.small.entity.memo.client.ClientE;
import com.nali.small.entity.memo.client.box.mix.MixBoxE;
import com.nali.small.entity.memo.client.ci.CI;
import com.nali.small.entity.memo.client.ci.MixCIE;
import com.nali.small.entity.memo.client.render.mix.MixRenderSe;
import net.minecraft.entity.Entity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.EntityDataManager;

//@SideOnly(Side.CLIENT)
public class CIEFrame
<
	BD extends IBothDaE & IBothDaO & IBothDaS,
	R extends RenderS<BD>,
	E extends Entity,
	I extends IMixE<BD, E> & IMixES,
	MC extends MixCIE<BD, R, E, I, MB, MR, C>,
	MB extends MixBoxE<BD, R, E, I, MC, MR, C>,
	MR extends MixRenderSe<BD, R, E, I, MC, MB, C>,
	C extends ClientE<BD, R, E, I, MC, MB, MR>
> extends CI<BD, R, E, I, MC, MB, MR, C>
{
	public static byte ID;

	public CIEFrame(C c)
	{
		super(c);
	}

	@Override
	public void init()
	{
	}

	@Override
	public void call()
	{
	}

	@Override
	public void onUpdate()
	{
		if (!this.c.fake)
		{
			I i = this.c.i;
			R r = this.c.r;

			EntityDataManager entitydatamanager = i.getE().getDataManager();
			r.scale = entitydatamanager.get(i.getFloatDataParameterArray()[0]);

			DataParameter<Integer>[] integer_dataparameter = i.getIntegerDataParameterArray();

			int[] frame_int_array = r.frame_int_array;
			for (int x = 0; x < this.c.i.getBD().S_MaxFrame(); ++x)
			{
				frame_int_array[x] = entitydatamanager.get(integer_dataparameter[x++]);
			}
		}
	}

	@Override
	public void onReadNBT()
	{
//		if (this.c.mc.fake)
//		{
		this.c.r.scale = this.c.i.getBD().E_Scale();
//		this.c.mr.updateSkinning();
//		}
	}
}
