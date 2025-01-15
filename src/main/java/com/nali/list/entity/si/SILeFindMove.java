package com.nali.list.entity.si;

import com.nali.da.IBothDaE;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.small.entity.memo.server.si.SI;
import com.nali.small.entity.memo.server.si.SIData;
import com.nali.small.entity.memo.server.si.path.PathMath;
import com.nali.small.entity.memo.server.si.path.SNode;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.MoverType;
import net.minecraft.init.MobEffects;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;

public class SILeFindMove
<
	BD extends IBothDaE,
	E extends EntityLe,
	I extends IMixE<BD, E>,
	S extends ServerLe<BD, E, I, MS>,
	MS extends MixSIE<BD, E, I, S>
> extends SI<BD, E, I, S, MS>
{
	public static byte ID;

//	public static int MAX_G = 64*64;

	public SIESit<BD, E, I, S, MS> siesit;
//	public AILeSetLocation<E, I, S, MS> silesetlocation;
//	public SILeMove<BD, E, I, S, MS> silemove;
	public SILeWalkTo<BD, E, I, S, MS> silewalkto;
	public SILeMineTo<BD, E, I, S, MS> silemineto;
	public SILeLook<BD, E, I, S, MS> silelook;

//	public int goal_x, goal_y, goal_z;
	public double goal_x, goal_y, goal_z;
//	public int temp_goal_x, temp_goal_y, temp_goal_z;
	public double temp_goal_x, temp_goal_y, temp_goal_z;
//	public double far;

//	public List<BlockPos> path_blockpos_list = new ArrayList();
	public SNode to_goal_snode;
//	public boolean is_goal;
//	public int path_index;
//	public int path_tick;
	public boolean try_move;
	public byte re_tick = 0;//Byte.MIN_VALUE;
	public byte mo_tick = 0;

	public double move_x, move_y, move_z;
//	public double old_x, old_y, old_z;

	public SILeFindMove(S s)
	{
		super(s);//skinningentitiesmove walk_to
	}

	@Override
	public void init()
	{
		this.siesit = (SIESit<BD, E, I, S, MS>)this.s.ms.si_map.get(SIESit.ID);
//		this.silesetlocation = (AILeSetLocation<E, I, S, MS>)this.s.ms.si_map.get(AILeSetLocation.ID);
//		this.silemove = (SILeMove<BD, E, I, S, MS>)this.s.ms.si_map.get(SILeMove.ID);
		this.silewalkto = (SILeWalkTo<BD, E, I, S, MS>)this.s.ms.si_map.get(SILeWalkTo.ID);
		this.silemineto = (SILeMineTo<BD, E, I, S, MS>)this.s.ms.si_map.get(SILeMineTo.ID);
		this.silelook = (SILeLook<BD, E, I, S, MS>)this.s.ms.si_map.get(SILeLook.ID);
	}

	@Override
	public void call()
	{

	}

	@Override
	public void onUpdate()
	{
		E e = this.s.i.getE();

		this.move_x = 0;
		this.move_y = 0;
		this.move_z = 0;

//		if (!e.onGround)
		if (!e.isOnLadder())
		{
			this.move_y = -0.5F;
		}
		if (e.isPotionActive(MobEffects.LEVITATION))
		{
			e.motionY += (0.05D * (double)(e.getActivePotionEffect(MobEffects.LEVITATION).getAmplifier() + 1) - e.motionY) * 0.2D;
		}

		if (this.s.isMove(e))
		{
//			if ((serverentitiesmemory.main_work_byte_array[serverentitiesmemory.workbytes.SIT() / 8] >> serverentitiesmemory.workbytes.SIT() % 8 & 1) == 0)
//			if ((this.s.work_byte_array[serverentitiesmemory.workbytes.SIT() / 8] >> serverentitiesmemory.workbytes.SIT() % 8 & 1) == 0)
			if ((this.siesit.state & 1) == 0)
			{
				BlockPos blockpos = e.getPosition();

		//		if (this.path_blockpos_array != null)
		//		{
				SNode s_to_goal_snode = this.to_goal_snode;
				while (s_to_goal_snode != null)
				{
					BlockPos bp = s_to_goal_snode.blockpos;
					this.s.worldserver.spawnParticle(EnumParticleTypes.CRIT, bp.getX() + 0.5, bp.getY() + 0.5, bp.getZ() + 0.5, 1, 0.0D, 0.0D, 0.0D, 0.0D);
					s_to_goal_snode = s_to_goal_snode.parent_snode;
				}
//				Nali.LOGGER.info("TICK " + this.path_tick);
		//		}

				if (this.try_move)
				{
					if (this.temp_goal_x == this.goal_x && this.temp_goal_y == this.goal_y && this.temp_goal_z == this.goal_z && this.to_goal_snode != null/* && !this.path_blockpos_list.isEmpty()*/)
					{
						this.moveTo(e);
					}
					else
					{
//						this.re_tick = 0;
						this.silemineto.clear();
		//				this.s.entitiesaimemory.this.silemineto.blockpos = null;
						this.silewalkto.blockpos_list.clear();
		//				this.s.entitiesaimemory.skinningentitieswalkto.block_list.clear();
		//				this.old_x = this.skinningentities.posX;
		//				this.old_y = this.skinningentities.posY;
		//				this.old_z = this.skinningentities.posZ;
						this.to_goal_snode = null;
//						this.path_blockpos_list.clear();
//						this.is_goal = false;
//						this.path_tick = 0;

						SNode snode = new SNode(blockpos);
						SNode temp_snode = this.find(snode);

						if (temp_snode != null)
						{
		//					this.far = blockpos.distanceSq(this.goal_x, this.goal_y, this.goal_z);
//							if (!this.is_goal)
//							{
							this.to_goal_snode = temp_snode;
//							}

//							this.path_blockpos_list.add(temp_snode.blockpos);

//							while (temp_snode.parent_snode != null)
//							{
//								temp_snode = temp_snode.parent_snode;
////								this.path_blockpos_list.add(temp_snode.blockpos);
//							}

//							this.path_index = this.path_blockpos_list.size() - 1;

//							if (this.path_index == -1)
//							{
//								Nali.warn("path_index -1");
//								this.path_index = 0;
//							}
//							else
//							{
							this.moveTo(e);
//							}
						}
					}

					this.temp_goal_x = this.goal_x;
					this.temp_goal_y = this.goal_y;
					this.temp_goal_z = this.goal_z;
				}
			}
		}
//		else if (!this.path_blockpos_list.isEmpty())
//		{
//			this.path_blockpos_list.clear();
//		}

		if (this.move_y != 0 || this.move_x != 0 || this.move_z != 0)
		{
			e.move(MoverType.SELF, this.move_x, this.move_y, this.move_z);
		}
	}

	public void moveTo(E e)
	{
//		if (!this.is_goal)
//		{
		if (this.mo_tick++ == 2)
		{
			this.mo_tick = 0;
			SNode temp_snode = this.find(this.to_goal_snode);
			if (temp_snode == null)
			{
				this.endGoal();
			}
			else
			{
	////				Nali.warn("temp_snode " + temp_snode);
	////				Nali.warn("blockpos" + temp_snode.blockpos);
	//				int old_max_index = this.path_blockpos_list.size();
				this.to_goal_snode = temp_snode;

	//				this.path_blockpos_list.clear();
	//				this.path_blockpos_list.add(temp_snode.blockpos);
	//
	//				while (temp_snode.parent_snode != null)
	//				{
	//					temp_snode = temp_snode.parent_snode;
	//					this.path_blockpos_list.add(temp_snode.blockpos);
	//				}
	//
	////				if (temp_snode == this.to_goal_snode.parent_snode)
	////				{
	////
	////				}
	////				else
	////				{
	//				this.path_index = this.path_blockpos_list.size() - (old_max_index - this.path_index);
	////				}
	//
	//				if (this.path_index == -1)
	//				{
	//					this.path_index = 0;
	//				}
			}

//			if (this.mo_tick % 2 == 0 && ++this.re_tick == Byte.MAX_VALUE)
			if (++this.re_tick == 60)
			{
				this.to_goal_snode = null;
//				this.re_tick = Byte.MIN_VALUE;
				this.re_tick = 0;
				return;
			}
		}
//		else
//		{
//			Nali.warn("can't find");
//			this.to_goal_snode = null;
//			return;
//		}
//			else
//			{
//				Nali.error("can't find");
//			}
//		}

//		//				if (this.is_goal)
//		//				{
//		if (this.path_index < 0)
//		{
//			Nali.warn("path_index < 0");
//			this.path_blockpos_list.clear();
//			return;
//		}

		//				Nali.LOGGER.info("WANT!");

//		BlockPos current_blockpos = this.path_blockpos_list.get(this.path_index);
		BlockPos current_blockpos = this.to_goal_snode.blockpos;

		//-if path hitbox is less than entity and on same blockpos will have some problem in future
//		if (this.is_goal/* && this.path_index == 0*/)
//		{
////							this.silemove.setWanted(this.goal_x, this.goal_y, this.goal_z);
////							this.silemove.should_on_pos = true;
//			this.move_x += this.goal_x - e.posX;
//			this.move_y = this.goal_y - e.posY;//fix y side
//			this.move_z += this.goal_z - e.posZ;
//			this.endGoal();
//		}
//		else
//		{
//							this.silemove.setWanted(current_blockpos.getX() + 0.5D, current_blockpos.getY(), current_blockpos.getZ() + 0.5D);
		double b_x = current_blockpos.getX() + 0.5D;
		double b_y = current_blockpos.getY();
		double b_z = current_blockpos.getZ() + 0.5D;
		this.move_x += (b_x - e.posX) / 2.0F;
		this.move_y = (b_y - e.posY) / 2.0F;
		this.move_z += (b_z - e.posZ) / 2.0F;
//		this.silelook.set(b_x, b_y, b_z, 45.0F / 2.0F);
//		this.silelook.set(b_x, b_y, b_z, 1.0F);
		this.silelook.set(b_x, b_y, b_z, 360.0F);
//		}

		//				double far = this.skinningentities.getDistanceSq(this.goal_x + 0.5D, this.goal_y, this.goal_z + 0.5D);
		//				if (/*(!this.is_goal && far >= this.far) || *//*(*/this.is_goal && this.skinningentities.posX == this.old_x && this.skinningentities.posY == this.old_y && this.skinningentities.posZ == this.old_z/*)*/)
		//				{
		//					if (++this.path_tick == 200)
		//					{
		//						this.path_blockpos_list.clear();
		//						return;
		//					}
		//				}

		//				this.old_x = this.skinningentities.posX;
		//				this.old_y = this.skinningentities.posY;
		//				this.old_z = this.skinningentities.posZ;

		//				int new_index = this.path_index + 1;
		//				if (new_index != this.path_blockpos_list.size())
		//				{
		//					this.far = this.path_blockpos_list.get(new_index).distanceSq(this.goal_x, this.goal_y, this.goal_z);
		//				}

//		int path_index = this.path_index;
////						if (this.silemove.isDone())
////						if (this.re_tick == )
//		if (true)
//		{
//			//					--this.path_index;
//			if (--this.path_index == -1)
//			{
//				Nali.warn("--path_index -1");
//				this.try_move = false;
////								this.silemove.move = false;
//				this.path_blockpos_list.clear();
//			}
////			this.re_tick = 0;
//		}
		//				else
		//				{
		//					for (int b = 0; b < this.path_blockpos_list.size(); ++b)
		//					{
		//						BlockPos on_blockpos = this.path_blockpos_list.get(b);
		//						if ((int)this.skinningentities.posX == on_blockpos.getX() && (int)this.skinningentities.posY == on_blockpos.getY() && (int)this.skinningentities.posZ == on_blockpos.getZ())
		//						{
		//							this.path_index = b - 1;
		//							break;
		//						}
		//					}
		//				}

		//				if (this.path_index == -1)
		//				{
		//					this.try_move = false;
		//					this.path_blockpos_list.clear();
		//				}
		//				}

//		if (path_index == this.path_index)
//		{
//			if (++this.path_tick == 200)
//			{
//				this.path_blockpos_list.clear();
//				return;
//			}
//		}
//		else
//		{
//			this.path_tick = 0;
//		}
	}

	@Override
	public void writeFile(SIData sidata)
	{
	}

	@Override
	public void readFile(SIData sidata)
	{
	}

	@Override
	public int size()
	{
		return 0;
	}

	public void setGoal(double x, double y, double z)
	{
//		this.setGoal(MathHelper.floor(x), MathHelper.floor(y), MathHelper.floor(z));
//	}
//
//	public void setGoal(int x, int y, int z)
//	{
		this.goal_x = x;
		this.goal_y = y;
		this.goal_z = z;
		this.try_move = true;
		this.s.ms.state &= 255-(1+2);
	}

////	public void setBreakGoal(double px, double py, double pz)
//	public void setBreakGoal(double x, double y, double z)
//	{
////		this.setBreakGoal(MathHelper.floor(px), MathHelper.floor(py), MathHelper.floor(pz));
////	}
////
////	public void setBreakGoal(int x, int y, int z)
////	{
//		if (this.silemineto.goal_x != x || this.silemineto.goal_y != y || this.silemineto.goal_z != z)
//		{
//			this.silemineto.clear();
//		}
//		this.setGoal(x, y, z);
//		this.silemineto.goal_x = x;
//		this.silemineto.goal_y = y;
//		this.silemineto.goal_z = z;
//	}

	public void endGoal()
	{
		this.try_move = false;
//		this.path_blockpos_list.clear();
//		this.silemove.move = false;

		this.silemineto.clear();
	}

	public boolean endGoalT()
	{
		this.endGoal();
		return true;
	}

	public SNode find(SNode start_snode)
	{
		if (start_snode.blockpos.getX() == (int)this.goal_x && start_snode.blockpos.getY() == (int)this.goal_y && start_snode.blockpos.getZ() == (int)this.goal_z)
		{
//			this.is_goal = true;
//			this.endGoal();
			return null;
		}

		if (start_snode.children_snode_array == null)
		{
			return start_snode.parent_snode;
		}

		double to_x = ((int)this.goal_x + 0.5D) - (start_snode.blockpos.getX() + 0.5D);
		double to_y = ((int)this.goal_y + 0.5D) - (start_snode.blockpos.getY() + 0.5D);
		double to_z = ((int)this.goal_z + 0.5D) - (start_snode.blockpos.getZ() + 0.5D);
		double length = to_x * to_x + to_y * to_y + to_z * to_z;
		byte new_x = 0;
		byte new_y = 0;
		byte new_z = 0;
		if (length != 0)
		{
			length = Math.sqrt(length);
			new_x = PathMath.signum(to_x / length);
			new_y = PathMath.signum(to_y / length);
			new_z = PathMath.signum(to_z / length);
		}

		int index = PathMath.getIndex(new_x, new_y, new_z);
		SNode pre_snode = this.setChild(index, start_snode, new_x, new_y, new_z);
		start_snode.children_snode_array[index] = pre_snode;
		if (pre_snode != null)
		{
			return pre_snode;
		}

		while (true)
		{
			double to_goal = Double.MAX_VALUE;
			for (byte x : PathMath.PATH_BYTE_ARRAY)
			{
				for (byte y : PathMath.PATH_BYTE_ARRAY)
				{
					for (byte z : PathMath.PATH_BYTE_ARRAY)
					{
						if (!(x == 0 && y == 0 && z == 0))
						{
							index = PathMath.getIndex(x, y, z);
							SNode new_pre_snode = this.setChild(index, start_snode, x, y, z);
//							if (new_pre_snode != null)
							if (new_pre_snode == null)
							{
								start_snode.children_snode_array[index] = new SNode(null);
//								start_snode.children_snode_array[index] = new SNode(new BlockPos(start_snode.blockpos.getX() + x, start_snode.blockpos.getY() + y, start_snode.blockpos.getZ() + z));
							}
							else
							{
								double px = to_x - x;
								double py = to_y - y;
								double pz = to_z - z;
								double new_to_goal = px * px + py * py + pz * pz;
								if (to_goal > new_to_goal)
								{
									to_goal = new_to_goal;
									pre_snode = new_pre_snode;
									start_snode.children_snode_array[index] = new_pre_snode;
								}
							}
						}
					}
				}
			}

			if (to_goal == Double.MAX_VALUE)
			{
//				Nali.warn("OUT");
				start_snode.children_snode_array = null;
				return start_snode.parent_snode;
			}
			else
			{
//				Nali.warn("IN");
				return pre_snode;
			}
		}
	}

	public SNode setChild(int index, SNode start_snode, byte new_x, byte new_y, byte new_z)
	{
//		int index = PathMath.getIndex(new_x, new_y, new_z);
		if (index != -1 && start_snode.children_snode_array[index] == null)
		{
			BlockPos blockpos = new BlockPos(start_snode.blockpos.getX() + new_x, start_snode.blockpos.getY() + new_y, start_snode.blockpos.getZ() + new_z);
			SNode children_snode = new SNode(blockpos);
			children_snode.parent_snode = start_snode;
//			children_snode.calculateG(start_snode);
			IBlockState iblockstate = this.s.i.getE().world.getBlockState(blockpos);
			Material material = iblockstate.getMaterial();
			if ((passBlock(material) || this.isPassWithAct(blockpos)) && !isSame(children_snode))
			{
				if (this.isWalkAble(start_snode, new_x, new_y, new_z)/* && children_snode.g < MAX_G*//* && blockpos.getDistance(this.goal_x, this.goal_y, this.goal_z) <= 16.0D*/)
				{
					return children_snode;
				}
			}
		}
		return null;
	}

	public boolean isWalkAble(SNode snode, byte x, byte y, byte z)
	{
		if (x != 0 && z != 0)
		{
			if (y == -1)
			{
				Material temp_material0 = this.s.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY(), snode.blockpos.getZ()));//temp_iblockstate0.getMaterial();
				Material temp_material1 = this.s.getMaterial(new BlockPos(snode.blockpos.getX(), snode.blockpos.getY(), snode.blockpos.getZ() + z));//temp_iblockstate1.getMaterial();
				Material temp_material2 = this.s.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY(), snode.blockpos.getZ() + z));//temp_iblockstate2.getMaterial();
				Material temp_material3 = this.s.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() + y - 1, snode.blockpos.getZ() + z));
				Material temp_material4 = this.s.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() + y - 2, snode.blockpos.getZ() + z));

				if
				(
					(isBlock(temp_material0) && isBlock(temp_material1)) ||
					isBlock(temp_material2) ||
					(fallBlock(temp_material3) && fallBlock(temp_material4))
				)
				{
					return false;
				}
			}
			else if (y == 0)
			{
				Material temp_material0 = this.s.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY(), snode.blockpos.getZ()));//temp_iblockstate0.getMaterial();
				Material temp_material1 = this.s.getMaterial(new BlockPos(snode.blockpos.getX(), snode.blockpos.getY(), snode.blockpos.getZ() + z));//temp_iblockstate1.getMaterial();
				Material temp_material2 = this.s.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() + y - 1, snode.blockpos.getZ() + z));
				Material temp_material3 = this.s.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() + y - 2, snode.blockpos.getZ() + z));

				if
				(
					(isBlock(temp_material0) && isBlock(temp_material1)) ||
					(fallBlock(temp_material2) && fallBlock(temp_material3))
				)
				{
					return false;
				}
			}
			else if (y == 1)
			{
				Material temp_material2 = this.s.getMaterial(new BlockPos(snode.blockpos.getX(), snode.blockpos.getY() + y, snode.blockpos.getZ()));//temp_iblockstate2.getMaterial();
				Material temp_material3 = this.s.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY(), snode.blockpos.getZ() + z));//temp_iblockstate3.getMaterial();
				Material temp_material4 = this.s.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() + y, snode.blockpos.getZ()));//temp_iblockstate4.getMaterial();
				Material temp_material5 = this.s.getMaterial(new BlockPos(snode.blockpos.getX(), snode.blockpos.getY() + y, snode.blockpos.getZ() + z));//temp_iblockstate5.getMaterial();
				Material start_down_material = this.s.getMaterial(snode.blockpos.down());

				if
				(
					isBlock(temp_material2) ||
					fallBlock(temp_material3) ||
					(isBlock(temp_material4) && isBlock(temp_material5)) ||
					(passBlock(start_down_material) && !this.s.i.getE().isInWater())
				)
				{
					return false;
				}
			}
		}
		else
		{
			if (y == -1)
			{
				Material temp_material0 = this.s.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY(), snode.blockpos.getZ() + z));//temp_iblockstate0.getMaterial();
				Material temp_material1 = this.s.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() + y - 1, snode.blockpos.getZ() + z));//temp_iblockstate1.getMaterial();
				Material temp_material2 = this.s.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() + y - 2, snode.blockpos.getZ() + z));//temp_iblockstate2.getMaterial();

				if
				(
					isBlock(temp_material0) ||
					(fallBlock(temp_material1) && fallBlock(temp_material2))
				)
				{
					return false;
				}
			}
			else if (y == 0)
			{
				Material temp_material0 = this.s.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() - 1, snode.blockpos.getZ() + z));//temp_iblockstate0.getMaterial();
				Material temp_material1 = this.s.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY() - 2, snode.blockpos.getZ() + z));//temp_iblockstate1.getMaterial();

				if
				(
					fallBlock(temp_material0) && fallBlock(temp_material1)
				)
				{
					return false;
				}
			}
			else if (y == 1)
			{
				Material temp_material0 = this.s.getMaterial(new BlockPos(snode.blockpos.getX() + x, snode.blockpos.getY(), snode.blockpos.getZ() + z));//temp_iblockstate0.getMaterial();
				Material temp_material2 = this.s.getMaterial(new BlockPos(snode.blockpos.getX(), snode.blockpos.getY() + y, snode.blockpos.getZ()));//temp_iblockstate2.getMaterial();
				Material start_down_material = this.s.getMaterial(snode.blockpos.down());

				if
				(
					fallBlock(temp_material0) ||
					isBlock(temp_material2) ||
					(passBlock(start_down_material) && !this.s.i.getE().isInWater())
				)
				{
					return false;
				}
			}
		}

		return true;
	}

	public boolean isPassWithAct(BlockPos blockpos)
	{
//		if ((this.s.main_work_byte_array[this.s.workbytes.WALK_TO() / 8] >> this.s.workbytes.WALK_TO() % 8 & 1) == 0)
		if ((this.silewalkto.state & 1) == 1)
		{
			return false;
		}

		IBlockState iblockstate = this.s.i.getE().world.getBlockState(blockpos);
		Block block = iblockstate.getBlock();
		if (iblockstate.getMaterial() != Material.IRON && block instanceof BlockDoor || block instanceof BlockFenceGate || block instanceof BlockTrapDoor)
		{
			this.silewalkto.blockpos_list.add(blockpos);
//			this.s.entitiesaimemory.skinningentitieswalkto.block_list.add(block);
			return true;
		}

//		if ((this.s.main_work_byte_array[this.s.workbytes.MINE() / 8] >> this.s.workbytes.MINE() % 8 & 1) == 1)
		if ((this.silemineto.state & 1) == 1)
		{
			if (this.silemineto.blockpos == null)
			{
				this.silemineto.blockpos = blockpos;
			}

//			this.endGoal();
			return true;
		}

		return false;
	}

	public static boolean passBlock(Material material)
	{
//		return material == Material.AIR || material == Material.WATER;
		return !material.blocksMovement();
	}

	public static boolean isBlock(Material material)
	{
//		return material != Material.AIR && material != Material.WATER;// && material != Material.LAVA
		return material.blocksMovement();
	}

	public static boolean fallBlock(Material material)
	{
		return material == Material.AIR;
//		return !material.blocksMovement();
	}

	public static boolean isSame(SNode snode)
	{
		int x = snode.blockpos.getX();
		int y = snode.blockpos.getY();
		int z = snode.blockpos.getZ();
		SNode current_snode = snode.parent_snode;
		while (current_snode != null)
		{
			if (x == current_snode.blockpos.getX() && y == current_snode.blockpos.getY() && z == current_snode.blockpos.getZ())
			{
				return true;
			}

			current_snode = current_snode.parent_snode;

//			if (current_snode != null && current_snode.children_snode_array != null)
//			{
//				for (SNode c_snode : current_snode.children_snode_array)
//				{
//					if (c_snode != null && x == c_snode.blockpos.getX() && y == c_snode.blockpos.getY() && z == c_snode.blockpos.getZ())
//					{
//						return true;
//					}
//				}
//			}
		}

		return false;
	}
}
