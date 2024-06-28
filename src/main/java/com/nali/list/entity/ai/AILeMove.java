package com.nali.list.entity.ai;

import com.nali.data.IBothDaE;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.small.entity.memo.server.ai.AI;
import com.nali.sound.ISoundLe;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.Vec3d;

public class AILeMove<SD extends ISoundLe, BD extends IBothDaE, E extends EntityLivingBase, I extends IMixLe<SD, BD, E>, S extends ServerLe<SD, BD, E, I, A>, A extends MixAIE<SD, BD, E, I, S>> extends AI<SD, BD, E, I, S, A>
{
    public static byte ID;

    public AILeLook<SD, BD, E, I, S, A> ailelook;
    public AILeJump<SD, BD, E, I, S, A> ailejump;
    public AIESit<SD, BD, E, I, S, A> aiesit;

    public double x, y, z;
//    public double ox, oy, oz;
    public double speed;
    public boolean move, should_on_pos;

    public AILeMove(S s)
    {
        super(s);
    }

    @Override
    public void init()
    {
        this.ailelook = (AILeLook<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AILeLook.ID);
        this.ailejump = (AILeJump<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AILeJump.ID);
        this.aiesit = (AIESit<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AIESit.ID);
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
            if ((this.aiesit.state & 1) == 0)
            {
                E e = this.s.i.getE();
                double wy = this.y - e.posY;
                // ((FriendsEntities)this.mob).entitiespath.tick();

                // this.wantedX = this.mob.getX() + 1;
                // this.wantedZ = this.mob.getX() + 1;

                if (e.isInWater())
                {
                    this.speed = 6.0F;
                    e.setNoGravity(true);
                    e.onGround = true;
                }
                else
                {
                    this.speed = 3.0F;
                    // this.mob.setOnGround(true);
                    e.setNoGravity(false);
                }

                if (this.move || wy > 0.0D)
                {
                    // double d0 = this.wantedX - this.mob.getX();
                    // double d2 = this.wantedZ - this.mob.getZ();
                    // double d3 = d0 * d0 + d1 * d1 + d2 * d2;

                    // if (d3 < (double)2.5000003E-7F)
                    // {
                    //     this.mob.setZza(0.0F);
                    // }
                    // else
                    // {
                        float f1 = (float)(e.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());

                        if (e.isInWater())
                        {
                            if (wy > 0)
                            {
                                wy = 2.0D;
                            }
                            else if (wy < 0)
                            {
                                wy = -2.0D;
                            }

                            if (e.onGround || e.isInWater())
                            {
                                e.motionY += wy * f1;//(this.entity.getDeltaMovement().add(0.0D, wy * f1, 0.0D));
                            }
                            // this.mob.setYya((float)d1 * f1 * 16.0F);
                        }
                        // else
                        // {
                        //     this.mob.setSpeed(f1 * this.outsideWaterSpeedModifier);
                        // }
                    // }
                }
                // // else
                // // {
                // //     this.mob.setSpeed(0.0F);
                // //     this.mob.setXxa(0.0F);
                // //     this.mob.setYya(0.0F);
                // //     this.mob.setZza(0.0F);
                // // }

                // super.tick();

                // if (this.operation == MoveControl.Operation.STRAFE)
                // {
                //     float f = (float)this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED);
                //     float f1 = (float)this.speedModifier * f;
                //     float f2 = this.strafeForwards;
                //     float f3 = this.strafeRight;
                //     float f4 = Mth.sqrt(f2 * f2 + f3 * f3);
                //     if (f4 < 1.0F)
                //     {
                //         f4 = 1.0F;
                //     }

                //     f4 = f1 / f4;
                //     f2 *= f4;
                //     f3 *= f4;
                //     float f5 = Mth.sin(this.mob.getYRot() * ((float)Math.PI / 180F));
                //     float f6 = Mth.cos(this.mob.getYRot() * ((float)Math.PI / 180F));
                //     // float f7 = f2 * f6 - f3 * f5;
                //     // float f8 = f3 * f6 + f2 * f5;
                //     // if (!this.isWalkable(f7, f8))
                //     // {
                //     //     this.strafeForwards = 1.0F;
                //     //     this.strafeRight = 0.0F;
                //     // }

                //     this.mob.setSpeed(f1);
                //     this.mob.setZza(this.strafeForwards);
                //     this.mob.setXxa(this.strafeRight);
                //     this.operation = MoveControl.Operation.WAIT;
                // }
        //        Nali.LOGGER.info("MOVE!");
                if (this.move)
                {
        //            this.move = false;
        //            double d0 = this.x - e.posX;
        //            double d1 = this.z - e.posZ;
        //            double d2 = this.y - e.posY;
        //            double d3 = d0 * d0 + d2 * d2 + d1 * d1;
        //            if (d3 < (double)2.5000003E-7F)
        //            {
        //                e.moveForward = 0.0F;
        //                return;
        //            }

        //            float f9 = (float)(MathHelper.atan2(d1, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
        //            e.rotationYaw = this.limitAngle(e.rotationYaw, f9, 90.0F);
        //            e.rotationYawHead = e.rotationYaw;
        //            Nali.LOGGER.info("DONE " + this.ailelook.done);
                    if (this.ailelook.done/* || d1 != 0*/)
                    {
                        Entity riding_entity = e.getRidingEntity();
                        if (riding_entity != null)
                        {
                            Vec3d vec3d = riding_entity.getLookVec().scale((float)(this.speed * e.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()));
                            riding_entity.move(MoverType.SELF, vec3d.x, vec3d.y, vec3d.z);
                        }
                        else
                        {
                            e.setAIMoveSpeed((float)(this.speed * e.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()));
                        }
                    }

                    this.ailelook.set(this.x, this.y, this.z, 4.5F);
        //            if (this.ox == this.x && this.oy == this.y && this.oz == this.z)
        //            {
        //            this.ailelook.done = true;
        //            }
        //
        //            this.ox = this.x;
        //            this.oy = this.y;
        //            this.oz = this.z;
        //            this.speed = 4.0F;
                    // BlockPos blockpos = this.mob.blockPosition();
                    // BlockState blockstate = this.mob.level.getBlockState(blockpos);
                    // VoxelShape voxelshape = blockstate.getCollisionShape(this.mob.level, blockpos);
                    //if (d2 > (double)this.mob.getStepHeight() && d0 * d0 + d1 * d1 < (double)Math.max(1.0F, this.mob.getBbWidth()) || !voxelshape.isEmpty() && this.mob.getY() < voxelshape.max(Direction.Axis.Y) + (double)blockpos.getY() && !blockstate.is(BlockTags.DOORS) && !blockstate.is(BlockTags.FENCES))
                    if (wy > 0.0D)
                    {
                        this.ailejump.setJumping();
                        // this.operation = MoveControl.Operation.JUMPING;
                    }
                }
                // else if (this.operation == MoveControl.Operation.JUMPING)
                // {
                //     this.mob.setSpeed((float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED)));
                //     if (this.mob.isOnGround())
                //     {
                //         this.operation = MoveControl.Operation.WAIT;
                //     }
                // }
                else
                {
                    e.moveForward = 0.0F;
                }
            }
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

    public void setWanted(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.move = true;
    }

//    public void setWantedX(double x)
//    {
//        this.posX = x;
//    }
//
//    public void setWantedY(double y)
//    {
//        this.posY = y;
//    }
//
//    public void setWantedZ(double z)
//    {
//        this.posZ = z;
//    }
//
//    public void setMoveOperation(EntityMoveHelper.Action action)
//    {
//        this.action = action;
//    }

//    public float limitAngle(float sourceAngle, float targetAngle, float maximumChange)
//    {
//        float f = MathHelper.wrapDegrees(targetAngle - sourceAngle);
//
//        if (f > maximumChange)
//        {
//            f = maximumChange;
//        }
//
//        if (f < -maximumChange)
//        {
//            f = -maximumChange;
//        }
//
//        float f1 = sourceAngle + f;
//
//        if (f1 < 0.0F)
//        {
//            f1 += 360.0F;
//        }
//        else if (f1 > 360.0F)
//        {
//            f1 -= 360.0F;
//        }
//
//        return f1;
//    }

    public boolean isDone()
    {
        E e = this.s.i.getE();
//        return e.getDistanceSq(this.x, this.y, this.z) <= 0.5D;
        boolean should_work = (int)e.posX == (int)this.x && (int)e.posY == (int)this.y && (int)e.posZ == (int)this.z;
        if (should_work && this.should_on_pos)
        {
            e.setPosition(this.x, this.y, this.z);
//            e.moveForward = Float.MIN_VALUE;
            this.should_on_pos = false;
        }
        return should_work;
    }
}
