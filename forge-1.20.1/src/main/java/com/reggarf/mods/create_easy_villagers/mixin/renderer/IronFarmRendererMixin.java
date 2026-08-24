package com.reggarf.mods.create_easy_villagers.mixin.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import de.maxhenkel.easyvillagers.blocks.tileentity.render.IronFarmRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = IronFarmRenderer.class, remap = false)
public abstract class IronFarmRendererMixin {

    @Redirect(
            method = "render(Lde/maxhenkel/easyvillagers/blocks/tileentity/IronFarmTileentity;FLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;II)V",
            at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;translate(DDD)V", ordinal = 1)
    )
    private void create_easy_villagers$offsetIronFarmVillager(PoseStack ms, double x, double y, double z) {
        ms.translate(x, y, -0.16D);
    }

    @Redirect(
            method = "render(Lde/maxhenkel/easyvillagers/blocks/tileentity/IronFarmTileentity;FLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;II)V",
            at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;translate(DDD)V", ordinal = 3)
    )
    private void create_easy_villagers$offsetIronFarmZombie(PoseStack ms, double x, double y, double z) {
        ms.translate(x, y, -0.16D);
    }
}
