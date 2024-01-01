package com.nali.ilol.entities;

import com.nali.ilol.ILOL;
import com.nali.ilol.NBTHelper;
import com.nali.ilol.entities.skinning.SkinningEntities;
import com.nali.ilol.networks.NetworksRegistry;
import com.nali.ilol.world.ChunkLoader;
import com.nali.list.messages.OpenGUIMessage;
import com.nali.system.Reflect;
import com.nali.system.bytes.BytesWriter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.List;

public class EntitiesContainerHelper
{
    public static List<Class> CONTAINER_CLASS_LIST;

    static
    {
        CONTAINER_CLASS_LIST = Reflect.getClasses("com.nali.list.container");
        CONTAINER_CLASS_LIST.sort(Comparator.comparing(Class::getName));

        int index = 0;
        for (Class clasz : CONTAINER_CLASS_LIST)
        {
            try
            {
                clasz.getField("ID").set(null, index++);
            }
            catch (IllegalAccessException | NoSuchFieldException e)
            {
                ILOL.error(e);
            }
        }
    }

    public static void setContainer(SkinningEntities skinningentities, EntityPlayer entityplayer, int id)
    {
        ChunkLoader.updateChunk(skinningentities);
        Entity entity = skinningentities.getEntity(1);

        if (skinningentities.server_work_byte_array[skinningentities.skinningentitiesbytes.LOCK_INVENTORY()] == 0 || (entity != null && entity.equals(entityplayer)))
        {
            try
            {
                Class container_class = CONTAINER_CLASS_LIST.get(id);
                Constructor constructor = container_class.getConstructor(IInventory.class, SkinningEntities.class, EntityPlayer.class);

                if (entityplayer.openContainer != entityplayer.inventoryContainer)
                {
                    entityplayer.closeScreen();
                }

                EntityPlayerMP entityplayermp = (EntityPlayerMP)entityplayer;
                entityplayermp.getNextWindowId();
                //                entityplayermp.connection.sendPacket(new SPacketOpenWindow(entityplayermp.currentWindowId, "EntityHorse", inventoryIn.getDisplayName(), inventoryIn.getSizeInventory(), horse.getEntityId()));
                //                entityplayermp.openContainer = new ContainerHorseInventory(entityplayermp.inventory, inventoryIn, horse, entityplayermp);
                entityplayermp.openContainer = ((Container)constructor.newInstance(entityplayermp.inventory, skinningentities, entityplayermp));
                entityplayermp.openContainer.windowId = entityplayermp.currentWindowId;
                entityplayermp.openContainer.addListener(entityplayermp);
                //
                NBTTagCompound nbttagcompound = new NBTTagCompound();

                skinningentities.writeEntityToNBTHelper(nbttagcompound);
                //                nbttagcompound.removeTag("ArmorItems");
//                nbttagcompound.removeTag("HandItems");
//                for (int l = 0; l < skinningentities.skinninginventory.getSizeInventory(); ++l)
//                {
//                    ItemStack itemstack = skinningentities.skinninginventory.getStackInSlot(l);
//                    if (!itemstack.isEmpty())
//                    {
//                        nbttagcompound.removeTag("ib" + l);
//                    }
//                }

                byte[] nbt_byte_array = NBTHelper.serializeNBT(nbttagcompound);
                byte[] byte_array = new byte[24 + 4 + 4 + nbt_byte_array.length];
                BytesWriter.set(byte_array, id, 0);
                BytesWriter.set(byte_array, skinningentities.getUUID(0), 4);
                BytesWriter.set(byte_array, entityplayermp.currentWindowId, 20);
//                int list_id = skinningentities.getEntityId();
                BytesWriter.set(byte_array, skinningentities.getEntityId(), 24);
//                int entity_id = EntityList.getID(skinningentities.getClass());
//                entityplayermp.connection.sendPacket(new SPacketSpawnObject(skinningentities, entity_id, list_id));

                BytesWriter.set(byte_array, EntityList.getID(skinningentities.getClass()), 28);
                System.arraycopy(nbt_byte_array, 0, byte_array, 32, nbt_byte_array.length);
                //                CutePomi.LOGGER.info("Main Server " + skinningentities.getDataManager().get(UUID_OPTIONAL_DATAPARAMETER_ARRAY[0]).orNull().toString());
                //                CutePomi.LOGGER.info("Old Server " + skinningentities.getUniqueID().toString());
                NetworksRegistry.I.sendTo(new OpenGUIMessage(byte_array), (EntityPlayerMP)entityplayer);
            }
            catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException/* | NoSuchFieldException*/ e)
            {
                ILOL.error(e);
            }
        }
    }
}
