package com.nali.small.mixin;

import com.nali.Nali;
import com.nali.list.gui.da.server.SDaInvSelect;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class MixinMinecraftServer
{
	@Inject(method = "tick", at = @At(value = "HEAD"))
	private void tick(CallbackInfo ci)
	{
		for (int i = 0; i < SDaInvSelect.RUNNABLE_LIST.size(); ++i)
		{
			try
			{
				SDaInvSelect.RUNNABLE_LIST.get(i).run();
			}
			catch (Exception e)
			{
				Nali.warn(e);
			}
		}
		SDaInvSelect.RUNNABLE_LIST.clear();
	}
}
