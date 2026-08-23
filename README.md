# Create Easy Villagers

[![NeoForge](https://img.shields.io/badge/NeoForge-1.21.1-orange.svg)](https://neoforged.net/)
[![Create](https://img.shields.io/badge/Create-6.0.10+-blue.svg)](https://curseforge.com/minecraft/mc-mods/create)
[![Easy Villagers](https://img.shields.io/badge/Easy%20Villagers-1.21.1-green.svg)](https://curseforge.com/minecraft/mc-mods/easy-villagers)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

**Create Easy Villagers** is a NeoForge add-on mod bridging [Create](https://curseforge.com/minecraft/mc-mods/create) and [Easy Villagers](https://curseforge.com/minecraft/mc-mods/easy-villagers). It balances all automated Easy Villagers machines by requiring rotational kinetic stress units (SU) from Create networks, complete with authentic spinning shaft visuals, Flywheel GPU instancing, and Engineer's Goggles diagnostics.

---

## ⚙️ Features

* **Kinetic Power Requirement:** Easy Villagers machines require rotational force supplied to the power port at the rear of the block.
* **Speed Thresholds & Scaling:**
  * **Minimum Speed:** Machines require at least **`32 RPM`** to function.
  * **Dynamic Speed Multipliers:** Faster rotation speeds directly accelerate production intervals (e.g. $1\times$ at 32 RPM, $2\times$ at 64 RPM, $4\times$ at 128 RPM, $8\times$ at 256 RPM).
* **Create Flywheel GPU Instancing:** Realistic `SHAFT_HALF` models render on the input side with full Flywheel shader support and 22.5° grid angle alignment.
* **Stress Unit (SU) Network Impact:** Machines put realistic strain on your kinetic network and will stop if the network is overstressed.
* **Engineer's Goggles Support:** Look at any machine with Engineer's Goggles to inspect real-time RPM, SU consumption, and speed multipliers.

---

## 🏭 Supported Machines & Behaviors

| Machine | Base Stress Impact | Speed Scaling Behavior | Goggles Diagnostic |
| :--- | :--- | :--- | :--- |
| **Auto Trader** | `4.0 SU / RPM` | Accelerates trading cooldown between automated trades | `Trading Speed` |
| **Iron Farm** | `8.0 SU / RPM` | Accelerates Iron Golem spawn and processing cycles | `Iron Output Rate` |
| **Farmer** | `4.0 SU / RPM` | Drives crop growth rate and automatic harvesting *(Requires Farmer Villager)* | `Harvest Rate` |
| **Breeder** | `6.0 SU / RPM` | Decreases breeding interval and accelerates child generation | `Breeding Rate` |
| **Converter** | `6.0 SU / RPM` | Speeds up the zombie infection and golden apple curing process | `Curing Speed` |
| **Incubator** | `4.0 SU / RPM` | Speeds up baby villager maturation into adulthood | `Growth Rate` |

---

## 📦 Required Dependencies & Mods Used

To run **Create Easy Villagers**, the following mods are required:

1. **[NeoForge](https://neoforged.net/)** (Version `21.1.248` or compatible for Minecraft `1.21.1`)
2. **[Create](https://curseforge.com/minecraft/mc-mods/create)** (Version `6.0.10+`)
3. **[Easy Villagers](https://curseforge.com/minecraft/mc-mods/easy-villagers)** by Henkelmax
4. **[Flywheel](https://curseforge.com/minecraft/mc-mods/flywheel)** (Included with Create)
5. **[Ponder](https://curseforge.com/minecraft/mc-mods/ponder)** (Included with Create)

---

## 🔗 Links & Resources

* **GitHub Repository:** [https://github.com/Reggarfgod/Create_Easy_Villagers](https://github.com/Reggarfgod/Create_Easy_Villagers)
* **Issue Tracker:** [https://github.com/Reggarfgod/Create_Easy_Villagers/issues](https://github.com/Reggarfgod/Create_Easy_Villagers/issues)
* **Easy Villagers Mod:** [https://www.curseforge.com/minecraft/mc-mods/easy-villagers](https://www.curseforge.com/minecraft/mc-mods/easy-villagers)
* **Create Mod:** [https://www.curseforge.com/minecraft/mc-mods/create](https://www.curseforge.com/minecraft/mc-mods/create)

---

## 📄 License

This project is licensed under the [MIT License](LICENSE).
