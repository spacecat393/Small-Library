package com.nali.small.entities.skinning.ai.path;

import com.mojang.authlib.GameProfile;
import com.nali.small.entities.memory.server.ServerEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.entities.skinning.ai.SkinningEntitiesAI;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;

import java.util.ArrayList;

public class SkinningEntitiesWalkTo extends SkinningEntitiesAI
{
//    public ArrayList<Block> block_arraylist = new ArrayList<Block>();
    public ArrayList<BlockPos> blockpos_arraylist = new ArrayList<BlockPos>();
    public Vec3d vec3d;

//    public int tick;

    public SkinningEntitiesWalkTo(SkinningEntities skinningentities)
    {
        super(skinningentities);
    }

    @Override
    public void onUpdate()
    {
        ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.skinningentities.bothentitiesmemory;
        if ((serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.WALK_TO() / 8] >> serverentitiesmemory.workbytes.WALK_TO() % 8 & 1) == 1)
        {
            if (this.skinningentities.ticksExisted % 20 == 0)
            {
                Vec3d vec3d = this.skinningentities.getPositionVector();
                for (BlockPos blockpos : this.blockpos_arraylist)
                {
                    if (this.skinningentities.getDistanceSq(blockpos) < 8.0D && this.vec3d != null && this.vec3d.squareDistanceTo(vec3d) < 0.0001D)
                    {
                        World world = this.skinningentities.world;
                        IBlockState iblockstate = world.getBlockState(blockpos);
                        Block block = iblockstate.getBlock();
//                    Block block = this.block_arraylist.get(i);
                        if (block instanceof BlockDoor)
                        {
                            BlockDoor blockdoor = (BlockDoor)block;
//                            blockdoor.toggleDoor(world, blockpos, !blockdoor.isPassable(world, blockpos));
                            FakePlayer fakeplayer = new FakePlayer((WorldServer)skinningentities.world, new GameProfile(null, "!"));
                            fakeplayer.rotationYaw = this.skinningentities.rotationYaw;
                            blockdoor.onBlockActivated(world, blockpos, iblockstate, fakeplayer, EnumHand.MAIN_HAND, EnumFacing.fromAngle(fakeplayer.rotationYaw), 0, 0, 0);
                        }
                        else if (block instanceof BlockFenceGate)
                        {
                            BlockFenceGate blockfencegate = (BlockFenceGate)block;
                            FakePlayer fakeplayer = new FakePlayer((WorldServer)skinningentities.world, new GameProfile(null, "!"));
                            fakeplayer.rotationYaw = this.skinningentities.rotationYaw;
                            blockfencegate.onBlockActivated(world, blockpos, iblockstate, fakeplayer, EnumHand.MAIN_HAND, EnumFacing.fromAngle(fakeplayer.rotationYaw), 0, 0, 0);
                        }
                        else if (block instanceof BlockTrapDoor)
                        {
                            BlockTrapDoor blocktrapdoor = (BlockTrapDoor)block;
                            FakePlayer fakeplayer = new FakePlayer((WorldServer)skinningentities.world, new GameProfile(null, "!"));
                            fakeplayer.rotationYaw = this.skinningentities.rotationYaw;
                            blocktrapdoor.onBlockActivated(world, blockpos, iblockstate, fakeplayer, EnumHand.MAIN_HAND, EnumFacing.fromAngle(fakeplayer.rotationYaw), 0, 0, 0);
                        }

//                        this.tick = 20;
                    }
                }

                this.vec3d = vec3d;
            }
        }
    }
}
