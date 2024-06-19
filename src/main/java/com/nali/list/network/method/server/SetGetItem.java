package com.nali.list.network.method.server;

import com.nali.list.capability.serializable.SmallSakuraSerializable;
import com.nali.list.capability.type.SmallSakuraType;
import com.nali.list.network.message.ServerMessage;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.EntityLeInv;
import com.nali.list.entity.ai.AILeInvGetItem;
import com.nali.system.bytes.BytesReader;
import net.minecraft.entity.player.EntityPlayerMP;

import static com.nali.list.network.handler.ServerHandler.canPass;
import static com.nali.list.network.method.server.FetchGetItem.fetch;
import static com.nali.small.entity.memo.server.ServerE.ENTITIES_MAP;

public class SetGetItem
{
    public static byte ID;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        EntityLeInv skinningentities = ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));
        if (skinningentities != null && canPass(skinningentities, entityplayermp))
        {
            ServerE serverentitiesmemory = (ServerE)skinningentities.bothentitiesmemory;
            AILeInvGetItem skinningentitiesgetitem = serverentitiesmemory.entitiesaimemory.skinningentitiesgetitem;
            float id = BytesReader.getFloat(servermessage.data, 1 + 16);
            float f = BytesReader.getFloat(servermessage.data, 1 + 16 + 4);

            SmallSakuraType smallsakuratypes = entityplayermp.getCapability(SmallSakuraSerializable.SMALLSAKURATYPES_CAPABILITY, null);
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
