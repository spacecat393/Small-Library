package com.nali.small.mixin;

import com.nali.small.Small;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import zone.rong.mixinbooter.IEarlyMixinLoader;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MixinLoader implements IEarlyMixinLoader, IFMLLoadingPlugin
{
	@Override
	public List<String> getMixinConfigs()
	{
		return Collections.singletonList("mixins." + Small.ID + ".json");
	}

	@Override
	public String[] getASMTransformerClass()
	{
		return null;
	}

	@Override
	public String getModContainerClass()
	{
		return null;
	}

	@Nullable
	@Override
	public String getSetupClass()
	{
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data)
	{

	}

	@Override
	public String getAccessTransformerClass()
	{
		return null;
	}
}
