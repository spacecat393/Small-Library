package com.nali.small.entities.skinning.ai.path;

import com.nali.small.entities.memory.server.ServerEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.entities.skinning.ai.SkinningEntitiesAI;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.ArrayList;

import static com.nali.small.entities.skinning.ai.path.PathMath.*;

public class SkinningEntitiesFindMove extends SkinningEntitiesAI
{
    public static int MAX_G = 64;

    public int goal_x;
    public int goal_y;
    public int goal_z;

    public int temp_goal_x;
    public int temp_goal_y;
    public int temp_goal_z;
    public double far;

    public ArrayList<BlockPos> path_blockpos_arraylist = new ArrayList<BlockPos>();
    public SNode to_goal_snode;
    public boolean is_goal;
    public int path_index;
    public int path_tick;
    public boolean try_move = false;

    public double old_x;
    public double old_y;
    public double old_z;

    public SkinningEntitiesFindMove(SkinningEntities skinningentities)
    {
        super(skinningentities);
    }

    @Override
    public void onUpdate()
    {
        ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.skinningentities.bothentitiesmemory;

        BlockPos blockpos = this.skinningentities.getPosition();

//        World world = this.skinningentities.world;
////        if (this.path_blockpos_array != null)
////        {
//        for (BlockPos bp : this.path_blockpos_arraylist)
//        {
//            if (bp != null)
//            {
//                ((WorldServer) world).spawnParticle(EnumParticleTypes.CRIT, bp.getX() + 0.5, bp.getY() + 0.5, bp.getZ() + 0.5, 1, 0.0D, 0.0D, 0.0D, 0.0D);
//            }
//        }
////        }

        if (this.try_move)
        {
            if (this.temp_goal_x == this.goal_x && this.temp_goal_y == this.goal_y && this.temp_goal_z == this.goal_z && !this.path_blockpos_arraylist.isEmpty())
            {
                if (!this.is_goal)
                {
                    SNode temp_snode = this.find(this.to_goal_snode);
                    if (temp_snode != null)
                    {
                        int old_max_index = this.path_blockpos_arraylist.size();
                        this.to_goal_snode = temp_snode;

                        this.path_blockpos_arraylist.clear();
                        this.path_blockpos_arraylist.add(temp_snode.blockpos);

                        while (temp_snode.parent_snode != null)
                        {
                            temp_snode = temp_snode.parent_snode;
                            this.path_blockpos_arraylist.add(temp_snode.blockpos);
                        }

                        this.path_index = this.path_blockpos_arraylist.size() - (old_max_index - this.path_index);

                        if (this.path_index == -1)
                        {
                            this.path_index = 0;
                        }
                    }
                }

//                if (this.is_goal)
//                {
                if (this.path_index < 0)
                {
                    this.path_blockpos_arraylist.clear();
                    return;
                }

                BlockPos current_blockpos = this.path_blockpos_arraylist.get(this.path_index);

                serverentitiesmemory.entitiesaimemory.skinningentitiesmove.setWanted(current_blockpos.getX() + 0.5D, current_blockpos.getY(), current_blockpos.getZ() + 0.5D);

                double far = this.skinningentities.getDistanceSq(this.goal_x + 0.5D, this.goal_y, this.goal_z + 0.5D);
                if ((!this.is_goal && far >= this.far) || (this.is_goal && this.skinningentities.posX == this.old_x && this.skinningentities.posY == this.old_y && this.skinningentities.posZ == this.old_z))
                {
                    if (++this.path_tick == 200)
                    {
                        this.path_blockpos_arraylist.clear();
                        return;
                    }
                }

                this.old_x = this.skinningentities.posX;
                this.old_y = this.skinningentities.posY;
                this.old_z = this.skinningentities.posZ;

                int new_index = this.path_index + 1;
                if (new_index != this.path_blockpos_arraylist.size())
                {
                    this.far = this.path_blockpos_arraylist.get(new_index).getDistance(this.goal_x, this.goal_y, this.goal_z);
                }

                if (serverentitiesmemory.entitiesaimemory.skinningentitiesmove.isDone())
                {
                    if (--this.path_index == -1)
                    {
                        this.try_move = false;
                        this.path_blockpos_arraylist.clear();
                    }
                }
//                }
            }
            else
            {
                serverentitiesmemory.entitiesaimemory.skinningentitiespassto.blockpos_arraylist.clear();
                serverentitiesmemory.entitiesaimemory.skinningentitiespassto.block_arraylist.clear();
                this.old_x = this.skinningentities.posX;
                this.old_y = this.skinningentities.posY;
                this.old_z = this.skinningentities.posZ;
                this.to_goal_snode = null;
                this.path_blockpos_arraylist.clear();
                this.is_goal = false;
                this.path_tick = 0;

                SNode snode = new SNode(blockpos);
                SNode temp_snode = this.find(snode);

                if (temp_snode != null)
                {
                    this.far = blockpos.getDistance(this.goal_x, this.goal_y, this.goal_z);
                    if (!this.is_goal)
                    {
                        this.to_goal_snode = temp_snode;
                    }

                    this.path_blockpos_arraylist.add(temp_snode.blockpos);

                    while (temp_snode.parent_snode != null)
                    {
                        temp_snode = temp_snode.parent_snode;
                        this.path_blockpos_arraylist.add(temp_snode.blockpos);
                    }

                    this.path_index = this.path_blockpos_arraylist.size() - 1;

                    if (this.path_index == -1)
                    {
                        this.path_index = 0;
                    }
                }
            }

            this.temp_goal_x = this.goal_x;
            this.temp_goal_y = this.goal_y;
            this.temp_goal_z = this.goal_z;
        }
    }

