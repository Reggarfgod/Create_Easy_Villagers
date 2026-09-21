# Changelog

All notable changes to **Create Easy Villagers** will be documented in this file.

## [1.1.1] - 2026-09-21

### Fixed
* **Forge 1.20.1 Startup Crash**: Fixed mixin injection failure on `ConverterRenderer` and `IronFarmRenderer` in Forge 1.20.1 by enabling proper SRG refmap generation for `PoseStack.translate`.
* **Kinetic Connection Validation**: Machines now strictly validate whether an adjacent kinetic source is actually connected to the machine's rear power socket (`IRotate.hasShaftTowards` and matching rotation axis). Sideways or misaligned sources (e.g., Creative Motors facing away) will no longer power machines.
* **Stress Consumption Accuracy**: Fixed `KineticBlockEntity` stress mixin to only apply stress to kinetic sources that expose an aligned output shaft toward the machine.
* **Quarter-Shaft Visual Consistency**: Restored the rear `SHAFT_QUARTER` model so it remains visible on all machines in both Flywheel instancing and standard BER rendering—spinning smoothly when active and remaining stationary when unpowered or disconnected.
