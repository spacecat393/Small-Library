package com.nali.list.netmethods.servermessage;

import com.nali.list.messages.ServerMessage;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.system.bytes.BytesReader;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.WorldServer;

public class Pat
{
    public static byte ID = 16;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        SkinningEntities skinningentities = SkinningEntities.SERVER_ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));
        if (skinningentities != null)
        {
            skinningentities.main_server_work_byte_array[skinningentities.skinningentitiesbytes.ON_PAT()] = 1;
            ((WorldServer)skinningentities.world).spawnParticle(EnumParticleTypes.HEART, skinningentities.posX, BytesReader.getFloat(servermessage.data, 1 + 16), skinningentities.posZ, 1, 0.0D, 0.0D, 0.0D, 0.0D);
//                        skinningentities.world.spawnEntity(new EntityXPOrb(skinningentities.world, skinningentities.posX, skinningentities.posY, skinningentities.posZ, 10));
        }
    }
}
