package com.nali.ilol.entities.object;

import com.nali.data.BothData;
import com.nali.data.ObjectData;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class ObjectEntities extends Entity
{
    public Object client_object;
    public BothData bothdata;
    public boolean server_sus_init;

    public ObjectEntities(World worldIn)
    {
        super(worldIn);

        this.bothdata = this.createBothData();
        float scale = this.bothdata.Scale();
        this.width = this.bothdata.Width() * scale;
        this.height = this.bothdata.Height() * scale;

        if (world.isRemote)
        {
            this.client_object = this.createClientObject();
        }
        else
        {
            this.getDataManager().set(this.getFloatDataParameterArray()[0], scale);
        }
    }

    @Override
    public void entityInit()
    {
        DataParameter<Byte>[] byte_dataparameter_array = this.getByteDataParameterArray();
        for (DataParameter<Byte> byte_dataparameter : byte_dataparameter_array)
        {
            this.dataManager.register(byte_dataparameter, (byte) 0);
        }

        DataParameter<Integer>[] integer_dataparameter_array = this.getIntegerDataParameterArray();
        for (DataParameter<Integer> integer_dataparameter : integer_dataparameter_array)
        {
            this.dataManager.register(integer_dataparameter, 0);
        }

        DataParameter<Float>[] float_dataparameter_array = this.getFloatDataParameterArray();
        for (DataParameter<Float> float_dataparameter : float_dataparameter_array)
        {
            this.dataManager.register(float_dataparameter, 0.0F);
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        EntityDataManager entitydatamanager = this.getDataManager();

        int i = 0;
        DataParameter<Byte>[] byte_dataparameter_array = this.getByteDataParameterArray();
        for (DataParameter<Byte> byte_dataparameter : byte_dataparameter_array)
        {
            nbttagcompound.setByte("byte_" + i++, entitydatamanager.get(byte_dataparameter));
        }
        i = 0;

        DataParameter<Integer>[] integer_dataparameter_array = this.getIntegerDataParameterArray();
        for (DataParameter<Integer> integer_dataparameter : integer_dataparameter_array)
        {
            nbttagcompound.setInteger("int_" + i++, entitydatamanager.get(integer_dataparameter));
        }
        i = 0;

        DataParameter<Float>[] float_dataparameter_array = this.getFloatDataParameterArray();
        for (DataParameter<Float> float_dataparameter : float_dataparameter_array)
        {
            nbttagcompound.setFloat("float_" + i++, entitydatamanager.get(float_dataparameter));
        }

        if (!this.server_sus_init)
        {
            this.initWriteEntityToNBT(nbttagcompound);
        }
        nbttagcompound.setBoolean("sus_init", true);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        EntityDataManager entitydatamanager = this.getDataManager();

        int i = 0;
        DataParameter<Byte>[] byte_dataparameter_array = this.getByteDataParameterArray();
        for (DataParameter<Byte> byte_dataparameter : byte_dataparameter_array)
        {
            entitydatamanager.set(byte_dataparameter, nbttagcompound.getByte("byte_" + i++));
        }
        i = 0;

        DataParameter<Integer>[] integer_dataparameter_array = this.getIntegerDataParameterArray();
        for (DataParameter<Integer> integer_dataparameter : integer_dataparameter_array)
        {
            entitydatamanager.set(integer_dataparameter, nbttagcompound.getInteger("int" + i++));
        }
        i = 0;

        DataParameter<Float>[] float_dataparameter_array = this.getFloatDataParameterArray();
        for (DataParameter<Float> float_dataparameter : float_dataparameter_array)
        {
            entitydatamanager.set(float_dataparameter, nbttagcompound.getFloat("float_" + i++));
        }

        this.server_sus_init = nbttagcompound.hasKey("sus_init");

        if (!this.server_sus_init)
        {
            this.initReadEntityFromNBT();
            this.server_sus_init = true;
        }
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
        this.updateBox();

        if (this.getEntityWorld().isRemote)
        {
            this.updateClientObject();
        }
    }

    @Override
    public double getYOffset()
    {
        return 0.3D;
    }

    public void updateClientObject()
    {
        ObjectData objectdata = (ObjectData)this.client_object;
        EntityDataManager entitydatamanager = this.getDataManager();

        objectdata.float_array[0] = entitydatamanager.get(this.getFloatDataParameterArray()[0]);

        DataParameter<Integer>[] integer_dataparameter = this.getIntegerDataParameterArray();
        for (int i = 0; i < objectdata.texture_index_int_array.length; ++i)
        {
            objectdata.texture_index_int_array[i] = entitydatamanager.get(integer_dataparameter[i]);
        }
    }

    public void updateBox()
    {
        float scale = this.getDataManager().get(this.getFloatDataParameterArray()[0]);
        this.width = this.bothdata.Width() * scale;
        this.height = this.bothdata.Height() * scale;
    }

    public abstract void initWriteEntityToNBT(NBTTagCompound nbttagcompound);
    public abstract void initReadEntityFromNBT();
    public abstract BothData createBothData();
    public abstract DataParameter<Byte>[] getByteDataParameterArray();
    public abstract DataParameter<Integer>[] getIntegerDataParameterArray();
    public abstract DataParameter<Float>[] getFloatDataParameterArray();
    @SideOnly(Side.CLIENT)
    public abstract Object createClientObject();
}
