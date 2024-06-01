package com.nali.small.entities.skinning.ai;

import com.nali.small.entities.memory.server.ServerEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.entities.skinning.ai.path.SkinningEntitiesFindMove;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;

import static com.nali.small.entities.EntitiesMath.getDistanceAABBToAABB;
import static com.nali.small.entities.EntitiesMath.isInArea;
import static com.nali.small.entities.skinning.ai.path.PathMath.PATH_BYTE_ARRAY;

public class SkinningEntitiesFollow extends SkinningEntitiesAI
{
    public float max_distance = 196.0F;
    public float min_distance = 96.0F;
    public byte flag = 4;//move_to | tp_to walk_to

    public SkinningEntitiesFollow(SkinningEntities skinningentities)
    {
        super(skinningentities);
    }

    @Override
    public void onUpdate()
    {
        ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.skinningentities.bothentitiesmemory;
        Entity owner_entity = serverentitiesmemory.getOwner();

        if (owner_entity instanceof EntityPlayerMP)
        {
            if (((EntityPlayerMP)owner_entity).isSpectator())
            {
                serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.FOLLOW() / 8] &= (byte)(255 - Math.pow(2, serverentitiesmemory.workbytes.FOLLOW() % 8));
                return;
            }
        }

        boolean move_to = (this.flag & 1) == 1;
        if (owner_entity != null &&
            serverentitiesmemory.isWork(serverentitiesmemory.workbytes.FOLLOW()) &&
            (serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.far == 0 || serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.blockpos == null || isInArea(owner_entity, serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.blockpos, serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.far)) &&
//            (this.skinningentities.getDistanceSq(owner_entity) > this.min_distance || move_to))
            (getDistanceAABBToAABB(this.skinningentities, owner_entity) > this.min_distance || move_to))
        {
            if ((owner_entity.world).provider.getDimension() != ((this.skinningentities.world).provider.getDimension()))
            {
                if (move_to)
                {
                    serverentitiesmemory.entitiesaimemory.skinningentitiesfindmove.endGoal();
                    this.flag ^= 1;
                }

                serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.FOLLOW() / 8] &= (byte)(255 - Math.pow(2, serverentitiesmemory.workbytes.FOLLOW() % 8));
                return;
            }

            if ((serverentitiesmemory.main_work_byte_array[serverentitiesmemory.workbytes.MINE() / 8] >> serverentitiesmemory.workbytes.MINE() % 8 & 1) == 1 && serverentitiesmemory.entitiesaimemory.skinningentitiesmine.blockpos != null)
            {
                serverentitiesmemory.entitiesaimemory.skinningentitiesmine.breakWork();
            }

            this.flag |= 1;
            if (this.skinningentities.isRiding())
            {
                this.skinningentities.dismountRidingEntity();
            }

//            double step = this.skinningentities.getDistanceSq(owner_entity);

//            if ((this.flag & 2) == 2 && step >= this.max_distance)
//            if ((this.flag & 2) == 2 && this.skinningentities.getDistanceSq(owner_entity) >= this.max_distance)
            if ((this.flag & 2) == 2 && getDistanceAABBToAABB(this.skinningentities, owner_entity) >= this.max_distance)
            {
                this.tryTeleport(owner_entity);
            }
            else if ((this.flag & 4) == 4)
            {
//                if (step <= getClose(this.skinningentities, owner_entity, 1.0D))
                if (getDistanceAABBToAABB(this.skinningentities, owner_entity) <= 1.0D)
                {
                    serverentitiesmemory.entitiesaimemory.skinningentitiesfindmove.endGoal();
                    this.flag &= 255-1;
                }
                else
                {
                    serverentitiesmemory.entitiesaimemory.skinningentitiesfindmove.setBreakGoal(owner_entity.posX, owner_entity.posY, owner_entity.posZ);
                }
            }
        }
        else if (move_to)
        {
            serverentitiesmemory.entitiesaimemory.skinningentitiesfindmove.endGoal();
            this.flag ^= 1;
        }

        if ((this.flag & 1) == 0)
        {
            serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.FOLLOW() / 8] &= (byte)(255 - Math.pow(2, serverentitiesmemory.workbytes.FOLLOW() % 8));
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

        if (SkinningEntitiesFindMove.fallBlock(serverentitiesmemory.getMaterial(down_blockpos)) || SkinningEntitiesFindMove.isBlock(serverentitiesmemory.getMaterial(to_blockpos)))
        {
            return false;
        }

        this.skinningentities.setPositionAndRotation(x + 0.5D, y, z + 0.5D, owner_entity.rotationYaw, owner_entity.rotationPitch);
        serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.FOLLOW() / 8] &= (byte)(255 - Math.pow(2, serverentitiesmemory.workbytes.FOLLOW() % 8));
        serverentitiesmemory.entitiesaimemory.skinningentitiesfindmove.endGoal();

        return true;
    }
}
