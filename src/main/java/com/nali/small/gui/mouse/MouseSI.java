//package com.nali.small.gui.mouse;
//
//import com.nali.Nali;
//import com.nali.list.container.gui.SmallGui;
//import com.nali.small.gui.key.KeyMenu;
//import com.nali.small.gui.page.*;
//import com.nali.system.Reflect;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//
//import java.util.Comparator;
//import java.util.List;
//
//import static com.nali.list.container.gui.SmallGui.SMALLGUI;
//import static com.nali.list.container.gui.SmallGui.addSet;
//import static com.nali.small.gui.page.Page.FONT_MH_SH;
//
//@SideOnly(Side.CLIENT)
//public class MouseSI extends Mouse
//{
//	public static List<Class> GI_CLASS_LIST;
//
//	static
//	{
//		GI_CLASS_LIST = Reflect.getClasses("com.nali.list.entity.gi");
//		GI_CLASS_LIST.sort(Comparator.comparing(Class::getName));
//	}
//
//	public static float Y, Y_STAR, DRAG;
//	public float mouse_y;
//
//	public MouseSI()
//	{
//		Y = 0;
//	}
//
//	@Override
//	public void run()
//	{
//		if (PageSI.PAGE == PAGE && HIT == 2)
//		{
//			KeyMenu.STATE |= 4;
//			PageTextSearch.STATE_BYTE_ARRAY[PageSI.TEXT_INDEX] |= 1;
//		}
//		else if ((STATE & 1) == 1)
//		{
//			KeyMenu.STATE &= 255-4;
//			PageTextSearch.STATE_BYTE_ARRAY[PageSI.TEXT_INDEX] &= 255-1;
//		}
//
//		if ((STATE & 2) == 2)
//		{
//			float drag = (this.mouse_y - MOUSE_Y);
//
//			if (drag > 45)
//			{
//				drag = 45;
//			}
//			else if (drag < -45)
//			{
//				drag = -45;
//			}
//
//			if (DRAG > 0 && drag < 0)
//			{
//				DRAG = 0;
//			}
//			else if (DRAG < 0 && drag > 0)
//			{
//				DRAG = 0;
//			}
//			DRAG += drag * 0.1F;
//		}
//
//		Y += DRAG * SMALLGUI.partial_ticks * 0.005F;
//
//		if (DRAG > 0)
//		{
//			DRAG -= SMALLGUI.partial_ticks;
//		}
//		else if (DRAG < 0)
//		{
//			DRAG += SMALLGUI.partial_ticks;
//		}
//
//		if ((int)DRAG == 0)
//		{
//			DRAG = 0;
//		}
//
//		Y -= EVENTDWHEEL * SMALLGUI.partial_ticks * 0.005F;
//		if (EVENTDWHEEL > 0)
//		{
//			EVENTDWHEEL -= SMALLGUI.partial_ticks;
//		}
//		else
//		{
//			EVENTDWHEEL += SMALLGUI.partial_ticks;
//		}
//
//		if ((int)EVENTDWHEEL == 0)
//		{
//			EVENTDWHEEL = 0;
//		}
//
//		if (Y < 0)
//		{
//			Y = 0;
//		}
//
//		if (Y > PageSI.MAX_Y)
//		{
//			Y = PageSI.MAX_Y;
//		}
//
//		if (PageSI.PAGE == PAGE)
//		{
////			Nali.warn("PAGE " + PAGE);
//
//			int id = HIT - 3;
//
////			Nali.warn("id " + id);
//			if (HIT > 2)
//			{
//				PageMenu.BYTE |= 1;
//
//				try
//				{
//					Class gi_class = GI_CLASS_LIST.get(PageSI.SEARCH_SI_BYTE_LIST.get(PageSI.INDEX_INT_ARRAY[id]));
//					Page page = (Page)gi_class.newInstance();
//					Nali.LOGGER.info("id " + id);
//					Nali.LOGGER.info("INDEX_INT_ARRAY " + PageSI.INDEX_INT_ARRAY[id]);
//					Nali.LOGGER.info("SEARCH_SI_BYTE_LIST " + PageSI.SEARCH_SI_BYTE_LIST.get(PageSI.INDEX_INT_ARRAY[id]));
//					Nali.LOGGER.info("Page " + gi_class.getSimpleName());
////					KEY = (Key)gi_class.getMethod("getKey").invoke(null);//new KeyMenuGI();
////					MOUSE = (Mouse)gi_class.getMethod("getMouse").invoke(null);//new Mouse();
////					PAGE_ARRAY = new Page[]
////					{
////						new PageBlur(),
////						new PageMenu(STRING_ARRAY[14] + ((KeyMenuMe.ME & 1) == 1 ? "|" + STRING_ARRAY[0] : "") + "|" + PageMe.UUID + "|" + STRING_ARRAY[1] + "|" + MixAIE.SI_CLASS_LIST.get(id).getSimpleName()),
////						new PageSakura(),
////						new PageKey(),
////						(Page)gi_class.newInstance()
////					};
//				}
//				catch (IllegalAccessException | InstantiationException/* | NoSuchMethodException | InvocationTargetException*/ e)
//				{
////					error(e);
//				}
//
//				addSet();
//			}
//		}
//
////		warn("HIT " + HIT);
//		if ((STATE & 2) == 2 && HIT == 1)
//		{
//			float
//			mouse_y = SmallGui.HEIGHT - MOUSE_Y,
//			h_offset_y = FONT_MH_SH + 4.0F * 0.005F * SmallGui.HEIGHT;
//
//			Y_STAR = (mouse_y - h_offset_y) / (SmallGui.HEIGHT) * 2.0F;
//			Y = PageSI.MAX_Y * (Y_STAR / PageSI.MAX_Y_STAR);
//		}
//		else
//		{
//			Y_STAR = PageSI.MAX_Y_STAR * (Y / PageSI.MAX_Y);
//		}
//
//		this.mouse_y = MOUSE_Y;
//
//		super.run();
//	}
//}
