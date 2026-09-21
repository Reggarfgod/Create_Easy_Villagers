package com.reggarf.mods.create_easy_villagers.util;

import com.reggarf.mods.create_easy_villagers.config.CreateEasyVillagersConfig;
import com.simibubi.create.content.kinetics.base.IRotate;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.foundation.utility.CreateLang;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.List;

public class EasyVillagerKineticHelper {

    public static Direction getPowerSide(BlockEntity be) {
        if (be != null && be.getBlockState().hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
            return be.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING).getOpposite();
        }
        return null;
    }

    public static KineticBlockEntity getConnectedKinetic(BlockEntity be) {
        Level level = be.getLevel();
        if (level == null) return null;

        Direction powerSide = getPowerSide(be);
        if (powerSide == null) return null;

        BlockPos neighborPos = be.getBlockPos().relative(powerSide);
        BlockEntity neighbor = level.getBlockEntity(neighborPos);
        if (neighbor instanceof KineticBlockEntity kbe) {
            BlockState neighborState = neighbor.getBlockState();
            Direction faceTowardsMachine = powerSide.getOpposite();
            if (neighborState.getBlock() instanceof IRotate rotate
                    && rotate.hasShaftTowards(level, neighborPos, neighborState, faceTowardsMachine)
                    && rotate.getRotationAxis(neighborState) == powerSide.getAxis()) {
                return kbe;
            }
        }
        return null;
    }

    public static float getKineticSpeed(BlockEntity be) {
        KineticBlockEntity kbe = getConnectedKinetic(be);
        if (kbe != null && !kbe.isOverStressed()) {
            return Math.abs(kbe.getSpeed());
        }
        return 0f;
    }

    public static int getSpeedMultiplier(float speed) {
        float absSpeed = Math.abs(speed);
        float minSpeed = CreateEasyVillagersConfig.getMinimumSpeed();
        if (absSpeed < minSpeed) return 0;

        float rpmPerMultiplier = CreateEasyVillagersConfig.getRpmPerMultiplier();
        int multiplier = (int) (absSpeed / rpmPerMultiplier);
        int maxMultiplier = CreateEasyVillagersConfig.getMaxSpeedMultiplier();
        return Math.min(multiplier, maxMultiplier);
    }

    public static boolean addGoggleTooltip(BlockEntity be, List<Component> tooltip, String machineName, String rateLabel, float baseImpact) {
        Level level = be.getLevel();
        if (level == null) return false;

        float speed = getKineticSpeed(be);
        float stressImpact = baseImpact * speed;
        int multiplier = getSpeedMultiplier(speed);

        CreateLang.builder()
                .add(CreateLang.text("Create Easy Villagers - " + machineName)
                        .style(ChatFormatting.GOLD))
                .forGoggles(tooltip);

        CreateLang.translate("gui.goggles.kinetic_stats")
                .forGoggles(tooltip);

        // Running speed
        CreateLang.translate("tooltip.speedRequirement")
                .style(ChatFormatting.GRAY)
                .forGoggles(tooltip);

        CreateLang.builder()
                .add(CreateLang.number(speed).text(" ").translate("generic.unit.rpm").style(ChatFormatting.AQUA))
                .forGoggles(tooltip, 1);

        // Stress impact
        CreateLang.translate("tooltip.stressImpact")
                .style(ChatFormatting.GRAY)
                .forGoggles(tooltip);

        CreateLang.builder()
                .add(CreateLang.number(stressImpact).text(" ").translate("generic.unit.stress").style(ChatFormatting.AQUA))
                .forGoggles(tooltip, 1);

        // Rate Multiplier
        CreateLang.text(rateLabel)
                .style(ChatFormatting.GRAY)
                .forGoggles(tooltip);

        CreateLang.builder()
                .add(CreateLang.text(multiplier + "x").style(multiplier > 0 ? ChatFormatting.GREEN : ChatFormatting.RED))
                .forGoggles(tooltip, 1);

        return true;
    }
}
