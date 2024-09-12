package com.nali.small.entity.memo.client;

import com.nali.sound.Sound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public interface IClientSound
{
	Sound getSound();
}
