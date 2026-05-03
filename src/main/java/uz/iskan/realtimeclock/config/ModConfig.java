package uz.iskan.realtimeclock.config;

import java.util.ArrayList;
import java.util.List;

public class ModConfig {

    public List<ClockEntry> clocks = new ArrayList<>(List.of(
            new ClockEntry("Tashkent", "Asia/Tashkent", 0xFFFFFF),
            new ClockEntry("London",   "Europe/London",  0xAADDFF),
            new ClockEntry("Tokyo",    "Asia/Tokyo",     0xFFCCAA)
    ));

    public boolean enabled      = true;

    public boolean showSeconds  = true;
    public boolean use24h       = true;
    public boolean showBackground = true;
    public boolean blinkColon   = false;

    public int offsetX = 6;
    public int offsetY = 6;

    public Corner corner = Corner.BOTTOM_RIGHT;

    public enum Corner {
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT
    }

    /** Ensures any null/invalid fields are replaced with defaults. */
    public void validate() {
        if (clocks == null || clocks.isEmpty()) {
            clocks = new ArrayList<>(List.of(
                    new ClockEntry("Tashkent", "Asia/Tashkent", 0xFFFFFF),
                    new ClockEntry("London",   "Europe/London",  0xAADDFF),
                    new ClockEntry("Tokyo",    "Asia/Tokyo",     0xFFCCAA)
            ));
        }
        for (ClockEntry e : clocks) {
            if (e.label == null)  e.label  = "Clock";
            if (e.zoneId == null) e.zoneId = "Asia/Tashkent";
        }
        if (corner == null) corner = Corner.BOTTOM_RIGHT;
        if (offsetX < 0)   offsetX = 0;
        if (offsetY < 0)   offsetY = 0;

    }
}
