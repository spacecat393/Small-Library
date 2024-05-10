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
    private static final DataParameter<Byte> SAKURA_BYTE_DATAPARAMETER = EntityDataManager.<Byte>createKey(MixinEntityPlayer.class, DataSerializers.BYTE);

    public MixinEntityPlayer(World worldIn)
    {
        super(worldIn);
    }

    @Inject(method = "entityInit", at = @At("HEAD"))
    @Mutable
    private void nali_small_entityInit(CallbackInfo ci)
    {
        this.dataManager.register(SAKURA_BYTE_DATAPARAMETER, (byte)0);
    }

    @Inject(method = "onUpdate", at = @At("HEAD"))
    @Mutable
    private void nali_small_onUpdate(CallbackInfo ci)
    {
        if (!this.world.isRemote)
        {
            this.dataManager.set(SAKURA_BYTE_DATAPARAMETER, this.getCapability(SmallSakuraSerializations.SMALLSAKURATYPES_CAPABILITY, null).get());
        }
        else
        {
            this.getEntityData().setInteger("sakura_nali", this.dataManager.get(SAKURA_BYTE_DATAPARAMETER));
        }
    }
}
