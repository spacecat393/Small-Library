package com.nali.small.entities.skinning.ai.frame;

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
//        Small.LOGGER.info("FRAME " + this.skinningentities.server_frame_int_array[this.integer_index]);

        for (Supplier<Boolean> boolean_supplier : this.condition_boolean_supplier_array)
        {
            if (boolean_supplier.get())
            {
                this.skinningentities.getDataManager().set(this.skinningentities.getIntegerDataParameterArray()[/*this.main_integer_index*/this.integer_index], this.skinningentities.server_frame_int_array[this.integer_index] + this.step);
                return;
            }
        }
    }

    public boolean setTLoop(int id0)
    {
        this.step = 1;
        if (this.skinningentities.server_frame_int_array[this.integer_index] < this.int_2d_array[id0][0] || this.skinningentities.server_frame_int_array[this.integer_index] > this.int_2d_array[id0][1] - 1)
        {
            this.skinningentities.server_frame_int_array[this.integer_index] = this.int_2d_array[id0][0];
            this.step = 0;
            return true;
        }

        return this.skinningentities.server_frame_int_array[this.integer_index] < this.int_2d_array[id0][1];
    }

    public boolean setFLoop(int id0, boolean result)
    {
        if (result)
        {
            if (this.skinningentities.server_frame_int_array[this.integer_index] == this.int_2d_array[id0][1])
            {
                this.skinningentities.server_frame_int_array[this.integer_index] = this.int_2d_array[id0][1];
                this.step = 0;
                return true;
            }
            else if (this.skinningentities.server_frame_int_array[this.integer_index] < this.int_2d_array[id0][0] || this.skinningentities.server_frame_int_array[this.integer_index] > this.int_2d_array[id0][1])
            {
                this.skinningentities.server_frame_int_array[this.integer_index] = this.int_2d_array[id0][0];
                this.step = 0;
                return true;
            }

            this.step = 1;
            return this.skinningentities.server_frame_int_array[this.integer_index] < this.int_2d_array[id0][1];
        }

        return result;
    }

    public boolean setFLoopFree(int id0, int byte_id, boolean result)
    {
        if (result)
        {
            if (this.skinningentities.server_frame_int_array[this.integer_index] == this.int_2d_array[id0][1] - 1)
            {
//                this.skinningentities.getDataManager().set(this.skinningentities.getByteDataParameterArray()[byte_id], (byte)0);
                this.skinningentities.main_server_work_byte_array[byte_id] = 0;
                this.skinningentities.server_frame_int_array[this.integer_index] = this.int_2d_array[id0][1] - 1;
                this.step = 0;
                return true;
            }
            else if (this.skinningentities.server_frame_int_array[this.integer_index] < this.int_2d_array[id0][0] || this.skinningentities.server_frame_int_array[this.integer_index] > this.int_2d_array[id0][1])
            {
                this.skinningentities.server_frame_int_array[this.integer_index] = this.int_2d_array[id0][0];
                this.step = 0;
                return true;
            }

            this.step = 1;
            return this.skinningentities.server_frame_int_array[this.integer_index] < this.int_2d_array[id0][1];
        }

        return result;
    }

    public boolean setFLoopOffSet(int id0, int id1)
    {
        boolean result = this.skinningentities.server_frame_int_array[this.integer_index] > this.int_2d_array[id0][0] && this.skinningentities.server_frame_int_array[this.integer_index] < this.int_2d_array[id1][1];

        if (result)
        {
            if (this.skinningentities.server_frame_int_array[this.integer_index] > this.int_2d_array[id0][1] - 1 && this.skinningentities.server_frame_int_array[this.integer_index] < this.int_2d_array[id1][0])
            {
                this.skinningentities.server_frame_int_array[this.integer_index] = this.int_2d_array[id1][0];
                this.step = 0;
                return true;
            }

            this.step = 1;
            return this.skinningentities.server_frame_int_array[this.integer_index] < this.int_2d_array[id1][1];
        }

        return result;
    }

    public boolean setFLoopInSet(int id0, int id1)
    {
        boolean result = this.skinningentities.server_frame_int_array[this.integer_index] >= this.int_2d_array[id0][1] && this.skinningentities.server_frame_int_array[this.integer_index] < this.int_2d_array[id1][1];

        if (result)
        {
            this.step = 1;
        }

        return result;
    }

    public boolean setTLoop(int id0, boolean result)
    {
        if (result)
        {
            return this.setTLoop(id0);
        }

        return result;
    }

    public boolean setTLoopInSet(int id0, int id1)
    {
        boolean result = this.skinningentities.server_frame_int_array[this.integer_index] >= this.int_2d_array[id0][1] && this.skinningentities.server_frame_int_array[this.integer_index] <= this.int_2d_array[id1][1];

        if (result)
        {
            if (this.skinningentities.server_frame_int_array[this.integer_index] == this.int_2d_array[id1][1])
            {
                this.skinningentities.server_frame_int_array[this.integer_index] = this.int_2d_array[id1][0];
                this.step = 0;
                return true;
            }

            this.step = 1;
            return this.skinningentities.server_frame_int_array[this.integer_index] < this.int_2d_array[id1][1];
        }

        return result;
    }

    public boolean setTLoopFB(int id0, boolean result)
    {
        if (result)
        {
            if (this.skinningentities.server_frame_int_array[this.integer_index] < this.int_2d_array[id0][0] + 1)
            {
                this.step = 1;
                this.skinningentities.server_frame_int_array[this.integer_index] = this.int_2d_array[id0][0];
            }

            if (this.skinningentities.server_frame_int_array[this.integer_index] > this.int_2d_array[id0][1] - 1)
            {
                this.step = -1;
                this.skinningentities.server_frame_int_array[this.integer_index] = this.int_2d_array[id0][1];
            }
        }

        return result;
    }

    public boolean setShoot(int id0, int id1, int id2, int id3, boolean bf, SkinningEntitiesAttack skinningentitiesattack)
    {
//        this.step = 1;
//        byte state = skinningentitiesattack.getByte();
//        EntityDataManager entitydatamanager = this.skinningentities.getDataManager();
//        DataParameter<Byte> byte_dataparameter = this.skinningentities.getByteDataParameterArray()[this.skinningentities.skinningentitiesbytes.AMMO()];
//        byte ammo = entitydatamanager.get(byte_dataparameter);
        if (this.skinningentities.main_server_work_byte_array[this.skinningentities.skinningentitiesbytes.AMMO()] <= 0)
        {
            this.step = 1;
            if (this.checkShoot(id0, id1, id2, true))
            {
                return true;
            }
            else
            {
                if (this.skinningentities.server_frame_int_array[this.integer_index] < this.int_2d_array[id3][0] || this.skinningentities.server_frame_int_array[this.integer_index] > this.int_2d_array[id3][1])
                {
                    this.skinningentities.server_frame_int_array[this.integer_index] = this.int_2d_array[id3][0];
                    this.step = 0;
                    return true;
                }
                else if (this.skinningentities.server_frame_int_array[this.integer_index] >= this.int_2d_array[id3][0] && this.skinningentities.server_frame_int_array[this.integer_index] < this.int_2d_array[id3][1])
                {
                    if (this.skinningentities.server_frame_int_array[this.integer_index] == this.int_2d_array[id3][1] - 1)
                    {
                        this.skinningentities.main_server_work_byte_array[this.skinningentities.skinningentitiesbytes.AMMO()] = skinningentitiesattack.max_ammo;
//                        entitydatamanager.set(byte_dataparameter, this.max_ammo);
                    }

                    return true;
                }
            }
        }
        else if (skinningentitiesattack.state == 0 || skinningentitiesattack.state == 1)
        {
            //start gun
            if (this.skinningentities.server_frame_int_array[this.integer_index] < this.int_2d_array[id0][0] || this.skinningentities.server_frame_int_array[this.integer_index] > this.int_2d_array[id1][1])
            {
                this.skinningentities.server_frame_int_array[this.integer_index] = this.int_2d_array[id0][0];
                this.step = 0;
                return true;
            }
            else
            {
                if (bf)
                {
                    if (this.skinningentities.server_frame_int_array[this.integer_index] < this.int_2d_array[id1][0])
                    {
                        this.step = 1;
                    }
                    else
                    {
                        if (this.skinningentities.server_frame_int_array[this.integer_index] < this.int_2d_array[id1][0] + 1)
                        {
                            this.step = 1;
                            this.skinningentities.server_frame_int_array[this.integer_index] = this.int_2d_array[id1][0];
                        }

                        if (this.skinningentities.server_frame_int_array[this.integer_index] > this.int_2d_array[id1][1] - 1)
                        {
                            this.step = -1;
                            this.skinningentities.server_frame_int_array[this.integer_index] = this.int_2d_array[id1][1];
                        }
                    }
                }
                else
                {
                    this.step = 1;
                    if (this.skinningentities.server_frame_int_array[this.integer_index] >= this.int_2d_array[id0][0] && this.skinningentities.server_frame_int_array[this.integer_index] < this.int_2d_array[id1][1])
                    {
                        if (this.skinningentities.server_frame_int_array[this.integer_index] == this.int_2d_array[id1][1] - 1)
                        {
                            this.skinningentities.server_frame_int_array[this.integer_index] = this.int_2d_array[id1][0];
                            this.step = 0;
                        }
                    }
                }

                //                skinningentitiesframeai.setByte((byte)1);
                for (int attack_frame : skinningentitiesattack.attack_frame_int_array)
                {
                    if (this.skinningentities.server_frame_int_array[this.integer_index] == attack_frame)
                    {
                        this.skinningentities.main_server_work_byte_array[this.skinningentities.skinningentitiesbytes.AMMO()] -= 1;
//                        entitydatamanager.set(byte_dataparameter, (byte)(ammo - 1));
                        skinningentitiesattack.state = 1;
                        break;
                    }
                }

                return true;
            }
        }
        else// if (state > 1)
        {
            this.step = 1;
            //if gun / shoot -> end gun
            return this.checkShoot(id0, id1, id2, false);
        }

        return false;
    }

    public boolean checkShoot(int id0, int id1, int id2, boolean try_reload)
    {
        if (this.skinningentities.server_frame_int_array[this.integer_index] >= this.int_2d_array[id0][0] && this.skinningentities.server_frame_int_array[this.integer_index] <= this.int_2d_array[id0][1])
        {
//            this.skinningentities.server_frame_int_array[this.integer_index] = this.int_2d_array[id2][0];
//            this.step = 0;
//            return true;
            if (this.skinningentities.server_frame_int_array[this.integer_index] == this.int_2d_array[id0][1])
            {
                this.skinningentities.server_frame_int_array[this.integer_index] = this.int_2d_array[id2][0];
                this.step = 0;
            }

            return true;
        }
        else if (this.skinningentities.server_frame_int_array[this.integer_index] >= this.int_2d_array[id1][0] && this.skinningentities.server_frame_int_array[this.integer_index] <= this.int_2d_array[id1][1])
        {
//            this.skinningentities.server_frame_int_array[this.integer_index] = this.int_2d_array[id2][0];
//            this.step = 0;
//            return true;
            if (this.skinningentities.server_frame_int_array[this.integer_index] == this.int_2d_array[id1][1])
            {
                this.skinningentities.server_frame_int_array[this.integer_index] = this.int_2d_array[id2][0];
                this.step = 0;
            }

            return true;
        }
        else if (!try_reload && this.skinningentities.server_frame_int_array[this.integer_index] >= this.int_2d_array[id2][0] && this.skinningentities.server_frame_int_array[this.integer_index] < this.int_2d_array[id2][1])
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean setHeal(int id0, SkinningEntitiesHeal skinningentitiesheal)
    {
        this.step = 1;
        if (skinningentitiesheal.state == 0 || skinningentitiesheal.state == 1)
        {
            for (int heal_frame : skinningentitiesheal.heal_frame_int_array)
            {
                if (this.skinningentities.server_frame_int_array[this.integer_index] == heal_frame)
                {
                    skinningentitiesheal.state = 1;
                    break;
                }
            }

            if (this.skinningentities.server_frame_int_array[this.integer_index] < this.int_2d_array[id0][0] || this.skinningentities.server_frame_int_array[this.integer_index] > this.int_2d_array[id0][1])
            {
                this.skinningentities.server_frame_int_array[this.integer_index] = this.int_2d_array[id0][0];
                this.step = 0;
            }
            else if (this.skinningentities.server_frame_int_array[this.integer_index] == this.int_2d_array[id0][1])
            {
                this.skinningentities.server_frame_int_array[this.integer_index] = this.int_2d_array[id0][0];
                this.step = 0;
            }

            return true;
        }

        return false;
    }

    public boolean setProtect(int id0, int id1, int id2, int id3, SkinningEntitiesProtect skinningentitiesprotect)
    {
        this.step = 1;
        switch (skinningentitiesprotect.state)
        {
            case 0:
            {
                if (this.skinningentities.server_frame_int_array[this.integer_index] < this.int_2d_array[id0][0] || this.skinningentities.server_frame_int_array[this.integer_index] > this.int_2d_array[id0][1])
                {
                    this.skinningentities.server_frame_int_array[this.integer_index] = this.int_2d_array[id0][0];
                    this.step = 0;
                }
                else if (this.skinningentities.server_frame_int_array[this.integer_index] == this.int_2d_array[id0][1])
                {
                    this.skinningentities.server_frame_int_array[this.integer_index] = this.int_2d_array[id1][0];
                    this.step = 0;
                    skinningentitiesprotect.main_state = 1;
                }

                break;
            }
            case 1:
            {
                if (this.skinningentities.server_frame_int_array[this.integer_index] < this.int_2d_array[id1][0] || this.skinningentities.server_frame_int_array[this.integer_index] > this.int_2d_array[id1][1])
                {
                    this.skinningentities.server_frame_int_array[this.integer_index] = this.int_2d_array[id1][0];
                    this.step = 0;
                }
                else if (this.skinningentities.server_frame_int_array[this.integer_index] == this.int_2d_array[id1][1])
                {
                    this.skinningentities.server_frame_int_array[this.integer_index] = this.int_2d_array[id1][0];
                    this.step = 0;
                }

                break;
            }
            case 2:
            {
                if (this.skinningentities.server_frame_int_array[this.integer_index] < this.int_2d_array[id2][0] || this.skinningentities.server_frame_int_array[this.integer_index] > this.int_2d_array[id2][1])
                {
                    this.skinningentities.server_frame_int_array[this.integer_index] = this.int_2d_array[id2][0];
                    this.step = 0;
                }
                else if (this.skinningentities.server_frame_int_array[this.integer_index] == this.int_2d_array[id2][1])
                {
                    this.skinningentities.server_frame_int_array[this.integer_index] = this.int_2d_array[id2][0];
                    this.step = 0;
                    skinningentitiesprotect.main_state = 1;
                }

                break;
            }
            case 3:
            {
                if (this.skinningentities.server_frame_int_array[this.integer_index] < this.int_2d_array[id3][0] || this.skinningentities.server_frame_int_array[this.integer_index] > this.int_2d_array[id3][1])
                {
                    this.skinningentities.server_frame_int_array[this.integer_index] = this.int_2d_array[id3][0];
                    this.step = 0;
                }
                else if (this.skinningentities.server_frame_int_array[this.integer_index] == this.int_2d_array[id3][1])
                {
//                    this.skinningentities.server_frame_int_array[this.integer_index] = this.int_2d_array[id3][0];
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
