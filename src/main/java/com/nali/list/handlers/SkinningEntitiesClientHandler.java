package com.nali.list.handlers;

import com.nali.ilol.ILOL;
import com.nali.ilol.entities.skinning.SkinningEntities;
import com.nali.list.container.PlayerContainer;
import com.nali.list.gui.PlayerGui;
import com.nali.list.messages.SkinningEntitiesClientMessage;
import com.nali.render.ObjectRender;
import com.nali.render.SkinningRender;
import com.nali.system.bytes.BytesReader;
import net.minecraft.client.Minecraft;
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

                for (int i = 1; i < skinningentitiesclientmessage.data.length; i += 4)
                {
                    UUID uuid = BytesReader.getUUID(skinningentitiesclientmessage.data, i);
                    i += 16;

//                    if (!SkinningEntities.CLIENT_ENTITIES_MAP.containsKey(uuid))
//                    {
                    int list_id = BytesReader.getInt(skinningentitiesclientmessage.data, i);
                    i += 4;

                    World world = Minecraft.getMinecraft().player.getEntityWorld();
                    Entity entity = world.getEntityByID(list_id);
                    if (!(entity instanceof SkinningEntities))
                    {
                        try
                        {
                            entity = EntityList.getClassFromID(BytesReader.getInt(skinningentitiesclientmessage.data, i)).getConstructor(World.class).newInstance(world);
                            entity.setEntityId(list_id);
                            entity.setUniqueId(uuid);
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

                            world.spawnEntity(entity);
                        }
                        catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e)
                        {
                            ILOL.error(e);
                        }
//                        entity = null;
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