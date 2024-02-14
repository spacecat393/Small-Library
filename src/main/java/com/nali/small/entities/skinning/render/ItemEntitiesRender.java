package com.nali.small.entities.skinning.render;

import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.mixin.IMixinLayerArmorBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nullable;

import static com.nali.system.opengl.memory.OpenGLCurrentMemory.GL_CULL_FACE;

@SideOnly(Side.CLIENT)
public class ItemEntitiesRender extends LayerRender
{
    public LayerBipedArmor layerbipedarmor;
    public int left_hand_i, left_hand_v,
            right_hand_i, right_hand_v,
            head_i, head_v,
            chest_i, chest_v,
            legs_i, legs_v,
            feet_i, feet_v;

    public static int DEBUG_I, DEBUG_V;

    public ItemEntitiesRender(SkinningEntities skinningentities)
    {
        super(skinningentities);
    }

    public void layer(SkinningEntitiesRender skinningentitiesrender, float partialTicks)
    {
//        this.legs_i = DEBUG_I;
//        this.legs_v = DEBUG_V;
        if (this.layerbipedarmor == null)
        {
            this.layerbipedarmor = new LayerBipedArmor(new RenderLivingBase(skinningentitiesrender.getRenderManager(), new ModelBase()
            {
                @Override
                public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
                {
                    super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
                }
            }, 0.0F)
            {
                @Nullable
                @Override
                protected ResourceLocation getEntityTexture(Entity entity)
                {
                    return null;
                }
            });
        }
        this.renderHeldItem(skinningentitiesrender, this.skinningentities.getHeldItemMainhand(), ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, this.right_hand_i, this.right_hand_v);
        this.renderHeldItem(skinningentitiesrender, this.skinningentities.getHeldItemOffhand(), ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND, this.left_hand_i, this.left_hand_v);

        this.renderArmor(skinningentitiesrender, EntityEquipmentSlot.HEAD, this.head_i, this.head_v, partialTicks);
        this.renderArmor(skinningentitiesrender, EntityEquipmentSlot.CHEST, this.chest_i, this.chest_v, partialTicks);
//        this.renderArmor(skinningentitiesrender, EntityEquipmentSlot.LEGS, this.legs_i, this.legs_v, partialTicks);
//        this.renderArmor(skinningentitiesrender, EntityEquipmentSlot.FEET, this.feet_i, this.feet_v, partialTicks);
    }

    public void renderHeldItem(SkinningEntitiesRender skinningentitiesrender, ItemStack itemstack, ItemCameraTransforms.TransformType transformtype, int i, int v)
    {
        if (!itemstack.isEmpty())
        {
            skinningentitiesrender.doModel(this.skinningentities, i, v, () ->
            {
                GL11.glRotatef(-this.skinningentities.rotationYaw - 180.0F, 0.0F, 0.0F, 1.0F);
//                GL11.glScalef(-0.25F, 0.25F, -0.25F);
                GL11.glScalef(0.25F, 0.25F, 0.25F);
                Minecraft.getMinecraft().getItemRenderer().renderItemSide(this.skinningentities, itemstack, transformtype, transformtype == ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND);
            });
        }
    }

    public void renderArmor(SkinningEntitiesRender skinningentitiesrender, EntityEquipmentSlot entityequipmentslot, int i, int v, float partialTicks)
    {
        ItemStack itemstack = this.skinningentities.getItemStackFromSlot(entityequipmentslot);

        if (itemstack.getItem() instanceof ItemArmor)
        {
            GL_CULL_FACE = GL11.glIsEnabled(GL11.GL_CULL_FACE);
            GL11.glDisable(GL11.GL_CULL_FACE);

            skinningentitiesrender.doModel(this.skinningentities, i, v, () ->
            {
                GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
                if (entityequipmentslot == EntityEquipmentSlot.HEAD)
                {
                    GL11.glTranslatef(0.0F, 0.4F, 0.0F);
                }
                else
                {
                    GL11.glRotatef(-this.skinningentities.renderYawOffset, 0.0F, 1.0F, 0.0F);
                    GL11.glTranslatef(0.0F, 0.75F, 0.0F);
                }
                GL11.glScalef(0.05F, -0.05F, -0.05F);
                ((IMixinLayerArmorBase)this.layerbipedarmor).GOrenderArmorLayer(this.skinningentities, this.skinningentities.limbSwing, this.skinningentities.limbSwingAmount, partialTicks, skinningentitiesrender.handleRotationFloat(this.skinningentities, partialTicks), this.skinningentities.rotationYaw, this.skinningentities.rotationPitch, 1.0F, entityequipmentslot);
            });

            if (GL_CULL_FACE)
            {
                GL11.glEnable(GL11.GL_CULL_FACE);
            }
            else
            {
                GL11.glDisable(GL11.GL_CULL_FACE);
            }
        }
    }
}
