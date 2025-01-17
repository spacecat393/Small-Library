package com.nali.small.mixin;

import com.nali.small.SmallAttribute;
import net.minecraft.entity.ai.attributes.BaseAttribute;
import net.minecraft.entity.ai.attributes.IAttribute;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//add id
@Mixin(BaseAttribute.class)
public abstract class MixinBaseAttribute
{
	@Inject(method = "<init>", at = @At(value = "TAIL"))
	private void nali_small_init(IAttribute parentIn, String unlocalizedNameIn, double defaultValueIn, CallbackInfo ci)
	{
		SmallAttribute.IATTRIBUTE_INDEX_MAP.put((BaseAttribute)(Object)this, SmallAttribute.IATTRIBUTE_MAP_INDEX);
		SmallAttribute.IATTRIBUTE_MAP.put(SmallAttribute.IATTRIBUTE_MAP_INDEX++, (BaseAttribute)(Object)this);
	}
}
