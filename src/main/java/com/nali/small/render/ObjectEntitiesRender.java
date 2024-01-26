//package com.nali.small.render;
//
//import com.nali.data.BothData;
//import com.nali.render.ObjectRender;
//import com.nali.system.DataLoader;
//import net.minecraft.entity.Entity;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.World;
//
//public class ObjectEntitiesRender extends ObjectRender
//{
//    public Entity entity;
//
//    public ObjectEntitiesRender(BothData bothdata, DataLoader dataloader, Entity entity)
//    {
//        super(bothdata, dataloader);
//        this.entity = entity;
//    }
//
//    @Override
//    public void updateLightCoord()
//    {
//        super.updateLightCoord();
//
////        int brightness = this.entity.getBrightnessForRender();
//        int brightness = 0;
//        World world = this.entity.world;
//        BlockPos blockpos = this.entity.getPosition();
//
//        if (world.isBlockLoaded(blockpos))
//        {
//            brightness = world.getCombinedLight(blockpos, 0);
//        }
//
//        this.lig_b = brightness & 0xFFFF;
//        this.lig_s = (brightness >> 16) & 0xFFFF;
//    }
//}
