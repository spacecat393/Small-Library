package com.nali.list.entity.si;

import com.nali.da.IBothDaNe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.small.entity.memo.server.si.SI;
import com.nali.small.entity.memo.server.si.SIData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

import java.util.ArrayList;
import java.util.List;

import static com.nali.small.entity.EntityMath.getDistanceAABBToAABB;

public class SILeProtect
<
	BD extends IBothDaNe,
	E extends EntityLivingBase,
	I extends IMixE<BD, E>,
	S extends ServerLe<BD, E, I, MS>,
	MS extends MixSIE<BD, E, I, S>
> extends SI<BD, E, I, S, MS>
{
	public static byte ID;

	public SIEArea<BD, E, I, S, MS> siearea;
	public SILeFindMove<BD, E, I, S, MS> silefindmove;

	public byte state;//on1 protect2 ani4-8
//	4+8 0
//	4+8 4
//	4+8 8
//	4+8 4+8
	public List<Entity> entity_list = new ArrayList();
	public List<Integer> cooldown_int_list = new ArrayList();
	public double minimum_distance = 1.5D;
//	public byte state = -1, main_state = -1;

	public SILeProtect(S s)
	{
		super(s);
	}

	@Override
	public void init()
	{
		this.siearea = (SIEArea<BD, E, I, S, MS>)this.s.ms.si_map.get(SIEArea.ID);
		this.silefindmove = (SILeFindMove<BD, E, I, S, MS>)this.s.ms.si_map.get(SILeFindMove.ID);
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
//			if (this.s.isWork(this.s.bytele.PROTECT()) && !this.siearea.out_entity_list.isEmpty())
			if ((this.s.ms.state & 1) == 1 && (this.state & 1) == 1)
			{
//				this.protect = true;
				this.state |= 2;
				this.silefindmove.endGoal();
//				if (this.main_state != -1)
//				{
//					this.state = this.main_state;
//					this.main_state = -1;
//				}

				for (int i = 0; i < this.cooldown_int_list.size(); ++i)
				{
					this.cooldown_int_list.set(i, this.cooldown_int_list.get(i) + 1);
					if (this.cooldown_int_list.get(i) == 1200)
					{
						this.entity_list.remove(i);
						this.cooldown_int_list.remove(i);
						--i;
					}
				}

//				if (this.state < 0)
//				{
//					this.state = 0;
//				}

				for (Entity entity : this.siearea.out_entity_list)
				{
	//				if (isTooClose(this.skinningentities, entity, this.minimum_distance))
					if (getDistanceAABBToAABB(e, entity) <= this.minimum_distance)
					{
						boolean in_list = false;
						for (Entity in_entity : this.entity_list)
						{
							in_list = in_entity.equals(entity);
							if (in_list)
							{
								break;
							}
						}

//						if (!in_list && this.state == 1 || this.state == 2)
						if (!in_list && ((this.state & 4) == 4 || (this.state & 8) == 8))
						{
							this.entity_list.add(entity);
							this.cooldown_int_list.add(0);
//							this.state = 2;
							this.state |= 8;
							this.state &= 255-4;

							if (entity instanceof EntityLivingBase)
							{
	//							((EntityLivingBase)entity).addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 1200, 10));
								entity.getEntityData().setByte("Nali_protect", (byte)3);
							}
						}
					}
				}
			}
			else
			{
//				if (this.protect)
				if ((this.state & 2) == 2)
				{
//					this.state = 3;
					this.state |= 4+8;
//					this.protect = false;
					this.state &= 255-2;
				}

//				if (this.state == 3)
//				{
//					this.silefindmove.endGoal();
//				}
//				else
//				{
//					this.s.current_work_byte_array[this.s.bytele.PROTECT() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.PROTECT() % 8));//0
//				}
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
