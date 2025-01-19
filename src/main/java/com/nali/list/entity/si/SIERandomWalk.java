package com.nali.list.entity.si;

import com.nali.da.IBothDaE;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.small.entity.memo.server.si.SI;
import com.nali.small.entity.memo.server.si.SIData;
import net.minecraft.entity.Entity;

import java.util.Random;

import static com.nali.small.entity.EntityMath.isInArea;

public class SIERandomWalk
<
	BD extends IBothDaE,
	E extends Entity,
	I extends IMixE<BD, E>,
	S extends ServerE<BD, E, I, MS>,
	MS extends MixSIE<BD, E, I, S>
> extends SI<BD, E, I, S, MS>
{
	public static byte ID;

	public SIESetLocation<BD, E, I, S, MS> silesetlocation;
	public SIEFindMove<BD, E, I, S, MS> siefindmove;

	public int tick;
	public byte state;//on walk

	public SIERandomWalk(S s)
	{
		super(s);
	}

	@Override
	public void init()
	{
		this.silesetlocation = (SIESetLocation<BD, E, I, S, MS>)this.s.ms.si_map.get(SIESetLocation.ID);
		this.siefindmove = (SIEFindMove<BD, E, I, S, MS>)this.s.ms.si_map.get(SIEFindMove.ID);
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
					this.siefindmove.endGoal();
					this.state &= 255-2;
//					this.walk = false;
				}

				if (--this.tick <= 0)
				{
					Random random = e.world.rand;
					double x = e.posX + random.nextInt(5) - random.nextInt(5),
					y = e.posY + random.nextInt(5) - random.nextInt(5),
					z = e.posZ + random.nextInt(5) - random.nextInt(5);

					if (this.silesetlocation.far == 0 || this.silesetlocation.blockpos == null || isInArea(x, y, z, this.silesetlocation.blockpos, this.silesetlocation.far))
					{
						this.siefindmove.setGoal(x, y, z);
					}
					this.tick = random.nextInt(100) + 100;
					this.state |= 2;
//					this.walk = true;
				}
			}
			else if ((this.state & 2) == 2)
			{
				this.siefindmove.endGoal();
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
