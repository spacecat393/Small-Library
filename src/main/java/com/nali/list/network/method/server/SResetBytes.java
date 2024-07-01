//package com.nali.list.network.method.server;
//
//import com.nali.list.network.message.ServerMessage;
//import com.nali.small.entity.memo.server.ServerE;
//import com.nali.small.entity.EntityLeInv;
//import com.nali.system.bytes.BytesReader;
//import net.minecraft.entity.player.EntityPlayerMP;
//
//import java.util.Arrays;
//
//import static com.nali.list.network.handler.ServerHandler.canPass;
//import static com.nali.small.entity.memo.server.ServerE.ENTITIES_MAP;
//
//public class SResetBytes
//{
//    public static byte ID;
//
//    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
//    {
//        EntityLeInv skinningentities = ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));
//        if (skinningentities != null && canPass(skinningentities, entityplayermp))
//        {
//            ServerE serverentitiesmemory = (ServerE)skinningentities.bothentitiesmemory;
//            Arrays.fill(serverentitiesmemory.main_work_byte_array, (byte)0);
//            serverentitiesmemory.initWorkBytes();
//        }
//    }
//}
