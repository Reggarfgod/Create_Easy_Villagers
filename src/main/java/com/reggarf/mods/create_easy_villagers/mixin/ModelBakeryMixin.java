package com.reggarf.mods.create_easy_villagers.mixin;

import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Map;

@Mixin(ModelBakery.class)
public abstract class ModelBakeryMixin {

    @Shadow
    @Final
    private Map<ResourceLocation, BlockModel> modelResources;

    @ModifyVariable(
            method = "loadBlockModel",
            at = @At("HEAD"),
            argsOnly = true
    )
    private ResourceLocation redirectEasyVillagersModel(ResourceLocation location) {
        if ("easy_villagers".equals(location.getNamespace())) {
            ResourceLocation customLoc = ResourceLocation.fromNamespaceAndPath("create_easy_villagers", location.getPath());
            if (this.modelResources.containsKey(customLoc)) {
                return customLoc;
            }
        }
        return location;
    }
}
