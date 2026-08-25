package com.reggarf.mods.create_easy_villagers;

import com.mojang.logging.LogUtils;
import com.reggarf.mods.create_easy_villagers.api.MessagePlugin;
import com.reggarf.mods.create_easy_villagers.client.ModPartialModels;
import com.reggarf.mods.create_easy_villagers.config.CreateEasyVillagersClientIniter;
import com.reggarf.mods.create_easy_villagers.config.CreateEasyVillagersConfig;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import org.slf4j.Logger;

@Mod(Create_easy_villagers.MODID)
public class Create_easy_villagers {
    public static final String MODID = "create_easy_villagers";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Create_easy_villagers(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(CreateEasyVillagersClientIniter::onInitializeClient);
        modContainer.registerConfig(ModConfig.Type.COMMON, CreateEasyVillagersConfig.SPEC);
        modEventBus.addListener(this::onClientSetup);
        MessagePlugin.register();
    }

    private void onClientSetup(FMLClientSetupEvent event) {
        ModPartialModels.init();
        LOGGER.info("Create Easy Villagers client initialized");
    }
}
