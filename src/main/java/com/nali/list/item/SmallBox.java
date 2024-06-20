package com.nali.list.item;

import com.nali.draw.DrawScreen;
import com.nali.list.render.BoxRender;
import com.nali.render.ObjectRender;
import com.nali.small.Small;
import com.nali.small.SmallTab;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.item.IMixItem;
import com.nali.small.mixin.IMixinWorldServer;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nullable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.List;

import static com.nali.Nali.I;

public class SmallBox extends Item implements IMixItem
{
    public static int ID;

    @SideOnly(Side.CLIENT)
    public static ObjectRender OBJECTRENDER;
    @SideOnly(Side.CLIENT)
    public static DrawScreen DRAWSCREEN;

    public SmallBox(String[] string_array)
    {
        super();
        this.init(this, string_array[0], string_array[1], SmallTab.TAB);
        this.setMaxStackSize(1);
//        I = this;
        if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
        {
            this.renderInit();
        }
    }

    @SideOnly(Side.CLIENT)
    public void renderInit()
    {
        OBJECTRENDER = new BoxRender();
        DRAWSCREEN = new DrawScreen();
        DRAWSCREEN.scale(0.25F);
        DRAWSCREEN.z = 0.0F;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        tooltip.add(I18n.translateToLocal("info." + Small.ID + ".box0"));
        tooltip.add(I18n.translateToLocal("info." + Small.ID + ".box1"));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ObjectRender getObjectRender()
    {
        return OBJECTRENDER;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public DrawScreen getDrawScreen()
    {
        return DRAWSCREEN;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void render()
    {
        GL11.glPushMatrix();
        GL11.glTranslatef(0.0F, -0.07357F, 0.0F);
        IMixItem.super.render();
//        GL11.glTranslatef(0.0F, 0.07357F, 0.0F);
        GL11.glPopMatrix();
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer entityplayer, EnumHand enumhand)
    {
        if (!entityplayer.isSneaking())
        {
            if (!world.isRemote)
            {
                ItemStack itemstack = entityplayer.getHeldItem(enumhand);
                NBTTagCompound nbttagcompound = itemstack.getTagCompound();

                if (nbttagcompound == null)
                {
                    Entity entity = null;
                    double max_dis = 4.0D;

                    for (Entity uuid_entity : new HashSet<>(((IMixinWorldServer)world).entitiesByUuid().values()))
                    {
                        double new_dis = uuid_entity.getDistanceSq(entityplayer);
                        if (/*uuid_entity.dimension == entityplayer.dimension && */new_dis < max_dis && !(uuid_entity instanceof EntityPlayer))
                        {
                            entity = uuid_entity;
                            max_dis = new_dis;
                        }
                    }

                    if (entity != null)
                    {
                        putToBox(entity, itemstack);
                    }
                }
            }

            return new ActionResult(EnumActionResult.SUCCESS, entityplayer.getHeldItem(enumhand));
        }

        return super.onItemRightClick(world, entityplayer, enumhand);
    }

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
                                world.spawnEntity(entity);
                                nbttagcompound.removeTag(key_id);
                                nbttagcompound.removeTag(entity_key);
                                itemstack.setTagCompound(null);
                            }
                            catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e)
                            {
                                I.error(e);
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
        ResourceLocation resourcelocation = EntityList.getKey(entity);
//                        if (resourcelocation == null)
//                        {
//                            return null;
//                        }

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

        if (entity instanceof IMixLe)
        {
            ((ServerE)((IMixLe)entity).getBothEntityMemory()).removeFromMap();
        }

        entity.world.removeEntity(entity);
        itemstack.setStackDisplayName(entity.getName());
    }
}