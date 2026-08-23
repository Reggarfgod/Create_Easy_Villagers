package com.reggarf.mods.create_easy_villagers.mixin;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.api.stress.BlockStressValues;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import de.maxhenkel.easyvillagers.blocks.tileentity.FakeWorldTileentity;
import de.maxhenkel.easyvillagers.blocks.tileentity.IronFarmTileentity;
import de.maxhenkel.easyvillagers.blocks.tileentity.BreederTileentity;
import de.maxhenkel.easyvillagers.blocks.tileentity.ConverterTileentity;
import de.maxhenkel.easyvillagers.blocks.tileentity.AutoTraderTileentity;
import de.maxhenkel.easyvillagers.blocks.tileentity.FarmerTileentity;
import de.maxhenkel.easyvillagers.blocks.tileentity.IncubatorTileentity;

import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

@Mixin(value = KineticBlockEntity.class, remap = false)
public abstract class EasyVillagersKineticBlockEntityStressMixin {

    @Shadow protected abstract Block getStressConfigKey();
    @Shadow protected float lastStressApplied;

    @Inject(method = "calculateStressApplied", at = @At("HEAD"), cancellable = true)
    private void addEasyVillagerStress(CallbackInfoReturnable<Float> cir) {
        KineticBlockEntity self = (KineticBlockEntity) (Object) this;
        Level level = self.getLevel();
        if (level == null) return;

        float impact = (float) BlockStressValues.getImpact(getStressConfigKey());
        float addedStress = 0f;

        for (Direction direction : Direction.values()) {
            BlockEntity neighbor = level.getBlockEntity(self.getBlockPos().relative(direction));
            if (neighbor instanceof FakeWorldTileentity evTile) {
                
                Direction powerSide = null;
                if (evTile.getBlockState().hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
                    powerSide = evTile.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING).getOpposite();
                }

                // If this KineticBlockEntity is directly attached to the block's power socket
                if (powerSide != null && powerSide == direction.getOpposite()) {
                    if (neighbor instanceof IronFarmTileentity) {
                        addedStress += 8.0f;
                    } else if (neighbor instanceof BreederTileentity || neighbor instanceof ConverterTileentity) {
                        addedStress += 6.0f;
                    } else if (neighbor instanceof AutoTraderTileentity || neighbor instanceof FarmerTileentity || neighbor instanceof IncubatorTileentity) {
                        addedStress += 4.0f;
                    } else {
                        addedStress += 4.0f;
                    }
                }
            }
        }

        if (addedStress > 0) {
            impact += addedStress;
            this.lastStressApplied = impact;
            cir.setReturnValue(impact);
        }
    }
}
