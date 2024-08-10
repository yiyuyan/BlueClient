package cn.ksmcbrigade.bc.mixin.handler;

import cn.ksmcbrigade.bc.BlueClient;
import cn.ksmcbrigade.bc.gui.HackListScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyBinding.class)
public class KeybindingMixin {
    @Inject(method = "onKeyPressed",at = @At("HEAD"))
    private static void keyInput(InputUtil.Key key, CallbackInfo ci){
        if(key.getCode()== GLFW.GLFW_KEY_RIGHT_CONTROL && MinecraftClient.getInstance().player!=null){
            MinecraftClient.getInstance().setScreen(new HackListScreen());
        }
        BlueClient.config.getHacks().forEach(h -> {
            try {
                if(key.getCode()==h.key){
                    h.setEnabled(!h.enabled);
                    if(MinecraftClient.getInstance().player!=null) BlueClient.sendToast(h.getName()+": "+h.enabled);
                }

                h.keyInput(key.getCode(),false);
            } catch (Exception e) {
                BlueClient.LOGGER.error("error in key input tick",e);
            }
        });
    }
}
