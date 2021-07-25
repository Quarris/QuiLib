package dev.quarris.simplegui;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class SetupHandler {

    @SubscribeEvent
    public void commonSetup(FMLCommonSetupEvent event) {
    }

    @SubscribeEvent
    public void clientSetup(FMLClientSetupEvent event) {

    }

}
