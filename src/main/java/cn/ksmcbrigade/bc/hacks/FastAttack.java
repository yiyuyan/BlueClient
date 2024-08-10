package cn.ksmcbrigade.bc.hacks;

import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Hack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;

import java.io.IOException;

public class FastAttack extends Hack {

    public FastAttack() throws IOException, NoSuchFieldException, IllegalAccessException {
        super(Category.COMBAT, FastAttack.class.getSimpleName(),false);
    }

    @Override
    public void playerTick(MinecraftClient MC, @Nullable PlayerEntity player) {
        MC.attackCooldown = 0;
    }
}
