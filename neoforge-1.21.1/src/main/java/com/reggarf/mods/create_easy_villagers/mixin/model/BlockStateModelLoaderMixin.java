package com.reggarf.mods.create_easy_villagers.mixin.model;

import net.minecraft.client.resources.model.BlockStateModelLoader;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mixin(BlockStateModelLoader.class)
public abstract class BlockStateModelLoaderMixin {

    @ModifyVariable(
            method = "<init>",
            at = @At("HEAD"),
            argsOnly = true,
            ordinal = 0
    )
    private static Map<ResourceLocation, List<BlockStateModelLoader.LoadedJson>> create_easy_villagers$redirectBlockstateMap(
            Map<ResourceLocation, List<BlockStateModelLoader.LoadedJson>> map
    ) {
        Map<ResourceLocation, List<BlockStateModelLoader.LoadedJson>> newMap = new HashMap<>(map);
        for (Map.Entry<ResourceLocation, List<BlockStateModelLoader.LoadedJson>> entry : map.entrySet()) {
            ResourceLocation key = entry.getKey();
            if ("create_easy_villagers".equals(key.getNamespace())) {
                ResourceLocation easyVillagersKey = ResourceLocation.fromNamespaceAndPath("easy_villagers", key.getPath());
                newMap.put(easyVillagersKey, entry.getValue());
            }
        }
        return newMap;
    }
}
