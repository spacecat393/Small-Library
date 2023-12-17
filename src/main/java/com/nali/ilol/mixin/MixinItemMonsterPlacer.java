package com.nali.ilol.mixin;

import com.nali.ilol.entities.object.ObjectEntities;
import com.nali.ilol.entities.skinning.SkinningEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemMonsterPlacer.class)
public abstract class MixinItemMonsterPlacer
{
    @Inject(method = "spawnCreature", at = @At(value = "JUMP", target = "Lnet/minecraft/entity/EntityList;createEntityByIDFromName(Lnet/minecraft/util/ResourceLocation;Lnet/minecraft/world/World;)Lnet/minecraft/entity/Entity;", ordinal = 1))
    private static void spawnCreature(World worldIn, ResourceLocation entityID, double x, double y, double z, CallbackInfoReturnable<Entity> cir)
    {
        Entity entity = EntityList.createEntityByIDFromName(entityID, worldIn);

        if (entity instanceof SkinningEntities || entity instanceof ObjectEntities)
        {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound = entity.writeToNBT(nbttagcompound);
            entity.readFromNBT(nbttagcompound);
            entity.setLocationAndAngles(x, y, z, MathHelper.wrapDegrees(worldIn.rand.nextFloat() * 360.0F), 0.0F);
            worldIn.spawnEntity(entity);
        }
    }
}
