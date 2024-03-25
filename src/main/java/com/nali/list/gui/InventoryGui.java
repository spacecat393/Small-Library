package com.nali.list.gui;

import com.nali.list.container.InventoryContainer;
import com.nali.list.messages.ServerMessage;
import com.nali.list.netmethods.servermessage.*;
import com.nali.list.render.BoxRender;
import com.nali.render.SkinningRender;
import com.nali.small.entities.memory.ClientEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.entities.skinning.render.SkinningEntitiesRender;
import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.messages.AttributesGUIFeatures;
import com.nali.small.gui.features.messages.EffectsGUIFeatures;
import com.nali.small.gui.features.messages.HPGUIFeatures;
import com.nali.small.gui.features.messages.inventory.BoxGUIFeatures;
import com.nali.small.gui.features.messages.inventory.MenuGUIFeatures;
import com.nali.small.gui.features.messages.inventory.TPGUIFeatures;
import com.nali.small.gui.features.messages.inventory.WorkGUIFeatures;
import com.nali.small.gui.features.messages.player.MimiTalkGUIFeatures;
import com.nali.small.gui.features.messages.works.LockDamageGUIFeatures;
import com.nali.small.gui.features.messages.works.LockInventoryGUIFeatures;
import com.nali.small.gui.features.messages.works.ManageItemGUIFeatures;
import com.nali.small.gui.inventory.ProfileGUI;
import com.nali.small.gui.inventory.ScannerGUI;
import com.nali.small.gui.inventory.SpecialStatGUI;
import com.nali.small.networks.NetworksRegistry;
import com.nali.small.system.Reference;
import com.nali.system.bytes.BytesWriter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.io.IOException;

import static com.nali.system.Timing.TD;
import static com.nali.system.opengl.memory.OpenGLCurrentMemory.GL_CURRENT_COLOR;
import static com.nali.system.opengl.memory.OpenGLCurrentMemory.OPENGL_FIXED_PIPE_FLOATBUFFER;

@SideOnly(Side.CLIENT)
public class InventoryGui extends MixGui
{
    public static ResourceLocation GUI_RESOURCELOCATION = new ResourceLocation(Reference.MOD_ID, "textures/gui/inventory.png");
    public static byte PAGE;
    public SkinningEntitiesRender skinningentitiesrender;
    public float px/*, py*/;
    public BoxRender boxrender = new BoxRender();

