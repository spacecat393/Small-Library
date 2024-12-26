package com.nali.list.item;

import com.nali.Nali;
import com.nali.da.IBothDaO;
import com.nali.list.da.BothDaBox;
import com.nali.list.render.RenderBox;
import com.nali.small.entity.EntityRegistry;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.si.SIData;
import com.nali.small.mix.item.ItemI;
import com.nali.small.mix.item.ItemRegistry;
import com.nali.small.mix.memo.client.ClientI;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

//throw to catch entity
//will use to clone entity later
public class SmallBox extends ItemI
{
	public static int ID;

//	@SideOnly(Side.CLIENT)
//	public static ObjectRender OBJECTRENDER;
//	@SideOnly(Side.CLIENT)
//	public static DrawScreen DRAWSCREEN;

	public SmallBox(String[] string_array)
	{
		super(string_array);
////		this.init(this, string_array[0], string_array[1], SmallTab.TAB);
//		this.setMaxStackSize(1);
////		I = this;
////		if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
////		{
////			this.renderInit();
////		}
	}

//	@SideOnly(Side.CLIENT)
//	public void renderInit()
//	{
//		OBJECTRENDER = new BoxRender();
//		DRAWSCREEN = new DrawScreen();
//		DRAWSCREEN.scale(0.25F);
//		DRAWSCREEN.z = 0.0F;
//	}

//	@Override
//	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
//	{
//		tooltip.add(I18n.translateToLocal("info." + Small.ID + ".box0"));
//		tooltip.add(I18n.translateToLocal("info." + Small.ID + ".box1"));
//		super.addInformation(stack, worldIn, tooltip, flagIn);
//	}

//	@Override
//	@SideOnly(Side.CLIENT)
//	public ObjectRender getObjectRender()
//	{
//		return OBJECTRENDER;
//	}
//
//	@Override
//	@SideOnly(Side.CLIENT)
//	public DrawScreen getDrawScreen()
//	{
//		return DRAWSCREEN;
//	}

