package cn.ksmcbrigade.bc.hacks;

import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Hack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

public class AutoSpring extends Hack {

    private boolean or = false;

    public AutoSpring() throws IOException, NoSuchFieldException, IllegalAccessException {
        super(Category.MOVE, AutoSpring.class.getSimpleName(),false);
    }

    @Override
    public void enabled(MinecraftClient MC) throws Exception {
        or = MC.options.sprintKey.isPressed();
    }

    @Override
    public void playerTick(MinecraftClient MC, @Nullable PlayerEntity player) {
        MC.options.sprintKey.setPressed(true);
    }

    @Override
    public void disabled(MinecraftClient MC) {
        MC.options.sprintKey.setPressed(or);
    }
}
