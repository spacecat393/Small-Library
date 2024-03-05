package com.nali.small.entities.skinning.ai.frame;

import com.nali.small.entities.memory.server.ServerEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.entities.skinning.ai.SkinningEntitiesAI;
import com.nali.small.entities.skinning.ai.SkinningEntitiesAttack;
import com.nali.small.entities.skinning.ai.SkinningEntitiesHeal;
import com.nali.small.entities.skinning.ai.SkinningEntitiesProtect;

import java.util.function.Supplier;

public class SkinningEntitiesLiveFrame extends SkinningEntitiesAI
{
    public int step = 1;
//    public int main_integer_index;
    public int integer_index;
    public int[][] int_2d_array; // start end
    public boolean lock;
    public Supplier<Boolean>[] condition_boolean_supplier_array;

    public SkinningEntitiesLiveFrame(SkinningEntities skinningentities/*, int main_integer_index*/, int integer_index, int[][] int_2d_array)
    {
        super(skinningentities);
//        this.main_integer_index = main_integer_index;
        this.integer_index = integer_index;
        this.int_2d_array = int_2d_array;
    }

    @Override
    public void onUpdate()
    {
//        Small.LOGGER.info("FRAME " + serverentitiesmemory.frame_int_array[this.integer_index]);

        if (!this.lock)
        {
            ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.skinningentities.bothentitiesmemory;
            for (Supplier<Boolean> boolean_supplier : this.condition_boolean_supplier_array)
            {
                if (boolean_supplier.get())
                {
                    this.skinningentities.getDataManager().set(this.skinningentities.getIntegerDataParameterArray()[/*this.main_integer_index*/this.integer_index], serverentitiesmemory.frame_int_array[this.integer_index] + this.step);
                    return;
                }
            }
        }
    }

    public void stepFrame()
    {
        ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.skinningentities.bothentitiesmemory;
        this.skinningentities.getDataManager().set(this.skinningentities.getIntegerDataParameterArray()[this.integer_index], serverentitiesmemory.frame_int_array[this.integer_index] + this.step);
    }

    public boolean setTLoop(int id0)
    {
        ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.skinningentities.bothentitiesmemory;
        this.step = 1;
        if (serverentitiesmemory.frame_int_array[this.integer_index] < this.int_2d_array[id0][0] || serverentitiesmemory.frame_int_array[this.integer_index] > this.int_2d_array[id0][1] - 1)
        {
            serverentitiesmemory.frame_int_array[this.integer_index] = this.int_2d_array[id0][0];
            this.step = 0;
            return true;
        }

        return serverentitiesmemory.frame_int_array[this.integer_index] < this.int_2d_array[id0][1];
    }

    public boolean setFLoop(int id0)
    {
        ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.skinningentities.bothentitiesmemory;
        if (serverentitiesmemory.frame_int_array[this.integer_index] == this.int_2d_array[id0][1])
        {
            serverentitiesmemory.frame_int_array[this.integer_index] = this.int_2d_array[id0][1];
            this.step = 0;
            return true;
        }
        else if (serverentitiesmemory.frame_int_array[this.integer_index] < this.int_2d_array[id0][0] || serverentitiesmemory.frame_int_array[this.integer_index] > this.int_2d_array[id0][1])
        {
            serverentitiesmemory.frame_int_array[this.integer_index] = this.int_2d_array[id0][0];
            this.step = 0;
            return true;
        }

        this.step = 1;
        return serverentitiesmemory.frame_int_array[this.integer_index] < this.int_2d_array[id0][1];
    }

    public boolean setFLoopFree(int id0, byte byte_id)
    {
        ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.skinningentities.bothentitiesmemory;
        if (serverentitiesmemory.frame_int_array[this.integer_index] == this.int_2d_array[id0][1] - 1)
        {
            byte on = (byte)(serverentitiesmemory.statentitiesmemory.stat & byte_id);
            serverentitiesmemory.statentitiesmemory.stat ^= on;

            serverentitiesmemory.frame_int_array[this.integer_index] = this.int_2d_array[id0][1] - 1;
            this.step = 0;
            return true;
        }
        else if (serverentitiesmemory.frame_int_array[this.integer_index] < this.int_2d_array[id0][0] || serverentitiesmemory.frame_int_array[this.integer_index] > this.int_2d_array[id0][1])
        {
            serverentitiesmemory.frame_int_array[this.integer_index] = this.int_2d_array[id0][0];
            this.step = 0;
            return true;
        }

        this.step = 1;
        return serverentitiesmemory.frame_int_array[this.integer_index] < this.int_2d_array[id0][1];
    }

