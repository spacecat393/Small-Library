package com.nali.list.handlers;

import com.nali.list.messages.SkinningEntitiesClientMessage;
import com.nali.small.Small;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.gui.MixGui;
import com.nali.small.gui.OpenGUIHelper;
import com.nali.small.gui.features.messages.EffectsGUIFeatures;
import com.nali.small.gui.features.messages.inventory.TargetGUIFeatures;
import com.nali.small.gui.features.messages.inventory.TroublemakerGUIFeatures;
import com.nali.system.bytes.BytesReader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import static com.nali.small.gui.features.messages.EffectsGUIFeatures.EFFECTS_INT_ARRAY;
import static com.nali.small.gui.features.messages.inventory.TargetGUIFeatures.TARGET_INT_ARRAY;
import static com.nali.small.gui.features.messages.inventory.TroublemakerGUIFeatures.TROUBLEMAKER_INT_ARRAY;

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

                    World world = Minecraft.getMinecraft().player.world;
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
//                            NBTTagCompound nbttagcompound = new NBTTagCompound();
//                            skinningentities.initWriteEntityToNBT(nbttagcompound);
//                            for (int ii = 0; ii < skinningentities.bothdata.MaxPart(); ++ii)
//                            {
//                                ((ObjectRender)skinningentities.client_object).texture_index_int_array[ii] = nbttagcompound.getInteger("int_" + ii);
//                            }
//                            skinningentities.initWriteEntityToNBT(nbttagcompound);
                            skinningentities.initFakeFrame();
                        }
                        catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e)
                        {
                            Small.error(e);
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
            case 3:
            {
                TARGET_INT_ARRAY = new int[(skinningentitiesclientmessage.data.length - 1) / 4];
                int index = 0;
                for (int i = 1; i < skinningentitiesclientmessage.data.length; i += 4)
                {
                    TARGET_INT_ARRAY[index++] = BytesReader.getInt(skinningentitiesclientmessage.data, i);
                }

                MixGui.GUIFEATURESLOADER = new TargetGUIFeatures(MixGui.I);

                break;
            }
            case 4:
            {
                TROUBLEMAKER_INT_ARRAY = new int[(skinningentitiesclientmessage.data.length - 1) / 4];
                int index = 0;
                for (int i = 1; i < skinningentitiesclientmessage.data.length; i += 4)
                {
                    TROUBLEMAKER_INT_ARRAY[index++] = BytesReader.getInt(skinningentitiesclientmessage.data, i);
                }

                MixGui.GUIFEATURESLOADER = new TroublemakerGUIFeatures(MixGui.I);

                break;
            }
            case 5://sync server uuid to client
            {
                Entity entity = Minecraft.getMinecraft().world.getEntityByID(BytesReader.getInt(skinningentitiesclientmessage.data, 1 + 16));
                if (entity instanceof SkinningEntities)
                {
                    ((SkinningEntities)entity).client_uuid = BytesReader.getUUID(skinningentitiesclientmessage.data, 1);
                }

                break;
            }
            case 6://sync work bytes
            {
                int id = BytesReader.getInt(skinningentitiesclientmessage.data, 1);
                Entity entity = Minecraft.getMinecraft().world.getEntityByID(id);
                if (!(entity instanceof SkinningEntities))
                {
                    UUID uuid = SkinningEntities.FAKE_CLIENT_ENTITIES_MAP.get(id);
                    entity = SkinningEntities.CLIENT_ENTITIES_MAP.get(uuid);
                }

                if (entity instanceof SkinningEntities)
                {
                    SkinningEntities skinningentities = (SkinningEntities)entity;
                    System.arraycopy(skinningentitiesclientmessage.data, 1 + 4, skinningentities.client_work_byte_array, 0, skinningentities.client_work_byte_array.length);
                }
                break;
            }
            case 7://get effect
            {
                Entity entity = Minecraft.getMinecraft().world.getEntityByID(BytesReader.getInt(skinningentitiesclientmessage.data, 1));

                if (entity instanceof SkinningEntities)
                {
                    EFFECTS_INT_ARRAY = new int[(skinningentitiesclientmessage.data.length - 1 - 4) / 4];
                    int index = 0;
                    for (int i = 1 + 4; i < skinningentitiesclientmessage.data.length; i += 4)
                    {
                        EFFECTS_INT_ARRAY[index++] = BytesReader.getInt(skinningentitiesclientmessage.data, i);
                        i += 4;
                        EFFECTS_INT_ARRAY[index++] = BytesReader.getInt(skinningentitiesclientmessage.data, i);
                        i += 4;
                        EFFECTS_INT_ARRAY[index++] = BytesReader.getInt(skinningentitiesclientmessage.data, i);
                    }

                    MixGui.GUIFEATURESLOADER = new EffectsGUIFeatures(MixGui.I);
                }
//                //sync looking
//                Entity entity = Minecraft.getMinecraft().world.getEntityByID(BytesReader.getInt(skinningentitiesclientmessage.data, 1));
//
//                if (entity instanceof SkinningEntities)
//                {
//                    SkinningEntities skinningentities = (SkinningEntities)entity;
//                    skinningentities.rotationYaw = BytesReader.getFloat(skinningentitiesclientmessage.data, 1 + 4);
//                    skinningentities.rotationPitch = BytesReader.getFloat(skinningentitiesclientmessage.data, 1 + 4 + 4);
//                    skinningentities.rotationYawHead = BytesReader.getFloat(skinningentitiesclientmessage.data, 1 + 4 + 4 + 4);
//                    skinningentities.renderYawOffset = BytesReader.getFloat(skinningentitiesclientmessage.data, 1 + 4 + 4 + 4 + 4);
//                }
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