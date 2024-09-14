package com.nali.list.entity.si;

import com.nali.da.IBothDaNe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.IMixESoundDa;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.SI;
import com.nali.small.entity.memo.server.si.SIData;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.sound.ISoundDaLe;
import net.minecraft.entity.EntityLivingBase;

public class SILeRandomLook
<
	SD extends ISoundDaLe,
	BD extends IBothDaNe,
	E extends EntityLivingBase,
	I extends IMixE<BD, E> & IMixESoundDa<SD>,
	S extends ServerLe<SD, BD, E, I, MS>,
	MS extends MixSIE<BD, E, I, S>
> extends SI<BD, E, I, S, MS>
{
	public static byte ID;

	public SILeLook<SD, BD, E, I, S, MS> silelook;

	public int tick;
	public byte state;//on look
	public double x, y, z;
//	public byte[] bypass_int_array = {e.bothentitiesmemory.workbytes.SIT(), e.bothentitiesmemory.workbytes.PROTECT()};

	public SILeRandomLook(S s)
	{
		super(s);
	}

	@Override
	public void init()
	{
		this.silelook = (SILeLook<SD, BD, E, I, S, MS>)this.s.ms.si_map.get(SILeLook.ID);
	}

	@Override
	public void call()
	{

	}

	@Override
	public void onUpdate()
	{
		E e = this.s.i.getE();
		if (this.s.isMove(e))
		{
			if ((this.s.ms.state & 2) == 2 && (this.state & 1) == 1)
//			if (serverentitiesmemory.isWorkBypass(serverentitiesmemory.workbytes.RANDOM_LOOK(), this.bypass_int_array))
			{
				if (--this.tick <= 0)
				{
					this.x = e.posX + e.getRNG().nextInt(5) - e.getRNG().nextInt(5);
					this.y = e.posY + e.getRNG().nextInt(5) - e.getRNG().nextInt(5);
					this.z = e.posZ + e.getRNG().nextInt(5) - e.getRNG().nextInt(5);
					this.tick = e.getRNG().nextInt(100) + 100;
//					this.look = true;
					this.s.ms.state |= 2;
				}

//				if (this.look)
				if ((this.state & 2) == 2)
				{
					this.silelook.set(this.x, this.y, this.z, 1.0F);
				}
	//			this.look = false;
				this.s.ms.state &= 255-2;
			}

//	//		if (!this.look)
//	//		{
//			serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.RANDOM_LOOK() / 8] &= (byte)(255 - Math.pow(2, serverentitiesmemory.workbytes.RANDOM_LOOK() % 8));//0
//	//		}
		}
	}

	@Override
	public void writeFile(SIData sidata)
	{
		sidata.byte_array[sidata.index++] = this.state;
	}

	@Override
	public void readFile(SIData sidata)
	{
		this.state = sidata.byte_array[sidata.index++];
	}

	@Override
	public int size()
	{
		return 1;
	}
}
