package com.nali.ilol.entities.skinning.ai.path;

import com.nali.ilol.entities.skinning.SkinningEntities;
import com.nali.ilol.entities.skinning.ai.SkinningEntitiesAI;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.math.MathHelper;

public class SkinningEntitiesMove extends SkinningEntitiesAI
{
    public double x;
    public double y;
    public double z;
    public double speed;
    public boolean move = false;

    public SkinningEntitiesMove(SkinningEntities skinningentities)
    {
        super(skinningentities);
    }

    @Override
    public void onUpdate()
    {
        double wy = this.y - this.skinningentities.posY;
        // ((FriendsEntities)this.mob).entitiespath.tick();

        // this.wantedX = this.mob.getX() + 1;
        // this.wantedZ = this.mob.getX() + 1;

        if (this.skinningentities.isInWater())
        {
            this.speed = 6.0F;
            this.skinningentities.setNoGravity(true);
            this.skinningentities.onGround = true;
        }
        else
        {
            this.speed = 3.0F;
            // this.mob.setOnGround(true);
            this.skinningentities.setNoGravity(false);
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
                float f1 = (float)(this.skinningentities.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());

                if (this.skinningentities.isInWater())
                {
                    if (wy > 0)
                    {
                        wy = 2.0D;
                    }
                    else if (wy < 0)
                    {
                        wy = -2.0D;
                    }

                    if (this.skinningentities.onGround || this.skinningentities.isInWater())
                    {
                        this.skinningentities.motionY += wy * f1;//(this.entity.getDeltaMovement().add(0.0D, wy * f1, 0.0D));
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
        if (this.move)
        {
            this.move = false;
            double d0 = this.x - this.skinningentities.posX;
            double d1 = this.z - this.skinningentities.posZ;
            double d2 = this.y - this.skinningentities.posY;
            double d3 = d0 * d0 + d2 * d2 + d1 * d1;
            if (d3 < (double)2.5000003E-7F)
            {
                this.skinningentities.moveForward = 0.0F;
                return;
            }

//            float f9 = (float)(MathHelper.atan2(d1, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
//            this.skinningentities.rotationYaw = this.limitAngle(this.skinningentities.rotationYaw, f9, 90.0F);
//            this.skinningentities.rotationYawHead = this.skinningentities.rotationYaw;
            this.skinningentities.skinningentitieslook.set(this.x, this.y, this.z, 90.0F);
//            this.speed = 4.0F;
            this.skinningentities.setAIMoveSpeed((float)(this.speed * this.skinningentities.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()));
            // BlockPos blockpos = this.mob.blockPosition();
            // BlockState blockstate = this.mob.level.getBlockState(blockpos);
            // VoxelShape voxelshape = blockstate.getCollisionShape(this.mob.level, blockpos);
            //if (d2 > (double)this.mob.getStepHeight() && d0 * d0 + d1 * d1 < (double)Math.max(1.0F, this.mob.getBbWidth()) || !voxelshape.isEmpty() && this.mob.getY() < voxelshape.max(Direction.Axis.Y) + (double)blockpos.getY() && !blockstate.is(BlockTags.DOORS) && !blockstate.is(BlockTags.FENCES))
            if (wy > 0.0D)
            {
                this.skinningentities.skinningentitiesjump.setJumping();
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
            this.skinningentities.moveForward = 0.0F;
        }
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

    public float limitAngle(float sourceAngle, float targetAngle, float maximumChange)
    {
        float f = MathHelper.wrapDegrees(targetAngle - sourceAngle);

        if (f > maximumChange)
        {
            f = maximumChange;
        }

        if (f < -maximumChange)
        {
            f = -maximumChange;
        }

        float f1 = sourceAngle + f;

        if (f1 < 0.0F)
        {
            f1 += 360.0F;
        }
        else if (f1 > 360.0F)
        {
            f1 -= 360.0F;
        }

        return f1;
    }

    public boolean isDone()
    {
        return this.skinningentities.getDistanceSq(this.x, this.y, this.z) <= 0.5D;
    }
}
