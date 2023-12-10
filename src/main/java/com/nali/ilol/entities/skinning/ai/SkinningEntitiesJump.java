package com.nali.ilol.entities.skinning.ai;

import com.nali.ilol.entities.skinning.SkinningEntities;

public class SkinningEntitiesJump
{
    public SkinningEntities skinningentities;
    public boolean isJumping;

    public SkinningEntitiesJump(SkinningEntities skinningentities)
    {
        this.skinningentities = skinningentities;
    }

    public void setJumping()
    {
        this.isJumping = true;
    }

    public void onUpdate()
    {
        this.skinningentities.setJumping(this.isJumping);
        this.isJumping = false;
    }
}
