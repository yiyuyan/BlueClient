package cn.ksmcbrigade.bc.hacks;

import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Hack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

public class AutoMine extends Hack {
    public AutoMine() throws IOException, NoSuchFieldException, IllegalAccessException {
        super(Category.BLOCK, AutoMine.class.getSimpleName(),false);
    }

    @Override
    public void playerTick(MinecraftClient MC, @Nullable PlayerEntity player) {
        MC.options.attackKey.setPressed(true);
    }

    @Override
    public void disabled(MinecraftClient MC) {
        MC.options.attackKey.setPressed(false);
    }
}
