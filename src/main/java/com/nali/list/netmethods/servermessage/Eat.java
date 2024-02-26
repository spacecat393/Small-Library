package com.nali.list.netmethods.servermessage;

import com.mojang.authlib.GameProfile;
import com.nali.list.messages.ClientMessage;
import com.nali.list.messages.ServerMessage;
import com.nali.small.entities.memory.server.ServerEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.mixin.IMixinItemFood;
import com.nali.small.networks.NetworksRegistry;
import com.nali.system.bytes.BytesReader;
import com.nali.system.bytes.BytesWriter;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;

import java.util.Collection;

import static com.nali.small.entities.memory.server.ServerEntitiesMemory.ENTITIES_MAP;

public class Eat
{
    public static byte ID = 17;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        SkinningEntities skinningentities = ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));
        ItemStack itemstack = entityplayermp.getHeldItemMainhand();
        if (skinningentities != null && skinningentities.canEat() && itemstack.getItem() instanceof ItemFood)
        {
            ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)skinningentities.bothentitiesmemory;
            ItemFood itemfood = (ItemFood)itemstack.getItem();
            FakePlayer fakeplayer = new FakePlayer((WorldServer)skinningentities.world, new GameProfile(null, "!"));
            fakeplayer.connection = entityplayermp.connection;
            ((IMixinItemFood)itemfood).GOonFoodEaten(itemstack, skinningentities.world, fakeplayer);
            Collection<PotionEffect> potioneffect_collection = fakeplayer.getActivePotionEffects();
            for (PotionEffect potioneffect : potioneffect_collection)
            {
                skinningentities.addPotionEffect(potioneffect);
            }
            serverentitiesmemory.main_work_byte_array[serverentitiesmemory.workbytes.ON_EAT()] = 1;
            skinningentities.world.spawnEntity(new EntityXPOrb(skinningentities.world, skinningentities.posX, skinningentities.posY, skinningentities.posZ, 10));
            skinningentities.heal(itemfood.getHealAmount(itemstack) + itemfood.getSaturationModifier(itemstack));
            itemstack.shrink(1);

//                        Vec3d view_vec3d = skinningentities.getLookVec().scale(0.25F);
            skinningentities.playSound(SoundEvents.ENTITY_GENERIC_EAT, skinningentities.getSoundVolume(), skinningentities.getSoundPitch());
            byte[] byte_array = new byte[1 + 4 + 4 + 4 + 4];
            byte_array[0] = 9;
            BytesWriter.set(byte_array, BytesReader.getFloat(servermessage.data, 1 + 16), 1);
            BytesWriter.set(byte_array, BytesReader.getFloat(servermessage.data, 1 + 16 + 4), 1 + 4);
            BytesWriter.set(byte_array, BytesReader.getFloat(servermessage.data, 1 + 16 + 4 + 4), 1 + 4 + 4);
            BytesWriter.set(byte_array, Item.getIdFromItem(itemfood), 1 + 4 + 4 + 4);
            NetworksRegistry.I.sendToAll(new ClientMessage(byte_array));
        }
    }
}
