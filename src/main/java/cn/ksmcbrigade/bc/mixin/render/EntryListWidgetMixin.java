package cn.ksmcbrigade.bc.mixin.render;

import cn.ksmcbrigade.bc.BlueClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.EntryListWidget;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntryListWidget.class)
public class EntryListWidgetMixin {

    @Shadow private boolean renderBackground;

    @Unique
    private boolean no = false;

    @Inject(method = "render",at = @At(value = "INVOKE",target = "Lnet/minecraft/client/gui/widget/EntryListWidget;isMouseOver(DD)Z",shift = At.Shift.AFTER))
    public void noBackground(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci){
        if(MinecraftClient.getInstance().cameraEntity!=null){
            this.renderBackground = false;
        }
        else{
            no = true;
        }
    }

    @Redirect(method = "render",at = @At(value = "INVOKE",target = "Lnet/minecraft/client/gui/DrawContext;drawTexture(Lnet/minecraft/util/Identifier;IIFFIIII)V"),slice = @Slice(
            from = @At(value = "INVOKE",target = "Lnet/minecraft/client/gui/DrawContext;disableScissor()V"),
            to = @At(value = "INVOKE",target = "Lnet/minecraft/client/gui/widget/EntryListWidget;getMaxScroll()I")
    ))
    public void headBackground(DrawContext instance, Identifier texture, int x, int y, float u, float v, int width, int height, int textureWidth, int textureHeight){
        instance.drawTexture(BlueClient.background,x,y,u,v,width,height,textureWidth,textureHeight);
    }

    @Redirect(method = "render",at = @At(value = "INVOKE",target = "Lnet/minecraft/client/gui/DrawContext;drawTexture(Lnet/minecraft/util/Identifier;IIFFIIII)V"),slice = @Slice(
            from = @At(value = "INVOKE",target = "Lnet/minecraft/client/gui/widget/EntryListWidget;isMouseOver(DD)Z"),
            to = @At(value = "INVOKE",target = "Lnet/minecraft/client/gui/widget/EntryListWidget;getRowLeft()I")
    ))
    public void listBackground(DrawContext instance, Identifier texture, int x, int y, float u, float v, int width, int height, int textureWidth, int textureHeight){
        if(no){instance.drawTexture(BlueClient.background,x,y,u,v,width,height,textureWidth,textureHeight);}
        else {instance.drawTexture(texture,x,y,u,v,width,height,textureWidth,textureHeight);}
    }
}
