package com.nali.small.gui.inventory;

import com.nali.list.container.InventoryContainer;
import com.nali.small.entities.memory.ClientEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.messages.works.*;

import static com.nali.small.gui.MixGui.GUIFEATURESLOADER;

public class ProfileGUI
{
    public static void drawGuiContainerBackgroundLayer(MixGui gui)
    {
        SkinningEntities skinningentities = ((InventoryContainer)gui.inventorySlots).skinningentities;
        ClientEntitiesMemory cliententitiesmemory = (ClientEntitiesMemory)skinningentities.bothentitiesmemory;
        int left = gui.getGuiLeft();
        int top = gui.getGuiTop();
        gui.drawTexturedModalRect(left + 49, top + 90, 86, 0, 14, 14);

        if (cliententitiesmemory.workbytes.FOLLOW() == -1)
        {
            gui.drawTexturedModalRect(left + 67, top + 90, 134, 14, 14, 14);
        }
        else
        {
            gui.drawTexturedModalRect(left + 67, top + 90, 30, 0, 14, 14);
        }

        gui.drawTexturedModalRect(left + 85, top + 90, 142, 0, 14, 14);

        if (cliententitiesmemory.workbytes.RANDOM_WALK() == -1)
        {
            gui.drawTexturedModalRect(left + 103, top + 90, 134, 14, 14, 14);
        }
        else
        {
            gui.drawTexturedModalRect(left + 105, top + 92, 52, 14, 12, 12);
        }

        if (cliententitiesmemory.workbytes.RANDOM_LOOK() == -1)
        {
            gui.drawTexturedModalRect(left + 121, top + 90, 134, 14, 14, 14);
        }
        else
        {
            gui.drawTexturedModalRect(left + 121, top + 94, 38, 14, 14, 8);
        }

        if (cliententitiesmemory.workbytes.ATTACK() == -1)
        {
            gui.drawTexturedModalRect(left + 139, top + 90, 134, 14, 14, 14);
        }
        else
        {
            gui.drawTexturedModalRect(left + 139, top + 90, 58, 0, 14, 14);
        }

        if (cliententitiesmemory.workbytes.REVIVE() == -1)
        {
            gui.drawTexturedModalRect(left + 157, top + 90, 134, 14, 14, 14);
        }
        else
        {
            gui.drawTexturedModalRect(left + 157, top + 90, 64, 14, 14, 14);
        }

        if (cliententitiesmemory.workbytes.HEAL() == -1)
        {
            gui.drawTexturedModalRect(left + 157 + 18, top + 90, 134, 14, 14, 14);
        }
        else
        {
            gui.drawTexturedModalRect(left + 157 + 18, top + 90, 114, 0, 14, 14);
        }

        if (cliententitiesmemory.workbytes.PROTECT() == -1)
        {
            gui.drawTexturedModalRect(left + 157 + 18 + 18, top + 90, 134, 14, 14, 14);
        }
        else
        {
            gui.drawTexturedModalRect(left + 157 + 18 + 18, top + 90, 148, 14, 14, 14);
        }

        if (cliententitiesmemory.workbytes.PLAY() == -1)
        {
            gui.drawTexturedModalRect(left + 49, top + 90 + 18, 134, 14, 14, 14);
        }
        else
        {
            gui.drawTexturedModalRect(left + 49, top + 90 + 18, 182, 0, 14, 14);
        }

        if (cliententitiesmemory.workbytes.CARE_OWNER() == -1)
        {
            gui.drawTexturedModalRect(left + 49 + 18, top + 90 + 18, 134, 14, 14, 14);
        }
        else
        {
            gui.drawTexturedModalRect(left + 49 + 18, top + 90 + 18, 72, 0, 14, 14);
        }
    }

