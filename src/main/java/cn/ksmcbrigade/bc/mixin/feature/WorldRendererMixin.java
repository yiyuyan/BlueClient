package cn.ksmcbrigade.bc.mixin.feature;

import cn.ksmcbrigade.bc.BlueClient;
import cn.ksmcbrigade.bc.hack.Hack;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
    @Inject(method = "hasBlindnessOrDarkness",at = @At("RETURN"), cancellable = true)
    public void blind(Camera camera, CallbackInfoReturnable<Boolean> cir){
        Hack nbbo = BlueClient.config.getHack("AntiBlind");
        Hack nbdo = BlueClient.config.getHack("AntiDarkness");
        if((nbbo!=null && nbbo.enabled) || (nbdo!=null && nbdo.enabled)) cir.setReturnValue(false);
    }

    @Inject(method = {"tickRainSplashing"},at = @At("HEAD"),cancellable = true)
    public void weather(CallbackInfo ci){
        Hack nbbo = BlueClient.config.getHack("NoWeather");
        if(nbbo!=null && nbbo.enabled){
            ci.cancel();
        }
    }
}
