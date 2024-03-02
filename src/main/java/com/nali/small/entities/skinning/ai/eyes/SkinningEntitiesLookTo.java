package com.nali.small.entities.skinning.ai.eyes;

import com.nali.small.entities.memory.server.ServerEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.entities.skinning.ai.SkinningEntitiesAI;
import com.nali.small.mixin.IMixinWorldServer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class SkinningEntitiesLookTo extends SkinningEntitiesAI
{
    public Entity entity;
    public double far;
    public BlockPos blockpos;

    public SkinningEntitiesLookTo(SkinningEntities skinningentities)
    {
        super(skinningentities);
    }

    @Override
    public void onUpdate()
    {
        ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.skinningentities.bothentitiesmemory;
        if (serverentitiesmemory.isWork(serverentitiesmemory.workbytes.LOOK_TO()))
        {
            this.far = Double.MAX_VALUE;

            Entity owner_entity = serverentitiesmemory.getOwner();
            Map<UUID, Entity> entity_map = ((IMixinWorldServer)this.skinningentities.world).entitiesByUuid();
            Set<UUID> keys_set = new HashSet<>(entity_map.keySet());

            for (UUID id : keys_set)
            {
                Entity entity = entity_map.get(id);
                if (this.entity == null)
                {
                    this.entity = entity;
                }
                else
                {
                    double far = this.entity.getDistanceSq(entity);
                    if (far < this.far)
                    {
                        this.entity = entity;
                        this.far = far;
                    }
                }
            }

            if (owner_entity != null)
            {
                if (this.entity == null)
                {
                    this.entity = owner_entity;
                }
                else
                {
                    if (this.entity.getDistanceSq(owner_entity) < this.far)
                    {
                        this.entity = owner_entity;
                    }
                }
            }

            if (this.blockpos != null)
            {
                serverentitiesmemory.entitiesaimemory.skinningentitieslook.set(this.blockpos.getX(), this.blockpos.getY(), this.blockpos.getZ(), 5.0F);
            }
            else if (this.entity != null)
            {
                serverentitiesmemory.entitiesaimemory.skinningentitieslook.set(this.entity.posX, this.entity.posY + this.entity.getEyeHeight(), this.entity.posZ, 5.0F);
            }
            else
            {
                serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.LOOK_TO()] = 0;
            }
        }
    }
}