	@SideOnly(Side.CLIENT)
	@Override
	public void render()
	{
		GL11.glPushMatrix();
		GL11.glTranslatef(0.0F, -0.07357F, 0.0F);
		super.render();
//		GL11.glTranslatef(0.0F, 0.07357F, 0.0F);
		GL11.glPopMatrix();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void newC()
	{
		RenderBox r = new RenderBox();
//		DrawScreen d = new DrawScreen(r);
//		d.scale(0.25F);
//		d.z = 0.0F;
		this.ibothi = new ClientI(r/*, d*/, this);
	}

	@Override
	public IBothDaO getBD()
	{
		return BothDaBox.IDA;
	}

//	@Override
//	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer entityplayer, EnumHand enumhand)
//	{
//		ItemStack itemstack = entityplayer.getHeldItem(enumhand);
//		byte[] byte_array = serializeNBT(itemstack.serializeNBT());
//		if (!entityplayer.isSneaking())
//		{
//			if (!world.isRemote)
//			{
//				ItemStack itemstack = entityplayer.getHeldItem(enumhand);
//				NBTTagCompound nbttagcompound = itemstack.getTagCompound();
//
//				if (nbttagcompound == null)
//				{
//					Entity entity = null;
//					double max_dis = 4.0D;
//
//					for (Entity uuid_entity : new HashSet<>(((IMixinWorldServer)world).entitiesByUuid().values()))
//					{
//						double new_dis = uuid_entity.getDistanceSq(entityplayer);
//						if (/*uuid_entity.dimension == entityplayer.dimension && */new_dis < max_dis && !(uuid_entity instanceof EntityPlayer))
//						{
//							entity = uuid_entity;
//							max_dis = new_dis;
//						}
//					}
//
//					if (entity != null)
//					{
//						putToBox(entity, itemstack);
//					}
//				}
//			}
//
//			return new ActionResult(EnumActionResult.SUCCESS, entityplayer.getHeldItem(enumhand));
//		}
//
//		return super.onItemRightClick(world, entityplayer, enumhand);
//	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer entityplayer, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if (entityplayer.isSneaking())
		{
			if (!world.isRemote)
			{
				ItemStack itemstack = entityplayer.getHeldItem(hand);
				NBTTagCompound nbttagcompound = itemstack.getTagCompound();

				String entity_key = "entity";

				if (nbttagcompound != null)
				{
					String key_id = "key_id";
					if (nbttagcompound.hasKey(key_id))
					{
						String id = nbttagcompound.getString(key_id);

						ResourceLocation resourcelocation = new ResourceLocation(id);
						if (ForgeRegistries.ENTITIES.containsKey(resourcelocation))
						{
							try
							{
								Constructor constructor = ForgeRegistries.ENTITIES.getValue(resourcelocation).getEntityClass().getConstructor(World.class);
								Entity entity = (Entity)constructor.newInstance(world);
								NBTTagCompound entity_nbttagcompound = nbttagcompound.getCompoundTag(entity_key);

								entity.readFromNBT(entity_nbttagcompound);

								pos = pos.up();
								entity.setLocationAndAngles(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, entity.rotationYaw, entity.rotationPitch);
								entity.isDead = false;
								while (((WorldServer)world).getEntityFromUuid(entity.getUniqueID()) != null)
								{
									entity.setUniqueId(MathHelper.getRandomUUID(world.rand));
								}
								world.spawnEntity(entity);

								int count = itemstack.getCount();
								if (count > 1)
								{
									itemstack.shrink(1);
									world.spawnEntity(new EntityItem(world, entityplayer.posX, entityplayer.posY, entityplayer.posZ, new ItemStack(ItemRegistry.ITEM_ARRAY[SmallBox.ID])));
								}
								else
								{
									nbttagcompound.removeTag(key_id);
									nbttagcompound.removeTag(entity_key);
									itemstack.setTagCompound(null);
								}

								if (entity instanceof IMixE)
								{
									ServerE s = (ServerE)((IMixE)entity).getB();
									SIData sidata = new SIData();
									sidata.byte_array = entity_nbttagcompound.getByteArray("SIData");
									if (sidata.byte_array.length != 0)
									{
										s.readSIData(sidata);
										ServerE.S_MAP.put((long) world.provider.getDimension() << 32 | entity.getEntityId(), s);

										if (count == 1)
										{
											entity_nbttagcompound.removeTag("SIData");
										}
									}
								}
							}
							catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e)
							{
								Nali.error(e);
							}
						}
					}
				}
			}

			return EnumActionResult.SUCCESS;
		}

		return super.onItemUse(entityplayer, world, pos, hand, facing, hitX, hitY, hitZ);
	}

	public static void putToBox(Entity entity, ItemStack itemstack)
	{
		if (itemstack.getTagCompound() == null)
		{
			ResourceLocation resourcelocation = EntityList.getKey(entity);
	//						if (resourcelocation == null)
	//						{
	//							return null;
	//						}

			itemstack.setTagCompound(new NBTTagCompound());
			NBTTagCompound nbttagcompound = itemstack.getTagCompound();

			String id_key = "key_id";
			if (!nbttagcompound.hasKey(id_key))
			{
				nbttagcompound.setString(id_key, resourcelocation.toString());
			}

			NBTTagCompound entity_nbttagcompound = new NBTTagCompound();
			entity.writeToNBT(entity_nbttagcompound);
			nbttagcompound.setTag("entity", entity_nbttagcompound);

			if (entity instanceof IMixE)
			{
				ServerE s = ((ServerE)((IMixE)entity).getB());
				if ((s.ms.state & 4) == 4)
				{
					nbttagcompound.setByteArray("SIData", s.genSIData().byte_array);
					s.remove();
				}
			}

			entity.world.removeEntity(entity);
			itemstack.setStackDisplayName(entity.getName());
		}
	}

	public static void randomToBox(World world, ItemStack itemstack)
	{
		try
		{
			int id = world.rand.nextInt(EntityRegistry.ENTITIES_CLASS_LIST.size());
			Constructor constructor = (EntityRegistry.ENTITIES_CLASS_LIST.get(id)).getConstructor(World.class);
			Entity entity = (Entity)constructor.newInstance(world);

			if (!(entity instanceof EntityPlayer))
			{
				SmallBox.putToBox(entity, itemstack);
			}
		}
		catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e)
		{
			Nali.error(e);
		}
	}
}
