package com.nali.list.netmethods.servermessage;

import com.nali.list.capabilitiesserializations.SmallSakuraSerializations;
import com.nali.list.capabilitiestypes.SmallSakuraTypes;
import com.nali.list.messages.ServerMessage;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.system.bytes.BytesReader;
import net.minecraft.entity.player.EntityPlayerMP;

import static com.nali.list.handlers.ServerHandler.canPass;
import static com.nali.small.entities.memory.server.ServerEntitiesMemory.ENTITIES_MAP;

public class TP
{
    public static byte ID;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        SkinningEntities skinningentities = ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));
        if (skinningentities != null && canPass(skinningentities, entityplayermp))
        {
            SmallSakuraTypes smallsakuratypes = entityplayermp.getCapability(SmallSakuraSerializations.SMALLSAKURATYPES_CAPABILITY, null);
            int value = smallsakuratypes.get();

            if (value >= 1)
            {
                smallsakuratypes.set(value - 1);
                int dimension = skinningentities.world.provider.getDimension();
                if (dimension != entityplayermp.world.provider.getDimension())
                {
                    entityplayermp.changeDimension(dimension, (x, y, z) ->
                    {
                        entityplayermp.setPositionAndUpdate(skinningentities.posX, skinningentities.posY, skinningentities.posZ);
                    });
                }
                else
                {
                    entityplayermp.setPositionAndUpdate(skinningentities.posX, skinningentities.posY, skinningentities.posZ);
                }
                //                        DataParameter<Byte> byte_dataparameter = skinningentities.getByteDataParameterArray()[id];
                //                        entitydatamanager.set(byte_dataparameter, entitydatamanager.get(byte_dataparameter) == 1 ? (byte)0 : 1);
                ////                        skinningentities.changeDimension(entityplayermp.world.provider.getDimension());
                //                        skinningentities.world.removeEntity(skinningentities);
                //                        skinningentities.isDead = false;
                //                        entityplayermp.world.spawnEntity(skinningentities);
                //                        entityplayermp.connection.sendPacket(new SPacketSpawnObject(skinningentities, EntityList.getID(skinningentities.getClass())));
                //
                //                        skinningentities.setPositionAndRotation(entityplayermp.posX, entityplayermp.posY, entityplayermp.posZ, skinningentities.rotationYaw, skinningentities.rotationPitch);
            }
        }
    }
}
