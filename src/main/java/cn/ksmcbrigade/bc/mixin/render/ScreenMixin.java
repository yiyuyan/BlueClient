package cn.ksmcbrigade.bc.mixin.render;

import cn.ksmcbrigade.bc.BlueClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Screen.class)
public class ScreenMixin {
    @Shadow @Nullable protected MinecraftClient client;

    @Inject(method = "renderBackgroundTexture",at = @At("HEAD"), cancellable = true)
    public void renderBackground(DrawContext context, CallbackInfo ci){
        int width = context.getScaledWindowWidth();
        int height = context.getScaledWindowHeight();

        context.drawTexture(BlueClient.background,0,0,0,0,width,height,width,height);

        ci.cancel();
    }

    @Inject(method = "tick",at = @At("HEAD"))
    public void tick(CallbackInfo ci){
        BlueClient.config.getEnables().forEach(h -> {
            try {
                h.screenTick(this.client);
            } catch (Exception e) {
                BlueClient.LOGGER.error("error in screen tick",e);
            }
        });
    }

    @Inject(method = "keyPressed",at = @At("HEAD"))
    public void keyInput(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir){
        BlueClient.config.getEnables().forEach(h -> {
            try {
                h.keyInput(keyCode,true);
            } catch (Exception e) {
                BlueClient.LOGGER.error("error in key input",e);
            }
        });
    }
}
