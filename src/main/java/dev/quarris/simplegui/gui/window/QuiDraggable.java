package dev.quarris.simplegui.gui.window;

import dev.quarris.simplegui.gui.base.QuiComponent;
import dev.quarris.simplegui.gui.base.QuiScreen;

public abstract class QuiDraggable extends QuiComponent {

    public QuiDraggable(QuiScreen screen, int x, int y, int width, int height) {
        super(screen, x, y, width, height);
    }

    public abstract boolean onDrag(int dx, int dy);

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        return this.onDrag((int) dragX, (int) dragY);
    }


}
