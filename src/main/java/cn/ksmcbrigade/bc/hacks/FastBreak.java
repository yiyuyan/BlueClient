package cn.ksmcbrigade.bc.hacks;

import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Hack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;

import java.io.IOException;

public class FastBreak extends Hack {

    public FastBreak() throws IOException, NoSuchFieldException, IllegalAccessException {
        super(Category.BLOCK, FastBreak.class.getSimpleName(),false, GLFW.GLFW_KEY_B);
    }

    @Override
    public void playerTick(MinecraftClient MC, @Nullable PlayerEntity player) {
        if(MC.interactionManager!=null){
            MC.interactionManager.blockBreakingCooldown = 0;
        }
    }

    @Override
    public void breakEvent(MinecraftClient instance, @Nullable ClientPlayerInteractionManager interactionManager, @NotNull BlockPos blockPos, @NotNull Direction direction) throws Exception {
        if(interactionManager==null) return;
        if(instance.player==null) return;
        if(instance.crosshairTarget!=null){
            instance.getNetworkHandler().sendPacket(new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.STOP_DESTROY_BLOCK,blockPos,direction));
        }
    }
}
