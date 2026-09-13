package com.mineverze.customxp.mixin;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {
    @Redirect(
        method = "renderExperienceLevel",
        at = @At(
            value = "INVOKE",
            target = "Lnet.minecraft.client.gui/DrawContext;drawText(Lnet.minecraft.client.font/TextRenderer;Ljava/lang/String;IIIZ)I"
        )
    )
    private int customXPText(DrawContext context, TextRenderer textRenderer, String text, int x, int y, int color, boolean shadow) {
        String customName = "Mineverze Gaming";
        int windowWidth = context.getScaledWindowWidth();
        int newX = (windowWidth - textRenderer.getWidth(customName)) / 2;
        return context.drawText(textRenderer, customName, newX, y, color, shadow);
    }
}
