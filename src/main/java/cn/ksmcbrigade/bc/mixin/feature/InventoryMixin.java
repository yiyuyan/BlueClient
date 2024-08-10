package cn.ksmcbrigade.bc.mixin.feature;

import cn.ksmcbrigade.bc.BlueClient;
import cn.ksmcbrigade.bc.hack.Hack;
import net.minecraft.entity.player.PlayerInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerInventory.class)
public class InventoryMixin {
    @Inject(method = "swapSlotWithHotbar",at = @At("HEAD"),cancellable = true)
    public void swap(int slot, CallbackInfo ci){
        Hack nbbo = BlueClient.config.getHack("Zoom");
        if(nbbo!=null && nbbo.enabled && BlueClient.temp.zoom){
            ci.cancel();
        }
    }
}
