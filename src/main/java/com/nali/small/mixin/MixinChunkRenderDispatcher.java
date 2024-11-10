//package com.nali.small.mixin;
//
//import com.nali.Nali;
//import com.nali.small.mix.memo.client.ClientN;
//import net.minecraft.client.renderer.chunk.ChunkRenderDispatcher;
//import net.minecraft.client.renderer.chunk.RenderChunk;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
//
//@Mixin(ChunkRenderDispatcher.class)
//public abstract class MixinChunkRenderDispatcher
//{
//	@Inject(method = "updateChunkNow", at = @At(value = "HEAD"))
//	private void nali_small_updateChunkNow(RenderChunk chunkRenderer, CallbackInfoReturnable<Boolean> cir)
//	{
//		Nali.warn("n_size " + ClientN.N_LIST.size());
//		for (ClientN n : ClientN.N_LIST)
//		{
//			Nali.warn("i " + n + " clear " + n.face_byte);
//			n.face_byte = 0;
//		}
//	}
//}
