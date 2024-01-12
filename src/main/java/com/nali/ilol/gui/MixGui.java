package com.nali.ilol.gui;

import com.nali.ilol.ILOL;
import com.nali.ilol.entities.skinning.SkinningEntities;
import com.nali.ilol.gui.features.GUIFeaturesLoader;
import com.nali.ilol.gui.features.messages.TargetGUIFeatures;
import com.nali.ilol.networks.NetworksRegistry;
import com.nali.list.container.InventoryContainer;
import com.nali.list.messages.SkinningEntitiesServerMessage;
import com.nali.system.bytes.BytesWriter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.inventory.Container;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.IOException;
import java.util.UUID;
import java.util.regex.Pattern;

public abstract class MixGui extends GuiContainer
{
    public int mouse_released = -1, mouse_clicked = -1;

    public static MixGui I;
    public static GUIFeaturesLoader GUIFEATURESLOADER;
    public static StringBuilder MESSAGE_STRINGBUILDER = new StringBuilder("_");
    public long last_time = Minecraft.getSystemTime();//System.currentTimeMillis();
    public int limit_time = 1000;
    public int message_state;
    public boolean render_text;

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
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state)
    {
        this.mouse_clicked = -1;
        this.mouse_released = state;
        super.mouseReleased(mouseX, mouseY, state);
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
        NetworksRegistry.I.sendToServer(new SkinningEntitiesServerMessage(byte_array));
    }

    public void sendPacketUUID(byte id)
    {
        this.sendPacketUUID(id, ((InventoryContainer)this.inventorySlots).skinningentities.client_uuid);
    }

    public void sendPacketUUID(byte id, UUID uuid)
    {
        byte[] byte_array = new byte[17];
        byte_array[0] = id;
        BytesWriter.set(byte_array, uuid, 1);
        NetworksRegistry.I.sendToServer(new SkinningEntitiesServerMessage(byte_array));
    }

//    public void sendPacketUUIDInt(byte id, UUID uuid, int i)
//    {
//        byte[] byte_array = new byte[21];
//        byte_array[0] = id;
//        BytesWriter.set(byte_array, uuid, 1);
//        BytesWriter.set(byte_array, i, 17);
//        NetworksRegistry.I.sendToServer(new SkinningEntitiesServerMessage(byte_array));
//    }

    public void sendPacketUUIDInt(int i)
    {
        SkinningEntities skinningentities = ((InventoryContainer)this.inventorySlots).skinningentities;
        byte[] byte_array = new byte[22];
        byte_array[0] = 1;
        BytesWriter.set(byte_array, skinningentities.client_uuid, 1);
        BytesWriter.set(byte_array, i, 17);
        byte_array[21] = skinningentities.client_work_byte_array[i] == 1 ? (byte)0 : (byte)1;
        NetworksRegistry.I.sendToServer(new SkinningEntitiesServerMessage(byte_array));
    }

    public void renderEntitiesName(SkinningEntities skinningentities, float x, float y, int width, int height, int mouseX, int mouseY)
    {
        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
        {
            if (!(GUIFEATURESLOADER instanceof TargetGUIFeatures))
            {
                GUIFEATURESLOADER = new TargetGUIFeatures(this);
            }

            if (this.mouse_released == 0)
            {
                copyToClipboard(skinningentities.client_uuid.toString());
            }

            this.render_text = true;
        }
    }

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
//                I18n.translateToLocal("gui.info.un"),
//                uuid_string,
//                I18n.translateToLocal("gui.info.unh")
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

    public static void copyToClipboard(String text)
    {
        StringSelection stringSelection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    public static String getTextFromClipboard()
    {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable transferable = clipboard.getContents(null);

        if (transferable != null && transferable.isDataFlavorSupported(DataFlavor.stringFlavor))
        {
            try
            {
                return (String) transferable.getTransferData(DataFlavor.stringFlavor);
            }
            catch (Exception e)
            {
                ILOL.error(e);
            }
        }

        return "";
    }

    public static boolean isValidUUIDString(String uuid_string)
    {
        String uuidRegex = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
        Pattern pattern = Pattern.compile(uuidRegex);
        return uuid_string.length() == 36 && pattern.matcher(uuid_string).matches();
    }
}
