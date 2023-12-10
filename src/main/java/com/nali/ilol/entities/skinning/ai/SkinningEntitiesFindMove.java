package com.nali.ilol.entities.skinning.ai;

import com.nali.ilol.entities.skinning.SkinningEntities;
import com.nali.ilol.entities.skinning.ai.path.SNode;
import com.nali.math.MixMath;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.util.ArrayList;

public class SkinningEntitiesFindMove
{
    public static byte[] PATH_BYTE_ARRAY = { -1, 0, 1 };
    public static int MAX_G = 64; //block
//    public int max_run = 50;

    public SkinningEntities skinningentities;
    public int goal_x;
    public int goal_y;
    public int goal_z;

    public int temp_goal_x;
    public int temp_goal_y;
    public int temp_goal_z;
    public double far;

//    public static byte LIMIT = 63;
//    public ArrayList<SNode> snode_arraylist = new ArrayList<SNode>();
//    public Map<Long, Boolean> index_snode_map = new WeakHashMap<>();
//    public ArrayList<BlockPos> index_blockpos_arraylist  = new ArrayList<BlockPos>();
    public ArrayList<BlockPos> path_blockpos_arraylist = new ArrayList<BlockPos>();
//    public ArrayList<Integer> index_size_arraylist = new ArrayList<Integer>();
    public SNode to_goal_snode;
    public boolean is_goal;
//    public byte stuck = 0;
    public int path_index;
    public int path_tick;
    public boolean try_move = false;
//    public int first_y;

    public double old_x;
    public double old_y;
    public double old_z;

    public SkinningEntitiesFindMove(SkinningEntities skinningentities)
    {
        this.skinningentities = skinningentities;
    }

