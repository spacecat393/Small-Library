package com.nali.small.entities.skinning;

import com.nali.data.BothData;
import com.nali.list.messages.ClientMessage;
import com.nali.list.messages.ServerMessage;
import com.nali.render.ObjectRender;
import com.nali.render.SkinningRender;
import com.nali.small.Small;
import com.nali.small.entities.EntitiesAttackHelper;
import com.nali.small.entities.bytes.WorkBytes;
import com.nali.small.entities.memory.BothEntitiesMemory;
import com.nali.small.entities.memory.ClientEntitiesMemory;
import com.nali.small.entities.memory.server.EntitiesAIMemory;
import com.nali.small.entities.memory.server.ServerEntitiesMemory;
import com.nali.small.entities.skinning.render.layer.ArrowLayerRender;
import com.nali.small.entities.skinning.render.layer.ItemLayerRender;
import com.nali.small.entities.skinning.works.SkinningEntitiesBodyYaw;
import com.nali.small.entities.skinning.works.SkinningEntitiesPat;
import com.nali.small.mixin.IMixinEntityCreeper;
import com.nali.small.mixin.IMixinEntityLivingBase;
import com.nali.small.networks.NetworksRegistry;
import com.nali.small.world.ChunkLoader;
import com.nali.system.bytes.BytesReader;
import com.nali.system.bytes.BytesWriter;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.UUID;

import static com.nali.list.handlers.ServerHandler.canPass;
import static com.nali.small.entities.EntitiesMath.rayAllTargetsView;
import static com.nali.small.entities.memory.server.ServerEntitiesMemory.ENTITIES_MAP;
import static com.nali.small.world.ChunkCallBack.CHUNK_MAP;

public abstract class SkinningEntities extends EntityLivingBase
{
    public BothEntitiesMemory bothentitiesmemory;

    public static DataParameter<ItemStack> MOUTH_ITEMSTACK_DATAPARAMETER = EntityDataManager.<ItemStack>createKey(SkinningEntities.class, DataSerializers.ITEM_STACK);

    public SkinningEntities(World world)
    {
        super(world);

        BothData bothdata = this.createBothData();
        WorkBytes workbytes = this.createWorkBytes();
        float scale = bothdata.Scale();
        int max_works = workbytes.MAX_WORKS();

        if (world.isRemote)
        {
            ClientEntitiesMemory cliententitiesmemory = this.createClientEntitiesMemory(bothdata, workbytes);
            this.bothentitiesmemory = cliententitiesmemory;
            this.width = bothdata.Width() * scale;
            this.height = bothdata.Height() * scale;

            cliententitiesmemory.arrowlayerrender = new ArrowLayerRender(this);
            cliententitiesmemory.itemlayerrender = new ItemLayerRender(this);
            cliententitiesmemory.objectrender = (ObjectRender)this.createObjectRender();
            cliententitiesmemory.work_byte_array = new byte[max_works];
            cliententitiesmemory.skinningentitiespat = new SkinningEntitiesPat(this);
            cliententitiesmemory.sync_byte_array = new byte[bothdata.MaxSync()];
        }
        else
        {
            ServerEntitiesMemory serverentitiesmemory = this.createServerEntitiesMemory(bothdata, workbytes);
            this.bothentitiesmemory = serverentitiesmemory;
            this.width = 0.5F;
            this.height = 0.5F;

            ChunkLoader.updateChunk(this);
            serverentitiesmemory.entitiesaimemory = new EntitiesAIMemory(this);
            serverentitiesmemory.main_work_byte_array = new byte[max_works];
            serverentitiesmemory.current_work_byte_array = new byte[max_works];
            serverentitiesmemory.frame_int_array = new int[this.getIntegerDataParameterArray().length];
            serverentitiesmemory.sync_byte_array = new byte[bothdata.MaxSync()];

            this.createServer();
            this.getDataManager().set(this.getFloatDataParameterArray()[0], scale);
        }

        this.bothentitiesmemory.skinningentitiesbody = new SkinningEntitiesBodyYaw(this);
    }

