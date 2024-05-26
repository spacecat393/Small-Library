package com.nali.list.netmethods.servermessage;

import com.nali.list.capabilitiesserializations.SmallSakuraSerializations;
import com.nali.list.capabilitiestypes.SmallSakuraTypes;
import com.nali.list.items.SmallBox;
import com.nali.list.messages.ServerMessage;
import com.nali.small.Small;
import com.nali.small.config.MyConfig;
import com.nali.small.entities.EntitiesRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import static com.nali.small.items.ItemsRegistry.ITEM_ARRAY;

public class X64
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

            if (value >= 64)
            {
                smallsakuratypes.set((byte)(value - 64));

                if (GUARANTEE >= 10)
                {
                    spawnX64(entityplayermp);
                    GUARANTEE = 0;
                }
                else
                {
                    ++GUARANTEE;
                }

                if (random.nextBoolean())
                {
                    entityplayermp.dropItem(Item.getItemById(entityplayermp.getRNG().nextInt(Item.REGISTRY.getKeys().size())), 1);
                }
                else
                {
                    spawnX64(entityplayermp);
                }
            }
        }
    }

    public static void spawnX64(EntityPlayerMP entityplayermp)
    {
        try
        {
            ItemStack itemstack = new ItemStack(ITEM_ARRAY[SmallBox.ID]);
            int id = entityplayermp.getRNG().nextInt(EntitiesRegistry.ENTITY_KEY_ARRAY.length);
            Constructor constructor = ((Class) EntitiesRegistry.ENTITY_KEY_ARRAY[id]).getConstructor(World.class);
            Entity entity = (Entity)constructor.newInstance(entityplayermp.world);

            if (!(entity instanceof EntityPlayer))
            {
                SmallBox.putToBox(entity, itemstack);
            }

            entityplayermp.entityDropItem(itemstack, 0.0F);
        }
        catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e)
        {
            Small.error(e);
        }
    }
}
