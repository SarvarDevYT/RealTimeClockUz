# 🕒 RealTimeClockUz

**RealTimeClockUz** is a lightweight **Fabric mod** for Minecraft that displays the **real-world system time directly on the HUD**. It is simple, configurable, and supports multiple languages.

![version](https://img.shields.io/badge/version-1.0.0-brightgreen.svg) ![license](https://img.shields.io/badge/license-GPL3.0-blue.svg)

## ⚙ Minimum Requirements

- Minecraft **1.21**
- **Fabric Loader**
- **Fabric API**
- **ModMenu**
- **Cloth Config**

## ✨ Features

- ✅ Shows real-world system time on the HUD  
- ✅ Default timezone: **Asia/Tashkent**  
- ✅ Timezone can be changed in settings  
- ✅ **ModMenu** configuration screen support  
- ✅ Configurable HUD position  
- ✅ X / Y offset settings  
- ✅ Optional seconds display  
- ✅ Supports multiple languages:
  - English
  - Russian
  - Uzbek

## 🛠 Installation

1. Install **Fabric Loader** for Minecraft 1.21  
2. Install **Fabric API**  
3. Install **ModMenu**  
4. Install **Cloth Config**  
5. Put `RealTimeClockUz.jar` into your `mods/` folder  
6. Start the game

## 🔧 Configuration

The mod creates its config file automatically:

```json
config/realtimeclock.json
```

You can configure:

- Timezone  
- Show / hide seconds  
- HUD corner position  
- X / Y offset  

You can also change settings through **ModMenu** in-game.

## 🌍 Localization

The mod supports:

- `en_us` – English
- `ru_ru` – Russian
- `uz_uz` – Uzbek

Language is selected automatically based on your Minecraft language settings.

## 📁 Project Structure

Main files usually include:

- `RealTimeClockMod.java`
- `RealTimeClockClient.java`
- `ModConfig.java`
- `ConfigManager.java`
- `ModMenuEntry.java`
- `ConfigScreen.java`

## ❓ Support

If you find bugs or have suggestions, open an issue on GitHub.

## 🧑‍💻 Author

Developer: **SarvarDev**

## 📜 License

This project is licensed under the **GNU General Public License v3.0 (GPL-3.0)**.
