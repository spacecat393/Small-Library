package com.nali.list.handlers;

import com.nali.ilol.entities.skinning.SkinningEntities;
import com.nali.list.messages.SkinningEntitiesClientMessage;
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
                SkinningEntities.CLIENT_ENTITIES_MAP.clear();

                for (int i = 1; i < skinningentitiesclientmessage.data.length; i += 4)
                {
                    UUID uuid = BytesReader.getUUID(skinningentitiesclientmessage.data, i);

//                    if (!SkinningEntities.CLIENT_ENTITIES_MAP.containsKey(uuid))
//                    {
                    i += 16;
                    SkinningEntities.CLIENT_ENTITIES_MAP.put(uuid, (SkinningEntities)Minecraft.getMinecraft().player.getEntityWorld().getEntityByID(BytesReader.getInt(skinningentitiesclientmessage.data, i)));
//                    }
                }

                break;
            }
            case 1:
            {
                Minecraft minecraft = Minecraft.getMinecraft();
                minecraft.player.isDead = false;
                minecraft.getMinecraft().player.deathTime = 0;
                minecraft.getMinecraft().player.respawnPlayer();
                minecraft.getMinecraft().player.setHealth(1.0F);
                minecraft.getMinecraft().addScheduledTask(() ->
                {
                    minecraft.displayGuiScreen(null);
                });
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