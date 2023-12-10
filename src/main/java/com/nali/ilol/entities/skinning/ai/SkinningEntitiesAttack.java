package com.nali.ilol.entities.skinning.ai;

import com.nali.ilol.entities.skinning.SkinningEntities;

public class SkinningEntitiesAttack
{
    public SkinningEntities skinningentities;
    public SkinningEntitiesMelee skinningentitiesmelee;
    public SkinningEntitiesRange skinningentitiesrange;

    public SkinningEntitiesAttack(SkinningEntities skinningentities)
    {
        this.skinningentities = skinningentities;
        this.skinningentitiesmelee = new SkinningEntitiesMelee(skinningentities);
        this.skinningentitiesrange = new SkinningEntitiesRange(skinningentities);
    }

    public void onUpdate()
    {
        if (this.skinningentities.isWork(this.skinningentities.skinningentitiesbytes.ATTACK()) && !this.skinningentities.skinningentitiesarea.all_entity_arraylist.isEmpty())
        {
            switch (this.skinningentities.server_work_byte_array[this.skinningentities.skinningentitiesbytes.ATTACK()])
            {
                case 1:
                {
                    this.skinningentitiesmelee.onUpdate();
                    break;
                }
                case 2:
                {
                    this.skinningentitiesrange.onUpdate();
                    break;
                }
                default:
                {
                    break;
                }
            }
        }
        else
        {
            this.skinningentities.server_work_byte_array[this.skinningentities.skinningentitiesbytes.ATTACK()] = 0;
        }
    }
}
