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
					IMixE imixe = (IMixE)entity_list.get(index);
					ClientE cliente = (ClientE)imixe.getB();

					if (cliente.mb.isOn(entityplayersp, player_vec3d, look_vec3d))
					{
//						send to server
						Minecraft.getMinecraft().player.swingArm(EnumHand.MAIN_HAND);
					}
				}
			}
		}
	}
}
