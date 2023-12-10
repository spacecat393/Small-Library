package com.nali.ilol.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.nali.ilol.entities.EntitiesRegistryHelper.ENTITIES_CLASS_LIST;

@Mixin(ItemMonsterPlacer.class)
public abstract class MixinItemMonsterPlacer
{
    @Inject(method = "spawnCreature", at = @At(value = "JUMP", target = "Lnet/minecraft/entity/EntityList;createEntityByIDFromName(Lnet/minecraft/util/ResourceLocation;Lnet/minecraft/world/World;)Lnet/minecraft/entity/Entity;", ordinal = 1))
    private static void spawnCreature(World worldIn, ResourceLocation entityID, double x, double y, double z, CallbackInfoReturnable<Entity> cir)
    {
        Entity entity = EntityList.createEntityByIDFromName(entityID, worldIn);

        for (Class clasz : ENTITIES_CLASS_LIST)
        {
            if (clasz.equals(entity.getClass()))
            {
                entity.setLocationAndAngles(x, y, z, MathHelper.wrapDegrees(worldIn.rand.nextFloat() * 360.0F), 0.0F);
                worldIn.spawnEntity(entity);
                break;
            }
        }
    }
}
