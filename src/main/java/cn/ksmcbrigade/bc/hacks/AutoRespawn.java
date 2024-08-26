package cn.ksmcbrigade.bc.hacks;

import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Hack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.network.packet.c2s.play.ClientStatusC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.network.packet.s2c.play.PlayerActionResponseS2CPacket;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.io.IOException;
import java.util.Objects;

public class AutoRespawn extends Hack {

    private boolean or = false;

    public AutoRespawn() throws IOException, NoSuchFieldException, IllegalAccessException {
        super(Category.COMBAT, AutoRespawn.class.getSimpleName(),false);
    }

    @Override
    public void playerTick(MinecraftClient MC, @Nullable PlayerEntity player) {
        if(player!=null && !player.isAlive()){
            Objects.requireNonNull(MC.getNetworkHandler()).sendPacket(new ClientStatusC2SPacket(ClientStatusC2SPacket.Mode.PERFORM_RESPAWN));
        }
    }
}
