package cn.ksmcbrigade.bc.mixin.feature;

import cn.ksmcbrigade.bc.BlueClient;
import cn.ksmcbrigade.bc.hack.Hack;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Inject(method = "getFov",at = @At("RETURN"), cancellable = true)
    public void fov(Camera camera, float tickDelta, boolean changingFov, CallbackInfoReturnable<Double> cir){
        Hack nbbo = BlueClient.config.getHack("Zoom");
        if(nbbo!=null && nbbo.enabled && BlueClient.temp.zoom){
            cir.setReturnValue(cir.getReturnValue()/nbbo.getConfig().data.get("fov").getAsFloat());
        }
    }
}
