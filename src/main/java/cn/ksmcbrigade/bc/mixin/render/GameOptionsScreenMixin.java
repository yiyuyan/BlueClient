package cn.ksmcbrigade.bc.mixin.render;

import cn.ksmcbrigade.bc.BlueClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GameOptionsScreen.class)
public class GameOptionsScreenMixin {

    @Redirect(method = "render",at = @At(value = "INVOKE",target = "Lnet/minecraft/client/gui/screen/option/GameOptionsScreen;renderBackground(Lnet/minecraft/client/gui/DrawContext;)V"))
    public void background(GameOptionsScreen instance, DrawContext context){
        int width = context.getScaledWindowWidth();
        int height = context.getScaledWindowHeight();

        context.drawTexture(BlueClient.background,0,0,0,0,width,height,width,height);
    }
}
