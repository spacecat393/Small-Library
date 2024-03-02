package com.nali.small.entities.memory.server;

import com.nali.small.entities.bytes.WorkBytes;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.entities.skinning.ai.*;
import com.nali.small.entities.skinning.ai.eyes.SkinningEntitiesLook;
import com.nali.small.entities.skinning.ai.eyes.SkinningEntitiesLookTo;
import com.nali.small.entities.skinning.ai.eyes.SkinningEntitiesRandomLook;
import com.nali.small.entities.skinning.ai.frame.SkinningEntitiesLiveFrame;
import com.nali.small.entities.skinning.ai.path.SkinningEntitiesFindMove;
import com.nali.small.entities.skinning.ai.path.SkinningEntitiesMove;
import com.nali.small.entities.skinning.ai.path.SkinningEntitiesPassTo;
import com.nali.small.entities.skinning.ai.path.SkinningEntitiesRandomWalk;
import net.minecraft.nbt.NBTTagCompound;

public class EntitiesAIMemory
{
    public SkinningEntities skinningentities;
    public SkinningEntitiesLiveFrame[] skinningentitiesliveframe_array;
    public SkinningEntitiesArea skinningentitiesarea;
    public SkinningEntitiesSetLocation skinningentitiessetlocation;
    public SkinningEntitiesFindMove skinningentitiesfindmove;
    public SkinningEntitiesLook skinningentitieslook;
    public SkinningEntitiesMove skinningentitiesmove;
    public SkinningEntitiesJump skinningentitiesjump;
    public SkinningEntitiesGetItem skinningentitiesgetitem;
    public SkinningEntitiesFollow skinningentitiesfollow;
    public SkinningEntitiesRandomWalk skinningentitiesrandomwalk;
    public SkinningEntitiesRandomLook skinningentitiesrandomlook;
    public SkinningEntitiesHeal skinningentitiesheal;
    public SkinningEntitiesProtect skinningentitiesprotect;
    public SkinningEntitiesCareOwner skinningentitiescareowner;
    public SkinningEntitiesAttack skinningentitiesattack;
    public SkinningEntitiesRevive skinningentitiesrevive;
    public SkinningEntitiesPlayWith skinningentitiesplaywith;
    public SkinningEntitiesPassTo skinningentitiespassto;
    public SkinningEntitiesLookTo skinningentitieslookto;

    public EntitiesAIMemory(SkinningEntities skinningentities)
    {
        this.skinningentities = skinningentities;
        WorkBytes workbytes = skinningentities.bothentitiesmemory.workbytes;

        this.skinningentitiesarea = new SkinningEntitiesArea(skinningentities);
        this.skinningentitiessetlocation = new SkinningEntitiesSetLocation(skinningentities);
        this.skinningentitiesfindmove = new SkinningEntitiesFindMove(skinningentities);
        this.skinningentitieslook = new SkinningEntitiesLook(skinningentities);
        this.skinningentitiesmove = new SkinningEntitiesMove(skinningentities);
        this.skinningentitiesjump = new SkinningEntitiesJump(skinningentities);
        this.skinningentitiesgetitem = new SkinningEntitiesGetItem(skinningentities);

        if (workbytes.FOLLOW() != -1)
        {
            this.skinningentitiesfollow = new SkinningEntitiesFollow(skinningentities);
        }
        if (workbytes.RANDOM_WALK() != -1)
        {
            this.skinningentitiesrandomwalk = new SkinningEntitiesRandomWalk(skinningentities);
        }
        if (workbytes.LOOK_TO() != -1)
        {
            this.skinningentitieslookto = new SkinningEntitiesLookTo(skinningentities);
        }
        if (workbytes.RANDOM_LOOK() != -1)
        {
            this.skinningentitiesrandomlook = new SkinningEntitiesRandomLook(skinningentities);
        }
        if (workbytes.WALK_TO() != -1)
        {
            this.skinningentitiespassto = new SkinningEntitiesPassTo(skinningentities);
        }

        if (workbytes.HEAL() != -1)
        {
            this.skinningentitiesheal = new SkinningEntitiesHeal(skinningentities);
        }
        if (workbytes.PROTECT() != -1)
        {
            this.skinningentitiesprotect = new SkinningEntitiesProtect(skinningentities);
        }
        if (workbytes.PLAY() != -1)
        {
            this.skinningentitiesplaywith = new SkinningEntitiesPlayWith(skinningentities);
        }
        if (workbytes.CARE_OWNER() != -1)
        {
            this.skinningentitiescareowner = new SkinningEntitiesCareOwner(skinningentities);
        }
        if (workbytes.ATTACK() != -1)
        {
            this.skinningentitiesattack = new SkinningEntitiesAttack(skinningentities);
        }
        if (workbytes.REVIVE() != -1)
        {
            this.skinningentitiesrevive = new SkinningEntitiesRevive(skinningentities);
        }
    }

