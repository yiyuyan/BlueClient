package cn.ksmcbrigade.bc.mixin.feature;

import cn.ksmcbrigade.bc.BlueClient;
import cn.ksmcbrigade.bc.hack.Hack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingMixin {
    @Inject(method = "hasStatusEffect",at = @At("RETURN"),cancellable = true)
    public void effect(StatusEffect effect, CallbackInfoReturnable<Boolean> cir){
        Hack nbbo = BlueClient.config.getHack("AntiBlind");
        Hack nbdo = BlueClient.config.getHack("AntiDarkness");
        Hack nno = BlueClient.config.getHack("AntiNausea");
        if((effect.equals(StatusEffects.BLINDNESS) && nbbo!=null && nbbo.enabled) || (effect.equals(StatusEffects.DARKNESS) && nbdo!=null && nbdo.enabled) || (effect.equals(StatusEffects.NAUSEA) && nno!=null && nno.enabled)) cir.setReturnValue(false);
    }
}
