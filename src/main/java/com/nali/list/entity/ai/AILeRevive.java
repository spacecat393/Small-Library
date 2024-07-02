package com.nali.list.entity.ai;

import com.nali.Nali;
import com.nali.data.IBothDaNe;
import com.nali.list.network.message.ClientMessage;
import com.nali.list.network.method.client.CRespawnPlayer;
import com.nali.network.NetworkRegistry;
import com.nali.small.chunk.ChunkLoader;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.ai.AI;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.small.mixin.IMixinEntityPlayer;
import com.nali.sound.ISoundLe;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import static com.nali.small.entity.EntityMath.getDistanceAABBToAABB;
import static com.nali.small.entity.EntityMath.isInArea;

public class AILeRevive<SD extends ISoundLe, BD extends IBothDaNe, E extends EntityLivingBase, I extends IMixLe<SD, BD, E>, S extends ServerLe<SD, BD, E, I, A>, A extends MixAIE<SD, BD, E, I, S>> extends AI<SD, BD, E, I, S, A>
{
    public static byte ID;

    public AIEOwner<SD, BD, E, I, S, A> aieowner;
    public AIEArea<SD, BD, E, I, S, A> aiearea;
    public AILeSetLocation<SD, BD, E, I, S, A> ailesetlocation;
    public AILeFindMove<SD, BD, E, I, S, A> ailefindmove;
    public AILeLook<SD, BD, E, I, S, A> ailelook;

    public byte state;//try_revive-N revive_owner revive_other_entity remote

//    public int tick = 0;
    public Entity entity;
    public UUID uuid;
//    public List<Entity> entity_list = new ArrayList();
//    public List<UUID> uuid_list = new ArrayList();
//    public Entity current_entity;
//    public boolean die;
    public BlockPos blockpos;
    public BlockPos need_blockpos;

    public AILeRevive(S s)
    {
        super(s);
    }

    @Override
    public void init()
    {
        this.aieowner = (AIEOwner<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AIEOwner.ID);
        this.aiearea = (AIEArea<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AIEArea.ID);
        this.ailesetlocation = (AILeSetLocation<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AILeSetLocation.ID);
        this.ailefindmove = (AILeFindMove<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AILeFindMove.ID);
        this.ailelook = (AILeLook<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AILeLook.ID);
    }

    @Override
    public void call()
    {

    }

