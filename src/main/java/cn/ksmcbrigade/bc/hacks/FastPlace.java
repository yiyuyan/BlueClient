package cn.ksmcbrigade.bc.hacks;

import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Hack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;

import java.io.IOException;

public class FastPlace extends Hack {

    public FastPlace() throws IOException, NoSuchFieldException, IllegalAccessException {
        super(Category.BLOCK, FastPlace.class.getSimpleName(),false, GLFW.GLFW_KEY_B);
    }

    @Override
    public void playerTick(MinecraftClient MC, @Nullable PlayerEntity player) {
        MC.itemUseCooldown = 0;
    }
}
