package uz.iskan.realtimeclock.hud;

import uz.iskan.realtimeclock.config.ClockEntry;
import uz.iskan.realtimeclock.config.ModConfig;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Vaqt satrlarini sekundiga bir marta yangilab turuvchi kesh.
 * HUD har tick chaqiriladi, lekin format hisob-kitobi faqat sekundiga bir marta bajariladi.
 */
public class TimeCache {

    private static final ZoneId FALLBACK = ZoneId.of("Asia/Tashkent");

    private long lastUpdateSecond = -1;
    private final List<CachedLine> lines = new ArrayList<>();

    public static class CachedLine {
        public final String label;
        public final String time;
        public final int color;

        CachedLine(String label, String time, int color) {
            this.label = label;
            this.time  = time;
            this.color = color;
        }
    }

    /**
     * Kerak bo'lsa yangilaydi va joriy qatorlar ro'yxatini qaytaradi.
     */
    public List<CachedLine> getLines(ModConfig config, boolean colonVisible) {
        long nowSecond = System.currentTimeMillis() / 1000L;
        if (nowSecond != lastUpdateSecond) {
            lastUpdateSecond = nowSecond;
            rebuild(config, colonVisible);
        }
        return lines;
    }

    private void rebuild(ModConfig config, boolean colonVisible) {
        lines.clear();

        String pattern;
        if (config.use24h) {
            pattern = config.showSeconds ? "HH:mm:ss" : "HH:mm";
        } else {
            pattern = config.showSeconds ? "hh:mm:ss a" : "hh:mm a";
        }

        String separator = colonVisible ? ":" : " ";
        pattern = pattern.replace(":", separator);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

        for (ClockEntry entry : config.clocks) {
            ZoneId zone;
            try {
                zone = ZoneId.of(entry.zoneId);
            } catch (Exception e) {
                zone = FALLBACK;
            }

            String time = ZonedDateTime.now(zone).format(formatter);
            String label = (entry.label != null && !entry.label.isBlank()) ? entry.label + ": " : "";
            lines.add(new CachedLine(label, time, entry.color | 0xFF000000));
        }
    }

    public void invalidate() {
        lastUpdateSecond = -1;
    }
}
