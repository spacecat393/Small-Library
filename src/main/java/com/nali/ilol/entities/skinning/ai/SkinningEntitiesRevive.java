package com.nali.ilol.entities.skinning.ai;

import com.nali.ilol.entities.skinning.SkinningEntities;
import com.nali.ilol.networks.NetworksRegistry;
import com.nali.list.messages.SkinningEntitiesClientMessage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

import static com.nali.ilol.entities.EntitiesMathHelper.isTooClose;

public class SkinningEntitiesRevive extends SkinningEntitiesAI
{
    public int tick = 0;
    public Entity entity;
    public boolean die;

    public SkinningEntitiesRevive(SkinningEntities skinningentities)
    {
        super(skinningentities);
    }

    @Override
    public void onUpdate()
    {
        if (this.skinningentities.isWork(this.skinningentities.skinningentitiesbytes.REVIVE()))
        {
            if (this.entity == null)
            {
                this.entity = this.skinningentities.getOwner();
                this.tick = 0;
            }

            if (!this.die && this.entity != null && !this.entity.isEntityAlive())
            {
//                this.entity.isDead = false;
                this.die = true;
            }

            if (this.die && !this.entity.isEntityAlive())
            {
                if ((this.entity.getEntityWorld()).provider.getDimension() != ((this.skinningentities.getEntityWorld()).provider.getDimension()))
                {
                    this.skinningentities.server_work_byte_array[this.skinningentities.skinningentitiesbytes.REVIVE()] = 0;
                    return;
                }
                else
                {
                    if (isTooClose(this.skinningentities, entity, 1.0D))
                    {
                        if (this.skinningentities.server_work_byte_array[this.skinningentities.skinningentitiesbytes.ON_REVIVE()] != 1)
                        {
                            this.skinningentities.main_server_work_byte_array[this.skinningentities.skinningentitiesbytes.ON_REVIVE()] = 1;
                        }

                        this.skinningentities.skinningentitieslook.set(this.entity.posX, this.entity.posY, this.entity.posZ, 90.0F);
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
                                entitylivingbase.setHealth(entitylivingbase.getMaxHealth());
                                entitylivingbase.deathTime = 0;

                                if (this.entity instanceof EntityPlayer)
                                {
                                    EntityPlayerMP entityplayermp = (EntityPlayerMP)this.entity;
    //                            entityplayermp.closeScreen();
                                    NetworksRegistry.I.sendTo(new SkinningEntitiesClientMessage(new byte[]{1}), entityplayermp);
                                }
                            }

                            this.entity = null;
                        }
                    }
                    else
                    {
                        this.skinningentities.skinningentitiesfindmove.setGoal(this.entity.posX, this.entity.posY, this.entity.posZ);
                    }
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
