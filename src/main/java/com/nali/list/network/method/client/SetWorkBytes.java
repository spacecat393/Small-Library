//package com.nali.list.network.method.client;
//
//import com.nali.list.network.message.ClientMessage;
//import com.nali.small.entity.memo.client.ClientLe;
//import com.nali.small.entity.EntityLeInv;
//import com.nali.system.bytes.BytesReader;
//import net.minecraft.client.Minecraft;
//import net.minecraft.entity.Entity;
//
//import java.util.UUID;
//
//import static com.nali.small.entity.memo.client.ClientLe.ENTITIES_MAP;
//import static com.nali.small.entity.memo.client.ClientLe.FAKE_ENTITIES_MAP;
//
//public class SetWorkBytes
//{
//    public static byte ID;
//
//    public static void run(ClientMessage clientmessage)
//    {
//        int id = BytesReader.getInt(clientmessage.data, 1);
//        Entity entity = Minecraft.getMinecraft().world.getEntityByID(id);
//        if (!(entity instanceof EntityLeInv))
//        {
//            UUID uuid = FAKE_ENTITIES_MAP.get(id);
//            entity = ENTITIES_MAP.get(uuid);
//        }
//
//        if (entity instanceof EntityLeInv)
//        {
////            SkinningEntities skinningentities = (SkinningEntities)entity;
//            ClientLe cliententitiesmemory = (ClientLe)((EntityLeInv)entity).bothentitiesmemory;
//            System.arraycopy(clientmessage.data, 1 + 4, cliententitiesmemory.work_byte_array, 0, cliententitiesmemory.work_byte_array.length);
//        }
//    }
//}
