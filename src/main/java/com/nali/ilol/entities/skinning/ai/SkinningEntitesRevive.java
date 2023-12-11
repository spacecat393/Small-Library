package com.nali.ilol.entities.skinning.ai;

import com.nali.ilol.entities.skinning.SkinningEntities;
import com.nali.ilol.networks.NetworksRegistry;
import com.nali.list.messages.SkinningEntitiesClientMessage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class SkinningEntitesRevive
{
    public SkinningEntities skinningentities;
    public int tick = 0;
    public Entity entity;
    public boolean die;

    public SkinningEntitesRevive(SkinningEntities skinningentities)
    {
        this.skinningentities = skinningentities;
    }

    public void onUpdate()
    {
        if (this.skinningentities.isWork(this.skinningentities.skinningentitiesbytes.REVIVE()))
        {
            if (this.entity == null)
            {
                this.entity = this.skinningentities.getEntity(1);
                this.tick = 0;
            }

            if (!this.die && this.entity != null && !this.entity.isEntityAlive())
            {
//                this.entity.isDead = false;
                this.die = true;
            }

            if (this.die && !this.entity.isEntityAlive())
            {
                ++this.tick;
                if (this.tick == 100)
//                if (this.entity != null)
                {
                    this.tick = 0;
                    this.entity.isDead = false;
                    this.skinningentities.world.spawnEntity(this.entity);
                    if (this.entity instanceof EntityLivingBase)
                    {
                        EntityLivingBase entitylivingbase = (EntityLivingBase)this.entity;
                        entitylivingbase.setHealth(1.0F);
                        entitylivingbase.deathTime = 0;

                        if (this.entity instanceof EntityPlayer)
                        {
                            NetworksRegistry.I.sendTo(new SkinningEntitiesClientMessage(new byte[]{1}), ((EntityPlayerMP)this.entity));
                        }
                    }

                    this.entity = null;
                }
            }

            if (!this.die)
            {
                this.skinningentities.server_work_byte_array[this.skinningentities.skinningentitiesbytes.REVIVE()] = 0;
            }

            this.die = false;
        }
    }
}
