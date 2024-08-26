package cn.ksmcbrigade.bc.mixin.handler;

import cn.ksmcbrigade.bc.BlueClient;
import cn.ksmcbrigade.bc.hack.Hack;
import com.mojang.datafixers.util.Either;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.Unit;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(PlayerEntity.class)
public abstract class PlayerMixin extends LivingEntity {
    @Shadow public abstract Either<PlayerEntity.SleepFailureReason, Unit> trySleep(BlockPos pos);

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

    @Inject(method = "attack",at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;getAttributeValue(Lnet/minecraft/entity/attribute/EntityAttribute;)D",shift = At.Shift.BEFORE))
    public void attack(Entity target, CallbackInfo ci){
        Hack nbbo = BlueClient.config.getHack("TPClick");
        if(nbbo!=null && nbbo.enabled){
            this.setPos(target.getX(),target.getY(),target.getZ());
            try {
                Objects.requireNonNull(MinecraftClient.getInstance().getNetworkHandler()).sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(target.getX(),target.getY(),target.getZ(),this.isOnGround()));
            } catch (Exception e) {
                BlueClient.LOGGER.error("error in attack mixin",e);
            }
        }
    }
}
