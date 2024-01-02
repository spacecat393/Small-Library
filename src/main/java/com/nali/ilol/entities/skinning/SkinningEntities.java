package com.nali.ilol.entities.skinning;

import com.google.common.base.Optional;
import com.nali.data.BothData;
import com.nali.ilol.ILOL;
import com.nali.ilol.entities.EntitiesAttackHelper;
import com.nali.ilol.entities.bytes.SkinningEntitiesBytes;
import com.nali.ilol.entities.skinning.ai.*;
import com.nali.ilol.world.ChunkLoader;
import com.nali.render.SkinningRender;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

import static com.nali.ilol.world.ChunkCallBack.CHUNK_MAP;

public abstract class SkinningEntities extends EntityLivingBase
{
    @SideOnly(Side.CLIENT)
    public Object client_object;

    public final static DataParameter<Optional<UUID>>[] UUID_OPTIONAL_DATAPARAMETER_ARRAY = new DataParameter[2];
    public boolean fake;
    public UUID client_uuid;
    public static Map<UUID, SkinningEntities> CLIENT_ENTITIES_MAP;
    public static Map<Integer, UUID> FAKE_CLIENT_ENTITIES_MAP;
    //    public UUID current_client_uuid;
    public static Map<UUID, SkinningEntities> SERVER_ENTITIES_MAP;
    public UUID current_server_uuid;
    public byte[] client_work_byte_array;
    public byte[] server_work_byte_array;
    public byte[] current_server_work_byte_array;
    public int[] server_frame_int_array;
    public SkinningEntitiesBody skinningentitiesbody = new SkinningEntitiesBody(this);
    public SkinningEntitiesArea skinningentitiesarea;
    public SkinningEntitiesFindMove skinningentitiesfindmove;
    public SkinningEntitiesLook skinningentitieslook;
    public SkinningEntitiesMove skinningentitiesmove;
    public SkinningEntitiesJump skinningentitiesjump;
    public SkinningEntitiesGetItem skinningentitiesgetitem;
    public SkinningEntitiesFollow skinningentitiesfollow;
    public SkinningEntitiesRandomWalk skinningentitiesrandomwalk;
    public SkinningEntitiesRandomLook skinningentitiesrandomlook;
    public SkinningEntitiesAttack skinningentitiesattack;
    public SkinningEntitiesRevive skinningentitiesrevive;
    public SkinningEntitiesLiveFrame[] server_skinningentitiesliveframe_array;
    public boolean server_sus_init;

    public SkinningEntitiesBytes skinningentitiesbytes;
    public BothData bothdata;
    public SkinningInventory skinninginventory = new SkinningInventory();
    //    public Vec3d position_vec3d;
//    public boolean moving;

    static
    {
        for (int i = 0; i < UUID_OPTIONAL_DATAPARAMETER_ARRAY.length; ++i)
        {
            UUID_OPTIONAL_DATAPARAMETER_ARRAY[i] = EntityDataManager.createKey(SkinningEntities.class, DataSerializers.OPTIONAL_UNIQUE_ID);
        }
    }

