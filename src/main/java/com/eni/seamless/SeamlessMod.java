package com.eni.seamless;

import com.eni.seamless.event.ModEvents;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SeamlessMod implements ModInitializer {
    public static final String MOD_ID = "seamless";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Seamless Universe Initializing...");
        
        // Register Tick Event
        ServerTickEvents.START_SERVER_TICK.register(ModEvents::onServerTick);
        // Player Tick Logic for Teleportation via Fabric
    }
}
