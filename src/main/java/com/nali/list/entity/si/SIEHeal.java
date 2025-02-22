package com.nali.list.entity.si;

import com.nali.da.IBothDaE;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.small.entity.memo.server.si.SI;
import com.nali.small.entity.memo.server.si.SIData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;

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
	public SIELocation<BD, E, I, S, MS> sielocation;
	public SIEFindMove<BD, E, I, S, MS> siefindmove;

	public int[] heal_frame_int_array;

	public int cooldown;
	public float how_heal = 16.0F;
	public double minimum_distance = 3.0D;

	public final static byte B_ON = 1;
	public final static byte B_ANIMATE_START = 2;
	public final static byte B_ANIMATE_HEAL = 4;
	public final static byte B_WORK = 8;
	public byte flag = B_ON;//on1 ani2-4
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
		this.sielocation = (SIELocation<BD, E, I, S, MS>)this.s.ms.si_map.get(SIELocation.ID);
		this.siefindmove = (SIEFindMove<BD, E, I, S, MS>)this.s.ms.si_map.get(SIEFindMove.ID);
	}

	@Override
	public void call(EntityPlayerMP entityplayermp, byte[] byte_array)
	{
	}

	@Override
	public void onUpdate()
	{
		E e = this.s.i.getE();
		if (this.s.isMove(e))
		{
//			if (this.s.isWork(this.s.bytele.HEAL()) && !this.siearea.out_entity_list.isEmpty() && ++this.cooldown >= 200)
			if ((this.s.ms.flag & MixSIE.B_MAIN_WORK) == MixSIE.B_MAIN_WORK && (this.flag & B_ON) == B_ON)
			{
				this.flag |= B_WORK;
//				this.heal = true;
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
					if (SIEArea.getDistanceAABBToAABB(e, entity) <= this.minimum_distance)
					{
//						if (this.state == -1)
						if ((this.flag & (B_ANIMATE_START + B_ANIMATE_HEAL)) == 0)
						{
//							this.state = 0;
							this.flag |= B_ANIMATE_START;
							this.flag &= 255-4;
						}

//						if (this.state == 1)
						if ((this.flag & (B_ANIMATE_START + B_ANIMATE_HEAL)) == B_ANIMATE_HEAL)
						{
							((EntityLivingBase)entity).heal(this.how_heal);
						}

						this.siefindmove.endGoal();
					}
					else
					{
						if (this.sielocation.in(entity))
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
				if ((this.flag & (B_ANIMATE_START + B_ANIMATE_HEAL)) == B_ANIMATE_HEAL)
				{
					this.cooldown = 0;
//					this.state = -1;
					this.flag &= 255 - (B_ANIMATE_START + B_ANIMATE_HEAL);
				}
	//			if (!should_move && should_move2)
	//			{
	//				this.s.current_work_byte_array[this.s.bytele.HEAL() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.HEAL() % 8));//0
	//			}
			}
			else
			{
				if ((this.flag & B_WORK) == B_WORK)
				{
					this.cooldown = 0;
//					this.state = -1;
					this.flag &= 255 - (B_ANIMATE_START + B_ANIMATE_HEAL + B_WORK);
					this.siefindmove.endGoal();
//					this.flag ^= B_WORK;
				}

//				this.s.current_work_byte_array[this.s.bytele.HEAL() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.HEAL() % 8));//0
			}
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