    public SkinningEntities(World world)
    {
        super(world);

        this.bothdata = this.createBothData();
        this.skinningentitiesbytes = this.createBytes();
        float scale = this.bothdata.Scale();
        this.width = this.bothdata.Width() * scale;
        this.height = this.bothdata.Height() * scale;

        if (world.isRemote)
        {
            this.client_object = this.createClientObject();
            this.client_work_byte_array = new byte[this.getByteDataParameterArray().length];
        }
        else
        {
            ChunkLoader.updateChunk(this);
            this.skinningentitiesarea = new SkinningEntitiesArea(this);
            this.skinningentitiesfindmove = new SkinningEntitiesFindMove(this);
            this.skinningentitieslook = new SkinningEntitiesLook(this);
            this.skinningentitiesmove = new SkinningEntitiesMove(this);
            this.skinningentitiesjump = new SkinningEntitiesJump(this);
            this.skinningentitiesgetitem = new SkinningEntitiesGetItem(this);
            this.skinningentitiesfollow = new SkinningEntitiesFollow(this);
            this.skinningentitiesrandomwalk = new SkinningEntitiesRandomWalk(this);
            this.skinningentitiesrandomlook = new SkinningEntitiesRandomLook(this);
            this.skinningentitiesattack = new SkinningEntitiesAttack(this);
            this.skinningentitiesrevive = new SkinningEntitiesRevive(this);

            this.server_work_byte_array = new byte[this.getByteDataParameterArray().length];
            this.current_server_work_byte_array = new byte[this.server_work_byte_array.length];
            int size = this.getIntegerDataParameterArray().length - this.bothdata.MaxPart();
            this.server_skinningentitiesliveframe_array = new SkinningEntitiesLiveFrame[size];
            this.server_frame_int_array = new int[size];
            this.createServer();
            this.getDataManager().set(this.getFloatDataParameterArray()[0], scale);

//            UUID uuid = this.getUniqueID();
//            this.current_server_uuid = uuid;
//            this.getDataManager().set(UUID_OPTIONAL_DATAPARAMETER_ARRAY[0], Optional.fromNullable(uuid));
//
////            if (!SERVER_ENTITIES_MAP.containsKey(uuid))
//            {
//                SERVER_ENTITIES_MAP.put(uuid, this);
//            }
        }
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

        for (DataParameter<Optional<UUID>> uuid_optional_dataparameter : UUID_OPTIONAL_DATAPARAMETER_ARRAY)
        {
            this.getDataManager().register(uuid_optional_dataparameter, Optional.absent());
        }
    }

    //NBTBase.NBT_TYPES
    @Override
    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
        this.writeEntityToNBTHelper(nbttagcompound);
        this.writeInventoryNBT(nbttagcompound);
    }

    public void writeEntityToNBTHelper(NBTTagCompound nbttagcompound)
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

//        for (DataParameter<Optional<UUID>> uuid_optional_dataparameter : UUID_OPTIONAL_DATAPARAMETER_ARRAY)
        for (i = 0; i < UUID_OPTIONAL_DATAPARAMETER_ARRAY.length; ++i)
        {
            UUID uuid = this.getUUID(i);

            if (uuid != null)
            {
                nbttagcompound.setString("uuid_optional" + i, uuid.toString());
            }
        }

        //write inv

//        String hand_key = "is_hand_active";
//        nbttagcompound.setByte(hand_key, this.getDataManager().get(HAND_STATES));

        if (!this.server_sus_init)
        {
            nbttagcompound.setByte("byte_" + this.skinningentitiesbytes.RANDOM_WALK(), (byte)1);
            nbttagcompound.setByte("byte_" + this.skinningentitiesbytes.RANDOM_LOOK(), (byte)1);
            nbttagcompound.setFloat("float_0", this.bothdata.Scale());
            this.initWriteEntityToNBT(nbttagcompound);
//            nbttagcompound.setByte(hand_key, (byte)1);
        }
        nbttagcompound.setBoolean("sus_init", true);

