package com.nali.ilol.entities.skinning;

import com.nali.system.bytes.BytesReader;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.UUID;

public class SkinningEntitiesClientHandler implements IMessageHandler<SkinningEntitiesClientMessage, IMessage>
{
    @Override
    public IMessage onMessage(SkinningEntitiesClientMessage skinningentitiesclientmessage, MessageContext messagecontext)
    {
        switch (skinningentitiesclientmessage.data[0])
        {
            case 0:
            {
                for (int i = 1; i < skinningentitiesclientmessage.data.length; i += 16)
                {
                    UUID uuid = BytesReader.getUUID(skinningentitiesclientmessage.data, i);

                    if (!SkinningEntities.CLIENT_ENTITIES_MAP.containsKey(uuid))
                    {
                        SkinningEntities.CLIENT_ENTITIES_MAP.put(uuid, null);
                    }
                }

                break;
            }
            case 1:
            {
                Minecraft.getMinecraft().player.isDead = false;
                Minecraft.getMinecraft().player.deathTime = 0;
                Minecraft.getMinecraft().player.respawnPlayer();
                break;
            }
            default:
            {
                break;
            }
        }

        return null;
    }
}