    @Override
    public void entityInit()
    {
        super.entityInit();

        DataParameter<Byte>[] byte_dataparameter_array = this.getByteDataParameterArray();
        for (DataParameter<Byte> byte_dataparameter : byte_dataparameter_array)
        {
            this.dataManager.register(byte_dataparameter, (byte)0);
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

        this.dataManager.register(MOUTH_ITEMSTACK_DATAPARAMETER, ItemStack.EMPTY);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);

        EntityDataManager entitydatamanager = this.getDataManager();

        int i = 0;

        DataParameter<Byte>[] byte_dataparameter_array = this.getByteDataParameterArray();
        for (DataParameter<Byte> byte_dataparameter : byte_dataparameter_array)
        {
            nbttagcompound.setInteger("byte_" + i++, entitydatamanager.get(byte_dataparameter));
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

        if (!this.world.isRemote)
        {
            ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.bothentitiesmemory;
            if (serverentitiesmemory.owner_uuid != null)
            {
                byte[] byte_array = new byte[16];
                BytesWriter.set(byte_array, serverentitiesmemory.owner_uuid, 0);
                nbttagcompound.setByteArray("owner_uuid", byte_array);
            }

            serverentitiesmemory.entitiesaimemory.writeNBT(nbttagcompound);

            if (serverentitiesmemory.main_work_byte_array == null)
            {
                serverentitiesmemory.main_work_byte_array = new byte[serverentitiesmemory.workbytes.MAX_WORKS()];
            }

            if (!serverentitiesmemory.sus_init)
            {
                serverentitiesmemory.main_work_byte_array[serverentitiesmemory.workbytes.LOCK_INVENTORY()] = 1;
                serverentitiesmemory.main_work_byte_array[serverentitiesmemory.workbytes.LOCK_DAMAGE()] = 1;

                if (serverentitiesmemory.workbytes.FOLLOW() != -1)
                {
                    serverentitiesmemory.main_work_byte_array[serverentitiesmemory.workbytes.FOLLOW()] = 1;
                }
                if (serverentitiesmemory.workbytes.RANDOM_WALK() != -1)
                {
                    serverentitiesmemory.main_work_byte_array[serverentitiesmemory.workbytes.RANDOM_WALK()] = 1;
                }
                if (serverentitiesmemory.workbytes.RANDOM_LOOK() != -1)
                {
                    serverentitiesmemory.main_work_byte_array[serverentitiesmemory.workbytes.RANDOM_LOOK()] = 1;
                }
                if (serverentitiesmemory.workbytes.CARE_OWNER() != -1)
                {
                    serverentitiesmemory.main_work_byte_array[serverentitiesmemory.workbytes.CARE_OWNER()] = 1;
                }
                nbttagcompound.setByteArray("work_bytes", serverentitiesmemory.main_work_byte_array);
                nbttagcompound.setFloat("float_0", serverentitiesmemory.bothdata.Scale());
                this.initWriteEntityToNBT(nbttagcompound);
            }

            nbttagcompound.setByteArray("work_bytes", serverentitiesmemory.main_work_byte_array);
        }

        nbttagcompound.setBoolean("sus_init", true);

        this.bothentitiesmemory.skinninginventory.writeNBT(nbttagcompound);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);

        EntityDataManager entitydatamanager = this.getDataManager();

        int i = 0;

        byte[] work_bytes = nbttagcompound.getByteArray("work_bytes");
        if (!this.world.isRemote)
        {
            ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.bothentitiesmemory;
            if (work_bytes.length == serverentitiesmemory.main_work_byte_array.length)
            {
                serverentitiesmemory.main_work_byte_array = work_bytes;
            }
        }
        else
        {
            ClientEntitiesMemory cliententitiesmemory = (ClientEntitiesMemory)this.bothentitiesmemory;
            this.initFakeFrame();
            cliententitiesmemory.objectrender.scale = cliententitiesmemory.bothdata.Scale();
        }

        DataParameter<Byte>[] byte_dataparameter_array = this.getByteDataParameterArray();
        for (DataParameter<Byte> byte_dataparameter : byte_dataparameter_array)
        {
            entitydatamanager.set(byte_dataparameter, nbttagcompound.getByte("byte_" + i++));
        }
        i = 0;

        DataParameter<Integer>[] integer_dataparameter_array = this.getIntegerDataParameterArray();
        for (DataParameter<Integer> integer_dataparameter : integer_dataparameter_array)
        {
            entitydatamanager.set(integer_dataparameter, nbttagcompound.getInteger("int_" + i++));
        }
        i = 0;

        DataParameter<Float>[] float_dataparameter_array = this.getFloatDataParameterArray();
        for (DataParameter<Float> float_dataparameter : float_dataparameter_array)
        {
            entitydatamanager.set(float_dataparameter, nbttagcompound.getFloat("float_" + i++));
        }

        if (nbttagcompound.hasKey("ArmorItems", 9))
        {
            NBTTagList nbttaglist = nbttagcompound.getTagList("ArmorItems", 10);

            for (int l = 0; l < this.bothentitiesmemory.skinninginventory.armor_itemstack_nonnulllist.size(); ++l)
            {
                this.bothentitiesmemory.skinninginventory.armor_itemstack_nonnulllist.set(l, new ItemStack(nbttaglist.getCompoundTagAt(l)));
            }
        }

        if (nbttagcompound.hasKey("HandItems", 9))
        {
            NBTTagList nbttaglist1 = nbttagcompound.getTagList("HandItems", 10);

            for (int j = 0; j < this.bothentitiesmemory.skinninginventory.hands_itemstack_nonnulllist.size(); ++j)
            {
                this.bothentitiesmemory.skinninginventory.hands_itemstack_nonnulllist.set(j, new ItemStack(nbttaglist1.getCompoundTagAt(j)));
            }
        }

        for (int k = 0; k < this.bothentitiesmemory.skinninginventory.getSizeInventory(); ++k)
        {
            String key = "ib" + k;

            if (nbttagcompound.hasKey(key, 10))
            {
                this.bothentitiesmemory.skinninginventory.setInventorySlotContents(k, new ItemStack(nbttagcompound.getCompoundTag(key)));
            }
        }

        if (!this.world.isRemote)
        {
            ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.bothentitiesmemory;
            if (nbttagcompound.hasKey("owner_uuid", 7))
            {
                serverentitiesmemory.owner_uuid = BytesReader.getUUID(nbttagcompound.getByteArray("owner_uuid"), 0);
            }

            serverentitiesmemory.entitiesaimemory.readNBT(nbttagcompound);

            serverentitiesmemory.sus_init = nbttagcompound.hasKey("sus_init");

            if (!serverentitiesmemory.sus_init)
            {
                serverentitiesmemory.main_work_byte_array[serverentitiesmemory.workbytes.LOCK_INVENTORY()] = 1;
                serverentitiesmemory.main_work_byte_array[serverentitiesmemory.workbytes.LOCK_DAMAGE()] = 1;

                if (serverentitiesmemory.workbytes.FOLLOW() != -1)
                {
                    serverentitiesmemory.main_work_byte_array[serverentitiesmemory.workbytes.FOLLOW()] = 1;
                }
                if (serverentitiesmemory.workbytes.RANDOM_WALK() != -1)
                {
                    serverentitiesmemory.main_work_byte_array[serverentitiesmemory.workbytes.RANDOM_WALK()] = 1;
                }
                if (serverentitiesmemory.workbytes.RANDOM_LOOK() != -1)
                {
                    serverentitiesmemory.main_work_byte_array[serverentitiesmemory.workbytes.RANDOM_LOOK()] = 1;
                }
                if (serverentitiesmemory.workbytes.CARE_OWNER() != -1)
                {
                    serverentitiesmemory.main_work_byte_array[serverentitiesmemory.workbytes.CARE_OWNER()] = 1;
                }
                entitydatamanager.set(float_dataparameter_array[0], this.bothentitiesmemory.bothdata.Scale());
                this.initReadEntityFromNBT();
                serverentitiesmemory.sus_init = true;
            }
        }
    }

