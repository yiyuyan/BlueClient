package cn.ksmcbrigade.bc.mixin.handler;

import cn.ksmcbrigade.bc.BlueClient;
import cn.ksmcbrigade.bc.hack.Hack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.world.World;
import net.minecraft.world.WorldProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(World.class)
public abstract class WorldMixin {
    @Shadow public abstract WorldProperties getLevelProperties();

    @Inject(method = "tickEntity",at = @At("HEAD"))
    public void tick(CallbackInfo ci){
        BlueClient.config.getEnables().forEach(h -> {
            try {
                h.worldTick(MinecraftClient.getInstance(),MinecraftClient.getInstance().world);
            } catch (Exception e) {
                BlueClient.LOGGER.error("error in world tick",e);
            }
        });
    }

    @Inject(method = "getTimeOfDay",at = @At("RETURN"),cancellable = true)
    public void time(CallbackInfoReturnable<Long> cir){
        Hack nbbo = BlueClient.config.getHack("OnlyDay");
        if(nbbo!=null && nbbo.enabled){
            cir.setReturnValue(1000L);
        }
    }

    @Inject(method = {"getRainGradient","getThunderGradient"},at = @At("RETURN"),cancellable = true)
    public void time2(CallbackInfoReturnable<Float> cir){
        Hack nbbo = BlueClient.config.getHack("NoWeather");
        if(nbbo!=null && nbbo.enabled){
            cir.setReturnValue(0F);
        }
    }
}
