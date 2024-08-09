package cn.ksmcbrigade.bc.mixin.feature;

import cn.ksmcbrigade.bc.BlueClient;
import net.minecraft.client.gui.screen.ConnectScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.network.ServerAddress;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MultiplayerScreen.class)
public abstract class ServerListScreenMixin extends Screen {
    protected ServerListScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "init",at = @At("TAIL"))
    public void addButton(CallbackInfo ci){
        if(this.client==null) return;
        ButtonWidget button = ButtonWidget.builder(Text.literal("LastServer"), ref-> {
            if(this.client!=null){
                ConnectScreen.connect(this, this.client, ServerAddress.parse(BlueClient.temp.lastServer.address),BlueClient.temp.lastServer,false);
            }
        }).size(100,20).position(width-154,10).build();
        button.active = BlueClient.temp.lastServer!=null;
        this.addDrawableChild(button);
    }
}