    public boolean setFLoopOffSet(int id0, int id1)
    {
        ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.skinningentities.bothentitiesmemory;
        boolean result = serverentitiesmemory.frame_int_array[this.integer_index] > this.int_2d_array[id0][0] && serverentitiesmemory.frame_int_array[this.integer_index] < this.int_2d_array[id1][1];

        if (result)
        {
            if (serverentitiesmemory.frame_int_array[this.integer_index] > this.int_2d_array[id0][1] - 1 && serverentitiesmemory.frame_int_array[this.integer_index] < this.int_2d_array[id1][0])
            {
                serverentitiesmemory.frame_int_array[this.integer_index] = this.int_2d_array[id1][0];
                this.step = 0;
                return true;
            }

            this.step = 1;
            return serverentitiesmemory.frame_int_array[this.integer_index] < this.int_2d_array[id1][1];
        }

        return result;
    }

    public boolean setFLoopInSet(int id0, int id1)
    {
        ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.skinningentities.bothentitiesmemory;
        boolean result = serverentitiesmemory.frame_int_array[this.integer_index] >= this.int_2d_array[id0][1] && serverentitiesmemory.frame_int_array[this.integer_index] < this.int_2d_array[id1][1];

        if (result)
        {
            this.step = 1;
        }

        return result;
    }

    public boolean setTLoopInSet(int id0, int id1)
    {
        ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.skinningentities.bothentitiesmemory;
        boolean result = serverentitiesmemory.frame_int_array[this.integer_index] >= this.int_2d_array[id0][1] && serverentitiesmemory.frame_int_array[this.integer_index] <= this.int_2d_array[id1][1];

        if (result)
        {
            if (serverentitiesmemory.frame_int_array[this.integer_index] == this.int_2d_array[id1][1])
            {
                serverentitiesmemory.frame_int_array[this.integer_index] = this.int_2d_array[id1][0];
                this.step = 0;
                return true;
            }

            this.step = 1;
            return serverentitiesmemory.frame_int_array[this.integer_index] < this.int_2d_array[id1][1];
        }

        return result;
    }

    public boolean setTLoopFB(int id0)
    {
        ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.skinningentities.bothentitiesmemory;
        if (serverentitiesmemory.frame_int_array[this.integer_index] < this.int_2d_array[id0][0] || serverentitiesmemory.frame_int_array[this.integer_index] > this.int_2d_array[id0][1])
        {
            this.step = 0;
            serverentitiesmemory.frame_int_array[this.integer_index] = this.int_2d_array[id0][0];
        }
        else if (serverentitiesmemory.frame_int_array[this.integer_index] == this.int_2d_array[id0][0])
        {
            this.step = 1;
        }
        else if (serverentitiesmemory.frame_int_array[this.integer_index] == this.int_2d_array[id0][1])
        {
            this.step = -1;
        }

        return true;
    }

