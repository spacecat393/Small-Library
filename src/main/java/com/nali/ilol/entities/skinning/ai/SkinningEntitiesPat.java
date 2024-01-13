package com.nali.ilol.entities.skinning.ai;

import com.nali.ilol.entities.skinning.SkinningEntities;
import net.minecraft.entity.item.EntityXPOrb;

public class SkinningEntitiesPat extends SkinningEntitiesAI
{
    public byte pat_time;

    public SkinningEntitiesPat(SkinningEntities skinningentities)
    {
        super(skinningentities);
    }

    @Override
    public void onUpdate()
    {
        if (!this.skinningentities.getEntityWorld().isRemote)
        {
//                    ItemStack itemstack = entityplayer.getHeldItem(enumhand);
////                    this.getEntityData().set(this.getByteEntityDataAccessorArray()[2], (byte)5);
////
////                    if (itemstack.getItem() == ItemsRegistry.HAIRBRUSH_ITEM_REGISTRYOBJECT.get())
////                    {
            if (--this.pat_time <= 0)
            {
                this.pat_time = (byte)this.skinningentities.getRNG().nextInt(16);
                this.skinningentities.server_work_byte_array[this.skinningentities.skinningentitiesbytes.ON_PAT()] = 1;
                this.skinningentities.getEntityWorld().spawnEntity(new EntityXPOrb(this.skinningentities.getEntityWorld(), this.skinningentities.posX, this.skinningentities.posY, this.skinningentities.posZ, 10));
//
//                            if (!entityplayer.isCreative())
//                            {
//                                itemstack.damageItem(1, entityplayer);
//                            }
            }
////                    }
        }
    }
}
