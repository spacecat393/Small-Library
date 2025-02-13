package com.nali.list.entity.si;

import com.nali.da.IBothDaE;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.small.entity.memo.server.si.SI;
import com.nali.small.entity.memo.server.si.SIData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

import java.util.Random;

public class SIERandomLook
<
	BD extends IBothDaE,
	E extends Entity,
	I extends IMixE<BD, E>,
	S extends ServerE<BD, E, I, MS>,
	MS extends MixSIE<BD, E, I, S>
> extends SI<BD, E, I, S, MS>
{
	public static byte ID;

	public SIELook<BD, E, I, S, MS> sielook;

	public int tick;
	public final static byte B_ON = 1;
	public final static byte B_LOOK = 2;
	public byte flag = B_ON;//on look
//	public double x, y, z;
//	public byte[] bypass_int_array = {e.bothentitiesmemory.workbytes.SIT(), e.bothentitiesmemory.workbytes.PROTECT()};

	public SIERandomLook(S s)
	{
		super(s);
	}

	@Override
	public void init()
	{
		this.sielook = (SIELook<BD, E, I, S, MS>)this.s.ms.si_map.get(SIELook.ID);
	}

	@Override
	public void call(EntityPlayerMP entityplayermp, byte[] byte_array)
	{
	}

	@Override
	public void onUpdate()
	{
		E e = this.s.i.getE();
		//add le
		if (this.s.isMove(e))
		{
			if ((this.s.ms.flag & MixSIE.B_SUB_WORK) == MixSIE.B_SUB_WORK && (this.flag & B_ON) == B_ON)
//			if (serverentitiesmemory.isWorkBypass(serverentitiesmemory.workbytes.RANDOM_LOOK(), this.bypass_int_array))
			{
				if (--this.tick <= 0)
				{
					Random random = e.world.rand;
//					this.x = e.posX + random.nextInt(5) - random.nextInt(5);
//					this.y = e.posY + random.nextInt(5) - random.nextInt(5);
//					this.z = e.posZ + random.nextInt(5) - random.nextInt(5);
//					this.x = random.nextInt(5) - random.nextInt(5);
//					this.y = random.nextInt(5) - random.nextInt(5);
//					this.z = random.nextInt(5) - random.nextInt(5);
					this.tick = random.nextInt(100) + 20;
//					this.look = true;
//					this.s.ms.state |= 2;
//					this.sielook.set(this.x, this.y, this.z, (byte)200);
					this.sielook.set(random.nextInt(5) - random.nextInt(5), random.nextInt(5) - random.nextInt(5), random.nextInt(5) - random.nextInt(5), (byte)(20 + random.nextInt(20)));
				}

	//			this.look = false;
//				this.s.ms.state &= 255-2;
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
		sidata.byte_array[sidata.index++] = this.flag;
	}

	@Override
	public void readFile(SIData sidata)
	{
		this.flag = sidata.byte_array[sidata.index++];
	}

	@Override
	public int size()
	{
		return 1;
	}
}
