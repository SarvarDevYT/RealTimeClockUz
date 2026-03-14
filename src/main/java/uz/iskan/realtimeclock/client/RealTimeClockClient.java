package uz.iskan.realtimeclock.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.text.Text;
import uz.iskan.realtimeclock.config.ConfigManager;
import uz.iskan.realtimeclock.config.ModConfig;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class RealTimeClockClient implements ClientModInitializer {
    private static final DateTimeFormatter FORMAT_WITH_SECONDS = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final DateTimeFormatter FORMAT_WITHOUT_SECONDS = DateTimeFormatter.ofPattern("HH:mm");

    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register(this::onHudRender);
    }

    private void onHudRender(DrawContext drawContext, RenderTickCounter tickCounter) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.options.hudHidden) return;

        ModConfig config = ConfigManager.getConfig();
        ZoneId zoneId;
        try {
            zoneId = ZoneId.of(config.timezone);
        } catch (Exception e) {
            zoneId = ZoneId.of("Asia/Tashkent");
        }

        ZonedDateTime now = ZonedDateTime.now(zoneId);
        String timeText = now.format(config.showSeconds ? FORMAT_WITH_SECONDS : FORMAT_WITHOUT_SECONDS);

        TextRenderer textRenderer = client.textRenderer;
        int textWidth = textRenderer.getWidth(timeText);
        int textHeight = textRenderer.fontHeight;

        int screenWidth = drawContext.getScaledWindowWidth();
        int screenHeight = drawContext.getScaledWindowHeight();

        int x = switch (config.corner) {
            case TOP_LEFT, BOTTOM_LEFT -> config.offsetX;
            case TOP_RIGHT, BOTTOM_RIGHT -> screenWidth - textWidth - config.offsetX;
        };

        int y = switch (config.corner) {
            case TOP_LEFT, TOP_RIGHT -> config.offsetY;
            case BOTTOM_LEFT, BOTTOM_RIGHT -> screenHeight - textHeight - config.offsetY;
        };

        drawContext.drawText(textRenderer, Text.literal(timeText), x, y, 0xFFFFFFFF, true);
    }
}
