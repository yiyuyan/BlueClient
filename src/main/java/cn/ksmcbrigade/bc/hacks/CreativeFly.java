package cn.ksmcbrigade.bc.hacks;

import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Hack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.network.packet.c2s.play.UpdatePlayerAbilitiesC2SPacket;
import net.minecraft.world.GameMode;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;

import java.io.IOException;

public class CreativeFly extends Hack {
    public CreativeFly() throws IOException, NoSuchFieldException, IllegalAccessException {
        super(Category.MOVE, "CreativeFly",false, GLFW.GLFW_KEY_G);
    }

    @Override
    public void enabled(MinecraftClient MC) {
        if(MC.player==null) return;
        if(MC.player.networkHandler==null) return;
        MC.player.getAbilities().allowFlying = true;
        MC.player.networkHandler.sendPacket(new UpdatePlayerAbilitiesC2SPacket(MC.player.getAbilities()));
    }

    @Override
    public void playerTick(MinecraftClient MC, @Nullable PlayerEntity player) {
        if(player==null) return;
        player.getAbilities().allowFlying = true;
    }

    @Override
    public void disabled(MinecraftClient MC) {
        if(MC.player==null) return;
        if(MC.player.networkHandler==null) return;
        if(MC.interactionManager==null) return;
        if(MC.interactionManager.getCurrentGameMode().isCreative() || MC.interactionManager.getCurrentGameMode().equals(GameMode.SPECTATOR)) return;
        MC.player.getAbilities().allowFlying = false;
        MC.player.networkHandler.sendPacket(new UpdatePlayerAbilitiesC2SPacket(MC.player.getAbilities()));
    }
}
