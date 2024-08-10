package cn.ksmcbrigade.bc.mixin.render;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SystemToast.class)
public class SystemToastMixin {
    @Redirect(method = "draw",at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawTexture(Lnet/minecraft/util/Identifier;IIIIII)V"))
    public void draw(DrawContext instance, Identifier texture, int x, int y, int u, int v, int width, int height){
        RenderSystem.blendFuncSeparate(770, 771, 1, 771);
        instance.drawTexture(texture, x, y, u, v, width, height);
        RenderSystem.blendFuncSeparate(768, 769, 0, 771);
    }
}
