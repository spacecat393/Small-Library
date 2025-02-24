package com.nali.list.entity.si;

import com.nali.da.IBothDaE;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.small.entity.memo.server.si.SI;
import com.nali.small.entity.memo.server.si.SIData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

public class SIEMineTo
<
	BD extends IBothDaE,
	E extends Entity,
	I extends IMixE<BD, E>,
	S extends ServerE<BD, E, I, MS>,
	MS extends MixSIE<BD, E, I, S>
> extends SI<BD, E, I, S, MS>
{
	public static byte ID;

	public SIELocation<BD, E, I, S, MS> sielocation;
	public SIEFindMove<BD, E, I, S, MS> siefindmove;
	public SIELook<BD, E, I, S, MS> sielook;

	public final static byte B_ON = 1;
	public final static byte B_REMOTE = 2;
	public byte flag = B_ON;//on //| remote1 x2-4 y8-16 z32-64 +-

	public BlockPos blockpos/*, start_blockpos, end_blockpos*/;
	public float hardness;
//	public float goal_x, goal_y, goal_z;
//	public double goal_x, goal_y, goal_z;

	public SIEMineTo(S s)
	{
		super(s);
	}

	@Override
	public void init()
	{
		this.sielocation = (SIELocation<BD, E, I, S, MS>)this.s.ms.si_map.get(SIELocation.ID);
		this.siefindmove = (SIEFindMove<BD, E, I, S, MS>)this.s.ms.si_map.get(SIEFindMove.ID);
		this.sielook = (SIELook<BD, E, I, S, MS>)this.s.ms.si_map.get(SIELook.ID);
//		if (this.sielook == null)
//		{
//			this.sielook = (SIELook)this.s.ms.si_map.get(SILeLook.ID);
//		}
	}

	@Override
	public void call(EntityPlayerMP entityplayermp, byte[] byte_array)
	{
	}

	@Override
	public void onUpdate()
	{
//		E e = this.s.i.getE();
//		if (this.s.isMove(e))
//		{
//			if ((this.flag & B_WORK) == B_WORK)
//			{
//	//			if (e.isEntityInsideOpaqueBlock())
//				if (this.s.worldserver.getBlockState(e.getPosition().up()).getMaterial().isSolid())
//				{
//					this.blockpos = e.getPosition().up();
//				}
//
//	//			if (this.blockpos == null)
//	//			{
//	////			this.s.current_work_byte_array[this.s.bytele.MINE() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.MINE() % 8));//0
//	////			this.reset();
//	//			}
//	//			else
//				if (this.blockpos != null)
//				{
//					IBlockState iblockstate = this.s.worldserver.getBlockState(this.blockpos);
//					Block block = iblockstate.getBlock();
//
//					if (block == Blocks.AIR)
//					{
//						this.clear();
//	//					worldserver.sendBlockBreakProgress(e.getEntityId(), this.blockpos, -1);
//	//				this.s.current_work_byte_array[this.s.bytele.MINE() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.MINE() % 8));//0
//						return;
//					}
//
//					if (e.getDistanceSq(this.blockpos) > 8.0D)
//					{
//	//					BlockPos blockpos = this.blockpos;
//						if (this.sielocation.far == 0 || this.sielocation.blockpos == null || isInArea(this.blockpos, this.sielocation.blockpos, this.sielocation.far))
//						{
//							this.siefindmove.setGoal(this.blockpos.getX() + 0.5D, this.blockpos.getY() + 0.5D, this.blockpos.getZ() + 0.5D);
//						}
//	//					this.blockpos = blockpos;
//					}
//					else
//					{
//						this.sielook.set(this.blockpos.getX() + 0.5D - e.posX, this.blockpos.getY() - e.posY, this.blockpos.getZ() + 0.5D - e.posZ, (byte)2);
//						this.siefindmove.endGoal();
//
//						FakePlayer fakeplayer = new FakePlayer(this.s.worldserver, new GameProfile(null, "!"));
//						fakeplayer.setHeldItem(EnumHand.MAIN_HAND, this.getItemStack(e));
//						float f = iblockstate.getPlayerRelativeBlockHardness(fakeplayer, this.s.worldserver, this.blockpos);
//						if (f < 0.005F)
//						{
//							f = 0.005F;
//						}
//
//						this.hardness += f;
//						if (this.hardness > 1.0F)
//						{
//							this.hardness = 1.0F;
//						}
//
//						if (e.ticksExisted % 4 == 0)
//						{
//							this.s.worldserver.sendBlockBreakProgress(e.getEntityId(), this.blockpos, (int)(this.hardness * 10) - 1);
//							SoundType soundtype = block.getSoundType();
//							this.s.worldserver.playSound(null, this.blockpos, soundtype.getHitSound(), SoundCategory.NEUTRAL, (soundtype.getVolume() + 1.0F) / 8.0F, soundtype.getPitch() * 0.5F);
//						}
//
//						if (this.hardness == 1.0F)
//						{
//							this.s.worldserver.playEvent(2001, this.blockpos, Block.getIdFromBlock(block));
//
//							if (block.removedByPlayer(iblockstate, this.s.worldserver, this.blockpos, fakeplayer, false))
//							{
//								block.onPlayerDestroy(this.s.worldserver, this.blockpos, iblockstate);
//								block.harvestBlock(this.s.worldserver, fakeplayer, this.blockpos, iblockstate, this.s.worldserver.getTileEntity(this.blockpos), this.getItemStack(e));
//								this.damageItem(e);
////								this.getItemStack(e).damageItem(1, e);
//							}
//
//	//						worldserver.sendBlockBreakProgress(e.getEntityId(), this.blockpos, -1);
//							this.clear();
//	//					this.s.current_work_byte_array[this.s.bytele.MINE() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.MINE() % 8));//0
//						}
//					}
//
//					this.s.ms.flag &= 255 - MixSIE.B_MAIN_WORK;
//				}
//			}
//	//		switch (this.s.ms.state & 4+1)
//	//		{
//	//			case 1:
//	//				this.breakWork();
//	//				break;
//	//			case 4:
//	//				//mine ai later
//	//				if (this.s.isMove())
//	//				{
//	//
//	//				}
//	////				this.s.current_work_byte_array[this.s.bytele.MINE() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.MINE() % 8));
//	//				break;
//	//			default:
//	//		}
//	//		if (this.s.isMove())
//	//		{
//			//		if ((this.s.main_work_byte_array[this.s.bytele.MINE() / 8] >> this.s.bytele.MINE() % 8 & 1) == 1 && this.s.entitiesaimemory.blockpos != null)
//
//	//		if ((this.s.ms.state & 1) == 1)
//	//		{
//	//		}
//
//	//		if (this.s.isWork(this.s.bytele.MINE()))
//	//		if ((this.s.ms.state & 4) == 4)
//	//		{
//	//		}
//	//		else
//	//		{
//	//			this.clear();
//	//		}
//	//		}
//		}
	}

	public ItemStack getItemStack(E e)
	{
		//e.getHeldItemMainhand()
		return ItemStack.EMPTY;
	}

	public void damageItem(E e)
	{
//		this.getItemStack(e).damageItem(1, e);
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
//				this.siefindmove.setGoal(this.blockpos.getX() + 0.5D, this.blockpos.getY() + 0.5D, this.blockpos.getZ() + 0.5D);
////					this.blockpos = blockpos;
//			}
//			else
//			{
//				this.sielook.set(this.blockpos.getX() + 0.5D, this.blockpos.getY(), this.blockpos.getZ() + 0.5D, 5.0F);
//				this.siefindmove.endGoal();
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
