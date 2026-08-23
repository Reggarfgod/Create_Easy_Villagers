package com.reggarf.mods.create_easy_villagers.client;

import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityVisual;
import com.simibubi.create.content.kinetics.base.RotatingInstance;
import com.simibubi.create.foundation.render.AllInstanceTypes;
import de.maxhenkel.easyvillagers.blocks.tileentity.FakeWorldTileentity;
import dev.engine_room.flywheel.api.instance.Instance;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.model.Models;
import dev.engine_room.flywheel.lib.visual.AbstractBlockEntityVisual;
import dev.engine_room.flywheel.lib.visual.SimpleTickableVisual;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.function.Consumer;

/**
 * Flywheel visual instance for Easy Villagers blocks.
 * Implements kinetic rotation of AllPartialModels.SHAFT_HALF on the power input side,
 * synchronized perfectly with Create's rotation offset grid.
 */
public class EasyVillagerVisual<T extends FakeWorldTileentity> extends AbstractBlockEntityVisual<T> implements SimpleTickableVisual {

    protected RotatingInstance rotatingShaft;
    protected Direction powerSide;

    public EasyVillagerVisual(VisualizationContext context, T blockEntity, float partialTick) {
        super(context, blockEntity, partialTick);

        if (blockState.hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
            powerSide = blockState.getValue(BlockStateProperties.HORIZONTAL_FACING).getOpposite();
        }

        if (powerSide != null) {
            rotatingShaft = instancerProvider().instancer(
                    AllInstanceTypes.ROTATING,
                    Models.partial(AllPartialModels.SHAFT_HALF)
            ).createInstance()
            .rotateToFace(Direction.SOUTH, powerSide)
            .setPosition(getVisualPosition());

            updateSpeed();
        }
    }

    private void updateSpeed() {
        if (powerSide == null || rotatingShaft == null) return;

        BlockEntity neighbor = level.getBlockEntity(pos.relative(powerSide));
        if (neighbor instanceof KineticBlockEntity kbe && !kbe.isOverStressed()) {
            float speed = kbe.getSpeed();
            if (Math.abs(speed) >= 32) {
                float offset = KineticBlockEntityVisual.rotationOffset(kbe.getBlockState(), powerSide.getAxis(), pos) + kbe.getRotationAngleOffset(powerSide.getAxis());
                rotatingShaft.setRotationAxis(powerSide.getAxis());
                rotatingShaft.setRotationalSpeed(speed * RotatingInstance.SPEED_MULTIPLIER);
                rotatingShaft.setRotationOffset(offset);
                rotatingShaft.setChanged();
                return;
            }
        }
        rotatingShaft.setRotationalSpeed(0);
        rotatingShaft.setRotationOffset(0);
        rotatingShaft.setChanged();
    }

    @Override
    public void tick(dev.engine_room.flywheel.api.visual.TickableVisual.Context ctx) {
        updateSpeed();
    }

    @Override
    public void updateLight(float pt) {
        if (rotatingShaft != null) relight(rotatingShaft);
    }

    @Override
    protected void _delete() {
        if (rotatingShaft != null) rotatingShaft.delete();
    }

    @Override
    public void collectCrumblingInstances(Consumer<Instance> consumer) {
        if (rotatingShaft != null) consumer.accept(rotatingShaft);
    }
}
