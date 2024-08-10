package cn.ksmcbrigade.bc.hacks;

import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Hack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;

import java.io.IOException;

public class NoFall extends Hack {
    public NoFall() throws IOException, NoSuchFieldException, IllegalAccessException {
        super(Category.MOVE, "NoFall",false, GLFW.GLFW_KEY_N);
    }

    @Override
    public void playerTick(MinecraftClient MC, @Nullable PlayerEntity player) {
        if(player==null) return;
        if(player.isCreative()) return;
        if(!player.getAbilities().allowFlying && player.fallDistance<=(player.isFallFlying()?1:2)) return;
        if(player.isFallFlying() && player.isSneaking() && !(player.getVelocity().y<-0.5)) return;
        if(MC.getNetworkHandler()==null) return;
        MC.getNetworkHandler().sendPacket(new PlayerMoveC2SPacket.OnGroundOnly(true));
    }
}
