package com.nali.small.entities.skinning.ai;

import com.nali.small.entities.memory.server.ServerEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import net.minecraft.entity.Entity;
import net.minecraft.world.WorldServer;

import static com.nali.small.entities.EntitiesMath.isInArea;
import static com.nali.small.entities.EntitiesMath.isTooClose;

public class SkinningEntitiesPlayWith extends SkinningEntitiesAI
{
    public Class clasz;
    public int tick;
    public boolean should_play;
    public boolean first_playwith;
    public SkinningEntities playwith_skinningentities;

    public SkinningEntitiesPlayWith(SkinningEntities skinningentities)
    {
        super(skinningentities);
    }

    @Override
    public void onUpdate()
    {
        ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.skinningentities.bothentitiesmemory;
        boolean play = false;
        if (serverentitiesmemory.isWork(serverentitiesmemory.workbytes.PLAY()))
        {
            boolean result = this.tick <= 0;

            if (this.playwith_skinningentities != null && ((WorldServer)this.skinningentities.world).getEntityFromUuid(this.playwith_skinningentities.getUniqueID()) == null)
            {
                this.playwith_skinningentities = null;
                this.should_play = false;
                this.first_playwith = false;
                this.tick = 1200 + this.skinningentities.getRNG().nextInt(5000);
            }

            if (result)
            {
                if (this.playwith_skinningentities != null)
                {
                    ((ServerEntitiesMemory)this.playwith_skinningentities.bothentitiesmemory).skinningentities = null;
                    this.playwith_skinningentities = null;
                    this.should_play = false;
                    this.first_playwith = false;
                    this.tick = 1200 + this.skinningentities.getRNG().nextInt(5000);
                }
                else if (this.skinningentities.ticksExisted % 200 == 0)
                {
                    SkinningEntities playwith_skinningentities = null;
                    for (Entity entity : serverentitiesmemory.entitiesaimemory.skinningentitiesarea.out_entity_list)
                    {
                        if (entity.getClass() == this.clasz)
                        {
                            playwith_skinningentities = (SkinningEntities)entity;
                            if (((ServerEntitiesMemory)playwith_skinningentities.bothentitiesmemory).skinningentities == null &&
                                this.skinningentities.getDataManager().get(this.skinningentities.getFloatDataParameterArray()[0]) <= playwith_skinningentities.getDataManager().get(playwith_skinningentities.getFloatDataParameterArray()[0]) &&
                                (serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.far == 0 || serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.blockpos == null || isInArea(playwith_skinningentities, serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.blockpos, serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.far)))
                            {
                                break;
                            }
                            else
                            {
                                playwith_skinningentities = null;
                            }
                        }
                    }

                    if (playwith_skinningentities == null)
                    {
                        this.tick = 1200;
                    }
                    else
                    {
                        this.playwith_skinningentities = playwith_skinningentities;
                        this.tick = 1200 + this.skinningentities.getRNG().nextInt(5000);
                    }
                }
            }
            else
            {
                if (this.playwith_skinningentities != null && (serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.far == 0 || serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.blockpos == null || isInArea(this.playwith_skinningentities, serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.blockpos, serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.far)))
                {
                    play = true;
                    if (!this.should_play)
                    {
                        if (isTooClose(this.skinningentities, this.playwith_skinningentities, 0.0F))
                        {
                            if (((ServerEntitiesMemory)this.playwith_skinningentities.bothentitiesmemory).skinningentities == null)
                            {
                                ((ServerEntitiesMemory)this.playwith_skinningentities.bothentitiesmemory).skinningentities = this.skinningentities;
                                this.should_play = true;
                                this.first_playwith = true;
                            }
                            else
                            {
                                this.playwith_skinningentities = null;
                                this.tick = 0;
                            }
                        }
                        else
                        {
                            serverentitiesmemory.entitiesaimemory.skinningentitiesfindmove.setGoal(this.playwith_skinningentities.posX, this.playwith_skinningentities.posY, this.playwith_skinningentities.posZ);
                        }
                    }
                    else
                    {
                        this.skinningentities.onShouldPlayWith();
                    }
                }

                --this.tick;
            }
        }
        else
        {
            if (this.playwith_skinningentities != null)
            {
                ((ServerEntitiesMemory)this.playwith_skinningentities.bothentitiesmemory).skinningentities = null;
                this.playwith_skinningentities = null;
            }

            this.should_play = false;
            this.first_playwith = false;
        }

        if (!play)
        {
            serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.PLAY() / 8] &= (byte)(255 - Math.pow(2, serverentitiesmemory.workbytes.PLAY() % 8));//0
        }
    }
}
