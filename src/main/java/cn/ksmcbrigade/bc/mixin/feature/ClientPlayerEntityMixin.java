package cn.ksmcbrigade.bc.mixin.feature;

import cn.ksmcbrigade.bc.BlueClient;
import cn.ksmcbrigade.bc.hack.Hack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {

    @Redirect(method = "updateNausea",at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;closeHandledScreen()V"))
    public void portal(ClientPlayerEntity instance){
        Hack nbbo = BlueClient.config.getHack("InvPortal");
        if(nbbo!=null && nbbo.enabled) return;
        instance.closeHandledScreen();
    }

    @Redirect(method = "updateNausea",at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;setScreen(Lnet/minecraft/client/gui/screen/Screen;)V"))
    public void portal(MinecraftClient instance, Screen screen){
        Hack nbbo = BlueClient.config.getHack("InvPortal");
        if(nbbo!=null && nbbo.enabled) return;
        instance.setScreen(screen);
    }
}
