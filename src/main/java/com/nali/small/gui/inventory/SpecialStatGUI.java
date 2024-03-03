package com.nali.small.gui.inventory;

import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.messages.AttributeStatGUIFeatures;
import com.nali.small.gui.features.messages.ScaleGUIFeatures;
import com.nali.small.gui.features.messages.inventory.LookGUIFeatures;
import com.nali.small.gui.features.messages.inventory.MoveGUIFeatures;
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
            gui.message_state = 5;

            if (!(GUIFEATURESLOADER instanceof ScaleGUIFeatures))
            {
                GUIFEATURESLOADER = new ScaleGUIFeatures(gui);
            }
            gui.render_text = true;
        }

        x = left + 66;// y = top + 89; width = 16; height = 16;
        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
        {
            gui.message_state = 6;

            if (!(GUIFEATURESLOADER instanceof AttributeStatGUIFeatures))
            {
                GUIFEATURESLOADER = new AttributeStatGUIFeatures(gui);
            }
            gui.render_text = true;
        }

        x = left + 84;// y = top + 89; width = 16; height = 16;
        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
        {
            gui.message_state = 7;

            if (!(GUIFEATURESLOADER instanceof LookGUIFeatures))
            {
                GUIFEATURESLOADER = new LookGUIFeatures(gui);
            }
            gui.render_text = true;
        }

        x = left + 102;// y = top + 89; width = 16; height = 16;
        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
        {
            gui.message_state = 8;

            if (!(GUIFEATURESLOADER instanceof MoveGUIFeatures))
            {
                GUIFEATURESLOADER = new MoveGUIFeatures(gui);
            }
            gui.render_text = true;
        }

        x = left + 120;// y = top + 89; width = 16; height = 16;
        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
        {
            //                    int id = cliententitiesmemory.workbytes.LOCATION();
//                    if (id != -1)
//                    {
            gui.message_state = 9;

            if (gui.mouse_released == 0)
            {
                gui.sendPacketUUID((byte)26);
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

            gui.render_text = true;
        }
    }
}
