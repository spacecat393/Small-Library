//package com.nali.small.mixin;
//
//import com.nali.Nali;
//import com.nali.small.blocks.MixBlocks;
//import net.minecraft.block.Block;
//import net.minecraft.client.renderer.ChunkRenderContainer;
//import net.minecraft.client.renderer.chunk.RenderChunk;
//import net.minecraft.util.BlockRenderLayer;
//import org.lwjgl.opengl.GL11;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Mutable;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//@Mixin(ChunkRenderContainer.class)
//public abstract class MixinChunkRenderContainer
//{
//    @Inject(method = "addRenderChunk", at = @At(value = "HEAD"))
//    private void addRenderChunk(RenderChunk renderChunkIn, BlockRenderLayer layer, CallbackInfo callbackinfo)
//    {
//        //need to find block in chunk
//        Block block = renderChunkIn.getWorld().getBlockState(renderChunkIn.getPosition()).getBlock();
//        Nali.LOGGER.info("Ya! " + block);
//        if (block instanceof MixBlocks)
//        {
//            Nali.LOGGER.info("Ya!");
//            GL11.glPushMatrix();
////            GL11.glTranslatef(0.5F, 0.5F, 0.5F);
//            MixBlocks mixblocks = (MixBlocks)block;
////            ObjectRender objectrender = mixblocks.getObjectRender();
//            mixblocks.render();
////            objectrender.draw();
////            objectrender.lig_b = -1.0F;
//            GL11.glPopMatrix();
//        }
//    }
//}
