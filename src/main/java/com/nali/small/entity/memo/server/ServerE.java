package com.nali.small.entity.memo.server;

import com.nali.da.IBothDaE;
import com.nali.list.entity.si.SIEFindMove;
import com.nali.list.network.method.client.CCI;
import com.nali.small.chunk.ChunkLoader;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.IBothE;
import com.nali.small.entity.memo.client.render.FRenderE;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.small.entity.memo.server.si.SIData;
import com.nali.system.bytes.ByteReader;
import com.nali.system.bytes.ByteWriter;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

import static com.nali.Nali.error;
import static com.nali.Nali.warn;

public abstract class ServerE
<
	BD extends IBothDaE,
	E extends Entity,
	I extends IMixE<BD, E>,
	MS extends MixSIE<BD, E, I, ?>
> implements IBothE<E>
{
//	public static Map<UUID, ServerE> S_MAP;
	public static Map<Long, ServerE> S_MAP;

	public I i;
	public MS ms;

	public WorldServer worldserver;

//	public UUID uuid;
	public float scale;
//	public byte[] sync_byte_array;

//	THREAD.start();
//	public static Thread THREAD = new Thread(() ->
//	{
//		while (true)
//		{
//			long startTime = System.currentTimeMillis();
//
//			Nali.LOGGER.info("T " + System.currentTimeMillis());
//
//			long elapsed_time = System.currentTimeMillis() - startTime;
//			long sleep_time = Math.max(0, 50 - elapsed_time);
//			try
//			{
//				Thread.sleep(sleep_time);
//			}
//			catch (InterruptedException e)
//			{
//				error(e);
//			}
//		}
//	});

	public ServerE(I i)
	{
		this.i = i;
		this.worldserver = (WorldServer)i.getE().world;
		BD bd = this.i.getBD();
//		this.sync_byte_array = new byte[bd.E_MaxSync()];
		this.scale = bd.E_Scale();
	}

	@Override
	public void onUpdate()
	{
//		E e = this.i.getE();
//		EntityDataManager entitydatamanager = e.getDataManager();
//		DataParameter<Byte>[] byte_dataparameter_array = this.i.getByteDataParameterArray();

//		for (int i = 0; i < byte_dataparameter_array.length; ++i)
//		{
//			entitydatamanager.set(byte_dataparameter_array[i], this.sync_byte_array[i]);
//		}

		this.updateServer();
//		e.width = 0.5F;
//		e.height = 0.5F;

//		UUID uuid = e.getUniqueID();
//
//		if (!uuid.equals(this.uuid))
//		{
//			if (this.uuid != null)
//			{
//				S_MAP.remove(this.uuid);
//			}
//
//			S_MAP.put(uuid, this);
//			this.uuid = uuid;
//		}

		this.ms.update();
	}

	public int size()
	{
		return this.i.getByteDataParameterArray().length/* + this.i.getFloatDataParameterArray().length * 4 + this.i.getIntegerDataParameterArray().length * 4 */+ this.ms.size();
	}

	public SIData genSIData()
	{
		SIData sidata = new SIData();

		EntityDataManager entitydatamanager = this.i.getE().getDataManager();
		DataParameter<Byte>[] byte_dataparameter_array = this.i.getByteDataParameterArray();
//		DataParameter<Float>[] float_dataparameter_array = this.i.getFloatDataParameterArray();
//		DataParameter<Integer>[] integer_dataparameter_array = this.i.getIntegerDataParameterArray();

		sidata.byte_array = new byte[this.size()];

		for (DataParameter<Byte> byte_dataparameter : byte_dataparameter_array)
		{
			sidata.byte_array[sidata.index++] = entitydatamanager.get(byte_dataparameter);
		}

//		for (DataParameter<Float> float_dataparameter : float_dataparameter_array)
//		{
//			ByteWriter.set(sidata.byte_array, entitydatamanager.get(float_dataparameter), sidata.rg);
//			sidata.rg += 4;
//		}
//
//		for (DataParameter<Integer> integer_dataparameter : integer_dataparameter_array)
//		{
//			ByteWriter.set(sidata.byte_array, entitydatamanager.get(integer_dataparameter), sidata.rg);
//			sidata.rg += 4;
//		}

		this.ms.writeFile(sidata);
		this.writeFile(sidata);

		return sidata;
	}

	public abstract void writeFile(SIData sidata);
	@Override
	public void writeFile()
	{
		try
		{
			int dimension = this.worldserver.provider.getDimension();
			File file = new File(this.worldserver.getSaveHandler().getWorldDirectory(), "nali/entity/" + dimension);
			file.mkdirs();

			Entity e = this.i.getE();
			byte[] chunk_byte_array = new byte[4 + 8/* + 4*/];
			ByteWriter.set(chunk_byte_array, this.worldserver.getWorldType().getId(), 0);
			ByteWriter.set(chunk_byte_array, e.getPosition().toLong(), 4);

			Files.write(new File(file, e.getUniqueID().toString()).toPath(), chunk_byte_array);

			file = new File(file, "data");
			file.mkdirs();

			SIData sidata = this.genSIData();
			Files.write(new File(file, e.getUniqueID().toString()).toPath(), sidata.byte_array);
		}
		catch (IOException ex)
		{
			error(ex);
		}
	}

	public void readSIData(SIData sidata)
	{
		E e = this.i.getE();
		EntityDataManager entitydatamanager = e.getDataManager();
		DataParameter<Byte>[] byte_dataparameter_array = this.i.getByteDataParameterArray();
//					DataParameter<Float>[] float_dataparameter_array = this.i.getFloatDataParameterArray();
//					DataParameter<Integer>[] integer_dataparameter_array = this.i.getIntegerDataParameterArray();

		this.scale = ByteReader.getFloat(sidata.byte_array, sidata.index);
		for (DataParameter<Byte> byte_dataparameter : byte_dataparameter_array)
		{
			entitydatamanager.set(byte_dataparameter, sidata.byte_array[sidata.index++]);
		}

//					this.scale = ByteReader.getFloat(sidata.byte_array, sidata.rg);
//					for (DataParameter<Float> float_dataparameter : float_dataparameter_array)
//					{
//						entitydatamanager.set(float_dataparameter, ByteReader.getFloat(sidata.byte_array, sidata.rg));
//						sidata.rg += 4;
//					}
//
//					for (DataParameter<Integer> integer_dataparameter : integer_dataparameter_array)
//					{
//						entitydatamanager.set(integer_dataparameter, ByteReader.getInt(sidata.byte_array, sidata.rg));
//						sidata.rg += 4;
//					}

		this.ms.readFile(sidata);
		this.readFile(sidata);
	}

	public abstract void readFile(SIData sidata);
	public abstract void initFile();
	@Override
	public void readFile()
	{
		if ((this.ms.flag & MixSIE.B_INIT) == 0)
		{
			this.ms.flag |= MixSIE.B_INIT;

			Entity e = this.i.getE();
			File file = new File(this.worldserver.getSaveHandler().getWorldDirectory(), "nali/entity/" + e.world.provider.getDimension() + "/data/" + e.getUniqueID());

//			warn("world " + e.world);
//			warn("world.provider " + e.world.provider);
//			warn("S_MAP " + S_MAP);
//			warn("e " + e);
//			warn("this " + this);
			S_MAP.put((long)e.world.provider.getDimension() << 32 | e.getEntityId(), this);

			try
			{
				if (file.exists())
				{
					SIData sidata = new SIData();
					sidata.byte_array = Files.readAllBytes(file.toPath());
					this.readSIData(sidata);
				}
				else
				{
					this.ms.initFile();
					this.initFile();
				}
			}
			catch (Exception ex)
			{
				warn(ex);
				file.delete();
			}
		}
	}

//	@Override
//	public void writeEntityToNBT(NBTTagCompound nbttagcompound)
//	{
////		if (this.owner_uuid != null)
////		{
////			byte[] byte_array = new byte[16];
////			ByteWriter.set(byte_array, this.owner_uuid, 0);
////			nbttagcompound.setByteArray("owner_uuid", byte_array);
////		}
//
////		this.entitiesaimemory.writeNBT(nbttagcompound);
//
////		if (this.main_work_byte_array == null)
////		{
////			this.main_work_byte_array = new byte[this.workbytes.MAX_WORKS()];
////			this.initWorkBytes();
////		}
//
////		if (!this.sus_init)
////		if ((this.ms.state & 4) == 0)
////		{
////			this.main_work_byte_array[this.workbytes.LOCK_INVENTORY() / 8] ^= (byte)Math.pow(2, this.workbytes.LOCK_INVENTORY() % 8);
////			this.main_work_byte_array[this.workbytes.LOCK_DAMAGE() / 8] ^= (byte)Math.pow(2, this.workbytes.LOCK_DAMAGE() % 8);
////
////			if (this.workbytes.CARE_OWNER() != -1)
////			{
////				this.main_work_byte_array[this.workbytes.CARE_OWNER() / 8] ^= (byte)Math.pow(2, this.workbytes.CARE_OWNER() % 8);
////			}
////
////			nbttagcompound.setFloat("float_0", this.i.getBothData().Scale());
////			this.initWriteEntityToNBT(nbttagcompound);
//////			nbttagcompound.setByteArray("work_bytes", this.main_work_byte_array);
////		}
//
////		nbttagcompound.setByteArray("work_bytes", this.main_work_byte_array);
//
////		nbttagcompound.setBoolean("sus_init", true);
//
//		this.ms.writeNBT(nbttagcompound);
////		this.statle.writeNBT(nbttagcompound);
//	}
//
//	@Override
//	public void readEntityFromNBT(NBTTagCompound nbttagcompound)
//	{
////		byte[] work_bytes = nbttagcompound.getByteArray("work_bytes");
////		if (work_bytes.length == this.main_work_byte_array.length)
////		{
////			this.main_work_byte_array = work_bytes;
////		}
//
////		if (nbttagcompound.hasKey("owner_uuid", 7))
////		{
////			this.owner_uuid = ByteReader.getUUID(nbttagcompound.getByteArray("owner_uuid"), 0);
////		}
//
////		this.entitiesaimemory.readNBT(nbttagcompound);
//		this.ms.readNBT(nbttagcompound);
//
////		this.sus_init = nbttagcompound.hasKey("sus_init");
////		if (!this.sus_init)
////		if ((this.ms.state & 4) == 0)
////		{
////			this.main_work_byte_array[this.workbytes.LOCK_INVENTORY() / 8] ^= (byte)Math.pow(2, this.workbytes.LOCK_INVENTORY() % 8);
////			this.main_work_byte_array[this.workbytes.LOCK_DAMAGE() / 8] ^= (byte)Math.pow(2, this.workbytes.LOCK_DAMAGE() % 8);
////
////			if (this.workbytes.CARE_OWNER() != -1)
////			{
////				this.main_work_byte_array[this.workbytes.CARE_OWNER() / 8] ^= (byte)Math.pow(2, this.workbytes.CARE_OWNER() % 8);
////			}
////
////			this.i.getE().getDataManager().set(this.i.getFloatDataParameterArray()[0], this.i.getBothData().Scale());
////			this.initReadEntityFromNBT(nbttagcompound);
////
////			this.sus_init = true;
////		}
//
////		this.statle.readNBT(nbttagcompound);
//	}

//	@Override
	public void remove()
	{
		Entity e = this.i.getE();
		World world = e.world;
		long key = (long)world.provider.getDimension() << 32 | e.getEntityId();

//		UUID uuid = this.i.getE().getUniqueID();
		SIEFindMove.SIEFINDMOVE_MAP.remove(key);
		ChunkLoader.removeChunk(key, e.getUniqueID(), this.worldserver.getSaveHandler().getWorldDirectory());
		S_MAP.remove(key);
	}

//	public Entity getOwner()
//	{
//		if (this.owner_uuid == null)
//		{
//			return null;
//		}
//
//		return this.worldserver.getEntityFromUuid(this.owner_uuid);
//	}

	public Material getMaterial(BlockPos blockpos)
	{
		IBlockState temp_iblockstate = this.i.getE().world.getBlockState(blockpos);
		return temp_iblockstate.getMaterial();
	}

	public void setCCI(byte[] byte_array, byte i)
	{
		E e = this.i.getE();
		byte_array[0] = CCI.ID;
		ByteWriter.set(byte_array, (long)e.world.provider.getDimension() << 32 | e.getEntityId(), 1);
		byte_array[1 + 8] = i;
	}

//	public boolean isWork(byte rg)
//	{
//		for (byte i = this.workbytes.SIT(); i < rg; ++i)
//		{
//			if ((this.current_work_byte_array[i / 8] >> i % 8 & 1) == 1)
//			{
//				return false;
//			}
//		}
//
//		return (this.current_work_byte_array[rg / 8] >> rg % 8 & 1) == 1;
//	}

//	public boolean isWorkBypass(byte rg, byte[] bypass_byte_array)
//	{
//		for (byte i = this.workbytes.SIT(); i < rg; ++i)
//		{
//			boolean on_bypass = false;
//			for (byte bypass : bypass_byte_array)
//			{
//				if (i == bypass)
//				{
//					on_bypass = true;
//					break;
//				}
//			}
//
//			if (!on_bypass && (this.current_work_byte_array[i / 8] >> i % 8 & 1) == 1)
//			{
//				return false;
//			}
//		}
//
//		return (this.current_work_byte_array[rg / 8] >> rg % 8 & 1) == 1;
//	}

//	public void initWriteEntityToNBT(NBTTagCompound nbttagcompound)
//	{
//		this.initWorkBytes();
//	}
//
//	public void initReadEntityFromNBT(NBTTagCompound nbttagcompound)
//	{
//		this.initWorkBytes();
//	}

//	@SideOnly(Side.CLIENT)
//	@Override
//	public void setGlowing(boolean b)
//	{
//	}

//	@SideOnly(Side.CLIENT)
	@Override
	public void onReadNBT()
	{
	}

//	@SideOnly(Side.CLIENT)
//	@Override
//	public void setShouldRender(boolean b)
//	{
//	}

	@SideOnly(Side.CLIENT)
	@Override
	public void doRender(FRenderE renderE, double v, double v1, double v2, float v3)
	{
	}

//	@SideOnly(Side.CLIENT)
//	@Override
//	public void setUUID(UUID uuid)
//	{
//	}

//	@SideOnly(Side.CLIENT)
//	@Override
//	public void playSound(int i)
//	{
//	}

//	@Override
//	public I getI()
//	{
//		return this.i;
//	}

//	public abstract A createA();

//	public abstract int[][] getFrame2DIntArray();

	public abstract void updateServer();
}
