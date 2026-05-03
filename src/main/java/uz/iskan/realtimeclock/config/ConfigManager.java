package uz.iskan.realtimeclock.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import uz.iskan.realtimeclock.RealTimeClockMod;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigManager {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH =
            FabricLoader.getInstance().getConfigDir().resolve("realtimeclock.json");

    private static ModConfig config = new ModConfig();

    public static void load() {
        if (Files.exists(CONFIG_PATH)) {
            try {
                String json = Files.readString(CONFIG_PATH, StandardCharsets.UTF_8);
                ModConfig loaded = GSON.fromJson(json, ModConfig.class);
                config = (loaded != null) ? loaded : new ModConfig();
            } catch (IOException e) {
                RealTimeClockMod.LOGGER.error("[RealTimeClockUz] Konfigni yuklashda xato: {}", e.getMessage());
                config = new ModConfig();
            }
        } else {
            config = new ModConfig();
            save();
        }
        config.validate();
    }

    public static void save() {
        try {
            Files.writeString(CONFIG_PATH, GSON.toJson(config), StandardCharsets.UTF_8);
        } catch (IOException e) {
            RealTimeClockMod.LOGGER.error("[RealTimeClockUz] Konfigni saqlashda xato: {}", e.getMessage());
        }
    }

    public static ModConfig getConfig() {
        return config;
    }
}