//        for (int i = 0; i < this.inventorybasic.getSizeInventory(); ++i)
//        {
//            ItemStack itemstack = this.inventorybasic.getStackInSlot(i);
//            if (!itemstack.isEmpty())
//            {
//                nbttagcompound.setTag("ib" + i, itemstack.writeToNBT(new NBTTagCompound()));
//            }
//        }
    }

    public void writeInventoryNBT(NBTTagCompound nbttagcompound)
    {
        NBTTagList nbttaglist = new NBTTagList();
        for (ItemStack itemstack : this.skinninginventory.armor_itemstack_nonnulllist)
        {
            NBTTagCompound new_nbttagcompound = new NBTTagCompound();

            if (!itemstack.isEmpty())
            {
                itemstack.writeToNBT(new_nbttagcompound);
            }

            nbttaglist.appendTag(new_nbttagcompound);
        }

        nbttagcompound.setTag("ArmorItems", nbttaglist);
        nbttaglist = new NBTTagList();

        for (ItemStack itemstack1 : this.skinninginventory.hands_itemstack_nonnulllist)
        {
            NBTTagCompound new_nbttagcompound = new NBTTagCompound();

            if (!itemstack1.isEmpty())
            {
                itemstack1.writeToNBT(new_nbttagcompound);
            }

            nbttaglist.appendTag(new_nbttagcompound);
        }

        nbttagcompound.setTag("HandItems", nbttaglist);

        for (int l = 0; l < this.skinninginventory.getSizeInventory(); ++l)
        {
            ItemStack itemstack = this.skinninginventory.getStackInSlot(l);
            if (!itemstack.isEmpty())
            {
                nbttagcompound.setTag("ib" + l, itemstack.writeToNBT(new NBTTagCompound()));
            }
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);

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
            entitydatamanager.set(integer_dataparameter, nbttagcompound.getInteger("int_" + i++));
        }
        i = 0;

        DataParameter<Float>[] float_dataparameter_array = this.getFloatDataParameterArray();
        for (DataParameter<Float> float_dataparameter : float_dataparameter_array)
        {
            entitydatamanager.set(float_dataparameter, nbttagcompound.getFloat("float_" + i++));
        }
        i = 0;

        for (DataParameter<Optional<UUID>> uuid_optional_dataparameter : UUID_OPTIONAL_DATAPARAMETER_ARRAY)
        {
            String key = "uuid_optional" + i++;

            if (nbttagcompound.hasKey(key, 8))
            {
                entitydatamanager.set(uuid_optional_dataparameter, Optional.fromNullable(UUID.fromString(nbttagcompound.getString(key))));
            }
        }

        if (nbttagcompound.hasKey("ArmorItems", 9))
        {
            NBTTagList nbttaglist = nbttagcompound.getTagList("ArmorItems", 10);

            for (int l = 0; l < this.skinninginventory.armor_itemstack_nonnulllist.size(); ++l)
            {
                this.skinninginventory.armor_itemstack_nonnulllist.set(l, new ItemStack(nbttaglist.getCompoundTagAt(l)));
            }
        }

        if (nbttagcompound.hasKey("HandItems", 9))
        {
            NBTTagList nbttaglist1 = nbttagcompound.getTagList("HandItems", 10);

            for (int j = 0; j < this.skinninginventory.hands_itemstack_nonnulllist.size(); ++j)
            {
                this.skinninginventory.hands_itemstack_nonnulllist.set(j, new ItemStack(nbttaglist1.getCompoundTagAt(j)));
            }
        }

        for (int k = 0; k < this.skinninginventory.getSizeInventory(); ++k)
        {
            String key = "ib" + k;

            if (nbttagcompound.hasKey(key, 10))
            {
                this.skinninginventory.setInventorySlotContents(k, new ItemStack(nbttagcompound.getCompoundTag(key)));
            }
        }

//        String hand_key = "is_hand_active";
//        if (nbttagcompound.hasKey(hand_key))
//        {
//            entitydatamanager.set(HAND_STATES, nbttagcompound.getByte(hand_key));
//        }

        this.server_sus_init = nbttagcompound.hasKey("sus_init");

        if (!this.server_sus_init)
        {
            entitydatamanager.set(byte_dataparameter_array[this.skinningentitiesbytes.RANDOM_WALK()], (byte)1);
            entitydatamanager.set(byte_dataparameter_array[this.skinningentitiesbytes.RANDOM_LOOK()], (byte)1);
//            entitydatamanager.set(HAND_STATES, (byte)1);
            entitydatamanager.set(float_dataparameter_array[0], this.bothdata.Scale());
            this.initReadEntityFromNBT();
            this.server_sus_init = true;
        }

