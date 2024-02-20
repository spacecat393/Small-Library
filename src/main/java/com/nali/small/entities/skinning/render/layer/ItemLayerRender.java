package com.nali.small.entities.skinning.render.layer;

import com.mojang.authlib.GameProfile;
import com.nali.list.items.SmallBox;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.entities.skinning.render.SkinningEntitiesRender;
import com.nali.small.mixin.IMixinLayerArmorBase;
import com.nali.system.opengl.memory.OpenGLCurrentMemory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerElytra;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.StringUtils;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nullable;

import static com.nali.small.entities.skinning.render.SkinningEntitiesRenderMath.handleRotationFloat;
import static com.nali.system.opengl.memory.OpenGLCurrentMemory.*;

@SideOnly(Side.CLIENT)
public class ItemLayerRender extends LayerRender
{
    public static boolean ARMOR;
    public static float Y, P;

    public LayerBipedArmor layerbipedarmor;
    public LayerElytra layerelytra;
    public int c_left_hand_i, c_left_hand_v, t_left_hand_i, t_left_hand_v,
            c_right_hand_i, c_right_hand_v, t_right_hand_i, t_right_hand_v,
            c_head_i, c_head_v, t_head_i, t_head_v,
            chest_i, chest_cv, chest_tyv, chest_tpv,
            c_legs_i, c_legs_v, t_legs_i, t_legs_v,
            c_feet_i, c_feet_v, t_feet_i, t_feet_v,
            c_mouth_i, c_mouth_v, t_mouth_i, t_mouth_v;

//    public float left_hand_y, left_hand_p,
//            right_hand_y, right_hand_p,
//            head_y, head_p,
//            chest_y, chest_p,
//            legs_y, legs_p,
//            feet_y, feet_p;

    public static int DEBUG_I, DEBUG_V;
    public float x, y, z;

    public ItemLayerRender(SkinningEntities skinningentities)
    {
        super(skinningentities);
    }

