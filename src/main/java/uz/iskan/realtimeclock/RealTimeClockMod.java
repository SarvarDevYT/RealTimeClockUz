package uz.iskan.realtimeclock;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uz.iskan.realtimeclock.config.ConfigManager;

public class RealTimeClockMod implements ModInitializer {
    public static final String MOD_ID = "realtimeclockuz";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ConfigManager.load();
        LOGGER.info("RealTimeClockUz (1.21.x) initialized!");
    }
}
