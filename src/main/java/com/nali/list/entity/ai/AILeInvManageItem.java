package com.nali.list.entity.ai;

import com.nali.data.IBothDaNe;
import com.nali.list.capability.serializable.SmallSakuraSerializable;
import com.nali.list.capability.type.SmallSakuraType;
import com.nali.list.network.message.ClientMessage;
import com.nali.list.network.method.client.CSetManageItem;
import com.nali.network.NetworkRegistry;
import com.nali.small.entity.EntityLeInv;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.Inventory;
import com.nali.small.entity.memo.server.ServerLeInv;
import com.nali.small.entity.memo.server.ai.AI;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.small.mixin.MixinInventoryCrafting;
import com.nali.sound.ISoundLe;
import com.nali.system.bytes.ByteReader;
import com.nali.system.bytes.ByteWriter;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

import static com.nali.list.entity.ai.AILeInvGetItem.isSameItemSameTags;
import static com.nali.small.entity.EntityMath.isInArea;

public class AILeInvManageItem<SD extends ISoundLe, BD extends IBothDaNe, E extends EntityLeInv, I extends IMixLe<SD, BD, E>, S extends ServerLeInv<SD, BD, E, I, A>, A extends MixAIE<SD, BD, E, I, S>> extends AI<SD, BD, E, I, S, A>
{
    public static byte ID;

    public AILeSetLocation<SD, BD, E, I, S, A> ailesetlocation;
    public AILeFindMove<SD, BD, E, I, S, A> ailefindmove;

    public BlockPos in_blockpos, out_blockpos;
    public byte state;//remote_in remote_out random_in random_out in out
    public int in_random = 2, out_random = 2;

    public AILeInvManageItem(S s)
    {
        super(s);
    }

    @Override
    public void init()
    {
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
        BlockPos blockpos = null;

        float x = ByteReader.getFloat(byte_array, 1 + 16 + 1 + 1 + 4);
        if (byte_array.length > 1 + 16 + 1 + 1 + 4 + 4)
        {
            float y = ByteReader.getFloat(byte_array, 1 + 16 + 1 + 1 + 4 + 4);
            float z = ByteReader.getFloat(byte_array, 1 + 16 + 1 + 1 + 4 + 4 + 4);
            blockpos = new BlockPos(x, y, z);
        }

//            Small.LOGGER.info("ID " + id);
//            Small.LOGGER.info("X " + x);

        SmallSakuraType smallsakuratypes = this.s.a.entityplayermp.getCapability(SmallSakuraSerializable.SMALLSAKURATYPES_CAPABILITY, null);
        byte value = smallsakuratypes.get();

//            if (id >= 2)
//            {
        if (id == 2.1F)
        {
            if (x == 1)
            {
                if (value >= 1)
                {
                    smallsakuratypes.set((byte)(value - 1));
                    this.state |= 32;
                }
            }
            else
            {
                this.state &= 255 - 32;
            }
        }
        else if (id == 2.2F)
        {
            if (x == 1)
            {
                if (value >= 1)
                {
                    smallsakuratypes.set((byte)(value - 1));
                    this.state |= 2;
                }
            }
            else
            {
                this.state &= 255 - 2;
            }
        }
        else if (id == 2.3F)
        {
            int v = (int)x;
            if (value >= v)
            {
                smallsakuratypes.set((byte)(value - v));
                this.out_random = v;
            }
        }
        else if (id == 2.0F)
        {
            if (blockpos != null)
            {
                this.out_blockpos = blockpos;
            }
            else
            {
                this.out_blockpos = null;
                this.state ^= 8;
            }
        }
//            }
//            else if (id >= 1)
//            {
        else if (id == 1.1F)
        {
            if (x == 1)
            {
                if (value >= 1)
                {
                    smallsakuratypes.set((byte)(value - 1));
                    this.state |= 16;
                }
            }
            else
            {
                this.state &= 255 - 16;
            }
        }
        else if (id == 1.2F)
        {
            if (x == 1)
            {
                if (value >= 1)
                {
                    smallsakuratypes.set((byte)(value - 1));
                    this.state |= 1;
                }
            }
            else
            {
                this.state &= 255 - 1;
            }
        }
        else if (id == 1.3F)
        {
            int v = (int)x;
            if (value >= v)
            {
                smallsakuratypes.set((byte)(value - v));
                this.in_random = v;
            }
        }
        else if (id == 1.0F)
        {
            if (blockpos != null)
            {
                this.in_blockpos = blockpos;
            }
            else
            {
                this.in_blockpos = null;
                this.state ^= 4;
            }
        }
//            }

        this.fetch();
    }

