package com.nali.small.entities.skinning.hits;

import com.nali.list.messages.ServerMessage;
import com.nali.list.netmethods.servermessage.DrinkMilk;
import com.nali.list.netmethods.servermessage.Eat;
import com.nali.networks.NetworksRegistry;
import com.nali.small.entities.memory.client.ClientEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.system.bytes.BytesWriter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SkinningEntitiesEat extends SkinningEntitiesHits
{
    public SkinningEntitiesEat(SkinningEntities skinningentities)
    {
        super(skinningentities);
    }

    @Override
    public void run(Entity player_entity, AxisAlignedBB axisalignedbb)
    {
        ClientEntitiesMemory cliententitiesmemory = (ClientEntitiesMemory)this.skinningentities.bothentitiesmemory;
        Item item = ((EntityLivingBase)player_entity).getHeldItemMainhand().getItem();
        boolean milk_bucket = item == Items.MILK_BUCKET;
//        boolean eat = item instanceof ItemFood;

        if (item instanceof ItemFood || milk_bucket)
        {
            byte[] byte_array;
            if (milk_bucket)
            {
                byte_array = new byte[1 + 16];
                byte_array[0] = DrinkMilk.ID;
            }
            else
            {
                byte_array = new byte[1 + 16 + 4 + 4 + 4];
                byte_array[0] = Eat.ID;
                BytesWriter.set(byte_array, (float)(axisalignedbb.maxX + (axisalignedbb.minX - axisalignedbb.maxX) / 2.0D), 1 + 16);
                BytesWriter.set(byte_array, (float)(axisalignedbb.maxY + (axisalignedbb.minY - axisalignedbb.maxY) / 2.0D), 1 + 16 + 4);
                BytesWriter.set(byte_array, (float)(axisalignedbb.maxZ + (axisalignedbb.minZ - axisalignedbb.maxZ) / 2.0D), 1 + 16 + 4 + 4);
            }

            BytesWriter.set(byte_array, cliententitiesmemory.uuid, 1);
            NetworksRegistry.I.sendToServer(new ServerMessage(byte_array));
        }
    }

    @Override
    public boolean should(Entity player_entity, AxisAlignedBB axisalignedbb)
    {
        Item item = ((EntityLivingBase)player_entity).getHeldItemMainhand().getItem();
        return item instanceof ItemFood || item == Items.MILK_BUCKET;
    }
}
