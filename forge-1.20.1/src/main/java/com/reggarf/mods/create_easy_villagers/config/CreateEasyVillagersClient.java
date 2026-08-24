package com.reggarf.mods.create_easy_villagers.config;

import com.reggarf.mods.create_easy_villagers.Create_easy_villagers;
import net.createmod.catnip.config.ui.BaseConfigScreen;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class CreateEasyVillagersClient {

    public static void onInitializeClient(final FMLClientSetupEvent event) {
        ModLoadingContext.get().registerExtensionPoint(
                ConfigScreenHandler.ConfigScreenFactory.class,
                () -> new ConfigScreenHandler.ConfigScreenFactory((mc, previous) -> new BaseConfigScreen(previous, Create_easy_villagers.MODID))
        );
    }
}
