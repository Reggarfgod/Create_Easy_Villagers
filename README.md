# Create Easy Villagers

[![NeoForge](https://img.shields.io/badge/NeoForge-1.21.1-orange.svg)](https://neoforged.net/)
[![Create](https://img.shields.io/badge/Create-6.0.10+-blue.svg)](https://curseforge.com/minecraft/mc-mods/create)
[![Easy Villagers](https://img.shields.io/badge/Easy%20Villagers-1.21.1-green.svg)](https://curseforge.com/minecraft/mc-mods/easy-villagers)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

**Create Easy Villagers** is a NeoForge add-on mod bridging [Create](https://curseforge.com/minecraft/mc-mods/create) and [Easy Villagers](https://curseforge.com/minecraft/mc-mods/easy-villagers). It balances automated Easy Villagers machines by requiring rotational kinetic stress units (SU) from Create networks, complete with authentic spinning shaft visuals, Flywheel GPU instancing, in-game Catnip configuration, and Engineer's Goggles diagnostics.

---

## ⚙️ Features

* **Kinetic Power Requirement:** Easy Villagers machines require rotational force supplied to the power port at the rear of the block.
* **Speed Thresholds & Scaling:**
  * **Minimum Speed:** Machines require at least **`32 RPM`** to operate (configurable). Below 32 RPM, operations are paused.
  * **Dynamic Speed Multipliers:** Faster rotation speeds directly accelerate production intervals (e.g. $1\times$ at 32 RPM, $2\times$ at 64 RPM, $4\times$ at 128 RPM, $8\times$ at 256 RPM).
* **Create Flywheel GPU Instancing:** Realistic `SHAFT_QUARTER` models render on the input side with full Flywheel shader support and 22.5° grid angle alignment.
* **Independent Stress (SU) Controls:** Customize stress (SU) consumption per machine or globally without modifying production speeds.
* **Engineer's Goggles Support:** Look at any machine with Engineer's Goggles to inspect real-time RPM, SU consumption, and speed multipliers.
* **Full In-Game Configuration:** Configurable via `config/create_easy_villagers-common.toml` or the in-game Catnip / Create Config UI.

---

## 📊 Speed Scaling & Multiplier Chart

The production rate is calculated dynamically from the shaft speed:

$$\text{Speed Multiplier} = \min\left(\left\lfloor \frac{\text{RPM}}{\text{rpmPerMultiplier}} \right\rfloor, \text{maxMultiplier}\right)$$

*(By default: $\text{rpmPerMultiplier} = 32$, $\text{maxMultiplier} = 16$)*

| Speed (RPM) | Multiplier | Operation Speed | Efficiency Boost |
| :--- : | :---: | :---: | :---: |
| **< 32 RPM** | **0×** | *Halted / Idle* | *No production* |
| **32 RPM** | **1×** | Standard Baseline | Baseline (100%) |
| **64 RPM** | **2×** | **2× faster** | 200% Output |
| **128 RPM** | **4×** | **4× faster** | 400% Output |
| **256 RPM** *(Max Create)* | **8×** | **8× faster** | **800% Output** |

---

## 🏭 Machine Breakdown & SU Consumption

Each machine consumes Stress Units proportional to its rotational speed:

$$\text{Stress Impact (SU)} = \text{Speed (RPM)} \times \text{Base Impact (SU/RPM)} \times \text{Global Multiplier}$$

---

### 1. 🪙 Auto Trader
* **Base Stress Impact:** `4.0 SU / RPM`
* **Default Stress at 256 RPM:** **`1,024 SU`**
* **Effect:** Accelerates automated villager trade cooldowns. At **256 RPM (8×)**, trades execute **8 times faster** as long as items and restocks are available.
* **Goggles Diagnostic:** `Trading Speed`

---

### 2. 🌾 Farmer
* **Base Stress Impact:** `4.0 SU / RPM`
* **Default Stress at 256 RPM:** **`1,024 SU`**
* **Effect:** Drives crop growth ticks and auto-harvest cycles. At **256 RPM (8×)**, crops grow and yield harvest **8 times faster**.
* **Goggles Diagnostic:** `Farming Speed`

---

### 3. 👶 Breeder
* **Base Stress Impact:** `6.0 SU / RPM`
* **Default Stress at 256 RPM:** **`1,536 SU`**
* **Effect:** Decreases the breeding cooldown timer and accelerates baby villager generation. At **256 RPM (8×)**, baby villagers are produced **8 times faster** (provided food is supplied).
* **Goggles Diagnostic:** `Breeding Speed`

---

### 4. 🧪 Converter
* **Base Stress Impact:** `6.0 SU / RPM`
* **Default Stress at 256 RPM:** **`1,536 SU`**
* **Effect:** Drastically shortens both the zombie villager infection time and the golden apple curing countdown. A process normally taking 3–5 minutes finishes in **~20–35 seconds (8× faster)** at 256 RPM.
* **Goggles Diagnostic:** `Curing Speed`

---

### 5. 🍼 Incubator
* **Base Stress Impact:** `4.0 SU / RPM`
* **Default Stress at 256 RPM:** **`1,024 SU`**
* **Effect:** Accelerates the baby villager maturation timer into an adult. At **256 RPM (8×)**, baby villagers grow into working adults **8 times faster**.
* **Goggles Diagnostic:** `Aging Speed`

---

### 6. ⚔️ Iron Farm
* **Base Stress Impact:** `14.0 SU / RPM`
* **Default Stress at 256 RPM:** **`3,584 SU`**
* **Effect:** Accelerates Iron Golem scare, spawn, and processing intervals. A cycle normally taking ~5 minutes (300 seconds) completes every **~37.5 seconds (8× faster)** at 256 RPM, generating **8 times more iron**.
* **Goggles Diagnostic:** `Iron Output Rate`

---

## ⚙️ Configuration Options

Configuration file is located at `config/create_easy_villagers-common.toml`:

```toml
[kinetics]
    # Minimum RPM required for Easy Villagers machines to operate.
    # Range: 0.0 ~ 256.0 (Default: 32.0)
    minimumSpeed = 32.0

    # RPM required for each +1x processing speed multiplier.
    # Range: 1.0 ~ 256.0 (Default: 32.0)
    rpmPerMultiplier = 32.0

    # Maximum processing speed multiplier cap.
    # Range: 1 ~ 64 (Default: 16)
    maxSpeedMultiplier = 16

[stress_impact]
    # Global multiplier applied to all machine stress values (Default: 1.0)
    globalStressMultiplier = 1.0

    # Custom stress impact (SU/RPM) for the Auto Trader (Default: 4.0)
    autoTrader = 4.0

    # Custom stress impact (SU/RPM) for the Farmer (Default: 4.0)
    farmer = 4.0

    # Custom stress impact (SU/RPM) for the Breeder (Default: 6.0)
    breeder = 6.0

    # Custom stress impact (SU/RPM) for the Converter (Default: 6.0)
    converter = 6.0

    # Custom stress impact (SU/RPM) for the Incubator (Default: 4.0)
    incubator = 4.0

    # Custom stress impact (SU/RPM) for the Iron Farm (Default: 14.0)
    ironFarm = 14.0
```

---

## 📦 Required Dependencies & Compatibility

1. **[NeoForge](https://neoforged.net/)** (`21.1.248+` for Minecraft `1.21.1`)
2. **[Create](https://curseforge.com/minecraft/mc-mods/create)** (`6.0.10+`)
3. **[Easy Villagers](https://curseforge.com/minecraft/mc-mods/easy-villagers)** (`1.21.1+`)
4. **[Flywheel](https://curseforge.com/minecraft/mc-mods/flywheel)** (bundled with Create)
5. **[Ponder](https://curseforge.com/minecraft/mc-mods/ponder)** (bundled with Create)

---

## 🔗 Links & Resources

* **GitHub Repository:** [https://github.com/Reggarfgod/Create_Easy_Villagers](https://github.com/Reggarfgod/Create_Easy_Villagers)
* **Issue Tracker:** [https://github.com/Reggarfgod/Create_Easy_Villagers/issues](https://github.com/Reggarfgod/Create_Easy_Villagers/issues)
* **Easy Villagers Mod:** [https://www.curseforge.com/minecraft/mc-mods/easy-villagers](https://www.curseforge.com/minecraft/mc-mods/easy-villagers)
* **Create Mod:** [https://www.curseforge.com/minecraft/mc-mods/create](https://www.curseforge.com/minecraft/mc-mods/create)

---

## 📄 License

This project is licensed under the [MIT License](LICENSE).
