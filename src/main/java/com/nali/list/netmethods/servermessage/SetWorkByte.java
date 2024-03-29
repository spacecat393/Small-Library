package com.nali.list.netmethods.servermessage;

import com.nali.list.messages.ClientMessage;
import com.nali.list.messages.ServerMessage;
import com.nali.list.netmethods.clientmessage.SetWorkBytes;
import com.nali.small.entities.memory.server.ServerEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.networks.NetworksRegistry;
import com.nali.system.bytes.BytesReader;
import com.nali.system.bytes.BytesWriter;
import net.minecraft.entity.player.EntityPlayerMP;

import java.util.UUID;

import static com.nali.list.handlers.ServerHandler.canPass;
import static com.nali.small.entities.memory.server.ServerEntitiesMemory.ENTITIES_MAP;

public class SetWorkByte
{
    public static byte ID = 1;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        UUID uuid = BytesReader.getUUID(servermessage.data, 1);
        SkinningEntities skinningentities = ENTITIES_MAP.get(uuid);

        if (skinningentities != null && canPass(skinningentities, entityplayermp))
        {
            ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)skinningentities.bothentitiesmemory;
//            int id = BytesReader.getInt(servermessage.data, 17);

            byte id = servermessage.data[17];
            byte index = (byte)(id / 8);
            byte bit = (byte)(id % 8);

            if (((serverentitiesmemory.main_work_byte_array[index] >> bit) & 1) == 1)
            {
                serverentitiesmemory.statentitiesmemory.stat |= 4;//Math.pow(2, 2)
                serverentitiesmemory.statentitiesmemory.stat &= 239;//255 - Math.pow(2, 4)
//                if ((serverentitiesmemory.statentitiesmemory.stat & 2) != 2)
//                {
//                    serverentitiesmemory.statentitiesmemory.stat ^= 2;
//                }
//
//                if ((serverentitiesmemory.statentitiesmemory.stat & 4) == 4)
//                {
//                    serverentitiesmemory.statentitiesmemory.stat ^= 4;
//                }
            }
            else
            {
                serverentitiesmemory.statentitiesmemory.stat &= 251;//255 - Math.pow(2, 2)
                serverentitiesmemory.statentitiesmemory.stat |= 16;//Math.pow(2, 4)
//                if ((serverentitiesmemory.statentitiesmemory.stat & 2) == 2)
//                {
//                    serverentitiesmemory.statentitiesmemory.stat ^= 2;
//                }
//
//                if ((serverentitiesmemory.statentitiesmemory.stat & 4) != 4)
//                {
//                    serverentitiesmemory.statentitiesmemory.stat ^= 4;
//                }
            }

            serverentitiesmemory.main_work_byte_array[index] ^= (byte)Math.pow(2, bit);

            byte[] byte_array = new byte[1 + 4 + serverentitiesmemory.main_work_byte_array.length];
            byte_array[0] = SetWorkBytes.ID;
            BytesWriter.set(byte_array, skinningentities.getEntityId(), 1);
            System.arraycopy(serverentitiesmemory.main_work_byte_array, 0, byte_array, 1 + 4, serverentitiesmemory.main_work_byte_array.length);
            NetworksRegistry.I.sendTo(new ClientMessage(byte_array), entityplayermp);
        }
    }
}