    public boolean setShoot(int id0, int id1, int id2, int id3, boolean bf, SkinningEntitiesAttack skinningentitiesattack)
    {
        ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.skinningentities.bothentitiesmemory;
//        Small.LOGGER.info("STATE " + skinningentitiesattack.state);
        if (serverentitiesmemory.statentitiesmemory.magic_point <= 0)
        {
            this.step = 1;
            if (this.checkShoot(id0, id1, id2, true))
            {
                return true;
            }
            else
            {
                serverentitiesmemory.entitiesaimemory.skinningentitiesfindmove.endGoal();

                if (serverentitiesmemory.frame_int_array[this.integer_index] < this.int_2d_array[id3][0] || serverentitiesmemory.frame_int_array[this.integer_index] > this.int_2d_array[id3][1])
                {
                    this.step = 0;
                    serverentitiesmemory.frame_int_array[this.integer_index] = this.int_2d_array[id3][0];
                    return true;
                }
                else if (serverentitiesmemory.frame_int_array[this.integer_index] >= this.int_2d_array[id3][0] && serverentitiesmemory.frame_int_array[this.integer_index] <= this.int_2d_array[id3][1])
                {
                    if (serverentitiesmemory.frame_int_array[this.integer_index] == this.int_2d_array[id3][1])
                    {
                        this.step = 0;
                        serverentitiesmemory.statentitiesmemory.magic_point = skinningentitiesattack.max_ammo;
//                        if (skinningentitiesattack.state == 0 || skinningentitiesattack.state == 1)
//                        {
//                            serverentitiesmemory.frame_int_array[this.integer_index] = this.int_2d_array[id0][1];
//                        }
                        return true;
                    }
                }
                return true;
            }
        }
        else if (skinningentitiesattack.state == 0 || skinningentitiesattack.state == 1)
        {
            if (serverentitiesmemory.frame_int_array[this.integer_index] >= this.int_2d_array[id1][0] && serverentitiesmemory.frame_int_array[this.integer_index] <= this.int_2d_array[id1][1])
            {
                if (bf)
                {
                    if (serverentitiesmemory.frame_int_array[this.integer_index] == this.int_2d_array[id1][0])
                    {
                        this.step = 1;
                    }
                    else if (serverentitiesmemory.frame_int_array[this.integer_index] == this.int_2d_array[id1][1])
                    {
                        this.step = -1;
                    }
                }
                else
                {
                    this.step = 1;
                    if (serverentitiesmemory.frame_int_array[this.integer_index] >= this.int_2d_array[id1][0] && serverentitiesmemory.frame_int_array[this.integer_index] <= this.int_2d_array[id1][1])
                    {
                        if (serverentitiesmemory.frame_int_array[this.integer_index] == this.int_2d_array[id1][1])
                        {
                            serverentitiesmemory.frame_int_array[this.integer_index] = this.int_2d_array[id1][0];
                            this.step = 0;
                        }
                    }
                }

                for (int attack_frame : skinningentitiesattack.attack_frame_int_array)
                {
                    if (serverentitiesmemory.frame_int_array[this.integer_index] == attack_frame)
                    {
                        serverentitiesmemory.statentitiesmemory.magic_point -= 1;
                        skinningentitiesattack.state = 1;
                        break;
                    }
                }
                return true;
            }
            else if (serverentitiesmemory.frame_int_array[this.integer_index] >= this.int_2d_array[id0][0] && serverentitiesmemory.frame_int_array[this.integer_index] <= this.int_2d_array[id0][1])
            {
                if (serverentitiesmemory.frame_int_array[this.integer_index] == this.int_2d_array[id0][1])
                {
                    serverentitiesmemory.frame_int_array[this.integer_index] = this.int_2d_array[id1][1];
                    this.step = 0;
                    return true;
                }

                this.step = 1;
                return true;
            }
            else if (serverentitiesmemory.frame_int_array[this.integer_index] < this.int_2d_array[id0][0] || serverentitiesmemory.frame_int_array[this.integer_index] > this.int_2d_array[id0][1])
            {
                serverentitiesmemory.frame_int_array[this.integer_index] = this.int_2d_array[id0][0];
                this.step = 0;
                return true;
            }
        }
        else
        {
            this.step = 1;
            return this.checkShoot(id0, id1, id2, false);
        }

        return false;
    }

