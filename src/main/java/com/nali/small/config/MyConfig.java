package com.nali.small.config;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import static com.nali.small.Small.ID;

@Config(modid = ID)
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

    @Mod.EventBusSubscriber(modid = ID, value = Side.CLIENT)
    public static class ConfigEvent
    {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
        {
            if (event.getModID().equals(ID))
            {
                ConfigManager.sync(ID, Config.Type.INSTANCE);
            }
        }
    }
}
