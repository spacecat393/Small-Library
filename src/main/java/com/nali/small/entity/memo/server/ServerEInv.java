package com.nali.small.entity.memo.server;

import com.nali.data.IBothDaE;
import com.nali.small.entity.EntityEInv;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.Inventory;
import com.nali.small.entity.memo.IBothEInv;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.sound.ISoundN;

public abstract class ServerEInv<SD extends ISoundN, BD extends IBothDaE, E extends EntityEInv, I extends IMixE<SD, BD, E>, A extends MixAIE<SD, BD, E, I, ?>> extends ServerE<SD, BD, E, I, A> implements IBothEInv<SD, BD, E, I>
{
    public Inventory inventory;

    public ServerEInv(I i, A a, Inventory inventory)
    {
        super(i, a);
        this.inventory = inventory;
    }

    @Override
    public Inventory getInventory()
    {
        return this.inventory;
    }
}
