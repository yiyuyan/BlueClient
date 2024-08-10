package cn.ksmcbrigade.bc.hacks;

import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Hack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

public class Sneak extends Hack {

    private boolean sneak = false;

    public Sneak() throws IOException, NoSuchFieldException, IllegalAccessException {
        super(Category.MOVE, Sneak.class.getSimpleName(),false);
    }

    @Override
    public void enabled(MinecraftClient MC) {
        this.sneak = MC.options.sneakKey.isPressed();
    }

    @Override
    public void playerTick(MinecraftClient MC, @Nullable PlayerEntity player) {
        MC.options.sneakKey.setPressed(true);
        if(player==null) return;
        player.setSneaking(true);
    }

    @Override
    public void disabled(MinecraftClient MC) {
        MC.options.sneakKey.setPressed(sneak);
        if(MinecraftClient.getInstance().player==null) return;
        MinecraftClient.getInstance().player.setSneaking(this.sneak);
    }
}
