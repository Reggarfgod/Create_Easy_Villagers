package com.reggarf.mods.create_easy_villagers.client;

import com.reggarf.mods.create_easy_villagers.Create_easy_villagers;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import net.minecraft.resources.ResourceLocation;

public class ModPartialModels {

    public static final PartialModel SHAFT_QUARTER = PartialModel.of(
            ResourceLocation.fromNamespaceAndPath(Create_easy_villagers.MODID, "block/shaft/shaft_quarter")
    );

    public static void init() {
        // Load static fields
    }
}
