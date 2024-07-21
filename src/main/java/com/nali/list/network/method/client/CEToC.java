package com.nali.list.network.method.client;

import com.nali.list.network.message.ClientMessage;
import com.nali.small.entity.memo.client.ClientE;
import com.nali.system.bytes.ByteReader;

import static com.nali.small.entity.memo.client.ClientE.C_MAP;

public class CEToC
{
    public static byte ID;

    public static void run(ClientMessage clientmessage)
    {
        ClientE c = C_MAP.get(ByteReader.getUUID(clientmessage.data, 1));
        if (c != null)
        {
            c.dimension = ByteReader.getInt(clientmessage.data, 1 + 16);
            c.x = ByteReader.getInt(clientmessage.data, 1 + 16 + 4);
            c.y = ByteReader.getInt(clientmessage.data, 1 + 16 + 4 + 4);
            c.z = ByteReader.getInt(clientmessage.data, 1 + 16 + 4 + 4 + 4);
        }
    }
}
