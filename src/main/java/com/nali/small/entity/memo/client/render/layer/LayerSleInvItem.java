package com.nali.small.entity.memo.client.render.layer;

import com.mojang.authlib.GameProfile;
import com.nali.da.IBothDaNe;
import com.nali.da.IBothDaSn;
import com.nali.da.client.IClientDaS;
import com.nali.render.RenderS;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.inv.InvLe;
import com.nali.small.entity.memo.IBothNInv;
import com.nali.small.entity.memo.client.ClientLe;
import com.nali.small.entity.memo.client.IClientInvE;
import com.nali.small.entity.memo.client.box.mix.MixBoxSleInv;
import com.nali.small.entity.memo.client.ci.MixCIE;
import com.nali.small.entity.memo.client.render.FRenderFle;
import com.nali.small.entity.memo.client.render.mix.MixRenderSe;
import com.nali.small.mixin.IMixinLayerArmorBase;
import com.nali.sound.ISoundDaLe;
import com.nali.system.opengl.memo.client.MemoC;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerElytra;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.entity.EntityLivingBase;
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

@SideOnly(Side.CLIENT)
public class LayerSleInvItem<IE extends InvLe, RC extends IClientDaS, R extends RenderS<BD, RC>, SD extends ISoundDaLe, BD extends IBothDaNe & IBothDaSn, E extends EntityLivingBase, I extends IMixE<SD, BD, E>, MC extends MixCIE<RC, R, SD, BD, E, I, MB, MR, C>, MR extends MixRenderSe<RC, R, SD, BD, E, I, MC, MB, C>, MB extends MixBoxSleInv<RC, R, SD, BD, E, I, MC, MR, C>, C extends ClientLe<RC, R, SD, BD, E, I, MC, MB, MR> & IClientInvE & IBothNInv<IE>> extends LayerE<RC, R, SD, BD, E, I, MC, MR, MB, C>
{
	public static FRenderFle FRENDERFLE = new FRenderFle();
	public static LayerBipedArmor LAYERBIPEDARMOR = new LayerBipedArmor(FRENDERFLE);
	public static LayerElytra LAYERELYTRA = new LayerElytra(FRENDERFLE);

//	public int[] iv_int_array;
//	public float[] rotation_float_array;
//	public float[] transform_float_array;

	public float x, y, z;
	public byte index;

	public LayerSleInvItem(C c)
	{
		super(c);
	}

	public void layer(float partialTicks)
	{
		E e = this.c.i.getE();
//		cliententitiesmemory.objectrender.takeDefault((OpenGLSkinningMemory)cliententitiesmemory.objectrender.memory_object_array[1]);
//		cliententitiesmemory.objectrender.setDefault((OpenGLSkinningMemory)cliententitiesmemory.objectrender.memory_object_array[1]);
//		OpenGLSkinningMemory openglskinningmemory = (OpenGLSkinningMemory)((ClientEntitiesMemory)e.bothentitiesmemory).objectrender.memory_object_array[14];
//		for (int v = 0; v < openglskinningmemory.index_int_array.length; ++v)
//		{
//			int vi = openglskinningmemory.index_int_array[v] * 3;
//			float x = openglskinningmemory.vertices_float_array[vi];
//			float y = openglskinningmemory.vertices_float_array[vi + 1];
//			float z = openglskinningmemory.vertices_float_array[vi + 2];
//
//			Vec3d vec3d_a = new Vec3d(0.0F, -0.096065F, 0.637554F);
//
//			if (vec3d_a.squareDistanceTo(x, y, z) < 0.0001F)
//			{
//				Small.LOGGER.info("V " + v);
//			}
//		}

		this.index = 0;
		int[] iv_int_array = this.c.getIVIntArray();
		this.renderHeldItem(e.getHeldItemMainhand(), ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, iv_int_array[2], iv_int_array[3]);
		this.renderHeldItem(e.getHeldItemOffhand(), ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND, iv_int_array[0], iv_int_array[1]);

		this.renderArmor(EntityEquipmentSlot.HEAD, iv_int_array[4], iv_int_array[5], partialTicks);
		this.renderArmor(EntityEquipmentSlot.CHEST, iv_int_array[6], iv_int_array[7], partialTicks);
		this.renderArmor(EntityEquipmentSlot.LEGS, iv_int_array[8], iv_int_array[9], partialTicks);
		this.renderArmor(EntityEquipmentSlot.FEET, iv_int_array[8], iv_int_array[9], partialTicks);
		this.renderHeldItem(this.c.getIE().mouth_itemstack, ItemCameraTransforms.TransformType.GROUND, iv_int_array[10], iv_int_array[11]);
	}

