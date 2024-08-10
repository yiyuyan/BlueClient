package cn.ksmcbrigade.bc.mixin.handler;

import cn.ksmcbrigade.bc.BlueClient;
import cn.ksmcbrigade.bc.hack.Hack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerMixin extends LivingEntity {
    protected PlayerMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "tick",at = @At("HEAD"))
    public void tick(CallbackInfo ci){
        BlueClient.config.getEnables().forEach(h -> {
            try {
                h.playerTick(MinecraftClient.getInstance(), MinecraftClient.getInstance().player);
            } catch (Exception e) {
                BlueClient.LOGGER.error("error in player tick",e);
            }
        });
    }

    @Override
    protected float getJumpVelocity() {
        Hack nbbo = BlueClient.config.getHack("HighJump");
        return super.getJumpVelocity()+(nbbo!=null&&nbbo.enabled?0.1F*nbbo.getConfig().data.get("block").getAsFloat():0F);
    }
}
