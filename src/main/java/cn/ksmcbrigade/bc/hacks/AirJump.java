package cn.ksmcbrigade.bc.hacks;

import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Hack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

public class AirJump extends Hack {

    public AirJump() throws IOException, NoSuchFieldException, IllegalAccessException {
        super(Category.MOVE, AirJump.class.getSimpleName(),false);
    }

    @Override
    public void playerTick(MinecraftClient MC, @Nullable PlayerEntity player) {
        if(player!=null && MC.options.jumpKey.isPressed()){
            player.jump();
        }
    }
}
