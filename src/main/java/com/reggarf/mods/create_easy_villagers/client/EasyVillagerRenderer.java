package com.reggarf.mods.create_easy_villagers.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.simibubi.create.AllPartialModels;
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
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

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

        Direction powerSide = null;
        if (be.getBlockState().hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
            powerSide = be.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING).getOpposite();
        }
        if (powerSide == null) return;

        BlockEntity neighbor = be.getLevel().getBlockEntity(be.getBlockPos().relative(powerSide));
        if (neighbor instanceof KineticBlockEntity kbe && !kbe.isOverStressed()) {
            float speed = kbe.getSpeed();
            if (Math.abs(speed) >= 32) {
                VertexConsumer vb = buffer.getBuffer(RenderType.solid());
                
                float time = AnimationTickHolder.getRenderTime(be.getLevel());
                float offset = KineticBlockEntityVisual.rotationOffset(kbe.getBlockState(), powerSide.getAxis(), be.getBlockPos()) + kbe.getRotationAngleOffset(powerSide.getAxis());
                float angle = ((time * speed * 3f / 10f + offset) % 360f) / 180f * (float) Math.PI;

                BlockState state = be.getBlockState();
                CachedBuffers.partialFacing(AllPartialModels.SHAFT_HALF, state, powerSide)
                        .rotateCentered(angle, Direction.get(Direction.AxisDirection.POSITIVE, powerSide.getAxis()))
                        .light(light)
                        .renderInto(ms, vb);
            }
        }
    }
}
