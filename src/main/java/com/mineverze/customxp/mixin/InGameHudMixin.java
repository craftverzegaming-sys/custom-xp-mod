package com.mineverze.customxp.mixin;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {

    @Shadow
    public abstract TextRenderer getTextRenderer();

    @Inject(method = "renderExperienceLevel", at = @At("HEAD"), cancellable = true)
    private void renderCustomXPText(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        TextRenderer textRenderer = this.getTextRenderer();
        if (textRenderer == null) return;

        String customName = "Mineverze Gaming";

        int windowWidth = context.getScaledWindowWidth();
        int windowHeight = context.getScaledWindowHeight();

        // Calculate positioning over the vanilla XP bar
        int x = (windowWidth - textRenderer.getWidth(customName)) / 2;
        int y = windowHeight - 32 - 3;

        // Render black text outline
        context.drawText(textRenderer, customName, x + 1, y, 0x000000, false);
        context.drawText(textRenderer, customName, x - 1, y, 0x000000, false);
        context.drawText(textRenderer, customName, x, y + 1, 0x000000, false);
        context.drawText(textRenderer, customName, x, y - 1, 0x000000, false);

        // Render main text in XP green (#80FF20)
        context.drawText(textRenderer, customName, x, y, 0x80FF20, false);

        // Cancel default XP number render
        ci.cancel();
    }
}
