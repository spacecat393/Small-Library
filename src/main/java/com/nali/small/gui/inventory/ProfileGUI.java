//package com.nali.small.gui.inventory;
//
//import com.nali.list.container.InventoryContainer;
//import com.nali.list.network.method.server.*;
//import com.nali.small.entity.memo.client.ClientLe;
//import com.nali.small.entity.EntityLeInv;
//import com.nali.small.gui.MixGui;
//import com.nali.small.gui.features.messages.works.*;
//
//import static com.nali.small.gui.MixGui.GUIFEATURESLOADER;
//
//public class ProfileGUI
//{
//    public static void drawGuiContainerBackgroundLayer(MixGui gui)
//    {
//        EntityLeInv skinningentities = ((InventoryContainer)gui.inventorySlots).skinningentities;
//        ClientLe cliententitiesmemory = (ClientLe)skinningentities.bothentitiesmemory;
//        int left = gui.getGuiLeft();
//        int top = gui.getGuiTop();
////        gui.drawTexturedModalRect(left + 49, top + 90, 86, 0, 14, 14);
////        gui.drawTexturedModalRect(left + 67, top + 90, 30, 0, 14, 14);
////        gui.drawTexturedModalRect(left + 85, top + 90, 142, 0, 14, 14);
////        gui.drawTexturedModalRect(left + 105, top + 92, 52, 14, 12, 12);
////        gui.drawTexturedModalRect(left + 121, top + 94, 38, 14, 14, 8);
//
////        if (cliententitiesmemory.workbytes.ATTACK() == -1)
////        {
////            gui.drawTexturedModalRect(left + 139, top + 90, 134, 14, 14, 14);
////        }
////        else
////        {
////            gui.drawTexturedModalRect(left + 139, top + 90, 58, 0, 14, 14);
////        }
//
////        gui.drawTexturedModalRect(left + 157, top + 90, 64, 14, 14, 14);
//
////        if (cliententitiesmemory.workbytes.HEAL() == -1)
////        {
////            gui.drawTexturedModalRect(left + 157 + 18, top + 90, 134, 14, 14, 14);
////        }
////        else
////        {
////            gui.drawTexturedModalRect(left + 157 + 18, top + 90, 114, 0, 14, 14);
////        }
//
////        if (cliententitiesmemory.workbytes.PROTECT() == -1)
////        {
////            gui.drawTexturedModalRect(left + 157 + 18 + 18, top + 90, 134, 14, 14, 14);
////        }
////        else
////        {
////            gui.drawTexturedModalRect(left + 157 + 18 + 18, top + 90, 148, 14, 14, 14);
////        }
//
////        if (cliententitiesmemory.workbytes.PLAY() == -1)
////        {
////            gui.drawTexturedModalRect(left + 49, top + 90 + 18, 134, 14, 14, 14);
////        }
////        else
////        {
////            gui.drawTexturedModalRect(left + 49, top + 90 + 18, 182, 0, 14, 14);
////        }
//
////        if (cliententitiesmemory.workbytes.CARE_OWNER() == -1)
////        {
////            gui.drawTexturedModalRect(left + 49 + 18, top + 90 + 18, 134, 14, 14, 14);
////        }
////        else
////        {
////            gui.drawTexturedModalRect(left + 49 + 18, top + 90 + 18, 72, 0, 14, 14);
////        }
//
////        gui.drawTexturedModalRect(left + 49 + 18 + 18, top + 90 + 18, 128, 0, 14, 14);
////        gui.drawTexturedModalRect(left + 49 + 18 + 18 + 18, top + 90 + 18, 196, 0, 14, 14);
//        gui.drawTexturedModalRect(left + 49 + 18 + 18 + 18 + 18, top + 90 + 18, 100, 0, 14, 14);
//        gui.drawTexturedModalRect(left + 49 + 18 + 18 + 18 + 18 + 18, top + 90 + 18, 156, 0, 14, 14);
////        gui.drawTexturedModalRect(left + 49 + 18 + 18 + 18 + 18 + 18 + 18, top + 90 + 18, 162, 14, 14, 14);
////        gui.drawTexturedModalRect(left + 49 + 18 + 18 + 18 + 18 + 18 + 18 + 18 + 1, top + 90 + 18, 170, 0, 12, 14);
//        gui.drawTexturedModalRect(left + 49 + 18 + 18 + 18 + 18 + 18 + 18 + 18 + 18, top + 90 + 18, 238, 0, 14, 14);
//    }
//
//    public static void drawScreen(MixGui gui, int mouseX, int mouseY)
//    {
//        EntityLeInv skinningentities = ((InventoryContainer)gui.inventorySlots).skinningentities;
//        ClientLe cliententitiesmemory = (ClientLe)skinningentities.bothentitiesmemory;
//        int left = gui.getGuiLeft();
//        int top = gui.getGuiTop();
//
////        int x = left + 48, y = top + 89, width = 16, height = 16;
////        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
////        {
////            if (gui.mouse_released == 0)
////            {
////                gui.sendPacketUUIDInt(cliententitiesmemory.workbytes.SIT());
////            }
////
////            if (!(GUIFEATURESLOADER instanceof SitGUIFeatures))
////            {
////                GUIFEATURESLOADER = new SitGUIFeatures(gui);
////            }
////            gui.state |= 2;
////        }
//
////        x = left + 66;// y = top + 89; width = 16; height = 16;
////        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
////        {
////            gui.state |= 1;
//////            int id = cliententitiesmemory.workbytes.FOLLOW();
//////            if (id != -1)
//////            {
////            if (gui.mouse_released == 0)
////            {
////                gui.sendPacketUUID(SetOwner.ID);
////                gui.sendPacketUUIDInt(cliententitiesmemory.workbytes.FOLLOW());
////            }
////            else if (gui.mouse_released == 1)
////            {
////                gui.sendPacketUUID(FetchFollow.ID);
////            }
////
////            if (!(GUIFEATURESLOADER instanceof FollowGUIFeatures))
////            {
////                GUIFEATURESLOADER = new FollowGUIFeatures(gui);
////            }
//////            }
//////            else
//////            {
//////                if (!(GUIFEATURESLOADER instanceof CantFollowGUIFeatures))
//////                {
//////                    GUIFEATURESLOADER = new CantFollowGUIFeatures(gui);
//////                }
//////            }
////            gui.state |= 2;
////        }
//
////        x = left + 84;// y = top + 89; width = 16; height = 16;
////        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
////        {
////            gui.state |= 1;
////            if (gui.mouse_released == 0)
////            {
////                gui.sendPacketUUIDInt(cliententitiesmemory.workbytes.GET_ITEM());
////            }
////            else if (gui.mouse_released == 1)
////            {
////                gui.sendPacketUUID(FetchGetItem.ID);
////            }
////
////            if (!(GUIFEATURESLOADER instanceof GetItemGUIFeatures))
////            {
////                GUIFEATURESLOADER = new GetItemGUIFeatures(gui);
////            }
////            gui.state |= 2;
////        }
//
////        x = left + 102;// y = top + 89; width = 16; height = 16;
////        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
////        {
//////            int id = cliententitiesmemory.workbytes.RANDOM_WALK();
//////            if (id != -1)
//////            {
////            if (gui.mouse_released == 0)
////            {
////                gui.sendPacketUUIDInt(cliententitiesmemory.workbytes.RANDOM_WALK());
////            }
////
////            if (!(GUIFEATURESLOADER instanceof RandomWalkGUIFeatures))
////            {
////                GUIFEATURESLOADER = new RandomWalkGUIFeatures(gui);
////            }
//////            }
//////            else
//////            {
//////                if (!(GUIFEATURESLOADER instanceof CantRandomWalkGUIFeatures))
//////                {
//////                    GUIFEATURESLOADER = new CantRandomWalkGUIFeatures(gui);
//////                }
//////            }
////            gui.state |= 2;
////        }
//
////        x = left + 120;// y = top + 89; width = 16; height = 16;
////        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
////        {
//////            int id = cliententitiesmemory.workbytes.RANDOM_LOOK();
//////            if (id != -1)
//////            {
////            if (gui.mouse_released == 0)
////            {
////                gui.sendPacketUUIDInt(cliententitiesmemory.workbytes.RANDOM_LOOK());
////            }
////
////            if (!(GUIFEATURESLOADER instanceof RandomLookGUIFeatures))
////            {
////                GUIFEATURESLOADER = new RandomLookGUIFeatures(gui);
////            }
//////            }
//////            else
//////            {
//////                if (!(GUIFEATURESLOADER instanceof CantRandomLookGUIFeatures))
//////                {
//////                    GUIFEATURESLOADER = new CantRandomLookGUIFeatures(gui);
//////                }
//////            }
////            gui.state |= 2;
////        }
//
////        x = left + 138;// y = top + 89; width = 16; height = 16;
////        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
////        {
////            gui.state |= 1;
////            byte id = cliententitiesmemory.workbytes.ATTACK();
////            if (id != -1)
////            {
////                if (gui.mouse_released == 0)
////                {
////                    gui.sendPacketUUIDInt(id);
////                }
////                else if (gui.mouse_released == 1)
////                {
////                    gui.sendPacketUUID(FetchAttack.ID);
////                }
////
////                if (!(GUIFEATURESLOADER instanceof AttackGUIFeatures))
////                {
////                    GUIFEATURESLOADER = new AttackGUIFeatures(gui);
////                }
////            }
////            else
////            {
////                if (!(GUIFEATURESLOADER instanceof CantAttackGUIFeatures))
////                {
////                    GUIFEATURESLOADER = new CantAttackGUIFeatures(gui);
////                }
////            }
////            gui.state |= 2;
////        }
//
////        x = left + 156;// y = top + 89; width = 16; height = 16;
////        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
////        {
//////            int id = cliententitiesmemory.workbytes.REVIVE();
//////            if (id != -1)
//////            {
////            if (gui.mouse_released == 0)
////            {
////                gui.sendPacketUUID(SetOwner.ID);
////                gui.sendPacketUUIDInt(cliententitiesmemory.workbytes.REVIVE());
////            }
////
////            if (!(GUIFEATURESLOADER instanceof ReviveGUIFeatures))
////            {
////                GUIFEATURESLOADER = new ReviveGUIFeatures(gui);
////            }
//////            }
//////            else
//////            {
//////                if (!(GUIFEATURESLOADER instanceof CantReviveGUIFeatures))
//////                {
//////                    GUIFEATURESLOADER = new CantReviveGUIFeatures(gui);
//////                }
//////            }
////            gui.state |= 2;
////        }
//
////        x = left + 156 + 18;// y = top + 89; width = 16; height = 16;
////        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
////        {
////            byte id = cliententitiesmemory.workbytes.HEAL();
////            if (id != -1)
////            {
////                if (gui.mouse_released == 0)
////                {
////                    gui.sendPacketUUIDInt(id);
////                }
////
////                if (!(GUIFEATURESLOADER instanceof HealGUIFeatures))
////                {
////                    GUIFEATURESLOADER = new HealGUIFeatures(gui);
////                }
////            }
////            else
////            {
////                if (!(GUIFEATURESLOADER instanceof CantHealGUIFeatures))
////                {
////                    GUIFEATURESLOADER = new CantHealGUIFeatures(gui);
////                }
////            }
////            gui.state |= 2;
////        }
//
////        x = left + 156 + 18 + 18;// y = top + 89; width = 16; height = 16;
////        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
////        {
////            byte id = cliententitiesmemory.workbytes.PROTECT();
////            if (id != -1)
////            {
////                if (gui.mouse_released == 0)
////                {
////                    gui.sendPacketUUIDInt(id);
////                }
////
////                if (!(GUIFEATURESLOADER instanceof ProtectGUIFeatures))
////                {
////                    GUIFEATURESLOADER = new ProtectGUIFeatures(gui);
////                }
////            }
////            else
////            {
////                if (!(GUIFEATURESLOADER instanceof CantProtectGUIFeatures))
////                {
////                    GUIFEATURESLOADER = new CantProtectGUIFeatures(gui);
////                }
////            }
////            gui.state |= 2;
////        }
//
////        x = left + 48; y = top + 107;// width = 16; height = 16;
////        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
////        {
////            byte id = cliententitiesmemory.workbytes.PLAY();
////            if (id != -1)
////            {
////                if (gui.mouse_released == 0)
////                {
////                    gui.sendPacketUUIDInt(id);
////                }
////
////                if (!(GUIFEATURESLOADER instanceof PlayGUIFeatures))
////                {
////                    GUIFEATURESLOADER = new PlayGUIFeatures(gui);
////                }
////            }
////            else
////            {
////                if (!(GUIFEATURESLOADER instanceof CantPlayGUIFeatures))
////                {
////                    GUIFEATURESLOADER = new CantPlayGUIFeatures(gui);
////                }
////            }
////            gui.state |= 2;
////        }
//
////        x = left + 66;// y = top + 107;// width = 16; height = 16;
////        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
////        {
////            byte id = cliententitiesmemory.workbytes.CARE_OWNER();
////            if (id != -1)
////            {
////                if (gui.mouse_released == 0)
////                {
////                    gui.sendPacketUUID(SetOwner.ID);
////                    gui.sendPacketUUIDInt(id);
////                }
////
////                if (!(GUIFEATURESLOADER instanceof CareOwnerGUIFeatures))
////                {
////                    GUIFEATURESLOADER = new CareOwnerGUIFeatures(gui);
////                }
////            }
////            else
////            {
////                if (!(GUIFEATURESLOADER instanceof CantCareOwnerGUIFeatures))
////                {
////                    GUIFEATURESLOADER = new CantCareOwnerGUIFeatures(gui);
////                }
////            }
////            gui.state |= 2;
////        }
//
////        x = left + 66 + 18;// y = top + 107;// width = 16; height = 16;
////        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
////        {
////            if (gui.mouse_released == 0)
////            {
////                gui.sendPacketUUIDInt(cliententitiesmemory.workbytes.WALK_TO());
////            }
////
////            if (!(GUIFEATURESLOADER instanceof WalkToGUIFeatures))
////            {
////                GUIFEATURESLOADER = new WalkToGUIFeatures(gui);
////            }
////            gui.state |= 2;
////        }
//
////        x = left + 66 + 18 + 18;// y = top + 107;// width = 16; height = 16;
////        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
////        {
////            if (gui.mouse_released == 0)
////            {
////                gui.sendPacketUUIDInt(cliententitiesmemory.workbytes.LOOK_TO());
////            }
////
////            if (!(GUIFEATURESLOADER instanceof LookToGUIFeatures))
////            {
////                GUIFEATURESLOADER = new LookToGUIFeatures(gui);
////            }
////            gui.state |= 2;
////        }
//
////        x = left + 66 + 18 + 18 + 18;// y = top + 107;// width = 16; height = 16;
////        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
////        {
////            gui.state |= 1;
////            if (gui.mouse_released == 0)
////            {
////                gui.sendPacketUUIDInt(cliententitiesmemory.workbytes.MINE());
////            }
////
////            if (!(GUIFEATURESLOADER instanceof MineGUIFeatures))
////            {
////                GUIFEATURESLOADER = new MineGUIFeatures(gui);
////            }
////            gui.state |= 2;
////        }
//
//        x = left + 66 + 18 + 18 + 18 + 18;// y = top + 107;// width = 16; height = 16;
//        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
//        {
//            if (gui.mouse_released == 0)
//            {
//                gui.sendPacketUUIDInt(cliententitiesmemory.workbytes.PLANT());
//            }
//
//            if (!(GUIFEATURESLOADER instanceof PlantGUIFeatures))
//            {
//                GUIFEATURESLOADER = new PlantGUIFeatures(gui);
//            }
//            gui.state |= 2;
//        }
//
//        x = left + 66 + 18 + 18 + 18 + 18 + 18;// y = top + 107;// width = 16; height = 16;
//        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
//        {
//            if (gui.mouse_released == 0)
//            {
//                gui.sendPacketUUIDInt(cliententitiesmemory.workbytes.FISHING());
//            }
//
//            if (!(GUIFEATURESLOADER instanceof FishingGUIFeatures))
//            {
//                GUIFEATURESLOADER = new FishingGUIFeatures(gui);
//            }
//            gui.state |= 2;
//        }
//
////        x = left + 66 + 18 + 18 + 18 + 18 + 18 + 18;// y = top + 107;// width = 16; height = 16;
////        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
////        {
////            if (gui.mouse_released == 0)
////            {
////                gui.sendPacketUUIDInt(cliententitiesmemory.workbytes.USE_TO());
////            }
////
////            if (!(GUIFEATURESLOADER instanceof UseToGUIFeatures))
////            {
////                GUIFEATURESLOADER = new UseToGUIFeatures(gui);
////            }
////            gui.state |= 2;
////        }
//
////        x = left + 66 + 18 + 18 + 18 + 18 + 18 + 18 + 18;// y = top + 107;// width = 16; height = 16;
////        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
////        {
////            gui.state |= 1;
////            if (gui.mouse_released == 0)
////            {
////                gui.sendPacketUUIDInt(cliententitiesmemory.workbytes.MANAGE_ITEM());
////            }
////            else if (gui.mouse_released == 1)
////            {
////                gui.sendPacketUUID(FetchManageItem.ID);
////            }
////
////            if (!(GUIFEATURESLOADER instanceof ManageItemGUIFeatures))
////            {
////                GUIFEATURESLOADER = new ManageItemGUIFeatures(gui);
////            }
////            gui.state |= 2;
////        }
//    }
//}