    public void fetch()
    {
        byte[] byte_array = new byte[1 + 1 + 8 + 8 + 4 + 4];
        byte_array[0] = CSetManageItem.ID;
        byte_array[1] = this.state;
        if (this.in_blockpos != null)
        {
            ByteWriter.set(byte_array, this.in_blockpos.toLong(), 1 + 1);
        }
        else
        {
            byte_array[1 + 1] = -1;
        }
        if (this.out_blockpos != null)
        {
            ByteWriter.set(byte_array, this.out_blockpos.toLong(), 1 + 1 + 8);
        }
        else
        {
            byte_array[1 + 1 + 8] = -1;
        }
        ByteWriter.set(byte_array, this.in_random, 1 + 1 + 8 + 8);
        ByteWriter.set(byte_array, this.out_random, 1 + 1 + 8 + 8 + 4);
        NetworkRegistry.I.sendTo(new ClientMessage(byte_array), this.s.a.entityplayermp);
    }

    @Override
    public void onUpdate()
    {
        if (this.s.isMove())
        {
//            if (this.s.isWork(this.s.bytele.MANAGE_ITEM()))
//            if ((this.s.a.state & 1) == 1)
//            {
//            if (e.ticksExisted % 20 == 0)
//            {
            E e = this.s.i.getE();
            Random random = e.getRNG();
            World world = e.world;
            Inventory inventory = this.s.inventory;

//                if ((this.s.main_work_byte_array[this.s.bytele.MINE() / 8] >> this.s.bytele.MINE() % 8 & 1) == 1 && this.s.entitiesaimemory.skinningentitiesmine.blockpos != null)
//                {
//    //                this.s.current_work_byte_array[this.s.bytele.MANAGE_ITEM() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.MANAGE_ITEM() % 8));
//    //
//    //                this.s.current_work_byte_array[this.s.bytele.GET_ITEM() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.ATTACK() % 8));
//                    this.s.entitiesaimemory.skinningentitiesmine.breakWork();
//                }

            boolean in = (this.state & 16) == 16,
            out = (this.state & 32) == 32;

            if (in)
            {
                if ((this.state & 4) == 4)
                {
//                    Small.LOGGER.info("RNG");
                    this.in_blockpos = new BlockPos(e.posX + random.nextInt(this.in_random) - random.nextInt(this.in_random), e.posY + random.nextInt(this.in_random) - random.nextInt(this.in_random), e.posZ + random.nextInt(this.in_random) - random.nextInt(this.in_random));
//                    Small.LOGGER.info(this.in_blockpos.toString());
                }

                if (this.in_blockpos != null)
                {
//                    Small.LOGGER.info("IN");
                    //walk to
                    if ((this.state & 1) == 1 || e.getDistanceSq(this.in_blockpos) < 4.0D)
                    {
//                        Small.LOGGER.info("DO");
                        IBlockState iblockstate = world.getBlockState(this.in_blockpos);
                        Block block = iblockstate.getBlock();

                        if (block.hasTileEntity(iblockstate))
                        {
//                            Small.LOGGER.info("hasTileEntity");
                            TileEntity tileentity = world.getTileEntity(this.in_blockpos);

                            if (tileentity instanceof IInventory)
                            {
//                                Small.LOGGER.info("IInventory");
                                IInventory iinventory = (IInventory)tileentity;

                                ItemStack itemstack = null;
                                int i = 0;
                                for (; i < inventory.getSizeInventory(); ++i)
                                {
                                    ItemStack is = inventory.getStackInSlot(i);
                                    if (!is.isEmpty())
                                    {
                                        itemstack = is;
                                        break;
                                    }
                                }

                                if (itemstack != null)
                                {
//                                    Small.LOGGER.info("itemstack");
                                    this.manage(iinventory, itemstack);

                                    if (i >= 27-3*3+2+4 && i <= 27-3*3+2+4+3*3)
                                    {
                                        Container container = ((MixinInventoryCrafting)inventory.inventorycrafting).eventHandler();
                                        if (container != null)
                                        {
                                            container.onCraftMatrixChanged(inventory.inventorycrafting);
                                        }
                                    }
                                }
                            }
                        }

                        this.ailefindmove.endGoal();
//                            this.s.current_work_byte_array[this.s.bytele.MANAGE_ITEM() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.MANAGE_ITEM() % 8));
                    }
//                        else
                    else if ((this.s.a.state & 1) == 1)
                    {
//                            this.ailefindmove.setBreakGoal(this.in_blockpos.getX(), this.in_blockpos.getY(), this.in_blockpos.getZ());
                        if (this.ailesetlocation.far == 0 || this.ailesetlocation.blockpos == null || isInArea(this.in_blockpos, this.ailesetlocation.blockpos, this.ailesetlocation.far))
                        {
                            this.ailefindmove.setGoal(this.in_blockpos.getX(), this.in_blockpos.getY(), this.in_blockpos.getZ());
                        }
                    }
                }
            }
            if (out)
            {
                if ((this.state & 8) == 8)
                {
                    this.out_blockpos = new BlockPos(e.posX + random.nextInt(this.out_random) - random.nextInt(this.out_random), e.posY + random.nextInt(this.out_random) - random.nextInt(this.out_random), e.posZ + random.nextInt(this.out_random) - random.nextInt(this.out_random));
                }

                if (this.out_blockpos != null)
                {
//                Small.LOGGER.info("OUT!");
                    if ((this.state & 2) == 2 || e.getDistanceSq(this.out_blockpos) < 4.0D)
                    {
                        IBlockState iblockstate = world.getBlockState(this.out_blockpos);
                        Block block = iblockstate.getBlock();

                        if (block.hasTileEntity(iblockstate))
                        {
                            TileEntity tileentity = world.getTileEntity(this.out_blockpos);

                            if (tileentity instanceof IInventory)
                            {
                                IInventory iinventory = (IInventory)tileentity;

                                ItemStack itemstack = null;
                                for (int i = 0; i < iinventory.getSizeInventory(); ++i)
                                {
                                    ItemStack is = iinventory.getStackInSlot(i);
                                    if (!is.isEmpty())
                                    {
                                        itemstack = is;
                                        break;
                                    }
                                }

                                if (itemstack != null)
                                {
                                    int i = this.manage(inventory, itemstack);

                                    if (i >= 27-3*3+2+4 && i <= 27-3*3+2+4+3*3)
                                    {
                                        Container container = ((MixinInventoryCrafting)inventory.inventorycrafting).eventHandler();
                                        if (container != null)
                                        {
                                            container.onCraftMatrixChanged(inventory.inventorycrafting);
                                        }
                                    }
                                }
                            }
                        }

                        this.ailefindmove.endGoal();
//                            this.s.current_work_byte_array[this.s.bytele.MANAGE_ITEM() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.MANAGE_ITEM() % 8));
                    }
//                        else
                    else if ((this.s.a.state & 1) == 1)
                    {
//                            this.ailefindmove.setBreakGoal(this.out_blockpos.getX(), this.out_blockpos.getY(), this.out_blockpos.getZ());
                        if (this.ailesetlocation.far == 0 || this.ailesetlocation.blockpos == null || isInArea(this.out_blockpos, this.ailesetlocation.blockpos, this.ailesetlocation.far))
                        {
                            this.ailefindmove.setGoal(this.out_blockpos.getX(), this.out_blockpos.getY(), this.out_blockpos.getZ());
                        }
                    }
                }
            }
//            }

//            this.s.current_work_byte_array[this.s.bytele.MANAGE_ITEM() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.MANAGE_ITEM() % 8));
//            }
        }
    }

