package com.nali.list.entity.si;

import com.mojang.authlib.GameProfile;
import com.nali.da.IBothDaNe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.small.entity.memo.server.si.SI;
import com.nali.small.entity.memo.server.si.SIData;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.FakePlayer;

import static com.nali.small.entity.EntityMath.isInArea;

public class SILeMineTo
<
	BD extends IBothDaNe,
	E extends EntityLivingBase,
	I extends IMixE<BD, E>,
	S extends ServerLe<BD, E, I, MS>,
	MS extends MixSIE<BD, E, I, S>
> extends SI<BD, E, I, S, MS>
{
	public static byte ID;

	public SILeSetLocation<BD, E, I, S, MS> silesetlocation;
	public SILeFindMove<BD, E, I, S, MS> silefindmove;
	public SIELook<BD, E, I, S, MS> sielook;

	public byte state;//on //| remote1 x2-4 y8-16 z32-64 +-
	public BlockPos blockpos/*, start_blockpos, end_blockpos*/;
	public float hardness;
//	public float goal_x, goal_y, goal_z;
	public double goal_x, goal_y, goal_z;

	public SILeMineTo(S s)
	{
		super(s);
	}

	@Override
	public void init()
	{
		this.silesetlocation = (SILeSetLocation<BD, E, I, S, MS>)this.s.ms.si_map.get(SILeSetLocation.ID);
		this.silefindmove = (SILeFindMove<BD, E, I, S, MS>)this.s.ms.si_map.get(SILeFindMove.ID);
		this.sielook = (SIELook<BD, E, I, S, MS>)this.s.ms.si_map.get(SIELook.ID);
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
			if ((this.state & 1) == 1)
			{
	//			if (e.isEntityInsideOpaqueBlock())
				if (this.s.worldserver.getBlockState(e.getPosition().up()).getMaterial().isSolid())
				{
					this.blockpos = e.getPosition().up();
				}

	//			if (this.blockpos == null)
	//			{
	////			this.s.current_work_byte_array[this.s.bytele.MINE() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.MINE() % 8));//0
	////			this.reset();
	//			}
	//			else
				if (this.blockpos != null)
				{
					IBlockState iblockstate = this.s.worldserver.getBlockState(this.blockpos);
					Block block = iblockstate.getBlock();

					if (block == Blocks.AIR)
					{
						this.clear();
	//					worldserver.sendBlockBreakProgress(e.getEntityId(), this.blockpos, -1);
	//				this.s.current_work_byte_array[this.s.bytele.MINE() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.MINE() % 8));//0
						return;
					}

					if (e.getDistanceSq(this.blockpos) > 8.0D)
					{
	//					BlockPos blockpos = this.blockpos;
						if (this.silesetlocation.far == 0 || this.silesetlocation.blockpos == null || isInArea(this.blockpos, this.silesetlocation.blockpos, this.silesetlocation.far))
						{
							this.silefindmove.setGoal(this.blockpos.getX() + 0.5D, this.blockpos.getY() + 0.5D, this.blockpos.getZ() + 0.5D);
						}
	//					this.blockpos = blockpos;
					}
					else
					{
						this.sielook.set(this.blockpos.getX() + 0.5D, this.blockpos.getY(), this.blockpos.getZ() + 0.5D, 5.0F);
						this.silefindmove.endGoal();

						FakePlayer fakeplayer = new FakePlayer(this.s.worldserver, new GameProfile(null, "!"));
						fakeplayer.setHeldItem(EnumHand.MAIN_HAND, e.getHeldItemMainhand());
						float f = iblockstate.getPlayerRelativeBlockHardness(fakeplayer, this.s.worldserver, this.blockpos);
						if (f < 0.005F)
						{
							f = 0.005F;
						}

						this.hardness += f;
						if (this.hardness > 1.0F)
						{
							this.hardness = 1.0F;
						}

						if (e.ticksExisted % 4 == 0)
						{
							this.s.worldserver.sendBlockBreakProgress(e.getEntityId(), this.blockpos, (int)(this.hardness * 10) - 1);
							SoundType soundtype = block.getSoundType();
							this.s.worldserver.playSound(null, this.blockpos, soundtype.getHitSound(), SoundCategory.NEUTRAL, (soundtype.getVolume() + 1.0F) / 8.0F, soundtype.getPitch() * 0.5F);
						}

						if (this.hardness == 1.0F)
						{
							this.s.worldserver.playEvent(2001, this.blockpos, Block.getIdFromBlock(block));

							if (block.removedByPlayer(iblockstate, this.s.worldserver, this.blockpos, fakeplayer, false))
							{
								block.onPlayerDestroy(this.s.worldserver, this.blockpos, iblockstate);
								block.harvestBlock(this.s.worldserver, fakeplayer, this.blockpos, iblockstate, this.s.worldserver.getTileEntity(this.blockpos), e.getHeldItemMainhand());
								e.getHeldItemMainhand().damageItem(1, e);
							}

	//						worldserver.sendBlockBreakProgress(e.getEntityId(), this.blockpos, -1);
							this.clear();
	//					this.s.current_work_byte_array[this.s.bytele.MINE() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.MINE() % 8));//0
						}
					}

					this.s.ms.state &= 255-1;
				}
			}
	//		switch (this.s.ms.state & 4+1)
	//		{
	//			case 1:
	//				this.breakWork();
	//				break;
	//			case 4:
	//				//mine ai later
	//				if (this.s.isMove())
	//				{
	//
	//				}
	////				this.s.current_work_byte_array[this.s.bytele.MINE() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.MINE() % 8));
	//				break;
	//			default:
	//		}
	//		if (this.s.isMove())
	//		{
			//		if ((this.s.main_work_byte_array[this.s.bytele.MINE() / 8] >> this.s.bytele.MINE() % 8 & 1) == 1 && this.s.entitiesaimemory.blockpos != null)

	//		if ((this.s.ms.state & 1) == 1)
	//		{
	//		}

	//		if (this.s.isWork(this.s.bytele.MINE()))
	//		if ((this.s.ms.state & 4) == 4)
	//		{
	//		}
	//		else
	//		{
	//			this.clear();
	//		}
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

//	@Override
//	public void writeNBT(NBTTagCompound nbttagcompound)
//	{
//		nbttagcompound.setByte("AILeMineTo_state", this.state);
////		if (this.start_blockpos != null)
////		{
////			new_nbttagcompound.setLong("start_blockpos", this.start_blockpos.toLong());
////		}
////		if (this.end_blockpos != null)
////		{
////			new_nbttagcompound.setLong("end_blockpos", this.end_blockpos.toLong());
////		}
//	}
//
//	@Override
//	public void readNBT(NBTTagCompound nbttagcompound)
//	{
//		this.state = nbttagcompound.getByte("AILeMineTo_state");
////			if (new_nbttagcompound.hasKey("start_blockpos_mine"))
////			{
////				this.start_blockpos = BlockPos.fromLong(new_nbttagcompound.getLong("start_blockpos_mine"));
////			}
////			if (new_nbttagcompound.hasKey("end_blockpos_mine"))
////			{
////				this.end_blockpos = BlockPos.fromLong(new_nbttagcompound.getLong("end_blockpos_mine"));
////			}
//	}

//	private void breakWork()
//	{
//		E e = this.s.getI().getE();
//
//		if (e.isEntityInsideOpaqueBlock())
//		{
//			this.blockpos = e.getPosition().up();
//		}
//
//		if (this.blockpos == null)
//		{
//			this.s.ms.state &= 255-1;
////			this.s.current_work_byte_array[this.s.bytele.MINE() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.MINE() % 8));//0
////			this.reset();
//		}
//		else
//		{
//			WorldServer worldserver = (WorldServer)e.world;
//			IBlockState iblockstate = worldserver.getBlockState(this.blockpos);
//			Block block = iblockstate.getBlock();
//
//			if (block == Blocks.AIR)
//			{
//				this.clear();
////					worldserver.sendBlockBreakProgress(e.getEntityId(), this.blockpos, -1);
//				this.s.ms.state &= 255-1;
////				this.s.current_work_byte_array[this.s.bytele.MINE() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.MINE() % 8));//0
//				return;
//			}
//
//			if (e.getDistanceSq(this.blockpos) > 8.0D)
//			{
////					BlockPos blockpos = this.blockpos;
//				this.silefindmove.setGoal(this.blockpos.getX() + 0.5D, this.blockpos.getY() + 0.5D, this.blockpos.getZ() + 0.5D);
////					this.blockpos = blockpos;
//			}
//			else
//			{
//				this.sielook.set(this.blockpos.getX() + 0.5D, this.blockpos.getY(), this.blockpos.getZ() + 0.5D, 5.0F);
//				this.silefindmove.endGoal();
//
//				FakePlayer fakeplayer = new FakePlayer(worldserver, new GameProfile(null, "!"));
//				fakeplayer.setHeldItem(EnumHand.MAIN_HAND, e.getHeldItemMainhand());
//				float f = iblockstate.getPlayerRelativeBlockHardness(fakeplayer, worldserver, this.blockpos);
//				if (f < 0.005F)
//				{
//					f = 0.005F;
//				}
//
//				this.hardness += f;
//				if (this.hardness > 1.0F)
//				{
//					this.hardness = 1.0F;
//				}
//
//				if (e.ticksExisted % 4 == 0)
//				{
//					worldserver.sendBlockBreakProgress(e.getEntityId(), this.blockpos, (int)(this.hardness * 10) - 1);
//					SoundType soundtype = block.getSoundType();
//					worldserver.playSound(null, this.blockpos, soundtype.getHitSound(), SoundCategory.NEUTRAL, (soundtype.getVolume() + 1.0F) / 8.0F, soundtype.getPitch() * 0.5F);
//				}
//
//				if (this.hardness == 1.0F)
//				{
//					worldserver.playEvent(2001, this.blockpos, Block.getIdFromBlock(block));
//
//					if (block.removedByPlayer(iblockstate, worldserver, this.blockpos, fakeplayer, false))
//					{
//						block.onPlayerDestroy(worldserver, this.blockpos, iblockstate);
//						block.harvestBlock(worldserver, fakeplayer, this.blockpos, iblockstate, worldserver.getTileEntity(this.blockpos), e.getHeldItemMainhand());
//						e.getHeldItemMainhand().damageItem(1, e);
//					}
//
////						worldserver.sendBlockBreakProgress(e.getEntityId(), this.blockpos, -1);
//					this.clear();
//					this.s.ms.state &= 255-1;
////					this.s.current_work_byte_array[this.s.bytele.MINE() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.MINE() % 8));//0
//				}
//			}
//		}
//	}

	public void clear()
	{
		this.reset();
		this.blockpos = null;
	}

	public void reset()
	{
		if (this.hardness != 0)
		{
			this.s.worldserver.sendBlockBreakProgress(this.s.i.getE().getEntityId(), this.blockpos, -1);
			this.hardness = 0;
		}
	}
}
