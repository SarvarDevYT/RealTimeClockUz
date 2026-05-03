package uz.iskan.realtimeclock.integration;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import uz.iskan.realtimeclock.config.ClockEntry;
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

        ConfigEntryBuilder eb = builder.entryBuilder();

        // ── Asosiy sozlamalar ──────────────────────────────────────────────
        ConfigCategory general = builder.getOrCreateCategory(
                Text.translatable("category.realtimeclock.general"));

        // HUD yoq/o'chir
        general.addEntry(eb.startBooleanToggle(
                        Text.translatable("option.realtimeclock.enabled"), config.enabled)
                .setDefaultValue(true)
                .setTooltip(Text.translatable("option.realtimeclock.enabled.tooltip"))
                .setSaveConsumer(v -> config.enabled = v)
                .build());

        // Sekundlarni ko'rsatish
        general.addEntry(eb.startBooleanToggle(
                        Text.translatable("option.realtimeclock.showSeconds"), config.showSeconds)
                .setDefaultValue(true)
                .setSaveConsumer(v -> config.showSeconds = v)
                .build());

        // 24 soatlik format
        general.addEntry(eb.startBooleanToggle(
                        Text.translatable("option.realtimeclock.use24h"), config.use24h)
                .setDefaultValue(true)
                .setSaveConsumer(v -> config.use24h = v)
                .build());

        // Miltillovchi ikki nuqta
        general.addEntry(eb.startBooleanToggle(
                        Text.translatable("option.realtimeclock.blinkColon"), config.blinkColon)
                .setDefaultValue(false)
                .setSaveConsumer(v -> config.blinkColon = v)
                .build());

        // Fon (shaffof to'rtburchak)
        general.addEntry(eb.startBooleanToggle(
                        Text.translatable("option.realtimeclock.showBackground"), config.showBackground)
                .setDefaultValue(true)
                .setSaveConsumer(v -> config.showBackground = v)
                .build());

        // ── HUD holati ─────────────────────────────────────────────────────
        ConfigCategory hud = builder.getOrCreateCategory(
                Text.translatable("category.realtimeclock.hud"));

        hud.addEntry(eb.startEnumSelector(
                        Text.translatable("option.realtimeclock.corner"),
                        ModConfig.Corner.class, config.corner)
                .setDefaultValue(ModConfig.Corner.BOTTOM_RIGHT)
                .setEnumNameProvider(corner -> Text.translatable(
                        "option.realtimeclock.corner." + corner.name().toLowerCase()))
                .setSaveConsumer(v -> config.corner = v)
                .build());

        hud.addEntry(eb.startIntField(
                        Text.translatable("option.realtimeclock.offsetX"), config.offsetX)
                .setDefaultValue(6)
                .setMin(0).setMax(500)
                .setSaveConsumer(v -> config.offsetX = v)
                .build());

        hud.addEntry(eb.startIntField(
                        Text.translatable("option.realtimeclock.offsetY"), config.offsetY)
                .setDefaultValue(6)
                .setMin(0).setMax(500)
                .setSaveConsumer(v -> config.offsetY = v)
                .build());

        // ── Vaqt zonalari ─────────────────────────────────────────────────
        ConfigCategory zones = builder.getOrCreateCategory(
                Text.translatable("category.realtimeclock.zones"));

        List<String> allZones = new ArrayList<>(ZoneId.getAvailableZoneIds());
        Collections.sort(allZones);

        // Har bir soat yozuvi uchun alohida qator
        List<ClockEntry> clocks = config.clocks;
        for (int i = 0; i < clocks.size(); i++) {
            final int idx = i;
            ClockEntry entry = clocks.get(idx);

            // Label
            zones.addEntry(eb.startStrField(
                            Text.translatable("option.realtimeclock.clock.label", i + 1), entry.label)
                    .setDefaultValue("Clock " + (i + 1))
                    .setSaveConsumer(v -> clocks.get(idx).label = v)
                    .build());

            // ZoneId dropdown
            zones.addEntry(eb.startStringDropdownMenu(
                            Text.translatable("option.realtimeclock.clock.zone", i + 1), entry.zoneId)
                    .setDefaultValue("Asia/Tashkent")
                    .setSelections(allZones)
                    .setSaveConsumer(v -> clocks.get(idx).zoneId = v)
                    .build());
        }

        return builder.build();
    }
}
