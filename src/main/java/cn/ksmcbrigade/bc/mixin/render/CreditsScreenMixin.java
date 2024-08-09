package cn.ksmcbrigade.bc.mixin.render;

import cn.ksmcbrigade.bc.BlueClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.CreditsScreen;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CreditsScreen.class)
public class CreditsScreenMixin {
    @Redirect(method = "renderBackground",at = @At(value = "INVOKE",target = "Lnet/minecraft/client/gui/DrawContext;drawTexture(Lnet/minecraft/util/Identifier;IIIFFIIII)V"))
    public void background(DrawContext context, Identifier texture, int x, int y, int z, float u, float v, int widt, int heigh, int textureWidth, int textureHeight){
        int width = context.getScaledWindowWidth();
        int height = context.getScaledWindowHeight();

        context.drawTexture(BlueClient.background,0,0,0,0,width,height,width,height);
    }
}
