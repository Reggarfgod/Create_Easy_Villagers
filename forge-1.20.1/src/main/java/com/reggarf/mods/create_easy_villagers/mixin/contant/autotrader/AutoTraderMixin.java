package com.reggarf.mods.create_easy_villagers.mixin.contant.autotrader;

import com.reggarf.mods.create_easy_villagers.config.CreateEasyVillagersConfig;
import com.reggarf.mods.create_easy_villagers.util.EasyVillagerKineticHelper;
import com.simibubi.create.api.equipment.goggles.IHaveGoggleInformation;
import de.maxhenkel.easyvillagers.blocks.tileentity.AutoTraderTileentity;
import de.maxhenkel.easyvillagers.corelib.blockentity.ITickableBlockEntity;
import de.maxhenkel.easyvillagers.entity.EasyVillagerEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(value = AutoTraderTileentity.class, remap = false)
public abstract class AutoTraderMixin implements IHaveGoggleInformation {

    private static final ThreadLocal<Boolean> IS_EXTRA_TICK = ThreadLocal.withInitial(() -> false);

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void tickAutoTrader(CallbackInfo ci) {
        if (IS_EXTRA_TICK.get()) return;

        BlockEntity be = (BlockEntity) (Object) this;
        Level level = be.getLevel();
        if (level == null) {
            return;
        }

        float speed = EasyVillagerKineticHelper.getKineticSpeed(be);
        int multiplier = EasyVillagerKineticHelper.getSpeedMultiplier(speed);

        if (multiplier <= 0) {
            ci.cancel();
            return;
        }

        int extraTicks = multiplier - 1;
        if (extraTicks > 0 && be instanceof ITickableBlockEntity tickable) {
            IS_EXTRA_TICK.set(true);
            try {
                for (int i = 0; i < extraTicks; i++) {
                    tickable.tick();
                }
            } finally {
                IS_EXTRA_TICK.set(false);
            }
        }
    }

    @Inject(method = "onAddVillager", at = @At("HEAD"))
    private void onAddVillagerFix(EasyVillagerEntity villager, CallbackInfo ci) {
        AutoTraderTileentity trader = (AutoTraderTileentity) (Object) this;
        trader.setTradeIndex(0);
    }

    @Inject(method = "setWorkstation", at = @At("HEAD"))
    private void onSetWorkstationFix(Block block, CallbackInfo ci) {
        AutoTraderTileentity trader = (AutoTraderTileentity) (Object) this;
        trader.setTradeIndex(0);
    }

    @Inject(method = "updateTradeInv", at = @At("HEAD"))
    private void onUpdateTradeInvFix(CallbackInfo ci) {
        BlockEntity be = (BlockEntity) (Object) this;
        Level level = be.getLevel();
        if (level == null || level.isClientSide) {
            return;
        }
        AutoTraderTileentity trader = (AutoTraderTileentity) (Object) this;
        EasyVillagerEntity villager = trader.getVillagerEntity();
        if (villager != null && trader.hasWorkstation()) {
            if (villager.getVillagerXp() <= 0 && villager.getVillagerData().getLevel() <= 1) {
                if (villager.getOffers() == null || villager.getOffers().isEmpty()) {
                    villager.setOffers(null);
                }
            }
        }
    }

    @Override
    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        BlockEntity be = (BlockEntity) (Object) this;
        return EasyVillagerKineticHelper.addGoggleTooltip(be, tooltip, "Auto Trader", "Trading Speed", CreateEasyVillagersConfig.getAutoTraderStress());
    }
}
