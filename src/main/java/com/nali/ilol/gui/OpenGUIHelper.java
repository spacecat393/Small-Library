package com.nali.ilol.gui;

import com.nali.ilol.ILOL;
import com.nali.ilol.entities.skinning.SkinningEntities;
import com.nali.system.bytes.BytesReader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class OpenGUIHelper
{
    public static List<Class> GUI_CLASS_LIST;
    public static void call(byte[] data)
    {
        try
        {
            Class gui_class = GUI_CLASS_LIST.get(BytesReader.getInt(data, 0));
            Constructor constructor = gui_class.getConstructor(IInventory.class, SkinningEntities.class);

            Minecraft minecraft = Minecraft.getMinecraft();
//            CutePomi.LOGGER.info("Client " + BytesReader.getUUID(data, 4).toString());
//            CutePomi.LOGGER.info("GUI? " + SkinningEntitiesHelper.ENTITIES_MAP.get(BytesReader.getUUID(data, 4)) != null);
            SkinningEntities skinningentities = SkinningEntities.CLIENT_ENTITIES_MAP.get(BytesReader.getUUID(data, 4));
//            minecraft.displayGuiScreen(new InventoryGui(skinningentities));

            minecraft.addScheduledTask(() ->
            {
                try
                {
                    minecraft.displayGuiScreen((GuiContainer)constructor.newInstance(minecraft.player.inventory, skinningentities));
                }
                catch (InstantiationException | IllegalAccessException | InvocationTargetException e)
                {
                    throw new RuntimeException(e);
                }
            });

            minecraft.player.openContainer.windowId = BytesReader.getInt(data, 20);
        }
        catch (NoSuchMethodException e)
        {
            ILOL.LOGGER.error(e.getMessage(), e);
        }
    }
}
