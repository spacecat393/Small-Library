package com.nali.small.mixin;

import com.nali.list.capabilitiesserializations.SmallSakuraSerializations;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayer.class)
public abstract class MixinEntityPlayer extends EntityLivingBase
{
    private static final DataParameter<Integer> SAKURA_INTEGER_DATAPARAMETER = EntityDataManager.<Integer>createKey(MixinEntityPlayer.class, DataSerializers.VARINT);

    public MixinEntityPlayer(World worldIn)
    {
        super(worldIn);
    }

    @Inject(method = "entityInit", at = @At("HEAD"))
    @Mutable
    private void mixinEntityInit(CallbackInfo ci)
    {
        this.dataManager.register(SAKURA_INTEGER_DATAPARAMETER, 0);
    }

    @Inject(method = "onUpdate", at = @At("HEAD"))
    @Mutable
    private void mixinOnUpdate(CallbackInfo ci)
    {
        if (!this.world.isRemote)
        {
            this.dataManager.set(SAKURA_INTEGER_DATAPARAMETER, this.getCapability(SmallSakuraSerializations.SMALLSAKURATYPES_CAPABILITY, null).get());
        }
        else
        {
            this.getEntityData().setInteger("sakura_nali", this.dataManager.get(SAKURA_INTEGER_DATAPARAMETER));
        }
    }
}
