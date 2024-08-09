package cn.ksmcbrigade.bc.mixin.render;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.PressableWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

import java.awt.*;

@Mixin(PressableWidget.class)
public abstract class PressableWidgetMixin extends ClickableWidget {
    public PressableWidgetMixin(int x, int y, int width, int height, Text message) {
        super(x, y, width, height, message);
    }

    @Redirect(method = "renderButton",at = @At(value = "INVOKE",target = "Lnet/minecraft/client/gui/DrawContext;setShaderColor(FFFF)V"),slice = @Slice(
            from = @At(value = "INVOKE",target = "Lnet/minecraft/client/MinecraftClient;getInstance()Lnet/minecraft/client/MinecraftClient;"),
            to = @At(value = "INVOKE",target = "Lnet/minecraft/client/gui/DrawContext;drawNineSlicedTexture(Lnet/minecraft/util/Identifier;IIIIIIIIII)V")
    ))
    public void shaderBackground(DrawContext instance, float red, float green, float blue, float alpha){
        instance.setShaderColor(Color.darkGray.getRed(), Color.darkGray.getGreen(), Color.darkGray.getBlue(), 0.8F);
    }
}
