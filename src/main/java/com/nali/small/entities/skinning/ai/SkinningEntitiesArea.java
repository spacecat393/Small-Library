package com.nali.small.entities.skinning.ai;

import com.nali.small.entities.EntitiesRegistryHelper;
import com.nali.small.entities.memory.server.ServerEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.mixin.IMixinWorldServer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;

import java.util.*;

public class SkinningEntitiesArea extends SkinningEntitiesAI
{
    public List<EntityXPOrb> xp_entity_list = new ArrayList();
    public List<EntityItem> item_entity_list = new ArrayList();
    public List<Entity> all_entity_list = new ArrayList(); // target
    public List<Entity> out_entity_list = new ArrayList(); // not_target

    public List<Integer> troublemaker_list = new ArrayList();
    public List<Integer> target_list = new ArrayList();

    public byte flag;//check_tameable is_tameable | put_player put_owner put_other_tameable put_owner_tameable put_all_tameable put_object

    public SkinningEntitiesArea(SkinningEntities skinningentities)
    {
        super(skinningentities);
    }

    @Override
    public void onUpdate()
    {
        this.xp_entity_list.clear();
        this.item_entity_list.clear();
        this.all_entity_list.clear();
        this.out_entity_list.clear();

        Map<UUID, Entity> entity_map = ((IMixinWorldServer)this.skinningentities.world).entitiesByUuid();

        Set<UUID> keys_set = new HashSet(entity_map.keySet());
        for (UUID id : keys_set)
        {
            Entity entity = entity_map.get(id);

            if (!entity.isEntityAlive())
            {
                continue;
            }

            if (entity instanceof EntityItem)
            {
                this.item_entity_list.add((EntityItem)entity);
            }

            if (entity instanceof EntityXPOrb)
            {
                this.xp_entity_list.add((EntityXPOrb)entity);
            }

            if (this.isTarget(entity))
            {
                this.all_entity_list.add(entity);
            }
            else
            {
                this.out_entity_list.add(entity);
            }
        }
    }

    public boolean isTarget(Entity entity)
    {
        boolean result = this.target_list.isEmpty();

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
            for (int id : this.target_list)
            {
                if (id < EntitiesRegistryHelper.ENTITY_KEY_ARRAY.length && entity.getClass().equals(EntitiesRegistryHelper.ENTITY_KEY_ARRAY[id]))
                {
                    result = true;
                    break;
                }
            }
        }

        for (int id : this.troublemaker_list)
        {
            if (id < EntitiesRegistryHelper.ENTITY_KEY_ARRAY.length && entity.getClass().equals(EntitiesRegistryHelper.ENTITY_KEY_ARRAY[id]))
            {
                return false;
            }
        }

        if (result)
        {
            ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.skinningentities.bothentitiesmemory;
            //check tameable
            if ((this.flag & 64) == 0)
            {
                if ((this.flag & 16) == 0)
                {
                    if (entity instanceof EntityTameable)
                    {
                        if (((EntityTameable)entity).getOwnerId() != null)
                        {
                            return false;
                        }
                        else
                        {
                            this.flag |= 1 + 2;
                        }
                    }
                }

                if ((this.flag & 32) == 0)
                {
                    UUID uuid = serverentitiesmemory.owner_uuid;
                    if (uuid != null)
                    {
                        byte flag = (byte)(this.flag & 1+2);
                        if (flag == 0)
                        {
                            if (entity instanceof EntityTameable && ((EntityTameable)entity).getOwnerId() == uuid)
                            {
                                this.flag &= 255-1;
                                return false;
                            }
                        }
                        else if (flag == 3 && ((EntityTameable)entity).getOwnerId() == uuid)
                        {
                            this.flag &= 255-(1+2);
                            return false;
                        }
                    }
                }
            }

            if ((this.flag & 4) == 0 && entity instanceof EntityPlayer)
            {
                return false;
            }

            if ((this.flag & 8) == 0 && entity.getUniqueID().equals(serverentitiesmemory.owner_uuid))
            {
                return false;
            }

            if ((this.flag & 128) == 0 && !(entity instanceof EntityLivingBase))
            {
                return false;
            }
        }

        return result;
    }
}
