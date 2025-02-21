package com.nali.small.mixin;

import com.nali.NaliConfig;
import com.nali.render.RenderO;
import com.nali.small.Small;
import com.nali.small.SmallConfig;
import com.nali.small.draw.Draw;
import com.nali.small.entity.IMixE;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.util.ClassInheritanceMultiMap;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL20;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

//@Mixin(value = RenderGlobal.class, priority = Integer.MAX_VALUE)
@Mixin(RenderGlobal.class)
public abstract class MixinRenderGlobal
{
	@Shadow @Final private Minecraft mc;
	private static int LAST;
	private static ClassInheritanceMultiMap<Entity>[] ENTITY_CLASSINHERITANCEMULTIMAP_ARRAY;
	private static ClassInheritanceMultiMap<Entity>[] NEW_ENTITY_CLASSINHERITANCEMULTIMAP_ARRAY = new ClassInheritanceMultiMap[1];

	@Redirect(method = "renderEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/BlockPos;getY()I"))
	private int nali_small_renderEntities(BlockPos instance)
	{
		ClassInheritanceMultiMap<Entity> entity_classinheritancemultimap = ENTITY_CLASSINHERITANCEMULTIMAP_ARRAY[instance.getY() / 16];

		List<Entity> small_entity_list = new ArrayList();
		List<Entity> entity_list = new ArrayList();
		List<Entity> translucent_entity_list = new ArrayList();

		if (!entity_classinheritancemultimap.isEmpty())
		{
			for (Entity entity : entity_classinheritancemultimap)
			{
				if (entity instanceof IMixE)
				{
					small_entity_list.add(entity);
				}
				else if (entity.isInvisible() || entity instanceof EntitySlime/* || entity.isInvisibleToPlayer(Minecraft.getMinecraft().player)*/)
				{
					if (!NaliConfig.NEED_EXTRA || (SmallConfig.FAST_RAW_FPS && Small.FLAG == 0) || (!SmallConfig.FAST_RAW_FPS && (Small.FLAG & 1) == 1))
					{
						translucent_entity_list.add(entity);
					}
				}
				else
				{
					entity_list.add(entity);
				}
			}
		}

		ClassInheritanceMultiMap<Entity> new_entity_classinheritancemultimap = new ClassInheritanceMultiMap(Entity.class);
		//s-small
		for (Entity entity : small_entity_list)
		{
			new_entity_classinheritancemultimap.add(entity);
		}

		int size = small_entity_list.size();
		if (size > 0)
		{
			LAST = small_entity_list.get(size - 1).getEntityId();
		}
		else
		{
			LAST = -1;
		}
		//e-small

		for (Entity entity : entity_list)
		{
			new_entity_classinheritancemultimap.add(entity);
		}

		//s-sort
		Entity view_entity = this.mc.getRenderViewEntity();
		size = translucent_entity_list.size() - 1;
		for (int x = 0; x < size; ++x)
		{
			for (int y = 0; y < size; ++y)
			{
				int y1 = y + 1;
				Entity y_entity = translucent_entity_list.get(y);
				Entity y1_entity = translucent_entity_list.get(y1);
				if (view_entity.getDistanceSq(y_entity) < view_entity.getDistanceSq(y1_entity))
				{
					translucent_entity_list.set(y, y1_entity);
					translucent_entity_list.set(y1, y_entity);
				}
			}
		}
		//e-sort

		for (Entity entity : translucent_entity_list)
		{
			new_entity_classinheritancemultimap.add(entity);
		}

		NEW_ENTITY_CLASSINHERITANCEMULTIMAP_ARRAY[0] = new_entity_classinheritancemultimap;
		return 0;
	}

	@Redirect(method = "renderEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/chunk/Chunk;getEntityLists()[Lnet/minecraft/util/ClassInheritanceMultiMap;"))
	private ClassInheritanceMultiMap<Entity>[] nali_small_renderEntities(Chunk chunk)
	{
		ENTITY_CLASSINHERITANCEMULTIMAP_ARRAY = chunk.getEntityLists();
		return NEW_ENTITY_CLASSINHERITANCEMULTIMAP_ARRAY;
	}

	@Redirect(method = "renderEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/RenderManager;shouldRender(Lnet/minecraft/entity/Entity;Lnet/minecraft/client/renderer/culling/ICamera;DDD)Z"))
	private boolean nali_small_renderEntities(RenderManager renderManager, Entity entity2, ICamera camera, double camX, double camY, double camZ)
	{
		if (LAST != -3)
		{
			if (LAST == entity2.getEntityId())
			{
				LAST = -2;
			}
		}

		boolean render = renderManager.shouldRender(entity2, camera, camX, camY, camZ) || entity2.isRidingOrBeingRiddenBy(this.mc.player);
		if (!render)
		{
			render();
		}

		return render;
	}

