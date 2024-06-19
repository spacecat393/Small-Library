package com.nali.list.network.method.server;

import com.nali.list.network.message.ClientMessage;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.client.PlaySound;
import com.nali.list.network.method.client.SetWorkBytes;
import com.nali.list.network.handler.ServerHandler;
import com.nali.networks.NetworksRegistry;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.EntityLeInv;
import com.nali.system.bytes.BytesReader;
import com.nali.system.bytes.BytesWriter;
import net.minecraft.entity.player.EntityPlayerMP;

import java.util.UUID;

import static com.nali.small.entity.memo.server.ServerE.ENTITIES_MAP;

public class SetWorkByte
{
    public static byte ID;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        UUID uuid = BytesReader.getUUID(servermessage.data, 1);
        EntityLeInv skinningentities = ENTITIES_MAP.get(uuid);

        if (skinningentities != null && ServerHandler.canPass(skinningentities, entityplayermp))
        {
            ServerE serverentitiesmemory = (ServerE)skinningentities.bothentitiesmemory;
//            int id = BytesReader.getInt(servermessage.data, 17);

            byte id = servermessage.data[17];
            byte index = (byte)(id / 8);
            byte bit = (byte)(id % 8);

            {
                byte[] byte_array = new byte[1 + 4 + 4];
                byte_array[0] = PlaySound.ID;
                BytesWriter.set(byte_array, skinningentities.getEntityId(), 1);
                if (((serverentitiesmemory.main_work_byte_array[index] >> bit) & 1) == 1)
                {
                    serverentitiesmemory.statentitiesmemory.stat &= 255-4;
                    serverentitiesmemory.statentitiesmemory.stat |= 2;

                    BytesWriter.set(byte_array, skinningentities.bothentitiesmemory.sounds.SOFT_READY(), 1 + 4);
                }
                else
                {
                    serverentitiesmemory.statentitiesmemory.stat |= 4;
                    serverentitiesmemory.statentitiesmemory.stat &= 255-2;

                    BytesWriter.set(byte_array, skinningentities.bothentitiesmemory.sounds.HARD_READY(), 1 + 4);
                }
                NetworksRegistry.I.sendTo(new ClientMessage(byte_array), entityplayermp);
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