//        for (int i = 0; i < this.inventorybasic.getSizeInventory(); ++i)
//        {
//            String key = "ib" + i;
//
//            if (nbttagcompound.hasKey(key, 10))
//            {
//                this.inventorybasic.setInventorySlotContents(i, new ItemStack(nbttagcompound.getCompoundTag(key)));
//            }
//        }
    }

    @Override
    public Iterable<ItemStack> getHeldEquipment()
    {
        return this.skinninginventory.hands_itemstack_nonnulllist;
    }

    @Override
    public Iterable<ItemStack> getArmorInventoryList()
    {
        return this.skinninginventory.armor_itemstack_nonnulllist;
    }

    @Override
    public ItemStack getItemStackFromSlot(EntityEquipmentSlot entityequipmentslot)
    {
        switch (entityequipmentslot.getSlotType())
        {
            case HAND:
            {
                return this.skinninginventory.hands_itemstack_nonnulllist.get(entityequipmentslot.getIndex());
            }
            case ARMOR:
            {
                return this.skinninginventory.armor_itemstack_nonnulllist.get(entityequipmentslot.getIndex());
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
                this.skinninginventory.hands_itemstack_nonnulllist.set(entityequipmentslot.getIndex(), itemstack);
                break;
            }
            case ARMOR:
            {
                this.skinninginventory.armor_itemstack_nonnulllist.set(entityequipmentslot.getIndex(), itemstack);
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

//    @Override
//    public boolean processInteract(EntityPlayer entityplayer, EnumHand enumhand)
//    {
//        if (entityplayer.isSneaking() && entityplayer.getHeldItemMainhand().isEmpty())
//        {
//            if (this.getEntityWorld().isRemote)
//            {
//                Nali.COMMONPROXY.openInventoryGui(this, entityplayer);
//            }
//            else
//            {
//                if (entityplayer.openContainer != entityplayer.inventoryContainer)
//                {
//                    entityplayer.closeScreen();
//                }
//
//                EntityPlayerMP entityplayermp = (EntityPlayerMP)entityplayer;
//                entityplayermp.getNextWindowId();
////                entityplayermp.connection.sendPacket(new SPacketOpenWindow(entityplayermp.currentWindowId, "EntityHorse", inventoryIn.getDisplayName(), inventoryIn.getSizeInventory(), horse.getEntityId()));
////                entityplayermp.openContainer = new ContainerHorseInventory(entityplayermp.inventory, inventoryIn, horse, entityplayermp);
//                entityplayermp.openContainer = new InventoryContainer(this, entityplayermp);
//                entityplayermp.openContainer.windowId = entityplayermp.currentWindowId;
//                entityplayermp.openContainer.addListener(entityplayermp);
//            }
////            entityplayer.openGui(Main.MAIN, 0, entityplayer.getEntityWorld(), 0, 0, 0);
//            return true;
//        }
//
//        return super.processInteract(entityplayer, enumhand);
//    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
        this.updateBox();

        //        Vec3d position_vec3d = this.getPositionVector();
//        if (this.position_vec3d != null && !this.position_vec3d.equals(position_vec3d))
//        {
//            this.moving = true;
//        }
//        else
//        {
//            this.moving = false;
//        }

        this.updateData();

//        if (this.ticksExisted % 20 == 0)
//        if (this.isMove())
//        {
//            this.skinningentitiesarea.onUpdate();
//            // will run first and won't run another because have work checker
//            this.skinningentitesgetitem.onUpdate();
//            this.skinningentitiesfindmove.onUpdate();
////            this.skinningentitieslook.onUpdate();
//
//            this.skinningentitiesmove.onUpdate();
//            this.skinningentitiesjump.onUpdate();
//        }

        if (this.getEntityWorld().isRemote)
        {
            if (!this.fake)
            {
                this.updateClientObject();
            }

            this.setInvisibility(this.client_object);

//            UUID uuid = this.getUUID(0);
//
////            if (!CLIENT_ENTITIES_MAP.containsKey(uuid))
////            if (this.current_client_uuid == null || !this.current_client_uuid.equals(uuid) || (this.current_client_uuid.equals(uuid) && CLIENT_ENTITIES_MAP.get(uuid) == null))
//            {
//                if (this.current_client_uuid != null)
//                {
//                    CLIENT_ENTITIES_MAP.remove(this.current_client_uuid);
//                }
//
//                CLIENT_ENTITIES_MAP.put(uuid, this);
//                this.current_client_uuid = uuid;
//            }
        }
        else
        {
            //
            UUID uuid = this.getUniqueID();
//            CutePomi.LOGGER.info("OPTIONAL UUID : " + this.getDataManager().get(UUID_OPTIONAL_DATAPARAMETER_ARRAY[0]).orNull());
//            CutePomi.LOGGER.info("Current UUID : " + uuid);

//            if (!SERVER_ENTITIES_MAP.containsKey(uuid))
//            if (this.current_server_uuid == null || !this.current_server_uuid.equals(uuid))
            {
                if (this.current_server_uuid != null)
                {
                    SERVER_ENTITIES_MAP.remove(this.current_server_uuid);
                }

                this.getDataManager().set(UUID_OPTIONAL_DATAPARAMETER_ARRAY[0], Optional.fromNullable(uuid));
                SERVER_ENTITIES_MAP.put(uuid, this);
                this.current_server_uuid = uuid;
            }
            //
            if (this.isMove())
            {
                this.skinningentitiesarea.onUpdate();
                this.skinningentitiesrevive.onUpdate();
                this.skinningentitiesfollow.onUpdate();
                this.skinningentitiesattack.onUpdate();
                this.skinningentitiesgetitem.onUpdate();
                this.skinningentitiesrandomwalk.onUpdate();
                this.skinningentitiesrandomlook.onUpdate();

                if (this.server_work_byte_array[this.skinningentitiesbytes.SIT()] == 0)
                {
                    this.skinningentitiesfindmove.onUpdate();
                    this.skinningentitiesmove.onUpdate();
                }

                this.skinningentitieslook.onUpdate();
            }
            else
            {
                this.skinningentitiesfindmove.path_blockpos_arraylist.clear();
            }

            this.skinningentitiesjump.onUpdate();

            for (SkinningEntitiesLiveFrame skinningentitiesliveframe : this.server_skinningentitiesliveframe_array)
            {
                skinningentitiesliveframe.onUpdate();
            }

//            Arrays.fill(this.server_work_boolean_array, false);
        }
    }

    @Override
    public double getYOffset()
    {
        return 0.3D;
    }

//    @Override
//    public void damageEntity(DamageSource damageSrc, float damageAmount)
//    {
//        if (!this.isEntityInvulnerable(damageSrc))
//        {
//            if (damageAmount <= 0) return;
//            damageAmount = net.minecraftforge.common.ISpecialArmor.ArmorProperties.applyArmor(this, this.skinninginventory.armor_itemstack_nonnulllist, damageSrc, damageAmount);
//        }
//
//        super.damageEntity(damageSrc, damageAmount);
//    }

    @Override
    public void damageArmor(float damage)
    {
        damage = damage / 4.0F;

        if (damage < 1.0F)
        {
            damage = 1.0F;
        }

        for (int i = 0; i < this.skinninginventory.armor_itemstack_nonnulllist.size(); ++i)
        {
            ItemStack itemstack = this.skinninginventory.armor_itemstack_nonnulllist.get(i);

            if (itemstack.getItem() instanceof ItemArmor)
            {
                itemstack.attemptDamageItem((int)damage, this.getRNG(), null);
            }
        }
    }

    @Override
    public void damageShield(float damage)
    {
        if (damage >= 3.0F && this.activeItemStack.getItem().isShield(this.activeItemStack, this))
        {
            int i = 1 + MathHelper.floor(damage);
            this.activeItemStack.attemptDamageItem(i, this.getRNG(), null);

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

//    @Override
//    protected float applyArmorCalculations(DamageSource source, float damage)
//    {
//        return super.applyArmorCalculations(source, damage);
//    }

    @SideOnly(Side.CLIENT)
    public void setInvisibility(Object object)
    {
        if (this.isInvisible() || this.isInvisibleToPlayer(Minecraft.getMinecraft().player))
        {
            SkinningRender skinningrender = (SkinningRender)object;
            Arrays.fill(skinningrender.model_boolean_array, false);
        }
        else
        {
            ((SkinningRender)this.client_object).setModel();
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
        this.skinningentitiesbody.onUpdate();//.updateRenderAngles();
        return f1;
//        return super.updateDistance(f0, f1);
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
            if (!this.getEntityWorld().isRemote)
            {
                entityplayer.openGui(ILOL.I, 0, entityplayer.world, this.getEntityId(), 0, 0);
//                EntitiesContainerHelper.setContainer(this, entityplayer, InventoryContainer.ID);
            }
        }
        else
        {
            if (!this.getEntityWorld().isRemote)
            {
//                ItemStack itemstack = entityplayer.getHeldItem(enumhand);
//                if (itemstack.getItem() == ItemsRegistryHelper.BOX_ITEM)
//                {
//                    for (int i = 0; i < EntitiesRegistryHelper.ENTITIES_CLASS_LIST.size(); ++i)
//                    {
//                        if (EntitiesRegistryHelper.ENTITIES_CLASS_LIST.get(i).equals(this.getClass()))
//                        {
//                            NBTTagCompound nbttagcompound = itemstack.getTagCompound();
//
//                            if (nbttagcompound == null)
//                            {
//                                itemstack.setTagCompound(new NBTTagCompound());
//                                nbttagcompound = itemstack.getTagCompound();
//
//                                String id_key = "key_id";
//                                if (!nbttagcompound.hasKey(id_key))
//                                {
//                                    nbttagcompound.setInteger(id_key, i);
//                                }
//
//                                String entity_key = "entity";
//                                NBTTagCompound entity_nbttagcompound = new NBTTagCompound();
//                                this.writeToNBT(entity_nbttagcompound);
//                                nbttagcompound.setTag(entity_key, entity_nbttagcompound);
//                                this.getEntityWorld().removeEntity(this);
//                                itemstack.setStackDisplayName(this.getName());
//                            }
//
//                            break;
//                        }
//                    }
//                }
//                else
//                {
                DataParameter<Byte> byte_dataparameter = this.getByteDataParameterArray()[this.skinningentitiesbytes.SIT()];
                if (this.server_work_byte_array[this.skinningentitiesbytes.SIT()] == 1)
                {
                    this.getDataManager().set(byte_dataparameter, (byte) 0);
                }
                else
                {
                    this.getDataManager().set(byte_dataparameter, (byte) 1);
                }
//                }
            }
        }

        entityplayer.swingArm(enumhand);
        return true;
//        return super.processInitialInteract(entityplayer, enumhand);
    }

//    @Override
//    public void damageEntity(DamageSource damageSrc, float damageAmount)
//    {
//        super.damageEntity(damageSrc, damageAmount);
//    }

    @Override
    public boolean attackEntityAsMob(Entity entity)
    {
        Entity owner_entity = this.getEntity(1);

//        if (entity instanceof EntityLivingBase)
//        {
//            EntityLivingBase entitylivingbase = (EntityLivingBase)entity;
//            entitylivingbase.maxHurtTime = 0;
//            entitylivingbase.hurtTime = 0;
//        }

//        if (entity instanceof EntityCreeper)
//        {
//            EntityCreeper entitycreeper = (EntityCreeper)entity;
//            ((IMixinEntityCreeper)entitycreeper).timeSinceIgnited(1);
//        }

        EntityLivingBase by_entitylivingbase = this;

        if (this.server_work_byte_array[this.skinningentitiesbytes.LOCK_DAMAGE()] == 1 && owner_entity instanceof EntityLivingBase)
        {
            by_entitylivingbase = (EntityLivingBase)owner_entity;
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
//            mainhand_itemstack.attemptDamageItem(1, this.getRNG(), null);
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

    //    @Override
//    public boolean attackEntityFrom(DamageSource damagesource, float amount)
//    {
//        Entity entity = this.getEntity(1);
//
//        if (this.server_work_boolean_array[1] && entity != null)
//        {
//            return entity.attackEntityFrom(damagesource, amount);
//        }
//
//        return super.attackEntityFrom(damagesource, amount);
//    }

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

//    @Override
//    public void setDead()
//    {
////        this.removeFromMap();
//        super.setDead();
//    }

    public void updateClientObject()
    {
        SkinningRender skinningrender = (SkinningRender)this.client_object;
        EntityDataManager entitydatamanager = this.getDataManager();
        this.client_uuid = this.getUUID(0);

        skinningrender.scale = entitydatamanager.get(this.getFloatDataParameterArray()[0]);

        DataParameter<Integer>[] integer_dataparameter = this.getIntegerDataParameterArray();
        for (int i = 0; i < skinningrender.texture_index_int_array.length; ++i)
        {
            skinningrender.texture_index_int_array[i] = entitydatamanager.get(integer_dataparameter[i]);
        }

        for (int i = 0; i < skinningrender.frame_int_array.length; ++i)
        {
            skinningrender.frame_int_array[i] = entitydatamanager.get(integer_dataparameter[this.bothdata.MaxPart() + i]);
        }
    }

    public void updateData()
    {
        DataParameter<Byte>[] byte_dataparameter = this.getByteDataParameterArray();
        DataParameter<Integer>[] integer_dataparameter = this.getIntegerDataParameterArray();
        EntityDataManager entitydatamanager = this.getDataManager();

        if (this.getEntityWorld().isRemote)
        {
            for (int i = 0; i < this.client_work_byte_array.length; ++i)
            {
                this.client_work_byte_array[i] = entitydatamanager.get(byte_dataparameter[i]);
            }
        }
        else
        {
//            this.getDataManager().set(this.getByteDataParameterArray()[8], (byte)1);
//            this.getDataManager().set(this.getByteDataParameterArray()[9], (byte)1);
            for (int i = 0; i < byte_dataparameter.length; ++i)
            {
                this.server_work_byte_array[i] = entitydatamanager.get(byte_dataparameter[i]);
                this.current_server_work_byte_array[i] = this.server_work_byte_array[i];
            }

            int max_part = this.bothdata.MaxPart();
            for (int i = 0; i < this.server_frame_int_array.length; ++i)
            {
                this.server_frame_int_array[i] = entitydatamanager.get(integer_dataparameter[max_part + i]);
            }

//            this.hands_itemstack_nonnulllist.set(0, this.inventorybasic.getStackInSlot(27));//mainhand
//            this.hands_itemstack_nonnulllist.set(1, this.inventorybasic.getStackInSlot(28));//offhand
//
//            this.armor_itemstack_nonnulllist.set(0, this.inventorybasic.getStackInSlot(32));//feet
//            this.armor_itemstack_nonnulllist.set(1, this.inventorybasic.getStackInSlot(31));//legs
//            this.armor_itemstack_nonnulllist.set(2, this.inventorybasic.getStackInSlot(30));//chest
//            this.armor_itemstack_nonnulllist.set(3, this.inventorybasic.getStackInSlot(29));//head
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
        for (int i = this.skinningentitiesbytes.AFTER_STATE(); i < this.server_work_byte_array.length; ++i)
        {
            if (i < index && this.server_work_byte_array[i] > 0)
            {
                return false;
            }
        }

        return this.server_work_byte_array[index] != 0;
    }

    public boolean isWorkBypass(int index, int bypass)
    {
        for (int i = this.skinningentitiesbytes.AFTER_STATE(); i < this.server_work_byte_array.length; ++i)
        {
            if (i != bypass && i < index && this.server_work_byte_array[i] > 0)
            {
                return false;
            }
        }

        return this.server_work_byte_array[index] != 0;
    }

    //get save entity by save uuid

    public Entity getEntity(int id)
    {
        UUID uuid = this.getUUID(id);

        if (uuid == null)
        {
            return null;
        }

        return ((WorldServer)this.getEntityWorld()).getEntityFromUuid(uuid);
    }

    public UUID getUUID(int id)
    {
        return this.getDataManager().get(UUID_OPTIONAL_DATAPARAMETER_ARRAY[id]).orNull();
    }

    public Material getMaterial(BlockPos blockpos)
    {
        IBlockState temp_iblockstate = this.getEntityWorld().getBlockState(blockpos);
        return temp_iblockstate.getMaterial();
    }

    public void removeFromMap()
    {
//        if (this.getEntityWorld().isRemote)
//        {
//            CLIENT_ENTITIES_MAP.remove(this.current_client_uuid);
//        }
//        else
        if (!this.getEntityWorld().isRemote)
        {
            ChunkLoader.removeChunk(this);
            SERVER_ENTITIES_MAP.remove(this.current_server_uuid);
        }
    }

    public void updateBox()
    {
        float scale = this.getDataManager().get(this.getFloatDataParameterArray()[0]);
        this.width = this.bothdata.Width() * scale;
        this.height = this.bothdata.Height() * scale;
//        this.dimension = this.getEntityWorld().provider.getDimension();

        if (!this.world.isRemote)
        {
            if (!CHUNK_MAP.containsKey(this))
            {
                ChunkLoader.updateChunk(this);
            }
        }
    }

    public abstract void initWriteEntityToNBT(NBTTagCompound nbttagcompound);
    public abstract void initReadEntityFromNBT();

    public abstract BothData createBothData();
    public abstract SkinningEntitiesBytes createBytes();
    public abstract void createServer();
    public abstract DataParameter<Byte>[] getByteDataParameterArray();
    public abstract DataParameter<Integer>[] getIntegerDataParameterArray();
    public abstract DataParameter<Float>[] getFloatDataParameterArray();
    @SideOnly(Side.CLIENT)
    public abstract Object createClientObject();

//    @Override
//    public void setPortal(BlockPos blockpos)
//    {
//
//    }
}