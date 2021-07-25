package dev.quarris.simplegui.gui.base;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.ITextComponent;

import java.util.LinkedList;
import java.util.List;

public final class QuiScreen extends Screen {

    // Linked List of gui, front most gui is stored in the back of the list
    protected LinkedList<QuiGui> guis;

    private QuiScreen(ITextComponent titleIn) {
        super(titleIn);
        this.guis = new LinkedList<>();
    }

    public static QuiScreen create(ITextComponent title) {
        QuiScreen screen = new QuiScreen(title);
        return screen;
    }

    @Override
    public void init(Minecraft minecraft, int width, int height) {
        super.init(minecraft, width, height);
    }

    @Override
    protected void init() {
        for (QuiGui gui : this.guis) {
            gui.init(this);
        }
    }

    public void addGui(QuiGui gui) {
        this.guis.add(gui);
    }

    public void removeGui(QuiGui gui) {
        this.guis.remove(gui);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        for (QuiGui gui : this.guis) {
            gui.render(matrixStack, mouseX, mouseY, partialTicks);
        }
    }

    @Override
    public void onClose() {
        for (QuiGui gui : this.guis) {
            gui.onScreenClose();
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return super.mouseClicked(mouseX, mouseY, button);
        /*Iterator<QuiGui> ite = this.guis.descendingIterator();
        while (ite.hasNext()) {
            QuiGui gui = ite.next();
            if (gui.mouseClicked(mouseX, mouseY, button)) {
                this.setListener(gui.getListener());
                this.focusGui(gui);
                if (button == 0) {
                    this.setDragging(true);
                }

                return true;
            }
        }

        return false;*/
    }

    @Override
    public List<? extends IGuiEventListener> getEventListeners() {
        return this.guis;
    }
}
