package cn.ksmcbrigade.bc.mixin.ex;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.DeathScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(DeathScreen.class)
public abstract class DeathScreenMixin extends Screen {

    @Shadow @Final private List<ButtonWidget> buttons;

    protected DeathScreenMixin(Text title) {
        super(title);
    }


    @Inject(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    shift = At.Shift.BEFORE,
                    target = "net/minecraft/client/gui/screen/Screen.render (Lnet/minecraft/client/gui/DrawContext;IIF)V"
            )
    )
    public void render(DrawContext guiGraphics, int i, int j, float f, CallbackInfo ci){
        assert this.client != null;
        assert this.client.player != null;
        String xyz = "[X: " + client.player.getBlockPos().getX() + " Y: " + client.player.getBlockPos().getY() + " Z: " + client.player.getBlockPos().getZ() + "]";
        guiGraphics.drawTextWithShadow(this.textRenderer, xyz, this.width / 2, this.height / 4 + 145, 16777215);
    }

    @Inject(
            method = "init",
            at = @At(
                    value = "FIELD",
                    shift = At.Shift.BEFORE,
                    target = "net/minecraft/client/gui/screen/DeathScreen.buttons : Ljava/util/List;",
                    ordinal = 2
            )
    )
    protected void init(CallbackInfo ci){
        String xyz =  this.client.player.getBlockPos().getX() + " " + client.player.getBlockPos().getY() + " " + client.player.getBlockPos().getZ();
        this.buttons.add(
                this.addDrawableChild(
                        new ButtonWidget.Builder(
                                Text.literal("Copy Location To Clipboard"),
                                button -> this.client.keyboard.setClipboard(xyz)
                        ).dimensions(
                                this.width / 2 - 100, this.height / 4 + 120, 200, 20
                        ).build()
                )
        );
    }
}
