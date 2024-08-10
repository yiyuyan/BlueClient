package cn.ksmcbrigade.bc.mixin.feature;

import cn.ksmcbrigade.bc.BlueClient;
import cn.ksmcbrigade.bc.hack.Hack;
import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RenderTickCounter.class)
public class TimerMixin {

    @Mutable
    @Shadow @Final private float tickTime;
    @Unique
    private float time = 20.0F;

    @Inject(method = "<init>",at = @At("TAIL"))
    public void init(float tps, long timeMillis, CallbackInfo ci){
        this.time = tps;
    }

    @Inject(method = "beginRenderTick",at = @At("HEAD"))
    public void timer(long timeMillis, CallbackInfoReturnable<Integer> cir){
        Hack timer = BlueClient.config.getHack("Timer");
        this.tickTime = 1000.0F / (this.time*((timer!=null && timer.enabled)?BlueClient.config.getHack("Timer").getConfig().data.get("timer").getAsFloat():1.0F));
    }
}
