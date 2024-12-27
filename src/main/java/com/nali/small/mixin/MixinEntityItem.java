package com.nali.small.mixin;

import com.nali.list.item.SmallBox;
import com.nali.small.entity.EntityMath;
import com.nali.small.entity.EntityRegistry;
import com.nali.small.mix.item.ItemRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.AbstractChestHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventoryChangedListener;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;
import java.util.UUID;

@Mixin(EntityItem.class)
public abstract class MixinEntityItem extends Entity
{
//	private ItemStack itemstack = ItemStack.EMPTY;

	public MixinEntityItem(World worldIn)
	{
		super(worldIn);
	}

//	@Inject(method = "setItem", at = @At(value = "HEAD"))
//	private void nali_small_setItem(ItemStack stack, CallbackInfo ci)
//	{
//		this.itemstack = stack;
//	}

	@Shadow public abstract ItemStack getItem();

	@Inject(method = "onUpdate", at = @At(value = "HEAD"))
	private void nali_small_onUpdate(CallbackInfo ci)
	{
		if (!this.world.isRemote)
		{
			ItemStack itemstack = this.getItem();
			if (itemstack != ItemStack.EMPTY && itemstack.getItem() == ItemRegistry.ITEM_ARRAY[SmallBox.ID])
			{
				Map<UUID, Entity> entity_map = ((IMixinWorldServer)this.world).entitiesByUuid();
				for (Entity entity : entity_map.values())
				{
					boolean has_inv = entity instanceof IInventoryChangedListener;
					if (this != entity && !(entity instanceof EntityPlayer) && EntityMath.getDistanceAABBToAABB(this, entity) <= 0)
					{
						if (entity instanceof EntityItem || has_inv && !(entity instanceof AbstractChestHorse))
						{
							EntityItem entityitem = (EntityItem)entity;
//							if (itemstack.getCount() < 64)
//							{
							ItemStack entityitem_itemstack = entityitem.getItem();
							if (entityitem_itemstack.getItem() instanceof ItemRecord/* && entityitem_itemstack.getCount() == 64*/)
							{
								if (itemstack.getTagCompound() == null)
								{
									if (itemstack.getCount() == 1 && !EntityRegistry.ENTITIES_CLASS_LIST.isEmpty())
									{
										SmallBox.randomToBox(this.world, itemstack);
										entityitem_itemstack.shrink(1);
									}
								}
								else
								{
									itemstack.grow(1);
									entityitem_itemstack.shrink(1);
								}
							}
//							}
						}
						else if (itemstack.getTagCompound() == null)
						{
							if (!has_inv || entity instanceof AbstractChestHorse && !((AbstractChestHorse)entity).hasChest())
							{
								SmallBox.putToBox(entity, itemstack);
							}
						}
						break;
					}
				}
			}
		}
	}
}
