package cn.ksmcbrigade.bc.mixin.feature;

import cn.ksmcbrigade.bc.BlueClient;
import cn.ksmcbrigade.bc.hack.Hack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.gl.PostEffectProcessor;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(WorldRenderer.class)
public abstract class WorldRendererMixin {
    @Shadow public abstract boolean canDrawEntityOutlines();

    @Shadow @Final private MinecraftClient client;

    @Shadow @Nullable private PostEffectProcessor entityOutlinePostProcessor;

    @Unique
    private boolean chams = false;

    @Inject(method = "hasBlindnessOrDarkness",at = @At("RETURN"), cancellable = true)
    public void blind(Camera camera, CallbackInfoReturnable<Boolean> cir){
        Hack nbbo = BlueClient.config.getHack("AntiBlind");
        Hack nbdo = BlueClient.config.getHack("AntiDarkness");
        if((nbbo!=null && nbbo.enabled) || (nbdo!=null && nbdo.enabled)) cir.setReturnValue(false);
    }

    @Inject(method = {"tickRainSplashing"},at = @At("HEAD"),cancellable = true)
    public void weather(CallbackInfo ci){
        Hack nbbo = BlueClient.config.getHack("NoWeather");
        if(nbbo!=null && nbbo.enabled){
            ci.cancel();
        }
    }

    /*@Redirect(method = "render",at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/WorldRenderer;canDrawEntityOutlines()Z"))
    public boolean renderOutline(WorldRenderer instance){
        Hack nbbo = BlueClient.config.getHack("Chams");
        if(nbbo!=null && nbbo.enabled){
            return true;
        }
        return instance.canDrawEntityOutlines();
    }

    @Redirect(method = "render",at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;hasOutline(Lnet/minecraft/entity/Entity;)Z"))
    public boolean hasOutline(MinecraftClient instance, Entity entity){
        Hack nbbo = BlueClient.config.getHack("Chams");
        if(nbbo!=null && nbbo.enabled){
            this.chams = true;
            return true;
        }
        return instance.hasOutline(entity);
    }*/
}
