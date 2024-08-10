package cn.ksmcbrigade.bc.hacks;

import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Hack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;

import java.io.IOException;

public class FullBright extends Hack {

    private double gamma;

    public FullBright() throws IOException, NoSuchFieldException, IllegalAccessException {
        super(Category.RENDER, "FullBright",false, GLFW.GLFW_KEY_C);
    }

    @Override
    public void enabled(MinecraftClient MC) {
        this.gamma = MC.options.getGamma().getValue();
    }

    @Override
    public void playerTick(MinecraftClient MC, @Nullable PlayerEntity player) {
        MC.options.getGamma().setValue(3000.0D);
    }

    @Override
    public void disabled(MinecraftClient MC) {
        MC.options.getGamma().setValue(gamma);
    }
}
