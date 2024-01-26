package com.nali.list.gui;

import com.nali.list.container.InventoryContainer;
import com.nali.list.messages.SkinningEntitiesServerMessage;
import com.nali.render.SkinningRender;
import com.nali.small.data.BoxData;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.entities.skinning.SkinningEntitiesRender;
import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.messages.*;
import com.nali.small.gui.features.messages.inventory.*;
import com.nali.small.gui.features.messages.player.MimiTalkGUIFeatures;
import com.nali.small.gui.features.messages.works.*;
import com.nali.small.networks.NetworksRegistry;
import com.nali.small.render.BoxRender;
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

import java.io.IOException;

import static com.nali.small.render.RenderHelper.DATALOADER;
import static com.nali.system.Timing.TD;

@SideOnly(Side.CLIENT)
public class InventoryGui extends MixGui
{
    public static ResourceLocation GUI_RESOURCELOCATION = new ResourceLocation(Reference.MOD_ID, "textures/gui/inventory.png");
    public static byte PAGE;
    public SkinningEntitiesRender skinningentitiesrender;
    public float px, py/*, rx, ry*/;
    public BoxRender boxrender = new BoxRender(new BoxData(), DATALOADER);

    public InventoryGui(IInventory iinventory, SkinningEntities skinningentities)
    {
        super(new InventoryContainer(iinventory, skinningentities, Minecraft.getMinecraft().player));
        this.mc = Minecraft.getMinecraft();
        ScaledResolution scaledresolution = new ScaledResolution(this.mc);
        this.setWorldAndResolution(Minecraft.getMinecraft(), scaledresolution.getScaledWidth(), scaledresolution.getScaledHeight());

//        if (skinningentities != null)
//        {
        this.skinningentitiesrender = (SkinningEntitiesRender)this.mc.getRenderManager().getEntityRenderObject(skinningentities);
//        SkinningRender skinningrender = (SkinningRender)skinningentities.client_object;
//        float s = -25.0F;
//        skinningrender.sx = s;
//        skinningrender.sy = s;
//        skinningrender.sz = s;
//        skinningrender.y = 0.0F;
//        skinningrender.z = 10.0F;
//        this.rx = -1.57079632679F;
//        skinningrender.rx = -90.0F;
//        skinningdata.screen_float_array[6] = -90.0F;
//        skinningrender.rz = 0.0F;
//        }

        this.xSize = 256;//170;
        this.ySize = 256;//206;
//        this.boxrender.rx = 90.0F;
//        this.boxrender.ry = 45.0F;
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

//        ScaledResolution scaledresolution = new ScaledResolution(this.mc);
//        this.setWorldAndResolution(Minecraft.getMinecraft(), scaledresolution.getScaledWidth(), scaledresolution.getScaledHeight());

        SkinningEntities skinningentities = ((InventoryContainer)this.inventorySlots).skinningentities;
        SkinningRender skinningrender = (SkinningRender)skinningentities.client_object;
        if (!skinningrender.should_render)
        {
            this.skinningentitiesrender.updateData(skinningentities, this.mc.getRenderPartialTicks());
        }

//        skinningrender.width = this.width;
//        skinningrender.height = this.height;
        skinningrender.x = this.guiLeft + 127.5F;
        skinningrender.y = this.guiTop + 72;
//        skinningrender.sx = s;
//        skinningrender.sy = s;
//        skinningrender.sz = s;
        skinningrender.lig_b = 208.0F;
        skinningrender.lig_s = 240.0F;
        skinningrender.objectscreendraw.renderScreen(1.0F, 1.0F, 1.0F, 1.0F);

        this.boxrender.x = this.guiLeft + 84 + 6.5F + 0.5F;
        this.boxrender.y = this.guiTop + 33 + 18 + 18 + 6.5F + 2.5F;
        this.boxrender.objectscreendraw.renderScreen(1.0F, 1.0F, 1.0F, 0.9F);

        if (PAGE == 0)
        {
            super.drawScreen(mouseX, mouseY, partialTicks);
        }
        else
        {
            this.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
        }

//        InventoryContainer inventorycontainer = (InventoryContainer)this.inventorySlots;
//        SkinningData skinningdata = null;
//        if (skinningentities != null)
//        {
//        skinningentitiesrender.renderOnScreen(skinningentities, this.width, this.height, mouseX, mouseY);
//        }

//        float ty = 0.0F;
        int x = this.guiLeft + 100, y = this.guiTop + 26, width = 56, height = 49;
        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
        {
            if (this.mouse_clicked == 0)
            {
//                if (skinningentities != null)
//                {
                skinningrender.rz += ((mouseX - this.px) * 10.0F) * TD;
    //                skinningdata.screen_float_array[5] += ((mouseY - this.py) / 20.0F) * DataLoader.TD;
    //                ty = ;
    //                skinningdata.screen_float_array[12] = 0.476175F;
    //                this.rx += mouseX - this.px;
    //                this.ry += mouseY - this.py;
//                }
            }
        }

//        new Quaternion(this.rx, this.ry, 0.0F).getM4x4().multiply(skinningdata.skinning_float_array, 0);

        this.px = mouseX;
        this.py = mouseY;

//        if (skinningentities != null)
//        {
//        float s = -25.0F;
//        ScaledResolution scaledresolution = new ScaledResolution(this.mc);
//        if (scaledresolution.getScaleFactor() <= 1)//this.mc.gameSettings.guiScale == 0//if ((this.width / (double)this.height) <= 1.3325D || (this.height / (double)this.width) <= 0.5608D)
//        {
//            s = 0.1F;
//        }

//        new Quaternion(-rx, -ry, 0.0F).getM4x4().multiply(skinningdata.skinning_float_array, 0);
//        }

        if (PAGE == 0)
        {
            this.renderHoveredToolTip(mouseX, mouseY);
        }
        else if (PAGE == 2 || PAGE == 3)
        {
            this.setMessage();
        }

        this.renderEntitiesName(skinningentities, this.guiLeft + 100, this.guiTop + 75, 56, 11, mouseX, mouseY);

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
                this.sendPacketUUID((byte)18);
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
                this.sendPacketUUID((byte)2);
                this.sendPacketUUIDInt(skinningentities.skinningentitiesbytes.LOCK_INVENTORY());
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
                this.sendPacketUUID((byte)2);
                this.sendPacketUUIDInt(skinningentities.skinningentitiesbytes.LOCK_DAMAGE());
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
                this.sendPacket1((byte)9);
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
                this.sendPacketUUID((byte)8);
            }

