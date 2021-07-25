package dev.quarris.simplegui.gui.base;

import com.mojang.blaze3d.matrix.MatrixStack;

public abstract class QuiGui extends QuiComponent {

    public QuiGui(QuiScreen screen, int x, int y, int width, int height) {
        super(screen, x, y, width, height);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack, mouseX, mouseY, partialTicks);
        this.renderForeground(matrixStack, mouseX, mouseY, partialTicks);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    protected abstract void renderBackground(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks);

    protected void renderForeground(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) { }
}
