package com.nali.small.entity.memo;

import com.nali.small.entity.inv.InvE;

public interface IBothNInv<IE extends InvE>
{
	IE getIE();
}
