package com.nali.list.entity.si;

import com.mojang.authlib.GameProfile;
import com.nali.da.IBothDaE;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.small.entity.memo.server.si.SI;
import com.nali.small.entity.memo.server.si.SIData;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;

import java.util.ArrayList;
import java.util.List;

public class SIEWalkTo
<
	BD extends IBothDaE,
	E extends Entity,
	I extends IMixE<BD, E>,
	S extends ServerE<BD, E, I, MS>,
	MS extends MixSIE<BD, E, I, S>
> extends SI<BD, E, I, S, MS>
{
	public static byte ID;

	public SIESit<BD, E, I, S, MS> siesit;

	public byte state;

//	public List<Block> block_list = new List<Block>();
	public List<BlockPos> blockpos_list = new ArrayList();
	public Vec3d vec3d;

//	public int tick;

	public SIEWalkTo(S s)
	{
		super(s);
	}

	@Override
	public void init()
	{
		this.siesit = (SIESit<BD, E, I, S, MS>)this.s.ms.si_map.get(SIESit.ID);
	}

	@Override
	public void call()
	{

	}

	@Override
	public void onUpdate()
	{
		E e = this.s.i.getE();
		if (this.s.isMove(e) && (this.siesit.state & 1) == 1)
		{
	//		if ((this.s.current_work_byte_array[this.s.workbytes.WALK_TO() / 8] >> this.s.workbytes.WALK_TO() % 8 & 1) == 1)
	//		if ((this.s.work_byte_array[this.num / 8] >> this.num % 8 & 1) == 1)
			if ((this.state & 1) == 1)
			{
				if (e.ticksExisted % 20 == 0)
				{
					Vec3d vec3d = e.getPositionVector();
					for (BlockPos blockpos : this.blockpos_list)
					{
						if (e.getDistanceSq(blockpos) < 8.0D && this.vec3d != null && this.vec3d.squareDistanceTo(vec3d) < 0.0001D)
						{
							World world = e.world;
							IBlockState iblockstate = world.getBlockState(blockpos);
							Block block = iblockstate.getBlock();
	//					Block block = this.block_list.get(i);
							if (block instanceof BlockDoor)
							{
								BlockDoor blockdoor = (BlockDoor)block;
	//							blockdoor.toggleDoor(world, blockpos, !blockdoor.isPassable(world, blockpos));
								FakePlayer fakeplayer = new FakePlayer(this.s.worldserver, new GameProfile(null, "!"));
								fakeplayer.rotationYaw = e.rotationYaw;
								blockdoor.onBlockActivated(world, blockpos, iblockstate, fakeplayer, EnumHand.MAIN_HAND, EnumFacing.fromAngle(fakeplayer.rotationYaw), 0, 0, 0);
							}
							else if (block instanceof BlockFenceGate)
							{
								BlockFenceGate blockfencegate = (BlockFenceGate)block;
								FakePlayer fakeplayer = new FakePlayer(this.s.worldserver, new GameProfile(null, "!"));
								fakeplayer.rotationYaw = e.rotationYaw;
								blockfencegate.onBlockActivated(world, blockpos, iblockstate, fakeplayer, EnumHand.MAIN_HAND, EnumFacing.fromAngle(fakeplayer.rotationYaw), 0, 0, 0);
							}
							else if (block instanceof BlockTrapDoor)
							{
								BlockTrapDoor blocktrapdoor = (BlockTrapDoor)block;
								FakePlayer fakeplayer = new FakePlayer(this.s.worldserver, new GameProfile(null, "!"));
								fakeplayer.rotationYaw = e.rotationYaw;
								blocktrapdoor.onBlockActivated(world, blockpos, iblockstate, fakeplayer, EnumHand.MAIN_HAND, EnumFacing.fromAngle(fakeplayer.rotationYaw), 0, 0, 0);
							}

	//						this.tick = 20;
						}
					}

					this.vec3d = vec3d;
				}
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
