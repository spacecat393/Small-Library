package com.nali.small.config;

import com.nali.small.system.Reference;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Config(modid = Small.ID)
public class MyConfig
{
    @Config.Name("Server Settings")
    public static final Server SERVER = new Server();

    public static class Server
    {
        @Config.Name("Player RNG")
        @Config.Comment("Enable RNG on Player GUI.")
        @Config.RequiresMcRestart
        public boolean player_rng = false;
    }

    @Mod.EventBusSubscriber(modid = Small.ID, value = Side.CLIENT)
    public static class ConfigEvent
    {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
        {
            if (event.getModID().equals(Small.ID))
            {
                ConfigManager.sync(Small.ID, Config.Type.INSTANCE);
            }
        }
    }
}
