package com.nali.small.entities.skinning.ai;

import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.entities.skinning.ai.path.SkinningEntitiesFindMove;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;

import static com.nali.small.entities.EntitiesMathHelper.getClose;
import static com.nali.small.entities.skinning.ai.path.SkinningEntitiesFindMove.PATH_BYTE_ARRAY;

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
        Entity owner_entity = this.skinningentities.getOwner();

        if (owner_entity instanceof EntityPlayerMP)
        {
            if (((EntityPlayerMP)owner_entity).isSpectator())
            {
                return;
            }
        }

        if (owner_entity != null && this.skinningentities.isWork(this.skinningentities.skinningentitiesbytes.FOLLOW()) && (this.skinningentities.getDistanceSq(owner_entity) > this.min_distance || this.follow))
        {
            if ((owner_entity.getEntityWorld()).provider.getDimension() != ((this.skinningentities.getEntityWorld()).provider.getDimension()))
            {
                if (this.follow)
                {
                    this.skinningentities.skinningentitiesfindmove.endGoal();
                    this.follow = false;
                }

                this.skinningentities.server_work_byte_array[this.skinningentities.skinningentitiesbytes.FOLLOW()] = 0;
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
                    this.skinningentities.skinningentitiesfindmove.endGoal();
                    this.follow = false;
                }
                else
                {
                    this.skinningentities.skinningentitiesfindmove.setGoal(owner_entity.posX, owner_entity.posY, owner_entity.posZ);
                }
            }
        }
        else if (this.follow)
        {
            this.skinningentities.skinningentitiesfindmove.endGoal();
            this.follow = false;
        }

        if (!this.follow)
        {
            this.skinningentities.server_work_byte_array[this.skinningentities.skinningentitiesbytes.FOLLOW()] = 0;
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
        BlockPos to_blockpos = new BlockPos(x, y, z);
        BlockPos down_blockpos = to_blockpos.down();

        if (SkinningEntitiesFindMove.fallBlock(this.skinningentities.getMaterial(down_blockpos)) || SkinningEntitiesFindMove.isBlock(this.skinningentities.getMaterial(to_blockpos)))
        {
            return false;
        }

        this.skinningentities.setPositionAndRotation(x + 0.5D, y, z + 0.5D, owner_entity.rotationYaw, owner_entity.rotationPitch);
        this.skinningentities.server_work_byte_array[3] = 0;
        this.skinningentities.skinningentitiesfindmove.endGoal();

        return true;
    }
}
