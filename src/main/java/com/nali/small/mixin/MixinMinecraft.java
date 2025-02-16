package com.nali.small.mixin;

import com.nali.small.entity.EntityMath;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.client.ClientE;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Minecraft.class)
public abstract class MixinMinecraft
{
//	@Shadow @Nullable public GuiScreen currentScreen;

	@Inject(method = "runGameLoop", at = @At("TAIL"))
	private void nali_small_runGameLoop(CallbackInfo ci)
	{
		Minecraft minecraft = Minecraft.getMinecraft();

		if (minecraft.gameSettings.keyBindUseItem.isKeyDown())
		{
			EntityPlayerSP entityplayersp = minecraft.player;

			if (entityplayersp != null)
			{
				List<Entity> entity_list = minecraft.world.loadedEntityList;

				Vec3d player_vec3d = entityplayersp.getPositionEyes(1.0F);
				Vec3d look_vec3d = entityplayersp.getLookVec();

				int index = -1;
				double max = Double.MAX_VALUE;
				for (int i = 0; i < entity_list.size(); ++i)
				{
					Entity entity = entity_list.get(i);
					if (!(entity instanceof IMixE))
					{
						continue;
					}

					if (EntityMath.ray(entity.getEntityBoundingBox(), player_vec3d, look_vec3d))
					{
						double new_max = EntityMath.getDistanceAABBToAABB(entityplayersp, entity);
						if (new_max < max)
						{
							index = i;
							max = new_max;
						}
					}
				}

				if (index != -1)
				{
					Entity e = entity_list.get(index);
					IMixE imixe = (IMixE)e;
					ClientE c = (ClientE)imixe.getB();

//					//s0-test
//					RenderS r = (RenderS)c.r;
//					IBothDaS s_bd = (IBothDaS)imixe.getBD();
//					IBothDaO o_bd = (IBothDaO)imixe.getBD();
//
//					GL11.glPointSize(10.0F);
//
////					float epsilon = 0.00001F;
//					MemoF2 f2 = BothLoader.F2_LIST.get(s_bd.S_FrameID());
//					for (int o = o_bd.O_StartPart(); o < o_bd.O_EndPart(); ++o)
//					{
//						MemoA2 ra2 = BothLoader.A2_MAP.get(o);
//
//						int[] index_int_array = ra2.index_int_array;
////						float[] vertex_float_array = ra2.vertex_float_array;
//
////						for (int i = 0; i < vertex_float_array.length; i += 3)
//						for (int i = 0; i < index_int_array.length; ++i)
//						{
////							float ix = vertex_float_array[i];
////							float iy = vertex_float_array[i + 1];
////							float iz = vertex_float_array[i + 2];
////
////							float ax = Math.abs(ix - 0.000026F);
////							float ay = Math.abs(iy - -0.101241F);
////							float az = Math.abs(iz - 0.671901F);
////							if
////							(
////								ax < epsilon &&
////								ay < epsilon &&
////								az < epsilon
////							)
////							{
////								Nali.warn("I " + i);
////								Nali.warn("X " + ax + "   " + ix);
////								Nali.warn("Y " + ay + "   " + iy);
////								Nali.warn("Z " + az + "   " + iz);
////							}
//
//							float[] pos_vec4 = f2.getScale3DSkinning(r.scale, r.skinning_float_array, (float)e.posX, (float)e.posY, (float)e.posZ, 0, 0, 0, o, i);
//
//							double x = pos_vec4[0] / pos_vec4[3];
//							double y = pos_vec4[1] / pos_vec4[3];
//							double z = pos_vec4[2] / pos_vec4[3];
//
//							GL11.glDisable(
//									GL11.glBegin(GL11.GL_POINTS);
//							GL11.glVertex3d(x, y, z);
//							GL11.glEnd();
//						}
//					}
//					//e0-test

//					if (c.mb.isOn(entityplayersp, player_vec3d, look_vec3d))
//					{
//						Minecraft.getMinecraft().player.swingArm(EnumHand.MAIN_HAND);
//					}
					c.mb.isOn(entityplayersp, player_vec3d, look_vec3d, true);
					Minecraft.getMinecraft().player.swingArm(EnumHand.MAIN_HAND);
				}
			}
		}
	}

//	@Redirect(method = "updateDisplay", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/Display;update()V"))
//	public void nali_small_updateDisplay()
//	{
//		if (this.currentScreen != null)
//		{
//			Display.update();
//		}
//	}
}
