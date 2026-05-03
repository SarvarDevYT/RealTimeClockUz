package uz.iskan.realtimeclock.hud;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import uz.iskan.realtimeclock.config.ConfigManager;
import uz.iskan.realtimeclock.config.ModConfig;

import java.util.List;

/**
 * HUD ko'rsatish logikasi. RealTimeClockClient tomonidan chaqiriladi.
 */
public class HudRenderer {

    private static final int PADDING   = 3;
    private static final int LINE_GAP  = 1;
    private static final int BG_COLOR  = 0x88000000;

    private final TimeCache cache = new TimeCache();
    private long blinkTimer = 0;

    public void onHudRender(DrawContext ctx) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.options.hudHidden) return;

        ModConfig config = ConfigManager.getConfig();
        if (!config.enabled) return;
        if (config.clocks == null || config.clocks.isEmpty()) return;

        // Blink: her 500ms da ikki punkch ko'rinishi o'zgaradi
        long now = System.currentTimeMillis();
        if (config.blinkColon && now - blinkTimer >= 500) {
            blinkTimer = now;
            cache.invalidate();
        }
        boolean colonVisible = !config.blinkColon || ((now / 500) % 2 == 0);

        List<TimeCache.CachedLine> lines = cache.getLines(config, colonVisible);
        if (lines.isEmpty()) return;

        TextRenderer tr = client.textRenderer;
        int lineH = tr.fontHeight + LINE_GAP;

        // Har bir qatorning maksimal kengligi
        int maxWidth = 0;
        for (TimeCache.CachedLine line : lines) {
            int w = tr.getWidth(line.label + line.time);
            if (w > maxWidth) maxWidth = w;
        }

        int totalHeight = lineH * lines.size() - LINE_GAP;

        int screenW = ctx.getScaledWindowWidth();
        int screenH = ctx.getScaledWindowHeight();

        // Chap yuqori burchak koordinatalari (background uchun)
        int boxX = calcX(config, screenW, maxWidth);
        int boxY = calcY(config, screenH, totalHeight);

        // Fon chizish
        if (config.showBackground) {
            ctx.fill(
                    boxX - PADDING,
                    boxY - PADDING,
                    boxX + maxWidth + PADDING,
                    boxY + totalHeight + PADDING,
                    BG_COLOR
            );
        }

        // Har bir qatorni chizish
        for (int i = 0; i < lines.size(); i++) {
            TimeCache.CachedLine line = lines.get(i);
            int y = boxY + i * lineH;
            // Label qora rangda, vaqt o'z rangida
            int labelColor = 0xCCCCCC | 0xFF000000;
            ctx.drawText(tr, line.label, boxX, y, labelColor, true);
            int labelW = tr.getWidth(line.label);
            ctx.drawText(tr, line.time, boxX + labelW, y, line.color, true);
        }
    }

    // --- Yordamchi metodlar ---

    private int calcX(ModConfig config, int screenW, int textW) {
        return switch (config.corner) {
            case TOP_LEFT, BOTTOM_LEFT   -> config.offsetX;
            case TOP_RIGHT, BOTTOM_RIGHT -> screenW - textW - config.offsetX;
        };
    }

    private int calcY(ModConfig config, int screenH, int totalH) {
        return switch (config.corner) {
            case TOP_LEFT, TOP_RIGHT     -> config.offsetY;
            case BOTTOM_LEFT, BOTTOM_RIGHT -> screenH - totalH - config.offsetY;
        };
    }
}
