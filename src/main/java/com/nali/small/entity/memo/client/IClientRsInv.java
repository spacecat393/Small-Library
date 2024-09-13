package com.nali.small.entity.memo.client;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public interface IClientRsInv
{
	int[] getIVIntArray();
	float[] getRotationFloatArray();
	float[] getTransformFloatArray();
}