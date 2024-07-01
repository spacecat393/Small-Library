//package com.nali.list.network.method.server;
//
//import com.nali.list.network.message.ClientMessage;
//import com.nali.list.network.message.ServerMessage;
//import com.nali.list.network.method.client.CSetEffect;
//import com.nali.network.NetworkRegistry;
//import com.nali.small.entity.EntityLeInv;
//import com.nali.system.bytes.ByteWriter;
//import com.nali.system.bytes.BytesReader;
//import net.minecraft.entity.player.EntityPlayerMP;
//import net.minecraft.potion.Potion;
//import net.minecraft.potion.PotionEffect;
//
//import java.util.Collection;
//
//import static com.nali.list.network.handler.ServerHandler.canPass;
//import static com.nali.small.entity.memo.server.ServerE.ENTITIES_MAP;
//
//public class SGetEffects
//{
//    public static byte ID;
//
//    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
//    {
//        EntityLeInv skinningentities = ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));
//        if (skinningentities != null && canPass(skinningentities, entityplayermp))
//        {
//            Collection<PotionEffect> potioneffect_collection = skinningentities.getActivePotionEffects();
//            byte[] byte_array = new byte[1 + 4 + (potioneffect_collection.size() * 3 * 4)];
//            byte_array[0] = CSetEffect.ID;
//            ByteWriter.set(byte_array, skinningentities.getEntityId(), 1);
//            int index = 1 + 4;
//            for (PotionEffect potioneffect : potioneffect_collection)
//            {
//                ByteWriter.set(byte_array, Potion.getIdFromPotion(potioneffect.getPotion()), index);
//                index += 4;
//                ByteWriter.set(byte_array, potioneffect.getDuration(), index);
//                index += 4;
//                ByteWriter.set(byte_array, potioneffect.getAmplifier(), index);
//                index += 4;
//            }
//            NetworkRegistry.I.sendTo(new ClientMessage(byte_array), entityplayermp);
//        }
//
//        //sync looking
////                    Entity entity = Minecraft.getMinecraft().world.getEntityByID(BytesReader.getInt(servermessage.data, 1));
//////                    SkinningEntities skinningentities = SkinningEntities.ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));
////                    if (entity instanceof SkinningEntities)
////                    {
////                        SkinningEntities skinningentities = (SkinningEntities)entity;
////                        byte[] byte_array = new byte[1 + 4 + 4 + 4 + 4 + 4];
////                        byte_array[0] = 7;
////                        BytesWriter.set(byte_array, skinningentities.getEntityId(), 1);
////                        BytesWriter.set(byte_array, skinningentities.rotationYaw, 1 + 4);
////                        BytesWriter.set(byte_array, skinningentities.rotationPitch, 1 + 4 + 4);
////                        BytesWriter.set(byte_array, skinningentities.rotationYawHead, 1 + 4 + 4 + 4);
////                        BytesWriter.set(byte_array, skinningentities.renderYawOffset, 1 + 4 + 4 + 4 + 4);
////                        NetworksRegistry.I.sendTo(new SkinningEntitiesClientMessage(byte_array), entityplayermp);
////                    }
//    }
//}
