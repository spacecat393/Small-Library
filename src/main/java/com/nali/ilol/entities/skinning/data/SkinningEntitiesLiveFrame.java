package com.nali.ilol.entities.skinning.data;

import com.nali.ilol.entities.bytes.SkinningEntitiesBytes;
import com.nali.ilol.entities.skinning.SkinningEntities;

import java.util.function.Supplier;

public class SkinningEntitiesLiveFrame
{
    public SkinningEntities skinningentities;
    public int integer_index;
    public int[][] int_2d_array; // start end
    public Supplier<Boolean>[] condition_boolean_supplier_array;

    public SkinningEntitiesLiveFrame(SkinningEntities skinningentities, int integer_index, int[][] int_2d_array)
    {
        this.skinningentities = skinningentities;
        this.integer_index = integer_index;
        this.int_2d_array = int_2d_array;
    }

    public void onUpdate()
    {
        for (int i = 0; i < this.condition_boolean_supplier_array.length; ++i)
        {
            if (this.condition_boolean_supplier_array[i].get())
            {
                if (this.skinningentities.server_frame_int_array[this.integer_index] < this.int_2d_array[i][1])
                {
                    this.skinningentities.getDataManager().set(this.skinningentities.getIntegerDataParameterArray()[this.skinningentities.bothdata.MaxPart() + this.integer_index], this.skinningentities.server_frame_int_array[this.integer_index] + 1);
                }

                return;
            }
        }
    }

    /**0-FLoop
     * 1-TLoop
     * 2-FLoopOffSet*/
    public boolean setCondition(byte state, int id0, int id1, boolean result)
    {
        if (result)
        {
            SkinningEntities skinningentities = this.skinningentities;

            switch (state)
            {
                case 0:
                {
                    if (skinningentities.server_frame_int_array[this.integer_index] == this.int_2d_array[id0][1] - 1)
                    {
                        skinningentities.server_frame_int_array[this.integer_index] = this.int_2d_array[id0][1];
                    }
                    else if (skinningentities.server_frame_int_array[this.integer_index] < this.int_2d_array[id0][0] || skinningentities.server_frame_int_array[this.integer_index] > this.int_2d_array[id0][1])
                    {
                        skinningentities.server_frame_int_array[this.integer_index] = this.int_2d_array[id0][0];
                    }

                    break;
                }
                case 1:
                {
                    if (skinningentities.server_frame_int_array[this.integer_index] < this.int_2d_array[id0][0] || skinningentities.server_frame_int_array[this.integer_index] > this.int_2d_array[id0][1] - 1)
                    {
                        skinningentities.server_frame_int_array[this.integer_index] = this.int_2d_array[id0][0];
                    }

                    break;
                }
                case 2:
                {
                    result = skinningentities.server_frame_int_array[this.integer_index] > this.int_2d_array[id0][0] && skinningentities.server_frame_int_array[this.integer_index] < this.int_2d_array[id1][1];

                    if (result)
                    {
                        if (skinningentities.server_frame_int_array[this.integer_index] > this.int_2d_array[id0][1] - 1 && skinningentities.server_frame_int_array[this.integer_index] < this.int_2d_array[id1][0])
                        {
                            skinningentities.server_frame_int_array[this.integer_index] = this.int_2d_array[id1][0];
                        }
                    }

                    break;
                }
                default:
                {
                    break;
                }
            }
        }

        return result;
    }

    /**0-Die
     * 1-Sit
     * 2-OnAttack
     * 3-Run
     * 4-EndRun
     * 5-Walk
     * 6-Ready
     * 7-HardIdle
     * 8-SoftIdle*/
    public static void set(SkinningEntitiesLiveFrame skinningentitiesliveframe, byte hard_idle)
    {
        SkinningEntities skinningentities = skinningentitiesliveframe.skinningentities;
        SkinningEntitiesBytes skinningentitiesbytes = skinningentities.skinningentitiesbytes;
        byte[] server_work_byte_array = skinningentities.server_work_byte_array;
        skinningentitiesliveframe.condition_boolean_supplier_array = new Supplier[]
        {
            () -> skinningentitiesliveframe.setCondition((byte)0, 0, -1, skinningentities.isZeroMove()),
            () -> skinningentitiesliveframe.setCondition((byte)1, 1, -1, server_work_byte_array[skinningentitiesbytes.SIT()] == 1),
            () -> skinningentitiesliveframe.setCondition((byte)1, 2, -1, server_work_byte_array[skinningentitiesbytes.ON_ATTACK()] == 1),
                //pop up tank
            () -> skinningentitiesliveframe.setCondition((byte)1, 3, -1, skinningentities.current_server_work_byte_array[skinningentitiesbytes.ATTACK()] == 1 && skinningentities.moveForward != 0),
            () -> skinningentitiesliveframe.setCondition((byte)2, 3, 4, true),
            () -> skinningentitiesliveframe.setCondition((byte)1, 5, -1, skinningentities.moveForward != 0),
            () -> skinningentitiesliveframe.setCondition((byte)1, 6, -1, server_work_byte_array[skinningentitiesbytes.READY()] == 1),
            () -> skinningentitiesliveframe.setCondition(hard_idle, 7, -1, skinningentities.current_server_work_byte_array[skinningentitiesbytes.ATTACK()] == 1),
            () -> skinningentitiesliveframe.setCondition((byte)1, 8, -1, true)
        };
    }
}
