package com.nali.small.entities.skinning.render.layer;

import com.mojang.authlib.GameProfile;
import com.nali.render.SkinningRender;
import com.nali.small.entities.memory.ClientEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.entities.skinning.render.RenderLivingBaseObject;
import com.nali.small.entities.skinning.render.SkinningEntitiesRender;
import com.nali.small.mixin.IMixinLayerArmorBase;
import com.nali.system.opengl.memory.OpenGLCurrentMemory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerElytra;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.StringUtils;
import org.lwjgl.opengl.GL11;

import static com.nali.system.opengl.memory.OpenGLCurrentMemory.GL_CULL_FACE;

@SideOnly(Side.CLIENT)
public class ItemLayerRender extends LayerRender
{
    public static RenderLivingBaseObject RENDERLIVINGBASEOBJECT = new RenderLivingBaseObject();
    public static LayerBipedArmor LAYERBIPEDARMOR = new LayerBipedArmor(RENDERLIVINGBASEOBJECT);
    public static LayerElytra LAYERELYTRA = new LayerElytra(RENDERLIVINGBASEOBJECT);

    public int[] iv_int_array;
    public float[] rotation_float_array;
    public float[] transform_float_array;

//    public int left_hand_i, left_hand_v,
//            right_hand_i, right_hand_v,
//            head_i, head_v,
//            chest_i, chest_v,
//            legs_i, legs_v,
//            feet_i, feet_v,
//            mouth_i, mouth_v;

//    public float left_hand_y, left_hand_p,
//            right_hand_y, right_hand_p,
//            head_y, head_p,
//            chest_y, chest_p,
//            legs_y, legs_p,
//            feet_y, feet_p;

    public float x, y, z;
    public byte index;

    public ItemLayerRender(SkinningEntities skinningentities)
    {
        super(skinningentities);
    }

    public void layer(SkinningEntitiesRender skinningentitiesrender, float partialTicks)
    {
        ClientEntitiesMemory cliententitiesmemory = (ClientEntitiesMemory)this.skinningentities.bothentitiesmemory;
//        cliententitiesmemory.objectrender.takeDefault((OpenGLSkinningMemory)cliententitiesmemory.objectrender.memory_object_array[1]);
//        cliententitiesmemory.objectrender.setDefault((OpenGLSkinningMemory)cliententitiesmemory.objectrender.memory_object_array[1]);
//        OpenGLSkinningMemory openglskinningmemory = (OpenGLSkinningMemory)((ClientEntitiesMemory)this.skinningentities.bothentitiesmemory).objectrender.memory_object_array[14];
//        for (int v = 0; v < openglskinningmemory.index_int_array.length; ++v)
//        {
//            int vi = openglskinningmemory.index_int_array[v] * 3;
//            float x = openglskinningmemory.vertices_float_array[vi];
//            float y = openglskinningmemory.vertices_float_array[vi + 1];
//            float z = openglskinningmemory.vertices_float_array[vi + 2];
//
//            Vec3d vec3d_a = new Vec3d(0.0F, -0.096065F, 0.637554F);
//
//            if (vec3d_a.squareDistanceTo(x, y, z) < 0.0001F)
//            {
//                Small.LOGGER.info("V " + v);
//            }
//        }

        this.index = 0;
        this.renderHeldItem(skinningentitiesrender, this.skinningentities.getHeldItemMainhand(), ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, this.iv_int_array[2], this.iv_int_array[3]);
//        cliententitiesmemory.objectrender.setDefault((OpenGLSkinningMemory)cliententitiesmemory.objectrender.memory_object_array[1]);
        this.renderHeldItem(skinningentitiesrender, this.skinningentities.getHeldItemOffhand(), ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND, this.iv_int_array[0], this.iv_int_array[1]);
//        cliententitiesmemory.objectrender.setDefault((OpenGLSkinningMemory)cliententitiesmemory.objectrender.memory_object_array[1]);
//        this.renderHeldItem(skinningentitiesrender, this.skinningentities.bothentitiesmemory.skinninginventory.armor_itemstack_nonnulllist.get(4), ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, this.iv_int_array[12], this.iv_int_array[13]);

        this.renderArmor(skinningentitiesrender, EntityEquipmentSlot.HEAD, this.iv_int_array[4], this.iv_int_array[5], partialTicks);
//        cliententitiesmemory.objectrender.setDefault((OpenGLSkinningMemory)cliententitiesmemory.objectrender.memory_object_array[1]);
        this.renderArmor(skinningentitiesrender, EntityEquipmentSlot.CHEST, this.iv_int_array[6], this.iv_int_array[7], partialTicks);
//        cliententitiesmemory.objectrender.setDefault((OpenGLSkinningMemory)cliententitiesmemory.objectrender.memory_object_array[1]);
        this.renderArmor(skinningentitiesrender, EntityEquipmentSlot.LEGS, this.iv_int_array[8], this.iv_int_array[9], partialTicks);
//        cliententitiesmemory.objectrender.setDefault((OpenGLSkinningMemory)cliententitiesmemory.objectrender.memory_object_array[1]);
        this.renderArmor(skinningentitiesrender, EntityEquipmentSlot.FEET, this.iv_int_array[8], this.iv_int_array[9], partialTicks);
//        cliententitiesmemory.objectrender.setDefault((OpenGLSkinningMemory)cliententitiesmemory.objectrender.memory_object_array[1]);
        this.renderHeldItem(skinningentitiesrender, cliententitiesmemory.mouth_itemstack, ItemCameraTransforms.TransformType.GROUND, this.iv_int_array[10], this.iv_int_array[11]);
//        cliententitiesmemory.objectrender.setDefault((OpenGLSkinningMemory)cliententitiesmemory.objectrender.memory_object_array[1]);
//        this.renderArmor(skinningentitiesrender, EntityEquipmentSlot.FEET, this.iv_int_array[10], this.iv_int_array[11], partialTicks);
    }

