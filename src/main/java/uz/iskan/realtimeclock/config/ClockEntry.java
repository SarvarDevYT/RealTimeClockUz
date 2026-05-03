package uz.iskan.realtimeclock.config;

public class ClockEntry {
    public String label;
    public String zoneId;
    public int color;

    public ClockEntry() {
        this.label = "Clock";
        this.zoneId = "Asia/Tashkent";
        this.color = 0xFFFFFF;
    }

    public ClockEntry(String label, String zoneId, int color) {
        this.label = label;
        this.zoneId = zoneId;
        this.color = color;
    }

    public ClockEntry copy() {
        return new ClockEntry(label, zoneId, color);
    }
}
