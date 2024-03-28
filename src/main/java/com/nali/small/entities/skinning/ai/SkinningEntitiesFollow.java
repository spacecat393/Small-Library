package com.nali.small.entities.skinning.ai;

import com.nali.small.entities.memory.server.ServerEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.entities.skinning.ai.path.SkinningEntitiesFindMove;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;

import static com.nali.small.entities.EntitiesMath.getClose;
import static com.nali.small.entities.EntitiesMath.isInArea;
import static com.nali.small.entities.skinning.ai.path.PathMath.PATH_BYTE_ARRAY;

public class SkinningEntitiesFollow extends SkinningEntitiesAI
{
    public byte state = 1 + 2;//remote1 walk_to2
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
        Entity owner_entity = serverentitiesmemory.getOwner();

        if (owner_entity instanceof EntityPlayerMP)
        {
            if (((EntityPlayerMP)owner_entity).isSpectator())
            {
                serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.FOLLOW() / 8] &= (byte)(255 - Math.pow(2, serverentitiesmemory.workbytes.FOLLOW() % 8));//0
                return;
            }
        }

        if (owner_entity != null &&
            serverentitiesmemory.isWork(serverentitiesmemory.workbytes.FOLLOW()) &&
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

                serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.FOLLOW() / 8] &= (byte)(255 - Math.pow(2, serverentitiesmemory.workbytes.FOLLOW() % 8));//0
                return;
            }

            if ((serverentitiesmemory.main_work_byte_array[serverentitiesmemory.workbytes.MINE() / 8] >> serverentitiesmemory.workbytes.MINE() % 8 & 1) == 1 && serverentitiesmemory.entitiesaimemory.skinningentitiesmine.blockpos != null)
            {
////                serverentitiesmemory.entitiesaimemory.skinningentitiesmine.walk();
//                serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.FOLLOW() / 8] &= (byte)(255 - Math.pow(2, serverentitiesmemory.workbytes.FOLLOW() % 8));//0
//                //disable
//                serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.ATTACK() / 8] &= (byte)(255 - Math.pow(2, serverentitiesmemory.workbytes.ATTACK() % 8));
//                serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.MANAGE_ITEM() / 8] &= (byte)(255 - Math.pow(2, serverentitiesmemory.workbytes.MANAGE_ITEM() % 8));
//                serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.GET_ITEM() / 8] &= (byte)(255 - Math.pow(2, serverentitiesmemory.workbytes.ATTACK() % 8));
                serverentitiesmemory.entitiesaimemory.skinningentitiesmine.breakWork();
            }

            this.follow = true;
            if (this.skinningentities.isRiding())
            {
                this.skinningentities.dismountRidingEntity();
            }

            double step = this.skinningentities.getDistanceSq(owner_entity);

            if ((this.state & 1) == 1 && step >= this.max_distance)
            {
                this.tryTeleport(owner_entity);
            }
            else if ((this.state & 2) == 2)
            {
                if (step <= getClose(this.skinningentities, owner_entity, 1.0D))
                {
                    serverentitiesmemory.entitiesaimemory.skinningentitiesfindmove.endGoal();
                    this.follow = false;
                }
                else
                {
                    serverentitiesmemory.entitiesaimemory.skinningentitiesfindmove.setBreakGoal(owner_entity.posX, owner_entity.posY, owner_entity.posZ);
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
            serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.FOLLOW() / 8] &= (byte)(255 - Math.pow(2, serverentitiesmemory.workbytes.FOLLOW() % 8));//0
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
        serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.FOLLOW() / 8] &= (byte)(255 - Math.pow(2, serverentitiesmemory.workbytes.FOLLOW() % 8));//0
        serverentitiesmemory.entitiesaimemory.skinningentitiesfindmove.endGoal();

        return true;
    }
}
