package com.nali.small.entities.skinning;

import com.nali.data.BothData;
import com.nali.list.messages.ServerMessage;
import com.nali.list.netmethods.servermessage.*;
import com.nali.networks.NetworksRegistry;
import com.nali.render.SkinningRender;
import com.nali.small.entities.EntitiesAttackHelper;
import com.nali.small.entities.bytes.WorkBytes;
import com.nali.small.entities.memory.BothEntitiesMemory;
import com.nali.small.entities.memory.client.ClientEntitiesMemory;
import com.nali.small.entities.memory.server.ServerEntitiesMemory;
import com.nali.small.entities.skinning.works.SkinningEntitiesBodyYaw;
import com.nali.small.entities.sounds.Sounds;
import com.nali.small.mixin.IMixinEntityCreeper;
import com.nali.small.mixin.IMixinEntityLivingBase;
import com.nali.small.world.ChunkLoader;
import com.nali.system.bytes.BytesWriter;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.UUID;

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

        if (world.isRemote)
        {
            this.createClientEntitiesMemory(this, bothdata, workbytes);
            this.width = bothdata.Width() * scale;
            this.height = bothdata.Height() * scale;
        }
        else
        {
            this.createServerEntitiesMemory(this, bothdata, workbytes);
            this.width = 0.5F;
            this.height = 0.5F;

            ChunkLoader.updateChunk(this);

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
            serverentitiesmemory.writeNBT(nbttagcompound);
        }

        this.bothentitiesmemory.skinninginventory.writeNBT(nbttagcompound);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);

        EntityDataManager entitydatamanager = this.getDataManager();

        int i = 0;

        if (this.world.isRemote)
        {
            ClientEntitiesMemory cliententitiesmemory = (ClientEntitiesMemory)this.bothentitiesmemory;
            this.initFakeFrame();
            cliententitiesmemory.objectrender.entitiesrendermemory.scale = cliententitiesmemory.bothdata.Scale();
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

        this.bothentitiesmemory.skinninginventory.readNBT(nbttagcompound);

        if (!this.world.isRemote)
        {
            ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.bothentitiesmemory;
            serverentitiesmemory.readNBT(nbttagcompound);
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
        DataParameter<Byte>[] byte_dataparameter_array = this.getByteDataParameterArray();
        if (this.world.isRemote)
        {
            ClientEntitiesMemory cliententitiesmemory = (ClientEntitiesMemory)this.bothentitiesmemory;

            if (!cliententitiesmemory.fake)
            {
                for (int i = 0; i < byte_dataparameter_array.length; ++i)
                {
                    cliententitiesmemory.sync_byte_array[i] = this.getDataManager().get(byte_dataparameter_array[i]);
                }

                this.updateClientObject();
                cliententitiesmemory.mouth_itemstack = entitydatamanager.get(MOUTH_ITEMSTACK_DATAPARAMETER);
            }

            this.updateClient();

            cliententitiesmemory.soundrender.set((float)this.posX, (float)this.posY, (float)this.posZ);
        }
        else
        {
            ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.bothentitiesmemory;
            DataParameter<Integer>[] integer_dataparameter = this.getIntegerDataParameterArray();

            for (int i = 0; i < byte_dataparameter_array.length; ++i)
            {
                serverentitiesmemory.sync_byte_array[i] = this.getDataManager().get(byte_dataparameter_array[i]);
            }

            ItemStack mouth_itemstack = serverentitiesmemory.skinninginventory.offset_itemstack_nonnulllist.get(0);
//            if (mouth_itemstack != serverentitiesmemory.current_mouth_itemstack)
//            {
//            serverentitiesmemory.current_mouth_itemstack = mouth_itemstack;
//            entitydatamanager.set(MOUTH_ITEMSTACK_DATAPARAMETER, mouth_itemstack);
//            }
            if (mouth_itemstack.isEmpty())
            {
                entitydatamanager.set(MOUTH_ITEMSTACK_DATAPARAMETER, ItemStack.EMPTY);
            }
            else
            {
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
        if (this.world.isRemote)
        {
            ClientEntitiesMemory cliententitiesmemory = (ClientEntitiesMemory)this.bothentitiesmemory;
            if (entityplayer.isSneaking())
            {
                if (cliententitiesmemory.uuid != null)
                {
                    byte[] byte_array = new byte[17];
                    byte_array[0] = OpenInvGUI.ID;
                    BytesWriter.set(byte_array, cliententitiesmemory.uuid, 1);
                    NetworksRegistry.I.sendToServer(new ServerMessage(byte_array));
                }
            }
            else
            {
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

                if (state == 0 && !eat_state && this.isMove())
                {
                    cliententitiesmemory.skinningentitiespat.run();
                }
                else if (state == 1)
                {
                    byte[] byte_array;
                    if (milk_bucket)
                    {
                        byte_array = new byte[1 + 16];
                        byte_array[0] = DrinkMilk.ID;
                    }
                    else
                    {
                        byte_array = new byte[1 + 16 + 4 + 4 + 4];
                        byte_array[0] = Eat.ID;
                        BytesWriter.set(byte_array, (float)(axisalignedbb_array[1].maxX + (axisalignedbb_array[1].minX - axisalignedbb_array[1].maxX) / 2.0D), 1 + 16);
                        BytesWriter.set(byte_array, (float)(axisalignedbb_array[1].maxY + (axisalignedbb_array[1].minY - axisalignedbb_array[1].maxY) / 2.0D), 1 + 16 + 4);
                        BytesWriter.set(byte_array, (float)(axisalignedbb_array[1].maxZ + (axisalignedbb_array[1].minZ - axisalignedbb_array[1].maxZ) / 2.0D), 1 + 16 + 4 + 4);
                    }

                    BytesWriter.set(byte_array, cliententitiesmemory.uuid, 1);
                    NetworksRegistry.I.sendToServer(new ServerMessage(byte_array));
                }
                else
                {
                    byte i = cliententitiesmemory.workbytes.SIT();
                    byte[] byte_array = new byte[21];
                    byte_array[0] = SetWorkByte.ID;
                    BytesWriter.set(byte_array, cliententitiesmemory.uuid, 1);
                    BytesWriter.set(byte_array, i, 17);
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
            if ((serverentitiesmemory.main_work_byte_array[serverentitiesmemory.workbytes.LOCK_DAMAGE() / 8] >> serverentitiesmemory.workbytes.LOCK_DAMAGE() % 8 & 1) == 1)
            {
                Entity owner_entity = serverentitiesmemory.getOwner();
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
            Entity owner_entity = serverentitiesmemory.getOwner();
            if ((serverentitiesmemory.main_work_byte_array[serverentitiesmemory.workbytes.LOCK_DAMAGE() / 8] >> serverentitiesmemory.workbytes.LOCK_DAMAGE() % 8 & 1) == 1 && owner_entity instanceof EntityLivingBase)
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
    public boolean canEntityBeSeen(Entity entityIn)
    {
        return this.world.rayTraceBlocks(new Vec3d(this.posX, this.posY, this.posZ), new Vec3d(entityIn.posX, entityIn.posY, entityIn.posZ), false, true, false) == null;
    }

    @Override
    public BlockPos getPosition()
    {
        return new BlockPos(this.posX, this.posY, this.posZ);
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

    @Nullable
    @Override
    public SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        if (this.world.isRemote)
        {
            ClientEntitiesMemory cliententitiesmemory = (ClientEntitiesMemory)this.bothentitiesmemory;
            cliententitiesmemory.soundrender.play(cliententitiesmemory.sounds.HURT());
        }

        return super.getHurtSound(damageSourceIn);
    }

    @Nullable
    @Override
    public SoundEvent getDeathSound()
    {
        ClientEntitiesMemory cliententitiesmemory = (ClientEntitiesMemory)this.bothentitiesmemory;
        cliententitiesmemory.soundrender.play(cliententitiesmemory.sounds.DEATH());
        return super.getDeathSound();
    }

    public void onShouldPlayWith()
    {
        ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.bothentitiesmemory;
        SkinningEntities playwith_skinningentities = serverentitiesmemory.entitiesaimemory.skinningentitiesplaywith.playwith_skinningentities;
        this.setPositionAndUpdate(playwith_skinningentities.posX, playwith_skinningentities.posY, playwith_skinningentities.posZ);
        this.rotationYaw = playwith_skinningentities.rotationYaw;
        this.rotationPitch = playwith_skinningentities.rotationPitch;
        this.renderYawOffset = playwith_skinningentities.renderYawOffset;
        this.fallDistance = 0;
    }

    public void updateClientObject()
    {
        ClientEntitiesMemory cliententitiesmemory = (ClientEntitiesMemory)this.bothentitiesmemory;
        EntityDataManager entitydatamanager = this.getDataManager();

        if (cliententitiesmemory.uuid == null)
        {
            byte[] byte_array = new byte[5];
            byte_array[0] = SyncUUIDToClient.ID;
            BytesWriter.set(byte_array, this.getEntityId(), 1);
            NetworksRegistry.I.sendToServer(new ServerMessage(byte_array));
        }

        cliententitiesmemory.objectrender.entitiesrendermemory.scale = entitydatamanager.get(this.getFloatDataParameterArray()[0]);
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
    public void createClientEntitiesMemory(SkinningEntities skinningentities, BothData bothdata, WorkBytes workbytes)
    {
        new ClientEntitiesMemory(skinningentities, bothdata, workbytes);
    }

    public void createServerEntitiesMemory(SkinningEntities skinningentities, BothData bothdata, WorkBytes workbytes)
    {
        new ServerEntitiesMemory(skinningentities, bothdata, workbytes);
    }

    public void initWriteEntityToNBT(NBTTagCompound nbttagcompound)
    {
        this.initWorkBytes();
    }

    public void initReadEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        this.initWorkBytes();
    }

    public void initWorkBytes()
    {
        ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.bothentitiesmemory;
        serverentitiesmemory.main_work_byte_array[serverentitiesmemory.workbytes.FOLLOW() / 8] ^= (byte)Math.pow(2, serverentitiesmemory.workbytes.FOLLOW() % 8);
        serverentitiesmemory.main_work_byte_array[serverentitiesmemory.workbytes.RANDOM_WALK() / 8] ^= (byte)Math.pow(2, serverentitiesmemory.workbytes.RANDOM_WALK() % 8);
        serverentitiesmemory.main_work_byte_array[serverentitiesmemory.workbytes.RANDOM_LOOK() / 8] ^= (byte)Math.pow(2, serverentitiesmemory.workbytes.RANDOM_LOOK() % 8);
        serverentitiesmemory.main_work_byte_array[serverentitiesmemory.workbytes.WALK_TO() / 8] ^= (byte)Math.pow(2, serverentitiesmemory.workbytes.WALK_TO() % 8);
        serverentitiesmemory.main_work_byte_array[serverentitiesmemory.workbytes.LOOK_TO() / 8] ^= (byte)Math.pow(2, serverentitiesmemory.workbytes.LOOK_TO() % 8);
        serverentitiesmemory.main_work_byte_array[serverentitiesmemory.workbytes.REVIVE() / 8] ^= (byte)Math.pow(2, serverentitiesmemory.workbytes.REVIVE() % 8);
    }

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

    public AxisAlignedBB getMouthAxisAlignedBB()
    {
        ClientEntitiesMemory cliententitiesmemory = (ClientEntitiesMemory)this.bothentitiesmemory;
        SkinningRender skinningrender = (SkinningRender)cliententitiesmemory.objectrender;
        int[] iv_int_array = this.getIVIntArray();

        float[] pos_vec4 = skinningrender.getScale3DSkinning((float)this.posX, (float)this.posY, (float)this.posZ, 0, 0, 0, iv_int_array[10], iv_int_array[11]);

        double x = pos_vec4[0] / pos_vec4[3];
        double y = pos_vec4[1] / pos_vec4[3];
        double z = pos_vec4[2] / pos_vec4[3];

        double hw = this.width / 1.5F;
        double hh = this.height / 4.0F;

        return new AxisAlignedBB
        (
        x - hw, y - hh, z - hw,
        x + hw, y + hh, z + hw
        );
    }

    public void initFakeFrame(){}

    public abstract BothData createBothData();
    public abstract WorkBytes createWorkBytes();
    public abstract Sounds createSounds();
    public abstract void createServer();
    public abstract DataParameter<Byte>[] getByteDataParameterArray();
    public abstract DataParameter<Integer>[] getIntegerDataParameterArray();
    public abstract DataParameter<Float>[] getFloatDataParameterArray();
    @SideOnly(Side.CLIENT)
    public abstract Object createObjectRender();
    @SideOnly(Side.CLIENT)
    public abstract Object createSoundRender();
    @SideOnly(Side.CLIENT)
    public abstract int[] getIVIntArray();
}