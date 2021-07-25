package dev.quarris.simplegui;

import dev.quarris.simplegui.gui.util.NinePatch;
import dev.quarris.simplegui.gui.util.Padding;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(QuiLib.ID)
public class QuiLib {

    public static final String ID = "quilib";
    public static final String NAME = "QuiLib";

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public static NinePatch mcNinePatch = new NinePatch(res("textures/gui/nine_patches/default.png"), new Padding(4), 9, 9);
    public static NinePatch terminalNinePatch = new NinePatch(res("test/textures/gui/nine_patches/dark.png"), new Padding(4), 9, 9);

    public QuiLib() {
        FMLJavaModLoadingContext.get().getModEventBus().register(new SetupHandler());
    }

    public static ResourceLocation res(String res) {
        return new ResourceLocation(ID, res);
    }
}