    public SNode find(SNode start_snode)
    {
        if (start_snode.blockpos.getX() == this.goal_x && start_snode.blockpos.getY() == this.goal_y && start_snode.blockpos.getZ() == this.goal_z)
        {
            this.is_goal = true;
            return start_snode;
        }

        SNode next_snode = this.nextSNode(start_snode);
        if (next_snode == null)
        {
            return start_snode.parent_snode;
        }
        else
        {
            return next_snode;
        }
    }

    public void setGoal(double x, double y, double z)
    {
        this.setGoal(MathHelper.floor(x), MathHelper.floor(y), MathHelper.floor(z));
    }

    public void setGoal(int x, int y, int z)
    {
        this.goal_x = x;
        this.goal_y = y;
        this.goal_z = z;
        this.try_move = true;
    }

    public void endGoal()
    {
        this.try_move = false;
    }

    public boolean endGoalT()
    {
        this.try_move = false;
        return true;
    }

    public SNode nextSNode(SNode start_snode)
    {
        if (start_snode.children_snode_array == null)
        {
            return null;
        }

        World world = this.skinningentities.world;

        SNode pre_snode;
        double to_x = (this.goal_x + 0.5D) - (start_snode.blockpos.getX() + 0.5D);
        double to_y = (this.goal_y + 0.5D) - (start_snode.blockpos.getY() + 0.5D);
        double to_z = (this.goal_z + 0.5D) - (start_snode.blockpos.getZ() + 0.5D);
        double length = to_x * to_x + to_y * to_y + to_z * to_z;
        byte new_x = 0;
        byte new_y = 0;
        byte new_z = 0;
        if (length != 0)
        {
            length = Math.sqrt(length);
            new_x = signum(to_x / length);
            new_y = signum(to_y / length);
            new_z = signum(to_z / length);
        }

        pre_snode = this.setChild(start_snode, new_x, new_y, new_z);
        if (pre_snode != null)
        {
            return pre_snode;
        }

        if (new_y != 0)
        {
            //0

            pre_snode = this.setChild(start_snode, new_x, new_y, (byte)0);
            if (pre_snode != null)
            {
                return pre_snode;
            }

            pre_snode = this.setChild(start_snode, (byte)0, new_y, new_z);
            if (pre_snode != null)
            {
                return pre_snode;
            }

            pre_snode = this.setChild(start_snode, (byte)0, new_y, (byte)0);
            if (pre_snode != null)
            {
                return pre_snode;
            }

            //-1

            pre_snode = this.setChild(start_snode, new_x, new_y, (byte)-1);
            if (pre_snode != null)
            {
                return pre_snode;
            }

            pre_snode = this.setChild(start_snode, (byte)-1, new_y, new_z);
            if (pre_snode != null)
            {
                return pre_snode;
            }

            pre_snode = this.setChild(start_snode, (byte)-1, new_y, (byte)-1);
            if (pre_snode != null)
            {
                return pre_snode;
            }

            //1

            pre_snode = this.setChild(start_snode, new_x, new_y, (byte)1);
            if (pre_snode != null)
            {
                return pre_snode;
            }

            pre_snode = this.setChild(start_snode, (byte)1, new_y, new_z);
            if (pre_snode != null)
            {
                return pre_snode;
            }

            pre_snode = this.setChild(start_snode, (byte)1, new_y, (byte)1);
            if (pre_snode != null)
            {
                return pre_snode;
            }

            //0 1

            pre_snode = this.setChild(start_snode, (byte)0, new_y, (byte)1);
            if (pre_snode != null)
            {
                return pre_snode;
            }

            pre_snode = this.setChild(start_snode, (byte)1, new_y, (byte)0);
            if (pre_snode != null)
            {
                return pre_snode;
            }

            //0 -1

            pre_snode = this.setChild(start_snode, (byte)0, new_y, (byte)-1);
            if (pre_snode != null)
            {
                return pre_snode;
            }

            pre_snode = this.setChild(start_snode, (byte)-1, new_y, (byte)0);
            if (pre_snode != null)
            {
                return pre_snode;
            }

            //-1 1

            pre_snode = this.setChild(start_snode, (byte)1, new_y, (byte)-1);
            if (pre_snode != null)
            {
                return pre_snode;
            }

            pre_snode = this.setChild(start_snode, (byte)-1, new_y, (byte)1);
            if (pre_snode != null)
            {
                return pre_snode;
            }
        }
        else
        {
            pre_snode = this.setChild(start_snode, new_x, (byte)0, new_z);
            if (pre_snode != null)
            {
                return pre_snode;
            }

            pre_snode = this.setChild(start_snode, new_x, (byte)1, new_z);
            if (pre_snode != null)
            {
                return pre_snode;
            }

            pre_snode = this.setChild(start_snode, new_x, (byte)-1, new_z);
            if (pre_snode != null)
            {
                return pre_snode;
            }

            //0 0

            pre_snode = this.setChild(start_snode, new_x, (byte)0, (byte)0);
            if (pre_snode != null)
            {
                return pre_snode;
            }

            pre_snode = this.setChild(start_snode, (byte)0, (byte)0, new_z);
            if (pre_snode != null)
            {
                return pre_snode;
            }

            //0 1

            pre_snode = this.setChild(start_snode, new_x, (byte)0, (byte)1);
            if (pre_snode != null)
            {
                return pre_snode;
            }

            pre_snode = this.setChild(start_snode, (byte)1, (byte)0, new_z);
            if (pre_snode != null)
            {
                return pre_snode;
            }

            //0 -1

            pre_snode = this.setChild(start_snode, new_x, (byte)0, (byte)-1);
            if (pre_snode != null)
            {
                return pre_snode;
            }

            pre_snode = this.setChild(start_snode, (byte)-1, (byte)0, new_z);
            if (pre_snode != null)
            {
                return pre_snode;
            }

            //1 0

            pre_snode = this.setChild(start_snode, new_x, (byte)1, (byte)0);
            if (pre_snode != null)
            {
                return pre_snode;
            }

            pre_snode = this.setChild(start_snode, (byte)0, (byte)1, new_z);
            if (pre_snode != null)
            {
                return pre_snode;
            }

            //1 1

            pre_snode = this.setChild(start_snode, new_x, (byte)1, (byte)1);
            if (pre_snode != null)
            {
                return pre_snode;
            }

            pre_snode = this.setChild(start_snode, (byte)1, (byte)1, new_z);
            if (pre_snode != null)
            {
                return pre_snode;
            }

            //1 -1

            pre_snode = this.setChild(start_snode, new_x, (byte)1, (byte)-1);
            if (pre_snode != null)
            {
                return pre_snode;
            }

            pre_snode = this.setChild(start_snode, (byte)-1, (byte)1, new_z);
            if (pre_snode != null)
            {
                return pre_snode;
            }

            //-1 0

            pre_snode = this.setChild(start_snode, new_x, (byte)-1, (byte)0);
            if (pre_snode != null)
            {
                return pre_snode;
            }

            pre_snode = this.setChild(start_snode, (byte)0, (byte)-1, new_z);
            if (pre_snode != null)
            {
                return pre_snode;
            }

            //-1 1

            pre_snode = this.setChild(start_snode, new_x, (byte)-1, (byte)1);
            if (pre_snode != null)
            {
                return pre_snode;
            }

            pre_snode = this.setChild(start_snode, (byte)1, (byte)-1, new_z);
            if (pre_snode != null)
            {
                return pre_snode;
            }

            //-1 -1

            pre_snode = this.setChild(start_snode, new_x, (byte)-1, (byte)-1);
            if (pre_snode != null)
            {
                return pre_snode;
            }

            pre_snode = this.setChild(start_snode, (byte)-1, (byte)-1, new_z);
            if (pre_snode != null)
            {
                return pre_snode;
            }
        }

        pre_snode = this.setChild(start_snode, new_x, (byte)0, (byte)0);
        if (pre_snode != null)
        {
            return pre_snode;
        }

        pre_snode = this.setChild(start_snode, (byte)0, (byte)0, new_z);
        if (pre_snode != null)
        {
            return pre_snode;
        }

        pre_snode = this.setChild(start_snode, new_x, (byte)0, new_z);
        if (pre_snode != null)
        {
            return pre_snode;
        }

        int index = 0;

        for (byte x : PATH_BYTE_ARRAY)
        {
            for (byte y : PATH_BYTE_ARRAY)
            {
                for (byte z : PATH_BYTE_ARRAY)
                {
                    if (!(x == 0 && y == 0 && z == 0))
                    {
                        BlockPos blockpos;
                        if (start_snode.children_snode_array[index] == null)
                        {
                            blockpos = new BlockPos(start_snode.blockpos.getX() + x, start_snode.blockpos.getY() + y, start_snode.blockpos.getZ() + z);
                            start_snode.children_snode_array[index] = new SNode(blockpos);
                            start_snode.children_snode_array[index].calculateG(start_snode);
                        }
                        else
                        {
                            ++index;
                            continue;
                        }

                        IBlockState iblockstate = world.getBlockState(blockpos);
                        Material material = iblockstate.getMaterial();
                        SNode children_snode = start_snode.children_snode_array[index];
                        if ((!passBlock(material) && !this.isPassWithAct(blockpos)) || isSame(children_snode))
                        {
                            ++index;
                            continue;
                        }

                        children_snode.calculateG(start_snode);

                        if (this.isWalkAble(start_snode, x, y, z) && children_snode.g < MAX_G /*&& blockpos.getDistance(this.goal_x, this.goal_y, this.goal_z) <= 16.0D*/)
                        {
                            return children_snode;
                        }
                        else
                        {
                            ++index;
                        }
                    }
                }
            }
        }

        start_snode.children_snode_array = null;

        return null;
    }

