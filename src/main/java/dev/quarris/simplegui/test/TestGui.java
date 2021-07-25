package dev.quarris.simplegui.test;

import com.mojang.blaze3d.matrix.MatrixStack;
import dev.quarris.simplegui.QuiLib;
import dev.quarris.simplegui.gui.base.QuiGui;
import dev.quarris.simplegui.gui.base.QuiScreen;
import dev.quarris.simplegui.gui.components.TextEditorComponent;
import net.minecraft.client.gui.AbstractGui;

public class TestGui extends QuiGui {

    private TextEditorComponent editor;

    public TestGui(QuiScreen screen, int width, int height) {
        super(screen, 0, 0, width, height);
        this.editor = new TextEditorComponent(screen, 0, 0, width - 10, height - 10);
        this.addComponent(this.editor);
        this.setFocusedDefault(this.editor);
    }

    @Override
    protected void init(QuiScreen screen) {
        this.x = (screen.width - this.width) / 2;
        this.y = (screen.height - this.height) / 2;
        super.init(screen);
        this.editor.x = this.x + 5;
        this.editor.y = this.y + 5;
    }

    @Override
    protected void renderBackground(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        QuiLib.terminalNinePatch.render(matrixStack, this.x, this.y, this.width, this.height);
        AbstractGui.fill(matrixStack, this.x + 5, this.y + 5, this.x + this.width - 5, this.y + this.height - 5, 0xff111111);
    }


}