    public void onUpdate()
    {
        // {
        //     Vec3 motion = new Vec3(1, 1, 1);
        //     this.setDeltaMovement(motion);
        // }

        BlockPos blockpos = this.skinningentities.getPosition();
//
//        int mob_x = blockpos.getX();
//        int mob_y = blockpos.getY();
//        int mob_z = blockpos.getZ();
//
//        // this.goal_x = Mth.floor(x);
//        // this.goal_y = Mth.floor(y);
//        // this.goal_z = Mth.floor(z);
//
//        int move_x = this.goal_x;
//        int move_y = this.goal_y;
//        int move_z = this.goal_z;

        // if (this.temp_goal_x != this.goal_x || this.temp_goal_y != this.goal_y || this.temp_goal_z != this.goal_z)
        // {
        //     Main.LOGGER.info("Goal :" + (this.temp_goal_x == this.goal_x && this.temp_goal_y == this.goal_y && this.temp_goal_z == this.goal_z));
        // }

        World world = this.skinningentities.getEntityWorld();
//        if (this.path_blockpos_array != null)
//        {
        for (BlockPos bp : this.path_blockpos_arraylist)
        {
            if (bp != null)
            {
                ((WorldServer) world).spawnParticle(EnumParticleTypes.CRIT, bp.getX() + 0.5, bp.getY() + 0.5, bp.getZ() + 0.5, 1, 0.0D, 0.0D, 0.0D, 0.0D);
            }
        }
//        }

        if (this.try_move)
        {
            if (this.temp_goal_x == this.goal_x && this.temp_goal_y == this.goal_y && this.temp_goal_z == this.goal_z && !this.path_blockpos_arraylist.isEmpty())
            {
                if (!this.is_goal)
                {
//                    this.to_goal_snode.parent_snode = null;
//                    this.to_goal_snode.children_snode_array = new SNode[26];
//                    this.to_goal_snode.parent_snode = null;
//                    this.first_y = blockpos.getY();
                    SNode temp_snode = this.find(this.to_goal_snode);
                    if (temp_snode != null)
                    {
                        int old_max_index = this.path_blockpos_arraylist.size();
                        this.to_goal_snode = temp_snode;

                        this.path_blockpos_arraylist.clear();
//                    ArrayList<BlockPos> new_path_blockpos_arraylist = new ArrayList<BlockPos>();
                        this.path_blockpos_arraylist.add(temp_snode.blockpos);

//                    if (temp_snode.children_snode_array != null)
//                    {
//                        new_path_blockpos_arraylist.add(temp_snode.blockpos);
//                    }
//                    new_path_blockpos_arraylist.add(temp_snode.blockpos);
//                    this.path_blockpos_array[this.path_index++] = temp_snode.blockpos;

                        while (temp_snode.parent_snode != null)
                        {
//                        this.path_blockpos_array[this.path_index] = temp_snode.blockpos;

//                        if (this.path_index < LIMIT)
//                        {
//                            ++this.path_index;
//                        }
//                        else
//                        {
//                            break;
//                        }

                            temp_snode = temp_snode.parent_snode;
                            this.path_blockpos_arraylist.add(temp_snode.blockpos);
//                        new_path_blockpos_arraylist.add(temp_snode.blockpos);
                        }

//                    if (this.path_blockpos_arraylist.size() < old_max_index)
//                    {
//                        this.path_blockpos_arraylist.clear();
//                        return;
//                    }
//                    new_path_blockpos_arraylist.addAll(this.path_blockpos_arraylist);
//                    this.path_blockpos_arraylist = new_path_blockpos_arraylist;

//                    this.path_index = this.path_blockpos_arraylist.size() - (old_max_index - this.path_index);
//                    int new_size = this.path_blockpos_arraylist.size() - old_max_index;
//                    this.index_size_arraylist.add(old_max_index);
                        this.path_index = this.path_blockpos_arraylist.size() - (old_max_index - this.path_index);

//                    this.path_index = temp_path_blockpos_arraylist.size() - 1;
//                    temp_path_blockpos_arraylist.addAll(this.path_blockpos_arraylist);
//                    this.path_index += this.path_blockpos_arraylist.size();
//                    this.path_blockpos_arraylist = temp_path_blockpos_arraylist;

                        if (this.path_index == -1)
                        {
                            this.path_index = 0;
                        }

//                    if (++this.path_tick >= 100)
//                    {
//                        this.path_blockpos_arraylist.clear();
//                        return;
//                    }

//                    if (--this.path_index < 0)
//                    {
//                        this.path_index = 0;
//                    }
                    }
                }

//                {
//                    BlockPos new_blockpos = this.path_blockpos_array[this.path_index];
//                    if (new_blockpos != null && this.skinningentities.getDistanceSq(new_blockpos.getX() + 0.5D, new_blockpos.getY(), new_blockpos.getZ() + 0.5D) <= 0.5D)
//                    {
//                        --this.path_index;
//                        if (this.path_index == 0)
//                        {
//                            this.try_move = false;
//                        }
//                        // this.path_tick = 0;
//                        // this.path_blockpos_array = null;
//                        // return;
//                    }
//                }

//                if (this.is_goal)
                {
//                    if (this.path_blockpos_array != null)
//                    {
//                        if (++this.path_tick >= 60)
//                        {
//                            this.path_blockpos_arraylist.clear();
////                            this.path_blockpos_array = null;
//                            return;
////                            // // this.path_tick = 0;
////                            // this.goal_x = this.temp_goal_x;
////                            // this.goal_y = this.temp_goal_y;
////                            // this.goal_z = this.temp_goal_z;
//                        }
//
//                        if (this.path_index == -1)
//                        {
//                            this.path_index = 0;
//                        }
//
//                        BlockPos new_blockpos = this.path_blockpos_array[this.path_index];
//
//                        if (new_blockpos != null)
//                        {
//                            if (this.path_index == 0)
//                            {
//                                move_x = this.goal_x;
//                                move_y = this.goal_y;
//                                move_z = this.goal_z;
//                            }
//                            else
//                            {
//                                move_x = new_blockpos.getX();
//                                move_y = new_blockpos.getY();
//                                move_z = new_blockpos.getZ();
//                            }
//
//                            // Vec3 motion = new Vec3(move_x - mob_x, move_y - mob_y, move_z - mob_z);
//                            // this.setDeltaMovement(motion);
//                            // if (++this.path_tick >= 60)
//                            // {
                    if (this.path_index < 0)
                    {
//                        this.path_index = this.path_blockpos_arraylist.size() - 1;
                        this.path_blockpos_arraylist.clear();
                        return;
                    }

                    BlockPos current_blockpos = this.path_blockpos_arraylist.get(this.path_index);
//                    BlockPos current_blockpos = this.path_blockpos_arraylist.get(this.path_blockpos_arraylist.size() - 1);
//                    int size = this.path_blockpos_arraylist.size();

//                    boolean no_path = true;
//                    for (int i = 0; i < size; ++i)
//                    {
//                        if (this.skinningentities.getDistanceSq(this.path_blockpos_arraylist.get(i)) <= 4.0D)
//                        {
//                            int path_index = i - 1;
//
//                            if (path_index == -1)
//                            {
//                                if (this.is_goal)
//                                {
//                                    this.try_move = false;
//                                }
//
//                                this.path_blockpos_arraylist.clear();
//                                return;
//                            }
//                            else
//                            {
//                                current_blockpos = this.path_blockpos_arraylist.get(path_index);
//                            }
//                        }
////                        else
////                        {
////                            no_path = false;
////                        }
//                    }

//                    if (no_path)
//                    {
//                        ++this.stuck;
//
//                        if (this.stuck == 60)
//                        {
////                            if (this.is_goal)
////                            {
////                                this.try_move = false;
////                            }
//
//                            this.path_blockpos_arraylist.clear();
//                            return;
//                        }
//                    }

//                    if (no_path)
//                    {
//                        if (--this.path_index == -1)
//                        {
//                            this.try_move = false;
//                            this.path_blockpos_arraylist.clear();
//                            return;
//                        }
//                    }

//                    this.skinningentities.skinningentitiesmove.setWanted(move_x + 0.5D, move_y, move_z + 0.5D);
                    this.skinningentities.skinningentitiesmove.setWanted(current_blockpos.getX() + 0.5D, current_blockpos.getY(), current_blockpos.getZ() + 0.5D);

                    double far = this.skinningentities.getDistanceSq(this.goal_x + 0.5D, this.goal_y, this.goal_z + 0.5D);
                    if ((!this.is_goal && far >= this.far) || (this.is_goal && this.skinningentities.posX == this.old_x && this.skinningentities.posY == this.old_y && this.skinningentities.posZ == this.old_z))
                    {
                        if (++this.path_tick == 200)
                        {
//                            this.try_move = false;
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

                    if (this.skinningentities.skinningentitiesmove.isDone())
                    {
//                        --this.path_index;
//                        if (this.path_index == 1)
//                        {
//                            if (!this.is_goal)
//                            {
//                                this.path_blockpos_arraylist.clear();
//                            }
////                                this.path_blockpos_arraylist.clear();
//
//                        }

                        if (--this.path_index == -1)
                        {
//                            if (this.is_goal)
//                            {
                            this.try_move = false;
                            this.path_blockpos_arraylist.clear();
//                            }
                        }
                    }

////                            SkinningEntitiesMoveHelper skinningentitiesmovehelper = (SkinningEntitiesMoveHelper)this.skinningentities.skinningentitiesmovehelper;
////                            skinningentitiesmovehelper.setWantedX(move_x + 0.5D);
////                            skinningentitiesmovehelper.setWantedY(move_y);
////                            skinningentitiesmovehelper.setWantedZ(move_z + 0.5D);
////                            skinningentitiesmovehelper.setMoveOperation();
//                            // this.moveTo(move_x + 0.5D, move_y, move_z + 0.5D);
//                            // this.path_tick = 0;
//                            // }
//                        }
//
//                        if (this.path_index == 0 || new_blockpos == null)
//                        {
//                            this.path_blockpos_array = null;
//                        }
//                    }
                }
            }
            else
            {
                this.old_x = this.skinningentities.posX;
                this.old_y = this.skinningentities.posY;
                this.old_z = this.skinningentities.posZ;
//                this.far = 0.0D;
                this.to_goal_snode = null;
//                this.stuck = 0;
//                this.index_size_arraylist.clear();
//                this.index_blockpos_arraylist.clear();
                this.path_blockpos_arraylist.clear();
                this.is_goal = false;
//                this.to_goal_snode = null;
                this.path_tick = 0;
                // this.try_move = 0.5F;
//                this.snode_arraylist.clear();
//                this.index_snode_map.clear();

                SNode snode = new SNode(blockpos);
//                snode.calculateH(this.goal_x, this.goal_y, this.goal_z);
//                this.snode_arraylist.add(snode);
//                this.index_snode_map.put(blockpos.toLong(), true);

//                this.path_blockpos_array = new BlockPos[LIMIT + 1];
//                this.path_index = 0;

//                int snode_index = this.find(mob_x, mob_y, mob_z, this.goal_x, this.goal_y, this.goal_z);
//                SNode temp_snode = this.snode_arraylist.get(snode_index);
//                this.first_y = blockpos.getY();
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
                        // if (temp_snode.parent_snode.parent_snode == null)
                        // {
                        // if (temp_snode.parent_snode.parent_snode.parent_snode == null)
                        // {
                        // break;
                        // }
                        // }

//                    this.path_blockpos_array[this.path_index] = temp_snode.blockpos;

//                    if (this.path_index < LIMIT)
//                    {
//                        ++this.path_index;
//                    }
//                    else
//                    {
//                        break;
//                    }

                        // if (temp_snode.parent_snode.sub_blockpos_array != null)
                        // {
                        //     for (int i = 0; i < temp_snode.parent_snode.sub_blockpos_array.length; ++i)
                        //     {
                        //         this.path_blockpos_array[this.path_index] = temp_snode.parent_snode.sub_blockpos_array[i];

                        //         if (this.path_index < LIMIT)
                        //         {
                        //             ++this.path_index;
                        //         }
                        //         else
                        //         {
                        //             break;
                        //         }
                        //     }
                        // }

                        // if (this.path_index >= LIMIT)
                        // {
                        //     break;
                        // }

                        temp_snode = temp_snode.parent_snode;
                        this.path_blockpos_arraylist.add(temp_snode.blockpos);
                    }

                    this.path_index = this.path_blockpos_arraylist.size() - 1;

                    if (this.path_index == -1)
                    {
                        this.path_index = 0;
                    }

//                if (--this.path_index < 0)
//                {
//                    this.path_index = 0;
//                }

                    // else
                    // {
                    //     BlockPos new_blockpos = this.path_blockpos_array[this.path_index];

                    //     if (new_blockpos != null)
                    //     {
                    //         move_x = new_blockpos.getX();
                    //         move_y = new_blockpos.getY();
                    //         move_z = new_blockpos.getZ();
                    //     }
                    // }
                }
            }

            this.temp_goal_x = this.goal_x;
            this.temp_goal_y = this.goal_y;
            this.temp_goal_z = this.goal_z;
        }
    }

