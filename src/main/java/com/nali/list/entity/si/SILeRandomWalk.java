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

import static com.nali.small.entity.EntityMath.isInArea;

public class SILeRandomWalk
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

	public SILeSetLocation<SD, BD, E, I, S, MS> silesetlocation;
	public SILeFindMove<SD, BD, E, I, S, MS> silefindmove;

	public int tick;
	public byte state;//on walk

	public SILeRandomWalk(S s)
	{
		super(s);
	}

	@Override
	public void init()
	{
		this.silesetlocation = (SILeSetLocation<SD, BD, E, I, S, MS>)this.s.ms.si_map.get(SILeSetLocation.ID);
		this.silefindmove = (SILeFindMove<SD, BD, E, I, S, MS>)this.s.ms.si_map.get(SILeFindMove.ID);
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
	//		if (serverentitiesmemory.isWork(serverentitiesmemory.workbytes.RANDOM_WALK()))
			if ((this.s.ms.state & 1) == 1 && (this.state & 1) == 1)
			{
				if (e.ticksExisted % 100 == 0)
				{
					this.silefindmove.endGoal();
					this.state &= 255-2;
//					this.walk = false;
				}

				if (--this.tick <= 0)
				{
					double x = e.posX + e.getRNG().nextInt(5) - e.getRNG().nextInt(5),
					y = e.posY + e.getRNG().nextInt(5) - e.getRNG().nextInt(5),
					z = e.posZ + e.getRNG().nextInt(5) - e.getRNG().nextInt(5);

					if (this.silesetlocation.far == 0 || this.silesetlocation.blockpos == null || isInArea(x, y, z, this.silesetlocation.blockpos, this.silesetlocation.far))
					{
						this.silefindmove.setGoal(x, y, z);
					}
					this.tick = e.getRNG().nextInt(100) + 100;
					this.state |= 2;
//					this.walk = true;
				}
			}
			else if ((this.state & 2) == 2)
			{
				this.silefindmove.endGoal();
				this.state &= 255-2;
//				this.walk = false;
			}

	//		if (!this.walk)
	//		{
	//			serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.RANDOM_WALK() / 8] &= (byte)(255 - Math.pow(2, serverentitiesmemory.workbytes.RANDOM_WALK() % 8));//0
	//		}
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
