package uz.iskan.realtimeclock.config;

public class ModConfig {
    public String timezone = "Asia/Tashkent";
    public boolean showSeconds = true;
    public int offsetX = 6;
    public int offsetY = 6;
    public Corner corner = Corner.BOTTOM_RIGHT;

    public enum Corner {
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT
    }
}
