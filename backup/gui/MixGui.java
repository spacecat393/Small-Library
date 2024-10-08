package com.nali.small.gui;

import com.nali.list.network.message.ServerMessage;
import com.nali.network.NetworkRegistry;
import com.nali.small.gui.features.GUIFeaturesLoader;
import com.nali.small.gui.net.GUINetLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.inventory.Container;
import org.lwjgl.input.Keyboard;

import java.io.IOException;

import static com.nali.key.KeyHelper.copyToClipboard;
import static com.nali.key.KeyHelper.getTextFromClipboard;

public abstract class MixGui extends GuiContainer
{
    public int mouse_released = -1, mouse_clicked = -1;
    public boolean block_mouse_clicked;

    public static MixGui I;
    public static GUIFeaturesLoader GUIFEATURESLOADER;
    public static GUINetLoader GUINETLOADER;
    public static StringBuilder MESSAGE_STRINGBUILDER = new StringBuilder("_");
    public long last_time = Minecraft.getSystemTime();//System.currentTimeMillis();
    public int limit_time = 1000;
    public byte state;//message render

    public MixGui(Container container)
    {
        super(container);
        I = this;
        GUIFEATURESLOADER = new GUIFeaturesLoader(I);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        this.mouse_clicked = mouseButton;
        if (!this.block_mouse_clicked)
        {
            super.mouseClicked(mouseX, mouseY, mouseButton);
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state)
    {
        this.mouse_clicked = -1;
        this.mouse_released = state;
        super.mouseReleased(mouseX, mouseY, state);
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) throws IOException
    {
        if ((this.state & 1) == 1 && keyCode != Keyboard.KEY_ESCAPE)
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
                    if (GUINETLOADER != null)
                    {
                        GUINETLOADER.run();
                    }

                    MESSAGE_STRINGBUILDER.setLength(0);
                    MESSAGE_STRINGBUILDER.append("!");

                    break;
                }
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

    public void setMessage()
    {
        long current_time = Minecraft.getSystemTime();//System.currentTimeMillis();
        if (current_time - this.last_time >= this.limit_time)
        {
            int index = MESSAGE_STRINGBUILDER.length() - 1;
            boolean show = MESSAGE_STRINGBUILDER.charAt(index) == '_';
            MESSAGE_STRINGBUILDER.deleteCharAt(index).append(show ? ' ' : '_');
            this.last_time = current_time;
        }
    }

    public void sendPacket1(byte id)
    {
        byte[] byte_array = new byte[1];
        byte_array[0] = id;
        NetworkRegistry.I.sendToServer(new ServerMessage(byte_array));
    }

//    public void sendPacketUUID(byte id)
//    {
//        ClientLe cliententitiesmemory = (ClientLe)((InventoryContainer)this.inventorySlots).skinningentities.bothentitiesmemory;
//        this.sendPacketUUID(id, cliententitiesmemory.uuid);
//    }

//    public void sendPacketUUID(byte id, UUID uuid)
//    {
//        byte[] byte_array = new byte[17];
//        byte_array[0] = id;
//        ByteWriter.set(byte_array, uuid, 1);
//        NetworkRegistry.I.sendToServer(new ServerMessage(byte_array));
//    }

//    public void sendPacketUUIDInt(byte id, UUID uuid, int i)
//    {
//        byte[] byte_array = new byte[21];
//        byte_array[0] = id;
//        BytesWriter.set(byte_array, uuid, 1);
//        BytesWriter.set(byte_array, i, 17);
//        NetworksRegistry.I.sendToServer(new SkinningEntitiesServerMessage(byte_array));
//    }

//    //should use byte
//    public void sendPacketUUIDInt(byte i)
//    {
//        ClientLe cliententitiesmemory = (ClientLe)((InventoryContainer)this.inventorySlots).skinningentities.bothentitiesmemory;
//        byte[] byte_array = new byte[18];
//        byte_array[0] = SetWorkByte.ID;
//        ByteWriter.set(byte_array, cliententitiesmemory.uuid, 1);
//        byte_array[17] = i;
////        BytesWriter.set(byte_array, i, 17);
//        NetworkRegistry.I.sendToServer(new ServerMessage(byte_array));
//    }

//    public void sendPacketUUIDByte(byte i)
//    {
//        ClientLe cliententitiesmemory = (ClientLe)((InventoryContainer)this.inventorySlots).skinningentities.bothentitiesmemory;
//        byte[] byte_array = new byte[18];
//        byte_array[0] = SSyncBitByte.ID;
//        ByteWriter.set(byte_array, cliententitiesmemory.uuid, 1);
//        byte_array[17] = i;
////        BytesWriter.set(byte_array, i, 17);
//        NetworkRegistry.I.sendToServer(new ServerMessage(byte_array));
//    }

//    public void renderEntitiesName(EntityLeInv skinningentities, float x, float y, int width, int height, int mouseX, int mouseY)
//    {
//        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
//        {
//            if (!(GUIFEATURESLOADER instanceof NameGUIFeatures))
//            {
//                GUIFEATURESLOADER = new NameGUIFeatures(this, skinningentities);
//            }
//
//            if (this.mouse_released == 0)
//            {
//                ClientLe cliententitiesmemory = (ClientLe)skinningentities.bothentitiesmemory;
//                copyToClipboard(cliententitiesmemory.uuid.toString());
//            }
//
//            this.state |= 2;
//        }
//    }

//    public void renderEntitiesUUID(UUID uuid, float x, float y, int width, int height, int mouseX, int mouseY)
//    {
//        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
//        {
//            String uuid_string = uuid.toString();
//            if (this.mouse_released == 0)
//            {
//                copyToClipboard(uuid_string);
//            }
//
//            this.drawHoveringText(new String[]
//            {
//                I18n.translateToLocal("info." + Small.ID + ".un"),
//                uuid_string,
//                I18n.translateToLocal("info." + Small.ID + ".ba")
//            }, mouseX, mouseY, false);
//        }
//    }

    public void drawHoveringText(String[] text_string_array, int[] color_int_array, int x, int y, boolean have_head)
    {
//        net.minecraftforge.fml.client.config.GuiUtils.drawHoveringText(textLines, x, y, width, height, -1, font);
//        if (false && !textLines.isEmpty())
//        {
        GlStateManager.disableRescaleNormal();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();

        int i = 0;

        for (String s : text_string_array)
        {
            int j = this.fontRenderer.getStringWidth(s);

            if (j > i)
            {
                i = j;
            }
        }

        int l1 = x + 12;
        int i2 = y - 12;
        int k = 8;

        int length = text_string_array.length;
        if (length > 1)
        {
            if (have_head)
            {
                k += 2;
            }

            k += (length - 1) * 10;
        }

        if (l1 + i > this.width)
        {
            l1 -= 28 + i;
        }

        if (i2 + k + 6 > this.height)
        {
            i2 = this.height - k - 6;
        }

        this.zLevel = 300.0F;
        this.itemRender.zLevel = 300.0F;
        //AARRGGBB
        int l = 0x88000000;
        int i1 = 0xFFF85A52;
        int j1 = 0xFFFFFFFF;

        this.drawGradientRect(l1 - 3, i2 - 3, l1 + i + 3, i2 + k + 3, l, l); //bg

        this.drawGradientRect(l1 - 3, i2 - 3 + 1, l1 - 3 + 1, i2 + k + 3 - 1, i1, j1); //left
        this.drawGradientRect(l1 + i + 2, i2 - 3 + 1, l1 + i + 3, i2 + k + 3 - 1, i1, j1); //right
        this.drawGradientRect(l1 - 3, i2 - 3, l1 + i + 3, i2 - 3 + 1, i1, i1); //top
        this.drawGradientRect(l1 - 3, i2 + k + 2, l1 + i + 3, i2 + k + 3, j1, j1); //bottom
        this.drawGradientRect(l1 - 3 + 1, i2 - 3 - 2, l1 - 3 + 3, i2 - 3, i1, i1); //top ribbon
        this.drawGradientRect(l1 - 3 - 2, i2 - 3 + 1, l1 - 3, i2 - 3 + 3, i1, i1); //left ribbon

        for (int k1 = 0; k1 < length; ++k1)
        {
            this.fontRenderer.drawStringWithShadow(text_string_array[k1], (float)l1, (float)i2, color_int_array[k1]/*(k1 + 1) % 2 == 0 ? 0xFFFFFFFF : 0xFFF85A52*/);

            if (have_head)
            {
                if (k1 == 0)
                {
                    i2 += 2;
                }
            }

            i2 += 10;
        }

        this.zLevel = 0.0F;
        this.itemRender.zLevel = 0.0F;
        GlStateManager.enableLighting();
        GlStateManager.enableDepth();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.enableRescaleNormal();
    }

    public void drawTextureRightToLeft(float xCoord, float yCoord, int minU, int minV, int maxU, int maxV)
    {
        float f = 0.00390625F;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);

        bufferbuilder.pos(xCoord + 0.0F, yCoord + maxV, this.zLevel).tex((float)(minU + maxU) * f, (float)(minV + maxV) * f).endVertex();
        bufferbuilder.pos(xCoord + maxU, yCoord + maxV, this.zLevel).tex((float)(minU) * f, (float)(minV + maxV) * f).endVertex();
        bufferbuilder.pos(xCoord + maxU, yCoord + 0.0F, this.zLevel).tex((float)(minU) * f, (float)(minV) * f).endVertex();
        bufferbuilder.pos(xCoord + 0.0F, yCoord + 0.0F, this.zLevel).tex((float)(minU + maxU) * f, (float)(minV) * f).endVertex();

        tessellator.draw();
    }

    public void copyOrPaste()
    {
        if (this.mouse_released == 0)
        {
            copyToClipboard(MESSAGE_STRINGBUILDER.toString());
        }
        else if (this.mouse_released == 1)
        {
            MESSAGE_STRINGBUILDER = new StringBuilder(getTextFromClipboard());
        }
    }
}
