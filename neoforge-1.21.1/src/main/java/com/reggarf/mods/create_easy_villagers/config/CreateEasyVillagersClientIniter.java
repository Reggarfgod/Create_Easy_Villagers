package com.reggarf.mods.create_easy_villagers.config;


import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;


public class CreateEasyVillagersClientIniter {
    public static void onInitializeClient(final FMLClientSetupEvent event) {
        CreateEasyVillagersClient.onInitializeClient(event);
    }
}