    public void writeNBT(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.setIntArray("target", this.skinningentitiesarea.target_arraylist.stream().mapToInt(Integer::intValue).toArray());
        nbttagcompound.setIntArray("troublemaker", this.skinningentitiesarea.troublemaker_arraylist.stream().mapToInt(Integer::intValue).toArray());

        nbttagcompound.setLong("blockpos_long", this.skinningentitiessetlocation.blockpos_long);
        nbttagcompound.setFloat("far_float", this.skinningentitiessetlocation.far);
    }

    public void readNBT(NBTTagCompound nbttagcompound)
    {
        int[] int_array = nbttagcompound.getIntArray("target");
        for (int x : int_array)
        {
            this.skinningentitiesarea.target_arraylist.add(x);
        }

        int_array = nbttagcompound.getIntArray("troublemaker");
        for (int x : int_array)
        {
            this.skinningentitiesarea.troublemaker_arraylist.add(x);
        }

        this.skinningentitiessetlocation.blockpos_long = nbttagcompound.getLong("blockpos_long");
        this.skinningentitiessetlocation.far = nbttagcompound.getFloat("far_float");
    }

    public void update()
    {
        ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.skinningentities.bothentitiesmemory;
        boolean is_move = this.skinningentities.isMove();
        if (is_move)
        {
            this.skinningentitiesarea.onUpdate();

            this.skinningentitiessetlocation.onUpdate();

            if (this.skinningentitiesfollow != null)
            {
                this.skinningentitiesfollow.onUpdate();
            }
            if (this.skinningentitiesrevive != null)
            {
                this.skinningentitiesrevive.onUpdate();
            }

            if (this.skinningentitiesplaywith != null)
            {
                this.skinningentitiesplaywith.onUpdate();
            }
            if (this.skinningentitiesheal != null)
            {
                this.skinningentitiesheal.onUpdate();
            }
            if (this.skinningentitiesprotect != null)
            {
                this.skinningentitiesprotect.onUpdate();
            }
            if (this.skinningentitiescareowner != null)
            {
                this.skinningentitiescareowner.onUpdate();
            }
            if (this.skinningentitiesattack != null)
            {
                this.skinningentitiesattack.onUpdate();
            }

            this.skinningentitiesgetitem.onUpdate();
            if (this.skinningentitiesrandomwalk != null)
            {
                this.skinningentitiesrandomwalk.onUpdate();
            }
            if (this.skinningentitieslookto != null)
            {
                this.skinningentitieslookto.onUpdate();
            }
            if (this.skinningentitiesrandomlook != null)
            {
                this.skinningentitiesrandomlook.onUpdate();
            }
        }

        for (SkinningEntitiesLiveFrame skinningentitiesliveframe : this.skinningentitiesliveframe_array)
        {
            skinningentitiesliveframe.onUpdate();
        }

        if (is_move)
        {
            if (serverentitiesmemory.main_work_byte_array[serverentitiesmemory.workbytes.SIT()] == 0)
            {
                this.skinningentitiesfindmove.onUpdate();
                this.skinningentitiesmove.onUpdate();
            }

            this.skinningentitieslook.onUpdate();
            if (this.skinningentitiespassto != null)
            {
                this.skinningentitiespassto.onUpdate();
            }
        }

        if (!is_move)
        {
            this.skinningentitiesfindmove.path_blockpos_arraylist.clear();
        }

        this.skinningentitiesjump.onUpdate();
    }
}
