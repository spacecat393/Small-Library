package com.nali.small.gui.mouse;

import com.nali.list.container.gui.SmallGui;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.server.SEToC;
import com.nali.network.NetworkRegistry;
import com.nali.small.gui.key.KeyMenuArmy;
import com.nali.small.gui.key.KeyMenuMe;
import com.nali.small.gui.page.PageArmy;
import com.nali.small.gui.page.PageMe;
import com.nali.system.bytes.ByteWriter;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.nali.list.container.gui.SmallGui.SMALLGUI;
import static com.nali.small.entity.memo.client.ClientE.C_MAP;
import static com.nali.small.gui.page.Page.FONT_MH_SH;
import static com.nali.small.gui.page.PageMe.openPageMe;

@SideOnly(Side.CLIENT)
public class MouseArmy extends Mouse
{
	public static float /*X, */Y, Y_STAR, DRAG;
	public float /*mouse_x, */mouse_y;

	public MouseArmy()
	{
		Y = 0;
	}

	@Override
	public void run()
	{
		if (PageArmy.PAGE == PAGE && HIT == 2)
		{
			KeyMenuArmy.STATE |= 4;
		}
		else if ((STATE & 1) == 1 && PAGE == 0 && HIT == 0)
		{
			KeyMenuArmy.STATE &= 255-4;
		}

		if ((STATE & 2) == 2)
		{
			float drag = (this.mouse_y - MOUSE_Y)/* * 0.1F*//* * SMALLGUI.partial_ticks*/;

			if (drag > 45)
			{
				drag = 45;
			}
			else if (drag < -45)
			{
				drag = -45;
			}

			if (DRAG > 0 && drag < 0)
			{
				DRAG = 0;
			}
			else if (DRAG < 0 && drag > 0)
			{
				DRAG = 0;
			}
			DRAG += drag * 0.1F;
		}

		Y += DRAG * SMALLGUI.partial_ticks * 0.005F;

		if (DRAG > 0)
		{
			DRAG -= SMALLGUI.partial_ticks/* * 0.05F*/;
		}
		else if (DRAG < 0)
		{
			DRAG += SMALLGUI.partial_ticks/* * 0.05F*/;
		}

		if ((int)DRAG == 0)
		{
			DRAG = 0;
		}

		Y -= EVENTDWHEEL * SMALLGUI.partial_ticks * 0.005F;

		if (EVENTDWHEEL > 0)
		{
			EVENTDWHEEL -= /*5.0F * */SMALLGUI.partial_ticks;
		}
		else
		{
			EVENTDWHEEL += /*5.0F * */SMALLGUI.partial_ticks;
		}

		if ((int)EVENTDWHEEL == 0)
		{
			EVENTDWHEEL = 0;
		}

		if (Y < 0)
		{
			Y = 0;
		}

		if (Y > PageArmy.MAX_Y)
		{
			Y = PageArmy.MAX_Y;
		}

		if (PageArmy.PAGE == PAGE)//126 -126 /2
		{
//			Nali.warn("PAGE " + PAGE);

			int id = /*(int)(Y / MAX_Y_OFFSET) * 62 + */HIT - 3;

//			Nali.warn("id " + id);
			if (HIT > 2 + 62)//re
			{
				byte[] byte_array = new byte[1 + 8];
				byte_array[0] = SEToC.ID;
				ByteWriter.set(byte_array, PageArmy.SEARCH_ID_KEY_LIST.get(PageArmy.INDEX_INT_ARRAY[id - 62] / 7), 1);
				NetworkRegistry.I.sendToServer(new ServerMessage(byte_array));
			}
			else if (HIT > 2)//me
			{
				KeyMenuMe.ME |= 1;
				PageMe.ID_KEY = PageArmy.SEARCH_ID_KEY_LIST.get(PageArmy.INDEX_INT_ARRAY[id] / 7);
				PageMe.E_ID = C_MAP.get(PageMe.ID_KEY).e_id;
				openPageMe();
			}
		}

//		warn("HIT " + HIT);
		if ((STATE & 2) == 2 && /*PageArmy.PAGE == PAGE && */HIT == 1)
		{
			float
			mouse_y = SmallGui.HEIGHT - MOUSE_Y,
			h_offset_y = FONT_MH_SH + 4.0F * 0.005F * SmallGui.HEIGHT;

			Y_STAR = (mouse_y - h_offset_y) / (SmallGui.HEIGHT) * 2.0F;
			Y = PageArmy.MAX_Y * (Y_STAR / PageArmy.MAX_Y_STAR);
		}
		else
		{
			Y_STAR = PageArmy.MAX_Y_STAR * (Y / PageArmy.MAX_Y);
		}

		this.mouse_y = MOUSE_Y;

		super.run();
	}
}
