package com.nali.list.entity.ai;

import com.nali.da.IBothDaNe;
import com.nali.list.capability.serializable.SmallSakuraSerializable;
import com.nali.list.capability.type.SmallSakuraType;
import com.nali.list.network.message.ClientMessage;
import com.nali.list.network.method.client.CSetGetItem;
import com.nali.network.NetworkRegistry;
import com.nali.small.entity.EntityLeInv;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.Inventory;
import com.nali.small.entity.memo.IBothLeInv;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.ai.AI;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.small.mixin.MixinInventoryCrafting;
import com.nali.sound.ISoundDaLe;
import com.nali.system.bytes.ByteReader;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import static com.nali.small.entity.EntityMath.isInArea;

public class AILeInvGetItem<SD extends ISoundDaLe, BD extends IBothDaNe, E extends EntityLeInv, I extends IMixLe<SD, BD, E>, S extends ServerLe<SD, BD, E, I, A> & IBothLeInv<SD, BD, E, I>, A extends MixAIE<SD, BD, E, I, S>> extends AI<SD, BD, E, I, S, A>
{
    public static byte ID;

    public AIEArea<SD, BD, E, I, S, A> aiearea;
    public AILeSetLocation<SD, BD, E, I, S, A> ailesetlocation;
    public AILeFindMove<SD, BD, E, I, S, A> ailefindmove;

    public byte flag;//move_to | remote_xp remote_item can_take_xp can_take_item walk_to_xp walk_to_item
//    public boolean pickup;
//    public int item_time_out, xp_time_out;

    public AILeInvGetItem(S s)
    {
        super(s);
    }

    @Override
    public void init()
    {
        this.aiearea = (AIEArea<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AIEArea.ID);
        this.ailesetlocation = (AILeSetLocation<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AILeSetLocation.ID);
        this.ailefindmove = (AILeFindMove<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AILeFindMove.ID);
    }

    @Override
    public void call()
    {

    }

    public void set()
    {
        byte[] byte_array = this.s.a.byte_array;
        float id = ByteReader.getFloat(byte_array, 1 + 16 + 1 + 1);
        float f = ByteReader.getFloat(byte_array, 1 + 16 + 1 + 1 + 4);

        SmallSakuraType smallsakuratypes = this.s.a.entityplayermp.getCapability(SmallSakuraSerializable.SMALLSAKURATYPES_CAPABILITY, null);
        byte value = smallsakuratypes.get();

        if (id >= 2)
        {
            if (id == 2.1F)
            {
                if (f == 1)
                {
                    if (value >= 1)
                    {
                        smallsakuratypes.set((byte)(value - 1));
                        this.flag |= 4;
                    }
                }
                else
                {
                    this.flag &= 255-4;
                }
            }
            else if (id == 2.2F)
            {
                if (f == 1)
                {
                    if (value >= 1)
                    {
                        smallsakuratypes.set((byte)(value - 1));
                        this.flag |= 64;
                    }
                }
                else
                {
                    this.flag &= 255-64;
                }
            }
            else if (id == 2.3F)
            {
                if (f == 1)
                {
                    if (value >= 1)
                    {
                        smallsakuratypes.set((byte)(value - 1));
                        this.flag |= 16;
                    }
                }
                else
                {
                    this.flag &= 255-16;
                }
            }
        }
        else if (id >= 1)
        {
            if (id == 1.1F)
            {
                if (f == 1)
                {
                    if (value >= 1)
                    {
                        smallsakuratypes.set((byte)(value - 1));
                        this.flag |= 2;
                    }
                }
                else
                {
                    this.flag &= 255-2;
                }
            }
            else if (id == 1.2F)
            {
                if (f == 1)
                {
                    if (value >= 1)
                    {
                        smallsakuratypes.set((byte)(value - 1));
                        this.flag |= 32;
                    }
                }
                else
                {
                    this.flag &= 255-32;
                }
            }
            else if (id == 1.3F)
            {
                if (f == 1)
                {
                    if (value >= 1)
                    {
                        smallsakuratypes.set((byte)(value - 1));
                        this.flag |= 8;
                    }
                }
                else
                {
                    this.flag &= 255-8;
                }
            }
        }

        this.fetch();
    }

