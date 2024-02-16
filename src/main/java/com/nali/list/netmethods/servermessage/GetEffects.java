package com.nali.list.netmethods.servermessage;

import com.nali.list.messages.ClientMessage;
import com.nali.list.messages.ServerMessage;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.networks.NetworksRegistry;
import com.nali.system.bytes.BytesReader;
import com.nali.system.bytes.BytesWriter;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import java.util.Collection;

import static com.nali.list.handlers.ServerHandler.canPass;

public class GetEffects
{
    public static int ID = 18;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        SkinningEntities skinningentities = SkinningEntities.SERVER_ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));
        if (skinningentities != null && canPass(skinningentities, entityplayermp))
        {
            Collection<PotionEffect> potioneffect_collection = skinningentities.getActivePotionEffects();
            byte[] byte_array = new byte[1 + 4 + (potioneffect_collection.size() * 3 * 4)];
            byte_array[0] = 7;
            BytesWriter.set(byte_array, skinningentities.getEntityId(), 1);
            int index = 1 + 4;
            for (PotionEffect potioneffect : potioneffect_collection)
            {
                BytesWriter.set(byte_array, Potion.getIdFromPotion(potioneffect.getPotion()), index);
                index += 4;
                BytesWriter.set(byte_array, potioneffect.getDuration(), index);
                index += 4;
                BytesWriter.set(byte_array, potioneffect.getAmplifier(), index);
                index += 4;
            }
            NetworksRegistry.I.sendTo(new ClientMessage(byte_array), entityplayermp);
        }

        //sync looking
//                    Entity entity = Minecraft.getMinecraft().world.getEntityByID(BytesReader.getInt(servermessage.data, 1));
////                    SkinningEntities skinningentities = SkinningEntities.SERVER_ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));
//                    if (entity instanceof SkinningEntities)
//                    {
//                        SkinningEntities skinningentities = (SkinningEntities)entity;
//                        byte[] byte_array = new byte[1 + 4 + 4 + 4 + 4 + 4];
//                        byte_array[0] = 7;
//                        BytesWriter.set(byte_array, skinningentities.getEntityId(), 1);
//                        BytesWriter.set(byte_array, skinningentities.rotationYaw, 1 + 4);
//                        BytesWriter.set(byte_array, skinningentities.rotationPitch, 1 + 4 + 4);
//                        BytesWriter.set(byte_array, skinningentities.rotationYawHead, 1 + 4 + 4 + 4);
//                        BytesWriter.set(byte_array, skinningentities.renderYawOffset, 1 + 4 + 4 + 4 + 4);
//                        NetworksRegistry.I.sendTo(new SkinningEntitiesClientMessage(byte_array), entityplayermp);
//                    }
    }
}
