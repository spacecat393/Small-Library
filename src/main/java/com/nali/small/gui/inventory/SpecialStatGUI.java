package com.nali.small.gui.inventory;

import com.nali.list.network.method.server.SendLocation;
import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.messages.AttributeStatGUIFeatures;
import com.nali.small.gui.features.messages.ScaleGUIFeatures;
import com.nali.small.gui.features.messages.inventory.SetLookGUIFeatures;
import com.nali.small.gui.features.messages.inventory.SetXYZGUIFeatures;
import com.nali.small.gui.features.messages.inventory.SetLocationGUIFeatures;

import static com.nali.small.gui.MixGui.GUIFEATURESLOADER;

public class SpecialStatGUI
{
    public static void drawGuiContainerBackgroundLayer(MixGui gui)
    {
        int left = gui.getGuiLeft();
        int top = gui.getGuiTop();

        gui.drawTexturedModalRect(left + 49, top + 90, 16, 28, 14, 14);
        gui.drawTexturedModalRect(left + 67, top + 90, 106, 14, 14, 14);
        gui.drawTexturedModalRect(left + 67 + 18, top + 94, 38, 14, 14, 8);
        gui.drawTexturedModalRect(left + 67 + 18 + 18, top + 90, 44, 0, 14, 14);

//                if (cliententitiesmemory.workbytes.LOCATION() == -1)
//                {
//                    gui.drawTexturedModalRect(left + 67 + 18 + 18 + 18, top + 90, 134, 14, 14, 14);
//                }
//                else
//                {
        gui.drawTexturedModalRect(left + 67 + 18 + 18 + 18, top + 90, 128, 0, 14, 14);
//                }
    }

    public static void drawScreen(MixGui gui, int mouseX, int mouseY)
    {
        int left = gui.getGuiLeft();
        int top = gui.getGuiTop();

        int x = left + 48, y = top + 89, width = 16, height = 16;
        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
        {
            gui.state |= 1;

            if (!(GUIFEATURESLOADER instanceof ScaleGUIFeatures))
            {
                GUIFEATURESLOADER = new ScaleGUIFeatures(gui);
            }
            gui.state |= 2;
        }

        x = left + 66;// y = top + 89; width = 16; height = 16;
        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
        {
            gui.state |= 1;

            if (!(GUIFEATURESLOADER instanceof AttributeStatGUIFeatures))
            {
                GUIFEATURESLOADER = new AttributeStatGUIFeatures(gui);
            }
            gui.state |= 2;
        }

        x = left + 84;// y = top + 89; width = 16; height = 16;
        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
        {
            gui.state |= 1;

            if (!(GUIFEATURESLOADER instanceof SetLookGUIFeatures))
            {
                GUIFEATURESLOADER = new SetLookGUIFeatures(gui);
            }
            gui.state |= 2;
        }

        x = left + 102;// y = top + 89; width = 16; height = 16;
        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
        {
            gui.state |= 1;

            if (!(GUIFEATURESLOADER instanceof SetXYZGUIFeatures))
            {
                GUIFEATURESLOADER = new SetXYZGUIFeatures(gui);
            }
            gui.state |= 2;
        }

        x = left + 120;// y = top + 89; width = 16; height = 16;
        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
        {
            //                    int id = cliententitiesmemory.workbytes.LOCATION();
//                    if (id != -1)
//                    {
            gui.state |= 1;

            if (gui.mouse_released == 0)
            {
                gui.sendPacketUUID(SendLocation.ID);
            }

            if (!(GUIFEATURESLOADER instanceof SetLocationGUIFeatures))
            {
                GUIFEATURESLOADER = new SetLocationGUIFeatures(gui);
            }
//                    }
//                    else
//                    {
//                        if (!(GUIFEATURESLOADER instanceof CantSetLocationGUIFeatures))
//                        {
//                            GUIFEATURESLOADER = new CantSetLocationGUIFeatures(gui);
//                        }
//                    }

            gui.state |= 2;
        }
    }
}
