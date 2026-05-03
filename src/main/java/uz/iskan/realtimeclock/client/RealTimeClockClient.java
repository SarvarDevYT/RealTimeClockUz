package uz.iskan.realtimeclock.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import uz.iskan.realtimeclock.hud.HudRenderer;

public class RealTimeClockClient implements ClientModInitializer {

    private final HudRenderer hudRenderer = new HudRenderer();

    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register((ctx, tick) -> hudRenderer.onHudRender(ctx));
    }
}
