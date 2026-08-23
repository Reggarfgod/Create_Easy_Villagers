package com.reggarf.mods.create_easy_villagers.mixin;

import com.reggarf.mods.create_easy_villagers.util.EasyVillagerKineticHelper;
import com.simibubi.create.api.equipment.goggles.IHaveGoggleInformation;
import de.maxhenkel.easyvillagers.blocks.tileentity.FarmerTileentity;
import de.maxhenkel.easyvillagers.corelib.blockentity.IServerTickableBlockEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(value = FarmerTileentity.class, remap = false)
public abstract class FarmerMixin implements IHaveGoggleInformation {

    private static final float BASE_STRESS_IMPACT = 4.0f;
    private static final ThreadLocal<Boolean> IS_EXTRA_TICK = ThreadLocal.withInitial(() -> false);

    @Inject(method = "tickServer", at = @At("HEAD"), cancellable = true)
    private void tickFarmer(CallbackInfo ci) {
        if (IS_EXTRA_TICK.get()) return;

        BlockEntity be = (BlockEntity) (Object) this;
        Level level = be.getLevel();
        if (level == null || level.isClientSide()) {
            return;
        }

        float speed = EasyVillagerKineticHelper.getKineticSpeed(be);
        int multiplier = EasyVillagerKineticHelper.getSpeedMultiplier(speed);

        if (multiplier <= 0) {
            ci.cancel();
            return;
        }

        int extraTicks = multiplier - 1;
        if (extraTicks > 0 && be instanceof IServerTickableBlockEntity serverTickable) {
            IS_EXTRA_TICK.set(true);
            try {
                for (int i = 0; i < extraTicks; i++) {
                    serverTickable.tickServer();
                }
            } finally {
                IS_EXTRA_TICK.set(false);
            }
        }
    }

    @Override
    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        BlockEntity be = (BlockEntity) (Object) this;
        return EasyVillagerKineticHelper.addGoggleTooltip(be, tooltip, "Farmer", "Harvest Rate", BASE_STRESS_IMPACT);
    }
}
