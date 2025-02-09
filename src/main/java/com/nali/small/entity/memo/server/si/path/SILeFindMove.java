package com.nali.small.entity.memo.server.si.path;

import com.nali.da.IBothDaE;
import com.nali.list.entity.si.SIEFindMove;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;
import net.minecraft.init.MobEffects;

public class SILeFindMove
<
	BD extends IBothDaE,
	E extends EntityLe,
	I extends IMixE<BD, E>,
	S extends ServerLe<BD, E, I, MS>,
	MS extends MixSIE<BD, E, I, S>
> extends SIEFindMove<BD, E, I, S, MS>
{
	public SILeFindMove(S s)
	{
		super(s);
	}

	@Override
	public void updateMove(E e)
	{
		if (!e.isOnLadder())
		{
			this.move_y = -0.5F;
		}
		if (e.isPotionActive(MobEffects.LEVITATION))
		{
			e.motionY += (0.05D * (double)(e.getActivePotionEffect(MobEffects.LEVITATION).getAmplifier() + 1) - e.motionY) * 0.2D;
		}
	}

	@Override
	public void look(E e)
	{
		super.look(e);
		e.rotation_yaw_head = e.rotationYaw;
	}
}
