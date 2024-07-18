package com.nali.small.entity.memo.server;

import com.nali.da.IBothDaNe;
import com.nali.list.entity.ai.AILeLockDMG;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.IBothLe;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.small.entity.memo.work.WorkEBodyYaw;
import com.nali.sound.ISoundDaLe;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;

public abstract class ServerLe<SD extends ISoundDaLe, BD extends IBothDaNe, E extends EntityLivingBase, I extends IMixLe<SD, BD, E>, A extends MixAIE<SD, BD, E, I, ?>> extends ServerE<SD, BD, E, I, A> implements IBothLe<SD, BD, E, I>
{
    public WorkEBodyYaw workebodyyaw;
    public byte serverle_state;//attackEntityAsMob attackEntityFrom attackEntityAsMob_trigger attackEntityFrom_trigger

    public Entity attack_entity_as_mob_entity;
    public DamageSource attack_entity_from_damagesource;
//    public AIEOwner<SD, BD, E, I, ?, A> aieowner;
//    public AILeLockDMG<SD, BD, E, I, ?, A> ailelockdmg;

    public ServerLe(I i)
    {
        super(i);
        this.workebodyyaw = new WorkEBodyYaw(this);
//        this.aieowner = (AIEOwner<SD, BD, E, I, ?, A>)this.a.aie_map.get(AIEOwner.ID);
//        this.ailelockdmg = (AILeLockDMG<SD, BD, E, I, ?, A>)this.a.aie_map.get(AILeLockDMG.ID);
    }

    @Override
    public boolean attackEntityFrom(DamageSource damagesource, float amount)
    {
        this.attack_entity_from_damagesource = damagesource;
        this.serverle_state |= 8;
        this.a.call(AILeLockDMG.ID);
        return true;
    }

    @Override
    public boolean attackEntityAsMob(Entity entity)//both?
    {
        this.attack_entity_as_mob_entity = entity;
        this.serverle_state |= 4;
        this.a.call(AILeLockDMG.ID);
        return (this.serverle_state & 1) == 1;
    }

    @Override
    public WorkEBodyYaw getWorkEBodyYaw()
    {
        return this.workebodyyaw;
    }

    @Override
    public void getHurtSound(DamageSource damageSource)
    {
    }

    @Override
    public void getDeathSound()
    {
    }
}
