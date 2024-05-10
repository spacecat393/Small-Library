package com.nali.small.mixin;

import com.nali.small.entities.skinning.SkinningEntities;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.server.CommandSummon;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(CommandSummon.class)
public abstract class MixinCommandSummon
{
    private boolean should_execute;

    @Inject(method = "execute", at = @At(value = "INVOKE", target = "Lnet/minecraft/nbt/JsonToNBT;getTagFromJson(Ljava/lang/String;)Lnet/minecraft/nbt/NBTTagCompound;", shift = At.Shift.AFTER))
    @Mutable
    private void nali_small_afterGetTagExecute(MinecraftServer server, ICommandSender sender, String[] args, CallbackInfo ci)
    {
        this.should_execute = true;
    }

    @Inject(method = "execute", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;setLocationAndAngles(DDDFF)V", shift = At.Shift.AFTER, ordinal = 0), locals = LocalCapture.CAPTURE_FAILSOFT)
    @Mutable
    private void nali_small_execute(MinecraftServer server, ICommandSender sender, String[] args, CallbackInfo ci, Entity entity)
    {
        if (this.should_execute && (entity instanceof SkinningEntities/* || entity instanceof ObjectEntities*/))
        {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound = entity.writeToNBT(nbttagcompound);
            entity.readFromNBT(nbttagcompound);
            this.should_execute = false;
        }
    }
}
