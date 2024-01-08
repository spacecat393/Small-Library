package com.nali.list.handlers;

import com.nali.ilol.ILOL;
import com.nali.ilol.entities.skinning.SkinningEntities;
import com.nali.ilol.gui.OpenGUIHelper;
import com.nali.list.messages.SkinningEntitiesClientMessage;
import com.nali.render.ObjectRender;
import com.nali.render.SkinningRender;
import com.nali.system.bytes.BytesReader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.lang.reflect.InvocationTargetException;
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
                SkinningEntities.FAKE_CLIENT_ENTITIES_MAP.clear();

                for (int i = 1; i < skinningentitiesclientmessage.data.length; i += 4)
                {
                    UUID uuid = BytesReader.getUUID(skinningentitiesclientmessage.data, i);
                    i += 16;

                    int list_id = BytesReader.getInt(skinningentitiesclientmessage.data, i);
                    i += 4;

                    World world = Minecraft.getMinecraft().player.getEntityWorld();
                    Entity entity = world.getEntityByID(list_id);
                    if (!(entity instanceof SkinningEntities))
                    {
                        int entity_id = BytesReader.getInt(skinningentitiesclientmessage.data, i);
                        SkinningEntities.FAKE_CLIENT_ENTITIES_MAP.put(list_id, uuid);

                        try
                        {
                            entity = EntityList.getClassFromID(entity_id).getConstructor(World.class).newInstance(world);
                            SkinningEntities skinningentities = (SkinningEntities)entity;
                            skinningentities.fake = true;
                            skinningentities.client_uuid = uuid;
                            NBTTagCompound nbttagcompound = new NBTTagCompound();
                            skinningentities.initWriteEntityToNBT(nbttagcompound);
                            for (int ii = 0; ii < skinningentities.bothdata.MaxPart(); ++ii)
                            {
                                ((ObjectRender)skinningentities.client_object).texture_index_int_array[ii] = nbttagcompound.getInteger("int_" + ii);
                            }

                            String key = "int_" + skinningentities.bothdata.MaxPart();
                            if (nbttagcompound.hasKey(key))
                            {
                                ((SkinningRender)skinningentities.client_object).frame_int_array[0] = nbttagcompound.getInteger(key);
                            }
                        }
                        catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e)
                        {
                            ILOL.error(e);
                        }
                    }
                    SkinningEntities.CLIENT_ENTITIES_MAP.put(uuid, (SkinningEntities)entity);
                }

                break;
            }
            case 1:
            {
                Minecraft minecraft = Minecraft.getMinecraft();
                EntityPlayerSP entityplayersp = minecraft.player;
                entityplayersp.isDead = false;
                entityplayersp.deathTime = 0;
                entityplayersp.respawnPlayer();
                entityplayersp.setHealth(entityplayersp.getMaxHealth());
                minecraft.addScheduledTask(() ->
                {
                    minecraft.displayGuiScreen(null);
                });
                break;
            }
            case 2:
            {
                OpenGUIHelper.callPlayerGUI();
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