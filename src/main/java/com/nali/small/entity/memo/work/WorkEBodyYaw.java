package com.nali.small.entity.memo.work;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

public class WorkEBodyYaw
{
	public int rotation_tick_counter;
	public float prev_render_yaw;

	public void run(EntityLivingBase e)
	{
		float f = 75.0F;

		if (Math.abs(e.rotationYaw - this.prev_render_yaw) > 15.0F)
		{
			this.rotation_tick_counter = 0;
			this.prev_render_yaw = e.rotationYaw;
		}
		else
		{
			++this.rotation_tick_counter;
			int i = 10;

			if (this.rotation_tick_counter > i)
			{
				f = Math.max(1.0F - (float)(this.rotation_tick_counter - i) / 10.0F, 0.0F) * 75.0F;
			}
		}

		e.renderYawOffset = this.computeAngleWithBound(e.rotationYaw, e.renderYawOffset, f);
	}

	public float computeAngleWithBound(float f0, float f1, float f2)
	{
		float f = MathHelper.wrapDegrees(f0 - f1);

		if (f < -f2)
		{
			f = -f2;
		}

		if (f >= f2)
		{
			f = f2;
		}

		return f0 - f;
	}
}
