package cn.ksmcbrigade.bc.mixin;

import cn.ksmcbrigade.bc.BlueClient;
import cn.ksmcbrigade.bc.utils.DarkUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.util.Icons;
import net.minecraft.client.util.Window;
import net.minecraft.resource.DefaultResourcePack;
import net.minecraft.server.integrated.IntegratedServer;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
    @Shadow @Nullable public abstract ClientPlayNetworkHandler getNetworkHandler();

    @Shadow @Nullable private IntegratedServer server;

    @Shadow public abstract boolean isConnectedToRealms();

    @Shadow @Nullable public abstract ServerInfo getCurrentServerEntry();

    @Shadow @Final private Window window;

    @Shadow @Final private DefaultResourcePack defaultResourcePack;

    @Shadow
    public static MinecraftClient getInstance() {
        return null;
    }

    /**
     * @author KSmc_brigade
     * @reason custom the window title
     */
    @Overwrite
    private String getWindowTitle() {
        StringBuilder stringBuilder = new StringBuilder(BlueClient.NAME);

        stringBuilder.append(" ").append("|").append(" ");
        stringBuilder.append(BlueClient.VERSION);
        ClientPlayNetworkHandler clientPlayNetworkHandler = this.getNetworkHandler();
        if (clientPlayNetworkHandler != null && clientPlayNetworkHandler.getConnection().isOpen()) {
            stringBuilder.append(" - ");
            if (this.server != null && !this.server.isRemote()) {
                stringBuilder.append(I18n.translate("title.singleplayer"));
            } else if (this.isConnectedToRealms()) {
                stringBuilder.append(I18n.translate("title.multiplayer.realms"));
            } else if (this.server == null && (this.getCurrentServerEntry() == null || !this.getCurrentServerEntry().isLocal())) {
                stringBuilder.append(I18n.translate("title.multiplayer.other"));
            } else {
                stringBuilder.append(I18n.translate("title.multiplayer.lan"));
            }
        }

        return stringBuilder.toString();
    }

    /**
     * @author KSmc_brigade
     * @reason reset the window icon
     */
    @Inject(method = "<init>",at = @At(value = "RETURN"))
    public void icon(RunArgs args, CallbackInfo ci) throws IOException {
        this.window.setIcon(this.defaultResourcePack, Icons.RELEASE);
        DarkUtils.setDark(this.window);
    }

    @Inject(method = "tick",at = @At("HEAD"))
    public void tick(CallbackInfo ci){
        BlueClient.config.getEnables().forEach(h -> {
            try {
                h.clientTick(getInstance());
            } catch (Exception e) {
                BlueClient.LOGGER.error("error in client tick",e);
            }
        });
    }

    @Inject(method = "close",at = @At("HEAD"))
    public void close(CallbackInfo ci){
        BlueClient.config.getEnables().forEach(h -> {
            try {
                h.MCClose(getInstance());
            } catch (Exception e) {
                BlueClient.LOGGER.error("error on client close time",e);
            }
        });
    }
}
