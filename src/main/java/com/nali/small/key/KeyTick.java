//package com.nali.small.key;
//
//import com.nali.list.gui.PlayerGui;
//import com.nali.list.key.SmallPlayerGui;
//import com.nali.list.key.SmallQuickInventoryGUI;
//import com.nali.list.messages.ServerMessage;
//import com.nali.list.netmethods.servermessage.OpenInvGUI;
//import com.nali.list.netmethods.servermessage.OpenPlayerGUI;
//import com.nali.networks.NetworksRegistry;
//import com.nali.small.system.Reference;
//import com.nali.system.bytes.BytesWriter;
//import net.minecraft.client.Minecraft;
//import net.minecraftforge.fml.common.Mod;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//import org.lwjgl.input.Keyboard;
//import org.lwjgl.input.Mouse;
//
//import java.util.UUID;
//
//import static com.nali.key.KeyHelper.getTextFromClipboard;
//import static com.nali.key.KeyHelper.isValidUUIDString;
//
//@Mod.EventBusSubscriber(modid = Reference.MOD_ID, value = Side.CLIENT)
//@SideOnly(Side.CLIENT)
//public class KeyTick
//{
////    public static Function FUNCTION = (v) -> v;
//    public static Void run(int key)
//    {
////        if
////        (
////            key == KeyRegistryHelper.PLAYER_GUI_KEYBINDING.getKeyCode()
////        )
////        {
////            com.nali.key.KeyTick.addKey(key);
////        }
//        Minecraft minecraft = Minecraft.getMinecraft();
//        int i = Keyboard.getEventKey() == 0 ? Keyboard.getEventCharacter() + 256 : Keyboard.getEventKey();
//        int l = Mouse.getEventButton() - 100;
//        if (SmallQuickInventoryGUI.I.getKeyCode() != 0 && (i == SmallQuickInventoryGUI.I.getKeyCode() || l == SmallQuickInventoryGUI.I.getKeyCode()))
//        {
//            if (minecraft.currentScreen == null)
//            {
//                String uuid_string = getTextFromClipboard();
//                if (isValidUUIDString(uuid_string))
//                {
//                    minecraft.addScheduledTask(() ->
//                    {
//                        byte[] byte_array = new byte[21];
//                        byte_array[0] = OpenInvGUI.ID;
//                        BytesWriter.set(byte_array, UUID.fromString(uuid_string), 1);
//                        BytesWriter.set(byte_array, 1, 17);
//                        NetworksRegistry.I.sendToServer(new ServerMessage(byte_array));
//                    });
//                }
//            }
//        }
//        else if (SmallPlayerGui.I.getKeyCode() != 0 && (i == SmallPlayerGui.I.getKeyCode() || l == SmallPlayerGui.I.getKeyCode()))
//        {
//            if (minecraft.currentScreen == null)
//            {
//                PlayerGui.PAGE = 0;
//
//                NetworksRegistry.I.sendToServer(new ServerMessage(new byte[]{OpenPlayerGUI.ID}));
//
////                minecraft.addScheduledTask(() ->
////                {
////                    minecraft.displayGuiScreen(new PlayerGui(new PlayerContainer()));
////                });
//            }
//        }
//
//        return null;
//    }
//}
