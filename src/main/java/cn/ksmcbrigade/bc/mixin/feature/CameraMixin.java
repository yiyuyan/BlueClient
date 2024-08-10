package cn.ksmcbrigade.bc.mixin.feature;

import cn.ksmcbrigade.bc.BlueClient;
import cn.ksmcbrigade.bc.hack.Hack;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.CameraSubmersionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Camera.class)
public class CameraMixin {
    @Inject(method = "getSubmersionType",at = @At("RETURN"), cancellable = true)
    public void under(CallbackInfoReturnable<CameraSubmersionType> cir){
        Hack nbbo = BlueClient.config.getHack("NoUnderOverlay");
        if(nbbo!=null && nbbo.enabled){
            cir.setReturnValue(CameraSubmersionType.NONE);
        }
    }

    @Inject(method = "clipToSpace",at = @At("HEAD"),cancellable = true)
    public void clip(double desiredCameraDistance, CallbackInfoReturnable<Double> cir){
        Hack nbbo = BlueClient.config.getHack("NoCameraClip");
        if(nbbo!=null && nbbo.enabled){
            cir.setReturnValue(desiredCameraDistance);
            cir.cancel();
        }
    }

    @ModifyVariable(method = "clipToSpace",at = @At("HEAD"),argsOnly = true)
    public double clip(double value){
        Hack nbbo = BlueClient.config.getHack("HighCameraDistance");
        if(nbbo!=null && nbbo.enabled){
            return nbbo.getConfig().data.get("reach").getAsDouble();
        }
        else{
            return value;
        }
    }

}