    public SNode find(SNode start_snode)
    {
//        double far = this.skinningentities.getDistanceSq(goal_x + 0.5D, goal_y, goal_z + 0.5D);

//        World world = this.skinningentities.getEntityWorld();

        // int max_y = this.snode_arraylist.get(0).blockpos.getY();

//        boolean have_goal = false;

//        for (int index = 0; index < this.snode_arraylist.size(); ++index)
//        float max_f = Float.MAX_VALUE;
//        float max_h = Float.MAX_VALUE;
//        float max_g = Float.MAX_VALUE;
//        float max_f = start_snode.f;
//        if (max_f == 0)
//        {
//            max_f = Float.MAX_VALUE;
//        }
//        float max_h = start_snode.h;
//        float max_g = start_snode.g;
//        SNode closest_snode = start_snode;
//        SNode goal_snode = null;
//        SNode closest_goal_snode = null;
//        while (start_snode != null)
//        BlockPos main_blockpos = this.skinningentities.getPosition().crossProduct(new Vec3i(this.goal_x, this.goal_y, this.goal_z));
//        int length = main_blockpos.getX() * main_blockpos.getX() + main_blockpos.getY() * main_blockpos.getY() + main_blockpos.getZ() * main_blockpos.getZ();
//        for (int run = 0; run < length / 200; ++run) // 10 sec
//        for (int run = 0; run < 100; ++run)
//        {
//            int index = 0;
//            SNode snode = this.snode_arraylist.get(index);

            // if (!snode.closed)
            // {

//            if (closest_snode.blockpos.getX() == this.goal_x && closest_snode.blockpos.getY() == this.goal_y && closest_snode.blockpos.getZ() == this.goal_z)
            if (start_snode.blockpos.getX() == this.goal_x && start_snode.blockpos.getY() == this.goal_y && start_snode.blockpos.getZ() == this.goal_z)
            {
                this.is_goal = true;
                return start_snode;
//                closest_snode = start_snode;
//                break;
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

//            if (next_snode == null)
//            {
////                start_snode.close = true;
//
////                float g = start_snode.g;
//                float f = start_snode.f;
//                float h = start_snode.h;
//
////                if (f <= max_f && h <= max_h && g <= max_g)
////                BlockPos blockpos = start_snode.blockpos;
////                boolean is_goal = blockpos.getX() == this.goal_x && blockpos.getY() == this.goal_y && blockpos.getZ() == this.goal_z;
//                if (f <= max_f && h <= max_h/* && g <= max_g*/)
//                {
//                    closest_snode = start_snode;
//                    max_f = f;
//                    max_h = h;
////                    max_g = g;
//
////                    if (is_goal)
////                    {
////                        closest_goal_snode = start_snode;
////                    }
//                }
//
////                if (is_goal)
////                {
////                    goal_snode = start_snode;
////                }
//
////                start_snode = start_snode.parent_snode;
////                if (start_snode.parent_snode != null)
////                {
//                start_snode = start_snode.parent_snode;
////                    this.index_blockpos_arraylist.add(start_snode.blockpos);
////                    closest_snode = start_snode;
////                }
//            }
//            else
//            {
//                start_snode = next_snode;
////                this.index_blockpos_arraylist.add(start_snode.blockpos);
////                closest_snode = next_snode;
//            }

            //     snode.closed = true;
            // }
//        }

//        int max_f = Integer.MAX_VALUE;
//        int max_h = Integer.MAX_VALUE;
//        SNode closest_snode = this.snode_arraylist.get(0);

//        for (int index = 0; index < this.snode_arraylist.size(); ++index)
//        {
//            SNode snode = this.snode_arraylist.get(index);
//
//            // if (goal_x == snode.blockpos.getX() && goal_y == snode.blockpos.getY() && goal_z == snode.blockpos.getZ())
//            // {
//            //     return index;
//            // }
//
//            int f = snode.f;
//            int h = snode.h;
//
////            boolean should_out = true;
////
////            if (have_goal)
////            {
////                should_out = goal_x == snode.blockpos.getX() && goal_y == snode.blockpos.getY() && goal_z == snode.blockpos.getZ();
////            }
//
//            if (snode.walk_able/* && have_goal*/ && f < max_f && h <= max_h)
//            {
//                boolean out = false;
//
//                SNode temp_snode = snode;
//                while (temp_snode.parent_snode != null)
//                {
//                    if (!temp_snode.walk_able)
//                    {
//                        out = true;
//                        break;
//                    }
//
//                    closest_snode = temp_snode;
//                    temp_snode = temp_snode.parent_snode;
//                }
//
//                if (!out)
//                {
//                    closest_snode = snode;
//                    max_f = f;
//                    max_h = h;
//                }
//            }
//        }

//        if (closest_goal_snode != null)
//        {
//            return closest_goal_snode;
//        }
//
//        if (goal_snode != null)
//        {
//            return goal_snode;
//        }

//        return closest_snode;
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

    public SNode nextSNode(SNode start_snode)
    {
//        if (start_snode.children_snode_array == null)
//        {
//            return null;
//        }

        World world = this.skinningentities.getEntityWorld();
//        byte index = 0;

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
            new_x = MixMath.signum(to_x / length);
            new_y = MixMath.signum(to_y / length);
            new_z = MixMath.signum(to_z / length);
        }
//        byte index = getIndex(new_x, (byte)0, (byte)0);
//        byte index = getIndex(new_x, new_y, new_z);
//        if (new_x != 0 && new_y != 0 && new_z != 0)
//        {
//            index = getIndex(new_x, (byte)0, new_z);
//        }
//        else
//        {
//            index = getIndex(new_x, new_y, new_z);
//        }
        pre_snode = this.setChild(start_snode, new_x, new_y, new_z);
        if (pre_snode != null)
        {
            return pre_snode;
        }

        if (new_y != 0/* || this.goal_y != this.first_y*/)
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
//                            BlockPos blockpos = new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() + y, snode.blockpos.getZ() + z);
                        BlockPos blockpos;
                        if (start_snode.children_snode_array[index] == null)
                        {
                            blockpos = new BlockPos(start_snode.blockpos.getX() + x, start_snode.blockpos.getY() + y, start_snode.blockpos.getZ() + z);
                            start_snode.children_snode_array[index] = new SNode(blockpos);
                            start_snode.children_snode_array[index].calculateG(start_snode);
//                            SNode temp_snode = start_snode.children_snode_array[index];
//                            boolean out = false;
//                            while (temp_snode != null)
//                            {
//                                for (SNode child_snode : temp_snode.children_snode_array)
//                                {
//                                    if (child_snode == null)
//                                    {
//                                        break;
//                                    }
//                                    else if (child_snode.blockpos.equals(blockpos))
//                                    {
//                                        out = true;
//                                        break;
//                                    }
//                                }
//
//                                if (out)
//                                {
//                                    break;
//                                }
//
//                                temp_snode = temp_snode.parent_snode;
//
//                                if (temp_snode.blockpos.equals(blockpos))
//                                {
//                                    out = true;
//                                    break;
//                                }
//                            }
//
//                            if (out)
//                            {
//                                ++index;
//                                continue;
//                            }
                        }
                        else
                        {
                            ++index;
                            continue;
                        }
//                            long key_long = blockpos.toLong();

                        // if (blockpos.getY() == max_y - 1 || blockpos.getY() == max_y + 1 || blockpos.getY() == max_y)
                        // {
//                            if (!this.index_snode_map.containsKey(key_long))
//                            {
//                            this.index_snode_map.put(key_long, true);
                        IBlockState iblockstate = world.getBlockState(blockpos);
                        Material material = iblockstate.getMaterial();
                        SNode children_snode = start_snode.children_snode_array[index];
                        if (!passBlock(material) || isSame(children_snode))
                        {
                            ++index;
                            continue;
                        }

//                        boolean walk_able = this.isWalkAble(start_snode, x, y, z, world);
                        // BlockPos[] sub_blockpos_array = null;

//                            SNode children_snode = new SNode(blockpos, walk_able);
                        children_snode.calculateG(start_snode);
//                        children_snode.walk_able = walk_able;

                        if (this.isWalkAble(start_snode, x, y, z, world) && children_snode.g < MAX_G /*&& blockpos.getDistance(this.goal_x, this.goal_y, this.goal_z) <= 16.0D*/)
                        {
//                            children_snode.calculateH(this.goal_x, this.goal_y, this.goal_z);
//                            children_snode.calculateF();
                            return children_snode;
                            // children_snode.sub_blockpos_array = sub_blockpos_array;
//                                this.snode_arraylist.add(children_snode);
                        }
                        else
                        {
                            ++index;
                        }

                        // if (goal_x == blockpos.getX() && goal_y == blockpos.getY() && goal_z == blockpos.getZ())
                        // {
                        //     return index;
                        // }
//                            }
                        // }
                    }
                }
            }

