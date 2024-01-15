package com.nali.small.entities.skinning.ai.eyes;

import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.entities.skinning.ai.SkinningEntitiesAI;
import net.minecraft.util.math.MathHelper;

public class SkinningEntitiesBody extends SkinningEntitiesAI
{
    public int rotationTickCounter;
    public float prevRenderYawHead;

    public SkinningEntitiesBody(SkinningEntities skinningentities)
    {
        super(skinningentities);
    }

    @Override
    public void onUpdate()
//    public void updateRenderAngles()
    {
//        double d0 = this.skinningentities.posX - this.skinningentities.prevPosX;
//        double d1 = this.skinningentities.posZ - this.skinningentities.prevPosZ;
//
//        if (d0 * d0 + d1 * d1 > 2.500000277905201E-7D)
//        {
//            this.skinningentities.renderYawOffset = this.skinningentities.rotationYaw;
//            this.skinningentities.rotationYawHead = this.computeAngleWithBound(this.skinningentities.renderYawOffset, this.skinningentities.rotationYawHead, 75.0F);
//            this.prevRenderYawHead = this.skinningentities.rotationYawHead;
//            this.rotationTickCounter = 0;
//        }
//        else
//        {
//        if (this.skinningentities.getPassengers().isEmpty() || !(this.skinningentities.getPassengers().get(0) instanceof EntityLiving))
//        {
        float f = 75.0F;

        if (Math.abs(this.skinningentities.rotationYawHead - this.prevRenderYawHead) > 15.0F)
        {
            this.rotationTickCounter = 0;
            this.prevRenderYawHead = this.skinningentities.rotationYawHead;
        }
        else
        {
            ++this.rotationTickCounter;
            int i = 10;

            if (this.rotationTickCounter > i)
            {
                f = Math.max(1.0F - (float)(this.rotationTickCounter - i) / 10.0F, 0.0F) * 75.0F;
            }
        }

        this.skinningentities.renderYawOffset = this.computeAngleWithBound(this.skinningentities.rotationYawHead, this.skinningentities.renderYawOffset, f);
//        }
//        }
    }

    public float computeAngleWithBound(float f0, float f1, float f2)
    {
        float f = MathHelper.wrapDegrees(f0 - f1);

        if (f < -f2)
        {
            f = -f2;
        }

        if (f >= f2)
        {
            f = f2;
        }

        return f0 - f;
    }
}
