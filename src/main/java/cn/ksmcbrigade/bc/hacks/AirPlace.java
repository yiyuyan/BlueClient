package cn.ksmcbrigade.bc.hacks;

import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Hack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.c2s.play.PlayerInteractBlockC2SPacket;
import net.minecraft.util.hit.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.Objects;

public class AirPlace extends Hack {

    public AirPlace() throws IOException, NoSuchFieldException, IllegalAccessException {
        super(Category.BLOCK, AirPlace.class.getSimpleName(),false);
    }

    @Override
    public void playerTick(MinecraftClient MC, @Nullable PlayerEntity player) throws Exception {
        if(MC.options.useKey.isPressed()){
            if(MC.interactionManager==null) return;
            if(player==null) return;
            if(MC.crosshairTarget==null) return;
            Objects.requireNonNull(MC.getNetworkHandler()).sendPacket(new PlayerInteractBlockC2SPacket(player.getActiveHand(), (BlockHitResult) MC.crosshairTarget,0));
        }
    }
}
