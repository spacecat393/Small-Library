package com.nali.ilol.gui;

import com.nali.ilol.ILOL;
import com.nali.ilol.NBTHelper;
import com.nali.ilol.entities.skinning.SkinningEntities;
import com.nali.system.bytes.BytesReader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.EntityList;
import net.minecraft.inventory.IInventory;
import net.minecraft.world.World;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

public class OpenGUIHelper
{
    public static List<Class> GUI_CLASS_LIST;
    public static void call(byte[] data)
    {
        try
        {
            Class gui_class = GUI_CLASS_LIST.get(BytesReader.getInt(data, 0));
            Constructor gui_constructor = gui_class.getConstructor(IInventory.class, SkinningEntities.class);

            UUID uuid = BytesReader.getUUID(data, 4);
            Minecraft minecraft = Minecraft.getMinecraft();
//            World world = minecraft.player.getEntityWorld();
//            SkinningEntities skinningentities = SkinningEntities.CLIENT_ENTITIES_MAP.get(BytesReader.getUUID(data, 4));
//            Constructor entity_constructor = EntitiesRegistryHelper.ENTITIES_CLASS_LIST.get(BytesReader.getInt(data, 24)).getConstructor(World.class);
//            SkinningEntities skinningentities = (SkinningEntities)entity_constructor.newInstance(world);
            SkinningEntities skinningentities = (SkinningEntities)minecraft.player.getEntityWorld().getEntityByID(BytesReader.getInt(data, 24));

            if (skinningentities == null)
            {
                skinningentities = (SkinningEntities)EntityList.getClassFromID(BytesReader.getInt(data, 28)).getConstructor(World.class).newInstance(minecraft.player.getEntityWorld());
                int size = data.length - 24 - 4 - 4;
                byte[] nbt_byte_array = new byte[size];
                System.arraycopy(data, data.length - size, nbt_byte_array, 0, nbt_byte_array.length);
                skinningentities.readFromNBT(NBTHelper.deserializeNBT(nbt_byte_array));
//            skinningentities.setUniqueId(uuid);
//            world.spawnEntity(skinningentities);
            }

            SkinningEntities.CLIENT_ENTITIES_MAP.clear();
            SkinningEntities.CLIENT_ENTITIES_MAP.put(uuid, skinningentities);

//            minecraft.displayGuiScreen(new InventoryGui(skinningentities));

            minecraft.displayGuiScreen((GuiContainer)gui_constructor.newInstance(minecraft.player.inventory, skinningentities));
//            minecraft.addScheduledTask(() ->
//            {
//                try
//                {
//                    minecraft.displayGuiScreen((GuiContainer)constructor.newInstance(minecraft.player.inventory, skinningentities));
//                }
//                catch (InstantiationException | IllegalAccessException | InvocationTargetException e)
//                {
//                    throw new RuntimeException(e);
//                }
//            });

            minecraft.player.openContainer.windowId = BytesReader.getInt(data, 20);
        }
        catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e)
        {
            ILOL.error(e);
        }
    }
}
