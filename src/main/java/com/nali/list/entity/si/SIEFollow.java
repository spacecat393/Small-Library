package com.nali.list.entity.si;

import com.nali.da.IBothDaE;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.small.entity.memo.server.si.SI;
import com.nali.small.entity.memo.server.si.SIData;
import com.nali.system.bytes.ByteReader;
import com.nali.system.bytes.ByteWriter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;

public class SIEFollow
<
	BD extends IBothDaE,
	E extends Entity,
	I extends IMixE<BD, E>,
	S extends ServerE<BD, E, I, MS>,
	MS extends MixSIE<BD, E, I, S>
> extends SI<BD, E, I, S, MS>
{
	public static byte ID;

	public SIEOwner<BD, E, I, S, MS> sieowner;
	public SIELocation<BD, E, I, S, MS> sielocation;
	public SIEFindMove<BD, E, I, S, MS> siefindmove;

	public float max_distance = 196.0F;
	public float min_distance = 96.0F;

	public final static byte B_MOVE_TO = 1;

	public final static byte B_TP_TO = 2;
	public final static byte B_WALK_TO = 4;
	public byte state = B_WALK_TO;//move_to | tp_to walk_to

	public SIEFollow(S s)
	{
		super(s);
	}

	@Override
	public void init()
	{
		this.sieowner = (SIEOwner<BD, E, I, S, MS>)this.s.ms.si_map.get(SIEOwner.ID);
		this.sielocation = (SIELocation<BD, E, I, S, MS>)this.s.ms.si_map.get(SIELocation.ID);
		this.siefindmove = (SIEFindMove<BD, E, I, S, MS>)this.s.ms.si_map.get(SIEFindMove.ID);
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
//					this.flag |= 4;
//				}
//			}
//			else
//			{
//				this.flag &= 255 - 4;
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
//					this.flag |= 2;
//				}
//			}
//			else
//			{
//				this.flag &= 255 - 2;
//			}
//		}
//		else if (id == 2.1F)
//		{
//			this.max_distance = x;
//		}
//		else if (id == 2.2F)
//		{
//			this.min_distance = x;
//		}
//
//		this.fetch();
//	}
//
//	public void fetch()
//	{
//		byte[] byte_array = new byte[1 + 1 + 4 + 4];
//		byte_array[0] = CSetFollow.ID;
//		byte_array[1] = this.flag;
//		ByteWriter.set(byte_array, this.min_distance, 1 + 1);
//		ByteWriter.set(byte_array, this.max_distance, 1 + 1 + 4);
//		NetworkRegistry.I.sendTo(new ClientMessage(byte_array), this.s.ms.entityplayermp);
//	}

	@Override
	public void onUpdate()
	{
		Entity owner_entity = this.sieowner.getOwner();

		if (owner_entity instanceof EntityPlayerMP)
		{
			if (((EntityPlayerMP)owner_entity).isSpectator())
			{
//				this.s.current_work_byte_array[this.s.bytele.FOLLOW() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.FOLLOW() % 8));
				return;
			}
		}

		E e = this.s.i.getE();
		boolean move_to = (this.state & B_MOVE_TO) == B_MOVE_TO;
		if (owner_entity != null &&
			(this.s.ms.flag & MixSIE.B_MAIN_WORK) == MixSIE.B_MAIN_WORK &&
//			this.s.isWork(this.s.bytele.FOLLOW()) &&
			this.sielocation.in(owner_entity) &&
//			(e.getDistanceSq(owner_entity) > this.min_distance || move_to))
			(SIEArea.getDistanceAABBToAABB(e, owner_entity) > this.min_distance || move_to))
		{
//			if ((owner_entity.world).provider.getDimension() != ((e.world).provider.getDimension()))
//			{
//				if (move_to)
//				{
//					this.siefindmove.endGoal();
//					this.flag ^= 1;
//				}
//
//				this.s.current_work_byte_array[this.s.bytele.FOLLOW() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.FOLLOW() % 8));
//				return;
//			}

//			if ((this.s.main_work_byte_array[this.s.bytele.MINE() / 8] >> this.s.bytele.MINE() % 8 & 1) == 1 && this.s.entitiesaimemory.skinningentitiesmine.blockpos != null)
//			{
//				this.s.entitiesaimemory.skinningentitiesmine.breakWork();
//			}

			this.state |= B_MOVE_TO;
			if (e.isRiding())
			{
				e.dismountRidingEntity();
			}

//			double step = e.getDistanceSq(owner_entity);

//			if ((this.flag & 2) == 2 && step >= this.max_distance)
//			if ((this.flag & 2) == 2 && e.getDistanceSq(owner_entity) >= this.max_distance)
			if ((this.state & B_TP_TO) == B_TP_TO && SIEArea.getDistanceAABBToAABB(e, owner_entity) >= this.max_distance)
			{
				this.tryTeleport(owner_entity);
			}
			else if ((this.state & B_WALK_TO) == B_WALK_TO)
			{
//				if (step <= getClose(e, owner_entity, 1.0D))
				if (SIEArea.getDistanceAABBToAABB(e, owner_entity) <= 1.0D)
				{
					this.siefindmove.endGoal();
					this.state &= 255 - B_MOVE_TO;
				}
				else
				{
//					this.siefindmove.setBreakGoal(owner_entity.posX, owner_entity.posY, owner_entity.posZ);
					this.siefindmove.setGoal(owner_entity.posX, owner_entity.posY, owner_entity.posZ);
				}
			}
		}
		else if (move_to)
		{
			this.siefindmove.endGoal();
			this.state ^= B_MOVE_TO;
		}

//		if ((this.flag & 1) == 0)
//		{
//			this.s.current_work_byte_array[this.s.bytele.FOLLOW() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.FOLLOW() % 8));
//		}
	}

	@Override
	public void writeFile(SIData sidata)
	{
		sidata.byte_array[sidata.index++] = this.state;

		ByteWriter.set(sidata.byte_array, this.min_distance, sidata.index);
		sidata.index += 4;

		ByteWriter.set(sidata.byte_array, this.max_distance, sidata.index);
		sidata.index += 4;
	}

	@Override
	public void readFile(SIData sidata)
	{
		this.state = sidata.byte_array[sidata.index++];

		this.min_distance = ByteReader.getFloat(sidata.byte_array, sidata.index);
		sidata.index += 4;

		this.max_distance = ByteReader.getFloat(sidata.byte_array, sidata.index);
		sidata.index += 4;
	}

	@Override
	public int size()
	{
		return 1 + 4 + 4;
	}

	public void tryTeleport(Entity owner_entity)
	{
		BlockPos blockpos = owner_entity.getPosition();

		for (byte xi : SIEFindMove.PATH_BYTE_ARRAY)
		{
			for (byte yi : SIEFindMove.PATH_BYTE_ARRAY)
			{
				for (byte zi : SIEFindMove.PATH_BYTE_ARRAY)
				{
					if (!(xi == 0 && yi == 0 && zi == 0))
					{
						if (!this.tryTeleportTo(owner_entity, blockpos.getX() + xi, blockpos.getY() + yi, blockpos.getZ() + zi))
						{
							continue;
						}

						return;
					}
				}
			}
		}
	}

	public boolean tryTeleportTo(Entity owner_entity, int x, int y, int z)
	{
		BlockPos to_blockpos = new BlockPos(x, y, z);
		BlockPos down_blockpos = to_blockpos.down();

		if (SIEFindMove.fallBlock(this.s.getMaterial(down_blockpos)) || SIEFindMove.isBlock(this.s.getMaterial(to_blockpos)))
		{
			return false;
		}

		this.s.i.getE().setPositionAndRotation(x + 0.5D, y, z + 0.5D, owner_entity.rotationYaw, owner_entity.rotationPitch);
//		this.s.current_work_byte_array[this.s.bytele.FOLLOW() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.FOLLOW() % 8));
		this.siefindmove.endGoal();

		return true;
	}
}
