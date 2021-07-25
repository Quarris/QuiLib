package dev.quarris.simplegui.gui.util;

public class Padding {

    public final int left;
    public final int right;
    public final int top;
    public final int bottom;

    public Padding(int left, int right, int top, int bottom) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
    }

    public Padding(int horizontal, int vertical) {
        this(horizontal, horizontal, vertical, vertical);
    }

    public Padding(int padding) {
        this(padding, padding);
    }
}