//                if (goal_x == snode.blockpos.getX() && goal_y == snode.blockpos.getY() && goal_z == snode.blockpos.getZ())
//                {
//                    have_goal = true;
//                }
        }

        start_snode.children_snode_array = null;

        return null;
    }

    public SNode setChild(SNode start_snode, byte new_x, byte new_y, byte new_z)
    {
        int index = getIndex(new_x, new_y, new_z);

        World world = this.skinningentities.getEntityWorld();
        if (index != -1 && start_snode.children_snode_array[index] == null)
        {
            BlockPos blockpos = new BlockPos(start_snode.blockpos.getX() + new_x, start_snode.blockpos.getY() + new_y, start_snode.blockpos.getZ() + new_z);
            start_snode.children_snode_array[index] = new SNode(blockpos);
            start_snode.children_snode_array[index].calculateG(start_snode);
            IBlockState iblockstate = world.getBlockState(blockpos);
            Material material = iblockstate.getMaterial();
            SNode children_snode = start_snode.children_snode_array[index];
            if (passBlock(material) && !isSame(children_snode))
            {
//                boolean walk_able = this.isWalkAble(start_snode, new_x, new_y, new_z, world);

                if (this.isWalkAble(start_snode, new_x, new_y, new_z, world) && children_snode.g < MAX_G/* && blockpos.getDistance(this.goal_x, this.goal_y, this.goal_z) <= 16.0D*/)
                {
//                    children_snode.calculateH(this.goal_x, this.goal_y, this.goal_z);
//                    children_snode.calculateF();
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

    public boolean isWalkAble(SNode snode, byte x, byte y, byte z, World world)
    {
        if (x != 0 && z != 0)
        {
            if (y == -1)
            {
//                BlockPos temp_blockpos0 = new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY(), snode.blockpos.getZ());
//                BlockPos temp_blockpos1 = new BlockPos(snode.blockpos.getX(), snode.blockpos.getY(), snode.blockpos.getZ() + z);
//                BlockPos temp_blockpos2 = new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY(), snode.blockpos.getZ() + z);
//                // BlockPos temp_blockpos3 = new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() - 1, snode.blockpos.getZ());
//                // BlockPos temp_blockpos4 = new BlockPos(snode.blockpos.getX(), snode.blockpos.getY() - 1, snode.blockpos.getZ() + z);
//                IBlockState temp_iblockstate0 = world.getBlockState(temp_blockpos0);
//                IBlockState temp_iblockstate1 = world.getBlockState(temp_blockpos1);
//                IBlockState temp_iblockstate2 = world.getBlockState(temp_blockpos2);
//                // BlockState temp_blockstate3 = level.getBlockState(temp_blockpos3);
//                // BlockState temp_blockstate4 = level.getBlockState(temp_blockpos4);
                Material temp_material0 = this.skinningentities.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY(), snode.blockpos.getZ()));//temp_iblockstate0.getMaterial();
                Material temp_material1 = this.skinningentities.getMaterial(new BlockPos(snode.blockpos.getX(), snode.blockpos.getY(), snode.blockpos.getZ() + z));//temp_iblockstate1.getMaterial();
                Material temp_material2 = this.skinningentities.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY(), snode.blockpos.getZ() + z));//temp_iblockstate2.getMaterial();
                // Material temp_material3 = temp_blockstate3.getMaterial();
                // Material temp_material4 = temp_blockstate4.getMaterial();
                Material temp_material3 = this.skinningentities.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() + y - 1, snode.blockpos.getZ() + z));
                Material temp_material4 = this.skinningentities.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() + y - 2, snode.blockpos.getZ() + z));

                // if (this.isBlock(temp_material3) && this.passBlock(temp_material2) && this.passBlock(temp_material0))
                // {
                //     sub_blockpos_array = new BlockPos[2];
                //     sub_blockpos_array[0] = temp_blockpos0;
                //     sub_blockpos_array[1] = temp_blockpos2;
                // }
                // else if (this.isBlock(temp_material4) && this.passBlock(temp_material2) && this.passBlock(temp_material1))
                // {
                //     sub_blockpos_array = new BlockPos[2];
                //     sub_blockpos_array[0] = temp_blockpos1;
                //     sub_blockpos_array[1] = temp_blockpos2;
                // }
                // else if (this.passBlock(temp_material0) && this.passBlock(temp_material1))
                // {
                //     //free
                // }
                // else
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
//                BlockPos temp_blockpos0 = new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY(), snode.blockpos.getZ());
//                BlockPos temp_blockpos1 = new BlockPos(snode.blockpos.getX(), snode.blockpos.getY(), snode.blockpos.getZ() + z);
//                // BlockPos temp_blockpos2 = new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() - 1, snode.blockpos.getZ());
//                // BlockPos temp_blockpos3 = new BlockPos(snode.blockpos.getX(), snode.blockpos.getY() - 1, snode.blockpos.getZ() + z);
//                IBlockState temp_iblockstate0 = world.getBlockState(temp_blockpos0);
//                IBlockState temp_iblockstate1 = world.getBlockState(temp_blockpos1);
//                // BlockState temp_blockstate2 = level.getBlockState(temp_blockpos2);
//                // BlockState temp_blockstate3 = level.getBlockState(temp_blockpos3);
                Material temp_material0 = this.skinningentities.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY(), snode.blockpos.getZ()));//temp_iblockstate0.getMaterial();
                Material temp_material1 = this.skinningentities.getMaterial(new BlockPos(snode.blockpos.getX(), snode.blockpos.getY(), snode.blockpos.getZ() + z));//temp_iblockstate1.getMaterial();
                Material temp_material2 = this.skinningentities.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() + y - 1, snode.blockpos.getZ() + z));
                Material temp_material3 = this.skinningentities.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() + y - 2, snode.blockpos.getZ() + z));

                // Material temp_material2 = temp_blockstate2.getMaterial();
                // Material temp_material3 = temp_blockstate3.getMaterial();

                // if (this.passBlock(temp_material0) && this.isBlock(temp_material2))
                // {
                //     sub_blockpos_array = new BlockPos[1];
                //     sub_blockpos_array[0] = temp_blockpos0;
                // }
                // else if (this.passBlock(temp_material1) && this.isBlock(temp_material3))
                // {
                //     sub_blockpos_array = new BlockPos[1];
                //     sub_blockpos_array[0] = temp_blockpos1;
                // }
                // else
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
////                BlockPos temp_blockpos0 = new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY(), snode.blockpos.getZ());
////                BlockPos temp_blockpos1 = new BlockPos(snode.blockpos.getX(), snode.blockpos.getY(), snode.blockpos.getZ() + z);
////                BlockPos temp_blockpos1 = new BlockPos(snode.blockpos.getX(), snode.blockpos.getY() - 1, snode.blockpos.getZ());
//                BlockPos temp_blockpos2 = new BlockPos(snode.blockpos.getX(), snode.blockpos.getY() + y, snode.blockpos.getZ());
//                BlockPos temp_blockpos3 = new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY(), snode.blockpos.getZ() + z);
//                BlockPos temp_blockpos4 = new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() + y, snode.blockpos.getZ());
//                BlockPos temp_blockpos5 = new BlockPos(snode.blockpos.getX(), snode.blockpos.getY() + y, snode.blockpos.getZ() + z);
//                // BlockPos temp_blockpos6 = new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() - 1, snode.blockpos.getZ());
//                // BlockPos temp_blockpos7 = new BlockPos(snode.blockpos.getX(), snode.blockpos.getY() - 1, snode.blockpos.getZ() + z);
////                IBlockState temp_iblockstate0 = world.getBlockState(temp_blockpos0);
////                IBlockState temp_iblockstate1 = world.getBlockState(temp_blockpos1);
//                IBlockState temp_iblockstate2 = world.getBlockState(temp_blockpos2);
//                IBlockState temp_iblockstate3 = world.getBlockState(temp_blockpos3);
//                IBlockState temp_iblockstate4 = world.getBlockState(temp_blockpos4);
//                IBlockState temp_iblockstate5 = world.getBlockState(temp_blockpos5);
//                // BlockState temp_blockstate6 = level.getBlockState(temp_blockpos6);
//                // BlockState temp_blockstate7 = level.getBlockState(temp_blockpos7);
////                Material temp_material0 = temp_iblockstate0.getMaterial();
////                Material temp_material1 = temp_iblockstate1.getMaterial();
                Material temp_material2 = this.skinningentities.getMaterial(new BlockPos(snode.blockpos.getX(), snode.blockpos.getY() + y, snode.blockpos.getZ()));//temp_iblockstate2.getMaterial();
                Material temp_material3 = this.skinningentities.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY(), snode.blockpos.getZ() + z));//temp_iblockstate3.getMaterial();
                Material temp_material4 = this.skinningentities.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() + y, snode.blockpos.getZ()));//temp_iblockstate4.getMaterial();
                Material temp_material5 = this.skinningentities.getMaterial(new BlockPos(snode.blockpos.getX(), snode.blockpos.getY() + y, snode.blockpos.getZ() + z));//temp_iblockstate5.getMaterial();
                Material start_down_material = this.skinningentities.getMaterial(snode.blockpos.down());

                // Material temp_material6 = temp_blockstate6.getMaterial();
                // Material temp_material7 = temp_blockstate7.getMaterial();

                // if (this.isBlock(temp_material0) && this.passBlock(temp_material4) && this.passBlock(temp_material2))
                // {
                //     // sub_blockpos_array = new BlockPos[1];
                //     // sub_blockpos_array[0] = temp_blockpos4;
                // }
                // else if (this.isBlock(temp_material1) && this.passBlock(temp_material5) && this.passBlock(temp_material2))
                // {
                //     // sub_blockpos_array = new BlockPos[1];
                //     // sub_blockpos_array[0] = temp_blockpos5;
                // }
                // else if (this.passBlock(temp_material4) && this.passBlock(temp_material5) && this.passBlock(temp_material2))
                // {
                //     //free
                // }
                // else if (this.passBlock(temp_material0) && this.passBlock(temp_material4) && this.isBlock(temp_material6))
                // {
                //     // sub_blockpos_array = new BlockPos[1];
                //     // sub_blockpos_array[0] = temp_blockpos0;
                // }
                // else if (this.passBlock(temp_material1) && this.passBlock(temp_material5) && this.isBlock(temp_material7))
                // {
                //     // sub_blockpos_array = new BlockPos[1];
                //     // sub_blockpos_array[0] = temp_blockpos1;
                // }
                // else if (this.passBlock(temp_material0) && this.passBlock(temp_material3) && this.isBlock(temp_material6))
                // {
                //     // sub_blockpos_array = new BlockPos[2];
                //     // sub_blockpos_array[0] = temp_blockpos0;
                //     // sub_blockpos_array[1] = temp_blockpos3;
                // }
                // else if (this.passBlock(temp_material1) && this.passBlock(temp_material3) && this.isBlock(temp_material7))
                // {
                //     // sub_blockpos_array = new BlockPos[2];
                //     // sub_blockpos_array[0] = temp_blockpos1;
                //     // sub_blockpos_array[1] = temp_blockpos3;
                // }
                // else if (this.passBlock(temp_material0) && this.passBlock(temp_material1) && this.passBlock(temp_material3))
                // {
                //     // sub_blockpos_array = new BlockPos[1];
                //     // sub_blockpos_array[0] = temp_blockpos3;
                // }
                // else
                if
                (
//                    (fallBlock(temp_material0) && fallBlock(temp_material1)) ||
//                    fallBlock(temp_material1) ||
                    isBlock(temp_material2) ||
                    fallBlock(temp_material3) ||
                    (isBlock(temp_material4) && isBlock(temp_material5)) ||
                    (passBlock(start_down_material) && !this.skinningentities.isInWater())
                )
                {
                    return false;
                }
            }

