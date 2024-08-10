package cn.ksmcbrigade.bc.mixin.ex;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.SimpleOption;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;
import java.util.function.Consumer;

@Mixin(SimpleOption.class)
public class OptionInstanceMixin<T> {
    @Shadow
    T value;

    @Shadow @Final private Consumer<T> changeCallback;

    @Inject(method = "setValue",at = @At("HEAD"),cancellable = true)
    public void set(T value, CallbackInfo ci){
        if (!MinecraftClient.getInstance().isRunning()) {
            this.value = value;
        } else {
            if (!Objects.equals(this.value, value)) {
                this.value = value;
                this.changeCallback.accept(this.value);
            }
        }

        ci.cancel();
    }
}
