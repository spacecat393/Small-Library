package com.nali.ilol.entities.skinning.data;

import com.nali.ilol.entities.skinning.SkinningEntities;

import java.util.function.Supplier;

public class SkinningEntitiesLiveFrame
{
    public SkinningEntities skinningentities;
    public int integer_index; // integer
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
        for (int i = 0; i < condition_boolean_supplier_array.length; ++i)
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

    /*
    0 die
    1 sit
    1 move
    2 end move
    3 hold
    */
    public static void set(SkinningEntitiesLiveFrame skinningentitiesliveframe)
    {
        SkinningEntities skinningentities = skinningentitiesliveframe.skinningentities;
//        EntityDataManager entitydatamanager = skinningentities.getDataManager();
//        DataParameter<Integer> integer_dataparameter = skinningentities.getIntegerDataParameterArray()[skinningentities.getMaxPart() + skinningentitiesliveframe.integer_index];

        skinningentitiesliveframe.condition_boolean_supplier_array = new Supplier[]
        {
            () ->
            {
                boolean result = skinningentities.isZeroMove();// don't loop die
                if (result)
                {
                    if (skinningentities.server_frame_int_array[skinningentitiesliveframe.integer_index] == skinningentitiesliveframe.int_2d_array[0][1] - 1)
                    {
                        skinningentities.server_frame_int_array[skinningentitiesliveframe.integer_index] = skinningentitiesliveframe.int_2d_array[0][1];
                    }
                    else if (skinningentities.server_frame_int_array[skinningentitiesliveframe.integer_index] < skinningentitiesliveframe.int_2d_array[0][0] || skinningentities.server_frame_int_array[skinningentitiesliveframe.integer_index] > skinningentitiesliveframe.int_2d_array[0][1])
                    {
                        skinningentities.server_frame_int_array[skinningentitiesliveframe.integer_index] = skinningentitiesliveframe.int_2d_array[0][0];
                    }
//                    else if (skinningentities.server_frame_int_array[skinningentitiesliveframe.integer_index] < skinningentitiesliveframe.int_2d_array[0][1] - 1)
//                    {
//                        skinningentities.server_frame_int_array[skinningentitiesliveframe.integer_index] += 1;
//                    }
                }

                return result;
            },
            () ->
            {
                boolean result = skinningentities.server_work_byte_array[skinningentities.skinningentitiesbytes.SIT()] == 1;
                if (result)
                {
                    if (skinningentities.server_frame_int_array[skinningentitiesliveframe.integer_index] < skinningentitiesliveframe.int_2d_array[1][0] || skinningentities.server_frame_int_array[skinningentitiesliveframe.integer_index] > skinningentitiesliveframe.int_2d_array[1][1] - 1)// loop sit
                    {
                        skinningentities.server_frame_int_array[skinningentitiesliveframe.integer_index] = skinningentitiesliveframe.int_2d_array[1][0];
                    }
//                    else if (skinningentities.server_frame_int_array[skinningentitiesliveframe.integer_index] < skinningentitiesliveframe.int_2d_array[1][1])
//                    {
//                        skinningentities.server_frame_int_array[skinningentitiesliveframe.integer_index] += 1;
//                    }
                }

                return result;
            },
            () ->
            {
                boolean result = skinningentities.moveForward != 0; // loop move
                if (result)
                {
                    if (skinningentities.server_frame_int_array[skinningentitiesliveframe.integer_index] < skinningentitiesliveframe.int_2d_array[2][0] || skinningentities.server_frame_int_array[skinningentitiesliveframe.integer_index] > skinningentitiesliveframe.int_2d_array[2][1] - 1)
                    {
                        skinningentities.server_frame_int_array[skinningentitiesliveframe.integer_index] = skinningentitiesliveframe.int_2d_array[2][0];
                    }
//                    else
//                    {
//                        skinningentities.server_frame_int_array[skinningentitiesliveframe.integer_index] += 1;
//                    }
                }

                return result;
            },
            () ->
            {
                boolean result = skinningentities.server_frame_int_array[skinningentitiesliveframe.integer_index] > skinningentitiesliveframe.int_2d_array[2][0] && skinningentities.server_frame_int_array[skinningentitiesliveframe.integer_index] < skinningentitiesliveframe.int_2d_array[3][1];// don't loop end move
                if (result)
                {
                    if (skinningentities.server_frame_int_array[skinningentitiesliveframe.integer_index] > skinningentitiesliveframe.int_2d_array[2][1] - 1 && skinningentities.server_frame_int_array[skinningentitiesliveframe.integer_index] < skinningentitiesliveframe.int_2d_array[3][0])
                    {
                        skinningentities.server_frame_int_array[skinningentitiesliveframe.integer_index] = skinningentitiesliveframe.int_2d_array[3][0];
                    }
//                    else
//                    {
//                        skinningentities.server_frame_int_array[skinningentitiesliveframe.integer_index] += 1;
//                    }
                }

                return result;
            },
            () ->
            {
                if (skinningentities.server_frame_int_array[skinningentitiesliveframe.integer_index] < skinningentitiesliveframe.int_2d_array[4][0] || skinningentities.server_frame_int_array[skinningentitiesliveframe.integer_index] > skinningentitiesliveframe.int_2d_array[4][1] - 1)// loop hold
                {
                    skinningentities.server_frame_int_array[skinningentitiesliveframe.integer_index] = skinningentitiesliveframe.int_2d_array[4][0];
                }
//                else
//                {
//                    skinningentities.server_frame_int_array[skinningentitiesliveframe.integer_index] += 1;
//                }

                return true;
            }
        };
    }
}
