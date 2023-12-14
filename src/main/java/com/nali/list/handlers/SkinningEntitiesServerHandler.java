package com.nali.list.handlers;

import com.google.common.base.Optional;
import com.nali.ilol.ILOL;
import com.nali.ilol.entities.skinning.SkinningEntities;
import com.nali.ilol.networks.NetworksRegistry;
import com.nali.ilol.world.ChunkLoader;
import com.nali.list.container.InventoryContainer;
import com.nali.list.messages.SkinningEntitiesClientMessage;
import com.nali.list.messages.SkinningEntitiesServerMessage;
import com.nali.system.bytes.BytesReader;
import com.nali.system.bytes.BytesWriter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPacketSpawnObject;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class SkinningEntitiesServerHandler implements IMessageHandler<SkinningEntitiesServerMessage, IMessage>
{
    @Override
    public IMessage onMessage(SkinningEntitiesServerMessage skinningentitiesservermessage, MessageContext messagecontext)
    {
        switch (skinningentitiesservermessage.data[0])
        {
//            case 0:
//            {
//                entitydatamanager.set(skinningentitieshelper.getIntegerDataParameterArray()[id], BytesReader.getInt(skinningentitiesmessage.data, 21));
//                break;
//            }
            case 1:
            {
                EntityPlayerMP entityplayermp = messagecontext.getServerHandler().player;
                UUID uuid = BytesReader.getUUID(skinningentitiesservermessage.data, 1);
                SkinningEntities skinningentities = SkinningEntities.SERVER_ENTITIES_MAP.get(uuid);
//                Entity temp_entity = ((WorldServer)entityplayermp.world).getEntityFromUuid(BytesReader.getUUID(skinningentitiesservermessage.data, 1));
//                if (!(temp_entity instanceof SkinningEntitiesHelper))
//                {
//                    break;
//                }

//                SkinningEntitiesHelper skinningentitieshelper = (SkinningEntitiesHelper)temp_entity;
//                if (skinningentitieshelper != null)
//                {
                if (skinningentities.server_work_byte_array[skinningentities.skinningentitiesbytes.LOCK_INVENTORY()] == 1)
                {
                    Entity entity = skinningentities.getEntity(1);
                    if (entity == null || !entity.equals(entityplayermp))
                    {
                        break;
                    }
                }

                EntityDataManager entitydatamanager = skinningentities.getDataManager();
                int id = BytesReader.getInt(skinningentitiesservermessage.data, 17);
                if (id == skinningentities.skinningentitiesbytes.FOLLOW() && skinningentitiesservermessage.data[21] == 1)
                {
                    skinningentities.setPositionAndRotation(entityplayermp.posX, entityplayermp.posY, entityplayermp.posZ, skinningentities.rotationYaw, skinningentities.rotationPitch);
                }

                entitydatamanager.set(skinningentities.getByteDataParameterArray()[id], skinningentitiesservermessage.data[21]);
//                }

                break;
            }
            case 2:
            {
                EntityPlayerMP entityplayermp = messagecontext.getServerHandler().player;
                SkinningEntities skinningentities = SkinningEntities.SERVER_ENTITIES_MAP.get(BytesReader.getUUID(skinningentitiesservermessage.data, 1));
//                Entity temp_entity = ((WorldServer)entityplayermp.world).getEntityFromUuid(BytesReader.getUUID(skinningentitiesservermessage.data, 1));
//                if (!(temp_entity instanceof SkinningEntitiesHelper))
//                {
//                    break;
//                }
//
//                SkinningEntitiesHelper skinningentitieshelper = (SkinningEntitiesHelper)temp_entity;
                if (skinningentities != null)
                {
                    if (skinningentities.server_work_byte_array[skinningentities.skinningentitiesbytes.LOCK_INVENTORY()] == 1)
                    {
                        Entity entity = skinningentities.getEntity(1);
                        if (entity == null || !entity.equals(entityplayermp))
                        {
                            break;
                        }
                    }

                    EntityDataManager entitydatamanager = skinningentities.getDataManager();
                    entitydatamanager.set(SkinningEntities.UUID_OPTIONAL_DATAPARAMETER_ARRAY[BytesReader.getInt(skinningentitiesservermessage.data, 17)], Optional.fromNullable(entityplayermp.getUniqueID()));
                }

                break;
            }
            case 3://x12
            {
//                EntityPlayerMP entityplayermp = messagecontext.getServerHandler().player;
//                Random random = entityplayermp.getRNG();
//                PyroxeneTypes pyroxenetypes = entityplayermp.getCapability(PyroxeneSerializations.PYROXENETYPES_CAPABILITY, null);
//                int value = pyroxenetypes.get();
////                if (value >= 12)
//                {
////                    pyroxenetypes.set(value - 12);
//                    if (random.nextInt(7) == 0)
//                    {
//                        try
//                        {
//                            ItemStack itemstack = ItemsRegistryHelper.BOX_ITEM.getDefaultInstance();
//                            int id = random.nextInt(EntitiesRegistryHelper.ENTITIES_CLASS_LIST.size());
//                            Constructor constructor = EntitiesRegistryHelper.ENTITIES_CLASS_LIST.get(id).getConstructor(World.class);
//                            Entity entity = (Entity)constructor.newInstance(entityplayermp.world);
//
//                            itemstack.setTagCompound(new NBTTagCompound());
//                            NBTTagCompound nbttagcompound = itemstack.getTagCompound();
//
//                            String key_id = "key_id";
//                            if (!nbttagcompound.hasKey(key_id))
//                            {
//                                nbttagcompound.setInteger(key_id, id);
//                            }
//
//                            String entity_key = "entity";
//                            NBTTagCompound entity_nbttagcompound = new NBTTagCompound();
//                            entity.writeToNBT(entity_nbttagcompound);
//                            nbttagcompound.setTag(entity_key, entity_nbttagcompound);
//                            itemstack.setStackDisplayName(entity.getName());
//
//                            entityplayermp.entityDropItem(itemstack, 0.0F);
//                        }
//                        catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e)
//                        {
//                            ILOL.LOGGER.error(e.getMessage(), e);
//                        }
//                    }
//                    else
//                    {
//                        entityplayermp.dropItem(Items.STICK, 1);
//                    }
//                }

                break;
            }
            case 4://x64
            {
//                EntityPlayerMP entityplayermp = messagecontext.getServerHandler().player;
//                Random random = entityplayermp.getRNG();
//                PyroxeneTypes pyroxenetypes = entityplayermp.getCapability(PyroxeneSerializations.PYROXENETYPES_CAPABILITY, null);
//                int value = pyroxenetypes.get();
//
////                if (value >= 64)
//                {
////                    pyroxenetypes.set(value - 64);
//                    if (random.nextBoolean())
//                    {
//                        entityplayermp.dropItem(Item.getItemById(entityplayermp.getRNG().nextInt(Item.REGISTRY.getKeys().size())), 1);
//                    }
//                    else
//                    {
//                        Object[] key_array = new HashSet<>(((IMixinEntityRegistry)EntityRegistry.instance()).entityClassEntries().keySet()).toArray();
//                        try
//                        {
//                            ItemStack itemstack = ItemsRegistryHelper.BOX_ITEM.getDefaultInstance();
//                            int id = entityplayermp.getRNG().nextInt(key_array.length);
//                            Constructor constructor = ((Class)key_array[id]).getConstructor(World.class);
//                            Entity entity = (Entity)constructor.newInstance(entityplayermp.world);
//
//                            if (!(entity instanceof EntityPlayer))
//                            {
//                                itemstack.setTagCompound(new NBTTagCompound());
//                                NBTTagCompound nbttagcompound = itemstack.getTagCompound();
//
//                                String mc_key_id = "mc_key_id";
//                                if (!nbttagcompound.hasKey(mc_key_id))
//                                {
//                                    nbttagcompound.setInteger(mc_key_id, id);
//                                }
//
//                                String entity_key = "entity";
//                                NBTTagCompound entity_nbttagcompound = new NBTTagCompound();
//                                entity.writeToNBT(entity_nbttagcompound);
//                                nbttagcompound.setTag(entity_key, entity_nbttagcompound);
//                            }
//
//                            itemstack.setStackDisplayName(entity.getName());
//
//                            entityplayermp.entityDropItem(itemstack, 0.0F);
//                        }
//                        catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e)
//                        {
//                            ILOL.LOGGER.error(e.getMessage(), e);
//                        }
//                    }
//                }

                break;
            }
            case 5:
            {
                EntityPlayerMP entityplayermp = messagecontext.getServerHandler().player;
//                WorldServer worldserver = (WorldServer)entityplayermp.world;
//                Map<UUID, Entity> entity_map = ((IMixinWorldServer)worldserver).entitiesByUuid();
//                if (!entity_map.isEmpty())
                if (!SkinningEntities.SERVER_ENTITIES_MAP.isEmpty())
                {
                    int index = 1;
//                    Set<UUID> keys_set = new HashSet<>(entity_map.keySet());
                    Set<UUID> keys_set = new HashSet<>(SkinningEntities.SERVER_ENTITIES_MAP.keySet());
                    byte[] byte_array = new byte[keys_set.size() * 16 + 1 + keys_set.size() * 4];
//                    byte_array[0] = 0;

                    for (UUID uuid : keys_set)
                    {
                        SkinningEntities skinningentities = SkinningEntities.SERVER_ENTITIES_MAP.get(uuid);
                        entityplayermp.connection.sendPacket(new SPacketSpawnObject(skinningentities, EntityList.getID(skinningentities.getClass())));
                        //should check long with uuid
                        ChunkLoader.updateChunk(skinningentities);
//                        if (worldserver.getEntityFromUuid(uuid) instanceof SkinningEntities)
//                        {
                        BytesWriter.set(byte_array, uuid, index);
                        index += 16;
                        BytesWriter.set(byte_array, skinningentities.getEntityId(), index);
                        index += 4;
//                        }
                    }

                    NetworksRegistry.I.sendTo(new SkinningEntitiesClientMessage(byte_array), entityplayermp);
                }

                break;
            }
            case 6:
            {
                SkinningEntities skinningentities = SkinningEntities.SERVER_ENTITIES_MAP.get(BytesReader.getUUID(skinningentitiesservermessage.data, 1));
                if (skinningentities != null)
                {
                    SkinningEntities.setContainer(skinningentities, messagecontext.getServerHandler().player, InventoryContainer.ID);
                }
                break;
            }
            case 7:
            {
                String string = new String(skinningentitiesservermessage.data, 1, skinningentitiesservermessage.data.length - 1);
//                Object[] key_array = new HashSet<>(((MixinEntityRegistry)EntityRegistry.instance()).entityClassEntries().keySet()).toArray();
//                for (Object object : key_array)
//                {
//                    ((Class)object).getName();
//                }
                ILOL.LOGGER.info("Name : " + string);
                ILOL.LOGGER.info("Have : " + ForgeRegistries.ENTITIES.containsKey(new ResourceLocation(string)));

            }
            default:
            {
                break;
            }
        }

        return null;
    }
}