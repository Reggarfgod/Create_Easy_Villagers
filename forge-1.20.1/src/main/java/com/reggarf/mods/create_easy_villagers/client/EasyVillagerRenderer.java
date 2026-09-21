package com.reggarf.mods.create_easy_villagers.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.reggarf.mods.create_easy_villagers.config.CreateEasyVillagersConfig;
import com.reggarf.mods.create_easy_villagers.util.EasyVillagerKineticHelper;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityVisual;

import de.maxhenkel.easyvillagers.blocks.tileentity.FakeWorldTileentity;
import dev.engine_room.flywheel.api.visualization.VisualizationManager;
import net.createmod.catnip.animation.AnimationTickHolder;
import net.createmod.catnip.render.CachedBuffers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

public class EasyVillagerRenderer<T extends FakeWorldTileentity> implements BlockEntityRenderer<T> {

    private final BlockEntityRenderer<T> originalRenderer;

    public EasyVillagerRenderer(BlockEntityRenderer<T> original) {
        this.originalRenderer = original;
    }

    @Override
    public void render(T be, float partialTicks, PoseStack ms, MultiBufferSource buffer, int light, int overlay) {
        // Draw the vanilla villager elements
        if (originalRenderer != null) {
            originalRenderer.render(be, partialTicks, ms, buffer, light, overlay);
        }

        // If Flywheel is active, let EasyVillagerVisual handle the animation!
        if (VisualizationManager.supportsVisualization(be.getLevel())) return;

        Direction powerSide = EasyVillagerKineticHelper.getPowerSide(be);
        if (powerSide == null) return;

        float speed = 0f;
        float offset = 0f;

        KineticBlockEntity kbe = EasyVillagerKineticHelper.getConnectedKinetic(be);
        if (kbe != null && !kbe.isOverStressed()) {
            float connectedSpeed = kbe.getSpeed();
            if (Math.abs(connectedSpeed) >= CreateEasyVillagersConfig.getMinimumSpeed()) {
                speed = connectedSpeed;
                offset = KineticBlockEntityVisual.rotationOffset(kbe.getBlockState(), powerSide.getAxis(), be.getBlockPos()) + kbe.getRotationAngleOffset(powerSide.getAxis());
            }
        }

        float angle = 0f;
        if (speed != 0f) {
            float time = AnimationTickHolder.getRenderTime(be.getLevel());
            angle = ((time * speed * 3f / 10f + offset) % 360f) / 180f * (float) Math.PI;
        }

        VertexConsumer vb = buffer.getBuffer(RenderType.solid());
        BlockState state = be.getBlockState();
        CachedBuffers.partialFacing(ModPartialModels.SHAFT_QUARTER, state, powerSide)
                .rotateCentered(angle, Direction.get(Direction.AxisDirection.POSITIVE, powerSide.getAxis()))
                .light(light)
                .renderInto(ms, vb);
    }
}
