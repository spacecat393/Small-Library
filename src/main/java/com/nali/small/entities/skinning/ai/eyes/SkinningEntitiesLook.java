package com.nali.small.entities.skinning.ai.eyes;

import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.entities.skinning.ai.SkinningEntitiesAI;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class SkinningEntitiesLook extends SkinningEntitiesAI
{
//    public static float EPSILON = 1e-6f;
//    public float deltaLookYaw;
//    public float deltaLookPitch;
    public boolean looking;
    public double x;
    public double y;
    public double z;
    public float max;
    public boolean done;

    public SkinningEntitiesLook(SkinningEntities skinningentities)
    {
        super(skinningentities);
    }

//    public void setLookPositionWithEntity(Entity entityIn, float deltaYaw, float deltaPitch)
//    {
//        this.posX = entityIn.posX;
//
//        if (entityIn instanceof EntityLivingBase)
//        {
//            this.posY = entityIn.posY + (double)entityIn.getEyeHeight();
//        }
//        else
//        {
//            this.posY = (entityIn.getEntityBoundingBox().minY + entityIn.getEntityBoundingBox().maxY) / 2.0D;
//        }
//
//        this.posZ = entityIn.posZ;
//        this.deltaLookYaw = deltaYaw;
//        this.deltaLookPitch = deltaPitch;
//        this.looking = true;
//    }

//    public void setLookPosition(double x, double y, double z, float deltaYaw, float deltaPitch)
//    {
//        this.posX = x;
//        this.posY = y;
//        this.posZ = z;
//        this.deltaLookYaw = deltaYaw;
//        this.deltaLookPitch = deltaPitch;
//        this.looking = true;
//    }

    public void set(double x, double y, double z, float max)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.max = max;
        this.looking = true;
        this.done = false;
    }

    @Override
    public void onUpdate()
    {
//        this.skinningentities.rotationPitch = 0.0F;
////        this.skinningentities.renderYawOffset = this.skinningentities.rotationYaw;
////        {
////        this.skinningentities.renderYawOffset = this.updateRotation(this.skinningentities.rotationYawHead, this.skinningentities.renderYawOffset, 10.0F);
////        }
//
        if (this.looking)
        {
            float yaw/*, pitch*/;

            this.looking = false;
            double d0 = this.x - this.skinningentities.posX;
            double d1 = this.y - this.skinningentities.posY;
            double d2 = this.z - this.skinningentities.posZ;
            double d3 = MathHelper.sqrt(d0 * d0 + d2 * d2);
            float f = (float)(MathHelper.atan2(d2, d0) * (180.0D / Math.PI)) - 90.0F;
            float f1 = (float)(-(MathHelper.atan2(d1, d3) * (180.0D / Math.PI)));
//            this.skinningentities.rotationPitch = this.updateRotation(this.skinningentities.rotationPitch, f1, this.deltaLookPitch);
//            this.skinningentities.rotationYawHead = this.updateRotation(this.skinningentities.rotationYawHead, f, this.deltaLookYaw);

            Entity riding_entity = this.skinningentities.getRidingEntity();
            if (riding_entity != null)
//        for (Entity entity : this.skinningentities.getPassengers())
            {
                yaw = riding_entity.rotationYaw;
//                pitch = riding_entity.rotationPitch;
                riding_entity.rotationYaw = this.limitAngle(riding_entity.rotationYaw, f, this.max);
                riding_entity.rotationPitch = this.limitAngle(riding_entity.rotationPitch, f1, this.max);
//            entity.rotationYaw = this.skinningentities.rotationYaw;
                riding_entity.setRotationYawHead(riding_entity.rotationYaw);
                this.skinningentities.rotationYaw = riding_entity.rotationYaw;
//                this.skinningentities.rotationYawHead = riding_entity.rotationYaw;

//                if (yaw * yaw - riding_entity.rotationYaw * riding_entity.rotationYaw < 0.000001F && pitch * pitch - riding_entity.rotationPitch * riding_entity.rotationPitch < 0.000001F)
                if (yaw == riding_entity.rotationYaw/* && pitch == riding_entity.rotationPitch*/)
//                if (Math.abs(yaw - riding_entity.rotationYaw) < EPSILON && Math.abs(pitch - riding_entity.rotationPitch) < EPSILON)
                {
                    this.done = true;
                }
            }
            else
            {
                yaw = this.skinningentities.rotationYaw;
//                pitch = this.skinningentities.rotationPitch;
                this.skinningentities.rotationYaw = this.limitAngle(this.skinningentities.rotationYaw, f, this.max);
                this.skinningentities.rotationPitch = this.limitAngle(this.skinningentities.rotationPitch, f1, this.max);
    //        this.skinningentities.prevRotationYaw = this.skinningentities.rotationYaw;
    //        this.skinningentities.renderYawOffset += 22.5F;
//                this.skinningentities.rotationYawHead = this.skinningentities.rotationYaw;
    //        this.skinningentities.prevRotationYawHead = this.skinningentities.rotationYaw;
    //        this.skinningentities.renderYawOffset = this.skinningentities.rotationYaw;
    //        this.skinningentities.prevRenderYawOffset = this.skinningentities.rotationYaw;

//                if (yaw * yaw - this.skinningentities.renderYawOffset * this.skinningentities.renderYawOffset < 0.000001F && pitch * pitch - this.skinningentities.rotationPitch * this.skinningentities.rotationPitch < 0.000001F)
                if (yaw == this.skinningentities.rotationYaw/* && pitch == this.skinningentities.rotationPitch*/)
//                if (Math.abs(yaw - this.skinningentities.rotationYaw) < EPSILON && Math.abs(pitch - this.skinningentities.rotationPitch) < EPSILON)
                {
                    this.done = true;
                }
            }
        }
//        this.skinningentities.skinningentitiesbody.onUpdate();//.updateRenderAngles();
//        this.skinningentities.rotationYawHead = this.skinningentities.rotationYaw;
//        this.skinningentities.renderYawOffset = this.skinningentities.rotationYaw;
//        else
//        {
//            this.skinningentities.rotationYawHead = this.updateRotation(this.skinningentities.rotationYawHead, this.skinningentities.renderYawOffset, 10.0F);
//        }
//
////        float f2 = MathHelper.wrapDegrees(this.skinningentities.rotationYawHead - this.skinningentities.renderYawOffset);
//
////        if (!this.skinningentities.getNavigator().noPath())
////        {
////            if (f2 < -75.0F)
////            {
////                this.skinningentities.rotationYawHead = this.skinningentities.renderYawOffset - 75.0F;
////            }
////
////            if (f2 > 75.0F)
////            {
////                this.skinningentities.rotationYawHead = this.skinningentities.renderYawOffset + 75.0F;
////            }
////        }
    }

    public float limitAngle(float source_angle, float target_angle, float maximum_change)
    {
        float f = MathHelper.wrapDegrees(target_angle - source_angle);

        if (f > maximum_change)
        {
            f = maximum_change;
        }

        if (f < -maximum_change)
        {
            f = -maximum_change;
        }

//        float f1 = source_angle + f;

//        if (f1 < 0.0F)
//        {
//            f1 += 360.0F;
//        }
//        else if (f1 > 360.0F)
//        {
//            f1 -= 360.0F;
//        }

//        return f1;
        return source_angle + f;
    }
}
