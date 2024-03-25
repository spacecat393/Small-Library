package com.nali.small.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.world.WorldServer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;
import java.util.UUID;

@Mixin(WorldServer.class)
public interface IMixinWorldServer
{
    @Accessor("entitiesByUuid")
    @Final
    @Mutable
    Map<UUID, Entity> entitiesByUuid();
}