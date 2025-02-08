package com.nali.list.entity.si;

import com.nali.da.IBothDaE;
import com.nali.list.network.message.ClientMessage;
import com.nali.list.network.method.client.CSetTarget;
import com.nali.list.network.method.client.CSetTroublemaker;
import com.nali.network.NetworkRegistry;
import com.nali.small.entity.EntityRegistry;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.small.entity.memo.server.si.SI;
import com.nali.small.entity.memo.server.si.SIData;
import com.nali.small.mixin.IMixinWorldServer;
import com.nali.system.bytes.ByteReader;
import com.nali.system.bytes.ByteWriter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;

import java.util.*;

public class SIEArea
<
	BD extends IBothDaE,
	E extends Entity,
	I extends IMixE<BD, E>,
	S extends ServerE<BD, E, I, MS>,
	MS extends MixSIE<BD, E, I, S>
> extends SI<BD, E, I, S, MS>
{
	public static byte ID;

	public SIEOwner<BD, E, I, S, MS> sieowner;
	public Map<UUID, Entity> entity_map;
	public Collection<Entity> entity_collection;

	public List<EntityXPOrb> xp_entity_list = new ArrayList();
	public List<EntityItem> item_entity_list = new ArrayList();
	public List<Entity> all_entity_list = new ArrayList();//target
	public List<Entity> out_entity_list = new ArrayList();//not_target

	public List<Integer> troublemaker_list = new ArrayList();
	public List<Integer> target_list = new ArrayList();

	public final static byte I_BYPASS_ARMY = 1;
	public final static byte I_PUT_ANIMAL = 2;
	public final static byte I_PUT_PLAYER = 4;
	public final static byte I_PUT_OWNER = 8;
	public final static byte I_PUT_OTHER_TAMEABLE = 16;
	public final static byte I_PUT_OWNER_TAMEABLE = 32;
	public final static byte I_PUT_ALL_TAMEABLE = 64;
	public final static byte I_PUT_OBJECT = (byte)128;
	public byte state;

	public SIEArea(S s)
	{
		super(s);
	}

	@Override
	public void init()
	{
		this.sieowner = (SIEOwner<BD, E, I, S, MS>)this.s.ms.si_map.get(SIEOwner.ID);
		this.entity_map = ((IMixinWorldServer)this.s.i.getE().world).entitiesByUuid();
	}

	@Override
	public void call()
	{
		switch (this.s.ms.byte_array[18])
		{
			case 0:
				this.addTarget();
				break;
			case 1:
				this.clearTarget();
				break;
			case 2:
				this.addTroublemaker();
				break;
			case 3:
				this.clearTroublemaker();
				break;
			case 4:
//				this.fetch();
		}
	}

	public void addTarget()
	{
		byte[] byte_array = this.s.ms.byte_array;
		for (int index = 1 + 8 + 1 + 1; index < byte_array.length; index += 4)
		{
			int id = ByteReader.getInt(byte_array, index);
//							int id = Integer.parseInt(new_string);

			if (id >= EntityRegistry.ENTITIES_CLASS_LIST.size())
			{
				continue;
			}

			boolean result = true;
			for (int i : this.target_list)
			{
				if (i == id)
				{
					result = false;
					break;
				}
			}

			if (result)
			{
				this.target_list.add(id);
			}
		}
	}

	public void removeTarget()
	{
//						String string = new String(servermessage.data, 1 + 16, servermessage.data.length - (1 + 16));
//						String[] string_array = string.split(" ");

//						for (String new_string : string_array)
		byte[] byte_array = this.s.ms.byte_array;
		for (int x = 1 + 8 + 1 + 1; x < byte_array.length; x += 4)
		{
			int id = ByteReader.getInt(byte_array, x);
//							int id = Integer.parseInt(new_string);

			int index = 0;
			for (int i : this.target_list)
			{
				if (i == id)
				{
					this.target_list.remove(index);
					break;
				}
				++index;
			}
		}
	}

	public void clearTarget()
	{
		this.target_list.clear();
	}

	public void addTroublemaker()
	{
		byte[] byte_array = this.s.ms.byte_array;
//						String string = new String(servermessage.data, 1 + 16, servermessage.data.length - (1 + 16));
//						String[] string_array = string.split(" ");

//						for (String new_string : string_array)
		for (int index = 1 + 8 + 1 + 1; index < byte_array.length; index += 4)
		{
			int id = ByteReader.getInt(byte_array, index);
//							int id = Integer.parseInt(new_string);

			if (id >= EntityRegistry.ENTITIES_CLASS_LIST.size())
			{
				continue;
			}

			boolean result = true;
			for (int i : this.troublemaker_list)
			{
				if (i == id)
				{
					result = false;
					break;
				}
			}

			if (result)
			{
				this.troublemaker_list.add(id);
			}
		}
	}

	public void removeTroublemaker()
	{
		//						String string = new String(servermessage.data, 1 + 16, servermessage.data.length - (1 + 16));
//						String[] string_array = string.split(" ");

//						for (String new_string : string_array)
		byte[] byte_array = this.s.ms.byte_array;
		for (int x = 1 + 8 + 1 + 1; x < byte_array.length; x += 4)
		{
			int id = ByteReader.getInt(byte_array, x);
//							int id = Integer.parseInt(new_string);

			int index = 0;
			for (int i : this.troublemaker_list)
			{
				if (i == id)
				{
					this.troublemaker_list.remove(index);
					break;
				}
				++index;
			}
		}
	}

	public void clearTroublemaker()
	{
		this.troublemaker_list.clear();
	}

//	public void set()
//	{
//		byte[] byte_array = this.s.ms.byte_array;
//		float id = ByteReader.getFloat(byte_array, 1 + 8 + 1 + 1);
//		float x = ByteReader.getFloat(byte_array, 1 + 8 + 1 + 1 + 4);
//
////		SmallSakuraType smallsakuratypes = this.s.ms.entityplayermp.getCapability(SmallSakuraSerializable.SMALLSAKURATYPES_CAPABILITY, null);
////		byte value = smallsakuratypes.get();
//		UUID player_uuid = this.s.ms.entityplayermp.getUniqueID();
//		byte value = PlayerData.SAKURA_MAP.getOrDefault(player_uuid, (byte)0);
//
//		if (id == 0.1F)
//		{
//			if (x == 1)
//			{
//				if (value >= 1)
//				{
////					smallsakuratypes.set((byte)(value - 1));
//					PlayerData.SAKURA_MAP.put(player_uuid, (byte)(value - 1));
//					this.flag |= 4;
//				}
//			}
//			else
//			{
//				this.flag &= 255 - 4;
//			}
//		}
//		else if (id == 0.2F)
//		{
//			if (x == 1)
//			{
//				if (value >= 1)
//				{
////					smallsakuratypes.set((byte)(value - 1));
//					PlayerData.SAKURA_MAP.put(player_uuid, (byte)(value - 1));
//					this.flag |= 8;
//				}
//			}
//			else
//			{
//				this.flag &= 255 - 8;
//			}
//		}
//		else if (id == 0.3F)
//		{
//			if (x == 1)
//			{
//				if (value >= 1)
//				{
////					smallsakuratypes.set((byte)(value - 1));
//					PlayerData.SAKURA_MAP.put(player_uuid, (byte)(value - 1));
//					this.flag |= 128;
//				}
//			}
//			else
//			{
//				this.flag &= 255 - 128;
//			}
//		}
//		else if (id == 1.1F)
//		{
//			if (x == 1)
//			{
//				if (value >= 1)
//				{
////					smallsakuratypes.set((byte)(value - 1));
//					PlayerData.SAKURA_MAP.put(player_uuid, (byte)(value - 1));
//					this.flag |= 16;
//				}
//			}
//			else
//			{
//				this.flag &= 255 - 16;
//			}
//		}
//		else if (id == 1.2F)
//		{
//			if (x == 1)
//			{
//				if (value >= 1)
//				{
////					smallsakuratypes.set((byte)(value - 1));
//					PlayerData.SAKURA_MAP.put(player_uuid, (byte)(value - 1));
//					this.flag |= 32;
//				}
//			}
//			else
//			{
//				this.flag &= 255 - 32;
//			}
//		}
//		else if (id == 1.3F)
//		{
//			if (x == 1)
//			{
//				if (value >= 1)
//				{
////					smallsakuratypes.set((byte)(value - 1));
//					PlayerData.SAKURA_MAP.put(player_uuid, (byte)(value - 1));
//					this.flag |= 64;
//				}
//			}
//			else
//			{
//				this.flag &= 255 - 64;
//			}
//		}
//
////		this.fetch();
//	}

//	public void fetch()
//	{
//		byte[] byte_array = new byte[1 + 1];
//		byte_array[0] = CSetArea.ID;
//		byte_array[1] = this.flag;
//		NetworkRegistry.I.sendTo(new ClientMessage(byte_array), this.s.ms.entityplayermp);
//	}

	public void fetchTarget()
	{
		int size = this.target_list.size() * 4;
		byte[] byte_array = new byte[1 + size];
		byte_array[0] = CSetTarget.ID;
		int index = 0;
		for (int i = 1; i < size; i += 4)
		{
			ByteWriter.set(byte_array, this.target_list.get(index++), i);
		}

		NetworkRegistry.I.sendTo(new ClientMessage(byte_array), this.s.ms.entityplayermp);
	}

	public void fetchTroublemaker()
	{
		int size = this.troublemaker_list.size() * 4;
		byte[] byte_array = new byte[1 + size];
		byte_array[0] = CSetTroublemaker.ID;
		int index = 0;
		for (int i = 1; i < size; i += 4)
		{
			ByteWriter.set(byte_array, this.troublemaker_list.get(index++), i);
		}

		NetworkRegistry.I.sendTo(new ClientMessage(byte_array), this.s.ms.entityplayermp);
	}

	@Override
	public void onUpdate()
	{
		E e = this.s.i.getE();
		if (this.s.isMove(e))
		{
			this.xp_entity_list.clear();
			this.item_entity_list.clear();
			this.all_entity_list.clear();
			this.out_entity_list.clear();

			this.entity_collection = new ArrayList(this.entity_map.values());

//			for (Entity entity : new ArrayList<>(this.entity_map.values()))
			for (Entity entity : this.entity_collection)
			{
				if (!entity.isEntityAlive() || entity == e)
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
	}

	@Override
	public void writeFile(SIData sidata)
	{
		sidata.byte_array[sidata.index++] = this.state;

		int[] target_int_array = this.target_list.stream().mapToInt(Integer::intValue).toArray();
		ByteWriter.set(sidata.byte_array, target_int_array.length, sidata.index);
		sidata.index += 4;
		for (int target : target_int_array)
		{
			ByteWriter.set(sidata.byte_array, target, sidata.index);
			sidata.index += 4;
		}

		int[] troublemaker_int_array = this.troublemaker_list.stream().mapToInt(Integer::intValue).toArray();
		ByteWriter.set(sidata.byte_array, troublemaker_int_array.length, sidata.index);
		sidata.index += 4;
		for (int troublemaker : troublemaker_int_array)
		{
			ByteWriter.set(sidata.byte_array, troublemaker, sidata.index);
			sidata.index += 4;
		}
	}

	@Override
	public void readFile(SIData sidata)
	{
		this.state = sidata.byte_array[sidata.index++];

		int target_size = ByteReader.getInt(sidata.byte_array, sidata.index);
		sidata.index += 4;
		for (int x = 0; x < target_size; ++x)
		{
			this.target_list.add(ByteReader.getInt(sidata.byte_array, sidata.index));
			sidata.index += 4;
		}

		int troublemaker_size = ByteReader.getInt(sidata.byte_array, sidata.index);
		sidata.index += 4;
		for (int x = 0; x < troublemaker_size; ++x)
		{
			this.troublemaker_list.add(ByteReader.getInt(sidata.byte_array, sidata.index));
			sidata.index += 4;
		}
	}

	@Override
	public int size()
	{
		return 1 + 4 + this.target_list.stream().mapToInt(Integer::intValue).toArray().length * 4 + 4 + this.troublemaker_list.stream().mapToInt(Integer::intValue).toArray().length * 4;
	}

	public boolean isTarget(Entity entity)
	{
//		boolean result = this.target_list.isEmpty();
		if (/*result && */((this.state & I_BYPASS_ARMY) == 0))
		{
			for (Class clasz : EntityRegistry.ENTITIES_CLASS_LIST)
			{
				if (entity.getClass().equals(clasz))
				{
					return false;
				}
			}
		}

		if (!this.target_list.isEmpty())
		{
			for (Integer id : this.target_list)
			{
				if (entity.getClass() == EntityList.getClassFromID(id))
				{
					return true;
				}
			}
			return false;
		}

		for (Integer id : this.troublemaker_list)
		{
			if (entity.getClass() == EntityList.getClassFromID(id))
			{
				return false;
			}
		}

//		if (result)
//		{
		if ((this.state & I_PUT_ALL_TAMEABLE) == 0 && entity instanceof EntityTameable)
		{
			if ((this.state & I_PUT_OTHER_TAMEABLE) == 0)
			{
				if (((EntityTameable)entity).getOwnerId() != null)
				{
					return false;
				}
			}

			if ((this.state & I_PUT_OWNER_TAMEABLE) == 0)
			{
				UUID uuid = this.sieowner.uuid;
				if (uuid != null)
				{
					if (((EntityTameable)entity).getOwnerId() == uuid)
					{
						return false;
					}
				}
			}
		}

		if ((this.state & I_PUT_PLAYER) == 0 && entity instanceof EntityPlayer)
		{
			return false;
		}

		if (this.sieowner.uuid != null)
		{
			if ((this.state & I_PUT_OWNER) == 0 && entity.getUniqueID().equals(this.sieowner.uuid))
			{
				return false;
			}
		}

		if ((this.state & I_PUT_OBJECT) == 0 && !(entity instanceof EntityLivingBase))
		{
			return false;
		}

		if ((this.state & I_PUT_ANIMAL) == 0 && entity instanceof IAnimals)
		{
			return false;
		}
//		}

		return true;
	}
}