    public SNode setChild(SNode start_snode, byte new_x, byte new_y, byte new_z)
    {
        int index = getIndex(new_x, new_y, new_z);

        World world = this.skinningentities.world;
        if (index != -1 && start_snode.children_snode_array[index] == null)
        {
            BlockPos blockpos = new BlockPos(start_snode.blockpos.getX() + new_x, start_snode.blockpos.getY() + new_y, start_snode.blockpos.getZ() + new_z);
            start_snode.children_snode_array[index] = new SNode(blockpos);
            start_snode.children_snode_array[index].calculateG(start_snode);
            IBlockState iblockstate = world.getBlockState(blockpos);
            Material material = iblockstate.getMaterial();
            SNode children_snode = start_snode.children_snode_array[index];
            if ((passBlock(material) || this.isPassWithAct(blockpos)) && !isSame(children_snode))
            {
                if (this.isWalkAble(start_snode, new_x, new_y, new_z) && children_snode.g < MAX_G/* && blockpos.getDistance(this.goal_x, this.goal_y, this.goal_z) <= 16.0D*/)
                {
                    return children_snode;
                }
            }

            return null;
        }
        else
        {
            return null;
        }
    }

    public boolean isWalkAble(SNode snode, byte x, byte y, byte z)
    {
        ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.skinningentities.bothentitiesmemory;
        if (x != 0 && z != 0)
        {
            if (y == -1)
            {
                Material temp_material0 = serverentitiesmemory.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY(), snode.blockpos.getZ()));//temp_iblockstate0.getMaterial();
                Material temp_material1 = serverentitiesmemory.getMaterial(new BlockPos(snode.blockpos.getX(), snode.blockpos.getY(), snode.blockpos.getZ() + z));//temp_iblockstate1.getMaterial();
                Material temp_material2 = serverentitiesmemory.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY(), snode.blockpos.getZ() + z));//temp_iblockstate2.getMaterial();
                Material temp_material3 = serverentitiesmemory.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() + y - 1, snode.blockpos.getZ() + z));
                Material temp_material4 = serverentitiesmemory.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() + y - 2, snode.blockpos.getZ() + z));

                if
                (
                    (isBlock(temp_material0) && isBlock(temp_material1)) ||
                    isBlock(temp_material2) ||
                    (fallBlock(temp_material3) && fallBlock(temp_material4))
                )
                {
                    return false;
                }
            }
            else if (y == 0)
            {
                Material temp_material0 = serverentitiesmemory.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY(), snode.blockpos.getZ()));//temp_iblockstate0.getMaterial();
                Material temp_material1 = serverentitiesmemory.getMaterial(new BlockPos(snode.blockpos.getX(), snode.blockpos.getY(), snode.blockpos.getZ() + z));//temp_iblockstate1.getMaterial();
                Material temp_material2 = serverentitiesmemory.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() + y - 1, snode.blockpos.getZ() + z));
                Material temp_material3 = serverentitiesmemory.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() + y - 2, snode.blockpos.getZ() + z));

                if
                (
                    (isBlock(temp_material0) && isBlock(temp_material1)) ||
                    (fallBlock(temp_material2) && fallBlock(temp_material3))
                )
                {
                    return false;
                }
            }
            else if (y == 1)
            {
                Material temp_material2 = serverentitiesmemory.getMaterial(new BlockPos(snode.blockpos.getX(), snode.blockpos.getY() + y, snode.blockpos.getZ()));//temp_iblockstate2.getMaterial();
                Material temp_material3 = serverentitiesmemory.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY(), snode.blockpos.getZ() + z));//temp_iblockstate3.getMaterial();
                Material temp_material4 = serverentitiesmemory.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() + y, snode.blockpos.getZ()));//temp_iblockstate4.getMaterial();
                Material temp_material5 = serverentitiesmemory.getMaterial(new BlockPos(snode.blockpos.getX(), snode.blockpos.getY() + y, snode.blockpos.getZ() + z));//temp_iblockstate5.getMaterial();
                Material start_down_material = serverentitiesmemory.getMaterial(snode.blockpos.down());

                if
                (
                    isBlock(temp_material2) ||
                    fallBlock(temp_material3) ||
                    (isBlock(temp_material4) && isBlock(temp_material5)) ||
                    (passBlock(start_down_material) && !this.skinningentities.isInWater())
                )
                {
                    return false;
                }
            }
        }
        else
        {
            if (y == -1)
            {
                Material temp_material0 = serverentitiesmemory.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY(), snode.blockpos.getZ() + z));//temp_iblockstate0.getMaterial();
                Material temp_material1 = serverentitiesmemory.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() + y - 1, snode.blockpos.getZ() + z));//temp_iblockstate1.getMaterial();
                Material temp_material2 = serverentitiesmemory.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() + y - 2, snode.blockpos.getZ() + z));//temp_iblockstate2.getMaterial();

                if
                (
                    isBlock(temp_material0) ||
                    (fallBlock(temp_material1) && fallBlock(temp_material2))
                )
                {
                    return false;
                }
            }
            else if (y == 0)
            {
                Material temp_material0 = serverentitiesmemory.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() - 1, snode.blockpos.getZ() + z));//temp_iblockstate0.getMaterial();
                Material temp_material1 = serverentitiesmemory.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() - 2, snode.blockpos.getZ() + z));//temp_iblockstate1.getMaterial();

                if
                (
                    fallBlock(temp_material0) && fallBlock(temp_material1)
                )
                {
                    return false;
                }
            }
            else if (y == 1)
            {
                Material temp_material0 = serverentitiesmemory.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY(), snode.blockpos.getZ() + z));//temp_iblockstate0.getMaterial();
                Material temp_material2 = serverentitiesmemory.getMaterial(new BlockPos(snode.blockpos.getX(), snode.blockpos.getY() + y, snode.blockpos.getZ()));//temp_iblockstate2.getMaterial();
                Material start_down_material = serverentitiesmemory.getMaterial(snode.blockpos.down());

                if
                (
                    fallBlock(temp_material0) ||
                    isBlock(temp_material2) ||
                    (passBlock(start_down_material) && !this.skinningentities.isInWater())
                )
                {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isPassWithAct(BlockPos blockpos)
    {
        World world = this.skinningentities.world;
        Block block = world.getBlockState(blockpos).getBlock();
        if (block instanceof BlockDoor)
        {
            ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.skinningentities.bothentitiesmemory;
            serverentitiesmemory.entitiesaimemory.skinningentitiespassto.blockpos_arraylist.add(blockpos);
            serverentitiesmemory.entitiesaimemory.skinningentitiespassto.block_arraylist.add(block);
            return true;
        }

        return false;
    }

    public static boolean passBlock(Material material)
    {
//        return material == Material.AIR || material == Material.WATER;
        return !material.blocksMovement();
    }

    public static boolean isBlock(Material material)
    {
//        return material != Material.AIR && material != Material.WATER;// && material != Material.LAVA
        return material.blocksMovement();
    }

    public static boolean fallBlock(Material material)
    {
        return material == Material.AIR;
//        return !material.blocksMovement();
    }

    public static boolean isSame(SNode snode)
    {
        SNode current_snode = snode.parent_snode;
        while (current_snode != null)
        {
            if (snode.blockpos.getX() == current_snode.blockpos.getX() && snode.blockpos.getY() == current_snode.blockpos.getY() && snode.blockpos.getZ() == current_snode.blockpos.getZ())
            {
                return true;
            }

            current_snode = current_snode.parent_snode;
        }

        return false;
    }
}
