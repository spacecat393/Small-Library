package com.nali.ilol.entities.skinning.ai;

import com.nali.ilol.entities.skinning.SkinningEntities;

public class SkinningEntitiesJump extends SkinningEntitiesAI
{
    public boolean isJumping;

    public SkinningEntitiesJump(SkinningEntities skinningentities)
    {
        super(skinningentities);
    }

    public void setJumping()
    {
        this.isJumping = true;
    }

    @Override
    public void onUpdate()
    {
        this.skinningentities.setJumping(this.isJumping);
        this.isJumping = false;
    }
}
