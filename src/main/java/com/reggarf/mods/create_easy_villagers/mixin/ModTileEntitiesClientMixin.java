package com.reggarf.mods.create_easy_villagers.mixin;

import de.maxhenkel.easyvillagers.blocks.tileentity.ModTileEntities;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.reggarf.mods.create_easy_villagers.client.EasyVillagerRenderer;
import com.reggarf.mods.create_easy_villagers.client.EasyVillagerVisual;

import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import dev.engine_room.flywheel.api.visualization.VisualizerRegistry;
import dev.engine_room.flywheel.lib.visualization.SimpleBlockEntityVisualizer;

import de.maxhenkel.easyvillagers.blocks.tileentity.render.AutoTraderRenderer;
import de.maxhenkel.easyvillagers.blocks.tileentity.render.IronFarmRenderer;
import de.maxhenkel.easyvillagers.blocks.tileentity.render.BreederRenderer;
import de.maxhenkel.easyvillagers.blocks.tileentity.render.ConverterRenderer;
import de.maxhenkel.easyvillagers.blocks.tileentity.render.FarmerRenderer;
import de.maxhenkel.easyvillagers.blocks.tileentity.render.IncubatorRenderer;

@Mixin(value = ModTileEntities.class, remap = false)
public class ModTileEntitiesClientMixin {

    @Inject(method = "clientSetup", at = @At("TAIL"))
    private static void overwriteRenderers(CallbackInfo ci) {
        // Overwrite the default BlockEntityRenderers with our wrapper that includes Create Shaft rendering
        BlockEntityRenderers.register(ModTileEntities.AUTO_TRADER.get(), ctx -> new EasyVillagerRenderer<>(new AutoTraderRenderer(ctx)));
        BlockEntityRenderers.register(ModTileEntities.IRON_FARM.get(), ctx -> new EasyVillagerRenderer<>(new IronFarmRenderer(ctx)));
        BlockEntityRenderers.register(ModTileEntities.BREEDER.get(), ctx -> new EasyVillagerRenderer<>(new BreederRenderer(ctx)));
        BlockEntityRenderers.register(ModTileEntities.CONVERTER.get(), ctx -> new EasyVillagerRenderer<>(new ConverterRenderer(ctx)));
        BlockEntityRenderers.register(ModTileEntities.FARMER.get(), ctx -> new EasyVillagerRenderer<>(new FarmerRenderer(ctx)));
        BlockEntityRenderers.register(ModTileEntities.INCUBATOR.get(), ctx -> new EasyVillagerRenderer<>(new IncubatorRenderer(ctx)));

        // Register Flywheel Visualizers for them exactly like Create Mod!
        SimpleBlockEntityVisualizer.builder(ModTileEntities.AUTO_TRADER.get()).factory(EasyVillagerVisual::new).neverSkipVanillaRender().apply();
        SimpleBlockEntityVisualizer.builder(ModTileEntities.IRON_FARM.get()).factory(EasyVillagerVisual::new).neverSkipVanillaRender().apply();
        SimpleBlockEntityVisualizer.builder(ModTileEntities.BREEDER.get()).factory(EasyVillagerVisual::new).neverSkipVanillaRender().apply();
        SimpleBlockEntityVisualizer.builder(ModTileEntities.CONVERTER.get()).factory(EasyVillagerVisual::new).neverSkipVanillaRender().apply();
        SimpleBlockEntityVisualizer.builder(ModTileEntities.FARMER.get()).factory(EasyVillagerVisual::new).neverSkipVanillaRender().apply();
        SimpleBlockEntityVisualizer.builder(ModTileEntities.INCUBATOR.get()).factory(EasyVillagerVisual::new).neverSkipVanillaRender().apply();
    }
}
