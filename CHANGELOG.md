# Changelog

All notable changes to **Create Easy Villagers** will be documented in this file.

## [1.1.2] - 2026-09-21

### Fixed
* **Dedicated Server Crash**: Fixed a crash when launching on dedicated servers caused by client screen classes loading on the server.
* **Forge 1.20.1 Startup Crash**: Fixed a crash that occurred while launching the game on Minecraft 1.20.1 Forge.
* **Rotational Power Connection**: Fixed an issue where machines would run even if a power source (like a Creative Motor or Shaft) was placed sideways or pointing the wrong way. Power sources must now point directly into the back socket of the machine to power it.
* **Stress (SU) Calculation**: Nearby kinetic blocks that are facing the wrong way or not actually connected will no longer drain stress capacity from your network.
* **Machine Shaft Visuals**: The small drive shaft on the back of each machine is now always visible—spinning smoothly when power is active, and staying stationary when idle or disconnected.
