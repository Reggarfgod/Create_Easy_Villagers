package com.reggarf.mods.create_easy_villagers.mixin.contant;

import com.reggarf.mods.create_easy_villagers.config.CreateEasyVillagersConfig;
import com.reggarf.mods.create_easy_villagers.util.EasyVillagerKineticHelper;
import com.simibubi.create.api.equipment.goggles.IHaveGoggleInformation;
import de.maxhenkel.easyvillagers.blocks.VillagerBlockBase;
import de.maxhenkel.easyvillagers.blocks.tileentity.BreederTileentity;
import de.maxhenkel.easyvillagers.blocks.tileentity.VillagerTileentity;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(value = BreederTileentity.class, remap = false)
public abstract class BreederMixin implements IHaveGoggleInformation {

    @Unique
    private long cev$timer = 0;

    @Inject(method = "tickServer", at = @At("HEAD"), cancellable = true)
    private void tickBreeder(CallbackInfo ci) {
        ci.cancel();

        BlockEntity be = (BlockEntity) (Object) this;
        Level level = be.getLevel();
        if (level == null || level.isClientSide()) {
            return;
        }

        float speed = EasyVillagerKineticHelper.getKineticSpeed(be);
        int multiplier = EasyVillagerKineticHelper.getSpeedMultiplier(speed);

        if (multiplier <= 0) {
            return;
        }

        BreederTileentity breeder = (BreederTileentity) (Object) this;

        boolean v1 = false;
        boolean v2 = false;
        for (int i = 0; i < multiplier; i++) {
            if (VillagerTileentity.advanceAge(breeder.getVillagerEntity1())) {
                v1 = true;
            }
            if (VillagerTileentity.advanceAge(breeder.getVillagerEntity2())) {
                v2 = true;
            }
        }
        if (v1 || v2) {
            breeder.sync();
        }

        if (breeder.hasVillager1() || breeder.hasVillager2()) {
            breeder.setChanged();
            VillagerBlockBase.playRandomVillagerSound(level, be.getBlockPos(), SoundEvents.VILLAGER_AMBIENT);
        }

        if (breeder.canBreed()) {
            this.cev$timer += multiplier;
            int breedingTime = CreateEasyVillagersConfig.getBreederBreedingTime();
            if (breedingTime <= 0) {
                breedingTime = 20;
            }
            if (this.cev$timer >= breedingTime) {
                this.cev$timer = 0;
                breeder.tryBreed();
            }
        }
    }

    @Override
    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        BlockEntity be = (BlockEntity) (Object) this;
        return EasyVillagerKineticHelper.addGoggleTooltip(be, tooltip, "Breeder", "Breeding Speed", CreateEasyVillagersConfig.getBreederStress());
    }
}
