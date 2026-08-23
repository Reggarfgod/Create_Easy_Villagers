package com.reggarf.mods.create_easy_villagers.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import de.maxhenkel.easyvillagers.blocks.tileentity.IronFarmTileentity;
import de.maxhenkel.easyvillagers.blocks.tileentity.render.IronFarmRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(IronFarmRenderer.class)
public abstract class IronFarmRendererMixin {

    @Redirect(
            method = "render(Lde/maxhenkel/easyvillagers/blocks/tileentity/IronFarmTileentity;FLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;II)V",
            at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;translate(DDD)V", ordinal = 1)
    )
    private void create_easy_villagers$offsetIronFarmVillager(PoseStack ms, double x, double y, double z) {
        // Shift the villager forward away from the back wall (from -0.3125 to -0.16)
        ms.translate(x, y, -0.16D);
    }

    @Redirect(
            method = "render(Lde/maxhenkel/easyvillagers/blocks/tileentity/IronFarmTileentity;FLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;II)V",
            at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;translate(DDD)V", ordinal = 3)
    )
    private void create_easy_villagers$offsetIronFarmZombie(PoseStack ms, double x, double y, double z) {
        // Shift the zombie forward away from the back wall (from -0.3125 to -0.16)
        ms.translate(x, y, -0.16D);
    }
}
