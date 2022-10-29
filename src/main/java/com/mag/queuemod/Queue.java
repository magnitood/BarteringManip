package com.mag.queuemod;

import com.mag.queuemod.core.BarteringManager;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Entry point class, nothing much going on here, main magic happens in BarteringManager and MixinBarterInjection
 */
public class Queue implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger("Bartering Manip");

    @Override
    public void onInitialize() {
        BarteringManager.setBarteringEnabled(true);
        LOGGER.info("Bartering Manip Mod Initialised");
        LOGGER.info("Bartering Queue is enabled");
    }
    
}
