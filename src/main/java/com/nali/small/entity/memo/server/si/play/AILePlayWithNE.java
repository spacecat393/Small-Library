//package com.nali.small.entity.memo.server.ai.play;
//
//import com.nali.da.IBothDaNe;
//import com.nali.list.entity.ai.AIEArea;
//import com.nali.list.entity.ai.AIEPlayWith;
//import com.nali.list.entity.ai.AILeFindMove;
//import com.nali.list.entity.ai.AILeSetLocation;
//import com.nali.small.entity.IMixLe;
//import com.nali.small.entity.memo.server.ServerLe;
//import com.nali.small.entity.memo.server.ai.MixAIE;
//import com.nali.sound.ISoundDaLe;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.EntityLivingBase;
//
//import java.util.Random;
//
//import static com.nali.small.entity.EntityMath.getDistanceAABBToAABB;
//import static com.nali.small.entity.EntityMath.isInArea;
//
//public abstract class AILePlayWithNE<E2 extends Entity, SD extends ISoundDaLe, BD extends IBothDaNe, E extends EntityLivingBase, I extends IMixLe<SD, BD, E>, S extends ServerLe<SD, BD, E, I, MS>, A extends MixAIE<SD, BD, E, I, S>> extends AIEPlayWith<E2, SD, BD, E, I, S, MS>
//{
////	public static byte ID;
//
//	public AIEArea<SD, BD, E, I, S, MS> siearea;
//	public AILeFindMove<SD, BD, E, I, S, MS> siefindmove;
//	public AILeSetLocation<SD, BD, E, I, S, MS> sielocation;
//
//	public Class[] class_array;
//	public int tick;
////	public boolean should_play;
////	public boolean first_playwith;
//
//	public AILePlayWithNE(S s)
//	{
//		super(s);
//	}
//
//	@Override
//	public void init()
//	{
//		this.siearea = (AIEArea<SD, BD, E, I, S, MS>)this.s.ms.si_map.get(AIEArea.ID);
//		this.siefindmove = (AILeFindMove<SD, BD, E, I, S, MS>)this.s.ms.si_map.get(AILeFindMove.ID);
//		this.sielocation = (AILeSetLocation<SD, BD, E, I, S, MS>)this.s.ms.si_map.get(AILeSetLocation.ID);
//	}
//
//	@Override
//	public void onUpdate()
//	{
//		if (this.s.isMove())
//		{
////			boolean play = false;
////			if (this.s.isWork(this.s.bytele.PLAY()))
//			if ((this.s.ms.state & 1) == 1 && (this.state & 1) == 1)
//			{
//				E e = this.s.i.getE();
//				Random random = e.world.rand;
//				boolean result = this.tick <= 0;
//
//				if (this.e2 != null && this.s.worldserver.getEntityFromUuid(this.e2.getUniqueID()) == null)
//				{
//					this.onFree();
////					this.e2 = null;
////					this.should_play = false;
////					this.first_playwith = false;
////					this.tick = 1200 + random.nextInt(5000);
//				}
//
//				if (result)
//				{
//					if (this.e2 != null)
//					{
//						this.onFree();
////						this.se.skinningentities = null;
////						this.e2 = null;
////						this.should_play = false;
////						this.first_playwith = false;
////						this.tick = 1200 + random.nextInt(5000);
//					}
//					else if (e.ticksExisted % 200 == 0)
//					{
////						EntityLeInv playwith_skinningentities = null;
//						int rg = -1;
//						double max = Double.MAX_VALUE;
//						for (int i = 0; i < this.siearea.out_entity_list.size(); ++i)
//						{
//							Entity entity = this.siearea.out_entity_list.get(i);
//							for (Class clasz : this.class_array)
//							{
//								if (entity.getClass() == clasz)
//								{
//									double new_max = getDistanceAABBToAABB(e, entity);
//									if (new_max < max)
//									{
//										rg = i;
//										max = new_max;
//									}
//								}
//							}
//						}
//
//						if (rg != -1)
//						{
//							this.onFind(rg);
//						}
//
//						this.tick = 1200 + random.nextInt(5000);
////						if (this.e2 == null)
////						{
////							this.tick = 1200;
////						}
////						else
////						{
//////							this.e2 = (E2)playwith_skinningentities;
////							this.tick = 1200 + random.nextInt(5000);
////						}
//					}
//				}
//				else
//				{
//					if (this.e2 != null && (this.sielocation.far == 0 || this.sielocation.blockpos == null || isInArea(this.e2, this.sielocation.blockpos, this.sielocation.far)))
//					{
////						play = true;
////						if (!this.should_play)
//						if ((this.state & 2) == 0)
//						{
//							this.onAdd();
//						}
//						else
//						{
//							this.onPlay();
//						}
//					}
//
//					--this.tick;
//				}
//			}
//		}
//		else if (this.e2 != null)
//		{
//			this.onFree();
////			if (this.e2 != null)
////			{
////				((ServerE)this.e2.bothentitiesmemory).skinningentities = null;
////				this.e2 = null;
////			}
//
////			this.should_play = false;
////			this.first_playwith = false;
//		}
//
////		if (!play)
////		{
////			this.s.current_work_byte_array[this.s.bytele.PLAY() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.PLAY() % 8));//0
////		}
//	}
//
//	public void onPlay()
//	{
//		E e = this.s.i.getE();
//		e.setPositionAndUpdate(this.e2.posX, this.e2.posY, this.e2.posZ);
//		e.rotationYaw = this.e2.rotationYaw;
//		e.rotationPitch = this.e2.rotationPitch;
//		e.fallDistance = 0;
//	}
//
//	public void onFind(int rg)
//	{
////		playwith_skinningentities = (EntityLeInv)entity;
////		if (((ServerE)playwith_skinningentities.bothentitiesmemory).skinningentities == null &&
////				e.getDataManager().get(this.s.i.getFloatDataParameterArray()[0]) <= playwith_skinningentities.getDataManager().get(playwith_skinningentities.getFloatDataParameterArray()[0]) &&
////				(this.sielocation.far == 0 || this.sielocation.blockpos == null || isInArea(playwith_skinningentities, this.sielocation.blockpos, this.sielocation.far)))
////		{
////			break;
////		}
////		else
////		{
////			playwith_skinningentities = null;
////		}
//	}
//
//	public void onAdd()
//	{
////		//						if (isTooClose(e, this.playwith_skinningentities, 0.0F))
////		if (e.getEntityBoundingBox().intersects(this.e2.getEntityBoundingBox()))
////		{
////			if (((ServerE)this.e2.bothentitiesmemory).skinningentities == null)
////			{
////				((ServerE)this.e2.bothentitiesmemory).skinningentities = e;
////				this.should_play = true;
////				this.first_playwith = true;
////			}
////			else
////			{
////				this.e2 = null;
////				this.tick = 0;
////			}
////		}
////		else
////		{
////			if (this.sielocation.far == 0 || this.sielocation.blockpos == null || isInArea(this.e2, this.sielocation.blockpos, this.sielocation.far))
////			{
////				this.s.entitiesaimemory.skinningentitiesfindmove.setGoal(this.e2.posX, this.e2.posY, this.e2.posZ);
////			}
////		}
//	}
//
//	public void onFree()
//	{
////		if (this.e2 != null)
////		{
////			((ServerE)this.e2.bothentitiesmemory).skinningentities = null;
////		this.should_play = false;
////		this.first_playwith = false;
//		this.e2 = null;
//		this.tick = 1200 + this.s.i.getE().world.rand.nextInt(1200);
////		}
//	}
//}