    public InventoryGui(IInventory iinventory, SkinningEntities skinningentities)
    {
        super(new InventoryContainer(iinventory, skinningentities, Minecraft.getMinecraft().player));
        this.mc = Minecraft.getMinecraft();
        ScaledResolution scaledresolution = new ScaledResolution(this.mc);
        this.setWorldAndResolution(Minecraft.getMinecraft(), scaledresolution.getScaledWidth(), scaledresolution.getScaledHeight());

        this.skinningentitiesrender = (SkinningEntitiesRender)this.mc.getRenderManager().getEntityRenderObject(skinningentities);

        this.xSize = 256;
        this.ySize = 256;
        this.boxrender.objectscreendraw.rz = 45.0F * 3.0F;
        float s = -3.5F;
        this.boxrender.objectscreendraw.sx = s;
        this.boxrender.objectscreendraw.sy = s;
        this.boxrender.objectscreendraw.sz = s;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.block_mouse_clicked = false;
        this.message_state = -1;
        this.render_text = false;

        SkinningEntities skinningentities = ((InventoryContainer)this.inventorySlots).skinningentities;
        ClientEntitiesMemory cliententitiesmemory = (ClientEntitiesMemory)skinningentities.bothentitiesmemory;
        SkinningRender skinningrender = (SkinningRender)cliententitiesmemory.objectrender;
        if (!skinningrender.entitiesrendermemory.should_render)
        {
            this.skinningentitiesrender.updateData(skinningentities, this.mc.getRenderPartialTicks());
        }

        skinningrender.objectscreendraw.x = this.guiLeft + 127.5F;
        skinningrender.objectscreendraw.y = this.guiTop + 72;
        skinningrender.lig_b = -1.0F;
        skinningrender.objectscreendraw.renderScreen();

        this.boxrender.objectscreendraw.x = this.guiLeft + 84 + 6.5F + 0.5F;
        this.boxrender.objectscreendraw.y = this.guiTop + 33 + 18 + 18 + 6.5F + 2.5F;

        this.boxrender.objectscreendraw.renderScreen();

        if (PAGE == 0)
        {
            super.drawScreen(mouseX, mouseY, partialTicks);
        }
        else
        {
            this.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
        }

        int x = this.guiLeft + 100, y = this.guiTop + 26, width = 56, height = 49;
        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
        {
            if (this.mouse_clicked == 0)
            {
                skinningrender.objectscreendraw.rz += ((mouseX - this.px) * 10.0F) * TD;
//                skinningrender.objectscreendraw.ry += ((mouseY - this.py) * 10.0F) * TD;
            }
        }

        this.px = mouseX;
//        this.py = mouseY;

        if (PAGE == 0)
        {
            this.renderHoveredToolTip(mouseX, mouseY);
        }
        else if (PAGE == 1 || PAGE == 2 || PAGE == 3)
        {
            this.setMessage();
        }

        this.renderEntitiesName(skinningentities, this.guiLeft + 100, this.guiTop + 75, 56, 11, mouseX, mouseY);

//        x = this.guiLeft + 63; y = this.guiTop + 25; width = 18; height = 19;
        x = this.guiLeft + 102; y = this.guiTop + 28; width = 14; height = 14;
        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
        {
            if (this.mouse_released == 0)
            {
                this.sendPacketUUID(TP.ID);
            }

            if (!(GUIFEATURESLOADER instanceof TPGUIFeatures))
            {
                GUIFEATURESLOADER = new TPGUIFeatures(this);
            }
            this.render_text = true;
        }

        x = this.guiLeft + 47; y = this.guiTop + 25; width = 5; height = 62;
        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
        {
            if (!(GUIFEATURESLOADER instanceof HPGUIFeatures))
            {
                GUIFEATURESLOADER = new HPGUIFeatures(this);
            }
            this.render_text = true;
        }

        x = this.guiLeft + 158;/* y = this.guiTop + 25;*/ width = 9; height = 19;
        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
        {
            if (!(GUIFEATURESLOADER instanceof AttributesGUIFeatures))
            {
                GUIFEATURESLOADER = new AttributesGUIFeatures(this);
            }
            this.render_text = true;
        }

        x = this.guiLeft + 204; y = this.guiTop + 144; width = 5; height = 6;
        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
        {
            if (this.mouse_released == 0)
            {
                this.sendPacketUUID(GetEffects.ID);
            }

            if (!(GUIFEATURESLOADER instanceof EffectsGUIFeatures))
            {
                GUIFEATURESLOADER = new EffectsGUIFeatures(this);
            }
            this.render_text = true;
        }

        x = this.guiLeft + 57; y = this.guiTop + 27; width = 5; height = 6;
        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
        {
            if (this.mouse_released == 0)
            {
                this.sendPacketUUID(SetOwner.ID);
                this.sendPacketUUIDInt(cliententitiesmemory.workbytes.LOCK_INVENTORY());
            }

            if (!(GUIFEATURESLOADER instanceof LockInventoryGUIFeatures))
            {
                GUIFEATURESLOADER = new LockInventoryGUIFeatures(this);
            }
            this.render_text = true;
        }

        /*x = this.guiLeft + 57; */y = this.guiTop + 35;/* width = 5; height = 6;*/
        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
        {
            if (this.mouse_released == 0)
            {
                this.sendPacketUUID(SetOwner.ID);
                this.sendPacketUUIDInt(cliententitiesmemory.workbytes.LOCK_DAMAGE());
            }

            if (!(GUIFEATURESLOADER instanceof LockDamageGUIFeatures))
            {
                GUIFEATURESLOADER = new LockDamageGUIFeatures(this);
            }
            this.render_text = true;
        }

        x = this.guiLeft + 82; y = this.guiTop + 31; width = 18; height = 18;
        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
        {
            if (this.mouse_released == 0)
            {
                ++PAGE;
                if (PAGE == 4)
                {
                    PAGE = 0;
                }
            }

            if (!(GUIFEATURESLOADER instanceof MenuGUIFeatures))
            {
                GUIFEATURESLOADER = new MenuGUIFeatures(this);
            }
            this.render_text = true;
        }

        /*x = this.guiLeft + 82; */y = this.guiTop + 31 + 18;// width = 18; height = 18;
        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
        {
            if (this.mouse_released == 0)
            {
                this.sendPacket1(OpenPlayerGUI.ID);
            }

            if (!(GUIFEATURESLOADER instanceof MimiTalkGUIFeatures))
            {
                GUIFEATURESLOADER = new MimiTalkGUIFeatures(this);
            }
            this.render_text = true;
        }

        /*x = this.guiLeft + 82; */y = this.guiTop + 31 + 18 + 18;// width = 18; height = 18;
        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
        {
            if (this.mouse_released == 0)
            {
                this.sendPacketUUID(PutToBox.ID);
            }

            if (!(GUIFEATURESLOADER instanceof BoxGUIFeatures))
            {
                GUIFEATURESLOADER = new BoxGUIFeatures(this);
            }
            this.render_text = true;
        }

        x = this.guiLeft + 156; y = this.guiTop + 47; width = 28; height = 39;
        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
        {
            if (this.mouse_released == 1)
            {
                this.sendPacketUUID(ResetBytes.ID);
            }

            if (!(GUIFEATURESLOADER instanceof WorkGUIFeatures))
            {
                GUIFEATURESLOADER = new WorkGUIFeatures(this);
            }
            this.render_text = true;
        }

        switch (PAGE)
        {
//            case 0:
//            {
//                break;
//            }
            case 1:
            {
                ProfileGUI.drawScreen(this, mouseX, mouseY);
                break;
            }
            case 2:
            {
                ScannerGUI.drawScreen(this, mouseX, mouseY);
                break;
            }
            case 3:
            {
                SpecialStatGUI.drawScreen(this, mouseX, mouseY);
            }
            default:
            {
                break;
            }
        }

        if (this.render_text)
        {
            GUIFEATURESLOADER.drawText(mouseX, mouseY);
        }

        this.mouse_released = -1;
    }

