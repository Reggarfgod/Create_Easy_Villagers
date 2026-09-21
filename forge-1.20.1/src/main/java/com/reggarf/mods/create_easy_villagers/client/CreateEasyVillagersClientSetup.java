package com.reggarf.mods.create_easy_villagers.client;

import com.mojang.logging.LogUtils;
import com.reggarf.mods.create_easy_villagers.config.CreateEasyVillagersClient;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.slf4j.Logger;

public class CreateEasyVillagersClientSetup {
    private static final Logger LOGGER = LogUtils.getLogger();

    public static void init(IEventBus modEventBus) {
        modEventBus.addListener(CreateEasyVillagersClient::onInitializeClient);
        modEventBus.addListener(CreateEasyVillagersClientSetup::onClientSetup);
    }

    private static void onClientSetup(FMLClientSetupEvent event) {
        ModPartialModels.init();
        LOGGER.info("Create Easy Villagers client initialized");
    }
}
