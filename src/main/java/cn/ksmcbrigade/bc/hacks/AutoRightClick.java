package cn.ksmcbrigade.bc.hacks;

import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Hack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

public class AutoRightClick extends Hack {

    private int timer = 6;

    public AutoRightClick() throws IOException, NoSuchFieldException, IllegalAccessException {
        super(Category.COMBAT, AutoRightClick.class.getSimpleName(),false);
    }

    @Override
    public void playerTick(MinecraftClient MC, @Nullable PlayerEntity player) {
        if(timer==0 && MC.interactionManager!=null){
            MC.options.useKey.setPressed(!MC.options.useKey.isPressed());
            timer = 6;
        }
        else{
            timer--;
        }
    }

    @Override
    public void disabled(MinecraftClient MC) {
        MC.options.useKey.setPressed(false);
    }
}
