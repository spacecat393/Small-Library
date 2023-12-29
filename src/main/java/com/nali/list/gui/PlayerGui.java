package com.nali.list.gui;

import com.nali.ilol.capabilities.CapabilitiesRegistryHelper;
import com.nali.ilol.data.SakuraData;
import com.nali.ilol.entities.skinning.SkinningEntities;
import com.nali.ilol.entities.skinning.SkinningEntitiesRender;
import com.nali.ilol.gui.MixGui;
import com.nali.ilol.networks.NetworksRegistry;
import com.nali.ilol.render.RenderHelper;
import com.nali.ilol.render.SakuraRender;
import com.nali.list.messages.CapabilitiesServerMessage;
import com.nali.list.messages.SkinningEntitiesServerMessage;
import com.nali.render.ObjectRender;
import com.nali.render.SkinningRender;
import com.nali.system.Timing;
import com.nali.system.bytes.BytesWriter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.inventory.Container;
import net.minecraft.util.text.translation.I18n;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class PlayerGui extends MixGui
{
    public ObjectRender sakura_objectrender = new SakuraRender(new SakuraData(), RenderHelper.DATALOADER);
    public static byte PAGE;
    public MixButton[] mixbutton_array;

    public PlayerGui(Container container)
    {
        super(container);
        this.mc = Minecraft.getMinecraft();
        ScaledResolution scaledresolution = new ScaledResolution(this.mc);
        this.setWorldAndResolution(Minecraft.getMinecraft(), scaledresolution.getScaledWidth(), scaledresolution.getScaledHeight());

        byte[] byte_array = new byte[4];
        BytesWriter.set(byte_array, 0, 0);
        NetworksRegistry.I.sendToServer(new CapabilitiesServerMessage(byte_array));
        this.xSize = 256;
        this.ySize = 256;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        super.drawScreen(mouseX, mouseY, partialTicks);
        if (PAGE == 0)
        {
            int x = this.guiLeft + 10, y = this.guiTop + 118, width = 18, height = 19;
            if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
            {
                if (this.mouse_released == 0)
                {
                    byte[] byte_array = new byte[1];
                    byte_array[0] = 3;
                    NetworksRegistry.I.sendToServer(new SkinningEntitiesServerMessage(byte_array));
                }

                this.drawHoveringText(new String[]
                {
                    I18n.translateToLocal("gui.info.a0"),
                    I18n.translateToLocal("gui.info.a00")
                },
                new int[]
                {
                    0xFFF85A52,
                    0xFFFFFFFF
                }, mouseX, mouseY, true);
            }

            x = this.guiLeft + 10 + 18 + 10;// y = this.guiTop + 118; width = 18; height = 19;
            if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
            {
                if (this.mouse_released == 0)
                {
                    byte[] byte_array = new byte[1];
                    byte_array[0] = 4;
                    NetworksRegistry.I.sendToServer(new SkinningEntitiesServerMessage(byte_array));
                }

                this.drawHoveringText(new String[]
                {
                    I18n.translateToLocal("gui.info.a1"),
                    I18n.translateToLocal("gui.info.a10")
                },
                new int[]
                {
                    0xFFF85A52,
                    0xFFFFFFFF
                }, mouseX, mouseY, true);
            }

            x = this.guiLeft + 10 + 18 + 10 + 18 + 10;// y = this.guiTop + 118; width = 18; height = 19;
            if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
            {
                if (this.mouse_released == 0)
                {
                    byte[] byte_array = new byte[1];
                    byte_array[0] = 5;
                    NetworksRegistry.I.sendToServer(new SkinningEntitiesServerMessage(byte_array));

                    int index = 0; x = 10; y = 10;
                    Set<UUID> keys_set = new HashSet<>(SkinningEntities.CLIENT_ENTITIES_MAP.keySet());
                    this.mixbutton_array = new MixButton[keys_set.size()];
                    for (UUID uuid : keys_set)
                    {
                        SkinningEntities skinningentities = SkinningEntities.CLIENT_ENTITIES_MAP.get(uuid);
//                    if (skinningentities == null)
//                    {
//                        this.mixbutton_array = null;
//                        break;
//                    }
                        this.mixbutton_array[index++] = new MixButton(skinningentities, uuid, x, y, 143, 51, 56, 60);
//                    this.mixbutton_array[index++] = new MixButton(SkinningEntities.CLIENT_ENTITIES_MAP.get(uuid), this.guiLeft + x, this.guiTop + y, 143, 51, 56, 60);

                        if (x >= this.width)
                        {
                            y += 60 + 10;
                            x = 10;
                        }
                        else
                        {
                            x += 56 + 10;
                        }
                    }

                    PAGE = 1;
                }

                this.drawHoveringText(new String[]
                {
                    I18n.translateToLocal("gui.info.a2"),
                    I18n.translateToLocal("gui.info.a20")
                },
                new int[]
                {
                    0xFFF85A52,
                    0xFFFFFFFF
                }, mouseX, mouseY, true);
            }
        }
        else
        {

        }

        this.mouse_released = -1;
    }

    @Override
    public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        boolean gl_blend = GL11.glIsEnabled(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_BLEND);

        this.mc.getTextureManager().bindTexture(InventoryGui.GUI_RESOURCELOCATION);

        if (PAGE == 0)
        {
            this.drawTexturedModalRect(this.guiLeft + 10, this.guiTop + 118.5F, 106, 50, 18, 19);
            this.drawTexturedModalRect(this.guiLeft + 10 + 2, this.guiTop + 121, 16, 0, 14, 14);
            this.drawTexturedModalRect(this.guiLeft + 10 + 18 + 10, this.guiTop + 118.5F, 106, 50, 18, 19);
            this.drawTexturedModalRect(this.guiLeft + 10 + 18 + 10 + 2, this.guiTop + 121, 238, 0, 14, 14);
            this.drawTexturedModalRect(this.guiLeft + 10 + 18 + 10 + 18 + 10, this.guiTop + 118.5F, 106, 50, 18, 19);
            this.drawTexturedModalRect(this.guiLeft + 10 + 18 + 10 + 18 + 10 + 2, this.guiTop + 122, 224, 0, 14, 12);
        }
        else
        {
            if (this.mixbutton_array != null)
            {
                for (MixButton mixbutton : this.mixbutton_array)
                {
                    mixbutton.render(this, mouseX, mouseY, this.guiLeft, this.guiTop, this.width, this.height);
                }
            }
        }

        if (gl_blend)
        {
            GL11.glEnable(GL11.GL_BLEND);
        }
        else
        {
            GL11.glDisable(GL11.GL_BLEND);
        }

//        this.sakura_objectrender.width = this.width;
//        this.sakura_objectrender.height = this.height;
        this.sakura_objectrender.x = 15.0F;
        this.sakura_objectrender.y = 15.0F;
        this.sakura_objectrender.ry += 2.0F * Timing.TD;
//        this.pyroxene_objectrender.z = -1.0F;
//        this.pyroxene_objectdata.screen_float_array[2] = this.width / 2.0F;
//        this.pyroxene_objectdata.screen_float_array[3] = this.guiTop + 72.0F;
        this.sakura_objectrender.objectscreendraw.renderScreen(1.0F, 1.0F, 1.0F, 1.0F);

//        if (CapabilitiesRegistryHelper.CLIENT_CAPABILITY_OBJECT_ARRAYLIST.size() > 0)
//        {
        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow("" + CapabilitiesRegistryHelper.CLIENT_CAPABILITY_OBJECT_ARRAYLIST.get(0), 25, 11, generateRainbowColor());
//        }
    }

    public static int generateRainbowColor()
    {
        float hue = (Minecraft.getSystemTime()/*System.currentTimeMillis()*/ % 3600) / 10.0F;
        Color color = Color.getHSBColor(hue / 360.0F, 1.0F, 1.0F);

        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        int alpha = 255;

        return (alpha << 24) | (red << 16) | (green << 8) | blue;
    }

    public static class MixButton
    {
        public SkinningEntities skinningentities;
        public SkinningEntitiesRender skinningentitiesrender;
        public int x, y, tx, ty, width, height;
        public UUID uuid;

        public MixButton(SkinningEntities skinningentities, UUID uuid, int x, int y, int tx, int ty, int width, int height)
        {
            this.skinningentities = skinningentities;
            if (this.skinningentities != null)
            {
                this.skinningentitiesrender = (SkinningEntitiesRender)Minecraft.getMinecraft().getRenderManager().getEntityRenderObject(skinningentities);
//                SkinningRender skinningrender = (SkinningRender)skinningentities.client_object;
//                skinningrender.screen_float_array[3] = 0.0F;
//                skinningrender.screen_float_array[4] = -10.0F;
//        this.rx = -1.57079632679F;
//                skinningrender.screen_float_array[5] = -1.57079632679F;
//        skinningdata.screen_float_array[6] = 0.0F;
//                skinningrender.screen_float_array[7] = 0.0F;
            }

            this.x = x;
            this.y = y;
            this.tx = tx;
            this.ty = ty;
            this.width = width;
            this.height = height;
            this.uuid = uuid;
        }

        public void render(MixGui mixgui, int mouseX, int mouseY, int left, int top, int sys_width, int sys_height)
        {
            int new_x = this.x;
            int new_y = this.y;
            mixgui.drawTexturedModalRect(new_x, new_y, this.tx, this.ty, this.width, this.height);

            if (this.skinningentities != null)
            {
                Minecraft mc = Minecraft.getMinecraft();
                SkinningRender skinningrender = (SkinningRender)this.skinningentities.client_object;
                if (!skinningrender.should_render)
                {
                    this.skinningentitiesrender.updateData(skinningentities, mc.getRenderPartialTicks());
                }

//                ScaledResolution scaledresolution = new ScaledResolution(mc);
//                float s = 0.2F;
//                if (scaledresolution.getScaleFactor() <= 1)//this.mc.gameSettings.guiScale == 0//if ((this.width / (double)this.height) <= 1.3325D || (this.height / (double)this.width) <= 0.5608D)
//                {
//                    s = 0.1F;
//                }
//                float s = -25.0F;

                skinningrender.x = this.x + 28;
                skinningrender.y = this.y + 47;
                skinningrender.lig_b = 208.0F;
                skinningrender.lig_s = 240.0F;
//                skinningrender.sx = s;
//                skinningrender.sy = s;
//                skinningrender.sz = s;
                skinningrender.objectscreendraw.renderScreen(1.0F, 1.0F, 1.0F, 1.0F);
            }

            if (mouseX >= new_x && mouseY >= new_y && mouseX < new_x + width && mouseY < new_y + height)
            {
                if (mixgui.mouse_released == 0)
                {
                    byte[] byte_array = new byte[21];
                    byte_array[0] = 6;
                    BytesWriter.set(byte_array, this.uuid, 1);
                    BytesWriter.set(byte_array, 1, 17);
                    NetworksRegistry.I.sendToServer(new SkinningEntitiesServerMessage(byte_array));
                }
            }
        }
    }
}