	public void renderHeldItem(ItemStack itemstack, ItemCameraTransforms.TransformType transformtype, int i, int v)
	{
		if (!itemstack.isEmpty())
		{
			if (((this.c.sync_byte_array[0] >> this.index) & 1) == 0)
			{
				R r = this.c.r;
				float[] c_vec4 = r.get3DSkinning(this.x, this.y, this.z, 0, 0, 0, i, v);
				GL11.glPushMatrix();
				r.apply3DSkinningVec4(c_vec4);

				float[] c_mat4 = r.getMat43DSkinning(i, v);
				float[] mat4 = new float[]
				{
					c_mat4[0], c_mat4[4], c_mat4[8], 0,
					c_mat4[1], c_mat4[5], c_mat4[9], 0,
					c_mat4[2], c_mat4[6], c_mat4[10], 0,
					0, 0, 0, 1.0F
				};
				MemoC.setFloatBuffer(mat4);
				GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
				GL11.glMultMatrix(MemoC.OPENGL_FLOATBUFFER);

				boolean left_hand = transformtype == ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND;
				if (left_hand)
				{
					float[] rotation_float_array = this.c.getRotationFloatArray();
					GL11.glRotatef(rotation_float_array[0], 0.0F, 1.0F, 0.0F);
					GL11.glRotatef(rotation_float_array[1], 1.0F, 0.0F, 0.0F);
				}
				else if (transformtype == ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND)
				{
					float[] rotation_float_array = this.c.getRotationFloatArray();
					GL11.glRotatef(rotation_float_array[2], 0.0F, 1.0F, 0.0F);
					GL11.glRotatef(rotation_float_array[3], 1.0F, 0.0F, 0.0F);
				}
				else
				{
					GL11.glTranslatef(0.0F, -0.1F * 0.5F, 0.01F * 0.5F);
	//				GL11.glRotatef(this.rotation_float_array[10], 0.0F, 1.0F, 0.0F);
	//				GL11.glRotatef(this.rotation_float_array[11], 1.0F, 0.0F, 0.0F);
				}

				GL11.glScalef(0.4F * 0.5F, 0.4F * 0.5F, 0.4F * 0.5F);
				Minecraft.getMinecraft().getItemRenderer().renderItemSide(this.c.i.getE(), itemstack, transformtype, left_hand);
				GL11.glPopMatrix();
			}
		}

		++this.index;
	}

	public void renderArmor(EntityEquipmentSlot entityequipmentslot, int i, int v, float partialTicks)
	{
		if (((this.c.sync_byte_array[0] >> this.index) & 1) == 0)
		{
			E e = this.c.i.getE();
			R r = this.c.r;

			ItemStack itemstack = e.getItemStackFromSlot(entityequipmentslot);
			boolean gl_cull_face = GL11.glIsEnabled(GL11.GL_CULL_FACE);
			GL11.glDisable(GL11.GL_CULL_FACE);

			float[] c_vec4 = r.get3DSkinning(this.x, this.y, this.z, 0, 0, 0, i, v);
			GL11.glPushMatrix();
			r.apply3DSkinningVec4(c_vec4);

			float[] c_mat4 = r.getMat43DSkinning(i, v);
			float[] mat4 = new float[]
			{
				c_mat4[0], c_mat4[4], c_mat4[8], 0,
				c_mat4[1], c_mat4[5], c_mat4[9], 0,
				c_mat4[2], c_mat4[6], c_mat4[10], 0,
				0, 0, 0, 1.0F
			};
			MemoC.setFloatBuffer(mat4);
			GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
			GL11.glMultMatrix(MemoC.OPENGL_FLOATBUFFER);

			Item item = itemstack.getItem();
			if (item instanceof ItemArmor)
			{
				this.setArmor(entityequipmentslot.getIndex());
				GL11.glScalef(0.08F * 0.5F, 0.08F * 0.5F, 0.08F * 0.5F);
				((IMixinLayerArmorBase)LAYERBIPEDARMOR).GOrenderArmorLayer(e, e.limbSwing, e.limbSwingAmount, partialTicks, FRENDERFLE.handleRotationFloat(e, partialTicks), 0.0F, 0.0F, 1.0F, entityequipmentslot);
			}
			else if (itemstack.getItem() == Items.ELYTRA)
			{
				this.setArmor(entityequipmentslot.getIndex());
				GL11.glTranslatef(0.0F, 1.0F * 0.5F, 0.0F);
				GL11.glScalef(0.035F * 0.5F, 0.035F * 0.5F, 0.035F * 0.5F);
				LAYERELYTRA.doRenderLayer(e, e.limbSwing, e.limbSwingAmount, partialTicks, FRENDERFLE.handleRotationFloat(e, partialTicks), 0.0F, 0.0F, 1.0F);
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
					GL11.glTranslatef(this.c.getTransformFloatArray()[-(entityequipmentslot.getIndex() - 3) * 3], 0.25F, 0.0F);
				}

				GL11.glScalef(0.25F * 0.5F, 0.25F * 0.5F, 0.25F * 0.5F);
				TileEntitySkullRenderer.instance.renderSkull(-0.5F, 0.0F, -0.5F, EnumFacing.UP, 180.0F, itemstack.getMetadata(), gameprofile, -1, e.limbSwing);
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
	//			if (entityequipmentslot != EntityEquipmentSlot.HEAD)
	//			{
	//				GL11.glTranslatef(0.0F, 0.0F, 0.1F);
	//			}
				GL11.glTranslatef(this.c.getTransformFloatArray()[-(entityequipmentslot.getIndex() - 3) * 3], 0.0F, 0.0F);

				Minecraft.getMinecraft().getItemRenderer().renderItem(e, itemstack, ItemCameraTransforms.TransformType.HEAD);
			}

			GL11.glPopMatrix();

			if (gl_cull_face)
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
		float[] transform_float_array = this.c.getTransformFloatArray();
		GL11.glTranslatef(transform_float_array[i], transform_float_array[i + 1], transform_float_array[i + 2]);
	}
}
