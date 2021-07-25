package dev.quarris.simplegui.test;

import dev.quarris.simplegui.gui.base.QuiScreen;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.text.StringTextComponent;
import org.lwjgl.glfw.GLFW;

public class TestMain {

    public static final KeyBinding OPEN_TEST_GUI_KEYBIND = new KeyBinding("key.openTestGui", GLFW.GLFW_KEY_K, "key.category.inventory");

    public static void init() {
    }

    public static QuiScreen newScreen() {
        QuiScreen screen = QuiScreen.create(new StringTextComponent("Qui Screen"));
        screen.addGui(new TestGui(screen, 200, 160));
        return screen;
    }

}