    public static void drawScreen(MixGui gui, int mouseX, int mouseY)
    {
        SkinningEntities skinningentities = ((InventoryContainer)gui.inventorySlots).skinningentities;
        ClientEntitiesMemory cliententitiesmemory = (ClientEntitiesMemory)skinningentities.bothentitiesmemory;
        int left = gui.getGuiLeft();
        int top = gui.getGuiTop();

        int x = left + 48, y = top + 89, width = 16, height = 16;
        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
        {
            if (gui.mouse_released == 0)
            {
                gui.sendPacketUUIDInt(cliententitiesmemory.workbytes.SIT());
            }

            if (!(GUIFEATURESLOADER instanceof SitGUIFeatures))
            {
                GUIFEATURESLOADER = new SitGUIFeatures(gui);
            }
            gui.render_text = true;
        }

        x = left + 66;// y = top + 89; width = 16; height = 16;
        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
        {
            int id = cliententitiesmemory.workbytes.FOLLOW();
            if (id != -1)
            {
                if (gui.mouse_released == 0)
                {
                    gui.sendPacketUUID((byte)2);
                    gui.sendPacketUUIDInt(id);
                }

                if (!(GUIFEATURESLOADER instanceof FollowGUIFeatures))
                {
                    GUIFEATURESLOADER = new FollowGUIFeatures(gui);
                }
            }
            else
            {
                if (!(GUIFEATURESLOADER instanceof CantFollowGUIFeatures))
                {
                    GUIFEATURESLOADER = new CantFollowGUIFeatures(gui);
                }
            }
            gui.render_text = true;
        }

        x = left + 84;// y = top + 89; width = 16; height = 16;
        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
        {
            if (gui.mouse_released == 0)
            {
                gui.sendPacketUUIDInt(cliententitiesmemory.workbytes.FIND_ITEM());
            }

            if (!(GUIFEATURESLOADER instanceof FindItemGUIFeatures))
            {
                GUIFEATURESLOADER = new FindItemGUIFeatures(gui);
            }
            gui.render_text = true;
        }

        x = left + 102;// y = top + 89; width = 16; height = 16;
        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
        {
            int id = cliententitiesmemory.workbytes.RANDOM_WALK();
            if (id != -1)
            {
                if (gui.mouse_released == 0)
                {
                    gui.sendPacketUUIDInt(cliententitiesmemory.workbytes.RANDOM_WALK());
                }

                if (!(GUIFEATURESLOADER instanceof RandomWalkGUIFeatures))
                {
                    GUIFEATURESLOADER = new RandomWalkGUIFeatures(gui);
                }
            }
            else
            {
                if (!(GUIFEATURESLOADER instanceof CantRandomWalkGUIFeatures))
                {
                    GUIFEATURESLOADER = new CantRandomWalkGUIFeatures(gui);
                }
            }
            gui.render_text = true;
        }

        x = left + 120;// y = top + 89; width = 16; height = 16;
        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
        {
            int id = cliententitiesmemory.workbytes.RANDOM_LOOK();
            if (id != -1)
            {
                if (gui.mouse_released == 0)
                {
                    gui.sendPacketUUIDInt(cliententitiesmemory.workbytes.RANDOM_LOOK());
                }

                if (!(GUIFEATURESLOADER instanceof RandomLookGUIFeatures))
                {
                    GUIFEATURESLOADER = new RandomLookGUIFeatures(gui);
                }
            }
            else
            {
                if (!(GUIFEATURESLOADER instanceof CantRandomLookGUIFeatures))
                {
                    GUIFEATURESLOADER = new CantRandomLookGUIFeatures(gui);
                }
            }
            gui.render_text = true;
        }

        x = left + 138;// y = top + 89; width = 16; height = 16;
        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
        {
            int id = cliententitiesmemory.workbytes.ATTACK();
            if (id != -1)
            {
                if (gui.mouse_released == 0)
                {
                    gui.sendPacketUUIDInt(id);
                }

                if (!(GUIFEATURESLOADER instanceof AttackGUIFeatures))
                {
                    GUIFEATURESLOADER = new AttackGUIFeatures(gui);
                }
            }
            else
            {
                if (!(GUIFEATURESLOADER instanceof CantAttackGUIFeatures))
                {
                    GUIFEATURESLOADER = new CantAttackGUIFeatures(gui);
                }
            }
            gui.render_text = true;
        }

        x = left + 156;// y = top + 89; width = 16; height = 16;
        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
        {
            int id = cliententitiesmemory.workbytes.REVIVE();
            if (id != -1)
            {
                if (gui.mouse_released == 0)
                {
                    gui.sendPacketUUID((byte)2);
                    gui.sendPacketUUIDInt(cliententitiesmemory.workbytes.REVIVE());
                }

                if (!(GUIFEATURESLOADER instanceof ReviveGUIFeatures))
                {
                    GUIFEATURESLOADER = new ReviveGUIFeatures(gui);
                }
            }
            else
            {
                if (!(GUIFEATURESLOADER instanceof CantReviveGUIFeatures))
                {
                    GUIFEATURESLOADER = new CantReviveGUIFeatures(gui);
                }
            }
            gui.render_text = true;
        }

        x = left + 156 + 18;// y = top + 89; width = 16; height = 16;
        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
        {
            int id = cliententitiesmemory.workbytes.HEAL();
            if (id != -1)
            {
                if (gui.mouse_released == 0)
                {
                    gui.sendPacketUUIDInt(id);
                }

                if (!(GUIFEATURESLOADER instanceof HealGUIFeatures))
                {
                    GUIFEATURESLOADER = new HealGUIFeatures(gui);
                }
            }
            else
            {
                if (!(GUIFEATURESLOADER instanceof CantHealGUIFeatures))
                {
                    GUIFEATURESLOADER = new CantHealGUIFeatures(gui);
                }
            }
            gui.render_text = true;
        }

        x = left + 156 + 18 + 18;// y = top + 89; width = 16; height = 16;
        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
        {
            int id = cliententitiesmemory.workbytes.PROTECT();
            if (id != -1)
            {
                if (gui.mouse_released == 0)
                {
                    gui.sendPacketUUIDInt(id);
                }

                if (!(GUIFEATURESLOADER instanceof ProtectGUIFeatures))
                {
                    GUIFEATURESLOADER = new ProtectGUIFeatures(gui);
                }
            }
            else
            {
                if (!(GUIFEATURESLOADER instanceof CantProtectGUIFeatures))
                {
                    GUIFEATURESLOADER = new CantProtectGUIFeatures(gui);
                }
            }
            gui.render_text = true;
        }

        x = left + 48; y = top + 107;// width = 16; height = 16;
        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
        {
            int id = cliententitiesmemory.workbytes.PLAY();
            if (id != -1)
            {
                if (gui.mouse_released == 0)
                {
                    gui.sendPacketUUIDInt(cliententitiesmemory.workbytes.PLAY());
                }

                if (!(GUIFEATURESLOADER instanceof PlayGUIFeatures))
                {
                    GUIFEATURESLOADER = new PlayGUIFeatures(gui);
                }
            }
            else
            {
                if (!(GUIFEATURESLOADER instanceof CantPlayGUIFeatures))
                {
                    GUIFEATURESLOADER = new CantPlayGUIFeatures(gui);
                }
            }
            gui.render_text = true;
        }

        x = left + 66; y = top + 107;// width = 16; height = 16;
        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
        {
            int id = cliententitiesmemory.workbytes.CARE_OWNER();
            if (id != -1)
            {
                if (gui.mouse_released == 0)
                {
                    gui.sendPacketUUID((byte)2);
                    gui.sendPacketUUIDInt(cliententitiesmemory.workbytes.CARE_OWNER());
                }

                if (!(GUIFEATURESLOADER instanceof CareOwnerGUIFeatures))
                {
                    GUIFEATURESLOADER = new CareOwnerGUIFeatures(gui);
                }
            }
            else
            {
                if (!(GUIFEATURESLOADER instanceof CantCareOwnerGUIFeatures))
                {
                    GUIFEATURESLOADER = new CantCareOwnerGUIFeatures(gui);
                }
            }
            gui.render_text = true;
        }
    }
}