    @Override
    public void writeNBT(NBTTagCompound nbttagcompound)
    {
        if (this.in_blockpos != null)
        {
            nbttagcompound.setLong("AILeManageItem_in_blockpos", this.in_blockpos.toLong());
        }
        if (this.out_blockpos != null)
        {
            nbttagcompound.setLong("AILeManageItem_out_blockpos", this.out_blockpos.toLong());
        }
        nbttagcompound.setByte("AILeManageItem_state", this.state);
        nbttagcompound.setInteger("AILeManageItem_in_random", this.in_random);
        nbttagcompound.setInteger("AILeManageItem_out_random", this.out_random);
    }

    @Override
    public void readNBT(NBTTagCompound nbttagcompound)
    {
        if (nbttagcompound.hasKey("AILeManageItem_in_blockpos"))
        {
            this.in_blockpos = BlockPos.fromLong(nbttagcompound.getLong("AILeManageItem_in_blockpos"));
        }
        if (nbttagcompound.hasKey("AILeManageItem_out_blockpos"))
        {
            this.out_blockpos = BlockPos.fromLong(nbttagcompound.getLong("AILeManageItem_out_blockpos"));
        }
        this.state = nbttagcompound.getByte("AILeManageItem_state");
        this.in_random = nbttagcompound.getInteger("AILeManageItem_in_random");
        this.out_random = nbttagcompound.getInteger("AILeManageItem_out_random");
    }

    public int manage(IInventory in_iinventory, ItemStack itemstack)
    {
        for (int i = 0; i < in_iinventory.getSizeInventory(); ++i)
        {
            ItemStack inv_itemstack = in_iinventory.getStackInSlot(i);

            int max_stack = inv_itemstack.getMaxStackSize();
            int e_count = itemstack.getCount();

            if (inv_itemstack.isEmpty())
            {
                in_iinventory.setInventorySlotContents(i, itemstack.copy());
                itemstack.setCount(0);

                return i;
            }

            int max_count = inv_itemstack.getCount() + e_count;
            int count = max_count - max_stack;
            if (isSameItemSameTags(inv_itemstack, itemstack))
            {
                if (count <= 0)
                {
                    inv_itemstack.setCount(max_count);
                    itemstack.setCount(0);

                    return i;
                }
                else
                {
                    inv_itemstack.setCount(max_stack);
                    itemstack.setCount(count);
                }
            }
        }

        return -1;
    }
}
