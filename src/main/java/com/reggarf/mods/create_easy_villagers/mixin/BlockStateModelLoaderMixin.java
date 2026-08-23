package com.reggarf.mods.create_easy_villagers.mixin;

import net.minecraft.client.resources.model.BlockStateModelLoader;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.StateDefinition;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.List;
import java.util.Map;

@Mixin(BlockStateModelLoader.class)
public abstract class BlockStateModelLoaderMixin {

    @Shadow
    @Final
    private Map<ResourceLocation, List<BlockStateModelLoader.LoadedJson>> blockStateResources;

    @ModifyVariable(
            method = "loadBlockStateDefinition",
            at = @At("HEAD"),
            argsOnly = true
    )
    private ResourceLocation redirectEasyVillagersBlockstate(ResourceLocation id) {
        if ("easy_villagers".equals(id.getNamespace())) {
            ResourceLocation customLoc = ResourceLocation.fromNamespaceAndPath("create_easy_villagers", id.getPath());
            if (this.blockStateResources.containsKey(customLoc)) {
                return customLoc;
            }
        }
        return id;
    }
}