    @Override
    public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        boolean gl_blend = GL11.glIsEnabled(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_BLEND);

//        this.mc.getTextureManager().bindTexture(GUI0_RESOURCELOCATION);
//
//        blit(this.guiLeft, this.guiTop, 0, 0, 16, 16, 16, 16);

        this.mc.getTextureManager().bindTexture(GUI_RESOURCELOCATION);
        this.drawTexturedModalRect(this.guiLeft + 43, this.guiTop + 25, 86, 50, 170, 206);

        float tx = this.guiLeft + 84;

        switch (PAGE)
        {
            case 0:
            {
                this.drawTexturedModalRect(tx, this.guiTop + 33, 238, 0, 14, 14);
                break;
            }
            case 1:
            {
                this.drawTexturedModalRect(tx, this.guiTop + 33, 16, 0, 14, 14);
                break;
            }
            case 2:
            {
                this.drawTexturedModalRect(tx, this.guiTop + 33, 44, 0, 14, 14);
                break;
            }
            case 3:
            {
                this.drawTexturedModalRect(tx, this.guiTop + 33, 16, 14, 14, 14);
                break;
            }
            default:
            {
                break;
            }
        }

        this.drawTexturedModalRect(tx, this.guiTop + 53/*33 + 18 + 2*/, 224, 0, 14, 11);

        for (int i = 0 + 1; i < 36 + 1; ++i)
        {
            Slot slot = this.inventorySlots.inventorySlots.get(i);
            if (slot.getStack().isEmpty())
            {
                this.drawTexturedModalRect(this.guiLeft + 4 + slot.xPos, this.guiTop + 5 + slot.yPos, 30, 14, 8, 6);
            }
        }

