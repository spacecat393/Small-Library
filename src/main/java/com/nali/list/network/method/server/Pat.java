package com.nali.list.network.method.server;

import com.nali.list.network.message.ServerMessage;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.EntityLeInv;
import com.nali.system.bytes.BytesReader;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.WorldServer;

import static com.nali.small.entity.memo.server.ServerE.ENTITIES_MAP;

public class Pat
{
    public static byte ID;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        EntityLeInv skinningentities = ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));
        if (skinningentities != null)
        {
            ServerE serverentitiesmemory = (ServerE)skinningentities.bothentitiesmemory;
            if ((serverentitiesmemory.statentitiesmemory.stat & 1) != 1)
            {
                serverentitiesmemory.statentitiesmemory.stat ^= 1;
            }
            ((WorldServer)skinningentities.world).spawnParticle(EnumParticleTypes.HEART, skinningentities.posX, BytesReader.getFloat(servermessage.data, 1 + 16), skinningentities.posZ, 1, 0.0D, 0.0D, 0.0D, 0.0D);
//                        skinningentities.world.spawnEntity(new EntityXPOrb(skinningentities.world, skinningentities.posX, skinningentities.posY, skinningentities.posZ, 10));
        }
    }
}
