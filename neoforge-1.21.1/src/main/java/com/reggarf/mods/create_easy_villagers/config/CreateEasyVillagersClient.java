package com.reggarf.mods.create_easy_villagers.config;

import com.reggarf.mods.create_easy_villagers.Create_easy_villagers;
import net.createmod.catnip.config.ui.BaseConfigScreen;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

public class CreateEasyVillagersClient {

    public static void onInitializeClient(final FMLClientSetupEvent event) {

        ModLoadingContext.get().registerExtensionPoint(
                IConfigScreenFactory.class,
                () -> (mc, previous) -> new BaseConfigScreen(previous, Create_easy_villagers.MODID)
        );
    }

}