    public void renderHeldItem(SkinningEntitiesRender skinningentitiesrender, ItemStack itemstack, ItemCameraTransforms.TransformType transformtype, int i, int v)
    {
        if (!itemstack.isEmpty())
        {
            ClientEntitiesMemory cliententitiesmemory = (ClientEntitiesMemory)this.skinningentities.bothentitiesmemory;
            SkinningRender skinningrender = (SkinningRender)cliententitiesmemory.objectrender;
            if (((cliententitiesmemory.sync_byte_array[0] >> this.index) & 1) == 0)
            {
                float[] c_vec4 = skinningentitiesrender.get3DSkinning(skinningrender, this.x, this.y, this.z, 0, 0, 0, i, v);
                GL11.glPushMatrix();
                skinningentitiesrender.apply3DSkinningVec4(c_vec4);

                float[] c_mat4 = skinningentitiesrender.getMat43DSkinning(skinningrender, i, v);
                float[] mat4 = new float[]
                {
                    c_mat4[0], c_mat4[4], c_mat4[8], 0,
                    c_mat4[1], c_mat4[5], c_mat4[9], 0,
                    c_mat4[2], c_mat4[6], c_mat4[10], 0,
                    0, 0, 0, 1.0F
                };
                OpenGLCurrentMemory.setFloatBuffer(mat4);
                GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                GL11.glMultMatrix(OpenGLCurrentMemory.OPENGL_FLOATBUFFER);

                boolean left_hand = transformtype == ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND;
                if (left_hand)
                {
                    GL11.glRotatef(this.rotation_float_array[0], 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(this.rotation_float_array[1], 1.0F, 0.0F, 0.0F);
                }
                else if (transformtype == ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND)
                {
                    GL11.glRotatef(this.rotation_float_array[2], 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(this.rotation_float_array[3], 1.0F, 0.0F, 0.0F);
                }
                else
                {
                    GL11.glTranslatef(0.0F, -0.1F * 0.5F, 0.01F * 0.5F);
    //                GL11.glRotatef(this.rotation_float_array[10], 0.0F, 1.0F, 0.0F);
    //                GL11.glRotatef(this.rotation_float_array[11], 1.0F, 0.0F, 0.0F);
                }

                GL11.glScalef(0.4F * 0.5F, 0.4F * 0.5F, 0.4F * 0.5F);
                Minecraft.getMinecraft().getItemRenderer().renderItemSide(this.skinningentities, itemstack, transformtype, left_hand);
                GL11.glPopMatrix();
            }
        }

        ++this.index;
    }

    public void renderArmor(SkinningEntitiesRender skinningentitiesrender, EntityEquipmentSlot entityequipmentslot, int i, int v, float partialTicks)
    {
        ClientEntitiesMemory cliententitiesmemory = (ClientEntitiesMemory)this.skinningentities.bothentitiesmemory;
        SkinningRender skinningrender = (SkinningRender)cliententitiesmemory.objectrender;
        if (((cliententitiesmemory.sync_byte_array[0] >> this.index) & 1) == 0)
        {
            ItemStack itemstack = this.skinningentities.getItemStackFromSlot(entityequipmentslot);
            GL_CULL_FACE = GL11.glIsEnabled(GL11.GL_CULL_FACE);
            GL11.glDisable(GL11.GL_CULL_FACE);

            float[] c_vec4 = skinningentitiesrender.get3DSkinning(skinningrender, this.x, this.y, this.z, 0, 0, 0, i, v);
            GL11.glPushMatrix();
            skinningentitiesrender.apply3DSkinningVec4(c_vec4);

            float[] c_mat4 = skinningentitiesrender.getMat43DSkinning(skinningrender, i, v);
            float[] mat4 = new float[]
            {
                c_mat4[0], c_mat4[4], c_mat4[8], 0,
                c_mat4[1], c_mat4[5], c_mat4[9], 0,
                c_mat4[2], c_mat4[6], c_mat4[10], 0,
                0, 0, 0, 1.0F
            };
            OpenGLCurrentMemory.setFloatBuffer(mat4);
            GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
            GL11.glMultMatrix(OpenGLCurrentMemory.OPENGL_FLOATBUFFER);

            Item item = itemstack.getItem();
            if (item instanceof ItemArmor)
            {
                this.setArmor(entityequipmentslot.getIndex());
                GL11.glScalef(0.08F * 0.5F, 0.08F * 0.5F, 0.08F * 0.5F);
                ((IMixinLayerArmorBase)LAYERBIPEDARMOR).GOrenderArmorLayer(this.skinningentities, this.skinningentities.limbSwing, this.skinningentities.limbSwingAmount, partialTicks, RENDERLIVINGBASEOBJECT.handleRotationFloat(this.skinningentities, partialTicks), 0.0F, 0.0F, 1.0F, entityequipmentslot);
            }
            else if (itemstack.getItem() == Items.ELYTRA)
            {
                this.setArmor(entityequipmentslot.getIndex());
                GL11.glTranslatef(0.0F, 1.0F * 0.5F, 0.0F);
                GL11.glScalef(0.035F * 0.5F, 0.035F * 0.5F, 0.035F * 0.5F);
                LAYERELYTRA.doRenderLayer(this.skinningentities, this.skinningentities.limbSwing, this.skinningentities.limbSwingAmount, partialTicks, RENDERLIVINGBASEOBJECT.handleRotationFloat(this.skinningentities, partialTicks), 0.0F, 0.0F, 1.0F);
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

                if (entityequipmentslot == EntityEquipmentSlot.HEAD)
                {
                    GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
                }
                else
                {
                    GL11.glTranslatef(this.transform_float_array[-(entityequipmentslot.getIndex() - 3) * 3], 0.25F, 0.0F);
                }

                GL11.glScalef(0.25F * 0.5F, 0.25F * 0.5F, 0.25F * 0.5F);
                TileEntitySkullRenderer.instance.renderSkull(-0.5F, 0.0F, -0.5F, EnumFacing.UP, 180.0F, itemstack.getMetadata(), gameprofile, -1, this.skinningentities.limbSwing);
            }
            else
            {
                GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
                if (item == Items.BONE)
                {
                    GL11.glTranslatef(0.0F, -0.36F * 0.5F, 0.275F * 0.5F);
                }

                if (item == Item.getItemFromBlock(Blocks.GLASS))
                {
                    GL11.glScalef(0.55F * 0.5F, 0.55F * 0.5F, 0.55F * 0.5F);
                    GL11.glTranslatef(0.0F, -0.4F * 0.5F, 0.0F);
                }
                else
                {
                    GL11.glScalef(0.25F * 0.5F, 0.25F * 0.5F, 0.25F * 0.5F);
                }
    //            if (entityequipmentslot != EntityEquipmentSlot.HEAD)
    //            {
    //                GL11.glTranslatef(0.0F, 0.0F, 0.1F);
    //            }
                GL11.glTranslatef(this.transform_float_array[-(entityequipmentslot.getIndex() - 3) * 3], 0.0F, 0.0F);

                Minecraft.getMinecraft().getItemRenderer().renderItem(this.skinningentities, itemstack, ItemCameraTransforms.TransformType.HEAD);
            }

            GL11.glPopMatrix();

            if (GL_CULL_FACE)
            {
                GL11.glEnable(GL11.GL_CULL_FACE);
            }
            else
            {
                GL11.glDisable(GL11.GL_CULL_FACE);
            }
        }

        ++this.index;
    }

    public void setArmor(int index)
    {
        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
        int i = -(index - 3) * 3;
        GL11.glTranslatef(this.transform_float_array[i], this.transform_float_array[i + 1], this.transform_float_array[i + 2]);
    }
}
