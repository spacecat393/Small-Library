package com.nali.small.entities.skinning.works;

import com.nali.list.messages.ServerMessage;
import com.nali.small.entities.memory.ClientEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.networks.NetworksRegistry;
import com.nali.system.bytes.BytesWriter;

public class SkinningEntitiesPat extends SkinningEntitiesWork
{
    public byte pat_time;

    public SkinningEntitiesPat(SkinningEntities skinningentities)
    {
        super(skinningentities);
        this.pat_time = (byte)this.skinningentities.getRNG().nextInt(16);
    }

    @Override
    public void run()
    {
        ClientEntitiesMemory cliententitiesmemory = (ClientEntitiesMemory)this.skinningentities.bothentitiesmemory;
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
            BytesWriter.set(byte_array, cliententitiesmemory.uuid, 1);
            BytesWriter.set(byte_array, (float)this.skinningentities.getEntityBoundingBox().maxY, 1 + 16);
            NetworksRegistry.I.sendToServer(new ServerMessage(byte_array));

//            serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.ON_PAT()] = 1;
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
