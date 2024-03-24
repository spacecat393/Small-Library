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
import com.nali.small.entities.skinning.ai.path.SkinningEntitiesWalkTo;
import com.nali.small.entities.skinning.ai.path.SkinningEntitiesRandomWalk;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;

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
    public SkinningEntitiesWalkTo skinningentitieswalkto;
    public SkinningEntitiesLookTo skinningentitieslookto;
    public SkinningEntitiesMine skinningentitiesmine;
    public SkinningEntitiesManageItem skinningentitiesmanageitem;

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
        this.skinningentitiesrandomwalk = new SkinningEntitiesRandomWalk(skinningentities);
        this.skinningentitieslookto = new SkinningEntitiesLookTo(skinningentities);
        this.skinningentitiesrandomlook = new SkinningEntitiesRandomLook(skinningentities);
        this.skinningentitieswalkto = new SkinningEntitiesWalkTo(skinningentities);
        this.skinningentitiesfollow = new SkinningEntitiesFollow(skinningentities);
        this.skinningentitiesrevive = new SkinningEntitiesRevive(skinningentities);
        this.skinningentitiesmine = new SkinningEntitiesMine(skinningentities);
        this.skinningentitiesmanageitem = new SkinningEntitiesManageItem(skinningentities);

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
    }

    public void writeNBT(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.setIntArray("target", this.skinningentitiesarea.target_arraylist.stream().mapToInt(Integer::intValue).toArray());
        nbttagcompound.setIntArray("troublemaker", this.skinningentitiesarea.troublemaker_arraylist.stream().mapToInt(Integer::intValue).toArray());

        nbttagcompound.setLong("blockpos_long", this.skinningentitiessetlocation.blockpos_long);
        nbttagcompound.setFloat("far_float", this.skinningentitiessetlocation.far);

        if (this.skinningentitiesmanageitem.in_blockpos != null)
        {
            nbttagcompound.setLong("in_manageitem", this.skinningentitiesmanageitem.in_blockpos.toLong());
        }
        if (this.skinningentitiesmanageitem.out_blockpos != null)
        {
            nbttagcompound.setLong("out_manageitem", this.skinningentitiesmanageitem.out_blockpos.toLong());
        }
        nbttagcompound.setByte("state_manageitem", this.skinningentitiesmanageitem.state);
        nbttagcompound.setInteger("rng_a_in_manageitem", this.skinningentitiesmanageitem.random_area_in);
        nbttagcompound.setInteger("rng_a_out_manageitem", this.skinningentitiesmanageitem.random_area_out);
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

        if (nbttagcompound.hasKey("in_manageitem"))
        {
            this.skinningentitiesmanageitem.in_blockpos = BlockPos.fromLong(nbttagcompound.getLong("in_manageitem"));
        }
        if (nbttagcompound.hasKey("out_manageitem"))
        {
            this.skinningentitiesmanageitem.out_blockpos = BlockPos.fromLong(nbttagcompound.getLong("out_manageitem"));
        }
        this.skinningentitiesmanageitem.state = nbttagcompound.getByte("state_manageitem");
        this.skinningentitiesmanageitem.random_area_in = nbttagcompound.getInteger("rng_a_in_manageitem");
        this.skinningentitiesmanageitem.random_area_out = nbttagcompound.getInteger("rng_a_out_manageitem");
    }

    public void update()
    {
        ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.skinningentities.bothentitiesmemory;
        boolean is_move = this.skinningentities.isMove();
        if (is_move)
        {
            this.skinningentitiesarea.onUpdate();
            this.skinningentitiessetlocation.onUpdate();
            this.skinningentitiesfollow.onUpdate();
            this.skinningentitiesrevive.onUpdate();

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

            this.skinningentitiesmanageitem.onUpdate();
            this.skinningentitiesgetitem.onUpdate();
            this.skinningentitiesmine.onUpdate();
            this.skinningentitiesrandomwalk.onUpdate();
            this.skinningentitieslookto.onUpdate();
            this.skinningentitiesrandomlook.onUpdate();
        }

        for (SkinningEntitiesLiveFrame skinningentitiesliveframe : this.skinningentitiesliveframe_array)
        {
            skinningentitiesliveframe.onUpdate();
        }

        if (is_move)
        {
            if ((serverentitiesmemory.main_work_byte_array[serverentitiesmemory.workbytes.SIT() / 8] >> serverentitiesmemory.workbytes.SIT() % 8 & 1) == 0)
            {
                this.skinningentitiesfindmove.onUpdate();
                this.skinningentitiesmove.onUpdate();
                this.skinningentitieswalkto.onUpdate();
            }

            this.skinningentitieslook.onUpdate();
        }

        if (!is_move)
        {
            this.skinningentitiesfindmove.path_blockpos_arraylist.clear();
        }

        this.skinningentitiesjump.onUpdate();
    }
}