//	//always true
//	@Redirect(method = "renderEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/WorldClient;isBlockLoaded(Lnet/minecraft/util/math/BlockPos;)Z"))
//	private boolean nali_small_renderEntities(WorldClient instance, BlockPos blockPos)
//	{
/// /		return instance.isBlockLoaded(blockPos);
//		return true;
//	}

	@Inject(method = "renderEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/RenderManager;renderEntityStatic(Lnet/minecraft/entity/Entity;FZ)V", ordinal = 1, shift = At.Shift.AFTER))
	private void nali_small_renderEntities(Entity renderViewEntity, ICamera camera, float partialTicks, CallbackInfo ci)
	{
		render();
	}

//	private static int PASS;
//
//	@Inject(method = "renderEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/BlockPos$PooledMutableBlockPos;release()V", shift = At.Shift.AFTER))
//	private void nali_renderWorldR(Entity renderViewEntity, ICamera camera, float partialTicks, CallbackInfo ci)
//	{
//		PASS = net.minecraftforge.client.MinecraftForgeClient.getRenderPass();
//
//		if (PASS == 0)
//		{
////			Nali.warn("0");
//			Draw.runE();
//		}
//	}
//
//	@Inject(method = "renderEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/RenderManager;setRenderOutlines(Z)V", shift = At.Shift.BEFORE, ordinal = 1))
//	private void nali_renderWorldO(Entity renderViewEntity, ICamera camera, float partialTicks, CallbackInfo ci)
//	{
//		if (PASS == 0)
//		{
////			Nali.warn("1");
//			Draw.runEG();
//		}
//	}
//
//	@Inject(method = "renderEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;postRenderDamagedBlocks()V", shift = At.Shift.BEFORE))
//	private void nali_renderWorldB(Entity renderViewEntity, ICamera camera, float partialTicks, CallbackInfo ci)
//	{
//		if (PASS == 0)
//		{
////			Nali.warn("2");
//			Draw.runT();
//			Draw.clear();
//		}
//	}

	private static void render()
	{
		if (LAST == -2)
		{
			RenderO.take();
			GL11.glEnable(GL11.GL_DEPTH_TEST);
			GL11.glEnable(GL11.GL_BLEND);
			GL20.glBlendEquationSeparate(GL14.GL_FUNC_ADD, GL14.GL_FUNC_ADD);
			GL14.glBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);

			if (!Draw.E_MODEL_MAP.isEmpty())
			{
				Draw.draw(Draw.E_MODEL_MAP);
			}

			if (!NaliConfig.NEED_EXTRA || (SmallConfig.FAST_RAW_FPS && Small.FLAG == 1) || (!SmallConfig.FAST_RAW_FPS && (Small.FLAG & 1) == 0))
			{
				if (!Draw.E_TRANSLUCENT_MAP.isEmpty())
				{
//					Draw.draw(Draw.E_TRANSLUCENT_MAP);
					Draw.drawT(Draw.E_TRANSLUCENT_MAP);
				}
			}

			RenderO.free();
			Draw.clear();
			LAST = -3;
		}
	}
}
