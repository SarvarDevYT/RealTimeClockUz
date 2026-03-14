package uz.iskan.realtimeclock.integration;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import uz.iskan.realtimeclock.config.ConfigManager;
import uz.iskan.realtimeclock.config.ModConfig;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConfigScreen {
    public static Screen create(Screen parent) {
        ModConfig config = ConfigManager.getConfig();
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.translatable("title.realtimeclock.config"))
                .setSavingRunnable(ConfigManager::save);

        ConfigCategory general = builder.getOrCreateCategory(Text.translatable("category.realtimeclock.general"));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        List<String> zoneIds = new ArrayList<>(ZoneId.getAvailableZoneIds());
        Collections.sort(zoneIds);

        general.addEntry(entryBuilder.startStringDropdownMenu(Text.translatable("option.realtimeclock.timezone"), config.timezone)
                .setDefaultValue("Asia/Tashkent")
                .setSelections(zoneIds)
                .setSaveConsumer(newValue -> config.timezone = newValue)
                .build());

        general.addEntry(entryBuilder.startBooleanToggle(Text.translatable("option.realtimeclock.showSeconds"), config.showSeconds)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> config.showSeconds = newValue)
                .build());

        general.addEntry(entryBuilder.startEnumSelector(Text.translatable("option.realtimeclock.corner"), ModConfig.Corner.class, config.corner)
                .setDefaultValue(ModConfig.Corner.BOTTOM_RIGHT)
                .setSaveConsumer(newValue -> config.corner = newValue)
                .build());

        general.addEntry(entryBuilder.startIntField(Text.translatable("option.realtimeclock.offsetX"), config.offsetX)
                .setDefaultValue(6)
                .setSaveConsumer(newValue -> config.offsetX = newValue)
                .build());

        general.addEntry(entryBuilder.startIntField(Text.translatable("option.realtimeclock.offsetY"), config.offsetY)
                .setDefaultValue(6)
                .setSaveConsumer(newValue -> config.offsetY = newValue)
                .build());

        return builder.build();
    }
}
