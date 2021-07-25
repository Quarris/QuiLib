package dev.quarris.simplegui.gui.util;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.ResourceLocation;

public class NinePatch {

    public final ResourceLocation texture;
    public final Padding padding;
    public final int textureWidth;
    public final int textureHeight;

    public NinePatch(ResourceLocation texture, Padding padding, int textureWidth, int textureHeight) {
        this.texture = texture;
        this.padding = padding;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
    }

    public void render(MatrixStack matrixStack, int x, int y, int width, int height) {
        Minecraft mc = Minecraft.getInstance();
        mc.getTextureManager().bindTexture(this.texture);

        int left = this.padding.left;
        int right = this.padding.right;
        int top = this.padding.top;
        int bottom = this.padding.bottom;
        int centerWidth = width - left - right;
        int centerHeight = height - top - bottom;
        int centerTextureWidth = this.textureWidth - left - right;
        int centerTextureHeight = this.textureHeight - top - bottom;

        this.blit(matrixStack, x, y, left, top,
                0, 0, left, top); // Top Left
        this.blit(matrixStack, x + left, y, centerWidth, top,
                left, 0, centerTextureWidth, top); // Top Center
        this.blit(matrixStack, x + left + centerWidth, y, right, top,
                left + centerTextureWidth, 0, right, top); // Top Right

        this.blit(matrixStack, x, y + top, left, centerHeight,
                0, top, left, centerTextureHeight); // Middle Left
        this.blit(matrixStack, x + left, y + top, centerWidth, centerHeight,
                left, top, centerTextureWidth, centerTextureHeight); // Middle Center
        this.blit(matrixStack, x + left + centerWidth, y + top, right, centerHeight,
                left + centerTextureWidth, top, right, centerTextureHeight); // Middle Right

        this.blit(matrixStack, x, y + top + centerHeight, left, bottom,
                0, top + centerTextureHeight, left, bottom); // Bottom Left
        this.blit(matrixStack, x + left, y + top + centerHeight, centerWidth, bottom,
                left, top + centerTextureHeight, centerTextureWidth, bottom); // Bottom Center
        this.blit(matrixStack, x + left + centerWidth, y + top + centerHeight, right, bottom,
                left + centerTextureWidth, top + centerTextureHeight, right, bottom); // Bottom Right
    }

    private void blit(MatrixStack matrixStack, int x, int y, int width, int height, int u, int v, int uvWidth, int uvHeight) {
        AbstractGui.blit(matrixStack, x, y, width, height, u, v, uvWidth, uvHeight, this.textureWidth, this.textureHeight);
    }

}