    @Override
    public Iterable<ItemStack> getHeldEquipment()
    {
        return this.bothentitiesmemory.skinninginventory.hands_itemstack_nonnulllist;
    }

    @Override
    public Iterable<ItemStack> getArmorInventoryList()
    {
        return this.bothentitiesmemory.skinninginventory.armor_itemstack_nonnulllist;
    }

    @Override
    public ItemStack getItemStackFromSlot(EntityEquipmentSlot entityequipmentslot)
    {
        switch (entityequipmentslot.getSlotType())
        {
            case HAND:
            {
                return this.bothentitiesmemory.skinninginventory.hands_itemstack_nonnulllist.get(entityequipmentslot.getIndex());
            }
            case ARMOR:
            {
                return this.bothentitiesmemory.skinninginventory.armor_itemstack_nonnulllist.get(entityequipmentslot.getIndex());
            }
            default:
            {
                return ItemStack.EMPTY;
            }
        }
    }

    @Override
    public void setItemStackToSlot(EntityEquipmentSlot entityequipmentslot, ItemStack itemstack)
    {
        this.playEquipSound(itemstack);
        switch (entityequipmentslot.getSlotType())
        {
            case HAND:
            {
                this.bothentitiesmemory.skinninginventory.hands_itemstack_nonnulllist.set(entityequipmentslot.getIndex(), itemstack);
                break;
            }
            case ARMOR:
            {
                this.bothentitiesmemory.skinninginventory.armor_itemstack_nonnulllist.set(entityequipmentslot.getIndex(), itemstack);
                break;
            }
            default:
            {
                break;
            }
        }
    }

