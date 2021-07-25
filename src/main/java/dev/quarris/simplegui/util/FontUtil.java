package dev.quarris.simplegui.util;

import net.minecraft.client.gui.FontRenderer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FontUtil {


    public static List<String> splitString(FontRenderer font, String text, int width) {
        if (font.getStringWidth(text) < width)
            return Collections.singletonList(text);

        StringBuilder sb = new StringBuilder(text);
        List<String> lines = new ArrayList<>();
        while (sb.length() > 0) {
            String line = font.trimStringToWidth(sb.toString(), width);
            sb.delete(0, line.length());
            lines.add(line);
        }

        return lines;
    }
}