            if (!(GUIFEATURESLOADER instanceof BoxGUIFeatures))
            {
                GUIFEATURESLOADER = new BoxGUIFeatures(this);
            }
            this.render_text = true;
        }

        if (PAGE > 0)
        {
            x = this.guiLeft + 48; y = this.guiTop + 89; width = 16; height = 16;
            if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
            {
                if (PAGE == 2)
                {
                    this.message_state = 0;

                    if (!(GUIFEATURESLOADER instanceof AddTargetGUIFeatures))
                    {
                        GUIFEATURESLOADER = new AddTargetGUIFeatures(this);
                    }
                    this.render_text = true;
                }
                else if (PAGE == 3)
                {
                    this.message_state = 5;

                    if (!(GUIFEATURESLOADER instanceof ScaleGUIFeatures))
                    {
                        GUIFEATURESLOADER = new ScaleGUIFeatures(this);
                    }
                    this.render_text = true;
                }
                else
                {
                    if (this.mouse_released == 0)
                    {
                        this.sendPacketUUIDInt(skinningentities.skinningentitiesbytes.SIT());
                    }

                    if (!(GUIFEATURESLOADER instanceof SitGUIFeatures))
                    {
                        GUIFEATURESLOADER = new SitGUIFeatures(this);
                    }
                    this.render_text = true;
                }
            }

            x = this.guiLeft + 66;// y = this.guiTop + 89; width = 16; height = 16;
            if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
            {
                if (PAGE == 2)
                {
                    this.message_state = 1;

                    if (!(GUIFEATURESLOADER instanceof AddTroublemakerGUIFeatures))
                    {
                        GUIFEATURESLOADER = new AddTroublemakerGUIFeatures(this);
                    }
                    this.render_text = true;
                }
                else if (PAGE == 3)
                {
                    this.message_state = 6;

                    if (!(GUIFEATURESLOADER instanceof AttributeStatGUIFeatures))
                    {
                        GUIFEATURESLOADER = new AttributeStatGUIFeatures(this);
                    }
                    this.render_text = true;
                }
                else
                {
                    int id = skinningentities.skinningentitiesbytes.FOLLOW();
                    if (id != -1)
                    {
                        if (this.mouse_released == 0)
                        {
                            this.sendPacketUUID((byte)2);
                            this.sendPacketUUIDInt(id);
                        }

                        if (!(GUIFEATURESLOADER instanceof FollowGUIFeatures))
                        {
                            GUIFEATURESLOADER = new FollowGUIFeatures(this);
                        }
                    }
                    else
                    {
                        if (!(GUIFEATURESLOADER instanceof CantFollowGUIFeatures))
                        {
                            GUIFEATURESLOADER = new CantFollowGUIFeatures(this);
                        }
                    }
                    this.render_text = true;
                }
            }

            x = this.guiLeft + 84;// y = this.guiTop + 89; width = 16; height = 16;
            if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
            {
                if (PAGE == 3)
                {
                    this.message_state = 7;

                    if (!(GUIFEATURESLOADER instanceof LookGUIFeatures))
                    {
                        GUIFEATURESLOADER = new LookGUIFeatures(this);
                    }
                    this.render_text = true;
                }
                else if (PAGE == 2)
                {
                    this.message_state = 4;

                    if (!(GUIFEATURESLOADER instanceof EntityListGUIFeatures))
                    {
                        GUIFEATURESLOADER = new EntityListGUIFeatures(this);
                    }
                    this.render_text = true;
                }
                else if (PAGE == 1)
                {
                    if (this.mouse_released == 0)
                    {
                        this.sendPacketUUIDInt(skinningentities.skinningentitiesbytes.FIND_ITEM());
                    }

                    if (!(GUIFEATURESLOADER instanceof FindItemGUIFeatures))
                    {
                        GUIFEATURESLOADER = new FindItemGUIFeatures(this);
                    }
                    this.render_text = true;
                }
            }

            x = this.guiLeft + 102;// y = this.guiTop + 89; width = 16; height = 16;
            if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
            {
                if (PAGE == 1)
                {
                    int id = skinningentities.skinningentitiesbytes.RANDOM_WALK();
                    if (id != -1)
                    {
                        if (this.mouse_released == 0)
                        {
                            this.sendPacketUUIDInt(skinningentities.skinningentitiesbytes.RANDOM_WALK());
                        }

                        if (!(GUIFEATURESLOADER instanceof RandomWalkGUIFeatures))
                        {
                            GUIFEATURESLOADER = new RandomWalkGUIFeatures(this);
                        }
                    }
                    else
                    {
                        if (!(GUIFEATURESLOADER instanceof CantRandomWalkGUIFeatures))
                        {
                            GUIFEATURESLOADER = new CantRandomWalkGUIFeatures(this);
                        }
                    }
                    this.render_text = true;
                }
            }

            x = this.guiLeft + 120;// y = this.guiTop + 89; width = 16; height = 16;
            if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
            {
                if (PAGE == 1)
                {
                    int id = skinningentities.skinningentitiesbytes.RANDOM_LOOK();
                    if (id != -1)
                    {
                        if (this.mouse_released == 0)
                        {
                            this.sendPacketUUIDInt(skinningentities.skinningentitiesbytes.RANDOM_LOOK());
                        }

                        if (!(GUIFEATURESLOADER instanceof RandomLookGUIFeatures))
                        {
                            GUIFEATURESLOADER = new RandomLookGUIFeatures(this);
                        }
                    }
                    else
                    {
                        if (!(GUIFEATURESLOADER instanceof CantRandomLookGUIFeatures))
                        {
                            GUIFEATURESLOADER = new CantRandomLookGUIFeatures(this);
                        }
                    }
                    this.render_text = true;
                }
            }

            x = this.guiLeft + 138;// y = this.guiTop + 89; width = 16; height = 16;
            if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
            {
                if (PAGE == 1)
                {
                    int id = skinningentities.skinningentitiesbytes.ATTACK();
                    if (id != -1)
                    {
                        if (this.mouse_released == 0)
                        {
                            this.sendPacketUUIDInt(id);
                        }

                        if (!(GUIFEATURESLOADER instanceof AttackGUIFeatures))
                        {
                            GUIFEATURESLOADER = new AttackGUIFeatures(this);
                        }
                    }
                    else
                    {
                        if (!(GUIFEATURESLOADER instanceof CantAttackGUIFeatures))
                        {
                            GUIFEATURESLOADER = new CantAttackGUIFeatures(this);
                        }
                    }
                    this.render_text = true;
                }
            }

            x = this.guiLeft + 156;// y = this.guiTop + 89; width = 16; height = 16;
            if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
            {
                if (PAGE == 1)
                {
                    int id = skinningentities.skinningentitiesbytes.REVIVE();
                    if (id != -1)
                    {
                        if (this.mouse_released == 0)
                        {
                            this.sendPacketUUID((byte)2);
                            this.sendPacketUUIDInt(skinningentities.skinningentitiesbytes.REVIVE());
                        }

                        if (!(GUIFEATURESLOADER instanceof ReviveGUIFeatures))
                        {
                            GUIFEATURESLOADER = new ReviveGUIFeatures(this);
                        }
                    }
                    else
                    {
                        if (!(GUIFEATURESLOADER instanceof CantReviveGUIFeatures))
                        {
                            GUIFEATURESLOADER = new CantReviveGUIFeatures(this);
                        }
                    }
                    this.render_text = true;
                }
            }

            x = this.guiLeft + 156 + 18;// y = this.guiTop + 89; width = 16; height = 16;
            if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
            {
                if (PAGE == 1)
                {
                    int id = skinningentities.skinningentitiesbytes.HEAL();
                    if (id != -1)
                    {
                        if (this.mouse_released == 0)
                        {
                            this.sendPacketUUIDInt(id);
                        }

                        if (!(GUIFEATURESLOADER instanceof HealGUIFeatures))
                        {
                            GUIFEATURESLOADER = new HealGUIFeatures(this);
                        }
                    }
                    else
                    {
                        if (!(GUIFEATURESLOADER instanceof CantHealGUIFeatures))
                        {
                            GUIFEATURESLOADER = new CantHealGUIFeatures(this);
                        }
                    }
                    this.render_text = true;
                }
            }

            x = this.guiLeft + 156 + 18 + 18;// y = this.guiTop + 89; width = 16; height = 16;
            if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
            {
                if (PAGE == 1)
                {
                    int id = skinningentities.skinningentitiesbytes.PROTECT();
                    if (id != -1)
                    {
                        if (this.mouse_released == 0)
                        {
                            this.sendPacketUUIDInt(id);
                        }

                        if (!(GUIFEATURESLOADER instanceof ProtectGUIFeatures))
                        {
                            GUIFEATURESLOADER = new ProtectGUIFeatures(this);
                        }
                    }
                    else
                    {
                        if (!(GUIFEATURESLOADER instanceof CantProtectGUIFeatures))
                        {
                            GUIFEATURESLOADER = new CantProtectGUIFeatures(this);
                        }
                    }
                    this.render_text = true;
                }
            }

            x = this.guiLeft + 48; y = this.guiTop + 107;// width = 16; height = 16;
            if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
            {
                if (PAGE == 1)
                {
                    int id = skinningentities.skinningentitiesbytes.PLAY();
                    if (id != -1)
                    {
                        if (this.mouse_released == 0)
                        {
                            this.sendPacketUUIDInt(skinningentities.skinningentitiesbytes.PLAY());
                        }

                        if (!(GUIFEATURESLOADER instanceof PlayGUIFeatures))
                        {
                            GUIFEATURESLOADER = new PlayGUIFeatures(this);
                        }
                    }
                    else
                    {
                        if (!(GUIFEATURESLOADER instanceof CantPlayGUIFeatures))
                        {
                            GUIFEATURESLOADER = new CantPlayGUIFeatures(this);
                        }
                    }
                    this.render_text = true;
                }
                else if (PAGE == 2)
                {
                    this.message_state = 2;

                    if (!(GUIFEATURESLOADER instanceof RemoveTargetGUIFeatures))
                    {
                        GUIFEATURESLOADER = new RemoveTargetGUIFeatures(this);
                    }
                    this.render_text = true;
                }
            }

            /*x = this.guiLeft + 48; */y = this.guiTop + 125;// width = 16; height = 16;
            if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
            {
                if (PAGE == 2)
                {
                    if (this.mouse_released == 0)
                    {
                        this.sendPacketUUID((byte)11);
                    }

                    if (!(GUIFEATURESLOADER instanceof TargetGUIFeatures))
                    {
                        GUIFEATURESLOADER = new TargetGUIFeatures(this);
                    }
                    this.render_text = true;
                }
            }

            x = this.guiLeft + 66; y = this.guiTop + 107;// width = 16; height = 16;
            if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
            {
                if (PAGE == 2)
                {
                    this.message_state = 3;

                    if (!(GUIFEATURESLOADER instanceof RemoveTroublemakerGUIFeatures))
                    {
                        GUIFEATURESLOADER = new RemoveTroublemakerGUIFeatures(this);
                    }
                    this.render_text = true;
                }
            }

            /*x = this.guiLeft + 66; */y = this.guiTop + 125;// width = 16; height = 16;
            if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height)
            {
                if (PAGE == 2)
                {
                    if (this.mouse_released == 0)
                    {
                        this.sendPacketUUID((byte)14);
                    }

                    if (!(GUIFEATURESLOADER instanceof TroublemakerGUIFeatures))
                    {
                        GUIFEATURESLOADER = new TroublemakerGUIFeatures(this);
                    }
                    this.render_text = true;
                }
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
        if (PAGE == 0)
        {
            this.drawTexturedModalRect(tx, this.guiTop + 33, 238, 0, 14, 14);
        }
        else if (PAGE == 2)
        {
            this.drawTexturedModalRect(tx, this.guiTop + 33, 44, 0, 14, 14);
        }
        else if (PAGE == 3)
        {
            this.drawTexturedModalRect(tx, this.guiTop + 33, 16, 14, 14, 14);
        }
        else
        {
            this.drawTexturedModalRect(tx, this.guiTop + 33, 16, 0, 14, 14);
        }

        this.drawTexturedModalRect(tx, this.guiTop + 53/*33 + 18 + 2*/, 224, 0, 14, 11);

        for (int i = 0; i < 36; ++i)
        {
            Slot slot = this.inventorySlots.inventorySlots.get(i);
            if (slot.getStack().isEmpty())
            {
                this.drawTexturedModalRect(this.guiLeft + 4 + slot.xPos, this.guiTop + 5 + slot.yPos, 30, 14, 8, 6);
            }
        }

        if (PAGE == 0)
        {
            for (int i = 36; i < 63; ++i)
            {
                Slot slot = this.inventorySlots.inventorySlots.get(i);

                if (slot.getStack().isEmpty())
                {
                    this.drawTexturedModalRect(this.guiLeft + 4 + slot.xPos, this.guiTop + 5 + slot.yPos, 30, 14, 8, 6);
                }
            }
        }

        SkinningEntities skinningentities = ((InventoryContainer)this.inventorySlots).skinningentities;

        switch (PAGE)
        {
            case 0:
            {
                Slot slot = this.inventorySlots.inventorySlots.get(63);
                if (slot.getStack().isEmpty())
                {
                    this.drawTexturedModalRect(this.guiLeft + 1 + slot.xPos, this.guiTop + 1 + slot.yPos, 0, 104, 14, 14);
                }
                slot = this.inventorySlots.inventorySlots.get(64);
                if (slot.getStack().isEmpty())
                {
                    this.drawTexturedModalRect(this.guiLeft + 1 + slot.xPos, this.guiTop + 1 + slot.yPos, 0, 118, 14, 14);
                }
                slot = this.inventorySlots.inventorySlots.get(65);
                if (slot.getStack().isEmpty())
                {
                    this.drawTexturedModalRect(this.guiLeft + 1 + slot.xPos, this.guiTop + 1 + slot.yPos, 0, 48, 14, 14);
                }
                slot = this.inventorySlots.inventorySlots.get(66);
                if (slot.getStack().isEmpty())
                {
                    this.drawTexturedModalRect(this.guiLeft + 1 + slot.xPos, this.guiTop + 1 + slot.yPos, 0, 62, 14, 14);
                }
                slot = this.inventorySlots.inventorySlots.get(67);
                if (slot.getStack().isEmpty())
                {
                    this.drawTexturedModalRect(this.guiLeft + 3 + slot.xPos, this.guiTop + 1 + slot.yPos, 0, 76, 10, 14);
                }
                slot = this.inventorySlots.inventorySlots.get(68);
                if (slot.getStack().isEmpty())
                {
                    this.drawTexturedModalRect(this.guiLeft + 1 + slot.xPos, this.guiTop + 1 + slot.yPos, 0, 90, 14, 14);
                }

                break;
            }
            case 1:
            {
                this.drawTexturedModalRect(this.guiLeft + 49, this.guiTop + 90, 86, 0, 14, 14);

                if (skinningentities.skinningentitiesbytes.FOLLOW() == -1)
                {
                    this.drawTexturedModalRect(this.guiLeft + 67, this.guiTop + 90, 134, 14, 14, 14);
                }
                else
                {
                    this.drawTexturedModalRect(this.guiLeft + 67, this.guiTop + 90, 30, 0, 14, 14);
                }

                this.drawTexturedModalRect(this.guiLeft + 85, this.guiTop + 90, 142, 0, 14, 14);

                if (skinningentities.skinningentitiesbytes.RANDOM_WALK() == -1)
                {
                    this.drawTexturedModalRect(this.guiLeft + 103, this.guiTop + 90, 134, 14, 14, 14);
                }
                else
                {
                    this.drawTexturedModalRect(this.guiLeft + 105, this.guiTop + 92, 52, 14, 12, 12);
                }

                if (skinningentities.skinningentitiesbytes.RANDOM_LOOK() == -1)
                {
                    this.drawTexturedModalRect(this.guiLeft + 121, this.guiTop + 90, 134, 14, 14, 14);
                }
                else
                {
                    this.drawTexturedModalRect(this.guiLeft + 121, this.guiTop + 94, 38, 14, 14, 8);
                }

                if (skinningentities.skinningentitiesbytes.ATTACK() == -1)
                {
                    this.drawTexturedModalRect(this.guiLeft + 139, this.guiTop + 90, 134, 14, 14, 14);
                }
                else
                {
                    this.drawTexturedModalRect(this.guiLeft + 139, this.guiTop + 90, 58, 0, 14, 14);
                }

                if (skinningentities.skinningentitiesbytes.REVIVE() == -1)
                {
                    this.drawTexturedModalRect(this.guiLeft + 157, this.guiTop + 90, 134, 14, 14, 14);
                }
                else
                {
                    this.drawTexturedModalRect(this.guiLeft + 157, this.guiTop + 90, 64, 14, 14, 14);
                }

                if (skinningentities.skinningentitiesbytes.HEAL() == -1)
                {
                    this.drawTexturedModalRect(this.guiLeft + 157 + 18, this.guiTop + 90, 134, 14, 14, 14);
                }
                else
                {
                    this.drawTexturedModalRect(this.guiLeft + 157 + 18, this.guiTop + 90, 114, 0, 14, 14);
                }

                if (skinningentities.skinningentitiesbytes.PROTECT() == -1)
                {
                    this.drawTexturedModalRect(this.guiLeft + 157 + 18 + 18, this.guiTop + 90, 134, 14, 14, 14);
                }
                else
                {
                    this.drawTexturedModalRect(this.guiLeft + 157 + 18 + 18, this.guiTop + 90, 148, 14, 14, 14);
                }

                if (skinningentities.skinningentitiesbytes.PLAY() == -1)
                {
                    this.drawTexturedModalRect(this.guiLeft + 49, this.guiTop + 90 + 18, 134, 14, 14, 14);
                }
                else
                {
                    this.drawTexturedModalRect(this.guiLeft + 49, this.guiTop + 90 + 18, 182, 0, 14, 14);
                }

                break;
            }
            case 2:
            {
                this.drawTexturedModalRect(this.guiLeft + 49, this.guiTop + 90, 106, 14, 14, 14);
                this.drawTexturedModalRect(this.guiLeft + 67, this.guiTop + 90, 78, 14, 14, 14);
                this.drawTexturedModalRect(this.guiLeft + 67 + 18, this.guiTop + 90, 16, 0, 14, 14);
                this.drawTexturedModalRect(this.guiLeft + 49, this.guiTop + 108, 92, 14, 14, 14);
                this.drawTexturedModalRect(this.guiLeft + 67, this.guiTop + 108, 92, 14, 14, 14);
                this.drawTexturedModalRect(this.guiLeft + 49, this.guiTop + 126, 120, 14, 14, 14);
                this.drawTexturedModalRect(this.guiLeft + 67, this.guiTop + 126, 120, 14, 14, 14);
                break;
            }
            case 3:
            {
                this.drawTexturedModalRect(this.guiLeft + 49, this.guiTop + 90, 16, 28, 14, 14);
                this.drawTexturedModalRect(this.guiLeft + 67, this.guiTop + 90, 106, 14, 14, 14);
                this.drawTexturedModalRect(this.guiLeft + 67 + 18, this.guiTop + 94, 38, 14, 14, 8);
//                this.drawTexturedModalRect(this.guiLeft + 67 + 18 + 18, this.guiTop + 90, 44, 0, 14, 14);
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

        this.drawTexturedModalRect(this.guiLeft + 57, this.guiTop + 27, 30, skinningentities.client_work_byte_array[skinningentities.skinningentitiesbytes.LOCK_INVENTORY()] == 1 ? 20 : 26, 5, 6);
        this.drawTexturedModalRect(this.guiLeft + 57, this.guiTop + 35, 30, skinningentities.client_work_byte_array[skinningentities.skinningentitiesbytes.LOCK_DAMAGE()] == 1 ? 20 : 26, 5, 6);

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
        if (this.message_state != -1)
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
                    if (this.message_state != 4)
                    {
                        SkinningEntities skinningentities = ((InventoryContainer)this.inventorySlots).skinningentities;

                        String[] string_array = MESSAGE_STRINGBUILDER.deleteCharAt(MESSAGE_STRINGBUILDER.length() - 1).toString().split(" ");
                        byte[] byte_array = new byte[string_array.length * 4 + 1 + 16];
                        int new_index = 1 + 16;
                        for (String new_string : string_array)
                        {
                            try
                            {
                                if (this.message_state == 5 || this.message_state == 6 || this.message_state == 7)
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
                                byte_array[0] = 10;
                                break;
                            }
                            case 1:
                            {
                                byte_array[0] = 13;
                                break;
                            }
                            case 2:
                            {
                                byte_array[0] = 12;
                                break;
                            }
                            case 3:
                            {
                                byte_array[0] = 15;
                                break;
                            }
                            case 5:
                            {
                                byte_array[0] = 20;
                                break;
                            }
                            case 6:
                            {
                                byte_array[0] = 21;
                                break;
                            }
                            case 7:
                            {
                                byte_array[0] = 22;
                                break;
                            }
                            default:
                            {
                                break;
                            }
                        }

                        BytesWriter.set(byte_array, skinningentities.client_uuid, 1);
                        NetworksRegistry.I.sendToServer(new SkinningEntitiesServerMessage(byte_array));
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

//    @Override
//    public void initGui()
//    {
//        super.initGui();
//        SkinningData skinningdata = (SkinningData)((InventoryContainer)this.inventorySlots).skinningentities.client_object;
//        skinningdata.screen_float_array[7] = 0.0F;
//        skinningdata.screen_float_array[5] = -1.57079632679F;
//        skinningdata.screen_float_array[3] = 0.0F;
////        this.px = this.guiLeft + 127.5F;
////        this.py = this.guiTop + 50;
//    }
}
