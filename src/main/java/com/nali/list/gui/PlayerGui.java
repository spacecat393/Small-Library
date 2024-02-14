package com.nali.list.gui;

import com.nali.list.messages.CapabilitiesServerMessage;
import com.nali.list.messages.SkinningEntitiesServerMessage;
import com.nali.render.SkinningRender;
import com.nali.small.capabilities.CapabilitiesRegistryHelper;
import com.nali.small.data.BoxData;
import com.nali.small.data.SakuraData;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.entities.skinning.render.ItemEntitiesRender;
import com.nali.small.entities.skinning.render.SkinningEntitiesRender;
import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.messages.player.*;
import com.nali.small.networks.NetworksRegistry;
import com.nali.small.render.BoxRender;
import com.nali.small.render.RenderHelper;
import com.nali.small.render.SakuraRender;
import com.nali.system.Timing;
import com.nali.system.bytes.BytesWriter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.inventory.Container;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static com.nali.small.render.RenderHelper.DATALOADER;

public class PlayerGui extends MixGui
{
    public static MixButton[] MIXBUTTON_ARRAY;
    public static int CURRENT_INDEX;
    public SakuraRender sakurarender = new SakuraRender(new SakuraData(), RenderHelper.DATALOADER);
    public BoxRender boxrender = new BoxRender(new BoxData(), DATALOADER);
    public static byte PAGE;

    public static int MAX_NEXT;
    public static int NEXT;

