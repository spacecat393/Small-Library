//package com.nali.small.entity.memo.client.render;
//
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//
//@SideOnly(Side.CLIENT)
//public class FRenderSeMath
//{
//	public static float interpolateRotation(float old_yaw, float yaw, float partial_ticks)
//	{
//		float f = yaw - old_yaw;
//
//		while (f < -180.0F)
//		{
//			f += 360.0F;
//		}
//
//		while (f >= 180.0F)
//		{
//			f -= 360.0F;
//		}
//
//		return old_yaw + partial_ticks * f;
//	}
//
////	public static float handleRotationFloat(SkinningEntities skinningentities, float partialTicks)
////	{
////		return (float)skinningentities.ticksExisted + partialTicks;
////	}
//
////	public static float[] lookAt(float[] source, float[] target)
////	{
////		double d0 = target[0] - source[0];
////		double d1 = target[1] - source[1];
////		double d2 = target[2] - source[2];
////		double d3 = MathHelper.sqrt(d0 * d0 + d2 * d2);
////		float f = (float)(MathHelper.atan2(d2, d0) * (180.0D / Math.PI)) - 90.0F;
////		float f1 = (float)(-(MathHelper.atan2(d1, d3) * (180.0D / Math.PI)));
////		return new float[]{f, f1};
////	}
//}
