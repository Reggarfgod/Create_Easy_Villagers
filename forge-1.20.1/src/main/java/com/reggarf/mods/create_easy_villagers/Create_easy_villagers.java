package com.reggarf.mods.create_easy_villagers;

import com.mojang.logging.LogUtils;
import com.reggarf.mods.create_easy_villagers.client.ModPartialModels;
import com.reggarf.mods.create_easy_villagers.config.CreateEasyVillagersClient;
import com.reggarf.mods.create_easy_villagers.config.CreateEasyVillagersConfig;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Create_easy_villagers.MODID)
public class Create_easy_villagers {
    public static final String MODID = "create_easy_villagers";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Create_easy_villagers() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CreateEasyVillagersConfig.SPEC);
        modEventBus.addListener(CreateEasyVillagersClient::onInitializeClient);
        modEventBus.addListener(this::onClientSetup);
    }

    private void onClientSetup(FMLClientSetupEvent event) {
        ModPartialModels.init();
        LOGGER.info("Create Easy Villagers client initialized");
    }
}
