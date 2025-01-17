//package com.nali.list.entity.si;
//
//import com.nali.da.IBothDaE;
//import com.nali.small.entity.EntityLe;
//import com.nali.small.entity.IMixE;
//import com.nali.small.entity.memo.server.ServerLe;
//import com.nali.small.entity.memo.server.si.MixSIE;
//import com.nali.small.entity.memo.server.si.SI;
//import com.nali.small.entity.memo.server.si.SIData;
//
//public class SILeMove
//<
//	BD extends IBothDaE,
//	E extends EntityLe,
//	I extends IMixE<BD, E>,
//	S extends ServerLe<BD, E, I, MS>,
//	MS extends MixSIE<BD, E, I, S>
//> extends SI<BD, E, I, S, MS>
//{
//	public static byte ID;
//
//	public SIELook<BD, E, I, S, MS> sielook;
//	public SILeJump<BD, E, I, S, MS> silejump;
//	public SIESit<BD, E, I, S, MS> siesit;
//
//	public double x, y, z;
//	public double speed;
//	public boolean move, should_on_pos;
//
//	public SILeMove(S s)
//	{
//		super(s);
//	}
//
//	@Override
//	public void init()
//	{
//		this.sielook = (SIELook<BD, E, I, S, MS>)this.s.ms.si_map.get(SIELook.ID);
//		this.silejump = (SILeJump<BD, E, I, S, MS>)this.s.ms.si_map.get(SILeJump.ID);
//		this.siesit = (SIESit<BD, E, I, S, MS>)this.s.ms.si_map.get(SIESit.ID);
//	}
//
//	@Override
//	public void call()
//	{
//
//	}
//
//	@Override
//	public void onUpdate()
//	{
////		//use e.move(MoverType type, double x, double y, double z)
////		E e = this.s.i.getE();
////		if (this.s.isMove(e))
////		{
////			if ((this.siesit.state & 1) == 0)
////			{
////				double wy = this.y - e.posY;
////
////				if (e.isInWater())
////				{
////					this.speed = 6.0F;
////					e.setNoGravity(true);
////					e.onGround = true;
////				}
////				else
////				{
////					this.speed = 3.0F;
////					e.setNoGravity(false);
////				}
////
////				if (this.move || wy > 0.0D)
////				{
////					float f1 = (float)(e.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());
////
////					if (e.isInWater())
////					{
////						if (wy > 0)
////						{
////							wy = 2.0D;
////						}
////						else if (wy < 0)
////						{
////							wy = -2.0D;
////						}
////
////						if (e.onGround || e.isInWater())
////						{
////							e.motionY += wy * f1;
////						}
////					}
////				}
////
////				if (this.move)
////				{
////					if (this.sielook.done)
////					{
////						Entity riding_entity = e.getRidingEntity();
////						if (riding_entity != null)
////						{
////							Vec3d vec3d = riding_entity.getLookVec().scale((float)(this.speed * e.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()));
////							riding_entity.move(MoverType.SELF, vec3d.x, vec3d.y, vec3d.z);
////						}
////						else
////						{
////							e.setAIMoveSpeed((float)(this.speed * e.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()));
////						}
////					}
////
////					this.sielook.set(this.x, this.y, this.z, 4.5F);
////					if (wy > 0.0D)
////					{
////						this.silejump.setJumping();
////					}
////				}
////				else
////				{
////					e.moveForward = 0.0F;
////				}
////			}
////		}
//	}
//
//	@Override
//	public void writeFile(SIData sidata)
//	{
//	}
//
//	@Override
//	public void readFile(SIData sidata)
//	{
//	}
//
//	@Override
//	public int size()
//	{
//		return 0;
//	}
//
//	public void setWanted(double x, double y, double z)
//	{
//		this.x = x;
//		this.y = y;
//		this.z = z;
//		this.move = true;
//	}
//
//	public boolean isDone()
//	{
//		E e = this.s.i.getE();
//		boolean should_work = (int)e.posX == (int)this.x && (int)e.posY == (int)this.y && (int)e.posZ == (int)this.z;
//		if (should_work && this.should_on_pos)
//		{
//			e.setPosition(this.x, this.y, this.z);
//			this.should_on_pos = false;
//		}
//		return should_work;
//	}
//}
