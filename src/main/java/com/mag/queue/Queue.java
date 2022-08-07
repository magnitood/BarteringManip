package com.mag.queue;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Queue implements ModInitializer {
    public static final String MOD_ID = "queue";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Bartering Manip Mod Initialised");
    }
}
