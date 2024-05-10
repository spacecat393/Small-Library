package com.nali.list.netmethods.servermessage;

import com.nali.list.capabilitiesserializations.SmallSakuraSerializations;
import com.nali.list.capabilitiestypes.SmallSakuraTypes;
import com.nali.list.messages.ServerMessage;
import com.nali.small.entities.memory.server.ServerEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.entities.skinning.ai.SkinningEntitiesArea;
import com.nali.system.bytes.BytesReader;
import net.minecraft.entity.player.EntityPlayerMP;

import static com.nali.list.handlers.ServerHandler.canPass;
import static com.nali.list.netmethods.servermessage.FetchArea.fetch;
import static com.nali.small.entities.memory.server.ServerEntitiesMemory.ENTITIES_MAP;

public class SetArea
{
    public static byte ID;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        SkinningEntities skinningentities = ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));
        if (skinningentities != null && canPass(skinningentities, entityplayermp))
        {
            ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)skinningentities.bothentitiesmemory;
            SkinningEntitiesArea skinningentitiesarea = serverentitiesmemory.entitiesaimemory.skinningentitiesarea;
            float id = BytesReader.getFloat(servermessage.data, 1 + 16);
            float x = BytesReader.getFloat(servermessage.data, 1 + 16 + 4);

            SmallSakuraTypes smallsakuratypes = entityplayermp.getCapability(SmallSakuraSerializations.SMALLSAKURATYPES_CAPABILITY, null);
            byte value = smallsakuratypes.get();

            if (id == 0.1F)
            {
                if (x == 1)
                {
                    if (value >= 1)
                    {
                        smallsakuratypes.set((byte)(value - 1));
                        skinningentitiesarea.flag |= 4;
                    }
                }
                else
                {
                    skinningentitiesarea.flag &= 255 - 4;
                }
            }
            else if (id == 0.2F)
            {
                if (x == 1)
                {
                    if (value >= 1)
                    {
                        smallsakuratypes.set((byte)(value - 1));
                        skinningentitiesarea.flag |= 8;
                    }
                }
                else
                {
                    skinningentitiesarea.flag &= 255 - 8;
                }
            }
            else if (id == 0.3F)
            {
                if (x == 1)
                {
                    if (value >= 1)
                    {
                        smallsakuratypes.set((byte)(value - 1));
                        skinningentitiesarea.flag |= 128;
                    }
                }
                else
                {
                    skinningentitiesarea.flag &= 255 - 128;
                }
            }
            else if (id == 1.1F)
            {
                if (x == 1)
                {
                    if (value >= 1)
                    {
                        smallsakuratypes.set((byte)(value - 1));
                        skinningentitiesarea.flag |= 16;
                    }
                }
                else
                {
                    skinningentitiesarea.flag &= 255 - 16;
                }
            }
            else if (id == 1.2F)
            {
                if (x == 1)
                {
                    if (value >= 1)
                    {
                        smallsakuratypes.set((byte)(value - 1));
                        skinningentitiesarea.flag |= 32;
                    }
                }
                else
                {
                    skinningentitiesarea.flag &= 255 - 32;
                }
            }
            else if (id == 1.3F)
            {
                if (x == 1)
                {
                    if (value >= 1)
                    {
                        smallsakuratypes.set((byte)(value - 1));
                        skinningentitiesarea.flag |= 64;
                    }
                }
                else
                {
                    skinningentitiesarea.flag &= 255 - 64;
                }
            }

            fetch(entityplayermp, skinningentitiesarea);
        }
    }
}