        if (PAGE == 0)
        {
            OPENGL_FIXED_PIPE_FLOATBUFFER.limit(16);
            GL11.glGetFloat(GL11.GL_CURRENT_COLOR, OPENGL_FIXED_PIPE_FLOATBUFFER);
            GL_CURRENT_COLOR[0] = OPENGL_FIXED_PIPE_FLOATBUFFER.get(0);
            GL_CURRENT_COLOR[1] = OPENGL_FIXED_PIPE_FLOATBUFFER.get(1);
            GL_CURRENT_COLOR[2] = OPENGL_FIXED_PIPE_FLOATBUFFER.get(2);
            GL_CURRENT_COLOR[3] = OPENGL_FIXED_PIPE_FLOATBUFFER.get(3);
            Color color = generateRainbowColor();
            float red = color.getRed() / 255.0F;
            float green = color.getGreen() / 255.0F;
            float blue = color.getBlue() / 255.0F;
            float alpha = 1.0F;
            for (int i = 36 + 1; i < 63 + 1 - (3*3); ++i)
            {
                Slot slot = this.inventorySlots.inventorySlots.get(i);

                if (slot.getStack().isEmpty())
                {
                    this.drawTexturedModalRect(this.guiLeft + 4 + slot.xPos, this.guiTop + 5 + slot.yPos, 30, 14, 8, 6);
                }
            }

            for (int i = 9*3+9+3*6+7+1; i < 9*3+9+3*6+7+3*3+1; ++i)
            {
                Slot slot = this.inventorySlots.inventorySlots.get(i);

                if (slot.getStack().isEmpty())
                {
                    GL11.glColor4f(red, green, blue, alpha);

                    this.drawTexturedModalRect(this.guiLeft + 4 + slot.xPos, this.guiTop + 5 + slot.yPos, 30, 14, 8, 6);

                    GL11.glColor4f(GL_CURRENT_COLOR[0], GL_CURRENT_COLOR[1], GL_CURRENT_COLOR[2], GL_CURRENT_COLOR[3]);
                }
            }
        }

        SkinningEntities skinningentities = ((InventoryContainer)this.inventorySlots).skinningentities;
        ClientEntitiesMemory cliententitiesmemory = (ClientEntitiesMemory)skinningentities.bothentitiesmemory;

        this.drawTexturedModalRect(this.guiLeft + 159, this.guiTop + 51, 16, 42, 22, 21);
        this.drawTexturedModalRect(this.guiLeft + 102, this.guiTop + 28, 30, 0, 14, 14);

        switch (PAGE)
        {
            case 0:
            {
                Slot slot = this.inventorySlots.inventorySlots.get(63 + 1-(3*3));
                if (slot.getStack().isEmpty())
                {
                    this.drawTexturedModalRect(this.guiLeft + 1 + slot.xPos, this.guiTop + 1 + slot.yPos, 0, 104, 14, 14);
                }
                this.drawHideIcon(slot, (byte)0, mouseX, mouseY);
                slot = this.inventorySlots.inventorySlots.get(64 + 1-(3*3));
                if (slot.getStack().isEmpty())
                {
                    this.drawTexturedModalRect(this.guiLeft + 1 + slot.xPos, this.guiTop + 1 + slot.yPos, 0, 118, 14, 14);
                }
                this.drawHideIcon(slot, (byte)1, mouseX, mouseY);
                slot = this.inventorySlots.inventorySlots.get(65 + 1-(3*3));
                if (slot.getStack().isEmpty())
                {
                    this.drawTexturedModalRect(this.guiLeft + 1 + slot.xPos, this.guiTop + 1 + slot.yPos, 0, 48, 14, 14);
                }
                this.drawHideIcon(slot, (byte)2, mouseX, mouseY);
                slot = this.inventorySlots.inventorySlots.get(66 + 1-(3*3));
                if (slot.getStack().isEmpty())
                {
                    this.drawTexturedModalRect(this.guiLeft + 1 + slot.xPos, this.guiTop + 1 + slot.yPos, 0, 62, 14, 14);
                }
                this.drawHideIcon(slot, (byte)3, mouseX, mouseY);
                slot = this.inventorySlots.inventorySlots.get(67 + 1-(3*3));
                if (slot.getStack().isEmpty())
                {
                    this.drawTexturedModalRect(this.guiLeft + 3 + slot.xPos, this.guiTop + 1 + slot.yPos, 0, 76, 10, 14);
                }
                this.drawHideIcon(slot, (byte)4, mouseX, mouseY);
                slot = this.inventorySlots.inventorySlots.get(68 + 1-(3*3));
                if (slot.getStack().isEmpty())
                {
                    this.drawTexturedModalRect(this.guiLeft + 1 + slot.xPos, this.guiTop + 1 + slot.yPos, 0, 90, 14, 14);
                }
                this.drawHideIcon(slot, (byte)5, mouseX, mouseY);
                slot = this.inventorySlots.inventorySlots.get(69 + 1-(3*3));
                if (slot.getStack().isEmpty())
                {
                    this.drawTexturedModalRect(this.guiLeft + 1 + 1 + slot.xPos, this.guiTop + 1 + slot.yPos, 0, 132, 12, 14);
                }
                this.drawHideIcon(slot, (byte)6, mouseX, mouseY);

                break;
            }
            case 1:
            {
                ProfileGUI.drawGuiContainerBackgroundLayer(this);
                break;
            }
            case 2:
            {
                ScannerGUI.drawGuiContainerBackgroundLayer(this);
                break;
            }
            case 3:
            {
                SpecialStatGUI.drawGuiContainerBackgroundLayer(this);
                break;
            }
            default:
            {
                break;
            }
        }

