package dev.quarris.simplegui.gui.window;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.text.ITextComponent;

public class QuiTitleBar extends QuiDraggable {

    protected final ITextComponent title;
    protected final QuiWindow window;

    public QuiTitleBar(QuiWindow window, ITextComponent title, int x, int y, int width, int height) {
        super(window.screen, x, y, width, height);
        this.window = window;
        this.title = title;
    }

    public ITextComponent getTitle() {
        return this.title;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        AbstractGui.fill(matrixStack, this.x, this.y, this.x + this.width, this.y + this.height, 0xff5693e3);
        Minecraft.getInstance().fontRenderer.drawText(matrixStack, this.title, this.x, this.y, 0xff000000);
    }

    @Override
    public boolean onDrag(int dx, int dy) {
        this.window.move(dx, dy);
        return true;
    }
}