    public void fetch()
    {
        byte[] byte_array = new byte[1 + 1];
        byte_array[0] = CSetGetItem.ID;
        byte_array[1] = this.flag;
        NetworkRegistry.I.sendTo(new ClientMessage(byte_array), this.s.a.entityplayermp);
    }

    @Override
    public void onUpdate()
    {
        if (this.s.isMove())
        {
            E e = this.s.i.getE();
            boolean xp = !this.aiearea.xp_entity_list.isEmpty();
            EntityXPOrb to_entityxporb = null;
            ItemStack xp_itemstack = null;
            boolean should_get_xp = false;
            if (xp)
            {
                to_entityxporb = this.aiearea.xp_entity_list.get(this.aiearea.xp_entity_list.size() - 1);
                xp_itemstack = EnchantmentHelper.getEnchantedItem(Enchantments.MENDING, e);
                should_get_xp = !xp_itemstack.isEmpty()/* && itemstack.isItemStackDamageable()*/ && xp_itemstack.getItemDamage() > 0/* && EnchantmentHelper.getEnchantmentLevel(Enchantments.MENDING, itemstack) > 0*/;
            }

            boolean item = !this.aiearea.item_entity_list.isEmpty();

//            if (this.s.isWork(this.s.bytele.GET_ITEM()))
            if ((this.s.a.state & 1) == 1)
            {
    //            if ((this.s.main_work_byte_array[this.s.bytele.MINE() / 8] >> this.s.bytele.MINE() % 8 & 1) == 1 && this.s.entitiesaimemory.skinningentitiesmine.blockpos != null)
    //            {
    //                this.s.entitiesaimemory.skinningentitiesmine.breakWork();
    //            }

                if (xp && should_get_xp)
                {
                    if ((this.flag & 32) == 32 && !e.getEntityBoundingBox().intersects(to_entityxporb.getEntityBoundingBox())/*!isTooClose(e, to_entityxporb, 0)*/)
                    {
                        if (this.ailesetlocation.far == 0 || this.ailesetlocation.blockpos == null || isInArea(to_entityxporb, this.ailesetlocation.blockpos, this.ailesetlocation.far))
                        {
    //                        Nali.LOGGER.info("XP START");
                            this.flag |= 1;
//                            this.ailefindmove.setBreakGoal(to_entityxporb.posX, to_entityxporb.posY, to_entityxporb.posZ);
                            this.ailefindmove.setGoal(to_entityxporb.posX, to_entityxporb.posY, to_entityxporb.posZ);
                        }
                    }
                }
                else if (item)
                {
                    EntityItem to_entityitem = this.aiearea.item_entity_list.get(this.aiearea.item_entity_list.size() - 1);
                    if ((this.flag & 64) == 64 && !e.getEntityBoundingBox().intersects(to_entityitem.getEntityBoundingBox())/*!isTooClose(e, to_entityitem, 0)*/)
                    {
                        if (this.ailesetlocation.far == 0 || this.ailesetlocation.blockpos == null || isInArea(to_entityitem, this.ailesetlocation.blockpos, this.ailesetlocation.far))
                        {
    //                        Nali.LOGGER.info("ITEM START");
                            this.flag |= 1;
//                            this.ailefindmove.setBreakGoal(to_entityitem.posX, to_entityitem.posY, to_entityitem.posZ);
                            this.ailefindmove.setGoal(to_entityitem.posX, to_entityitem.posY, to_entityitem.posZ);
                        }
                    }
                }
            }
    //        else
    //        {
    //            this.s.current_work_byte_array[this.s.bytele.GET_ITEM() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.GET_ITEM() % 8));
    //        }

            if (xp)
            {
                for (EntityXPOrb entityxporb : this.aiearea.xp_entity_list)
                {
                    if (should_get_xp)
                    {
                        if ((this.flag & 2) == 2 || e.getEntityBoundingBox().intersects(entityxporb.getEntityBoundingBox())/*isTooClose(e, entityxporb, 0)*/)
                        {
                            if ((this.flag & 1) == 1)
                            {
    //                            Nali.LOGGER.info("XP END");
                                this.ailefindmove.endGoal();
                                this.flag &= 255-1;
    //                            this.s.current_work_byte_array[this.s.bytele.GET_ITEM() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.GET_ITEM() % 8));
                            }
    //                        Nali.LOGGER.info("XP STEP");
                            if ((this.flag & 8) == 8)
                            {
    //                            Nali.LOGGER.info("XP TAKE");
                                e.onItemPickup(entityxporb, 1);

                                if (!xp_itemstack.isEmpty() && xp_itemstack.isItemDamaged())
                                {
                                    float ratio = xp_itemstack.getItem().getXpRepairRatio(xp_itemstack);
                                    int i = Math.min(roundAverage(entityxporb.xpValue * ratio), xp_itemstack.getItemDamage());
                                    entityxporb.xpValue -= roundAverage(i / ratio);
                                    xp_itemstack.setItemDamage(xp_itemstack.getItemDamage() - i);
                                }

                                entityxporb.setDead();
                            }
                        }
                    }
                }
            }

            if (item)
            {
                for (EntityItem entityitem : this.aiearea.item_entity_list)
                {
                    ItemStack itemstack = entityitem.getItem();

                    if ((this.flag & 4) == 4 || e.getEntityBoundingBox().intersects(entityitem.getEntityBoundingBox())/*isTooClose(e, entityitem, 0)*/)
                    {
                        if ((this.flag & 1) == 1)
                        {
    //                        Nali.LOGGER.info("ITEM END");
                            this.ailefindmove.endGoal();
                            this.flag &= 255-1;
    //                        this.s.current_work_byte_array[this.s.bytele.GET_ITEM() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.GET_ITEM() % 8));
                        }
    //                    Nali.LOGGER.info("ITEM STEP");
                        if ((this.flag & 16) == 16)
                        {
    //                        Nali.LOGGER.info("ITEM TAKE");
                            Inventory inventory = this.s.getInventory();
                            for (byte i = 0; i < inventory.getSizeInventory(); ++i)
                            {
                                ItemStack inv_itemstack = inventory.getStackInSlot(i);

                                int max_stack = inv_itemstack.getMaxStackSize();
                                int e_count = itemstack.getCount();

                                if (inv_itemstack.isEmpty())
                                {
                                    e.onItemPickup(entityitem, e_count);
                                    inventory.setInventorySlotContents(i, itemstack);
                                    entityitem.setDead();

                                    if (i >= 27-3*3+2+4 && i <= 27-3*3+2+4+3*3)
                                    {
                                        Container container = ((MixinInventoryCrafting)inventory.inventorycrafting).eventHandler();
                                        if (container != null)
                                        {
                                            container.onCraftMatrixChanged(inventory.inventorycrafting);
                                        }
                                    }

                                    break;
                                }

                                int max_count = inv_itemstack.getCount() + e_count;
                                int count = max_count - max_stack;
                                if (isSameItemSameTags(inv_itemstack, itemstack))
                                {
                                    if (count <= 0)
                                    {
                                        e.onItemPickup(entityitem, e_count);
                                        inv_itemstack.setCount(max_count);
                                        entityitem.setDead();

                                        break;
                                    }
                                    else
                                    {
                                        inv_itemstack.setCount(max_stack);
                                        itemstack.setCount(count);
                                        entityitem.setItem(itemstack);
                                    }
                                }
                            }
                        }
                    }
                }
            }

    //        if ((this.flag & 1) == 1)
    //        {
    //            this.ailefindmove.endGoal();
    //            this.flag &= 255-1;
    //            this.s.current_work_byte_array[this.s.bytele.GET_ITEM() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.GET_ITEM() % 8));
    //        }
//            if ((this.flag & 1) == 0)
//            {
//                this.s.current_work_byte_array[this.s.bytele.GET_ITEM() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.GET_ITEM() % 8));
//            }
        }
    }

    @Override
    public void writeNBT(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.setByte("AILeInvGetItem_flag", this.flag);
    }

    @Override
    public void readNBT(NBTTagCompound nbttagcompound)
    {
        this.flag = nbttagcompound.getByte("AILeInvGetItem_flag");
    }

    public static boolean isSameItemSameTags(ItemStack itemstack_a, ItemStack itemstack_b)
    {
        return itemstack_a.getItem() == itemstack_b.getItem() && ItemStack.areItemStackTagsEqual(itemstack_a, itemstack_b);
    }

    public static int roundAverage(float value)
    {
        double floor = Math.floor(value);
        return (int) floor + (Math.random() < value - floor ? 1 : 0);
    }
}
