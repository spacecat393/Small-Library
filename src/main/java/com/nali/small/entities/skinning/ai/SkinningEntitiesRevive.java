package com.nali.small.entities.skinning.ai;

import com.nali.list.messages.SkinningEntitiesClientMessage;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.mixin.IMixinEntityPlayer;
import com.nali.small.networks.NetworksRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;

import static com.nali.small.entities.EntitiesMathHelper.isTooClose;

public class SkinningEntitiesRevive extends SkinningEntitiesAI
{
//    public int tick = 0;
    public Entity entity;
//    public Entity current_entity;
    public boolean die;
    public boolean should_set_spawn_point;
    public BlockPos blockpos;

    public SkinningEntitiesRevive(SkinningEntities skinningentities)
    {
        super(skinningentities);
    }

    @Override
    public void onUpdate()
    {
        if (this.should_set_spawn_point)
        {
            Entity owner = this.skinningentities.getOwner();
            if (owner instanceof EntityPlayerMP)
            {
                EntityPlayerMP entityplayermp = (EntityPlayerMP)owner;
                entityplayermp.setSpawnPoint(blockpos, true);
            }
            this.should_set_spawn_point = false;
        }

        if (this.skinningentities.isWork(this.skinningentities.skinningentitiesbytes.REVIVE()))
        {
            if (this.entity == null)
            {
                this.entity = this.skinningentities.getOwner();
//                this.tick = 0;
            }

            if (!this.die && this.entity != null && !this.entity.isEntityAlive())
            {
//                this.entity.isDead = false;
//                this.current_entity = ((WorldServer)this.entity.world).getEntityFromUuid(this.entity.getUniqueID());
//                this.current_entity = this.skinningentities.getOwner();
                this.die = true;
            }

            if (this.die && !this.entity.isEntityAlive()/* && (this.current_entity == null || !this.current_entity.isEntityAlive())*/)
            {
                if ((this.entity.world).provider.getDimension() != ((this.skinningentities.world).provider.getDimension()))
                {
                    this.skinningentities.server_work_byte_array[this.skinningentities.skinningentitiesbytes.REVIVE()] = 0;
                    return;
                }
                else
                {
                    if (isTooClose(this.skinningentities, entity, 1.0D))
                    {
                        if (this.skinningentities.server_work_byte_array[this.skinningentities.skinningentitiesbytes.ON_REVIVE()] != 1)
                        {
                            this.skinningentities.main_server_work_byte_array[this.skinningentities.skinningentitiesbytes.ON_REVIVE()] = 1;
                        }

                        this.skinningentities.skinningentitieslook.set(this.entity.posX, this.entity.posY, this.entity.posZ, 90.0F);
//                        ++this.tick;
//                        if (this.tick == 100)
    //                if (this.entity != null)
                        {
//                            this.tick = 0;
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
                                this.should_set_spawn_point = true;
                                entityplayermp.setSpawnPoint(entityplayermp.getPosition(), true);
                                NetworksRegistry.I.sendTo(new SkinningEntitiesClientMessage(new byte[]{1}), entityplayermp);
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
                        this.skinningentities.skinningentitiesfindmove.setGoal(this.entity.posX, this.entity.posY, this.entity.posZ);
                    }
                }
            }
//            else if (this.entity != null && this.current_entity != null && this.current_entity.isEntityAlive())
//            {
////                this.entity.world.removeEntity(this.entity);
////                this.current_entity.setDead();
//                this.entity = null;
//            }
            else
            {
                this.entity = null;
            }

            if (!this.die)
            {
                this.skinningentities.server_work_byte_array[this.skinningentities.skinningentitiesbytes.REVIVE()] = 0;
            }

            this.die = false;
        }
    }
}
