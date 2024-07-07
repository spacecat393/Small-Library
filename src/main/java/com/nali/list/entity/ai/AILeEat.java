package com.nali.list.entity.ai;

import com.mojang.authlib.GameProfile;
import com.nali.da.IBothDaNe;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.ai.AI;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.small.mixin.IMixinItemFood;
import com.nali.sound.ISoundDaLe;
import com.nali.system.bytes.ByteReader;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;

import java.util.Collection;

public class AILeEat<SD extends ISoundDaLe, BD extends IBothDaNe, E extends EntityLe, I extends IMixLe<SD, BD, E>, S extends ServerLe<SD, BD, E, I, A>, A extends MixAIE<SD, BD, E, I, S>> extends AI<SD, BD, E, I, S, A>
{
    public static byte ID;

    public byte state;//t-eat t-drinkMilk

    public AILeEat(S s)
    {
        super(s);
    }

    @Override
    public void init()
    {
    }

    @Override
    public void call()
    {
        ItemStack itemstack = this.s.a.entityplayermp.getHeldItemMainhand();
        if (itemstack.getItem() == Items.MILK_BUCKET)
        {
            this.drinkMilk();
        }
        else
        {
            this.eat(itemstack);
        }
    }

    public void eat(ItemStack itemstack)
    {
        EntityPlayerMP entityplayermp = this.s.a.entityplayermp;
//        ItemStack itemstack = entityplayermp.getHeldItemMainhand();
        if (itemstack.getItem() instanceof ItemFood)
        {
            E e = this.s.i.getE();
            byte[] byte_array = this.s.a.byte_array;
            WorldServer worldserver = this.s.worldserver;
            ItemFood itemfood = (ItemFood)itemstack.getItem();
            FakePlayer fakeplayer = new FakePlayer(worldserver, new GameProfile(null, "!"));
            fakeplayer.connection = entityplayermp.connection;
            ((IMixinItemFood)itemfood).GOonFoodEaten(itemstack, worldserver, fakeplayer);
            Collection<PotionEffect> potioneffect_collection = fakeplayer.getActivePotionEffects();
            for (PotionEffect potioneffect : potioneffect_collection)
            {
                e.addPotionEffect(potioneffect);
            }

            this.state |= 1;

            worldserver.spawnEntity(new EntityXPOrb(worldserver, e.posX, e.posY, e.posZ, 10));
            e.heal(itemfood.getHealAmount(itemstack) + itemfood.getSaturationModifier(itemstack));
            itemstack.shrink(1);

//                        Vec3d view_vec3d = skinningentities.getLookVec().scale(0.25F);
            e.playSound(SoundEvents.ENTITY_GENERIC_EAT, e.getSoundVolume(), e.getSoundPitch());
            float x = ByteReader.getFloat(byte_array, 1 + 16 + 1);
            float y = ByteReader.getFloat(byte_array, 1 + 16 + 1 + 4);
            float z = ByteReader.getFloat(byte_array, 1 + 16 + 1 + 4 + 4);
            if (itemstack.getHasSubtypes())
            {
                this.s.worldserver.spawnParticle(EnumParticleTypes.ITEM_CRACK, x, y, z, 0, 0.0D, 0.0D, 0.0D, Item.getIdFromItem(itemstack.getItem()), itemstack.getMetadata());
            }
            else
            {
                this.s.worldserver.spawnParticle(EnumParticleTypes.ITEM_CRACK, x, y, z, 0.0D, 0.0D, 0.0D, Item.getIdFromItem(itemstack.getItem()));
            }
//            byte[] byte_array = new byte[1 + 4 + 4 + 4 + 4];
//            byte_array[0] = CRenderFood.ID;
//            ByteWriter.set(byte_array, ByteReader.getFloat(a_byte_array, 1 + 16 + 1), 1);
//            ByteWriter.set(byte_array, ByteReader.getFloat(a_byte_array, 1 + 16 + 1 + 4), 1 + 4);
//            ByteWriter.set(byte_array, ByteReader.getFloat(a_byte_array, 1 + 16 + 1 + 4 + 4), 1 + 4 + 4);
//            ByteWriter.set(byte_array, Item.getIdFromItem(itemfood), 1 + 4 + 4 + 4);
//            NetworkRegistry.I.sendToAll(new ClientMessage(byte_array));
        }
    }

    public void drinkMilk()
    {
        EntityPlayerMP entityplayermp = this.s.a.entityplayermp;
//        ItemStack itemstack = entityplayermp.getHeldItemMainhand();
//        if (itemstack.getItem() == Items.MILK_BUCKET)
//        {
        E e = this.s.i.getE();
        e.clearActivePotions();
        entityplayermp.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.BUCKET));

        e.playSound(SoundEvents.ENTITY_GENERIC_DRINK, e.getSoundVolume(), e.getSoundPitch());
//        }
    }

    @Override
    public void onUpdate()
    {
    }

    @Override
    public void writeNBT(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.setByte("AIEEat_state", this.state);
    }

    @Override
    public void readNBT(NBTTagCompound nbttagcompound)
    {
        this.state = nbttagcompound.getByte("AIEEat_state");
    }
}