    @Override
    public EnumHandSide getPrimaryHand()
    {
        return EnumHandSide.RIGHT;
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        EntityDataManager entitydatamanager = this.getDataManager();
        if (this.world.isRemote)
        {
            ClientEntitiesMemory cliententitiesmemory = (ClientEntitiesMemory)this.bothentitiesmemory;

            if (!cliententitiesmemory.fake)
            {
                DataParameter<Byte>[] byte_dataparameter_array = this.getByteDataParameterArray();
                for (int i = 0; i < byte_dataparameter_array.length; ++i)
                {
                    cliententitiesmemory.sync_byte_array[i] = this.getDataManager().get(byte_dataparameter_array[i]);
                }

                this.updateClientObject();
                cliententitiesmemory.mouth_itemstack = entitydatamanager.get(MOUTH_ITEMSTACK_DATAPARAMETER);
            }

            this.updateClient();
        }
        else
        {
            ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.bothentitiesmemory;
            DataParameter<Integer>[] integer_dataparameter = this.getIntegerDataParameterArray();

            ItemStack mouth_itemstack = serverentitiesmemory.skinninginventory.offset_itemstack_nonnulllist.get(0);
            if (mouth_itemstack != serverentitiesmemory.current_mouth_itemstack)
            {
                serverentitiesmemory.current_mouth_itemstack = mouth_itemstack;
                entitydatamanager.set(MOUTH_ITEMSTACK_DATAPARAMETER, mouth_itemstack);
            }

            if (!CHUNK_MAP.containsKey(this))
            {
                ChunkLoader.updateChunk(this);
            }

            System.arraycopy(serverentitiesmemory.main_work_byte_array, 0, serverentitiesmemory.current_work_byte_array, 0, serverentitiesmemory.current_work_byte_array.length);

            for (int i = 0; i < serverentitiesmemory.frame_int_array.length; ++i)
            {
                serverentitiesmemory.frame_int_array[i] = entitydatamanager.get(integer_dataparameter[i]);
            }

            this.updateServer();
            this.width = 0.5F;
            this.height = 0.5F;

            UUID uuid = this.getUniqueID();

            if (!uuid.equals(serverentitiesmemory.uuid))
            {
                if (serverentitiesmemory.uuid != null)
                {
                    ENTITIES_MAP.remove(serverentitiesmemory.uuid);
                }

                ENTITIES_MAP.put(uuid, this);
                serverentitiesmemory.uuid = uuid;
            }

            serverentitiesmemory.entitiesaimemory.update();
        }
    }

    @Override
    public double getYOffset()
    {
        return 0.3D;
    }

