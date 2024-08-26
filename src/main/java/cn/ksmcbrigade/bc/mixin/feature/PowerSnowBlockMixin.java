package cn.ksmcbrigade.bc.mixin.feature;

import cn.ksmcbrigade.bc.BlueClient;
import cn.ksmcbrigade.bc.hack.Hack;
import net.minecraft.block.PowderSnowBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PowderSnowBlock.class)
public class PowerSnowBlockMixin {
    @Inject(method = "canWalkOnPowderSnow",at = @At("RETURN"),cancellable = true)
    private static void can(Entity entity, CallbackInfoReturnable<Boolean> cir){
        Hack ss = BlueClient.config.getHack("SnowShoe");
        if(MinecraftClient.getInstance().player!=null && entity.getId()== MinecraftClient.getInstance().player.getId() && ss!=null && ss.enabled){
            cir.setReturnValue(true);
        }
    }
}
