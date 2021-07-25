package dev.quarris.simplegui.gui.window;

import com.mojang.blaze3d.matrix.MatrixStack;
import dev.quarris.simplegui.gui.base.QuiGui;
import dev.quarris.simplegui.gui.base.QuiScreen;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.text.StringTextComponent;

public class QuiWindow extends QuiGui {

    protected QuiTitleBar titleBar;
    protected String title;

    public QuiWindow(QuiScreen screen, int x, int y, int width, int height, String title) {
        super(screen, x, y, width, height);
        this.title = title;
    }

    @Override
    protected void init(QuiScreen screen) {
        this.titleBar = new QuiTitleBar(this, new StringTextComponent(title), x, y, width, 10);
        this.addComponent(this.titleBar);
        super.init(screen);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    protected void renderBackground(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        AbstractGui.fill(matrixStack, this.x, this.y, this.x + this.width, this.y + this.height, 0xffdddddd);
    }
}
