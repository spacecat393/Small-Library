package com.nali.list.network.method.server;

import com.nali.list.entity.ai.AIEPat;
import com.nali.list.network.message.ServerMessage;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.system.bytes.ByteReader;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumParticleTypes;

import static com.nali.small.entity.memo.server.ServerE.S_MAP;

public class Pat
{
    public static byte ID;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        ServerE servere = S_MAP.get(ByteReader.getUUID(servermessage.data, 1));

        if (servere != null)
        {
            Entity entity = servere.i.getE();
            servere.a.call(AIEPat.ID);
            servere.worldserver.spawnParticle(EnumParticleTypes.HEART, entity.posX, ByteReader.getFloat(servermessage.data, 1 + 16), entity.posZ, 1, 0.0D, 0.0D, 0.0D, 0.0D);
//                        skinningentities.world.spawnEntity(new EntityXPOrb(skinningentities.world, skinningentities.posX, skinningentities.posY, skinningentities.posZ, 10));
        }
    }
}