        float health_percent = skinningentities.getHealth() / skinningentities.getMaxHealth();
        int health = (int)(59 * health_percent);
        for (int i = 0; i < health; ++i)
        {
            this.drawTexturedModalRect(this.guiLeft + 48, this.guiTop + 84 - i, 0, 0, 3, 1);
        }

        this.drawTexturedModalRect(this.guiLeft + 57, this.guiTop + 27, 30, (cliententitiesmemory.work_byte_array[cliententitiesmemory.workbytes.LOCK_INVENTORY() / 8] >> cliententitiesmemory.workbytes.LOCK_INVENTORY() % 8 & 1) == 1 ? 20 : 26, 5, 6);
        this.drawTexturedModalRect(this.guiLeft + 57, this.guiTop + 35, 30, (cliententitiesmemory.work_byte_array[cliententitiesmemory.workbytes.LOCK_DAMAGE() / 8] >> cliententitiesmemory.workbytes.LOCK_DAMAGE() % 8 & 1) == 1 ? 20 : 26, 5, 6);

        if (gl_blend)
        {
            GL11.glEnable(GL11.GL_BLEND);
        }
        else
        {
            GL11.glDisable(GL11.GL_BLEND);
        }
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) throws IOException
    {
//        this.type_char = typedChar;
//        this.key_code = keyCode;
//        CutePomi.LOGGER.info((int)typedChar);
        if (this.message_state != -1 && keyCode != Keyboard.KEY_ESCAPE)
        {
            int index = MESSAGE_STRINGBUILDER.length() - 1;
            char end = MESSAGE_STRINGBUILDER.charAt(index);

            switch (typedChar)
            {
                case '\b':
                {
                    if (MESSAGE_STRINGBUILDER.length() > 1)
                    {
                        MESSAGE_STRINGBUILDER.deleteCharAt(index - 1);
                    }

                    break;
                }
                case '\r':
                {
                    if (this.message_state == 10 && MESSAGE_STRINGBUILDER.length() == 2)
                    {
                        ManageItemGUIFeatures.PAGE = Byte.parseByte(MESSAGE_STRINGBUILDER.deleteCharAt(MESSAGE_STRINGBUILDER.length() - 1).toString());
                    }

                    if (this.message_state != 4)
                    {
                        SkinningEntities skinningentities = ((InventoryContainer)this.inventorySlots).skinningentities;
                        ClientEntitiesMemory cliententitiesmemory = (ClientEntitiesMemory)skinningentities.bothentitiesmemory;

                        String[] string_array = MESSAGE_STRINGBUILDER.deleteCharAt(MESSAGE_STRINGBUILDER.length() - 1).toString().split(" ");
                        byte[] byte_array = new byte[string_array.length * 4 + 1 + 16];
                        int new_index = 1 + 16;
                        for (String new_string : string_array)
                        {
                            try
                            {
                                if (this.message_state == 5 || this.message_state == 6 || this.message_state == 7 || this.message_state == 8 || this.message_state == 9 || this.message_state == 10)
                                {
                                    BytesWriter.set(byte_array, Float.parseFloat(new_string), new_index);
                                }
                                else
                                {
                                    BytesWriter.set(byte_array, Integer.parseInt(new_string), new_index);
                                }
                            }
                            catch (Exception ignored)
                            {
                                break;
                            }
                            new_index += 4;
                        }

                        switch (this.message_state)
                        {
                            case 0:
                            {
                                byte_array[0] = AddTarget.ID;
                                break;
                            }
                            case 1:
                            {
                                byte_array[0] = AddTroublemaker.ID;
                                break;
                            }
                            case 2:
                            {
                                byte_array[0] = RemoveTarget.ID;
                                break;
                            }
                            case 3:
                            {
                                byte_array[0] = RemoveTroublemaker.ID;
                                break;
                            }
                            case 5:
                            {
                                byte_array[0] = Scale.ID;
                                break;
                            }
                            case 6:
                            {
                                byte_array[0] = SetAttribute.ID;
                                break;
                            }
                            case 7:
                            {
                                byte_array[0] = SetLook.ID;
                                break;
                            }
                            case 8:
                            {
                                byte_array[0] = SetXYZ.ID;
                                break;
                            }
                            case 9:
                            {
                                byte_array[0] = SetLocation.ID;
                                break;
                            }
                            case 10:
                            {
                                byte_array[0] = SetManageItem.ID;
                            }
                            default:
                            {
                                break;
                            }
                        }

                        BytesWriter.set(byte_array, cliententitiesmemory.uuid, 1);
                        NetworksRegistry.I.sendToServer(new ServerMessage(byte_array));
                        MESSAGE_STRINGBUILDER.setLength(0);
                        MESSAGE_STRINGBUILDER.append("!");
                    }

                    break;
                }
//                case 0:// if ((typedChar >= 'a' && typedChar <= 'z') || (typedChar >= 'A' && typedChar <= 'Z'))
//                {
//                    break;
//                }
                default:
                {
                    boolean isShiftKeyDown = (keyCode == Keyboard.KEY_LSHIFT || keyCode == Keyboard.KEY_RSHIFT);

                    if (!(isShiftKeyDown && (typedChar == ' ' || typedChar == '\0')))
                    {
                        MESSAGE_STRINGBUILDER.deleteCharAt(index).append(typedChar).append(end);
                    }

                    break;
                }
            }
        }
        else
        {
            super.keyTyped(typedChar, keyCode);
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        if (PAGE < 1)
        {
            super.mouseClicked(mouseX, mouseY, mouseButton);
        }
        else
        {
            this.mouse_clicked = mouseButton;
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state)
    {
        if (PAGE < 1)
        {
            super.mouseReleased(mouseX, mouseY, state);
        }
        else
        {
            this.mouse_clicked = -1;
            this.mouse_released = state;
        }
    }

    public void drawHideIcon(Slot slot, byte index, int mouseX, int mouseY)
    {
        int x = this.guiLeft + slot.xPos;
        int y = this.guiTop + slot.yPos;
        if (mouseX >= x && mouseY >= y && mouseX <= x + 16 && mouseY <= y + 16)
        {
            x += 12;
            y += 12;
            this.drawTexturedModalRect(x, y, 252, 0, 4, 4);

            SkinningEntities skinningentities = ((InventoryContainer)this.inventorySlots).skinningentities;
            ClientEntitiesMemory cliententitiesmemory = (ClientEntitiesMemory)skinningentities.bothentitiesmemory;
            byte bit = cliententitiesmemory.sync_byte_array[0];
            if (((bit >> index) & 1) == 1)
            {
                this.drawTexturedModalRect(x + 1, y + 1, 253, 4, 3, 3);
            }

            if (mouseX >= x && mouseY >= y && mouseX <= x + 4 && mouseY <= y + 4)
            {
                this.block_mouse_clicked = true;
                if (this.mouse_released == 0)
                {
                    this.sendPacketUUIDByte(index);
                }
            }
        }
    }
}
