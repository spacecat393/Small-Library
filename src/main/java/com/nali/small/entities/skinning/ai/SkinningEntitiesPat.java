package com.nali.small.entities.skinning.ai;

import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.networks.NetworksRegistry;
import com.nali.list.messages.SkinningEntitiesServerMessage;
import com.nali.system.bytes.BytesWriter;

public class SkinningEntitiesPat extends SkinningEntitiesAI
{
    public byte pat_time;

    public SkinningEntitiesPat(SkinningEntities skinningentities)
    {
        super(skinningentities);
        this.pat_time = (byte)this.skinningentities.getRNG().nextInt(16);
    }

    @Override
    public void onUpdate()
    {
//                    ItemStack itemstack = entityplayer.getHeldItem(enumhand);
////                    this.getEntityData().set(this.getByteEntityDataAccessorArray()[2], (byte)5);
////
////                    if (itemstack.getItem() == ItemsRegistry.HAIRBRUSH_ITEM_REGISTRYOBJECT.get())
////                    {
        if (--this.pat_time <= 0)
        {
            this.pat_time = (byte)this.skinningentities.getRNG().nextInt(16);

            byte[] byte_array = new byte[1 + 16 + 4];
            byte_array[0] = 16;
            BytesWriter.set(byte_array, this.skinningentities.client_uuid, 1);
            BytesWriter.set(byte_array, (float)this.skinningentities.getEntityBoundingBox().maxY, 1 + 16);
            NetworksRegistry.I.sendToServer(new SkinningEntitiesServerMessage(byte_array));

//            this.skinningentities.server_work_byte_array[this.skinningentities.skinningentitiesbytes.ON_PAT()] = 1;
//            this.skinningentities.world.spawnEntity(new EntityXPOrb(this.skinningentities.world, this.skinningentities.posX, this.skinningentities.posY, this.skinningentities.posZ, 10));
//
//                            if (!entityplayer.isCreative())
//                            {
//                                itemstack.damageItem(1, entityplayer);
//                            }
        }
////                    }
    }
}
