package com.nali.list.netmethods.clientmessage;

import com.nali.list.messages.ClientMessage;
import com.nali.small.Small;
import com.nali.small.entities.memory.client.ClientEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.system.bytes.BytesReader;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.world.World;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import static com.nali.small.entities.memory.client.ClientEntitiesMemory.ENTITIES_MAP;
import static com.nali.small.entities.memory.client.ClientEntitiesMemory.FAKE_ENTITIES_MAP;

public class SetSEMap
{
    public static byte ID;

    public static void run(ClientMessage clientmessage)
    {
        ENTITIES_MAP.clear();
        FAKE_ENTITIES_MAP.clear();

        for (int i = 1; i < clientmessage.data.length; i += 4)
        {
            UUID uuid = BytesReader.getUUID(clientmessage.data, i);
            i += 16;

            int list_id = BytesReader.getInt(clientmessage.data, i);
            i += 4;

            World world = Minecraft.getMinecraft().player.world;
            Entity entity = world.getEntityByID(list_id);
            if (!(entity instanceof SkinningEntities))
            {
                int entity_id = BytesReader.getInt(clientmessage.data, i);
                FAKE_ENTITIES_MAP.put(list_id, uuid);

                try
                {
                    entity = EntityList.getClassFromID(entity_id).getConstructor(World.class).newInstance(world);
                    SkinningEntities skinningentities = (SkinningEntities)entity;
                    ClientEntitiesMemory cliententitiesmemory = (ClientEntitiesMemory)skinningentities.bothentitiesmemory;
                    cliententitiesmemory.fake = true;
                    cliententitiesmemory.uuid = uuid;
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
            ENTITIES_MAP.put(uuid, (SkinningEntities)entity);
        }
    }
}