    public boolean checkShoot(int id0, int id1, int id2, boolean try_reload)
    {
        ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.skinningentities.bothentitiesmemory;
        if (serverentitiesmemory.frame_int_array[this.integer_index] >= this.int_2d_array[id0][0] && serverentitiesmemory.frame_int_array[this.integer_index] <= this.int_2d_array[id0][1])
        {
            serverentitiesmemory.entitiesaimemory.skinningentitiesfindmove.endGoal();
            if (serverentitiesmemory.frame_int_array[this.integer_index] == this.int_2d_array[id0][1])
            {
                serverentitiesmemory.frame_int_array[this.integer_index] = this.int_2d_array[id2][0];
                this.step = 0;
            }
            return true;
        }
        else if (serverentitiesmemory.frame_int_array[this.integer_index] >= this.int_2d_array[id1][0] && serverentitiesmemory.frame_int_array[this.integer_index] <= this.int_2d_array[id1][1])
        {
            serverentitiesmemory.entitiesaimemory.skinningentitiesfindmove.endGoal();
            if (serverentitiesmemory.frame_int_array[this.integer_index] == this.int_2d_array[id1][1])
            {
                serverentitiesmemory.frame_int_array[this.integer_index] = this.int_2d_array[id2][0];
                this.step = 0;
            }
            return true;
        }
        else if (!try_reload && serverentitiesmemory.frame_int_array[this.integer_index] >= this.int_2d_array[id2][0] && serverentitiesmemory.frame_int_array[this.integer_index] < this.int_2d_array[id2][1])
        {
            serverentitiesmemory.entitiesaimemory.skinningentitiesfindmove.endGoal();
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean setHeal(int id0, SkinningEntitiesHeal skinningentitiesheal)
    {
        ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.skinningentities.bothentitiesmemory;
        this.step = 1;
        if (skinningentitiesheal.state == 0 || skinningentitiesheal.state == 1)
        {
            for (int heal_frame : skinningentitiesheal.heal_frame_int_array)
            {
                if (serverentitiesmemory.frame_int_array[this.integer_index] == heal_frame)
                {
                    skinningentitiesheal.state = 1;
                    break;
                }
            }

            if (serverentitiesmemory.frame_int_array[this.integer_index] < this.int_2d_array[id0][0] || serverentitiesmemory.frame_int_array[this.integer_index] > this.int_2d_array[id0][1])
            {
                serverentitiesmemory.frame_int_array[this.integer_index] = this.int_2d_array[id0][0];
                this.step = 0;
            }
            else if (serverentitiesmemory.frame_int_array[this.integer_index] == this.int_2d_array[id0][1])
            {
                serverentitiesmemory.frame_int_array[this.integer_index] = this.int_2d_array[id0][0];
                this.step = 0;
            }

            return true;
        }

        return false;
    }

    public boolean setProtect(int id0, int id1, int id2, int id3, SkinningEntitiesProtect skinningentitiesprotect)
    {
        ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.skinningentities.bothentitiesmemory;
        this.step = 1;
        switch (skinningentitiesprotect.state)
        {
            case 0:
            {
                if (serverentitiesmemory.frame_int_array[this.integer_index] < this.int_2d_array[id0][0] || serverentitiesmemory.frame_int_array[this.integer_index] > this.int_2d_array[id0][1])
                {
                    serverentitiesmemory.frame_int_array[this.integer_index] = this.int_2d_array[id0][0];
                    this.step = 0;
                }
                else if (serverentitiesmemory.frame_int_array[this.integer_index] == this.int_2d_array[id0][1])
                {
                    serverentitiesmemory.frame_int_array[this.integer_index] = this.int_2d_array[id1][0];
                    this.step = 0;
                    skinningentitiesprotect.main_state = 1;
                }

                break;
            }
            case 1:
            {
                if (serverentitiesmemory.frame_int_array[this.integer_index] < this.int_2d_array[id1][0] || serverentitiesmemory.frame_int_array[this.integer_index] > this.int_2d_array[id1][1])
                {
                    serverentitiesmemory.frame_int_array[this.integer_index] = this.int_2d_array[id1][0];
                    this.step = 0;
                }
                else if (serverentitiesmemory.frame_int_array[this.integer_index] == this.int_2d_array[id1][1])
                {
                    serverentitiesmemory.frame_int_array[this.integer_index] = this.int_2d_array[id1][0];
                    this.step = 0;
                }

                break;
            }
            case 2:
            {
                if (serverentitiesmemory.frame_int_array[this.integer_index] < this.int_2d_array[id2][0] || serverentitiesmemory.frame_int_array[this.integer_index] > this.int_2d_array[id2][1])
                {
                    serverentitiesmemory.frame_int_array[this.integer_index] = this.int_2d_array[id2][0];
                    this.step = 0;
                }
                else if (serverentitiesmemory.frame_int_array[this.integer_index] == this.int_2d_array[id2][1])
                {
                    serverentitiesmemory.frame_int_array[this.integer_index] = this.int_2d_array[id2][0];
                    this.step = 0;
                    skinningentitiesprotect.main_state = 1;
                }

                break;
            }
            case 3:
            {
                if (serverentitiesmemory.frame_int_array[this.integer_index] < this.int_2d_array[id3][0] || serverentitiesmemory.frame_int_array[this.integer_index] > this.int_2d_array[id3][1])
                {
                    serverentitiesmemory.frame_int_array[this.integer_index] = this.int_2d_array[id3][0];
                    this.step = 0;
                }
                else if (serverentitiesmemory.frame_int_array[this.integer_index] == this.int_2d_array[id3][1])
                {
//                    serverentitiesmemory.frame_int_array[this.integer_index] = this.int_2d_array[id3][0];
                    this.step = 0;
                    skinningentitiesprotect.main_state = -2;
                    return true;
                }
                break;
            }
            default:
            {
                break;
            }
        }

        return skinningentitiesprotect.state > -1;
    }
}