    @Override
    public void onUpdate()
    {
        if (this.s.isMove())
        {
//            if (this.s.isWork(this.s.bytele.REVIVE()))
            if ((this.s.a.state & 1) == 1)
            {
//                Entity owner_entity = this.s.getOwner();
                E e = this.s.i.getE();
//                NBTTagCompound e_nbttagcompound = e.getEntityData();
//                if (e_nbttagcompound.hasKey("Nali_reviver"))
                if (this.uuid != null)
                {
//                    Entity entity = this.s.worldserver.getEntityFromUuid(e_nbttagcompound.getUniqueId("Nali_reviver"));
                    Entity entity = this.s.worldserver.getEntityFromUuid(this.uuid);
//                    Entity owner = this.s.getOwner();
//                    if (owner_entity instanceof EntityPlayerMP && ((EntityPlayerMP)owner_entity).getHealth() > 0.0F)
                    if (/*entity instanceof EntityPlayerMP && *//*((EntityPlayerMP)entity).getHealth() > 0.0F*/entity != null && entity.isEntityAlive())
                    {
                        if (this.need_blockpos != null)
                        {
//                            if (!this.need_blockpos.equals(entity.getPosition()))
//                            {
//                                owner_entity.setPositionAndUpdate(this.need_blockpos.getX(), this.need_blockpos.getY(), this.need_blockpos.getZ());
                            entity.setPositionAndUpdate(this.need_blockpos.getX(), this.need_blockpos.getY(), this.need_blockpos.getZ());
                        }

//                            EntityPlayerMP entityplayermp = (EntityPlayerMP)owner_entity;
                        EntityPlayerMP entityplayermp = (EntityPlayerMP)entity;
                        int dimension = entityplayermp.dimension;
                        entityplayermp.dimension = 0;
                        entityplayermp.setSpawnPoint(this.blockpos, true);
                        entityplayermp.dimension = dimension;
                        entityplayermp.getEntityData().removeTag("Nali_revive");
                        this.uuid = null;
//                        }
//                        e_nbttagcompound.setBoolean("Nali_reviver", false);
                    }
//                    e_nbttagcompound.removeTag("Nali_reviver");
                }
                else
                {
    //                if (this.die && !this.entity.isEntityAlive() && (this.current_entity == null || !this.current_entity.isEntityAlive()))
                    if ((this.state & 1) == 1)
                    {
    //                    int size = this.entity_list.size();
    //                    byte[] byte_array = new byte[(int)Math.ceil(size / 8.0F)];
    //                    for (int i = 0; i < size; ++i)
    //                    {
    //                        Entity entity = this.entity_list.get(i);
    //                        Entity world_entity = this.s.worldserver.getEntityFromUuid(this.uuid_list.get(i));
    //                    if (!this.entity.isEntityAlive() && (owner_entity == null || !owner_entity.isEntityAlive()))
    //                        if (!entity.isEntityAlive() && (world_entity == null || !world_entity.isEntityAlive()))
    //                    Entity entity = this.s.worldserver.getEntityFromUuid(this.entity.getUniqueID());
    //                    if (!this.entity.isEntityAlive() && (entity == null || !entity.isEntityAlive()))
    //                    {
        //                    if ((this.entity.world).provider.getDimension() != ((this.skinningentities.world).provider.getDimension()))
        //                    {
        //                        this.s.current_work_byte_array[this.s.bytele.REVIVE() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.REVIVE() % 8));//0
        //                        return;
        //                    }
        //                    else
        //                    {
        //                    if (isTooClose(this.skinningentities, entity, 1.0D))
                            if (e.getEntityBoundingBox().intersects(this.entity.getEntityBoundingBox()))
    //                            if (e.getEntityBoundingBox().intersects(entity.getEntityBoundingBox()))
                            {
        //                        if (this.s.current_work_byte_array[this.s.bytele.ON_REVIVE()] != 1)
        //                        {
        //                            this.s.main_work_byte_array[this.s.bytele.ON_REVIVE()] = 1;
        //                        }

                                this.ailelook.set(this.entity.posX, this.entity.posY, this.entity.posZ, 90.0F);
    //                            this.ailelook.set(entity.posX, entity.posY, entity.posZ, 90.0F);
    //                        ++this.tick;
    //                            if (++this.tick >= 60)
        //                if (this.entity != null)
    //                            {
    //                                this.tick = 0;
    //                            this.entity.isDead = false;
    //                            this.skinningentities.world.spawnEntity(this.entity);
    //                            if (this.entity instanceof EntityLivingBase)
    //                            {
    //                                EntityLivingBase entitylivingbase = (EntityLivingBase)this.entity;
    //                                entitylivingbase.setHealth(entitylivingbase.getMaxHealth());
    //                                entitylivingbase.deathTime = 0;

                                Entity entity = this.s.worldserver.getEntityFromUuid(this.entity.getUniqueID());
                                if (this.entity instanceof EntityPlayer && (entity == null || !entity.isEntityAlive()))
    //                                    if (entity instanceof EntityPlayer)
                                {
                                    EntityPlayerMP entityplayermp = (EntityPlayerMP)this.entity;
    //                                        EntityPlayerMP entityplayermp = (EntityPlayerMP)entity;
                                    this.blockpos = ((IMixinEntityPlayer)entityplayermp).spawnPos();
                                    this.need_blockpos = entityplayermp.getPosition();
    //                                        nbttagcompound.setBoolean("Nali_revive", true);
    //                                        byte_array[i / 8] |= (byte)Math.pow(2, i % 8);
                                    int dimension = entityplayermp.dimension;
                                    entityplayermp.dimension = 0;
                                    entityplayermp.setSpawnPoint(entityplayermp.getPosition(), true);
                                    entityplayermp.dimension = dimension;
                                    entityplayermp.getEntityData().setBoolean("Nali_revive", true);
                                    this.uuid = this.entity.getUniqueID();
//                                    e_nbttagcompound.setUniqueId("Nali_reviver", this.entity.getUniqueID());
                                    ChunkLoader.updateChunk(this.s);
                                    NetworkRegistry.I.sendTo(new ClientMessage(new byte[]{CRespawnPlayer.ID}), entityplayermp);
    //                                entityplayermp.setPositionAndUpdate(x, y, z);

    //                                GameType gametype = entityplayermp.interactionManager.getGameType();
    //                                entityplayermp = entityplayermp.server.getPlayerList().recreatePlayerEntity(entityplayermp, entityplayermp.dimension, false);
    //                                entityplayermp.setPositionAndUpdate(x, y, z);
    //
    //                                // Restore the player's game type and game mode
    //                                entityplayermp.interactionManager.setGameType(gametype);
    //
    //                                // Fix for potential interaction issues (you may need to adjust this based on your specific needs)
    //                                entityplayermp.world.updateEntityWithOptionalForce(entityplayermp, false);
    //                                entityplayermp.world.updateEntity(entityplayermp);
    ////                                entityplayermp.isDead = false;
    ////                                entityplayermp.deathTime = 0;
    ////                                entityplayermp.setHealth(entityplayermp.getMaxHealth());
    ////                                entityplayermp.world.spawnEntity(entityplayermp);
    ////                                    if (this.current_entity == null)
    ////                                    {
    ////                                        this.entity.world.spawnEntity(this.entity);
    ////                                    }
    ////                            entityplayermp.closeScreen();
    ////                                byte[] byte_array = new byte[1 + 4 + 4 + 4];
    ////                                byte_array[0] = 1;
    ////                                BytesWriter.set(byte_array, x, 1);
    ////                                BytesWriter.set(byte_array, y, 1 + 4);
    ////                                BytesWriter.set(byte_array, z, 1 + 4 + 4);
    ////                                NetworksRegistry.I.sendTo(new SkinningEntitiesClientMessage(byte_array), entityplayermp);
                                }
                                else if (entity == null)
                                {
                                    try
                                    {
                                        this.s.worldserver.spawnEntity(this.entity.getClass().getConstructor(World.class).newInstance(this.entity.world));
                                    }
                                    catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ex)
                                    {
                                        Nali.I.error(ex);
                                    }
                                }
    ////                            }

                                this.entity = null;
    //                            }
    //                            }
                            }
                            else
                            {
                                if (this.ailesetlocation.far == 0 || this.ailesetlocation.blockpos == null || isInArea(this.entity, this.ailesetlocation.blockpos, this.ailesetlocation.far))
                                {
                                    this.ailefindmove.setGoal(this.entity.posX, this.entity.posY, this.entity.posZ);
                                }
    //                                this.ailefindmove.setGoal(entity.posX, entity.posY, entity.posZ);
        //                        this.s.a.state &= 255-1;
                            }
    //                    }
    //                    else
    //                    {
    //                        this.clear();
    //                    }
    //                    }
    //                    e_nbttagcompound.setByteArray("Nali_revive", byte_array);
                    }
                    else
                    {
    //                if (this.entity == null)
    ////                {
    ////                    this.entity = this.s.getOwner();
    ////    //                this.tick = 0;
    ////                }
    ////                Entity owner_entity = this.s.getOwner();
    ////                if (!this.die && this.entity != null && !this.entity.isEntityAlive())
    ////                if ((this.state & 1) == 0 && owner_entity != null && !this.entity.isEntityAlive())
    //                    int index = -1;
    //                    double far = Double.MAX_VALUE;
    ////                    for (Entity entity : this.aiearea.out_entity_list)
    //                    for (int i = 0; i < this.aiearea.out_entity_list.size(); ++i)
    //                    {
    //                        Entity entity = this.aiearea.out_entity_list.get(i);
    ////                    this.entity = owner_entity;
    //                        if (!entity.isEntityAlive())
    //                        {
    //                            double new_far = getDistanceAABBToAABB(e, entity);
    //                            if (new_far < far)
    //                            {
    //                                far = new_far;
    //                                index = i;
    //                            }
    //                        }
    //                        //                this.entity.isDead = false;
    //                        //                this.current_entity = ((WorldServer)this.entity.world).getEntityFromUuid(this.entity.getUniqueID());
    ////                    this.current_entity = this.s.getOwner();
    ////                    this.die = true;
    ////                    this.state |= 1;
    //                    }
    //
    //                    if (index != -1)
    //                    {
    ////                        Entity entity = this.aiearea.out_entity_list.get(index);
    ////                        this.entity_list.add(entity);
    ////                        this.entity = entity;
    //                        this.entity = this.aiearea.out_entity_list.get(index);
    //                        e_nbttagcompound.setBoolean("Nali_reviver", true);
    ////                        e_nbttagcompound.setUniqueId("Nali_reviver", entity.getUniqueID());
    ////                        this.uuid_list.add(entity.getUniqueID());
    //                        this.state |= 1;
    //                    }

    //                    if (owner_entity != null && !this.entity.isEntityAlive())
    //                    {
    //                        this.entity = owner_entity;
    //                        this.state |= 1;
    //                    }

                        if ((this.state & 2) == 2)
                        {
                            Entity owner_entity = this.aieowner.getOwner();
                            if (owner_entity != null && !owner_entity.isEntityAlive()/* && owner_entity instanceof EntityPlayerMP*/)
                            {
    //                            e_nbttagcompound.setUniqueId("Nali_reviver", owner_entity.getUniqueID());
                                this.state |= 1;
                            }
                        }
                        else if ((this.state & 4) == 4)//?
                        {
                            int index = -1;
                            double max_far = Double.MAX_VALUE;
                            for (int i = 0; i < this.aiearea.out_entity_list.size(); ++i)
                            {
                                Entity entity = this.aiearea.out_entity_list.get(i);

                                if (!entity.isEntityAlive())
                                {
                                    if (entity.getUniqueID().equals(this.aieowner.uuid))
                                    {
                                        continue;
                                    }

                                    double new_far = getDistanceAABBToAABB(e, entity);
                                    if (new_far < max_far)
                                    {
                                        this.entity = entity;
                                        max_far = new_far;
                                        index = i;
                                    }
                                }
                            }

                            if (index != -1)
                            {
    //                            if (this.entity instanceof EntityPlayerMP)
    //                            {
    //                                e_nbttagcompound.setUniqueId("Nali_reviver", this.aiearea.out_entity_list.get(index).getUniqueID());
    //                            }
                                this.state |= 1;
                            }
                        }
                    }
        //            else if (this.entity != null && this.current_entity != null && this.current_entity.isEntityAlive())
        //            {
        ////                this.entity.world.removeEntity(this.entity);
        ////                this.current_entity.setDead();
        //                this.entity = null;
        //            }
    //                else if (this.die)
    //                {
    //                    this.ailefindmove.endGoal();
    //                    this.entity = null;
    ////                    this.die = false;
    ////                    this.current_entity = null;
    //                    this.state &= 255-1;
    //                }

    //                if (!this.die)
    //                {
    //                    this.s.current_work_byte_array[this.s.bytele.REVIVE() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.REVIVE() % 8));//0
    //                }

    //                this.die = false;
                }
            }
            else if ((this.state & 1) == 1)
            {
//                this.clear();
                this.ailefindmove.endGoal();
                this.entity = null;
                this.uuid = null;
                this.blockpos = null;
//                    this.die = false;
//                    this.current_entity = null;
                this.state &= 255-1;
            }
        }
    }

    @Override
    public void writeNBT(NBTTagCompound nbttagcompound)
    {
        if (this.blockpos != null)
        {
            nbttagcompound.setLong("AILeRevive_blockpos", this.blockpos.toLong());
        }

        if (this.uuid != null)
        {
            nbttagcompound.setUniqueId("AILeRevive_uuid", this.uuid);
        }
    }

    @Override
    public void readNBT(NBTTagCompound nbttagcompound)
    {
        if (nbttagcompound.hasKey("AILeRevive_blockpos"))
        {
            this.blockpos = BlockPos.fromLong(nbttagcompound.getLong("AILeRevive_blockpos"));
        }

        if (nbttagcompound.hasKey("AILeRevive_uuid"))
        {
            this.uuid = nbttagcompound.getUniqueId("AILeRevive_uuid");
        }
    }

//    public void clear()
//    {
//        this.ailefindmove.endGoal();
//        this.entity = null;
////                    this.die = false;
////                    this.current_entity = null;
//        this.state &= 255-1;
//    }
}
