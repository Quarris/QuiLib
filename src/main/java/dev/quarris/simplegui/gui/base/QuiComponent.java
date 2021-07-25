package dev.quarris.simplegui.gui.base;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.FocusableGui;
import net.minecraft.client.gui.IGuiEventListener;

import java.util.LinkedList;
import java.util.List;

public class QuiComponent extends FocusableGui {

    public final QuiScreen screen;
    public QuiComponent parent;
    public int x;
    public int y;
    public int width;
    public int height;
    protected LinkedList<QuiComponent> children;

    public QuiComponent(QuiScreen screen, int x, int y, int width, int height) {
        this.screen = screen;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.children = new LinkedList<>();
    }

    protected void init(QuiScreen screen) {
        for (QuiComponent child : this.children) {
            child.init(screen);
        }
    }

    public void addComponent(QuiComponent widget) {
        widget.parent = this;
        this.children.addLast(widget);
    }

    public void addComponent(QuiComponent widget, int layer) {
        widget.parent = this;
        this.children.add(layer, widget);
    }

    public void removeComponent(QuiComponent widget) {
        this.children.remove(widget);
    }

    public boolean move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
        for (QuiComponent child : this.children) {
            child.move(dx, dy);
        }

        return true;
    }

    public boolean setPos(int x, int y) {
        int dx = x - this.x;
        int dy = y - this.y;

        return this.move(dx, dy);
    }

    public boolean resize(int dw, int dh) {
        this.width += dw;
        this.height += dh;
        return true;
    }

    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        for (QuiComponent child : this.children) {
            child.render(matrixStack, mouseX, mouseY, partialTicks);
        }
    }

    @Override
    public List<? extends IGuiEventListener> getEventListeners() {
        return this.children;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.isMouseOver(mouseX, mouseY)) {
            return super.mouseClicked(mouseX, mouseY, button);
        }

        return false;

        /*if (!this.isMouseOver(mouseX, mouseY)) {
            this.changeFocus(false);
            for (QuiComponent child : this.children)
                child.changeFocus(false);
            return false;
        }

        for (QuiComponent child : this.children) {
            if (child.mouseClicked(mouseX, mouseY, button)) {
                child.changeFocus(true);
                this.focusComponent(child);
                return true;
            } else {
                child.changeFocus(false);
            }
        }

        this.focusedComponent = this;
        return true;*/
    }

    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
         return mouseX > this.x && mouseX < this.x + this.width && mouseY > this.y && mouseY < this.y + this.height;
    }

    public void onScreenClose() {
        for (QuiComponent component : this.children) {
            component.onScreenClose();
        }
    }
}
