package com.reggarf.mods.create_easy_villagers.config;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class CreateEasyVillagersConfig {

    public static class Common {
        public final ModConfigSpec.DoubleValue minimumSpeed;
        public final ModConfigSpec.DoubleValue rpmPerMultiplier;
        public final ModConfigSpec.IntValue maxSpeedMultiplier;

        public final ModConfigSpec.DoubleValue globalStressMultiplier;

        public final ModConfigSpec.DoubleValue autoTraderStressImpact;
        public final ModConfigSpec.DoubleValue farmerStressImpact;
        public final ModConfigSpec.DoubleValue breederStressImpact;
        public final ModConfigSpec.DoubleValue converterStressImpact;
        public final ModConfigSpec.DoubleValue incubatorStressImpact;
        public final ModConfigSpec.DoubleValue ironFarmStressImpact;

        public Common(ModConfigSpec.Builder builder) {
            builder.comment("Kinetic Speed and Multiplier Settings")
                    .push("kinetics");

            minimumSpeed = builder
                    .comment("Minimum RPM required for Easy Villagers machines to operate.")
                    .defineInRange("minimumSpeed", 32.0D, 0.0D, 256.0D);

            rpmPerMultiplier = builder
                    .comment("RPM required for each +1x processing speed multiplier (e.g. at 32 RPM/multiplier, 64 RPM = 2x, 128 RPM = 4x).")
                    .defineInRange("rpmPerMultiplier", 32.0D, 1.0D, 256.0D);

            maxSpeedMultiplier = builder
                    .comment("Maximum processing speed multiplier cap.")
                    .defineInRange("maxSpeedMultiplier", 16, 1, 64);

            builder.pop();

            builder.comment("Global Stress Settings")
                    .push("global_stress");

            globalStressMultiplier = builder
                    .comment("Global multiplier applied to all machine stress values (e.g. 1.0 = standard, 2.0 = double stress for all machines).")
                    .defineInRange("globalStressMultiplier", 1.0D, 0.0D, 100.0D);

            builder.pop();

            builder.comment("Per-Machine Stress Impact Settings (SU per RPM)")
                    .push("stress_impact");

            autoTraderStressImpact = builder
                    .comment("Custom stress impact (SU/RPM) for the Auto Trader.")
                    .defineInRange("autoTrader", 4.0D, 0.0D, 2048.0D);

            farmerStressImpact = builder
                    .comment("Custom stress impact (SU/RPM) for the Farmer.")
                    .defineInRange("farmer", 4.0D, 0.0D, 2048.0D);

            breederStressImpact = builder
                    .comment("Custom stress impact (SU/RPM) for the Breeder.")
                    .defineInRange("breeder", 6.0D, 0.0D, 2048.0D);

            converterStressImpact = builder
                    .comment("Custom stress impact (SU/RPM) for the Converter.")
                    .defineInRange("converter", 6.0D, 0.0D, 2048.0D);

            incubatorStressImpact = builder
                    .comment("Custom stress impact (SU/RPM) for the Incubator.")
                    .defineInRange("incubator", 4.0D, 0.0D, 2048.0D);

            ironFarmStressImpact = builder
                    .comment("Custom stress impact (SU/RPM) for the Iron Farm.")
                    .defineInRange("ironFarm", 14.0D, 0.0D, 2048.0D);

            builder.pop();
        }
    }

    public static final ModConfigSpec SPEC;
    public static final Common CONFIG;

    static {
        Pair<Common, ModConfigSpec> pair = new ModConfigSpec.Builder().configure(Common::new);
        CONFIG = pair.getLeft();
        SPEC = pair.getRight();
    }

    public static float getMinimumSpeed() {
        return CONFIG != null && CONFIG.minimumSpeed != null ? CONFIG.minimumSpeed.get().floatValue() : 32.0f;
    }

    public static float getRpmPerMultiplier() {
        return CONFIG != null && CONFIG.rpmPerMultiplier != null ? CONFIG.rpmPerMultiplier.get().floatValue() : 32.0f;
    }

    public static int getMaxSpeedMultiplier() {
        return CONFIG != null && CONFIG.maxSpeedMultiplier != null ? CONFIG.maxSpeedMultiplier.get() : 16;
    }

    public static float getGlobalStressMultiplier() {
        return CONFIG != null && CONFIG.globalStressMultiplier != null ? CONFIG.globalStressMultiplier.get().floatValue() : 1.0f;
    }

    public static float getAutoTraderStress() {
        float base = CONFIG != null && CONFIG.autoTraderStressImpact != null ? CONFIG.autoTraderStressImpact.get().floatValue() : 4.0f;
        return base * getGlobalStressMultiplier();
    }

    public static float getFarmerStress() {
        float base = CONFIG != null && CONFIG.farmerStressImpact != null ? CONFIG.farmerStressImpact.get().floatValue() : 4.0f;
        return base * getGlobalStressMultiplier();
    }

    public static float getBreederStress() {
        float base = CONFIG != null && CONFIG.breederStressImpact != null ? CONFIG.breederStressImpact.get().floatValue() : 6.0f;
        return base * getGlobalStressMultiplier();
    }

    public static float getConverterStress() {
        float base = CONFIG != null && CONFIG.converterStressImpact != null ? CONFIG.converterStressImpact.get().floatValue() : 6.0f;
        return base * getGlobalStressMultiplier();
    }

    public static float getIncubatorStress() {
        float base = CONFIG != null && CONFIG.incubatorStressImpact != null ? CONFIG.incubatorStressImpact.get().floatValue() : 4.0f;
        return base * getGlobalStressMultiplier();
    }

    public static float getIronFarmStress() {
        float base = CONFIG != null && CONFIG.ironFarmStressImpact != null ? CONFIG.ironFarmStressImpact.get().floatValue() : 14.0f;
        return base * getGlobalStressMultiplier();
    }
}
