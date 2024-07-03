package com.nali.small.entity.memo.server;

import com.nali.data.IBothDaNe;
import com.nali.small.entity.EntityEInv;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.Inventory;
import com.nali.small.entity.memo.IBothEInv;
import com.nali.small.entity.memo.server.ai.MixAIE;

public abstract class ServerEInv<SD, BD extends IBothDaNe, E extends EntityEInv, I extends IMixE<SD, BD, E>, A extends MixAIE<SD, BD, E, I, ?>> extends ServerE<SD, BD, E, I, A> implements IBothEInv<SD, BD, E, I>
{
    public Inventory inventory;

    public ServerEInv(I i, Inventory inventory)
    {
        super(i);
        this.inventory = inventory;
    }

    @Override
    public Inventory getInventory()
    {
        return this.inventory;
    }
}
