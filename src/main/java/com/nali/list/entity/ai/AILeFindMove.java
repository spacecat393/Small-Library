package com.nali.list.entity.ai;

import com.nali.da.IBothDaNe;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.ai.AI;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.small.entity.memo.server.ai.path.PathMath;
import com.nali.small.entity.memo.server.ai.path.SNode;
import com.nali.sound.ISoundDaLe;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class AILeFindMove<SD extends ISoundDaLe, BD extends IBothDaNe, E extends EntityLivingBase, I extends IMixLe<SD, BD, E>, S extends ServerLe<SD, BD, E, I, A>, A extends MixAIE<SD, BD, E, I, S>> extends AI<SD, BD, E, I, S, A>
{
    public static byte ID;

    public static int MAX_G = 64;

    public AIESit<SD, BD, E, I, S, A> aiesit;
//    public AILeSetLocation<E, I, S, A> ailesetlocation;
    public AILeMove<SD, BD, E, I, S, A> ailemove;
    public AILeWalkTo<SD, BD, E, I, S, A> ailewalkto;
    public AILeMineTo<SD, BD, E, I, S, A> ailemineto;

//    public int goal_x, goal_y, goal_z;
    public double goal_x, goal_y, goal_z;
//    public int temp_goal_x, temp_goal_y, temp_goal_z;
    public double temp_goal_x, temp_goal_y, temp_goal_z;
//    public double far;

    public List<BlockPos> path_blockpos_list = new ArrayList();
    public SNode to_goal_snode;
    public boolean is_goal;
    public int path_index;
    public int path_tick;
    public boolean try_move;

//    public double old_x, old_y, old_z;

    public AILeFindMove(S s)
    {
        super(s);//skinningentitiesmove walk_to
    }

    @Override
    public void init()
    {
        this.aiesit = (AIESit<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AIESit.ID);
//        this.ailesetlocation = (AILeSetLocation<E, I, S, A>)this.s.a.aie_map.get(AILeSetLocation.ID);
        this.ailemove = (AILeMove<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AILeMove.ID);
        this.ailewalkto = (AILeWalkTo<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AILeWalkTo.ID);
        this.ailemineto = (AILeMineTo<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AILeMineTo.ID);
    }

    @Override
    public void call()
    {

    }

    @Override
    public void onUpdate()
    {
        if (this.s.isMove())
        {
//            if ((serverentitiesmemory.main_work_byte_array[serverentitiesmemory.workbytes.SIT() / 8] >> serverentitiesmemory.workbytes.SIT() % 8 & 1) == 0)
//            if ((this.s.work_byte_array[serverentitiesmemory.workbytes.SIT() / 8] >> serverentitiesmemory.workbytes.SIT() % 8 & 1) == 0)
            if ((this.aiesit.state & 1) == 1)
            {
                BlockPos blockpos = this.s.i.getE().getPosition();

        //        World world = this.skinningentities.world;
        ////        if (this.path_blockpos_array != null)
        ////        {
        //        for (BlockPos bp : this.path_blockpos_list)
        //        {
        //            if (bp != null)
        //            {
        //                ((WorldServer) world).spawnParticle(EnumParticleTypes.CRIT, bp.getX() + 0.5, bp.getY() + 0.5, bp.getZ() + 0.5, 1, 0.0D, 0.0D, 0.0D, 0.0D);
        //            }
        //        }
        //        Nali.LOGGER.info("TICK " + this.path_tick);
        ////        }

                if (this.try_move)
                {
                    if (this.temp_goal_x == this.goal_x && this.temp_goal_y == this.goal_y && this.temp_goal_z == this.goal_z && !this.path_blockpos_list.isEmpty())
                    {
                        if (!this.is_goal)
                        {
                            SNode temp_snode = this.find(this.to_goal_snode);
                            if (temp_snode != null)
                            {
                                int old_max_index = this.path_blockpos_list.size();
                                this.to_goal_snode = temp_snode;

                                this.path_blockpos_list.clear();
                                this.path_blockpos_list.add(temp_snode.blockpos);

                                while (temp_snode.parent_snode != null)
                                {
                                    temp_snode = temp_snode.parent_snode;
                                    this.path_blockpos_list.add(temp_snode.blockpos);
                                }

                                this.path_index = this.path_blockpos_list.size() - (old_max_index - this.path_index);

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
                            this.path_blockpos_list.clear();
                            return;
                        }

        //                Nali.LOGGER.info("WANT!");

                        BlockPos current_blockpos = this.path_blockpos_list.get(this.path_index);

                        //-if path hitbox is less than entity and on same blockpos will have some problem in future
                        if (this.is_goal && this.path_index == 0)
                        {
                            this.ailemove.setWanted(this.goal_x, this.goal_y, this.goal_z);
                            this.ailemove.should_on_pos = true;
                        }
                        else
                        {
                            this.ailemove.setWanted(current_blockpos.getX() + 0.5D, current_blockpos.getY(), current_blockpos.getZ() + 0.5D);
                        }

        //                double far = this.skinningentities.getDistanceSq(this.goal_x + 0.5D, this.goal_y, this.goal_z + 0.5D);
        //                if (/*(!this.is_goal && far >= this.far) || *//*(*/this.is_goal && this.skinningentities.posX == this.old_x && this.skinningentities.posY == this.old_y && this.skinningentities.posZ == this.old_z/*)*/)
        //                {
        //                    if (++this.path_tick == 200)
        //                    {
        //                        this.path_blockpos_list.clear();
        //                        return;
        //                    }
        //                }

        //                this.old_x = this.skinningentities.posX;
        //                this.old_y = this.skinningentities.posY;
        //                this.old_z = this.skinningentities.posZ;

        //                int new_index = this.path_index + 1;
        //                if (new_index != this.path_blockpos_list.size())
        //                {
        //                    this.far = this.path_blockpos_list.get(new_index).distanceSq(this.goal_x, this.goal_y, this.goal_z);
        //                }

                        //-loop check all and select next pos
                        //check speed&time and loop after offset
                        int path_index = this.path_index;
                        if (this.ailemove.isDone())
                        {
        //                    --this.path_index;
                            if (--this.path_index == -1)
                            {
                                this.try_move = false;
                                this.ailemove.move = false;
                                this.path_blockpos_list.clear();
                            }
                        }
        //                else
        //                {
        //                    for (int b = 0; b < this.path_blockpos_list.size(); ++b)
        //                    {
        //                        BlockPos on_blockpos = this.path_blockpos_list.get(b);
        //                        if ((int)this.skinningentities.posX == on_blockpos.getX() && (int)this.skinningentities.posY == on_blockpos.getY() && (int)this.skinningentities.posZ == on_blockpos.getZ())
        //                        {
        //                            this.path_index = b - 1;
        //                            break;
        //                        }
        //                    }
        //                }

        //                if (this.path_index == -1)
        //                {
        //                    this.try_move = false;
        //                    this.path_blockpos_list.clear();
        //                }
        //                }

                        if (path_index == this.path_index)
                        {
                            if (++this.path_tick == 200)
                            {
                                this.path_blockpos_list.clear();
                                return;
                            }
                        }
                        else
                        {
                            this.path_tick = 0;
                        }
                    }
                    else
                    {
                        this.ailemineto.clear();
        //                this.s.entitiesaimemory.this.ailemineto.blockpos = null;
                        this.ailewalkto.blockpos_list.clear();
        //                this.s.entitiesaimemory.skinningentitieswalkto.block_list.clear();
        //                this.old_x = this.skinningentities.posX;
        //                this.old_y = this.skinningentities.posY;
        //                this.old_z = this.skinningentities.posZ;
                        this.to_goal_snode = null;
                        this.path_blockpos_list.clear();
                        this.is_goal = false;
                        this.path_tick = 0;

                        SNode snode = new SNode(blockpos);
                        SNode temp_snode = this.find(snode);

                        if (temp_snode != null)
                        {
        //                    this.far = blockpos.distanceSq(this.goal_x, this.goal_y, this.goal_z);
                            if (!this.is_goal)
                            {
                                this.to_goal_snode = temp_snode;
                            }

                            this.path_blockpos_list.add(temp_snode.blockpos);

                            while (temp_snode.parent_snode != null)
                            {
                                temp_snode = temp_snode.parent_snode;
                                this.path_blockpos_list.add(temp_snode.blockpos);
                            }

                            this.path_index = this.path_blockpos_list.size() - 1;

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
        }
        else if (!this.path_blockpos_list.isEmpty())
        {
            this.path_blockpos_list.clear();
        }
    }

    @Override
    public void writeNBT(NBTTagCompound nbttagcompound)
    {

    }

    @Override
    public void readNBT(NBTTagCompound nbttagcompound)
    {

    }

    public SNode find(SNode start_snode)
    {
        if (start_snode.blockpos.getX() == (int)this.goal_x && start_snode.blockpos.getY() == (int)this.goal_y && start_snode.blockpos.getZ() == (int)this.goal_z)
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
//        this.setGoal(MathHelper.floor(x), MathHelper.floor(y), MathHelper.floor(z));
//    }
//
//    public void setGoal(int x, int y, int z)
//    {
        this.goal_x = x;
        this.goal_y = y;
        this.goal_z = z;
        this.try_move = true;
        this.s.a.state &= 255-(1+2);
    }

////    public void setBreakGoal(double px, double py, double pz)
//    public void setBreakGoal(double x, double y, double z)
//    {
////        this.setBreakGoal(MathHelper.floor(px), MathHelper.floor(py), MathHelper.floor(pz));
////    }
////
////    public void setBreakGoal(int x, int y, int z)
////    {
//        if (this.ailemineto.goal_x != x || this.ailemineto.goal_y != y || this.ailemineto.goal_z != z)
//        {
//            this.ailemineto.clear();
//        }
//        this.setGoal(x, y, z);
//        this.ailemineto.goal_x = x;
//        this.ailemineto.goal_y = y;
//        this.ailemineto.goal_z = z;
//    }

    public void endGoal()
    {
        this.try_move = false;
        this.path_blockpos_list.clear();
        this.ailemove.move = false;

        this.ailemineto.clear();
    }

    public boolean endGoalT()
    {
        this.endGoal();
        return true;
    }

    public SNode nextSNode(SNode start_snode)
    {
        if (start_snode.children_snode_array == null)
        {
            return null;
        }

        SNode pre_snode;
        double to_x = ((int)this.goal_x + 0.5D) - (start_snode.blockpos.getX() + 0.5D);
        double to_y = ((int)this.goal_y + 0.5D) - (start_snode.blockpos.getY() + 0.5D);
        double to_z = ((int)this.goal_z + 0.5D) - (start_snode.blockpos.getZ() + 0.5D);
        double length = to_x * to_x + to_y * to_y + to_z * to_z;
        byte new_x = 0;
        byte new_y = 0;
        byte new_z = 0;
        if (length != 0)
        {
            length = Math.sqrt(length);
            new_x = PathMath.signum(to_x / length);
            new_y = PathMath.signum(to_y / length);
            new_z = PathMath.signum(to_z / length);
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

        for (byte x : PathMath.PATH_BYTE_ARRAY)
        {
            for (byte y : PathMath.PATH_BYTE_ARRAY)
            {
                for (byte z : PathMath.PATH_BYTE_ARRAY)
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

                        IBlockState iblockstate = this.s.i.getE().world.getBlockState(blockpos);
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
        int index = PathMath.getIndex(new_x, new_y, new_z);

        if (index != -1 && start_snode.children_snode_array[index] == null)
        {
            BlockPos blockpos = new BlockPos(start_snode.blockpos.getX() + new_x, start_snode.blockpos.getY() + new_y, start_snode.blockpos.getZ() + new_z);
            start_snode.children_snode_array[index] = new SNode(blockpos);
            start_snode.children_snode_array[index].calculateG(start_snode);
            IBlockState iblockstate = this.s.i.getE().world.getBlockState(blockpos);
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
        if (x != 0 && z != 0)
        {
            if (y == -1)
            {
                Material temp_material0 = this.s.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY(), snode.blockpos.getZ()));//temp_iblockstate0.getMaterial();
                Material temp_material1 = this.s.getMaterial(new BlockPos(snode.blockpos.getX(), snode.blockpos.getY(), snode.blockpos.getZ() + z));//temp_iblockstate1.getMaterial();
                Material temp_material2 = this.s.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY(), snode.blockpos.getZ() + z));//temp_iblockstate2.getMaterial();
                Material temp_material3 = this.s.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() + y - 1, snode.blockpos.getZ() + z));
                Material temp_material4 = this.s.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() + y - 2, snode.blockpos.getZ() + z));

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
                Material temp_material0 = this.s.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY(), snode.blockpos.getZ()));//temp_iblockstate0.getMaterial();
                Material temp_material1 = this.s.getMaterial(new BlockPos(snode.blockpos.getX(), snode.blockpos.getY(), snode.blockpos.getZ() + z));//temp_iblockstate1.getMaterial();
                Material temp_material2 = this.s.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() + y - 1, snode.blockpos.getZ() + z));
                Material temp_material3 = this.s.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() + y - 2, snode.blockpos.getZ() + z));

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
                Material temp_material2 = this.s.getMaterial(new BlockPos(snode.blockpos.getX(), snode.blockpos.getY() + y, snode.blockpos.getZ()));//temp_iblockstate2.getMaterial();
                Material temp_material3 = this.s.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY(), snode.blockpos.getZ() + z));//temp_iblockstate3.getMaterial();
                Material temp_material4 = this.s.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() + y, snode.blockpos.getZ()));//temp_iblockstate4.getMaterial();
                Material temp_material5 = this.s.getMaterial(new BlockPos(snode.blockpos.getX(), snode.blockpos.getY() + y, snode.blockpos.getZ() + z));//temp_iblockstate5.getMaterial();
                Material start_down_material = this.s.getMaterial(snode.blockpos.down());

                if
                (
                    isBlock(temp_material2) ||
                    fallBlock(temp_material3) ||
                    (isBlock(temp_material4) && isBlock(temp_material5)) ||
                    (passBlock(start_down_material) && !this.s.getI().getE().isInWater())
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
                Material temp_material0 = this.s.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY(), snode.blockpos.getZ() + z));//temp_iblockstate0.getMaterial();
                Material temp_material1 = this.s.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() + y - 1, snode.blockpos.getZ() + z));//temp_iblockstate1.getMaterial();
                Material temp_material2 = this.s.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() + y - 2, snode.blockpos.getZ() + z));//temp_iblockstate2.getMaterial();

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
                Material temp_material0 = this.s.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() - 1, snode.blockpos.getZ() + z));//temp_iblockstate0.getMaterial();
                Material temp_material1 = this.s.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() - 2, snode.blockpos.getZ() + z));//temp_iblockstate1.getMaterial();

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
                Material temp_material0 = this.s.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY(), snode.blockpos.getZ() + z));//temp_iblockstate0.getMaterial();
                Material temp_material2 = this.s.getMaterial(new BlockPos(snode.blockpos.getX(), snode.blockpos.getY() + y, snode.blockpos.getZ()));//temp_iblockstate2.getMaterial();
                Material start_down_material = this.s.getMaterial(snode.blockpos.down());

                if
                (
                    fallBlock(temp_material0) ||
                    isBlock(temp_material2) ||
                    (passBlock(start_down_material) && !this.s.getI().getE().isInWater())
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
//        if ((this.s.main_work_byte_array[this.s.workbytes.WALK_TO() / 8] >> this.s.workbytes.WALK_TO() % 8 & 1) == 0)
        if ((this.ailewalkto.state & 1) == 1)
        {
            return false;
        }

        IBlockState iblockstate = this.s.i.getE().world.getBlockState(blockpos);
        Block block = iblockstate.getBlock();
        if (iblockstate.getMaterial() != Material.IRON && block instanceof BlockDoor || block instanceof BlockFenceGate || block instanceof BlockTrapDoor)
        {
            this.ailewalkto.blockpos_list.add(blockpos);
//            this.s.entitiesaimemory.skinningentitieswalkto.block_list.add(block);
            return true;
        }

//        if ((this.s.main_work_byte_array[this.s.workbytes.MINE() / 8] >> this.s.workbytes.MINE() % 8 & 1) == 1)
        if ((this.ailemineto.state & 1) == 1)
        {
            if (this.ailemineto.blockpos == null)
            {
                this.ailemineto.blockpos = blockpos;
            }

//            this.endGoal();
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
