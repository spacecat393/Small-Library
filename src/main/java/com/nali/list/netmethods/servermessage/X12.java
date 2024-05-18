package com.nali.list.netmethods.servermessage;

import com.nali.list.capabilitiesserializations.SmallSakuraSerializations;
import com.nali.list.capabilitiestypes.SmallSakuraTypes;
import com.nali.list.items.SmallBox;
import com.nali.list.messages.ServerMessage;
import com.nali.small.Small;
import com.nali.small.config.MyConfig;
import com.nali.small.entities.EntitiesRegistryHelper;
import com.nali.small.entities.memory.server.ServerEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import static com.nali.small.items.ItemsRegistryHelper.ITEM_ARRAY;

public class X12
{
    public static byte ID;

    public static byte GUARANTEE;
    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        if (MyConfig.SERVER.player_rng)
        {
            Random random = entityplayermp.getRNG();
            SmallSakuraTypes smallsakuratypes = entityplayermp.getCapability(SmallSakuraSerializations.SMALLSAKURATYPES_CAPABILITY, null);
            byte value = smallsakuratypes.get();
            if (value >= 12)
            {
                smallsakuratypes.set((byte)(value - 12));

                if (GUARANTEE >= 10)
                {
                    spawnX12(random, entityplayermp);
                    GUARANTEE = 0;
                }
                else
                {
                    ++GUARANTEE;
                }

                if (random.nextInt(7) == 0)
                {
                    spawnX12(random, entityplayermp);
                }
                else
                {
                    entityplayermp.dropItem(Items.STICK, 1);
                }
            }
        }
    }

    public static void spawnX12(Random random, EntityPlayerMP entityplayermp)
    {
        try
        {
            ItemStack itemstack = new ItemStack(ITEM_ARRAY[SmallBox.ID]);
            int id = random.nextInt(EntitiesRegistryHelper.ENTITIES_CLASS_LIST.size());
            SkinningEntities skinningentities = (SkinningEntities)EntitiesRegistryHelper.ENTITIES_CLASS_LIST.get(id).getConstructor(World.class).newInstance(entityplayermp.world);
            ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)skinningentities.bothentitiesmemory;
            serverentitiesmemory.owner_uuid = entityplayermp.getUniqueID();
            SmallBox.putToBox(skinningentities, itemstack);
            entityplayermp.entityDropItem(itemstack, 0.0F);
        }
        catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e)
        {
            Small.error(e);
        }
    }
}
