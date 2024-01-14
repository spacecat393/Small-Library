package com.nali.list.handlers;

import com.nali.small.Small;
import com.nali.small.entities.EntitiesRegistryHelper;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.networks.NetworksRegistry;
import com.nali.small.world.ChunkLoader;
import com.nali.list.capabilitiesserializations.SmallSakuraSerializations;
import com.nali.list.capabilitiestypes.SmallSakuraTypes;
import com.nali.list.items.SmallBox;
import com.nali.list.messages.SkinningEntitiesClientMessage;
import com.nali.list.messages.SkinningEntitiesServerMessage;
import com.nali.system.bytes.BytesReader;
import com.nali.system.bytes.BytesWriter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

public class SkinningEntitiesServerHandler implements IMessageHandler<SkinningEntitiesServerMessage, IMessage>
{
    @Override
    public IMessage onMessage(SkinningEntitiesServerMessage skinningentitiesservermessage, MessageContext messagecontext)
    {
        EntityPlayerMP entityplayermp = messagecontext.getServerHandler().player;
        ((WorldServer)entityplayermp.world).addScheduledTask(() ->
        {
            switch (skinningentitiesservermessage.data[0])
            {
                case 0://sync server uuid to client
                {
                    int id = BytesReader.getInt(skinningentitiesservermessage.data, 1);
                    Entity entity = entityplayermp.getEntityWorld().getEntityByID(id);
                    if (entity instanceof SkinningEntities)
                    {
                        byte[] byte_array = new byte[1 + 16 + 4];
                        byte_array[0] = 5;
                        BytesWriter.set(byte_array, entity.getUniqueID(), 1);
                        BytesWriter.set(byte_array, id, 1 + 16);
                        NetworksRegistry.I.sendTo(new SkinningEntitiesClientMessage(byte_array), entityplayermp);
                    }
    //                entitydatamanager.set(skinningentitieshelper.getIntegerDataParameterArray()[id], BytesReader.getInt(skinningentitiesmessage.data, 21));
                    break;
                }
                case 1://set byte
                {
                    UUID uuid = BytesReader.getUUID(skinningentitiesservermessage.data, 1);
                    SkinningEntities skinningentities = SkinningEntities.SERVER_ENTITIES_MAP.get(uuid);
    //                Entity temp_entity = ((WorldServer)entityplayermp.world).getEntityFromUuid(BytesReader.getUUID(skinningentitiesservermessage.data, 1));
    //                if (!(temp_entity instanceof SkinningEntitiesHelper))
    //                {
    //                    break;
    //                }

    //                SkinningEntitiesHelper skinningentitieshelper = (SkinningEntitiesHelper)temp_entity;
                    if (skinningentities != null)
                    {
                        if (skinningentities.main_server_work_byte_array[skinningentities.skinningentitiesbytes.LOCK_INVENTORY()] == 1)
                        {
                            Entity entity = skinningentities.getOwner();
                            if (entity == null || !entity.equals(entityplayermp))
                            {
                                break;
                            }
                        }

//                        EntityDataManager entitydatamanager = skinningentities.getDataManager();
                        int id = BytesReader.getInt(skinningentitiesservermessage.data, 17);
                        if (id == skinningentities.skinningentitiesbytes.FOLLOW())
                        {
                            int dimension = skinningentities.world.provider.getDimension();
                            if (dimension != entityplayermp.world.provider.getDimension())
                            {
                                entityplayermp.changeDimension(dimension, (x, y, z) ->
                                {
                                    entityplayermp.setPositionAndUpdate(skinningentities.posX, skinningentities.posY, skinningentities.posZ);
                                });
                            }
                            else
                            {
                                entityplayermp.setPositionAndUpdate(skinningentities.posX, skinningentities.posY, skinningentities.posZ);
                            }
    //                        DataParameter<Byte> byte_dataparameter = skinningentities.getByteDataParameterArray()[id];
    //                        entitydatamanager.set(byte_dataparameter, entitydatamanager.get(byte_dataparameter) == 1 ? (byte)0 : 1);
    ////                        skinningentities.changeDimension(entityplayermp.getEntityWorld().provider.getDimension());
    //                        skinningentities.getEntityWorld().removeEntity(skinningentities);
    //                        skinningentities.isDead = false;
    //                        entityplayermp.getEntityWorld().spawnEntity(skinningentities);
    //                        entityplayermp.connection.sendPacket(new SPacketSpawnObject(skinningentities, EntityList.getID(skinningentities.getClass())));
    //
    //                        skinningentities.setPositionAndRotation(entityplayermp.posX, entityplayermp.posY, entityplayermp.posZ, skinningentities.rotationYaw, skinningentities.rotationPitch);
                        }

//                        DataParameter<Byte>[] byte_dataparameter_array = skinningentities.getByteDataParameterArray();
                        if (skinningentitiesservermessage.data[21] == 0)
                        {
                            skinningentities.main_server_work_byte_array[skinningentities.skinningentitiesbytes.SOFT_READY()] = 1;
                            skinningentities.main_server_work_byte_array[skinningentities.skinningentitiesbytes.HARD_READY()] = 0;
//                            entitydatamanager.set(byte_dataparameter_array[skinningentities.skinningentitiesbytes.SOFT_READY()], (byte)1);
//                            entitydatamanager.set(byte_dataparameter_array[skinningentities.skinningentitiesbytes.HARD_READY()], (byte)0);
                        }
                        else
                        {
                            skinningentities.main_server_work_byte_array[skinningentities.skinningentitiesbytes.SOFT_READY()] = 0;
                            skinningentities.main_server_work_byte_array[skinningentities.skinningentitiesbytes.HARD_READY()] = 1;
//                            entitydatamanager.set(byte_dataparameter_array[skinningentities.skinningentitiesbytes.HARD_READY()], (byte)1);
//                            entitydatamanager.set(byte_dataparameter_array[skinningentities.skinningentitiesbytes.SOFT_READY()], (byte)0);
                        }

                        skinningentities.main_server_work_byte_array[id] = skinningentitiesservermessage.data[21];
//                        entitydatamanager.set(byte_dataparameter_array[id], skinningentitiesservermessage.data[21]);

                        byte[] byte_array = new byte[1 + 4 + skinningentities.main_server_work_byte_array.length];
                        byte_array[0] = 6;
                        BytesWriter.set(byte_array, skinningentities.getEntityId(), 1);
                        System.arraycopy(skinningentities.main_server_work_byte_array, 0, byte_array, 1 + 4, skinningentities.main_server_work_byte_array.length);
                        NetworksRegistry.I.sendTo(new SkinningEntitiesClientMessage(byte_array), entityplayermp);
                    }

                    break;
                }
                case 2://set owner
                {
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
                        if (skinningentities.main_server_work_byte_array[skinningentities.skinningentitiesbytes.LOCK_INVENTORY()] == 1)
                        {
                            Entity entity = skinningentities.getOwner();
                            if (entity == null || !entity.equals(entityplayermp))
                            {
                                break;
                            }
                        }

                        skinningentities.owner_uuid = entityplayermp.getUniqueID();
//                        EntityDataManager entitydatamanager = skinningentities.getDataManager();
//                        entitydatamanager.set(SkinningEntities.UUID_OPTIONAL_DATAPARAMETER_ARRAY[BytesReader.getInt(skinningentitiesservermessage.data, 17)], Optional.fromNullable(entityplayermp.getUniqueID()));
                    }

                    break;
                }
                case 3://x12
                {
                    Random random = entityplayermp.getRNG();
                    SmallSakuraTypes smallsakuratypes = entityplayermp.getCapability(SmallSakuraSerializations.SMALLSAKURATYPES_CAPABILITY, null);
                    int value = smallsakuratypes.get();
                    if (value >= 12)
                    {
                        smallsakuratypes.set(value - 12);
                        if (random.nextInt(7) == 0)
                        {
                            try
                            {
                                ItemStack itemstack = SmallBox.I.getDefaultInstance();
                                int id = random.nextInt(EntitiesRegistryHelper.ENTITIES_CLASS_LIST.size());
                                Constructor constructor = EntitiesRegistryHelper.ENTITIES_CLASS_LIST.get(id).getConstructor(World.class);
                                Entity entity = (Entity)constructor.newInstance(entityplayermp.world);
                                SmallBox.putToBox(entity, itemstack);
                                entityplayermp.entityDropItem(itemstack, 0.0F);
                            }
                            catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e)
                            {
                                Small.error(e);
                            }
                        }
                        else
                        {
                            entityplayermp.dropItem(Items.STICK, 1);
                        }
                    }

                    break;
                }
                case 4://x64
                {
                    Random random = entityplayermp.getRNG();
                    SmallSakuraTypes smallsakuratypes = entityplayermp.getCapability(SmallSakuraSerializations.SMALLSAKURATYPES_CAPABILITY, null);
                    int value = smallsakuratypes.get();

                    if (value >= 64)
                    {
                        smallsakuratypes.set(value - 64);
                        if (random.nextBoolean())
                        {
                            entityplayermp.dropItem(Item.getItemById(entityplayermp.getRNG().nextInt(Item.REGISTRY.getKeys().size())), 1);
                        }
                        else
                        {
                            try
                            {
                                ItemStack itemstack = SmallBox.I.getDefaultInstance();
                                int id = entityplayermp.getRNG().nextInt(EntitiesRegistryHelper.ENTITY_KEY_ARRAY.length);
                                Constructor constructor = ((Class)EntitiesRegistryHelper.ENTITY_KEY_ARRAY[id]).getConstructor(World.class);
                                Entity entity = (Entity)constructor.newInstance(entityplayermp.world);

                                if (!(entity instanceof EntityPlayer))
                                {
                                    SmallBox.putToBox(entity, itemstack);
                                }

                                entityplayermp.entityDropItem(itemstack, 0.0F);
                            }
                            catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e)
                            {
                                Small.error(e);
                            }
                        }
                    }

                    break;
                }
                case 5:
                {
    //                WorldServer worldserver = (WorldServer)entityplayermp.world;
    //                Map<UUID, Entity> entity_map = ((IMixinWorldServer)worldserver).entitiesByUuid();
    //                if (!entity_map.isEmpty())
                    if (!SkinningEntities.SERVER_ENTITIES_MAP.isEmpty())
                    {
                        int index = 1;
    //                    Set<UUID> keys_set = new HashSet<>(entity_map.keySet());
                        Set<UUID> keys_set = new HashSet<>(SkinningEntities.SERVER_ENTITIES_MAP.keySet());
                        byte[] byte_array = new byte[keys_set.size() * 16 + 1 + keys_set.size() * 8];
    //                    byte_array[0] = 0;

                        for (UUID uuid : keys_set)
                        {
                            SkinningEntities skinningentities = SkinningEntities.SERVER_ENTITIES_MAP.get(uuid);
//                            entityplayermp.connection.sendPacket(new SPacketSpawnObject(skinningentities, EntityList.getID(skinningentities.getClass())));
                            //should check long with uuid
                            ChunkLoader.updateChunk(skinningentities);
    //                        if (worldserver.getEntityFromUuid(uuid) instanceof SkinningEntities)
    //                        {
                            BytesWriter.set(byte_array, uuid, index);
                            index += 16;
                            BytesWriter.set(byte_array, skinningentities.getEntityId(), index);
                            index += 4;
                            BytesWriter.set(byte_array, EntityList.getID(skinningentities.getClass()), index);
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
                        entityplayermp.openGui(Small.I, 0, skinningentities.getEntityWorld(), skinningentities.getEntityId(), 0, 0);

                        byte[] byte_array = new byte[1 + 4 + skinningentities.main_server_work_byte_array.length];
                        byte_array[0] = 6;
                        BytesWriter.set(byte_array, skinningentities.getEntityId(), 1);
                        System.arraycopy(skinningentities.main_server_work_byte_array, 0, byte_array, 1 + 4, skinningentities.main_server_work_byte_array.length);
                        NetworksRegistry.I.sendTo(new SkinningEntitiesClientMessage(byte_array), entityplayermp);
//                        EntitiesContainerHelper.setContainer(skinningentities, messagecontext.getServerHandler().player, InventoryContainer.ID);
                    }
                    break;
                }
                case 7:
                {
                    InventoryPlayer inventoryplayer = entityplayermp.inventory;
                    String string = new String(skinningentitiesservermessage.data, 1, skinningentitiesservermessage.data.length - 1);
                    String[] string_array = string.split(" ");

                    int max_count = 0, index = 0;
                    int[] index_int_array = new int[string_array.length];
                    int hand_index = inventoryplayer.mainInventory.size() + inventoryplayer.armorInventory.size();
                    int armor_index = inventoryplayer.mainInventory.size();
                    for (String new_string : string_array)
                    {
                        int i = Integer.parseInt(new_string);
                        index_int_array[index++] = i;

                        if (i < inventoryplayer.mainInventory.size() + inventoryplayer.mainInventory.size() + inventoryplayer.armorInventory.size())
                        {
                            if (i >= inventoryplayer.mainInventory.size() + inventoryplayer.armorInventory.size())
                            {
                                max_count += inventoryplayer.offHandInventory.get(i - hand_index).getCount();
                            }
                            else if (i >= inventoryplayer.mainInventory.size())
                            {
                                max_count += inventoryplayer.armorInventory.get(i - armor_index).getCount();
                            }
                            else
                            {
                                max_count += inventoryplayer.mainInventory.get(i).getCount();
                            }
                        }
                    }

                    if (max_count % 64 == 0)
                    {
                        for (int i : index_int_array)
                        {
                            if (i >= inventoryplayer.mainInventory.size() + inventoryplayer.armorInventory.size())
                            {
                                inventoryplayer.offHandInventory.set(i - hand_index, ItemStack.EMPTY);
                            }
                            else if (i >= inventoryplayer.mainInventory.size())
                            {
                                inventoryplayer.armorInventory.set(i - armor_index, ItemStack.EMPTY);
                            }
                            else
                            {
                                inventoryplayer.mainInventory.set(i, ItemStack.EMPTY);
                            }
                        }

                        entityplayermp.getCapability(SmallSakuraSerializations.SMALLSAKURATYPES_CAPABILITY, null).add(max_count / 64);
                    }

//    //                Object[] key_array = new HashSet<>(((MixinEntityRegistry)EntityRegistry.instance()).entityClassEntries().keySet()).toArray();
//    //                for (Object object : key_array)
//    //                {
//    //                    ((Class)object).getName();
//    //                }
//                    SMALL.LOGGER.info("Name : " + string);
//                    SMALL.LOGGER.info("Have : " + ForgeRegistries.ENTITIES.containsKey(new ResourceLocation(string)));

                    break;
                }
                case 8:
                {
                    SkinningEntities skinningentities = SkinningEntities.SERVER_ENTITIES_MAP.get(BytesReader.getUUID(skinningentitiesservermessage.data, 1));
                    if (skinningentities != null)
                    {
                        ItemStack itemstack = entityplayermp.getHeldItemMainhand();

                        if (itemstack.getItem() == SmallBox.I && itemstack.getTagCompound() == null)
                        {
                            SmallBox.putToBox(skinningentities, itemstack);
                            entityplayermp.closeScreen();
                        }
                    }

                    break;
                }
                case 9:
                {
                    entityplayermp.closeScreen();
                    NetworksRegistry.I.sendTo(new SkinningEntitiesClientMessage(new byte[]{2}), entityplayermp);
                    break;
                }
                case 10://add t
                {
                    SkinningEntities skinningentities = SkinningEntities.SERVER_ENTITIES_MAP.get(BytesReader.getUUID(skinningentitiesservermessage.data, 1));
                    if (skinningentities != null)
                    {
                        String string = new String(skinningentitiesservermessage.data, 1 + 16, skinningentitiesservermessage.data.length - (1 + 16));
                        String[] string_array = string.split(" ");

                        for (String new_string : string_array)
                        {
                            int id = Integer.parseInt(new_string);

                            if (id >= EntitiesRegistryHelper.ENTITY_KEY_ARRAY.length)
                            {
                                continue;
                            }

                            boolean result = true;
                            for (int i : skinningentities.skinningentitiesarea.target_arraylist)
                            {
                                if (i == id)
                                {
                                    result = false;
                                    break;
                                }
                            }

                            if (result)
                            {
                                skinningentities.skinningentitiesarea.target_arraylist.add(id);
                            }
                        }
                    }

                    break;
                }
                case 11://view t
                {
                    SkinningEntities skinningentities = SkinningEntities.SERVER_ENTITIES_MAP.get(BytesReader.getUUID(skinningentitiesservermessage.data, 1));

                    if (skinningentities != null)
                    {
                        int size = skinningentities.skinningentitiesarea.target_arraylist.size() * 4;
                        byte[] byte_array = new byte[1 + size];
                        byte_array[0] = 3;
                        int index = 0;
                        for (int i = 1; i < size; i += 4)
                        {
                            BytesWriter.set(byte_array, skinningentities.skinningentitiesarea.target_arraylist.get(index++), i);
                        }

                        NetworksRegistry.I.sendTo(new SkinningEntitiesClientMessage(byte_array), entityplayermp);
                    }

                    break;
                }
                case 12://remove t
                {
                    SkinningEntities skinningentities = SkinningEntities.SERVER_ENTITIES_MAP.get(BytesReader.getUUID(skinningentitiesservermessage.data, 1));
                    if (skinningentities != null)
                    {
                        String string = new String(skinningentitiesservermessage.data, 1 + 16, skinningentitiesservermessage.data.length - (1 + 16));
                        String[] string_array = string.split(" ");

                        for (String new_string : string_array)
                        {
                            int id = Integer.parseInt(new_string);

                            int index = 0;
                            for (int i : skinningentities.skinningentitiesarea.target_arraylist)
                            {
                                if (i == id)
                                {
                                    skinningentities.skinningentitiesarea.target_arraylist.remove(index);
                                    break;
                                }
                                ++index;
                            }
                        }
                    }

                    break;
                }
                case 13://add tm
                {
                    SkinningEntities skinningentities = SkinningEntities.SERVER_ENTITIES_MAP.get(BytesReader.getUUID(skinningentitiesservermessage.data, 1));
                    if (skinningentities != null)
                    {
                        String string = new String(skinningentitiesservermessage.data, 1 + 16, skinningentitiesservermessage.data.length - (1 + 16));
                        String[] string_array = string.split(" ");

                        for (String new_string : string_array)
                        {
                            int id = Integer.parseInt(new_string);

                            if (id >= EntitiesRegistryHelper.ENTITY_KEY_ARRAY.length)
                            {
                                continue;
                            }

                            boolean result = true;
                            for (int i : skinningentities.skinningentitiesarea.troublemaker_arraylist)
                            {
                                if (i == id)
                                {
                                    result = false;
                                    break;
                                }
                            }

                            if (result)
                            {
                                skinningentities.skinningentitiesarea.troublemaker_arraylist.add(id);
                            }
                        }
                    }

                    break;
                }
                case 14://view tm
                {
                    SkinningEntities skinningentities = SkinningEntities.SERVER_ENTITIES_MAP.get(BytesReader.getUUID(skinningentitiesservermessage.data, 1));

                    if (skinningentities != null)
                    {
                        int size = skinningentities.skinningentitiesarea.troublemaker_arraylist.size() * 4;
                        byte[] byte_array = new byte[1 + size];
                        byte_array[0] = 4;
                        int index = 0;
                        for (int i = 1; i < size; i += 4)
                        {
                            BytesWriter.set(byte_array, skinningentities.skinningentitiesarea.troublemaker_arraylist.get(index++), i);
                        }

                        NetworksRegistry.I.sendTo(new SkinningEntitiesClientMessage(byte_array), entityplayermp);
                    }

                    break;
                }
                case 15://remove tm
                {
                    SkinningEntities skinningentities = SkinningEntities.SERVER_ENTITIES_MAP.get(BytesReader.getUUID(skinningentitiesservermessage.data, 1));
                    if (skinningentities != null)
                    {
                        String string = new String(skinningentitiesservermessage.data, 1 + 16, skinningentitiesservermessage.data.length - (1 + 16));
                        String[] string_array = string.split(" ");

                        for (String new_string : string_array)
                        {
                            int id = Integer.parseInt(new_string);

                            int index = 0;
                            for (int i : skinningentities.skinningentitiesarea.troublemaker_arraylist)
                            {
                                if (i == id)
                                {
                                    skinningentities.skinningentitiesarea.troublemaker_arraylist.remove(index);
                                    break;
                                }
                                ++index;
                            }
                        }
                    }

                    break;
                }
                case 16://pat
                {
                    SkinningEntities skinningentities = SkinningEntities.SERVER_ENTITIES_MAP.get(BytesReader.getUUID(skinningentitiesservermessage.data, 1));
                    if (skinningentities != null)
                    {
                        skinningentities.server_work_byte_array[skinningentities.skinningentitiesbytes.ON_PAT()] = 1;
                        skinningentities.getEntityWorld().spawnEntity(new EntityXPOrb(skinningentities.getEntityWorld(), skinningentities.posX, skinningentities.posY, skinningentities.posZ, 10));
                    }
                    break;
                }
                default:
                {
                    break;
                }
            }
        });

        return null;
    }
}