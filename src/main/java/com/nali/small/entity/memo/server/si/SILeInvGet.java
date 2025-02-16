package com.nali.small.entity.memo.server.si;

import com.nali.da.IBothDaE;
import com.nali.list.entity.si.SIEInvGet;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.inv.InvE;
import com.nali.small.entity.memo.IBothEInv;
import com.nali.small.entity.memo.server.ServerE;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;

public class SILeInvGet
<
	IE extends InvE,
	BD extends IBothDaE,
	E extends EntityLivingBase,
	I extends IMixE<BD, E>,
	S extends ServerE<BD, E, I, MS> & IBothEInv<IE>,
	MS extends MixSIE<BD, E, I, S>
> extends SIEInvGet<IE, BD, E, I, S, MS>
{
	public SILeInvGet(S s)
	{
		super(s);
	}

	@Override
	public ItemStack getXPItemStack(E e)
	{
		return EnchantmentHelper.getEnchantedItem(Enchantments.MENDING, e);
	}
}
