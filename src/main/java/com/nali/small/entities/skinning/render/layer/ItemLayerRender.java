package com.nali.small.entities.skinning.render.layer;

import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.entities.skinning.render.SkinningEntitiesRender;
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
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nullable;

import static com.nali.small.entities.skinning.render.SkinningEntitiesRenderMath.handleRotationFloat;
import static com.nali.small.entities.skinning.render.SkinningEntitiesRenderMath.lookAt;
import static com.nali.system.opengl.memory.OpenGLCurrentMemory.GL_CULL_FACE;

@SideOnly(Side.CLIENT)
public class ItemLayerRender extends LayerRender
{
    public LayerBipedArmor layerbipedarmor;
    public int c_left_hand_i, c_left_hand_v, t_left_hand_i, t_left_hand_v,
            c_right_hand_i, c_right_hand_v, t_right_hand_i, t_right_hand_v,
            c_head_i, c_head_v, t_head_i, t_head_v,
            c_chest_i, c_chest_v, t_chest_i, t_chest_v,
            c_legs_i, c_legs_v, t_legs_i, t_legs_v,
            c_feet_i, c_feet_v, t_feet_i, t_feet_v;

    public float hand_y, hand_p,
            head_y, head_p;

    public static int DEBUG_I, DEBUG_V;

    public ItemLayerRender(SkinningEntities skinningentities)
    {
        super(skinningentities);
    }

    public void layer(SkinningEntitiesRender skinningentitiesrender, float partialTicks)
    {
//        this.t_chest_i = DEBUG_I;
        this.c_chest_v = DEBUG_V;
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

        this.renderHeldItem(skinningentitiesrender, this.skinningentities.getHeldItemMainhand(), ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, this.c_right_hand_i, this.c_right_hand_v, this.t_right_hand_i, this.t_right_hand_v);
        this.renderHeldItem(skinningentitiesrender, this.skinningentities.getHeldItemOffhand(), ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND, this.c_left_hand_i, this.c_left_hand_v, this.t_left_hand_i, this.t_left_hand_v);

        this.renderArmor(skinningentitiesrender, EntityEquipmentSlot.HEAD, this.c_head_i, this.c_head_v, this.t_head_i, this.t_head_v, partialTicks);
        this.renderArmor(skinningentitiesrender, EntityEquipmentSlot.CHEST, this.c_chest_i, this.c_chest_v, this.t_chest_i, this.t_chest_v, partialTicks);
        this.renderArmor(skinningentitiesrender, EntityEquipmentSlot.LEGS, this.c_legs_i, this.c_legs_v, this.t_legs_i, this.t_legs_v, partialTicks);
        this.renderArmor(skinningentitiesrender, EntityEquipmentSlot.FEET, this.c_feet_i, this.c_feet_v, this.t_feet_i, this.t_feet_v, partialTicks);
    }

    public void renderHeldItem(SkinningEntitiesRender skinningentitiesrender, ItemStack itemstack, ItemCameraTransforms.TransformType transformtype, int c_i, int c_v, int t_i, int t_v)
    {
        if (!itemstack.isEmpty())
        {
            GL11.glPushMatrix();
            Vec3d c_vec3d = skinningentitiesrender.doModel(this.skinningentities, c_i, c_v);
            Vec3d t_vec3d = skinningentitiesrender.doModel(this.skinningentities, t_i, t_v);
            Vec3d l_vec3d = lookAt(c_vec3d, t_vec3d);
            GL11.glTranslatef((float)c_vec3d.x, (float)c_vec3d.y, (float)c_vec3d.z);
            GL11.glScalef(0.25F, 0.25F, 0.25F);
            GL11.glRotatef((float)-l_vec3d.x + this.hand_y, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef((float)-l_vec3d.y + this.hand_p, 1.0F, 0.0F, 0.0F);
            Minecraft.getMinecraft().getItemRenderer().renderItemSide(this.skinningentities, itemstack, transformtype, transformtype == ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND);
            GL11.glPopMatrix();
        }
    }

    public void renderArmor(SkinningEntitiesRender skinningentitiesrender, EntityEquipmentSlot entityequipmentslot, int c_i, int c_v, int t_i, int t_v, float partialTicks)
    {
        ItemStack itemstack = this.skinningentities.getItemStackFromSlot(entityequipmentslot);

        if (itemstack.getItem() instanceof ItemArmor)
        {
            GL_CULL_FACE = GL11.glIsEnabled(GL11.GL_CULL_FACE);
            GL11.glDisable(GL11.GL_CULL_FACE);

            Vec3d c_vec3d = skinningentitiesrender.doModel(this.skinningentities, c_i, c_v);
            Vec3d t_vec3d = skinningentitiesrender.doModel(this.skinningentities, t_i, t_v);
            Vec3d l_vec3d = lookAt(c_vec3d, t_vec3d);
            GL11.glPushMatrix();
            GL11.glTranslatef((float)c_vec3d.x, (float)c_vec3d.y, (float)c_vec3d.z);

//            GL11.glPointSize(10.0F);
//            GL11.glColor4f(1.0F, 0.0F, 0.0F, 1.0F);
//            GL11.glBegin(GL11.GL_POINTS);
//            GL11.glVertex3f(0.0F, 0.0F, 0.0F);
//            GL11.glEnd();

            GL11.glRotatef((float)-l_vec3d.x + this.head_y, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef((float)-l_vec3d.y + this.head_p, 1.0F, 0.0F, 0.0F);
            if (entityequipmentslot == EntityEquipmentSlot.HEAD)
            {
                GL11.glTranslatef(0.0F, 0.4F, 0.0F);
            }
            else
            {
                GL11.glTranslatef(0.0F, 0.75F, 0.0F);
            }
            GL11.glScalef(0.05F, 0.05F, 0.05F);

            GL11.glScalef(1.0F, -1.0F, -1.0F);
            ((IMixinLayerArmorBase)this.layerbipedarmor).GOrenderArmorLayer(this.skinningentities, this.skinningentities.limbSwing, this.skinningentities.limbSwingAmount, partialTicks, handleRotationFloat(this.skinningentities, partialTicks), 0.0F, 0.0F, 1.0F, entityequipmentslot);

            GL11.glPopMatrix();

//            GL11.glPushMatrix();
//            GL11.glTranslatef((float)t_vec3d.x, (float)t_vec3d.y, (float)t_vec3d.z);
//            GL11.glPointSize(10.0F);
//            GL11.glColor4f(0.0F, 0.0F, 1.0F, 1.0F);
//            GL11.glBegin(GL11.GL_POINTS);
//            GL11.glVertex3f(0.0F, 0.0F, 0.0F);
//            GL11.glEnd();
//            GL11.glPopMatrix();

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
