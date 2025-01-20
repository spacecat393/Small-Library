package com.nali.list.entity.si;

import com.nali.da.IBothDaE;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.small.entity.memo.server.si.SI;
import com.nali.small.entity.memo.server.si.SIData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

import static com.nali.small.entity.EntityMath.getDistanceAABBToAABB;
import static com.nali.small.entity.EntityMath.isInArea;

public class SIEHeal
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
	public SIESetLocation<BD, E, I, S, MS> silesetlocation;
	public SIEFindMove<BD, E, I, S, MS> siefindmove;

	public int[] heal_frame_int_array;

	public int cooldown;
	public boolean heal = false;
	public float how_heal = 16.0F;
	public double minimum_distance = 3.0D;
	public byte state;//on1 ani2-4
//	2-4 0
//	2-4 2
//	2-4 4
//	2-4 2-4
//	public byte state = -1;

	public SIEHeal(S s)
	{
		super(s);
	}

	@Override
	public void init()
	{
		this.siearea = (SIEArea<BD, E, I, S, MS>)this.s.ms.si_map.get(SIEArea.ID);
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
//			if (this.s.isWork(this.s.bytele.HEAL()) && !this.siearea.out_entity_list.isEmpty() && ++this.cooldown >= 200)
			if ((this.s.ms.state & 1) == 1 && (this.state & 1) == 1)
			{
				this.heal = true;
	//			double[] far = new double[this.siearea.out_entity_list.size()];
	//			boolean should_move = false;
	//			boolean should_move2 = true;

				int index = -1;
				double max_dis = Double.MAX_VALUE;
				for (int i = 0; i < this.siearea.out_entity_list.size(); ++i)
				{
					Entity entity = this.siearea.out_entity_list.get(i);
					if (e.equals(entity))
					{
						continue;
					}

	//				if (!(entity instanceof EntityLivingBase) || ((EntityLivingBase)entity).getMaxHealth() - ((EntityLivingBase)entity).getHealth() < 1.0F)
					if (entity instanceof EntityLivingBase && ((EntityLivingBase)entity).getMaxHealth() - ((EntityLivingBase)entity).getHealth() >= 1.0F)
					{
	//					far[rg] = Double.MAX_VALUE;
	//				}
	//				else
	//				{
	//					if (e.equals(entity))
	//					{
	//						should_move2 = false;
	//					}

						double far = e.getDistanceSq(entity);
						if (far < max_dis)
						{
							index = i;
							max_dis = far;
						}
	//					far[rg] = e.getDistanceSq(entity);

	//					if (isTooClose(e, entity, this.minimum_distance))
	//					{
	//						if (this.state == -1)
	//						{
	//							this.state = 0;
	//						}
	//
	//						if (this.state == 1)
	//						{
	//							((EntityLivingBase)entity).heal(this.how_heal);
	//						}
	//
	//						this.siefindmove.endGoal();
	//					}
	//					else
	//					{
	//						should_move = true;
	//					}
					}
	//				++rg;
				}

	//			if (should_move && should_move2)
	//			{
	//				rg = 0;

	//				double max_dis = Double.MAX_VALUE;

	//				for (int i = 0; i < far.length; ++i)
	//				{
	//					if (far[i] < max_dis)
	//					{
	//						rg = i;
	//						max_dis = far[i];
	//					}
	//				}

				if (index != -1)
				{
					Entity entity = this.siearea.out_entity_list.get(index);
	//				if (isTooClose(e, entity, this.minimum_distance))
					if (getDistanceAABBToAABB(e, entity) <= this.minimum_distance)
					{
//						if (this.state == -1)
						if ((this.state & (2+4)) == 0)
						{
//							this.state = 0;
							this.state |= 2;
							this.state &= 255-4;
						}

//						if (this.state == 1)
						if ((this.state & (2+4)) == 4)
						{
							((EntityLivingBase)entity).heal(this.how_heal);
						}

						this.siefindmove.endGoal();
					}
					else
					{
						if (this.silesetlocation.far == 0 || this.silesetlocation.blockpos == null || isInArea(entity, this.silesetlocation.blockpos, this.silesetlocation.far))
						{
							this.siefindmove.setGoal(entity.posX, entity.posY, entity.posZ);
						}
					}
				}
//				else
//				{
//					this.s.current_work_byte_array[this.s.bytele.HEAL() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.HEAL() % 8));//0
//				}

	//			/*if (this.state == 0 || this.state == 1)
	//			{
	////				this.s.entitiesaimemory.skinningentitieslook.set(entity.posX, entity.posY, entity.posZ, 20.0F);
	//			}
	//			else */if (!this.siefindmove.try_move)
	//			{
	//				this.state = 0;
	////				this.s.entitiesaimemory.skinningentitieslook.set(entity.posX, entity.posY, entity.posZ, 20.0F);
	//			}

	//				if (!isTooClose(e, entity, this.minimum_distance))
	//				{
	//				this.siefindmove.setGoal(entity.posX, entity.posY, entity.posZ);
	//				this.state = -1;
	//				}
	//			}

//				if (this.state == 1)
				if ((this.state & (2+4)) == 4)
				{
					this.cooldown = 0;
//					this.state = -1;
					this.state &= 255-(2+4);
				}
	//			if (!should_move && should_move2)
	//			{
	//				this.s.current_work_byte_array[this.s.bytele.HEAL() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.HEAL() % 8));//0
	//			}
			}
			else
			{
				if (this.heal)
				{
					this.cooldown = 0;
//					this.state = -1;
					this.state &= 255-(2+4);
					this.siefindmove.endGoal();
					this.heal = false;
				}

//				this.s.current_work_byte_array[this.s.bytele.HEAL() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.HEAL() % 8));//0
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
