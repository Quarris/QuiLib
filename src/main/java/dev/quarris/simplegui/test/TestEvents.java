package dev.quarris.simplegui.test;

import dev.quarris.simplegui.QuiLib;
import dev.quarris.simplegui.gui.base.QuiScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = QuiLib.ID)
public class TestEvents {

    @SubscribeEvent
    public static void processKeybinds(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            while (TestMain.OPEN_TEST_GUI_KEYBIND.isPressed()) {
                Minecraft.getInstance().displayGuiScreen(TestMain.newScreen());
            }
        }
    }

}
