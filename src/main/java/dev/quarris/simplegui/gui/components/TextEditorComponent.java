package dev.quarris.simplegui.gui.components;

import com.mojang.blaze3d.matrix.MatrixStack;
import dev.quarris.simplegui.gui.base.QuiComponent;
import dev.quarris.simplegui.gui.base.QuiScreen;
import dev.quarris.simplegui.util.FontUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;

import java.util.LinkedList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;

public class TextEditorComponent extends QuiComponent {

    private final LinkedList<StringBuilder> textLines;
    private int currentLine;
    private int currentPos;

    private long lastSecond;
    private boolean renderCursor;

    public TextEditorComponent(QuiScreen screen, int x, int y, int width, int height) {
        super(screen, x, y, width, height);
        this.textLines = new LinkedList<>();
        this.textLines.add(new StringBuilder());
        this.lastSecond = Util.milliTime();
    }

    @Override
    protected void init(QuiScreen screen) {
        super.init(screen);
        Minecraft.getInstance().keyboardListener.enableRepeatEvents(true);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        Minecraft mc = Minecraft.getInstance();
        FontRenderer font = mc.fontRenderer;
        try {
            if ((Util.milliTime() - this.lastSecond) > 500) {
                this.renderCursor = !this.renderCursor;
                this.lastSecond += 500;
            }
            for (int i = 0, trueLine = 0; i < this.textLines.size(); i++, trueLine++) {
                StringBuilder sb = this.textLines.get(i);
                int drawX = this.x + font.getStringWidth("000 ");
                int drawY = this.y + trueLine * font.FONT_HEIGHT + 1;
                font.drawText(matrixStack, new StringTextComponent(String.valueOf(i + 1)), this.x, drawY, 0xffdddddd);
                /*
                List<String> lines = FontUtil.splitString(font, sb.toString(), this.width - font.getStringWidth("000 "));
                for (String line : lines) {
                    font.drawText(matrixStack, new StringTextComponent(line), drawX, drawY, 0xffdddddd);
                    trueLine++;
                    drawY += font.FONT_HEIGHT + 1;
                }
                drawY -= font.FONT_HEIGHT + 1;
                */
                font.drawString(matrixStack, sb.toString(), drawX, drawY, 0xffdddddd);
                if (this.currentLine == i && this.renderCursor) {
                    font.drawString(matrixStack, "|", drawX + font.getStringWidth(sb.substring(0, this.currentPos)) - 1, drawY, 0xffdddddd);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        Minecraft mc = Minecraft.getInstance();
        FontRenderer font = mc.fontRenderer;
        int clickedLine = Math.min((int)((mouseY - this.y) / font.FONT_HEIGHT), this.textLines.size() - 1);
        StringBuilder s = this.textLines.get(clickedLine);
        int clickedPixelPos = Math.max(0, (int)((mouseX - this.x - font.getStringWidth("000 "))));
        int clickedCursorPos = font.trimStringToWidth(s.toString(), clickedPixelPos).length();
        if (clickedCursorPos < s.length()) {
            int charWidth = font.getStringWidth(String.valueOf(s.charAt(clickedCursorPos)));
            int strWidth = font.getStringWidth(s.substring(0, clickedCursorPos));
            int diff = clickedPixelPos - strWidth;
            if (diff >= charWidth / 2F) {
                clickedCursorPos++;
            }
        }
        this.currentPos = clickedCursorPos;
        this.currentLine = clickedLine;
        return true;
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        return false;
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        return false;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        try {
            switch (keyCode) {
                case GLFW_KEY_UP: {
                    if (this.currentLine > 0) {
                        this.currentLine--;
                        if (this.currentPos > this.getCurrentTextLine().length()) {
                            this.currentPos = this.getCurrentTextLine().length();
                        }
                    } else {
                        this.currentPos = 0;
                    }
                    return true;
                }
                case GLFW_KEY_DOWN: {
                    if (this.currentLine < this.textLines.size() - 1) {
                        this.currentLine++;
                        if (this.currentPos > this.getCurrentTextLine().length()) {
                            this.currentPos = this.getCurrentTextLine().length();
                        }
                    } else {
                        this.currentPos = this.getCurrentTextLine().length();
                    }
                    return true;
                }
                case GLFW_KEY_LEFT: {
                    if (this.currentPos == 0 && this.currentLine > 0) {
                        this.currentLine--;
                        this.currentPos = this.getCurrentTextLine().length();
                    } else if (this.currentPos > 0) {
                        if ((modifiers & GLFW_MOD_CONTROL) == GLFW_MOD_CONTROL) {
                            int testIndex = this.currentPos - 1;
                            char testChar = this.getCurrentTextLine().charAt(testIndex);
                            if (isJumpToCharacter(testChar)) {
                                this.currentPos--;
                                return true;
                            }
                            while (testIndex > 0 && (Character.isLetterOrDigit(testChar))) {
                                testIndex--;
                                testChar = this.getCurrentTextLine().charAt(testIndex);
                            }
                            this.currentPos = testIndex;
                        } else {
                            this.currentPos--;
                        }
                    }
                    return true;
                }
                case GLFW_KEY_RIGHT: {
                    if (this.currentPos == this.getCurrentTextLine().length() &&
                            this.currentLine < this.textLines.size() - 1) {
                        this.currentPos = 0;
                        this.currentLine++;
                    } else if (this.currentPos < this.getCurrentTextLine().length()) {
                        this.currentPos++;
                    }
                    return true;
                }
                case GLFW_KEY_ENTER: {
                    String line = this.getCurrentTextLine().substring(this.currentPos);
                    if (!line.isEmpty()) {
                        this.getCurrentTextLine().delete(this.currentPos, this.getCurrentTextLine().length());
                    }
                    this.currentLine++;
                    this.currentPos = 0;
                    this.textLines.add(this.currentLine, new StringBuilder(line));
                    return true;
                }
                case GLFW_KEY_BACKSPACE: {
                    if (this.currentPos == 0 && this.currentLine > 0) {
                        StringBuilder line = this.textLines.remove(this.currentLine);
                        this.currentLine--;
                        this.currentPos = this.getCurrentTextLine().length();
                        if (line.length() > 0) {
                            this.getCurrentTextLine().append(line);
                        }
                    } else if (this.currentPos > 0) {
                        this.currentPos--;
                        this.getCurrentTextLine().deleteCharAt(this.currentPos);
                    }
                    return true;
                }
                case GLFW_KEY_DELETE: {
                    if (this.currentPos == this.getCurrentTextLine().length() && this.currentLine < this.textLines.size() - 1) {
                        StringBuilder line = this.textLines.get(this.currentLine + 1);
                        this.textLines.remove(this.currentLine + 1);
                        this.getCurrentTextLine().append(line);
                    } else if (this.currentPos < this.getCurrentTextLine().length()) {
                        this.getCurrentTextLine().deleteCharAt(this.currentPos);
                    }
                    return true;
                }
                case GLFW_KEY_HOME: {
                    this.currentPos = 0;
                    return true;
                }
                case GLFW_KEY_END: {
                    this.currentPos = this.getCurrentTextLine().length();
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private static boolean isJumpToCharacter(char c) {
        return Character.getType(c) > Character.DASH_PUNCTUATION;
    }

    private StringBuilder getCurrentTextLine() {
        return this.textLines.get(this.currentLine);
    }

    @Override
    public boolean charTyped(char codePoint, int modifiers) {
        this.getCurrentTextLine().insert(this.currentPos, codePoint);
        this.currentPos++;
        return true;
    }

    @Override
    public void onScreenClose() {
        Minecraft.getInstance().keyboardListener.enableRepeatEvents(false);
    }
}