    public void layer(SkinningEntitiesRender skinningentitiesrender, float partialTicks)
    {
        this.chest_tyv = DEBUG_V;
        this.chest_tpv = DEBUG_I;
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

        if (this.layerelytra == null)
        {
            this.layerelytra = new LayerElytra(new RenderLivingBase(skinningentitiesrender.getRenderManager(), new ModelBase()
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

        //
        OPENGL_FIXED_PIPE_FLOATBUFFER.limit(16);
        GL11.glGetFloat(GL11.GL_CURRENT_COLOR, OPENGL_FIXED_PIPE_FLOATBUFFER);
        GL_CURRENT_COLOR[0] = OPENGL_FIXED_PIPE_FLOATBUFFER.get(0);
        GL_CURRENT_COLOR[1] = OPENGL_FIXED_PIPE_FLOATBUFFER.get(1);
        GL_CURRENT_COLOR[2] = OPENGL_FIXED_PIPE_FLOATBUFFER.get(2);
        GL_CURRENT_COLOR[3] = OPENGL_FIXED_PIPE_FLOATBUFFER.get(3);

        this.renderHeldItem(skinningentitiesrender, this.skinningentities.getHeldItemMainhand(), ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, this.c_right_hand_i, this.c_right_hand_v, this.t_right_hand_i, this.t_right_hand_v);
        this.renderHeldItem(skinningentitiesrender, this.skinningentities.getHeldItemOffhand(), ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND, this.c_left_hand_i, this.c_left_hand_v, this.t_left_hand_i, this.t_left_hand_v);
        this.renderHeldItem(skinningentitiesrender, this.skinningentities.skinninginventory.armor_itemstack_nonnulllist.get(4), ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, this.c_mouth_i, this.c_mouth_v, this.t_mouth_i, this.t_mouth_v);

        this.renderArmor(skinningentitiesrender, EntityEquipmentSlot.HEAD, this.c_head_i, this.c_head_v, this.t_head_i, this.t_head_v, partialTicks);
        this.renderArmor(skinningentitiesrender, EntityEquipmentSlot.CHEST, this.chest_i, this.chest_cv, this.chest_tyv, this.chest_tpv, partialTicks);
        this.renderArmor(skinningentitiesrender, EntityEquipmentSlot.LEGS, this.c_legs_i, this.c_legs_v, this.t_legs_i, this.t_legs_v, partialTicks);
        this.renderArmor(skinningentitiesrender, EntityEquipmentSlot.FEET, this.c_feet_i, this.c_feet_v, this.t_feet_i, this.t_feet_v, partialTicks);
    }

    public void renderHeldItem(SkinningEntitiesRender skinningentitiesrender, ItemStack itemstack, ItemCameraTransforms.TransformType transformtype, int i, int cv, int tyv, int tpv)
    {
        if (!itemstack.isEmpty())
        {
            float[] c_vec4 = skinningentitiesrender.get3DSkinning(this.skinningentities, this.x, this.y, this.z, i, cv);
            float[] ty_vec4 = skinningentitiesrender.get3DSkinning(this.skinningentities, this.x, this.y, this.z, i, tyv);
            float[] tp_vec4 = skinningentitiesrender.get3DSkinning(this.skinningentities, this.x, this.y, this.z, i, tpv);
//            float[] ly_vec2 = lookAt(c_vec4, ty_vec4);
//            float[] lp_vec2 = lookAt(c_vec4, tp_vec4);
            GL11.glPushMatrix();
            skinningentitiesrender.apply3DSkinningVec4(c_vec4);

            //
            GL11.glPushMatrix();
            GL11.glScalef(0.05F, 0.05F, 0.05F);
//            GL11.glPointSize(10.0F);
//            GL11.glColor4f(1.0F, 0.0F, 0.0F, 1.0F);
//            GL11.glBegin(GL11.GL_POINTS);
//            GL11.glVertex3f(0.0F, 0.0F, 0.0F);
//            GL11.glEnd();
            Minecraft.getMinecraft().getItemRenderer().renderItemSide(this.skinningentities, SmallBox.I.getDefaultInstance(), ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, false);
            GL11.glPopMatrix();

            GL11.glScalef(0.25F, 0.25F, 0.25F);
            boolean left_hand = transformtype == ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND;
//            if (left_hand)
//            {
//                GL11.glRotatef(-l_vec2[0] + this.left_hand_y, 0.0F, 1.0F, 0.0F);
//                GL11.glRotatef(-l_vec2[1] + this.left_hand_p, 1.0F, 0.0F, 0.0F);
//            }
//            else
//            {
//                GL11.glRotatef(-l_vec2[0] + this.right_hand_y, 0.0F, 1.0F, 0.0F);
//                GL11.glRotatef(-l_vec2[1] + this.right_hand_p, 1.0F, 0.0F, 0.0F);
//            }
//            GL11.glRotatef(-ly_vec2[0] - lp_vec2[0], 0.0F, 1.0F, 0.0F);
//            GL11.glRotatef(-ly_vec2[1] - lp_vec2[1], 1.0F, 0.0F, 0.0F);

//            GL11.glScalef(1.0F, -1.0F, -1.0F);
            Minecraft.getMinecraft().getItemRenderer().renderItemSide(this.skinningentities, itemstack, transformtype, left_hand);
            GL11.glPopMatrix();

            //

            GL11.glPushMatrix();
            skinningentitiesrender.apply3DSkinningVec4(tp_vec4);
            GL11.glScalef(0.05F, 0.05F, 0.05F);
            GL11.glColor4f(0.0F, 1.0F, 0.0F, 1.0F);
            Minecraft.getMinecraft().getItemRenderer().renderItemSide(this.skinningentities, SmallBox.I.getDefaultInstance(), ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, false);
            GL11.glColor4f(GL_CURRENT_COLOR[0], GL_CURRENT_COLOR[1], GL_CURRENT_COLOR[2], GL_CURRENT_COLOR[3]);
            GL11.glPopMatrix();

            GL11.glPushMatrix();
            skinningentitiesrender.apply3DSkinningVec4(ty_vec4);
            GL11.glScalef(0.05F, 0.05F, 0.05F);
            GL11.glColor4f(0.0F, 0.0F, 0.0F, 1.0F);
            Minecraft.getMinecraft().getItemRenderer().renderItemSide(this.skinningentities, SmallBox.I.getDefaultInstance(), ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, false);
            GL11.glColor4f(GL_CURRENT_COLOR[0], GL_CURRENT_COLOR[1], GL_CURRENT_COLOR[2], GL_CURRENT_COLOR[3]);
            GL11.glPopMatrix();
        }
    }

    public void renderArmor(SkinningEntitiesRender skinningentitiesrender, EntityEquipmentSlot entityequipmentslot, int i, int cv, int tyv, int tpv, float partialTicks)
    {
        ItemStack itemstack = this.skinningentities.getItemStackFromSlot(entityequipmentslot);
        GL_CULL_FACE = GL11.glIsEnabled(GL11.GL_CULL_FACE);
        GL11.glDisable(GL11.GL_CULL_FACE);

        // 3 dots
        float[] c_vec4 = skinningentitiesrender.get3DSkinning(this.skinningentities, this.x, this.y, this.z, i, cv);
        float[] ty_vec4 = skinningentitiesrender.get3DSkinning(this.skinningentities, this.x, this.y, this.z, i, tyv);
        float[] tp_vec4 = skinningentitiesrender.get3DSkinning(this.skinningentities, this.x, this.y, this.z, i, tpv);
//        float[] ly_vec2 = lookAt(c_vec4, ty_vec4);
//        float[] lp_vec2 = lookAt(c_vec4, tp_vec4);
        GL11.glPushMatrix();
        skinningentitiesrender.apply3DSkinningVec4(c_vec4);

        //
        if (!itemstack.isEmpty())
        {
            GL11.glPushMatrix();
            GL11.glScalef(0.05F, 0.05F, 0.05F);
            Minecraft.getMinecraft().getItemRenderer().renderItemSide(this.skinningentities, SmallBox.I.getDefaultInstance(), ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, false);
            GL11.glPopMatrix();
        }

//        float y = 0.0F;
//        switch (entityequipmentslot.getIndex())
//        {
//            case 0:
//            {
////                GL11.glRotatef(l_vec2[0] - this.feet_y, 0.0F, 1.0F, 0.0F);
////                GL11.glRotatef(-l_vec2[1] + this.feet_p, 1.0F, 0.0F, 0.0F);
//                break;
//            }
//            case 1:
//            {
////                GL11.glRotatef(l_vec2[0] - this.legs_y, 0.0F, 1.0F, 0.0F);
////                GL11.glRotatef(-l_vec2[1] + this.legs_p, 1.0F, 0.0F, 0.0F);
//                break;
//            }
//            case 2:
//            {
////                GL11.glRotatef(l_vec2[0] - this.chest_y - 180.0F, 0.0F, 1.0F, 0.0F);
////                GL11.glRotatef(-l_vec2[1] + this.chest_p + 90.0F, 1.0F, 0.0F, 0.0F);
////                y = 180.0F;
//                break;
//            }
//            case 3:
//            {
////                GL11.glRotatef(-l_vec2[0] + this.head_y - 180.0F, 0.0F, 1.0F, 0.0F);
////                GL11.glRotatef(-l_vec2[1] + this.head_p, 1.0F, 0.0F, 0.0F);
////                y = -90.0F;
//                break;
//            }
//            default:
//            {
//                break;
//            }
//        }
//        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
//        GlStateManager.translate(0.0F, -1.501F, 0.0F);
//        this.skinningentities.getEntityData().setFloat("y_nali", -l_vec2[0]);
//        this.skinningentities.getEntityData().setFloat("p_nali", -l_vec2[1]);
//        Y = -l_vec2[0] + 90.0F;
//        P = -l_vec2[1];
        float render_yaw_offset = this.skinningentities.renderYawOffset;
        this.skinningentities.renderYawOffset = 0.0F;
        float[] c_mat4 = skinningentitiesrender.getMat43DSkinning(this.skinningentities, i, cv);
        float[] mat4 = new float[]
        {
            c_mat4[0], c_mat4[4], c_mat4[8], 0,
            c_mat4[1], c_mat4[5], c_mat4[9], 0,
            c_mat4[2], c_mat4[6], c_mat4[10], 0,
            0, 0, 0, 1.0F
        };
//        float[] mat4 = new float[]
//        {
//            c_mat4[0], c_mat4[1], c_mat4[2], 0,
//            c_mat4[4], c_mat4[5], c_mat4[6], 0,
//            c_mat4[8], c_mat4[9], c_mat4[10], 0,
//            0, 0, 0, 1.0F
//        };
        OpenGLCurrentMemory.setFloatBuffer(mat4);
        GL11.glMultMatrix(OpenGLCurrentMemory.OPENGL_FLOATBUFFER);
//        GL11.glRotatef(-ly_vec2[0] * 2.0F + lp_vec2[0], 0.0F, 1.0F, 0.0F);
//        GL11.glRotatef(ly_vec2[1] * 2.0F - lp_vec2[1], 1.0F, 0.0F, 0.0F);

//        GL11.glRotatef(-l_vec2[0], 0.0F, 1.0F, 0.0F);
//        GL11.glRotatef(-l_vec2[1], 1.0F, 0.0F, 0.0F);

        Item item = itemstack.getItem();
        if (item instanceof ItemArmor)
        {
//            GL11.glScalef(-1.0F, -1.0F, -1.0F);
//            GL11.glTranslatef(0.0F, 0.4F, 0.0F);
//            GL11.glScalef(-0.0625F, -0.0625F, 0.0625F);
//            GL11.glScalef(1.0F, -1.0F, -1.0F);
            switch (entityequipmentslot.getIndex())
            {
//                case 0:
//                {
//                    GL11.glTranslatef(0.0F, -1.00F, -0.075F);
//                    break;
//                }
//                case 1:
//                {
//                    GL11.glTranslatef(0.0F, -0.8F, -0.075F);
//                    break;
//                }
                case 2:
                {
//                    Small.LOGGER.info("YA " + ly_vec2[0]);
//                    Small.LOGGER.info("YB " + ly_vec2[1]);
//                    Small.LOGGER.info("PA " + lp_vec2[0]);
//                    Small.LOGGER.info("PB " + lp_vec2[1]);
//                    GL11.glTranslatef(0.0F, -13F, -2.5F);
                    break;
                }
//                case 3:
//                {
////                    GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
//                    GL11.glTranslatef(0.0F, -10F, 0.0F);
//                    break;
//                }
                default:
                {
                    break;
                }
            }

            //mixin armor use nbt to rotate
//            Minecraft.getMinecraft().getItemRenderer().renderItemSide(this.skinningentities, SmallBox.I.getDefaultInstance(), ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, false);
            GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
            GL11.glTranslatef(0.0F, -1.0F, 0.0F);
            GL11.glScalef(0.08F, 0.08F, 0.08F);
//            ARMOR = true;
            ((IMixinLayerArmorBase)this.layerbipedarmor).GOrenderArmorLayer(this.skinningentities, this.skinningentities.limbSwing, this.skinningentities.limbSwingAmount, partialTicks, handleRotationFloat(this.skinningentities, partialTicks), 0.0F, 0.0F, 1.0F, entityequipmentslot);
//            ARMOR = false;
        }
        else if (itemstack.getItem() == Items.ELYTRA)
        {
//            GL11.glTranslatef(0.0F, 0.0F, -0.1F);
            GL11.glScalef(0.0225F, 0.0225F, 0.0225F);
//            GL11.glScalef(1.0F, -1.0F, -1.0F);
            this.layerelytra.doRenderLayer(this.skinningentities, this.skinningentities.limbSwing, this.skinningentities.limbSwingAmount, partialTicks, handleRotationFloat(this.skinningentities, partialTicks), 0.0F, 0.0F, 1.0F);
        }
        else if (item == Items.SKULL)
        {
            GameProfile gameprofile = null;

            if (itemstack.hasTagCompound())
            {
                NBTTagCompound nbttagcompound = itemstack.getTagCompound();

                if (nbttagcompound.hasKey("SkullOwner", 10))
                {
                    gameprofile = NBTUtil.readGameProfileFromNBT(nbttagcompound.getCompoundTag("SkullOwner"));
                }
                else if (nbttagcompound.hasKey("SkullOwner", 8))
                {
                    String s = nbttagcompound.getString("SkullOwner");

                    if (!StringUtils.isBlank(s))
                    {
                        gameprofile = TileEntitySkull.updateGameProfile(new GameProfile(null, s));
                        nbttagcompound.setTag("SkullOwner", NBTUtil.writeGameProfile(new NBTTagCompound(), gameprofile));
                    }
                }
            }

            GL11.glScalef(0.25F, 0.25F, 0.25F);
            TileEntitySkullRenderer.instance.renderSkull(-0.5F, 0.0F, -0.5F, EnumFacing.UP, 180.0F, itemstack.getMetadata(), gameprofile, -1, this.skinningentities.limbSwing);
        }
        else
        {
            GL11.glScalef(0.25F, 0.25F, 0.25F);
            Minecraft.getMinecraft().getItemRenderer().renderItem(this.skinningentities, itemstack, ItemCameraTransforms.TransformType.HEAD);
        }

        GL11.glPopMatrix();

        //
        if (!itemstack.isEmpty())
        {
            GL11.glPushMatrix();
            GL11.glScalef(0.0F, 0.0F, 0.0F);
            Minecraft.getMinecraft().getItemRenderer().renderItemSide(this.skinningentities, SmallBox.I.getDefaultInstance(), ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, false);
            GL11.glPopMatrix();

            GL11.glPushMatrix();
//            GL11.glPushAttrib(GL11.GL_COLOR_BUFFER_BIT);
            GL11.glColor4f(1.0F, 0.0F, 0.0F, 1.0F);
            skinningentitiesrender.apply3DSkinningVec4(ty_vec4);
            GL11.glScalef(0.05F, 0.05F, 0.05F);
            Minecraft.getMinecraft().getItemRenderer().renderItemSide(this.skinningentities, SmallBox.I.getDefaultInstance(), ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, false);
            GL11.glColor4f(GL_CURRENT_COLOR[0], GL_CURRENT_COLOR[1], GL_CURRENT_COLOR[2], GL_CURRENT_COLOR[3]);
//            GL11.glPopAttrib();
            GL11.glPopMatrix();

            GL11.glPushMatrix();
//            GL11.glPushAttrib(GL11.GL_COLOR_BUFFER_BIT);
            GL11.glColor4f(0.0F, 1.0F, 0.0F, 1.0F);
            skinningentitiesrender.apply3DSkinningVec4(tp_vec4);
            GL11.glScalef(0.05F, 0.05F, 0.05F);
            Minecraft.getMinecraft().getItemRenderer().renderItemSide(this.skinningentities, SmallBox.I.getDefaultInstance(), ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, false);
            GL11.glColor4f(GL_CURRENT_COLOR[0], GL_CURRENT_COLOR[1], GL_CURRENT_COLOR[2], GL_CURRENT_COLOR[3]);
//            GL11.glPopAttrib();
            GL11.glPopMatrix();
        }

        this.skinningentities.renderYawOffset = render_yaw_offset;

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