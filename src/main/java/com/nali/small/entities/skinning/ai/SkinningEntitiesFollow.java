package com.nali.small.entities.skinning.ai;

import com.nali.small.entities.memory.server.ServerEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.entities.skinning.ai.path.SkinningEntitiesFindMove;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;

import static com.nali.small.entities.EntitiesMath.*;
import static com.nali.small.entities.skinning.ai.path.PathMath.PATH_BYTE_ARRAY;

public class SkinningEntitiesFollow extends SkinningEntitiesAI
{
    public float max_distance = 196.0F;
    public float min_distance = 96.0F;
    public boolean follow;

    public SkinningEntitiesFollow(SkinningEntities skinningentities)
    {
        super(skinningentities);
    }

    @Override
    public void onUpdate()
    {
        ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.skinningentities.bothentitiesmemory;
        Entity owner_entity = this.skinningentities.getOwner();

        if (owner_entity instanceof EntityPlayerMP)
        {
            if (((EntityPlayerMP)owner_entity).isSpectator())
            {
                return;
            }
        }

        if (owner_entity != null &&
            this.skinningentities.isWork(serverentitiesmemory.workbytes.FOLLOW()) &&
            (serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.far == 0 || serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.blockpos == null || isInArea(owner_entity, serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.blockpos, serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.far)) &&
            (this.skinningentities.getDistanceSq(owner_entity) > this.min_distance || this.follow))
        {
            if ((owner_entity.world).provider.getDimension() != ((this.skinningentities.world).provider.getDimension()))
            {
                if (this.follow)
                {
                    serverentitiesmemory.entitiesaimemory.skinningentitiesfindmove.endGoal();
                    this.follow = false;
                }

                serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.FOLLOW()] = 0;
                return;
            }

            this.follow = true;
            if (this.skinningentities.isRiding())
            {
                this.skinningentities.dismountRidingEntity();
            }

            double step = this.skinningentities.getDistanceSq(owner_entity);

            if (step >= this.max_distance)
            {
                this.tryTeleport(owner_entity);
            }
            else
            {
                if (step <= getClose(this.skinningentities, owner_entity, 1.0D))
                {
                    serverentitiesmemory.entitiesaimemory.skinningentitiesfindmove.endGoal();
                    this.follow = false;
                }
                else
                {
                    serverentitiesmemory.entitiesaimemory.skinningentitiesfindmove.setGoal(owner_entity.posX, owner_entity.posY, owner_entity.posZ);
                }
            }
        }
        else if (this.follow)
        {
            serverentitiesmemory.entitiesaimemory.skinningentitiesfindmove.endGoal();
            this.follow = false;
        }

        if (!this.follow)
        {
            serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.FOLLOW()] = 0;
        }
    }

    public void tryTeleport(Entity owner_entity)
    {
        BlockPos blockpos = owner_entity.getPosition();

        for (byte xi : PATH_BYTE_ARRAY)
        {
            for (byte yi : PATH_BYTE_ARRAY)
            {
                for (byte zi : PATH_BYTE_ARRAY)
                {
                    if (!(xi == 0 && yi == 0 && zi == 0))
                    {
                        if (!this.tryTeleportTo(owner_entity, blockpos.getX() + xi, blockpos.getY() + yi, blockpos.getZ() + zi))
                        {
                            continue;
                        }

                        return;
                    }
                }
            }
        }
    }

    public boolean tryTeleportTo(Entity owner_entity, int x, int y, int z)
    {
        ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.skinningentities.bothentitiesmemory;
        BlockPos to_blockpos = new BlockPos(x, y, z);
        BlockPos down_blockpos = to_blockpos.down();

        if (SkinningEntitiesFindMove.fallBlock(this.skinningentities.getMaterial(down_blockpos)) || SkinningEntitiesFindMove.isBlock(this.skinningentities.getMaterial(to_blockpos)))
        {
            return false;
        }

        this.skinningentities.setPositionAndRotation(x + 0.5D, y, z + 0.5D, owner_entity.rotationYaw, owner_entity.rotationPitch);
        serverentitiesmemory.current_work_byte_array[3] = 0;
        serverentitiesmemory.entitiesaimemory.skinningentitiesfindmove.endGoal();

        return true;
    }
}
