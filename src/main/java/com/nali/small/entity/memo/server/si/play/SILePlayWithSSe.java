package com.nali.small.entity.memo.server.si.play;

import com.nali.da.IBothDaE;
import com.nali.list.entity.si.SIEArea;
import com.nali.list.entity.si.SIEFindMove;
import com.nali.list.entity.si.SIEPlayWithRSe;
import com.nali.list.entity.si.SIESetLocation;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;
import net.minecraft.entity.Entity;

import static com.nali.small.entity.EntityMath.getDistanceAABBToAABB;
import static com.nali.small.entity.EntityMath.isInArea;

public class SILePlayWithSSe
<
	R2 extends SIEPlayWithRSe<S, BD, E, I, MS, BD2, E2, I2, S2, A2>,
	S2 extends ServerE<BD2, E2, I2, A2>,
	BD2 extends IBothDaE,
	E2 extends Entity,
	I2 extends IMixE<BD2, E2>,
	A2 extends MixSIE<BD2, E2, I2, S2>,
	BD extends IBothDaE,
	E extends EntityLe,
	I extends IMixE<BD, E>,
	S extends ServerLe<BD, E, I, MS>,
	MS extends MixSIE<BD, E, I, S>
> extends SIEPlayWithRSe<S2, BD2, E2, I2, A2, BD, E, I, S, MS>
{
//	public static byte ID;

	public SIEArea<BD, E, I, S, MS> siearea;
	public SIEFindMove<BD, E, I, S, MS> siefindmove;
	public SIESetLocation<BD, E, I, S, MS> silesetlocation;

//	public AIESit<SD2, BD2, E2, I2, S2, A2> siesit2;
	public R2 r2;

	public Class e2_class;
	public int tick;

	public SILePlayWithSSe(S s)
	{
		super(s);
	}

	@Override
	public void init()
	{
		this.siearea = (SIEArea<BD, E, I, S, MS>)this.s.ms.si_map.get(SIEArea.ID);
		this.siefindmove = (SIEFindMove<BD, E, I, S, MS>)this.s.ms.si_map.get(SIEFindMove.ID);
		this.silesetlocation = (SIESetLocation<BD, E, I, S, MS>)this.s.ms.si_map.get(SIESetLocation.ID);
	}

	@Override
	public void onUpdate()
	{
		E e = this.s.i.getE();
		if (this.s.isMove(e))
		{
			if ((this.s.ms.state & 1) == 1 && (this.state & 1) == 1)
			{
				E2 e2 = this.s2.i.getE();
				if (this.s2 != null && this.s.worldserver.getEntityFromUuid(e2.getUniqueID()) == null)
				{
					this.onFree();
				}
				else if (--this.tick <= 0)
				{
					if (this.s2 != null)
					{
						this.onFree();
					}
					else if (e.ticksExisted % 200 == 0)
					{
//						int rg = -1;
						double max = Double.MAX_VALUE;
						for (int i = 0; i < this.siearea.out_entity_list.size(); ++i)
						{
							Entity entity = this.siearea.out_entity_list.get(i);
//							for (Class clasz : this.class_array)
//							{
//							if (entity.getClass() == clasz)
							if (entity.getClass() == this.e2_class)
							{
								double new_max = getDistanceAABBToAABB(e, entity);
								if (new_max < max && this.shouldAdd(i))
								{
//										rg = i;
									max = new_max;
								}
								else
								{
									this.s2 = null;
									this.r2 = null;
								}
							}
//							}
						}

//						super.onUpdate();
//						if (this.o2 != null)
//						{
//							this.siesit2 = ((AIESit)this.o2.ms.si_map.get(AIESit.ID));
//						}
//						if (rg != -1)
//						{
////							this.onFind(rg);
//						}

						this.tick = 1200 + e.world.rand.nextInt(5000);
					}
				}
				else
				{
					if (this.s2 != null && (this.silesetlocation.far == 0 || this.silesetlocation.blockpos == null || isInArea(e2, this.silesetlocation.blockpos, this.silesetlocation.far)))
					{
						if ((this.state & 2) == 0)
						{
							this.onAdd();
						}
						else
						{
							this.onPlay();
						}
					}
				}
			}
		}
		else if (this.s2 != null)
		{
			this.onFree();
		}
	}

	public void onPlay()
	{
		E e = this.s.i.getE();
		E2 e2 = this.s2.i.getE();
		e.setPositionAndUpdate(e2.posX, e2.posY, e2.posZ);
		e.rotationYaw = e2.rotationYaw;
		e.rotationPitch = e2.rotationPitch;
		e.fallDistance = 0;
//		e.renderYawOffset = e2.rotationYaw;
	}

	public boolean shouldAdd(int index)
	{
		Entity entity = this.siearea.out_entity_list.get(index);
		this.s2 = (S2)ServerE.S_MAP.get(entity.getUniqueID());
		this.r2 = ((R2)this.s2.ms.si_map.get(SIEPlayWithRSe.ID));
		return this.r2.s2 == null && (this.r2.state & 1) == 1 && (this.silesetlocation.far == 0 || this.silesetlocation.blockpos == null || isInArea(entity, this.silesetlocation.blockpos, this.silesetlocation.far));
	}

//	public void onFind(int rg)
//	{
//		if (s2 != null)
//		{
//			if ( &&
////					e.getDataManager().get(this.s.i.getFloatDataParameterArray()[0]) <= playwith_skinningentities.getDataManager().get(playwith_skinningentities.getFloatDataParameterArray()[0]) &&
//					)
//			{
//				break;
//			}
//			else
//			{
//				playwith_skinningentities = null;
//			}
//		}
//	}

	public void onAdd()
	{
		E e = this.s.i.getE();
		E2 e2 = this.s2.i.getE();
		if (e.getEntityBoundingBox().intersects(e2.getEntityBoundingBox()))
		{
			if ((this.r2.state & 1) == 1 && this.r2.s2 == null)
			{
				this.r2.s2 = this.s;
				this.state |= 2;
//				this.aieplaywith2.state |= 2+4;
			}
			else
			{
				this.onFree();
//				this.o2 = null;
//				this.tick = 0;
			}
		}
		else
		{
			if (this.silesetlocation.far == 0 || this.silesetlocation.blockpos == null || isInArea(e2, this.silesetlocation.blockpos, this.silesetlocation.far))
			{
				this.siefindmove.setGoal(e2.posX, e2.posY, e2.posZ);
			}
		}
	}

//	@Override
	public void onFree()
	{
//		super.onFree();
//		this.siesit2 = null;
		this.r2.s2 = null;
//		this.aieplaywith2.state &= 255-(2+4);
		this.r2.state &= 255-2;
		this.r2 = null;

		this.s2 = null;
//		this.state &= 255-(2+4);
		this.state &= 255-2;

		this.tick = 1200 + this.s.i.getE().world.rand.nextInt(1200);
	}

	@Override
	public void call()
	{
	}
}
