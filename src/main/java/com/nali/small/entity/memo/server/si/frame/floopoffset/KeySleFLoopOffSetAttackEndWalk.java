package com.nali.small.entity.memo.server.si.frame.floopoffset;

import com.nali.da.IBothDaE;
import com.nali.da.IBothDaS;
import com.nali.da.IBothDaSe;
import com.nali.list.entity.si.SILeAttack;
import com.nali.list.entity.si.SIEFindMove;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;

public class KeySleFLoopOffSetAttackEndWalk
<
	BD extends IBothDaE & IBothDaS & IBothDaSe,
	E extends EntityLe,
	I extends IMixE<BD, E>,
	S extends ServerLe<BD, E, I, MS> & IServerS,
	MS extends MixSIE<BD, E, I, S>
> extends KeySFLoopOffSet<BD, E, I, S, MS>
{
	public SIEFindMove<BD, E, I, S, MS> siefindmove;
	public SILeAttack<BD, E, I, S, MS> sileattack;

	public KeySleFLoopOffSetAttackEndWalk(S s, byte key_data_index)
	{
		super(s, key_data_index);

		this.sileattack = (SILeAttack<BD, E, I, S, MS>)this.s.ms.si_map.get(SILeAttack.ID);
		this.siefindmove = (SIEFindMove<BD, E, I, S, MS>)this.s.ms.si_map.get(SIEFindMove.ID);
	}
//
//	@Override
//	public void init()
//	{
//		super.init();
//		this.sileattack = (SILeAttack<SD, BD, E, I, S, MS>)this.s.ms.si_map.get(SILeAttack.ID);
//	}

	@Override
	public boolean onUpdate()
	{
		return (this.sileattack.state & SILeAttack.B_ATTACK_POSE) == SILeAttack.B_ATTACK_POSE && this.siefindmove.move_x == 0 && this.siefindmove.move_z == 0 && super.onUpdate();
	}
}
