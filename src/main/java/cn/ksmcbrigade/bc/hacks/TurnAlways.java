package cn.ksmcbrigade.bc.hacks;

import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Hack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class TurnAlways extends Hack {

    Random random = new Random();

    public TurnAlways() throws IOException, NoSuchFieldException, IllegalAccessException {
        super(Category.FUN, TurnAlways.class.getSimpleName(),false, GLFW.GLFW_KEY_H);
    }

    @Override
    public void worldTick(MinecraftClient MC, @Nullable World world) {
        if(MC.player!=null){
            Objects.requireNonNull(MC.getNetworkHandler()).sendPacket(new PlayerMoveC2SPacket.LookAndOnGround(random.nextInt(-180,180),random.nextInt(-180,180),MC.player.isOnGround()));
        }
    }
}