//            if (!this.skinningentities.isInWater())
//            {
////                if (y == -1)
////                {
////////                    BlockPos temp_blockpos0 = new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY(), snode.blockpos.getZ() + z);
//////                    BlockPos temp_blockpos1 = new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() + y - 1, snode.blockpos.getZ() + z);
//////                    BlockPos temp_blockpos2 = new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() + y - 2, snode.blockpos.getZ() + z);
////////                    IBlockState temp_iblockstate0 = world.getBlockState(temp_blockpos0);
//////                    IBlockState temp_iblockstate1 = world.getBlockState(temp_blockpos1);
//////                    IBlockState temp_iblockstate2 = world.getBlockState(temp_blockpos2);
////////                    Material temp_material0 = temp_iblockstate0.getMaterial();
////                    Material temp_material1 = this.skinningentities.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() + y - 1, snode.blockpos.getZ() + z));//temp_iblockstate1.getMaterial();
////                    Material temp_material2 = this.skinningentities.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() + y - 2, snode.blockpos.getZ() + z));//temp_iblockstate2.getMaterial();
////
////                    if
////                    (
//////                        isBlock(temp_material0) ||
////                        (fallBlock(temp_material1) && fallBlock(temp_material2))
////                    )
////                    {
////                        return false;
////                    }
////                }
////                else if (y == 0)
////                {
//////                    BlockPos temp_blockpos0 = new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() + y - 1, snode.blockpos.getZ() + z);
//////                    BlockPos temp_blockpos2 = new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() + y - 2, snode.blockpos.getZ() + z);
//////                    IBlockState temp_iblockstate0 = world.getBlockState(temp_blockpos0);
//////                    IBlockState temp_iblockstate2 = world.getBlockState(temp_blockpos2);
////                    Material temp_material0 = this.skinningentities.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() + y - 1, snode.blockpos.getZ() + z));//temp_iblockstate0.getMaterial();
////                    Material temp_material2 = this.skinningentities.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() + y - 2, snode.blockpos.getZ() + z));//temp_iblockstate2.getMaterial();
////
////                    if
////                    (
////                        fallBlock(temp_material0) && fallBlock(temp_material2)
////                    )
////                    {
////                        return false;
////                    }
////                }
////                else if (y == 1)
////                {
////                    Material start_down_material = this.skinningentities.getMaterial(snode.blockpos.down());
////                    if (this.passBlock(start_down_material))
////                    {
////                        return false;
////                    }
////                }
//            }
        }
        else
        {
//            if (!this.skinningentities.isInWater())
//            {
//                if (y == 1)
//                {
//                    Material start_down_material = this.skinningentities.getMaterial(snode.blockpos.down());
//                    if (passBlock(start_down_material))
//                    {
//                        return false;
//                    }
//                }
//            }

            if (y == -1)
            {
//                BlockPos temp_blockpos0 = new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY(), snode.blockpos.getZ() + z);
//                BlockPos temp_blockpos1 = new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() + y - 1, snode.blockpos.getZ() + z);
//                BlockPos temp_blockpos2 = new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() + y - 2, snode.blockpos.getZ() + z);
//                IBlockState temp_iblockstate0 = world.getBlockState(temp_blockpos0);
//                IBlockState temp_iblockstate1 = world.getBlockState(temp_blockpos1);
//                IBlockState temp_iblockstate2 = world.getBlockState(temp_blockpos2);
                Material temp_material0 = this.skinningentities.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY(), snode.blockpos.getZ() + z));//temp_iblockstate0.getMaterial();
                Material temp_material1 = this.skinningentities.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() + y - 1, snode.blockpos.getZ() + z));//temp_iblockstate1.getMaterial();
                Material temp_material2 = this.skinningentities.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() + y - 2, snode.blockpos.getZ() + z));//temp_iblockstate2.getMaterial();

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
//                BlockPos temp_blockpos0 = new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() - 1, snode.blockpos.getZ() + z);
//                BlockPos temp_blockpos1 = new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() - 2, snode.blockpos.getZ() + z);
//                IBlockState temp_iblockstate0 = world.getBlockState(temp_blockpos0);
//                IBlockState temp_iblockstate1 = world.getBlockState(temp_blockpos1);
                Material temp_material0 = this.skinningentities.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() - 1, snode.blockpos.getZ() + z));//temp_iblockstate0.getMaterial();
                Material temp_material1 = this.skinningentities.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() - 2, snode.blockpos.getZ() + z));//temp_iblockstate1.getMaterial();

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
//                BlockPos temp_blockpos0 = new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY(), snode.blockpos.getZ() + z);
////                BlockPos temp_blockpos1 = new BlockPos(snode.blockpos.getX(), snode.blockpos.getY() - 1, snode.blockpos.getZ());
//                BlockPos temp_blockpos2 = new BlockPos(snode.blockpos.getX(), snode.blockpos.getY() + y, snode.blockpos.getZ());
//                IBlockState temp_iblockstate0 = world.getBlockState(temp_blockpos0);
////                IBlockState temp_iblockstate1 = world.getBlockState(temp_blockpos1);
//                IBlockState temp_iblockstate2 = world.getBlockState(temp_blockpos2);
                Material temp_material0 = this.skinningentities.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY(), snode.blockpos.getZ() + z));//temp_iblockstate0.getMaterial();
