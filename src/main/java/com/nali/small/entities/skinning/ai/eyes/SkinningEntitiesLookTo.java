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
    public int tick;
    public int[] bypass_int_array = {this.skinningentities.bothentitiesmemory.workbytes.SIT(), this.skinningentities.bothentitiesmemory.workbytes.PROTECT()};

    public SkinningEntitiesLookTo(SkinningEntities skinningentities)
    {
        super(skinningentities);
    }

    @Override
    public void onUpdate()
    {
        ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.skinningentities.bothentitiesmemory;
        if (serverentitiesmemory.isWorkBypass(serverentitiesmemory.workbytes.LOOK_TO(), this.bypass_int_array))
        {
            this.far = Double.MAX_VALUE;

            Map<UUID, Entity> entity_map = ((IMixinWorldServer)this.skinningentities.world).entitiesByUuid();
            Set<UUID> keys_set = new HashSet<>(entity_map.keySet());

            Entity current_entity = this.entity;

            for (UUID id : keys_set)
            {
                Entity entity = entity_map.get(id);
                if (entity.equals(this.skinningentities))
                {
                    continue;
                }

                double far = this.skinningentities.getDistanceSq(entity);
                if (far < this.far)
                {
                    this.entity = entity;
                    this.far = far;
                    this.setTime(current_entity);
                }
            }

//            Entity owner_entity = serverentitiesmemory.getOwner();
//            if (owner_entity != null)
//            {
//                if (this.skinningentities.getDistanceSq(owner_entity) < this.far)
//                {
//                    this.entity = owner_entity;
//                    this.setTime(current_entity);
//                }
//            }

            if (--this.tick > 300)
            {
                if (this.blockpos != null)
                {
                    serverentitiesmemory.entitiesaimemory.skinningentitieslook.set(this.blockpos.getX(), this.blockpos.getY(), this.blockpos.getZ(), 5.0F);
                }
                else if (this.entity != null)
                {
                    serverentitiesmemory.entitiesaimemory.skinningentitieslook.set(this.entity.posX, this.entity.posY + this.entity.getEyeHeight(), this.entity.posZ, 5.0F);
                }
            }
            else
            {
                if (this.tick <= 0)
                {
                    this.entity = null;
                    this.blockpos = null;
                }

                serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.LOOK_TO()] = 0;
            }
        }
    }

    public void setTime(Entity current_entity)
    {
        if (this.tick <= 0 && current_entity != this.entity)
        {
            this.tick = this.entity.world.rand.nextInt(700) + 200;
        }
    }
}
