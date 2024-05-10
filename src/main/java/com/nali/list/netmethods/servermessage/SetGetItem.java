package com.nali.list.netmethods.servermessage;

import com.nali.list.capabilitiesserializations.SmallSakuraSerializations;
import com.nali.list.capabilitiestypes.SmallSakuraTypes;
import com.nali.list.messages.ServerMessage;
import com.nali.small.entities.memory.server.ServerEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.entities.skinning.ai.SkinningEntitiesGetItem;
import com.nali.system.bytes.BytesReader;
import net.minecraft.entity.player.EntityPlayerMP;

import static com.nali.list.handlers.ServerHandler.canPass;
import static com.nali.list.netmethods.servermessage.FetchGetItem.fetch;
import static com.nali.small.entities.memory.server.ServerEntitiesMemory.ENTITIES_MAP;

public class SetGetItem
{
    public static byte ID;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        SkinningEntities skinningentities = ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));
        if (skinningentities != null && canPass(skinningentities, entityplayermp))
        {
            ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)skinningentities.bothentitiesmemory;
            SkinningEntitiesGetItem skinningentitiesgetitem = serverentitiesmemory.entitiesaimemory.skinningentitiesgetitem;
            float id = BytesReader.getFloat(servermessage.data, 1 + 16);
            float f = BytesReader.getFloat(servermessage.data, 1 + 16 + 4);

            SmallSakuraTypes smallsakuratypes = entityplayermp.getCapability(SmallSakuraSerializations.SMALLSAKURATYPES_CAPABILITY, null);
            byte value = smallsakuratypes.get();

            if (id >= 2)
            {
                if (id == 2.1F)
                {
                    if (f == 1)
                    {
                        if (value >= 1)
                        {
                            smallsakuratypes.set((byte)(value - 1));
                            skinningentitiesgetitem.flag |= 4;
                        }
                    }
                    else
                    {
                        skinningentitiesgetitem.flag &= 255-4;
                    }
                }
                else if (id == 2.2F)
                {
                    if (f == 1)
                    {
                        if (value >= 1)
                        {
                            smallsakuratypes.set((byte)(value - 1));
                            skinningentitiesgetitem.flag |= 64;
                        }
                    }
                    else
                    {
                        skinningentitiesgetitem.flag &= 255-64;
                    }
                }
                else if (id == 2.3F)
                {
                    if (f == 1)
                    {
                        if (value >= 1)
                        {
                            smallsakuratypes.set((byte)(value - 1));
                            skinningentitiesgetitem.flag |= 16;
                        }
                    }
                    else
                    {
                        skinningentitiesgetitem.flag &= 255-16;
                    }
                }
            }
            else if (id >= 1)
            {
                if (id == 1.1F)
                {
                    if (f == 1)
                    {
                        if (value >= 1)
                        {
                            smallsakuratypes.set((byte)(value - 1));
                            skinningentitiesgetitem.flag |= 2;
                        }
                    }
                    else
                    {
                        skinningentitiesgetitem.flag &= 255-2;
                    }
                }
                else if (id == 1.2F)
                {
                    if (f == 1)
                    {
                        if (value >= 1)
                        {
                            smallsakuratypes.set((byte)(value - 1));
                            skinningentitiesgetitem.flag |= 32;
                        }
                    }
                    else
                    {
                        skinningentitiesgetitem.flag &= 255-32;
                    }
                }
                else if (id == 1.3F)
                {
                    if (f == 1)
                    {
                        if (value >= 1)
                        {
                            smallsakuratypes.set((byte)(value - 1));
                            skinningentitiesgetitem.flag |= 8;
                        }
                    }
                    else
                    {
                        skinningentitiesgetitem.flag &= 255-8;
                    }
                }
            }

            fetch(entityplayermp, skinningentitiesgetitem);
        }
    }
}
