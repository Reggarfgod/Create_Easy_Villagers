package com.reggarf.mods.create_easy_villagers.config;

import com.reggarf.mods.create_easy_villagers.Create_easy_villagers;
import com.reggarf.mods.create_easy_villagers.client.AutoTraderGuiEvents;
import net.createmod.catnip.config.ui.BaseConfigScreen;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;

public class CreateEasyVillagersClient {

    public static void onInitializeClient(final FMLClientSetupEvent event) {

        NeoForge.EVENT_BUS.register(new AutoTraderGuiEvents());

        ModLoadingContext.get().registerExtensionPoint(
                IConfigScreenFactory.class,
                () -> (mc, previous) -> new BaseConfigScreen(previous, Create_easy_villagers.MODID)
        );
    }

}
