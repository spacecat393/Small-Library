package com.nali.ilol.entities.skinning.ai;

import com.nali.ilol.entities.skinning.SkinningEntities;
import com.nali.math.MixMath;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;

public class SkinningEntitiesAttack extends SkinningEntitiesAI
{
    public boolean attack = false;
    public double minimum_distance = 3.0D;
    public double minimum_away_distance = 2.0D;

    public SkinningEntitiesAttack(SkinningEntities skinningentities)
    {
        super(skinningentities);
    }

    @Override
    public void onUpdate()
    {
        if (this.skinningentities.isWork(this.skinningentities.skinningentitiesbytes.ATTACK()) && !this.skinningentities.skinningentitiesarea.all_entity_arraylist.isEmpty())
        {
            this.attack = true;
            boolean attack = false;

            for (Entity entity : this.skinningentities.skinningentitiesarea.all_entity_arraylist)
            {
                if (this.skinningentities.canEntityBeSeen(entity) && MixMath.isTooClose(this.skinningentities, entity, this.minimum_distance))
                {
                    attack = true;

                    this.skinningentities.skinningentitieslook.set(entity.posX, entity.posY, entity.posZ, 90.0F);
                    this.skinningentities.swingArm(EnumHand.MAIN_HAND);
                    this.skinningentities.attackEntityAsMob(entity);

                    if (MixMath.isTooClose(this.skinningentities, entity, this.minimum_away_distance))
                    {
                        Vec3d a_pos = this.skinningentities.getPositionVector();
                        Vec3d direction = a_pos.subtract(entity.getPositionVector()).normalize();
                        Vec3d targetPos = a_pos.add(direction);
                        this.skinningentities.skinningentitiesfindmove.setGoal(targetPos.x, targetPos.y, targetPos.z);
                    }
                    else
                    {
                        this.skinningentities.skinningentitiesfindmove.endGoal();
                    }
                }
                else
                {
                    this.skinningentities.skinningentitiesfindmove.setGoal(entity.posX, entity.posY, entity.posZ);
                }
            }

            if (attack && this.skinningentities.server_work_byte_array[this.skinningentities.skinningentitiesbytes.ON_ATTACK()] != 1)
            {
                this.skinningentities.getDataManager().set(this.skinningentities.getByteDataParameterArray()[this.skinningentities.skinningentitiesbytes.ON_ATTACK()], (byte)1);
            }
        }
        else
        {
            if (this.attack)
            {
                this.skinningentities.skinningentitiesfindmove.endGoal();
                this.attack = false;
            }

            this.skinningentities.server_work_byte_array[this.skinningentities.skinningentitiesbytes.ATTACK()] = 0;
        }
    }
}
