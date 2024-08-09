package cn.ksmcbrigade.bc.mixin;

import net.minecraft.util.ModStatus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ModStatus.class)
public class ModStatusMixin {
    @Inject(method = "isModded",at = @At("RETURN"), cancellable = true)
    public void noModded(CallbackInfoReturnable<Boolean> cir){
        cir.setReturnValue(false);
    }
}
