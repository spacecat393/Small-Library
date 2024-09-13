package com.nali.list.entity.si;

import com.nali.da.IBothDaNe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.SI;
import com.nali.small.entity.memo.server.si.SIData;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.small.mixin.IMixinEntityLivingBase;
import com.nali.sound.ISoundDaLe;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

import java.util.ArrayList;
import java.util.List;

public class SILeCareOwner<SD extends ISoundDaLe, BD extends IBothDaNe, E extends EntityLivingBase, I extends IMixE<SD, BD, E>, S extends ServerLe<SD, BD, E, I, MS>, MS extends MixSIE<SD, BD, E, I, S>> extends SI<SD, BD, E, I, S, MS>
{
	public static byte ID;

	public SIEOwner<SD, BD, E, I, S, MS> sieowner;
	public SIEArea<SD, BD, E, I, S, MS> siearea;

	public byte state;

	public List<Entity> target_entity_list = new ArrayList();
	public List<Double> far_double_list = new ArrayList();

	public SILeCareOwner(S s)
	{
		super(s);
	}

	@Override
	public void init()
	{
		this.sieowner = (SIEOwner<SD, BD, E, I, S, MS>)this.s.ms.si_map.get(SIEOwner.ID);
		this.siearea = (SIEArea<SD, BD, E, I, S, MS>)this.s.ms.si_map.get(SIEArea.ID);
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
			Entity owner_entity = this.sieowner.getOwner();
//			if (this.s.isWork(this.s.bytele.CARE_OWNER()) && owner_entity != null && !this.siearea.all_entity_list.isEmpty())
			if ((this.s.ms.state & 1) == 1 && (this.state & 1) == 1 && owner_entity != null && !this.siearea.all_entity_list.isEmpty())
			{
				List<Entity> target_entity_list = new ArrayList(this.target_entity_list);
				this.target_entity_list.clear();
				this.far_double_list.clear();
				for (Entity entity : this.siearea.all_entity_list)
				{
					boolean should_attack = false;
					for (Entity old_entity : target_entity_list)
					{
						if (old_entity.equals(entity))
						{
							should_attack = true;
							break;
						}
					}

					if (should_attack || ourTarget(entity, owner_entity) || ourTarget(entity, e))
					{
						this.target_entity_list.add(entity);
						this.far_double_list.add(e.getDistanceSq(entity));
					}
				}
	//			if (!this.far_double_list.isEmpty())
	//			{
	//				int index = 0;
	//				double max_dis = Double.MAX_VALUE;
	//				for (int i = 0; i < this.far_double_list.size(); ++i)
	//				{
	//					double far = this.far_double_list.get(i);
	//					if (far < max_dis)
	//					{
	//						index = i;
	//						max_dis = far;
	//					}
	//				}
	//
	//				this.target_entity = this.siearea.all_entity_list.get(index);
	//			}
	//			else
	//			{
	//				this.target_entity = null;
	//			}
			}
			else
			{
				this.target_entity_list.clear();
				this.far_double_list.clear();
			}

//			this.s.current_work_byte_array[this.s.bytele.CARE_OWNER() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.CARE_OWNER() % 8));//0
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

	public static boolean ourTarget(Entity entity, Entity us_entity)
	{
		return (entity instanceof EntityLivingBase &&
		(
			us_entity.equals(((EntityLivingBase)entity).getCombatTracker().getBestAttacker()) ||
			us_entity.equals(((IMixinEntityLivingBase)entity).attackingPlayer()) ||
			us_entity.equals(((EntityLivingBase)entity).getRevengeTarget()) ||
			us_entity.equals(((EntityLivingBase)entity).getLastAttackedEntity()) ||
			((EntityLivingBase)entity).getLastDamageSource() != null && us_entity.equals(((EntityLivingBase)entity).getLastDamageSource().getTrueSource())
		) || (entity instanceof EntityLiving && us_entity.equals(((EntityLiving)entity).getAttackTarget())))
		&& /*!us_entity.equals(entity) && */!(entity instanceof EntityPlayer);// && fastCheck(entity);
	}
}