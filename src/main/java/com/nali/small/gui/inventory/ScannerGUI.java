package com.nali.small.gui.inventory;

import com.nali.list.netmethods.servermessage.ClearTarget;
import com.nali.list.netmethods.servermessage.ClearTroublemaker;
import com.nali.list.netmethods.servermessage.ViewTarget;
import com.nali.list.netmethods.servermessage.ViewTroublemaker;
import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.messages.inventory.*;

import static com.nali.small.gui.MixGui.GUIFEATURESLOADER;

public class ScannerGUI
{
    public static void drawGuiContainerBackgroundLayer(MixGui gui)
    {
        int left = gui.getGuiLeft();
        int top = gui.getGuiTop();
        gui.drawTexturedModalRect(left + 49, top + 90, 106, 14, 14, 14);
        gui.drawTexturedModalRect(left + 67, top + 90, 78, 14, 14, 14);
        gui.drawTexturedModalRect(left + 67 + 18, top + 90, 16, 0, 14, 14);
        gui.drawTexturedModalRect(left + 49, top + 108, 92, 14, 14, 14);
        gui.drawTexturedModalRect(left + 67, top + 108, 92, 14, 14, 14);
        gui.drawTexturedModalRect(left + 49, top + 126, 120, 14, 14, 14);
        gui.drawTexturedModalRect(left + 67, top + 126, 120, 14, 14, 14);
    }

    public static void drawScreen(MixGui gui, int mouseX, int mouseY)
    {
        int left = gui.getGuiLeft();
        int top = gui.getGuiTop();

        int x = left + 48, y = top + 89, width = 16, height = 16;
        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
        {
            gui.copyOrPaste();

            gui.message_state = true;

            if (!(GUIFEATURESLOADER instanceof AddTargetGUIFeatures))
            {
                GUIFEATURESLOADER = new AddTargetGUIFeatures(gui);
            }
            gui.render_text = true;
        }

        x = left + 66;// y = top + 89; width = 16; height = 16;
        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
        {
            gui.copyOrPaste();

            gui.message_state = true;

            if (!(GUIFEATURESLOADER instanceof AddTroublemakerGUIFeatures))
            {
                GUIFEATURESLOADER = new AddTroublemakerGUIFeatures(gui);
            }
            gui.render_text = true;
        }

        x = left + 84;// y = top + 89; width = 16; height = 16;
        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
        {
            gui.copyOrPaste();

            gui.message_state = true;

            if (!(GUIFEATURESLOADER instanceof EntityListGUIFeatures))
            {
                GUIFEATURESLOADER = new EntityListGUIFeatures(gui);
            }
            gui.render_text = true;
        }

        x = left + 48; y = top + 107;// width = 16; height = 16;
        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
        {
            gui.copyOrPaste();

            gui.message_state = true;

            if (!(GUIFEATURESLOADER instanceof RemoveTargetGUIFeatures))
            {
                GUIFEATURESLOADER = new RemoveTargetGUIFeatures(gui);
            }
            gui.render_text = true;
        }

        /*x = left + 48; */y = top + 125;// width = 16; height = 16;
        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
        {
            if (gui.mouse_released == 0)
            {
                gui.sendPacketUUID(ViewTarget.ID);
            }
            else if (gui.mouse_released == 1)
            {
                gui.sendPacketUUID(ClearTarget.ID);
            }

            if (!(GUIFEATURESLOADER instanceof TargetGUIFeatures))
            {
                GUIFEATURESLOADER = new TargetGUIFeatures(gui);
            }
            gui.render_text = true;
        }

        x = left + 66; y = top + 107;// width = 16; height = 16;
        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
        {
            gui.copyOrPaste();

            gui.message_state = true;

            if (!(GUIFEATURESLOADER instanceof RemoveTroublemakerGUIFeatures))
            {
                GUIFEATURESLOADER = new RemoveTroublemakerGUIFeatures(gui);
            }
            gui.render_text = true;
        }

        /*x = left + 66; */y = top + 125;// width = 16; height = 16;
        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
        {
            if (gui.mouse_released == 0)
            {
                gui.sendPacketUUID(ViewTroublemaker.ID);
            }
            else if (gui.mouse_released == 1)
            {
                gui.sendPacketUUID(ClearTroublemaker.ID);
            }

            if (!(GUIFEATURESLOADER instanceof TroublemakerGUIFeatures))
            {
                GUIFEATURESLOADER = new TroublemakerGUIFeatures(gui);
            }
            gui.render_text = true;
        }
    }
}
