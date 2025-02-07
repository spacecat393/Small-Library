package com.nali.list.entity.si;

import com.nali.da.IBothDaE;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.small.entity.memo.server.si.SI;
import com.nali.small.entity.memo.server.si.SIData;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;

public class SIELookTo
<
	BD extends IBothDaE,
	E extends Entity,
	I extends IMixE<BD, E>,
	S extends ServerE<BD, E, I, MS>,
	MS extends MixSIE<BD, E, I, S>
> extends SI<BD, E, I, S, MS>
{
	public static byte ID;

	public SIEArea<BD, E, I, S, MS> siearea;
	public SIELook<BD, E, I, S, MS> sielook;

	public byte state = 1;//on
	public Entity entity;
	public BlockPos blockpos;
	public int tick;
//	public byte[] bypass_int_array = {this.skinningentities.bothentitiesmemory.workbytes.SIT(), this.skinningentities.bothentitiesmemory.workbytes.PROTECT()};

	public SIELookTo(S s)
	{
		super(s);
	}

	@Override
	public void init()
	{
		this.siearea = (SIEArea<BD, E, I, S, MS>)this.s.ms.si_map.get(SIEArea.ID);
		this.sielook = (SIELook<BD, E, I, S, MS>)this.s.ms.si_map.get(SIELook.ID);
	}

	@Override
	public void call()
	{

	}

	@Override
	public void onUpdate()
	{
//		if (serverentitiesmemory.isWorkBypass(serverentitiesmemory.workbytes.LOOK_TO(), this.bypass_int_array))
		E e = this.s.i.getE();
		if (this.s.isMove(e))
		{
			if ((this.s.ms.state & 2) == 2 && (this.state & 1) == 1)
			{
				double max_far = Double.MAX_VALUE;
	//			Entity current_entity = null;
				if (this.entity == null)
				{
					for (Entity entity : this.siearea.entity_collection)
					{
						if (entity.equals(e))
						{
							continue;
						}

						double far = e.getDistanceSq(entity);
						if (far < max_far)
						{
							this.entity = entity;
							max_far = far;
						}
					}
				}

				if (this.entity != null)
				{
					if (this.tick <= 0)
					{
						this.tick = this.entity.world.rand.nextInt(700) + 200;
					}
				}

	//			Entity owner_entity = serverentitiesmemory.getOwner();
	//			if (owner_entity != null)
	//			{
	//				if (this.skinningentities.getDistanceSq(owner_entity) < this.far)
	//				{
	//					this.entity = owner_entity;
	//					this.setTime(current_entity);
	//				}
	//			}

				if (--this.tick > 300)
//				if (this.tick != 0)
				{
					if (this.blockpos != null)
					{
						this.sielook.set(this.blockpos.getX() - e.posX, this.blockpos.getY() - e.posY, this.blockpos.getZ() - e.posZ, (byte)2);
					}
					else if (this.entity != null)
					{
						this.sielook.set(this.entity.posX - e.posX, this.entity.posY + this.entity.getEyeHeight() - e.posY, this.entity.posZ - e.posZ, (byte)2);
					}
				}
				else
				{
					if (this.tick <= 0)
					{
						this.entity = null;
						this.blockpos = null;
					}
	//				serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.LOOK_TO() / 8] &= (byte)(255 - Math.pow(2, serverentitiesmemory.workbytes.LOOK_TO() % 8));//0
				}
			}
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
