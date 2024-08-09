package cn.ksmcbrigade.bc.mixin.render;

import cn.ksmcbrigade.bc.BlueClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.SplashOverlay;
import net.minecraft.client.util.Window;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.awt.*;

@Mixin(SplashOverlay.class)
public class SplashOverlayMixin {

    /**
     * @author KSmc_brigade
     * @reason custom the game loading overlay background
     */
    @Inject(method = "render",at = @At(value = "INVOKE", target = "Lnet/minecraft/resource/ResourceReload;getProgress()F"),locals = LocalCapture.CAPTURE_FAILSOFT)
    public void render(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci, int i, int j, long l, float f, float g, float h, int k, int p, double d, int q, double e, int r, int s){
        Window window = MinecraftClient.getInstance().getWindow();
        context.fill(0,0,window.getScaledWidth(),window.getScaledHeight(), Color.BLUE.getRGB());
        context.drawTexture(new Identifier(BlueClient.MOD_ID,"gui/background/background.png"), 0,0, 0,0, window.getScaledWidth(),window.getScaledHeight(),window.getScaledWidth(),window.getScaledHeight());
    }
}