package dev.quarris.simplegui.test;

import dev.quarris.simplegui.QuiLib;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = QuiLib.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TestSetup {

    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event) {
        TestMain.init();
    }

}
