package com.nali.small.entities.skinning.ai;

import com.nali.small.entities.skinning.SkinningEntities;

import java.util.function.Supplier;

public class SkinningEntitiesLiveFrame extends SkinningEntitiesAI
{
    public int step = 1;
    public int integer_index;
    public int[][] int_2d_array; // start end
    public int[] attack_frame_int_array;
    public byte max_ammo = 16;
    public Supplier<Boolean>[] condition_boolean_supplier_array;

    public SkinningEntitiesLiveFrame(SkinningEntities skinningentities, int integer_index, int[][] int_2d_array, int[] attack_frame_int_array)
    {
        super(skinningentities);
        this.integer_index = integer_index;
        this.int_2d_array = int_2d_array;
        this.attack_frame_int_array = attack_frame_int_array;
    }

    @Override
    public void onUpdate()
    {
        for (Supplier<Boolean> boolean_supplier : this.condition_boolean_supplier_array)
        {
            if (boolean_supplier.get())
            {
                this.skinningentities.getDataManager().set(this.skinningentities.getIntegerDataParameterArray()[this.skinningentities.bothdata.MaxPart() + this.integer_index], this.skinningentities.server_frame_int_array[this.integer_index] + this.step);
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
            if (this.skinningentities.server_frame_int_array[this.integer_index] == this.int_2d_array[id0][1] - 1)
            {
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

    public boolean setTLoop(int id0, boolean result)
    {
        if (result)
        {
            return this.setTLoop(id0);
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

    public boolean setShoot(int id0, int id1, int id2, int id3, SkinningEntitiesFrameAI skinningentitiesframeai)
    {
        this.step = 1;
        byte state = skinningentitiesframeai.getByte();
//        EntityDataManager entitydatamanager = this.skinningentities.getDataManager();
//        DataParameter<Byte> byte_dataparameter = this.skinningentities.getByteDataParameterArray()[this.skinningentities.skinningentitiesbytes.AMMO()];
//        byte ammo = entitydatamanager.get(byte_dataparameter);
        byte ammo = this.skinningentities.main_server_work_byte_array[this.skinningentities.skinningentitiesbytes.AMMO()];
        if (ammo <= 0)
        {
            if (this.checkShoot(id0, id1, id2))
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
                    if (this.skinningentities.server_frame_int_array[this.integer_index] == this.int_2d_array[id3][1] -1)
                    {
                        this.skinningentities.main_server_work_byte_array[this.skinningentities.skinningentitiesbytes.AMMO()] = this.max_ammo;
//                        entitydatamanager.set(byte_dataparameter, this.max_ammo);
                    }

                    return true;
                }
            }
        }
        else if (state == 0 || state == 1)
        {
            //start gun
            if (this.skinningentities.server_frame_int_array[this.integer_index] < this.int_2d_array[id0][0] || this.skinningentities.server_frame_int_array[this.integer_index] > this.int_2d_array[id1][1])
            {
                this.skinningentities.server_frame_int_array[this.integer_index] = this.int_2d_array[id0][0];
                this.step = 0;
                return true;
            }
            else if (this.skinningentities.server_frame_int_array[this.integer_index] >= this.int_2d_array[id0][0] && this.skinningentities.server_frame_int_array[this.integer_index] < this.int_2d_array[id1][1])
            {
                if (this.skinningentities.server_frame_int_array[this.integer_index] == this.int_2d_array[id1][1] -1)
                {
                    this.skinningentities.server_frame_int_array[this.integer_index] = this.int_2d_array[id1][0];
                    this.step = 0;
                    return true;
                }

//                skinningentitiesframeai.setByte((byte)1);
                for (int attack_frame : this.attack_frame_int_array)
                {
                    if (this.skinningentities.server_frame_int_array[this.integer_index] == attack_frame)
                    {
                        this.skinningentities.main_server_work_byte_array[this.skinningentities.skinningentitiesbytes.AMMO()] = (byte)(ammo - 1);
//                        entitydatamanager.set(byte_dataparameter, (byte)(ammo - 1));
                        skinningentitiesframeai.setByte((byte)1);
                        break;
                    }
                }

                return true;
            }
        }
        else// if (state > 1)
        {
            //if gun / shoot -> end gun
            return this.checkShoot(id0, id1, id2);
        }

        return false;
    }

    public boolean checkShoot(int id0, int id1, int id2)
    {
        if (this.skinningentities.server_frame_int_array[this.integer_index] >= this.int_2d_array[id0][0] && this.skinningentities.server_frame_int_array[this.integer_index] < this.int_2d_array[id0][1])
        {
//            this.skinningentities.server_frame_int_array[this.integer_index] = this.int_2d_array[id2][0];
//            this.step = 0;
//            return true;
            if (this.skinningentities.server_frame_int_array[this.integer_index] == this.int_2d_array[id0][1] - 1)
            {
                this.skinningentities.server_frame_int_array[this.integer_index] = this.int_2d_array[id1][0];
                this.step = 0;
                return true;
            }
            else
            {
                return true;
            }
        }
        else if (this.skinningentities.server_frame_int_array[this.integer_index] >= this.int_2d_array[id1][0] && this.skinningentities.server_frame_int_array[this.integer_index] < this.int_2d_array[id1][1])
        {
//            this.skinningentities.server_frame_int_array[this.integer_index] = this.int_2d_array[id2][0];
//            this.step = 0;
//            return true;
            if (this.skinningentities.server_frame_int_array[this.integer_index] == this.int_2d_array[id1][1] - 1)
            {
                this.skinningentities.server_frame_int_array[this.integer_index] = this.int_2d_array[id2][0];
                this.step = 0;
                return true;
            }
            else
            {
                return true;
            }
        }
        else if (this.skinningentities.server_frame_int_array[this.integer_index] >= this.int_2d_array[id2][0] && this.skinningentities.server_frame_int_array[this.integer_index] < this.int_2d_array[id2][1])
        {
            if (this.skinningentities.server_frame_int_array[this.integer_index] == this.int_2d_array[id2][1] - 1)
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        else
        {
            return false;
        }
    }
}
