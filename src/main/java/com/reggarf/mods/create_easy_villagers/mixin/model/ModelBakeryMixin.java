package com.reggarf.mods.create_easy_villagers.mixin.model;

import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.HashMap;
import java.util.Map;

@Mixin(ModelBakery.class)
public abstract class ModelBakeryMixin {

    @ModifyVariable(
            method = "<init>",
            at = @At("HEAD"),
            argsOnly = true,
            ordinal = 0
    )
    private static Map<ResourceLocation, BlockModel> create_easy_villagers$redirectModelMap(
            Map<ResourceLocation, BlockModel> map
    ) {
        Map<ResourceLocation, BlockModel> newMap = new HashMap<>(map);
        for (Map.Entry<ResourceLocation, BlockModel> entry : map.entrySet()) {
            ResourceLocation key = entry.getKey();
            if ("create_easy_villagers".equals(key.getNamespace())) {
                ResourceLocation easyVillagersKey = ResourceLocation.fromNamespaceAndPath("easy_villagers", key.getPath());
                newMap.put(easyVillagersKey, entry.getValue());
            }
        }
        return newMap;
    }
}
