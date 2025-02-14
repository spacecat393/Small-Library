package com.nali.small.mixin;

import com.nali.small.chunk.ChunkLoader;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.si.MixSIE;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldServer.class)
public abstract class MixinWorldServer
{
	@Inject(method = "tick", at = @At("HEAD"))
	public void nali_small_tick(CallbackInfo ci)
	{
		for (ServerE s : ServerE.S_MAP.values())
		{
			if ((s.ms.flag & MixSIE.B_LOAD_CHUNK) == MixSIE.B_LOAD_CHUNK)
			{
				Chunk chunk = s.worldserver.getChunk(s.i.getE().getPosition());
				if (s.chunk != chunk)
				{
					ChunkLoader.updateChunk(s);
					s.chunk = chunk;
				}
			}
		}
	}
}
