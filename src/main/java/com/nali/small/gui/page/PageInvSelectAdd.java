package com.nali.small.gui.page;

import com.nali.gui.box.text.BoxTextAll;
import com.nali.gui.page.PageEdit;
import com.nali.list.gui.data.server.SDataInvSelectAdd;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.server.SPage;
import com.nali.network.NetworkRegistry;
import com.nali.system.bytes.ByteWriter;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PageInvSelectAdd extends PageEdit
{
	public static byte STATE;//enter client init

	@Override
	public void init()
	{
		super.init();

		InventoryPlayer inventoryplayer = Minecraft.getMinecraft().player.inventory;
		byte inventoryplayer_size = (byte)inventoryplayer.getSizeInventory();
		byte index = 0;
		this.boxtextall_array = new BoxTextAll[2 + inventoryplayer_size + 2];
		this.boxtextall_array[index++] = new BoxTextAll("SELECT-ADD".toCharArray());
		this.boxtextall_array[index++] = new BoxTextAll("ITEM".toCharArray());
		for (byte i = 0; i < inventoryplayer_size; ++i)
		{
			ItemStack itemstack = inventoryplayer.getStackInSlot(i);
			String text_string = i + " " + itemstack.getDisplayName();
//			Nali.warn(text_string);
			if (text_string.length() > 20)
			{
				text_string = text_string.substring(0, 20) + "...";
			}
			this.boxtextall_array[index++] = new BoxTextAll(text_string.toCharArray());
		}
		this.boxtextall_array[index++] = new BoxTextAll("ACTION".toCharArray());

		if ((this.state & 4) == 0)
		{
			this.select = index;
			this.state |= 4;
		}

		this.boxtextall_array[index] = new BoxTextAll("BACK".toCharArray());

		this.group_byte_array = new byte[(byte)Math.ceil((this.boxtextall_array.length - 1) / 8.0F)];
		byte new_index = (byte)(index - 2);
		this.group_byte_array[0 / 8] |= 1 << 0 % 8;
		this.group_byte_array[new_index / 8] |= 1 << new_index % 8;

//		for (BoxTextAll boxtextall : this.boxtextall_array)
//		{
//			Nali.warn("" + boxtextall);
//			Nali.warn("" + boxtextall.char_array);
//		}
	}

	@Override
	public void enter()
	{
//		if ((STATE & 1) == 0)
//		{
//			STATE |= 1;
		if (this.select == this.boxtextall_array.length - 1)
		{
			this.back();
		}
		else
		{
			byte[] byte_array = new byte[1 + 1 + 2 + 1];
			byte_array[0] = SPage.ID;
			byte_array[1] = SDataInvSelectAdd.ID;
			ByteWriter.set(byte_array, PageInv.INV, 2);
			byte_array[2+2] = (byte)(this.select - 2);
			NetworkRegistry.I.sendToServer(new ServerMessage(byte_array));
		}
//			STATE &= 255-1;
//		}
	}

	@Override
	public void draw()
	{
		if ((STATE & 4) == 4)
		{
			this.state &= 255-4;
			this.clear();
			this.init();

			this.gen();
			STATE &= 255-(2+4);
		}
		super.draw();
	}
}