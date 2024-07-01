//package com.nali.list.network.method.client;
//
//import com.nali.Nali;
//import com.nali.list.network.message.ClientMessage;
//import com.nali.small.entity.EntityLeInv;
//import com.nali.small.entity.IMixE;
//import com.nali.small.entity.memo.client.ClientE;
//import com.nali.small.entity.memo.client.ClientLe;
//import com.nali.system.bytes.ByteReader;
//import net.minecraft.client.Minecraft;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.EntityList;
//import net.minecraft.world.World;
//
//import java.lang.reflect.InvocationTargetException;
//import java.util.UUID;
//
//public class CSetSEMap
//{
//    public static byte ID;
//
//    public static void run(ClientMessage clientmessage)
//    {
//        ClientE.UUID_MAP.clear();
//        ClientE.C_MAP.clear();
//
//        for (int i = 1; i < clientmessage.data.length; i += 4)
//        {
//            UUID uuid = ByteReader.getUUID(clientmessage.data, i);
//            i += 16;
//
//            int list_id = ByteReader.getInt(clientmessage.data, i);
//            i += 4;
//
//            World world = Minecraft.getMinecraft().player.world;
//            Entity entity = world.getEntityByID(list_id);
//            ClientE cliente = null;
//            if (entity instanceof IMixE)
//            {
//                IMixE imixe = (IMixE)entity;
//                cliente = imixe.getB();
//            }
//            else
//            {
//                int entity_id = ByteReader.getInt(clientmessage.data, i);
//                ClientE.UUID_MAP.put(list_id, uuid);
//
//                try
//                {
//                    entity = EntityList.getClassFromID(entity_id).getConstructor(World.class).newInstance(world);
//                    IMixE imixe = (IMixE)entity;
//                    cliente = imixe.getB();
//                    cliententitiesmemory.fake = true;
//                    cliententitiesmemory.uuid = uuid;
////                            NBTTagCompound nbttagcompound = new NBTTagCompound();
////                            skinningentities.initWriteEntityToNBT(nbttagcompound);
////                            for (int ii = 0; ii < skinningentities.bothdata.MaxPart(); ++ii)
////                            {
////                                ((ObjectRender)skinningentities.client_object).texture_index_int_array[ii] = nbttagcompound.getInteger("int_" + ii);
////                            }
////                            skinningentities.initWriteEntityToNBT(nbttagcompound);
//                    cliententitiesmemory.initFakeFrame();
//                }
//                catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e)
//                {
//                    Nali.I.warn(e);
//                }
//            }
//            ClientE.C_MAP.put(uuid, cliente);
//        }
//    }
//}
