package com.reggarf.mods.create_easy_villagers;

import com.reggarf.mods.create_easy_villagers.api.MessagePlugin;
import com.reggarf.mods.create_easy_villagers.client.CreateEasyVillagersClientSetup;
import com.reggarf.mods.create_easy_villagers.config.CreateEasyVillagersConfig;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.loading.FMLEnvironment;

@Mod(Create_easy_villagers.MODID)
public class Create_easy_villagers {
    public static final String MODID = "create_easy_villagers";

    public Create_easy_villagers(IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.COMMON, CreateEasyVillagersConfig.SPEC);
        if (FMLEnvironment.dist.isClient()) {
            CreateEasyVillagersClientSetup.init(modEventBus);
        }
        MessagePlugin.register();
    }
}
