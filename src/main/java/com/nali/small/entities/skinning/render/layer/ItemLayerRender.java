package com.nali.small.entities.skinning.render.layer;

import com.mojang.authlib.GameProfile;
import com.nali.list.items.SmallBox;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.entities.skinning.render.SkinningEntitiesRender;
import com.nali.small.mixin.IMixinLayerArmorBase;
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
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.StringUtils;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nullable;

import static com.nali.small.entities.skinning.render.SkinningEntitiesRenderMath.handleRotationFloat;
import static com.nali.small.entities.skinning.render.SkinningEntitiesRenderMath.lookAt;
import static com.nali.system.opengl.memory.OpenGLCurrentMemory.GL_CULL_FACE;

@SideOnly(Side.CLIENT)
public class ItemLayerRender extends LayerRender
{
    public LayerBipedArmor layerbipedarmor;
    public LayerElytra layerelytra;
    public int c_left_hand_i, c_left_hand_v, t_left_hand_i, t_left_hand_v,
            c_right_hand_i, c_right_hand_v, t_right_hand_i, t_right_hand_v,
            c_head_i, c_head_v, t_head_i, t_head_v,
            c_chest_i, c_chest_v, t_chest_i, t_chest_v,
            c_legs_i, c_legs_v, t_legs_i, t_legs_v,
            c_feet_i, c_feet_v, t_feet_i, t_feet_v;

    public float hand_y, hand_p,
            head_y, head_p,
            chest_y, chest_p,
            legs_y, legs_p,
            feet_y, feet_p;

    public static int DEBUG_I, DEBUG_V;

    public ItemLayerRender(SkinningEntities skinningentities)
    {
        super(skinningentities);
    }

    public void layer(SkinningEntitiesRender skinningentitiesrender, float partialTicks)
    {
//        this.c_legs_i = DEBUG_I;
//        this.t_feet_v = DEBUG_V;
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
        GL_CULL_FACE = GL11.glIsEnabled(GL11.GL_CULL_FACE);
        GL11.glDisable(GL11.GL_CULL_FACE);

        Vec3d c_vec3d = skinningentitiesrender.doModel(this.skinningentities, c_i, c_v);
        Vec3d t_vec3d = skinningentitiesrender.doModel(this.skinningentities, t_i, t_v);
        Vec3d l_vec3d = lookAt(c_vec3d, t_vec3d);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)c_vec3d.x, (float)c_vec3d.y, (float)c_vec3d.z);

        //
        if (!itemstack.isEmpty())
        {
            GL11.glPushMatrix();
            GL11.glScalef(0.05F, 0.05F, 0.05F);
            Minecraft.getMinecraft().getItemRenderer().renderItemSide(this.skinningentities, SmallBox.I.getDefaultInstance(), ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, false);
            GL11.glPopMatrix();
        }

        switch (entityequipmentslot.getIndex())
        {
            case 0:
            {
                GL11.glRotatef((float)-l_vec3d.x + this.feet_y, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef((float)-l_vec3d.y + this.feet_p, 1.0F, 0.0F, 0.0F);
                break;
            }
            case 1:
            {
                GL11.glRotatef((float)-l_vec3d.x + this.legs_y, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef((float)-l_vec3d.y + this.legs_p, 1.0F, 0.0F, 0.0F);
                break;
            }
            case 2:
            {
                GL11.glRotatef((float)-l_vec3d.x + this.chest_y, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef((float)-l_vec3d.y + this.chest_p, 1.0F, 0.0F, 0.0F);
                break;
            }
            case 3:
            {
                GL11.glRotatef((float)-l_vec3d.x + this.head_y, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef((float)-l_vec3d.y + this.head_p, 1.0F, 0.0F, 0.0F);
                break;
            }
            default:
            {
                break;
            }
        }

        Item item = itemstack.getItem();
        if (item instanceof ItemArmor)
        {
            switch (entityequipmentslot.getIndex())
            {
                case 0:
                {
                    GL11.glTranslatef(0.0F, 1.00F, -0.075F);
                    break;
                }
                case 1:
                {
                    GL11.glTranslatef(0.0F, 0.8F, -0.075F);
                    break;
                }
                case 2:
                {
                    GL11.glTranslatef(0.0F, 0.725F, -0.075F);
                    break;
                }
                case 3:
                {
                    GL11.glTranslatef(0.0F, 0.4F, 0.0F);
                    break;
                }
                default:
                {
                    break;
                }
            }

            GL11.glScalef(0.05F, 0.05F, 0.05F);
            GL11.glScalef(1.0F, -1.0F, -1.0F);

            ((IMixinLayerArmorBase)this.layerbipedarmor).GOrenderArmorLayer(this.skinningentities, this.skinningentities.limbSwing, this.skinningentities.limbSwingAmount, partialTicks, handleRotationFloat(this.skinningentities, partialTicks), 0.0F, 0.0F, 1.0F, entityequipmentslot);
        }
        else if (itemstack.getItem() == Items.ELYTRA)
        {
//            GL11.glTranslatef(0.0F, 0.0F, -0.1F);
            GL11.glScalef(0.0225F, 0.0225F, 0.0225F);
            GL11.glScalef(1.0F, -1.0F, -1.0F);
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
            GL11.glTranslatef((float)t_vec3d.x, (float)t_vec3d.y, (float)t_vec3d.z);
            GL11.glScalef(0.01F, 0.01F, 0.01F);
            Minecraft.getMinecraft().getItemRenderer().renderItemSide(this.skinningentities, SmallBox.I.getDefaultInstance(), ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, false);
            GL11.glPopMatrix();
        }

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
