package cn.ksmcbrigade.bc.mixin.render;

import cn.ksmcbrigade.bc.BlueClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.LogoDrawer;
import net.minecraft.client.gui.RotatingCubeMapRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.SplashTextRenderer;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin extends Screen {
    protected TitleScreenMixin(Text title) {
        super(title);
    }

    @Redirect(method = "render",at = @At(value = "INVOKE",target = "Lnet/minecraft/client/gui/RotatingCubeMapRenderer;render(FF)V"))
    public void noRenderVanillaBackground(RotatingCubeMapRenderer instance, float delta, float alpha){
    }

    @Redirect(method = "render",at = @At(value = "INVOKE",target = "Lnet/minecraft/client/gui/screen/SplashTextRenderer;render(Lnet/minecraft/client/gui/DrawContext;ILnet/minecraft/client/font/TextRenderer;I)V"))
    public void noRenderSplash(SplashTextRenderer instance, DrawContext context, int screenWidth, TextRenderer textRenderer, int alpha){}

    @Inject(method = "render",at = @At(value = "INVOKE",target = "Lnet/minecraft/client/gui/RotatingCubeMapRenderer;render(FF)V",shift = At.Shift.AFTER))
    public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci){
        int width = context.getScaledWindowWidth();
        int height = context.getScaledWindowHeight();

        context.drawTexture(BlueClient.background,0,0,0,0,width,height,width,height);
    }

    @Redirect(method = "render",at = @At(value = "INVOKE",target = "Lnet/minecraft/client/gui/LogoDrawer;draw(Lnet/minecraft/client/gui/DrawContext;IF)V"))
    public void drawLogo(LogoDrawer instance, DrawContext context, int screenWidth, float alpha){
        int x = screenWidth / 2 - 128;
        context.drawTexture(new Identifier(BlueClient.MOD_ID,"gui/title/title.png"),x,30,0,0,256,64+16,256,64+16);
    }
}
