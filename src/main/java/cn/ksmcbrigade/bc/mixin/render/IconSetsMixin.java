package cn.ksmcbrigade.bc.mixin.render;

import cn.ksmcbrigade.bc.BlueClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Icons;
import net.minecraft.resource.InputSupplier;
import net.minecraft.resource.ResourcePack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.io.InputStream;

@Mixin(Icons.class)
public class IconSetsMixin {

    /**
     * @author KSmc_brigade
     * @reason custom the window icon
     */
    @Inject(method = "getIcon",at = @At("RETURN"),cancellable = true,locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    public void icon(ResourcePack resourcePack, String fileName, CallbackInfoReturnable<InputSupplier<InputStream>> cir, String[] strings, InputSupplier<InputStream> inputSupplier){
        if(MinecraftClient.getInstance().getResourceManager()==null) return;
        cir.setReturnValue(()-> MinecraftClient.getInstance().getResourceManager().getResourceOrThrow(new Identifier(BlueClient.MOD_ID,fileName)).getInputStream());
    }
}