//                Material temp_material1 = temp_iblockstate1.getMaterial();
                Material temp_material2 = this.skinningentities.getMaterial(new BlockPos(snode.blockpos.getX(), snode.blockpos.getY() + y, snode.blockpos.getZ()));//temp_iblockstate2.getMaterial();
                Material start_down_material = this.skinningentities.getMaterial(snode.blockpos.down());

                if
                (
                    fallBlock(temp_material0) ||
//                    fallBlock(temp_material1) ||
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

    public static byte getIndex(byte x, byte y, byte z)
    {
        byte index = 0;

        for (byte xi : PATH_BYTE_ARRAY)
        {
            for (byte yi : PATH_BYTE_ARRAY)
            {
                for (byte zi : PATH_BYTE_ARRAY)
                {
                    if (!(xi == 0 && yi == 0 && zi == 0))
                    {
                        if (xi == x && yi == y && zi == z)
                        {
                            return index;
                        }

                        ++index;
                    }
                }
            }
        }

        return -1;
    }

    public static boolean isSame(SNode snode)
    {
//        full count
        SNode current_snode = snode.parent_snode;
        while (current_snode != null)
        {
            if (snode.blockpos.getX() == current_snode.blockpos.getX() && snode.blockpos.getY() == current_snode.blockpos.getY() && snode.blockpos.getZ() == current_snode.blockpos.getZ())
            {
                return true;
            }

            current_snode = current_snode.parent_snode;
        }

//        lazy count
//        for (BlockPos blockpos : this.index_blockpos_arraylist)
//        {
//            if (blockpos.getX() == snode.blockpos.getX() && blockpos.getY() == snode.blockpos.getY() && blockpos.getZ() == snode.blockpos.getZ())
//            {
//                return true;
//            }
//        }

        return false;
    }
}