    @Override
    public void damageArmor(float damage)
    {
        damage = damage / 4.0F;

        if (damage < 1.0F)
        {
            damage = 1.0F;
        }

        for (int i = 0; i < this.bothentitiesmemory.skinninginventory.armor_itemstack_nonnulllist.size(); ++i)
        {
            ItemStack itemstack = this.bothentitiesmemory.skinninginventory.armor_itemstack_nonnulllist.get(i);

            if (itemstack.getItem() instanceof ItemArmor)
            {
                itemstack.damageItem((int)damage, this);
            }
        }
    }

    @Override
    public Vec3d getLook(float partialTicks)
    {
        if (partialTicks == 1.0F)
        {
            return this.getVectorForRotation(this.rotationPitch, this.rotationYaw);
        }
        else
        {
            float f = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * partialTicks;
            float f1 = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * partialTicks;
            return this.getVectorForRotation(f, f1);
        }
    }

    @Override
    public void damageShield(float damage)
    {
        if (damage >= 3.0F && this.activeItemStack.getItem().isShield(this.activeItemStack, this))
        {
            int i = 1 + MathHelper.floor(damage);
            this.activeItemStack.damageItem(i, this);

            if (this.activeItemStack.isEmpty())
            {
                EnumHand enumhand = this.getActiveHand();

                if (enumhand == EnumHand.MAIN_HAND)
                {
                    this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemStack.EMPTY);
                }
                else
                {
                    this.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, ItemStack.EMPTY);
                }

                this.activeItemStack = ItemStack.EMPTY;
                this.playSound(SoundEvents.ITEM_SHIELD_BREAK, 0.8F, 0.8F + this.world.rand.nextFloat() * 0.4F);
            }
        }
    }

    @Override
    public boolean isPushedByWater()
    {
        return false;
    }

    @Override
    public float updateDistance(float f0, float f1)
    {
        this.bothentitiesmemory.skinningentitiesbody.run();
        return f1;
    }

    @Override
    public void onDeath(DamageSource damagesource)
    {
    }

    @Override
    public void onDeathUpdate()
    {

    }

    @Override
    public boolean processInitialInteract(EntityPlayer entityplayer, EnumHand enumhand)
    {
        if (entityplayer.isSneaking())
        {
            if (!this.world.isRemote)
            {
                this.sendInventoryGUI((EntityPlayerMP)entityplayer);
            }
        }
        else
        {
            if (this.world.isRemote && this.canEat())
            {
                ClientEntitiesMemory cliententitiesmemory = (ClientEntitiesMemory)this.bothentitiesmemory;
                Item item = entityplayer.getHeldItemMainhand().getItem();
                boolean milk_bucket = item == Items.MILK_BUCKET;
                boolean eat_state = item instanceof ItemFood || milk_bucket;

                AxisAlignedBB[] axisalignedbb_array;
                if (eat_state)
                {
                    axisalignedbb_array = new AxisAlignedBB[]
                    {
                        this.getEntityBoundingBox(),
                        this.getMouthAxisAlignedBB()
                    };
                }
                else
                {
                    axisalignedbb_array = new AxisAlignedBB[]
                    {
                        this.getHeadAxisAlignedBB()
                    };
                }
                int state = rayAllTargetsView(entityplayer, axisalignedbb_array, (byte)50);

                if (state == 0 && !eat_state && this.isMove() && this.canPat())
                {
                    cliententitiesmemory.skinningentitiespat.run();
                }
                else if (state == 1)
                {
                    byte[] byte_array;
                    if (milk_bucket)
                    {
                        byte_array = new byte[1 + 16];
                        byte_array[0] = 19;
                    }
                    else
                    {
                        byte_array = new byte[1 + 16 + 4 + 4 + 4];
                        byte_array[0] = 17;
                        BytesWriter.set(byte_array, (float)(axisalignedbb_array[1].maxX + (axisalignedbb_array[1].minX - axisalignedbb_array[1].maxX) / 2.0D), 1 + 16);
                        BytesWriter.set(byte_array, (float)(axisalignedbb_array[1].maxY + (axisalignedbb_array[1].minY - axisalignedbb_array[1].maxY) / 2.0D), 1 + 16 + 4);
                        BytesWriter.set(byte_array, (float)(axisalignedbb_array[1].maxZ + (axisalignedbb_array[1].minZ - axisalignedbb_array[1].maxZ) / 2.0D), 1 + 16 + 4 + 4);
                    }

                    BytesWriter.set(byte_array, cliententitiesmemory.uuid, 1);
                    NetworksRegistry.I.sendToServer(new ServerMessage(byte_array));
                }
                else
                {
                    int i = cliententitiesmemory.workbytes.SIT();
                    byte[] byte_array = new byte[22];
                    byte_array[0] = 1;
                    BytesWriter.set(byte_array, cliententitiesmemory.uuid, 1);
                    BytesWriter.set(byte_array, i, 17);
                    byte_array[21] = cliententitiesmemory.work_byte_array[i] == 1 ? (byte)0 : (byte)1;
                    NetworksRegistry.I.sendToServer(new ServerMessage(byte_array));
                }
            }
        }

        entityplayer.swingArm(enumhand);
        return true;
    }

    @Override
    public boolean attackEntityFrom(DamageSource damagesource, float amount)
    {
        if (!this.world.isRemote)
        {
            ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.bothentitiesmemory;
            if (serverentitiesmemory.main_work_byte_array[serverentitiesmemory.workbytes.LOCK_DAMAGE()] == 1)
            {
                Entity owner_entity = this.getOwner();
                if (owner_entity != null && owner_entity.equals(damagesource.getTrueSource()))
                {
                    return false;
                }
            }
        }

        return super.attackEntityFrom(damagesource, amount);
    }

    @Override
    public boolean attackEntityAsMob(Entity entity)
    {
        entity.hurtResistantTime = 0;
        if (entity instanceof EntityLivingBase)
        {
            EntityLivingBase entitylivingbase = (EntityLivingBase)entity;
            ((IMixinEntityLivingBase)entitylivingbase).recentlyHit(20);
            entitylivingbase.maxHurtTime = 0;
            entitylivingbase.hurtTime = 0;
        }

        if (entity instanceof EntityCreeper)
        {
            EntityCreeper entitycreeper = (EntityCreeper)entity;
            ((IMixinEntityCreeper)entitycreeper).timeSinceIgnited(1);
        }

        EntityLivingBase by_entitylivingbase = this;

        if (!this.world.isRemote)
        {
            ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.bothentitiesmemory;
            Entity owner_entity = this.getOwner();
            if (serverentitiesmemory.main_work_byte_array[serverentitiesmemory.workbytes.LOCK_DAMAGE()] == 1 && owner_entity instanceof EntityLivingBase)
            {
                by_entitylivingbase = (EntityLivingBase)owner_entity;
            }
        }

        float f = (float)this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
        int i = 0;

        ItemStack mainhand_itemstack = this.getHeldItemMainhand();

        if (entity instanceof EntityLivingBase)
        {
            f += EnchantmentHelper.getModifierForCreature(mainhand_itemstack, ((EntityLivingBase)entity).getCreatureAttribute());
            i += EnchantmentHelper.getKnockbackModifier(this);
        }

        boolean flag = EntitiesAttackHelper.doAttackEntityFrom(entity, this, DamageSource.causeMobDamage(by_entitylivingbase), f);

        if (flag)
        {
            mainhand_itemstack.damageItem(1, this);
            if (i > 0 && entity instanceof EntityLivingBase)
            {
                ((EntityLivingBase)entity).knockBack(this, (float)i * 0.5F, MathHelper.sin(this.rotationYaw * 0.017453292F), -MathHelper.cos(this.rotationYaw * 0.017453292F));
                this.motionX *= 0.6D;
                this.motionZ *= 0.6D;
            }

            int j = EnchantmentHelper.getFireAspectModifier(this);

            if (j > 0)
            {
                entity.setFire(j * 4);
            }

//            if (entity instanceof EntityPlayer)
//            {
//                EntityPlayer entityplayer = (EntityPlayer)entity;
//                ItemStack itemstack = this.getHeldItemMainhand();
//                ItemStack itemstack1 = entityplayer.isHandActive() ? entityplayer.getActiveItemStack() : ItemStack.EMPTY;
//
//                if (!itemstack.isEmpty() && !itemstack1.isEmpty() && itemstack.getItem().canDisableShield(itemstack, itemstack1, entityplayer, this) && itemstack1.getItem().isShield(itemstack1, entityplayer))
//                {
//                    float f1 = 0.25F + (float)EnchantmentHelper.getEfficiencyModifier(this) * 0.05F;
//
//                    if (this.rand.nextFloat() < f1)
//                    {
//                        entityplayer.getCooldownTracker().setCooldown(itemstack1.getItem(), 100);
//                        this.world.setEntityState(entityplayer, (byte)30);
//                    }
//                }
//            }

            this.applyEnchantments(this, entity);
        }

        return flag;
    }

    @Override
    public void setAIMoveSpeed(float speedIn)
    {
        super.setAIMoveSpeed(speedIn);
        this.moveForward = speedIn;
    }

    @Override
    public void heal(float value)
    {
        value = net.minecraftforge.event.ForgeEventFactory.onLivingHeal(this, value);
        if (value <= 0) return;
        float health = this.getHealth();
        this.setHealth(health + value);
    }

    @Override
    public boolean isMovementBlocked()
    {
        return false;
    }

    @Override
    public boolean isEntityAlive()
    {
        return !this.isDead;
    }

    @Override
    public float getSoundVolume()
    {
        return super.getSoundVolume();
    }

    @Override
    public float getSoundPitch()
    {
        return super.getSoundPitch();
    }

    public void sendInventoryGUI(EntityPlayerMP entityplayermp)
    {
        if (canPass(this, entityplayermp))
        {
            entityplayermp.getEntityData().setUniqueId("loli_nali", this.getUniqueID());
            ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.bothentitiesmemory;
            entityplayermp.openGui(Small.I, 0, entityplayermp.world, this.getEntityId(), 0, 0);

            byte[] byte_array = new byte[1 + 4 + serverentitiesmemory.main_work_byte_array.length];
            byte_array[0] = 6;
            BytesWriter.set(byte_array, this.getEntityId(), 1);
            System.arraycopy(serverentitiesmemory.main_work_byte_array, 0, byte_array, 1 + 4, serverentitiesmemory.main_work_byte_array.length);
            NetworksRegistry.I.sendTo(new ClientMessage(byte_array), entityplayermp);
        }
    }

    public boolean canPat()
    {
        return true;
    }

    public boolean canEat()
    {
        return true;
    }

    public void onShouldPlayWith()
    {
        ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.bothentitiesmemory;
        SkinningEntities playwith_skinningentities = serverentitiesmemory.entitiesaimemory.skinningentitiesplaywith.playwith_skinningentities;
        this.setPositionAndUpdate(playwith_skinningentities.posX, playwith_skinningentities.posY, playwith_skinningentities.posZ);
        this.rotationYaw = playwith_skinningentities.rotationYaw;
        this.rotationPitch = playwith_skinningentities.rotationPitch;
        this.renderYawOffset = playwith_skinningentities.renderYawOffset;
    }

    public void updateClientObject()
    {
        ClientEntitiesMemory cliententitiesmemory = (ClientEntitiesMemory)this.bothentitiesmemory;
        EntityDataManager entitydatamanager = this.getDataManager();

        if (cliententitiesmemory.uuid == null)
        {
            byte[] byte_array = new byte[5];
            byte_array[0] = 0;
            BytesWriter.set(byte_array, this.getEntityId(), 1);
            NetworksRegistry.I.sendToServer(new ServerMessage(byte_array));
        }

        cliententitiesmemory.objectrender.scale = entitydatamanager.get(this.getFloatDataParameterArray()[0]);
        this.updateRendering(entitydatamanager);
    }

    public void updateRendering(EntityDataManager entitydatamanager)
    {
        ClientEntitiesMemory cliententitiesmemory = (ClientEntitiesMemory)this.bothentitiesmemory;
        SkinningRender skinningrender = (SkinningRender)cliententitiesmemory.objectrender;
        DataParameter<Integer>[] integer_dataparameter = this.getIntegerDataParameterArray();

        for (int x = 0; x < skinningrender.frame_int_array.length; ++x)
        {
            skinningrender.frame_int_array[x] = entitydatamanager.get(integer_dataparameter[x++]);
        }
    }

    public boolean isMove()
    {
        return !this.isDead && this.getHealth() > 0.0F;
    }

    public boolean isZeroMove()
    {
        return this.getHealth() <= 0.0F;
    }

    public boolean isWork(int index)
    {
        ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.bothentitiesmemory;
        for (int i = serverentitiesmemory.workbytes.SIT(); i < index; ++i)
        {
            if (serverentitiesmemory.current_work_byte_array[i] > 0)
            {
                return false;
            }
        }

        return serverentitiesmemory.current_work_byte_array[index] != 0;
    }

    public boolean isWorkBypass(int index, int[] bypass_int_array)
    {
        ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.bothentitiesmemory;
        for (int i = serverentitiesmemory.workbytes.SIT(); i < index; ++i)
        {
            boolean on_bypass = false;
            for (int bypass : bypass_int_array)
            {
                if (i == bypass)
                {
                    on_bypass = true;
                    break;
                }
            }

            if (!on_bypass && serverentitiesmemory.current_work_byte_array[i] > 0)
            {
                return false;
            }
        }

        return serverentitiesmemory.current_work_byte_array[index] != 0;
    }

    public Entity getOwner()
    {
        ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.bothentitiesmemory;
        if (serverentitiesmemory.owner_uuid == null)
        {
            return null;
        }

        return ((WorldServer)this.world).getEntityFromUuid(serverentitiesmemory.owner_uuid);
    }

    public Material getMaterial(BlockPos blockpos)
    {
        IBlockState temp_iblockstate = this.world.getBlockState(blockpos);
        return temp_iblockstate.getMaterial();
    }

    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getHeadAxisAlignedBB()
    {
        double hw = this.width / 2.0F + 0.001F;
        double y = this.posY + this.height / 1.125F;

        return new AxisAlignedBB
        (
        this.posX - hw, y, this.posZ - hw,
        this.posX + hw, this.posY + this.height + 0.001F, this.posZ + hw
        );
    }

    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getMouthAxisAlignedBB()
    {
        if (this.isZeroMove())
        {
            return this.getEntityBoundingBox().grow(0.25F);
        }
        else
        {
            double hw = this.width / 2.0F;
            double hh = 0.5F;

            Vec3d view_vec3d = this.getLookVec().scale(0.25F);
            double x = this.posX + view_vec3d.x;
            double y = (this.posY + this.height / 2.0F) + view_vec3d.y;
            double z = this.posZ + view_vec3d.z;

            return new AxisAlignedBB
            (
            x - hw, y, z - hw,
            x + hw, y + hh, z + hw
            );
        }
    }


    public void removeFromMap()
    {
        if (!this.world.isRemote)
        {
            ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.bothentitiesmemory;
            ChunkLoader.removeChunk(this);
            ENTITIES_MAP.remove(serverentitiesmemory.uuid);
        }
    }

    public void updateClient()
    {
        float scale = this.getDataManager().get(this.getFloatDataParameterArray()[0]);
        this.width = this.bothentitiesmemory.bothdata.Width() * scale;
        this.height = this.bothentitiesmemory.bothdata.Height() * scale;
    }

    public void updateServer()
    {

    }

    @SideOnly(Side.CLIENT)
    public ClientEntitiesMemory createClientEntitiesMemory(BothData bothdata, WorkBytes workbytes)
    {
        return new ClientEntitiesMemory(bothdata, workbytes);
    }

    public ServerEntitiesMemory createServerEntitiesMemory(BothData bothdata, WorkBytes workbytes)
    {
        return new ServerEntitiesMemory(bothdata, workbytes);
    }

    public void initFakeFrame(){}
    public void initWriteEntityToNBT(NBTTagCompound nbttagcompound){}
    public void initReadEntityFromNBT(){}

    public abstract BothData createBothData();
    public abstract WorkBytes createWorkBytes();
    public abstract void createServer();
    public abstract DataParameter<Byte>[] getByteDataParameterArray();
    public abstract DataParameter<Integer>[] getIntegerDataParameterArray();
    public abstract DataParameter<Float>[] getFloatDataParameterArray();
    @SideOnly(Side.CLIENT)
    public abstract Object createObjectRender();
}