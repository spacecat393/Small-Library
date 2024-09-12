//package com.nali.list.key;
//
//import com.nali.key.MixKeyBinding;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//import org.lwjgl.input.Keyboard;
//
//@SideOnly(Side.CLIENT)
//public class SmallQuickInventoryGUI extends MixKeyBinding
//{
//	public static int ID;
//	public SmallQuickInventoryGUI(String[] string_array, Integer key)
//	{
//		super(string_array, key == null ? Keyboard.KEY_B : key);
////		this.setKeyConflictContext(KeyConflictContext.IN_GAME);
//	}
//
//	public static void detect()
//	{
////		if (Minecraft.getMinecraft().currentScreen == null)
////		{
////			String uuid_string = getTextFromClipboard();
////			if (isValidUUIDString(uuid_string))
////			{
////				byte[] byte_array = new byte[21];
////				byte_array[0] = SOpenInvGUI.ID;
////				ByteWriter.set(byte_array, UUID.fromString(uuid_string), 1);
////				ByteWriter.set(byte_array, 1, 17);
////				NetworkRegistry.I.sendToServer(new ServerMessage(byte_array));
////			}
////		}
//	}
//}
