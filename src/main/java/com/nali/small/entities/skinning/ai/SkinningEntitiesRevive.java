package com.nali.small.entities.skinning.ai;

import com.nali.list.messages.ClientMessage;
import com.nali.small.entities.memory.server.ServerEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.mixin.IMixinEntityPlayer;
import com.nali.small.networks.NetworksRegistry;
import com.nali.small.world.ChunkLoader;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;

import static com.nali.small.entities.EntitiesMath.isTooClose;

public class SkinningEntitiesRevive extends SkinningEntitiesAI
{
    public int tick = 0;
    public Entity entity;
    public Entity current_entity;
    public boolean die;
    public BlockPos blockpos;
    public BlockPos need_blockpos;

    public SkinningEntitiesRevive(SkinningEntities skinningentities)
    {
        super(skinningentities);
    }

    @Override
    public void onUpdate()
    {
        ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.skinningentities.bothentitiesmemory;
        if (this.skinningentities.getEntityData().getBoolean("revive_nali"))
        {
            Entity owner = serverentitiesmemory.getOwner();
            if (owner instanceof EntityPlayerMP && ((EntityPlayerMP)owner).getHealth() > 0.0F)
            {
                if (this.need_blockpos != null)
                {
                    if (!this.need_blockpos.equals(owner.getPosition()))
                    {
                        owner.setPositionAndUpdate(this.need_blockpos.getX(), this.need_blockpos.getY(), this.need_blockpos.getZ());
                    }

                    EntityPlayerMP entityplayermp = (EntityPlayerMP)owner;
                    int dimension = entityplayermp.dimension;
                    entityplayermp.dimension = 0;
                    entityplayermp.setSpawnPoint(this.blockpos, true);
                    entityplayermp.dimension = dimension;
                    entityplayermp.getEntityData().removeTag("revive_nali");
                }

                this.skinningentities.getEntityData().setBoolean("revive_nali", false);
            }
        }

        if (serverentitiesmemory.isWork(serverentitiesmemory.workbytes.REVIVE()))
        {
            if (this.entity == null)
            {
                this.entity = serverentitiesmemory.getOwner();
//                this.tick = 0;
            }

            if (!this.die && this.entity != null && !this.entity.isEntityAlive())
            {
//                this.entity.isDead = false;
//                this.current_entity = ((WorldServer)this.entity.world).getEntityFromUuid(this.entity.getUniqueID());
                this.current_entity = serverentitiesmemory.getOwner();
                this.die = true;
            }

            if (this.die && !this.entity.isEntityAlive() && (this.current_entity == null || !this.current_entity.isEntityAlive()))
            {
                if ((this.entity.world).provider.getDimension() != ((this.skinningentities.world).provider.getDimension()))
                {
                    serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.REVIVE() / 8] &= (byte)(255 - Math.pow(2, serverentitiesmemory.workbytes.REVIVE() % 8));//0
                    return;
                }
                else
                {
                    if (isTooClose(this.skinningentities, entity, 1.0D))
                    {
//                        if (serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.ON_REVIVE()] != 1)
//                        {
//                            serverentitiesmemory.main_work_byte_array[serverentitiesmemory.workbytes.ON_REVIVE()] = 1;
//                        }

                        serverentitiesmemory.entitiesaimemory.skinningentitieslook.set(this.entity.posX, this.entity.posY, this.entity.posZ, 90.0F);
//                        ++this.tick;
                        if (++this.tick >= 60)
    //                if (this.entity != null)
                        {
                            this.tick = 0;
//                            this.entity.isDead = false;
//                            this.skinningentities.world.spawnEntity(this.entity);
//                            if (this.entity instanceof EntityLivingBase)
//                            {
//                                EntityLivingBase entitylivingbase = (EntityLivingBase)this.entity;
//                                entitylivingbase.setHealth(entitylivingbase.getMaxHealth());
//                                entitylivingbase.deathTime = 0;

                            if (this.entity instanceof EntityPlayer)
                            {
                                EntityPlayerMP entityplayermp = (EntityPlayerMP)this.entity;
                                this.blockpos = ((IMixinEntityPlayer)entityplayermp).spawnPos();
                                this.need_blockpos = entityplayermp.getPosition();
                                this.skinningentities.getEntityData().setBoolean("revive_nali", true);
                                int dimension = entityplayermp.dimension;
                                entityplayermp.dimension = 0;
                                entityplayermp.setSpawnPoint(entityplayermp.getPosition(), true);
                                entityplayermp.dimension = dimension;
                                entityplayermp.getEntityData().setBoolean("revive_nali", true);
                                ChunkLoader.updateChunk(this.skinningentities);
                                NetworksRegistry.I.sendTo(new ClientMessage(new byte[]{1}), entityplayermp);
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
////                            }

                            this.entity = null;
                        }
                    }
                    else
                    {
                        serverentitiesmemory.entitiesaimemory.skinningentitiesfindmove.setGoal(this.entity.posX, this.entity.posY, this.entity.posZ);
                    }
                }
            }
//            else if (this.entity != null && this.current_entity != null && this.current_entity.isEntityAlive())
//            {
////                this.entity.world.removeEntity(this.entity);
////                this.current_entity.setDead();
//                this.entity = null;
//            }
            else if (this.die)
            {
                serverentitiesmemory.entitiesaimemory.skinningentitiesfindmove.endGoal();
                this.entity = null;
                this.die = false;
                this.current_entity = null;
            }

            if (!this.die)
            {
                serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.REVIVE() / 8] &= (byte)(255 - Math.pow(2, serverentitiesmemory.workbytes.REVIVE() % 8));//0
            }

            this.die = false;
        }
    }
}
