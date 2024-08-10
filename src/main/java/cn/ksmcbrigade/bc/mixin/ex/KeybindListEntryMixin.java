package cn.ksmcbrigade.bc.mixin.ex;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.option.ControlsListWidget;
import net.minecraft.client.gui.screen.option.KeybindsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ControlsListWidget.KeyBindingEntry.class)
public class KeybindListEntryMixin {

    @Shadow private boolean duplicate;

    @Shadow @Final private ButtonWidget editButton;

    @Shadow @Final private KeyBinding binding;

    @Inject(method = "update",at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/ButtonWidget;setMessage(Lnet/minecraft/text/Text;)V",shift = At.Shift.BEFORE),slice = @Slice(
            from = @At(value = "INVOKE", target = "Lnet/minecraft/text/MutableText;append(Lnet/minecraft/text/Text;)Lnet/minecraft/text/MutableText;"),
            to = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/ButtonWidget;setTooltip(Lnet/minecraft/client/gui/tooltip/Tooltip;)V")),
            cancellable = true)
    public void update(CallbackInfo ci){
        this.duplicate = false;
        this.editButton.setTooltip(null);
        if ((MinecraftClient.getInstance().currentScreen instanceof KeybindsScreen screen) && screen.selectedKeyBinding == this.binding) {
            this.editButton.setMessage(Text.literal("> ").append(this.editButton.getMessage().copy().formatted(new Formatting[]{Formatting.WHITE, Formatting.UNDERLINE})).append(" <").formatted(Formatting.YELLOW));
        }
        ci.cancel();
    }
}
