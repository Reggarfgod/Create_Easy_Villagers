package com.reggarf.mods.create_easy_villagers;

import com.reggarf.mods.create_easy_villagers.client.CreateEasyVillagersClientSetup;
import com.reggarf.mods.create_easy_villagers.config.CreateEasyVillagersConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Create_easy_villagers.MODID)
public class Create_easy_villagers {
    public static final String MODID = "create_easy_villagers";

    public Create_easy_villagers() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CreateEasyVillagersConfig.SPEC);

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> CreateEasyVillagersClientSetup.init(modEventBus));
    }
}
