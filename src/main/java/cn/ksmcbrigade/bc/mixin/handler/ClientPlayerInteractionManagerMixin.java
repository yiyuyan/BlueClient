package cn.ksmcbrigade.bc.mixin.handler;

import cn.ksmcbrigade.bc.BlueClient;
import cn.ksmcbrigade.bc.hack.Hack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(ClientPlayerInteractionManager.class)
public class ClientPlayerInteractionManagerMixin {
    @Shadow @Final private MinecraftClient client;

    @Inject(method = {"updateBlockBreakingProgress"},at = @At(value = "HEAD"))
    public void breakEvent(BlockPos pos, Direction direction, CallbackInfoReturnable<Boolean> cir){
        BlueClient.config.getEnables().forEach(h -> {
            try {
                h.breakEvent(MinecraftClient.getInstance(), this.client.interactionManager,pos,direction);
            } catch (Exception e) {
                BlueClient.LOGGER.error("error in breaking the block event.",e);
            }
        });
    }

    @Inject(method = {"breakBlock"},at = @At(value = "HEAD"))
    public void breakEvent(BlockPos pos, CallbackInfoReturnable<Boolean> cir){
        BlueClient.config.getEnables().forEach(h -> {
            try {
                assert this.client.player != null;
                h.breakEvent(MinecraftClient.getInstance(), this.client.interactionManager,pos,this.client.player.getMovementDirection());
            } catch (Exception e) {
                BlueClient.LOGGER.error("error in breaking the block event.",e);
            }
        });
    }

    @Inject(method = "getReachDistance",at = @At("RETURN"),cancellable = true)
    public void reach(CallbackInfoReturnable<Float> cir){
        Hack reach = BlueClient.config.getHack("Reach");
        if(reach!=null && reach.enabled){
            cir.setReturnValue(Math.max(cir.getReturnValue(), Objects.requireNonNull(reach.getConfig().get("reach")).getAsFloat()));
        }
    }

    @Inject(method = "hasExtendedReach",at = @At("HEAD"),cancellable = true)
    public void reach2(CallbackInfoReturnable<Boolean> cir){
        Hack reach = BlueClient.config.getHack("Reach");
        if(reach!=null && reach.enabled){
            cir.setReturnValue(true);
        }
    }
}
