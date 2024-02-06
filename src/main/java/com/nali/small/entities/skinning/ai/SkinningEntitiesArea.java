package com.nali.small.entities.skinning.ai;

import com.nali.small.entities.EntitiesRegistryHelper;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.mixin.IMixinWorldServer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;

import java.util.*;

public class SkinningEntitiesArea extends SkinningEntitiesAI
{
//    public Entity[] target_entity_array = new Entity[2]; // xp item
    public ArrayList<EntityXPOrb> xp_entity_arraylist = new ArrayList<EntityXPOrb>();
    public ArrayList<EntityItem> item_entity_arraylist = new ArrayList<EntityItem>();
    public ArrayList<Entity> all_entity_arraylist = new ArrayList<Entity>(); // target
    public ArrayList<Entity> out_entity_arraylist = new ArrayList<Entity>(); // not_target
//    public double[] distance_to_target_array = new double[2];

    public ArrayList<Integer> troublemaker_arraylist = new ArrayList<>();
    public ArrayList<Integer> target_arraylist = new ArrayList<>();

    public SkinningEntitiesArea(SkinningEntities skinningentities)
    {
        super(skinningentities);
    }

    @Override
    public void onUpdate()
    {
//        Arrays.fill(this.target_entity_array, null);
        this.xp_entity_arraylist.clear();
        this.item_entity_arraylist.clear();
        this.all_entity_arraylist.clear();
        this.out_entity_arraylist.clear();
//        byte target_level = -1;
//
//        if (this.skinningentities.getByteDataParameterArray().length > 7)
//        {
//            target_level = this.getEntityData().get(this.getByteEntityDataAccessorArray()[7]);
//        }
//        Arrays.fill(this.distance_to_target_array, Double.MAX_VALUE);


        Map<UUID, Entity> entity_map = ((IMixinWorldServer)this.skinningentities.world).entitiesByUuid();
//        List<Entity> entity_list = this.skinningentities.world.getEntitiesWithinAABB(Entity.class, this.skinningentities.getEntityBoundingBox().grow(30.0D), entity -> entity.isEntityAlive());

//        for (int i = 0; i < this.target_entity_array.length; ++i)
//        {
//            if ((this.target_entity_array[i] != null && this.target_entity_array[i].isEntityAlive()))
//            {
//                this.distance_to_target_array[i] = this.skinningentities.getDistanceSq(this.target_entity_array[i]);
//            }
//        }

//        for (Entity entity : entity_list)
        Set<UUID> keys_set = new HashSet<>(entity_map.keySet());
        for (UUID id : keys_set)
        {
            Entity entity = entity_map.get(id);

            if (/*entity.dimension != this.skinningentities.dimension || */!entity.isEntityAlive())
            {
                continue;
            }

            // if (entity instanceof LivingEntity)
            // {
            //     LivingEntity livingentity = (LivingEntity)entity_list.get(i);
            // }

            if (entity instanceof EntityItem)
            {
//                this.target_entity_array[1] = entity;
//                this.setTarget(entity, 1);
                this.item_entity_arraylist.add((EntityItem)entity);
            }

            if (entity instanceof EntityXPOrb)
            {
//                this.target_entity_array[0] = entity;
                this.xp_entity_arraylist.add((EntityXPOrb)entity);
            }

            if (this.isTarget(entity))
            {
//                if (entity.isEntityAlive())
//                {
                this.all_entity_arraylist.add(entity);
//                this.setTarget(entity, 0);
//                }
            }
            else
            {
                this.out_entity_arraylist.add(entity);
            }

//            else if (target_level != -1 && entity.isAlive())
//            {
//                switch (target_level)
//                {
//                    case 1:
//                    {
//                        if (entity instanceof Enemy)
//                        {
//                            distance_to_target = this.setTarget(entity, distance_to_target);
//                            break;
//                        }
//
//                        if (entity instanceof Mob && ((Mob)entity).getTarget() == this)
//                        {
//                            distance_to_target = this.setTarget(entity, distance_to_target);
//                        }
//
//                        break;
//                    }
//                    case 2:
//                    {
//                        if (!FriendsMethods.isNotTarget(entity, this))
//                        {
//                            distance_to_target = this.setTarget(entity, distance_to_target);
//                        }
//
//                        break;
//                    }
//                    default:
//                    {
//                        break;
//                    }
//                }
//            }
        }
    }

//    public void setTarget(Entity entity, int index)
//    {
//        double new_distance_to_target = this.skinningentities.getDistanceSq(entity);
//
//        if (this.distance_to_target_array[index] > new_distance_to_target)
//        {
//            this.target_entity_array[index] = entity;
//            this.distance_to_target_array[index] = new_distance_to_target;
//        }
//    }

    public boolean isTarget(Entity entity)
    {
        boolean result = this.target_arraylist.isEmpty();

        if (result)
        {
            for (Class clasz : EntitiesRegistryHelper.ENTITIES_CLASS_LIST)
            {
                if (entity.getClass().equals(clasz))
                {
                    return false;
                }
            }
        }
        else
        {
            for (int id : this.target_arraylist)
            {
                if (id < EntitiesRegistryHelper.ENTITY_KEY_ARRAY.length && entity.getClass().equals(EntitiesRegistryHelper.ENTITY_KEY_ARRAY[id]))
                {
                    result = true;
                    break;
                }
            }
        }

        for (int id : this.troublemaker_arraylist)
        {
            if (id < EntitiesRegistryHelper.ENTITY_KEY_ARRAY.length && entity.getClass().equals(EntitiesRegistryHelper.ENTITY_KEY_ARRAY[id]))
            {
                return false;
            }
        }

        return result && !(entity instanceof EntityPlayer) && fastCheck(entity);
    }

    public static boolean fastCheck(Entity entity)
    {
        if (entity instanceof EntityTameable && ((EntityTameable)entity).getOwnerId() != null)
        {
            return false;
        }

        return true/*entity instanceof EntityLivingBase*/;
    }
}
