package com.nali.list.handlers;

import com.nali.ilol.entities.skinning.SkinningEntities;
import com.nali.list.container.PlayerContainer;
import com.nali.list.gui.PlayerGui;
import com.nali.list.messages.SkinningEntitiesClientMessage;
import com.nali.system.bytes.BytesReader;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
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
                    Entity entity = Minecraft.getMinecraft().player.getEntityWorld().getEntityByID(BytesReader.getInt(skinningentitiesclientmessage.data, i));
                    if (!(entity instanceof SkinningEntities))
                    {
                        entity = null;
                    }
                    SkinningEntities.CLIENT_ENTITIES_MAP.put(uuid, (SkinningEntities)entity);
//                    }
                }

                break;
            }
            case 1:
            {
                Minecraft minecraft = Minecraft.getMinecraft();
                minecraft.player.isDead = false;
                minecraft.player.deathTime = 0;
                minecraft.player.respawnPlayer();
                minecraft.player.setHealth(1.0F);
                minecraft.addScheduledTask(() ->
                {
                    minecraft.displayGuiScreen(null);
                });
                break;
            }
            case 2:
            {
                Minecraft minecraft = Minecraft.getMinecraft();
                minecraft.addScheduledTask(() ->
                {
                    minecraft.displayGuiScreen(new PlayerGui(new PlayerContainer()));
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