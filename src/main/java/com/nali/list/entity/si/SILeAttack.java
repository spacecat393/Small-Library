package com.nali.list.entity.si;

import com.nali.da.IBothDaE;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.small.entity.memo.server.si.SI;
import com.nali.small.entity.memo.server.si.SIData;
import com.nali.system.bytes.ByteReader;
import com.nali.system.bytes.ByteWriter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumHand;

import java.util.List;

import static com.nali.small.entity.EntityMath.getDistanceAABBToAABB;

public class SILeAttack
<
	BD extends IBothDaE,
	E extends EntityLe,
	I extends IMixE<BD, E>,
	S extends ServerLe<BD, E, I, MS>,
	MS extends MixSIE<BD, E, I, S>
> extends SI<BD, E, I, S, MS>
{
	public static byte ID;

	public SIEArea<BD, E, I, S, MS> siearea;
	public SIELocation<BD, E, I, S, MS> sielocation;
	public SIEFindMove<BD, E, I, S, MS> siefindmove;
	public SIELook<BD, E, I, S, MS> sielook;
	public SIECareOwner<BD, E, I, S, MS> siecareowner;

	public int[] attack_frame_int_array;

	public final static byte B_MOVE_TO = 1;
	public final static byte B_PREPARE = 2;
	public final static byte B_HIT = 4;

	public final static byte B_REMOTE = 8;
	public final static byte B_ATTACK_POSE = 16;
	public final static byte B_WALK_TO = 32;
	public byte state = B_WALK_TO;//move_to prepare hit | remote attack_pose walk_to

	public float minimum_distance = 3.0F;
	public int
		magic_point,
		max_magic_point = 16;

	public SILeAttack(S s)
	{
		super(s);
	}

	@Override
	public void init()
	{
		this.siearea = (SIEArea<BD, E, I, S, MS>)this.s.ms.si_map.get(SIEArea.ID);
		this.sielocation = (SIELocation<BD, E, I, S, MS>)this.s.ms.si_map.get(SIELocation.ID);
		this.siefindmove = (SIEFindMove<BD, E, I, S, MS>)this.s.ms.si_map.get(SIEFindMove.ID);
		this.sielook = (SIELook<BD, E, I, S, MS>)this.s.ms.si_map.get(SIELook.ID);
		this.siecareowner = (SIECareOwner<BD, E, I, S, MS>)this.s.ms.si_map.get(SIECareOwner.ID);
	}

	@Override
	public void call(EntityPlayerMP entityplayermp, byte[] byte_array)
	{
	}

//	public void set()
//	{
//		byte[] byte_array = this.s.ms.byte_array;
//		float id = ByteReader.getFloat(byte_array, 1 + 8 + 1 + 1);
//		float x = ByteReader.getFloat(byte_array, 1 + 8 + 1 + 1 + 4);
//
////		SmallSakuraType smallsakuratypes = this.s.ms.entityplayermp.getCapability(SmallSakuraSerializable.SMALLSAKURATYPES_CAPABILITY, null);
////		byte value = smallsakuratypes.get();
//		UUID player_uuid = this.s.ms.entityplayermp.getUniqueID();
//		byte value = PlayerData.SAKURA_MAP.getOrDefault(player_uuid, (byte)0);
//
//		if (id == 1.1F)
//		{
//			if (x == 1)
//			{
//				if (value >= 1)
//				{
////					smallsakuratypes.set((byte)(value - 1));
//					PlayerData.SAKURA_MAP.put(player_uuid, (byte)(value - 1));
//					this.flag |= 32;
//				}
//			}
//			else
//			{
//				this.flag &= 255 - 32;
//			}
//		}
//		else if (id == 1.2F)
//		{
//			if (x == 1)
//			{
//				if (value >= 1)
//				{
////					smallsakuratypes.set((byte)(value - 1));
//					PlayerData.SAKURA_MAP.put(player_uuid, (byte)(value - 1));
//					this.flag |= 8;
//				}
//			}
//			else
//			{
//				this.flag &= 255 - 8;
//			}
//		}
//		else if (id == 1.3F)
//		{
//			int v = (int)x;
//			if (value >= v)
//			{
////				smallsakuratypes.set((byte)(value - v));
//				PlayerData.SAKURA_MAP.put(player_uuid, (byte)(value - v));
//				this.minimum_distance = x;
//			}
//		}
//		else if (id == 1.4F)
//		{
//			int v = (int)x;
//			if (value >= v)
//			{
////				smallsakuratypes.set((byte)(value - v));
//				PlayerData.SAKURA_MAP.put(player_uuid, (byte)(value - v));
//				this.max_magic_point = v;
//			}
//		}
//
//		this.fetch();
//	}

//	public void fetch()
//	{
//		byte[] byte_array = new byte[1 + 1 + 4 + 4];
//		byte_array[0] = CSetAttack.ID;
//		byte_array[1] = this.flag;
//		ByteWriter.set(byte_array, this.minimum_distance, 1 + 1);
//		ByteWriter.set(byte_array, this.max_magic_point, 1 + 1 + 4);
//		NetworkRegistry.I.sendTo(new ClientMessage(byte_array), this.s.ms.entityplayermp);
//	}

	@Override
	public void onUpdate()
	{
		E e = this.s.i.getE();
//		boolean should_work = false;
//		if ((this.s.main_work_byte_array[this.s.bytele.CARE_OWNER() / 8] >> this.s.bytele.CARE_OWNER() % 8 & 1) == 1)
//		{
//			this.s.current_work_byte_array[this.s.bytele.CARE_OWNER() / 8] |= (byte)Math.pow(2, this.s.bytele.CARE_OWNER() % 8);
//			should_work = this.s.isWork(this.s.bytele.CARE_OWNER());
//			this.s.current_work_byte_array[this.s.bytele.CARE_OWNER() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.CARE_OWNER() % 8));
//		}

//		boolean work = this.s.isWork(this.s.bytele.ATTACK());
//		if ((!work && should_work && !this.siecareowner.target_entity_list.isEmpty()) || (work && !this.siearea.all_entity_list.isEmpty()))
		if ((this.s.ms.flag & MixSIE.B_MAIN_WORK) == MixSIE.B_MAIN_WORK)
		{
			if (((this.siecareowner.flag & SIECareOwner.B_ON) == SIECareOwner.B_ON && !this.siecareowner.target_entity_list.isEmpty()) || ((this.state & (B_REMOTE + B_WALK_TO)) != 0 && !this.siearea.all_entity_list.isEmpty()))
			{
	//			if ((this.s.main_work_byte_array[this.s.bytele.MINE() / 8] >> this.s.bytele.MINE() % 8 & 1) == 1 && this.s.entitiesaimemory.skinningentitiesmine.blockpos != null)
	//			{
	//				this.s.entitiesaimemory.skinningentitiesmine.breakWork();
	//			}

				Entity target_entity;
				if (!this.siecareowner.target_entity_list.isEmpty())
				{
					target_entity = this.attackAndFind(this.siecareowner.target_entity_list);
				}
				else
				{
					target_entity = this.attackAndFind(this.siearea.all_entity_list);
				}

				if (this.sielocation.in(target_entity))
				{
					this.state |= B_PREPARE;

					if ((this.siefindmove.flag & SIEFindMove.B_TRY_MOVE) == 0)
					{
						this.sielook.set(target_entity.posX - e.posX, target_entity.posY - e.posY, target_entity.posZ - e.posZ, (byte)2);
					}

					if ((this.state & B_WALK_TO + B_REMOTE) == B_WALK_TO && !(e.canEntityBeSeen(target_entity) && getDistanceAABBToAABB(e, target_entity) <= this.minimum_distance))
					{
	//					this.siefindmove.setBreakGoal(target_entity.posX, target_entity.posY, target_entity.posZ);
						this.siefindmove.setGoal(target_entity.posX, target_entity.posY, target_entity.posZ);
						this.state |= B_MOVE_TO;
						this.state &= 255 - B_PREPARE;
					}
				}

				this.state &= 255 - B_HIT;
			}
			else
			{
				if ((this.state & B_MOVE_TO) == B_MOVE_TO)
				{
					this.siefindmove.endGoal();
	//				this.flag &= 255-(1+2+4);
				}
				this.state &= 255 - (B_MOVE_TO + B_PREPARE + B_HIT);

	//			this.s.current_work_byte_array[this.s.bytele.ATTACK() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.ATTACK() % 8));
			}
		}
	}

	@Override
	public void writeFile(SIData sidata)
	{
		sidata.byte_array[sidata.index++] = this.state;

		ByteWriter.set(sidata.byte_array, this.minimum_distance, sidata.index);
		sidata.index += 4;

		ByteWriter.set(sidata.byte_array, this.magic_point, sidata.index);
		sidata.index += 4;

		ByteWriter.set(sidata.byte_array, this.max_magic_point, sidata.index);
		sidata.index += 4;
	}

	@Override
	public void readFile(SIData sidata)
	{
		this.state = sidata.byte_array[sidata.index++];

		this.minimum_distance = ByteReader.getFloat(sidata.byte_array, sidata.index);
		sidata.index += 4;

		this.magic_point = ByteReader.getInt(sidata.byte_array, sidata.index);
		sidata.index += 4;

		this.max_magic_point = ByteReader.getInt(sidata.byte_array, sidata.index);
		sidata.index += 4;
	}

	@Override
	public int size()
	{
		return 1 + 4 + 4 + 4;
	}

	public Entity attackAndFind(List<Entity> entity_list)
	{
		E e = this.s.i.getE();
//		double[] far = new double[entity_list.size()];
//		int rg = 0;
		int index = -1;
		double max_dis = Double.MAX_VALUE;
		for (int i = 0; i < entity_list.size(); ++i)
		{
			Entity entity = entity_list.get(i);
			double far = e.getDistanceSq(entity);
			if (far < max_dis)
			{
				index = i;
				max_dis = far;
			}
//			far[rg++] = e.getDistanceSq(entity);
			if (this.sielocation.in(entity))
			{
//				if ((this.flag & 8) == 8 || (e.canEntityBeSeen(entity) && isTooClose(e, entity, this.minimum_distance)))
				if ((this.state & B_REMOTE) == B_REMOTE || (e.canEntityBeSeen(entity) && getDistanceAABBToAABB(e, entity) <= this.minimum_distance))
				{
					if ((this.state & B_HIT) == B_HIT)
					{
						e.swingArm(EnumHand.MAIN_HAND);
						e.attackEntityAsMob(entity);
					}

					this.siefindmove.endGoal();
				}
			}
		}

//		rg = 0;

//		double max_dis = Double.MAX_VALUE;

//		for (int i = 0; i < far.length; ++i)
//		{
//			if (far[i] < max_dis)
//			{
//				rg = i;
//				max_dis = far[i];
//			}
//		}

		if (index == -1)
		{
			return null;
		}
		else
		{
			return entity_list.get(index);
		}
	}
}