    public PlayerGui(Container container)
    {
        super(container);
        this.mc = Minecraft.getMinecraft();
        ScaledResolution scaledresolution = new ScaledResolution(this.mc);
        this.setWorldAndResolution(Minecraft.getMinecraft(), scaledresolution.getScaledWidth(), scaledresolution.getScaledHeight());

//        byte[] byte_array = new byte[4];
//        BytesWriter.set(byte_array, 0, 0);
//        NetworksRegistry.I.sendToServer(new CapabilitiesServerMessage(byte_array));
        NetworksRegistry.I.sendToServer(new CapabilitiesServerMessage(new byte[4]));
        this.xSize = 256;
        this.ySize = 256;

        this.boxrender.rz = 45.0F * 3.0F;
        float s = -3.5F;
        this.boxrender.sx = s;
        this.boxrender.sy = s;
        this.boxrender.sz = s;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.message_state = -1;
        this.render_text = false;
        //
        this.message_state = 1;
        if (this.mouse_clicked == 0)
        {
            ++ItemEntitiesRender.DEBUG_V;
        }
        if (!(GUIFEATURESLOADER instanceof DebugGUIFeatures))
        {
            GUIFEATURESLOADER = new DebugGUIFeatures(this);
        }
        this.render_text = true;
        //

        super.drawScreen(mouseX, mouseY, partialTicks);
        if (PAGE == 0)
        {
            this.setMessage();

            float x = this.guiLeft + 256/2.0F - (18 + 1)*4/2.0F, y = this.guiTop + 256/2.0F - 19/2.0F, width = 18, height = 19;
            if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
            {
                if (this.mouse_released == 0)
                {
                    this.sendPacket1((byte) 3);
                }

                if (!(GUIFEATURESLOADER instanceof RandomAGUIFeatures))
                {
                    GUIFEATURESLOADER = new RandomAGUIFeatures(this);
                }
                this.render_text = true;
            }

            x = this.guiLeft + 256/2.0F - (18 + 1)*4/2.0F + (18 + 1);// y = this.guiTop + 118; width = 18; height = 19;
            if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
            {
                if (this.mouse_released == 0)
                {
                    this.sendPacket1((byte)4);
                }

                if (!(GUIFEATURESLOADER instanceof RandomBGUIFeatures))
                {
                    GUIFEATURESLOADER = new RandomBGUIFeatures(this);
                }
                this.render_text = true;
            }

            x = this.guiLeft + 256/2.0F - (18 + 1)*4/2.0F + (18 + 1)*2;// y = this.guiTop + 118; width = 18; height = 19;
            if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
            {
                if (this.mouse_released == 0)
                {
//                    byte[] byte_array = new byte[1];
//                    byte_array[0] = 5;
//                    NetworksRegistry.I.sendToServer(new SkinningEntitiesServerMessage(byte_array));
//
//                    this.initEntities();

                    PAGE = 1;
                }

                if (!(GUIFEATURESLOADER instanceof MimiTalkGUIFeatures))
                {
                    GUIFEATURESLOADER = new MimiTalkGUIFeatures(this);
                }
                this.render_text = true;
            }

            x = this.guiLeft + 256/2.0F - (18 + 1)*4/2.0F + (18 + 1)*3;// y = this.guiTop + 118; width = 18; height = 19;
            if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
            {
                this.message_state = 0;

                if (!(GUIFEATURESLOADER instanceof DropGUIFeatures))
                {
                    GUIFEATURESLOADER = new DropGUIFeatures(this);
                }
                this.render_text = true;
            }
        }
        else
        {
            if (MIXBUTTON_ARRAY != null)
            {
                int img = 4 + (MIXBUTTON_ARRAY.length - (CURRENT_INDEX + 4));
                if (img > 4)
                {
                    img = 4;
                }
                int max = 0;
                float x = 256/2.0F - (56 + 1)*img/2.0F, y = 256/2.0F - 60/2.0F;
                for (int i = CURRENT_INDEX; i < MIXBUTTON_ARRAY.length; ++i)
                {
                    MIXBUTTON_ARRAY[i].renderTooltip(this, this.guiLeft + x, this.guiTop + y, mouseX, mouseY/*, this.guiLeft, this.guiTop, this.width, this.height*/);

                    if (++max == 4)
                    {
                        break;
                    }
                    x += (56 + 1);
                }
            }

            MAX_NEXT = 0;
            NEXT = (CURRENT_INDEX / 4);
            if (MIXBUTTON_ARRAY != null)
            {
                MAX_NEXT = (int)Math.ceil(MIXBUTTON_ARRAY.length / 4.0F) - 1;
                if (MAX_NEXT == -1)
                {
                    MAX_NEXT = 0;
                }
                if (CURRENT_INDEX > MIXBUTTON_ARRAY.length)
                {
                    CURRENT_INDEX = MIXBUTTON_ARRAY.length - 4;
                }
                if (CURRENT_INDEX < 0)
                {
                    CURRENT_INDEX = 0;
                }
            }

            float x = this.guiLeft + 256/2.0F - (18 + 1)*4/2.0F, y = this.guiTop + 256/2.0F - 19/2.0F + 61, width = 18, height = 19;
            if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
            {
                if (this.mouse_released == 0)
                {
                    if (NEXT > 0)
                    {
                        CURRENT_INDEX -= 4;
                    }
                }

                if (!(GUIFEATURESLOADER instanceof BackGUIFeatures))
                {
                    GUIFEATURESLOADER = new BackGUIFeatures(this);
                }
                this.render_text = true;
            }

            x = this.guiLeft + 256/2.0F - (18 + 1)*4/2.0F + (18 + 1);
            if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
            {
                if (this.mouse_released == 0)
                {
                    this.initEntities();
                }
                else if (this.mouse_released == 1)
                {
                    this.sendPacket1((byte)5);
                }

                if (!(GUIFEATURESLOADER instanceof FetchGUIFeatures))
                {
                    GUIFEATURESLOADER = new FetchGUIFeatures(this);
                }
                this.render_text = true;
            }

            x = this.guiLeft + 256/2.0F - (18 + 1)*4/2.0F + (18 + 1)*2;
            if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
            {
                if (this.mouse_released == 0)
                {
                    if (NEXT < MAX_NEXT)
                    {
                        CURRENT_INDEX += 4;
                    }
                }

                if (!(GUIFEATURESLOADER instanceof NextGUIFeatures))
                {
                    GUIFEATURESLOADER = new NextGUIFeatures(this);
                }
                this.render_text = true;
            }

            x = this.guiLeft + 256/2.0F - (18 + 1)*4/2.0F + (18 + 1)*3;
            if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
            {
                if (this.mouse_released == 0)
                {
                    String uuid_string = getTextFromClipboard();
                    if (isValidUUIDString(uuid_string))
                    {
                        this.sendPacketUUID((byte)6, UUID.fromString(uuid_string));
                    }
                }

                if (!(GUIFEATURESLOADER instanceof EnterGUIFeatures))
                {
                    GUIFEATURESLOADER = new EnterGUIFeatures(this);
                }
                this.render_text = true;
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

        this.mc.getTextureManager().bindTexture(InventoryGui.GUI_RESOURCELOCATION);

        if (PAGE == 0)
        {
            this.drawTexturedModalRect(this.guiLeft + 256/2.0F - (18 + 1)*4/2.0F, this.guiTop + 256/2.0F - 19/2.0F, 106, 50, 18, 19);
            this.drawTexturedModalRect(this.guiLeft + 256/2.0F - (18 + 1)*4/2.0F + 2, this.guiTop + 256/2.0F - 14/2.0F, 16, 0, 14, 14);
            this.drawTexturedModalRect(this.guiLeft + 256/2.0F - (18 + 1)*4/2.0F + (18 + 1), this.guiTop + 256/2.0F - 19/2.0F, 106, 50, 18, 19);
            this.drawTexturedModalRect(this.guiLeft + 256/2.0F - (18 + 1)*4/2.0F + (18 + 1) + 2, this.guiTop + 256/2.0F - 14/2.0F, 238, 0, 14, 14);
            this.drawTexturedModalRect(this.guiLeft + 256/2.0F - (18 + 1)*4/2.0F + (18 + 1)*2, this.guiTop + 256/2.0F - 19/2.0F, 106, 50, 18, 19);
            this.drawTexturedModalRect(this.guiLeft + 256/2.0F - (18 + 1)*4/2.0F + (18 + 1)*2 + 2, this.guiTop + 256/2.0F - 12/2.0F, 224, 0, 14, 12);
            this.drawTexturedModalRect(this.guiLeft + 256/2.0F - (18 + 1)*4/2.0F + (18 + 1)*3, this.guiTop + 256/2.0F - 19/2.0F, 106, 50, 18, 19);
            this.boxrender.x = this.guiLeft + 256/2.0F - (18 + 1)*4/2.0F + (18 + 1)*3 + (18 + 1)/2.0F - 0.5F;
            this.boxrender.y = this.guiTop + 256/2.0F - 19/2.0F + 19/2.0F + 2.5F/2.0F;
            this.boxrender.objectscreendraw.renderScreen(1.0F, 1.0F, 1.0F, 0.9F);
        }
        else
        {
            this.drawTexturedModalRect(this.guiLeft + 256/2.0F - (18 + 1)*4/2.0F, this.guiTop + 256/2.0F - 19/2.0F + 61, 106, 50, 18, 19);
            this.drawTexturedModalRect(this.guiLeft + 256/2.0F - (18 + 1)*4/2.0F + (18-8)/2.0F, this.guiTop + 256/2.0F - 6/2.0F + 61, 30, 0, 8, 6);
            this.drawTexturedModalRect(this.guiLeft + 256/2.0F - (18 + 1)*4/2.0F + (18 + 1), this.guiTop + 256/2.0F - 19/2.0F + 61, 106, 50, 18, 19);
            this.drawTexturedModalRect(this.guiLeft + 256/2.0F - (18 + 1)*4/2.0F + (18 + 1) + (18-12)/2.0F, this.guiTop + 256/2.0F - 12/2.0F + 61, 52, 14, 12, 12);
            this.drawTexturedModalRect(this.guiLeft + 256/2.0F - (18 + 1)*4/2.0F + (18 + 1)*2, this.guiTop + 256/2.0F - 19/2.0F + 61, 106, 50, 18, 19);
            this.drawTextureRightToLeft(this.guiLeft + 256/2.0F - (18 + 1)*4/2.0F + (18 + 1)*2 + (18-8)/2.0F, this.guiTop + 256/2.0F - 6/2.0F + 61, 30, 0, 8, 6);
            this.drawTexturedModalRect(this.guiLeft + 256/2.0F - (18 + 1)*4/2.0F + (18 + 1)*3, this.guiTop + 256/2.0F - 19/2.0F + 61, 106, 50, 18, 19);
            this.drawTexturedModalRect(this.guiLeft + 256/2.0F - (18 + 1)*4/2.0F + (18 + 1)*3 + (18-14)/2.0F, this.guiTop + 256/2.0F - 8/2.0F + 61, 38, 14, 14, 8);

            if (MIXBUTTON_ARRAY != null)
            {
                int img = 4 + (MIXBUTTON_ARRAY.length - (CURRENT_INDEX + 4));
                if (img > 4)
                {
                    img = 4;
                }
                int max = 0;
                float x = 256/2.0F - (56 + 1)*img/2.0F, y = 256/2.0F - 60/2.0F;
                for (int i = CURRENT_INDEX; i < MIXBUTTON_ARRAY.length; ++i)
                {
                    MIXBUTTON_ARRAY[i].render(this, this.guiLeft + x, this.guiTop + y, mouseX, mouseY/*, this.guiLeft, this.guiTop, this.width, this.height*/);

                    if (++max == 4)
                    {
                        break;
                    }
                    x += (56 + 1);
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
        this.sakurarender.x = 15.0F;
        this.sakurarender.y = 15.0F;
        this.sakurarender.ry += 2.0F * Timing.TD;
//        this.pyroxene_objectrender.z = -1.0F;
//        this.pyroxene_objectdata.screen_float_array[2] = this.width / 2.0F;
//        this.pyroxene_objectdata.screen_float_array[3] = this.guiTop + 72.0F;
        this.sakurarender.objectscreendraw.renderScreen(1.0F, 1.0F, 1.0F, 1.0F);

//        if (CapabilitiesRegistryHelper.CLIENT_CAPABILITY_OBJECT_ARRAYLIST.size() > 0)
//        {
        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow("" + CapabilitiesRegistryHelper.CLIENT_CAPABILITY_OBJECT_ARRAYLIST.get(0), 25, 11, generateRainbowColor());
//        }
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) throws IOException
    {
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
                    if (this.message_state == 1)
                    {
                        String[] string_array = MESSAGE_STRINGBUILDER.deleteCharAt(MESSAGE_STRINGBUILDER.length() - 1).toString().split(" ");
                        int o = 0;
                        for (String new_string : string_array)
                        {
                            try
                            {
                                int v = Integer.parseInt(new_string);
                                if (o == 0)
                                {
                                    ItemEntitiesRender.DEBUG_V = v;
                                }
                                else if (o == 1)
                                {
                                    ItemEntitiesRender.DEBUG_I = v;
                                }
                                ++o;
                            }
                            catch (Exception ignored)
                            {
                                break;
                            }
                        }
                    }
                    else
                    {
//                    switch (this.message_state)
//                    {
//                        case 0:
//                        {
//                    byte[] string_byte_array = MESSAGE_STRINGBUILDER.toString().getBytes();
//                    int string_byte_array_size = string_byte_array.length - 1;
                    String[] string_array = MESSAGE_STRINGBUILDER.deleteCharAt(MESSAGE_STRINGBUILDER.length() - 1).toString().split(" ");
                    byte[] byte_array = new byte[/*string_byte_array_size*/string_array.length * 4 + 1];
                    byte_array[0] = 7;
                    int new_index = 1;
                    for (String new_string : string_array)
                    {
                        try
                        {
                            BytesWriter.set(byte_array, Integer.parseInt(new_string), new_index);
                        }
                        catch (Exception ignored)
                        {
                            break;
                        }
                        new_index += 4;
                    }
//                    System.arraycopy(string_byte_array, 0, byte_array, 1, string_byte_array_size);
                    NetworksRegistry.I.sendToServer(new SkinningEntitiesServerMessage(byte_array));
//                            break;
//                        }
//                        default:
//                        {
//                            break;
//                        }
//                    }
//
                    }
                    MESSAGE_STRINGBUILDER.setLength(0);
                    MESSAGE_STRINGBUILDER.append("!");
                    break;
                }
//                case 0:// if ((typedChar >= 'a' && typedChar <= 'z') || (typedChar >= 'A' && typedChar <= 'Z'))
//                {
//                    break;
//                }
                default:
                {
                    MESSAGE_STRINGBUILDER.deleteCharAt(index).append(typedChar).append(end);
                    break;
                }
            }
        }
        else
        {
            super.keyTyped(typedChar, keyCode);
        }
    }

    public static class MixButton
    {
        public SkinningEntities skinningentities;
        public SkinningEntitiesRender skinningentitiesrender;
        public int /*x, y, */tx, ty, width, height;
        public UUID uuid;

        public MixButton(SkinningEntities skinningentities, UUID uuid/*, int x, int y*/, int tx, int ty, int width, int height)
        {
            this.skinningentities = skinningentities;
//            if (this.skinningentities != null)
//            {
            this.skinningentitiesrender = (SkinningEntitiesRender)Minecraft.getMinecraft().getRenderManager().getEntityRenderObject(skinningentities);
//                SkinningRender skinningrender = (SkinningRender)skinningentities.client_object;
//                skinningrender.screen_float_array[3] = 0.0F;
//                skinningrender.screen_float_array[4] = -10.0F;
//        this.rx = -1.57079632679F;
//                skinningrender.screen_float_array[5] = -1.57079632679F;
//        skinningdata.screen_float_array[6] = 0.0F;
//                skinningrender.screen_float_array[7] = 0.0F;
//            }

//            this.x = x;
//            this.y = y;
            this.tx = tx;
            this.ty = ty;
            this.width = width;
            this.height = height;
            this.uuid = uuid;
        }

        public void render(MixGui mixgui, float x, float y, int mouseX, int mouseY/*, int left, int top, int sys_width, int sys_height*/)
        {
            mixgui.drawTexturedModalRect(x, y, this.tx, this.ty, this.width, this.height);

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

                skinningrender.x = x + 28;
                skinningrender.y = y + 47;
                skinningrender.lig_b = 208.0F;
                skinningrender.lig_s = 240.0F;
//                skinningrender.sx = s;
//                skinningrender.sy = s;
//                skinningrender.sz = s;
                skinningrender.objectscreendraw.renderScreen(1.0F, 1.0F, 1.0F, 1.0F);
            }
        }

        public void renderTooltip(MixGui mixgui, float x, float y, int mouseX, int mouseY/*, int left, int top, int sys_width, int sys_height*/)
        {
//            if (this.skinningentities != null)
//            {
            mixgui.renderEntitiesName(this.skinningentities, x, y + 49, 56, 11, mouseX, mouseY);
//            }
//            else
//            {
//                mixgui.renderEntitiesUUID(this.uuid, x, y + 49, 56, 11, mouseX, mouseY);
//            }

            if (mouseX >= x && mouseY >= y && mouseX < x + this.width && mouseY < y + this.height)
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

    public void initEntities()
    {
        int index = 0;//, x = 10, y = 10;
        Set<UUID> keys_set = new HashSet<>(SkinningEntities.CLIENT_ENTITIES_MAP.keySet());
        MIXBUTTON_ARRAY = new MixButton[keys_set.size()];
        for (UUID uuid : keys_set)
        {
            SkinningEntities skinningentities = SkinningEntities.CLIENT_ENTITIES_MAP.get(uuid);
//                    if (skinningentities == null)
//                    {
//                        this.mixbutton_array = null;
//                        break;
//                    }
            MIXBUTTON_ARRAY[index++] = new MixButton(skinningentities, uuid/*, 0, 0*/, 143, 51, 56, 60);
//            this.mixbutton_array[index++] = new MixButton(skinningentities, uuid, x, y, 143, 51, 56, 60);
//                    this.mixbutton_array[index++] = new MixButton(SkinningEntities.CLIENT_ENTITIES_MAP.get(uuid), this.guiLeft + x, this.guiTop + y, 143, 51, 56, 60);

//            if (x >= this.width)
//            {
//                y += 60 + 10;
//                x = 10;
//            }
//            else
//            {
//                x += 56 + 10;
//            }
        }
    }
}
