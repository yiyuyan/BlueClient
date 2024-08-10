package cn.ksmcbrigade.bc.hacks;

import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Hack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

public class AutoMidClick extends Hack {

    private int timer = 6;

    public AutoMidClick() throws IOException, NoSuchFieldException, IllegalAccessException {
        super(Category.COMBAT, AutoMidClick.class.getSimpleName(),false);
    }

    @Override
    public void playerTick(MinecraftClient MC, @Nullable PlayerEntity player) {
        if(timer==0 && MC.interactionManager!=null){
            MC.options.pickItemKey.setPressed(!MC.options.pickItemKey.isPressed());
            timer = 6;
        }
        else{
            timer--;
        }
    }

    @Override
    public void disabled(MinecraftClient MC) {
        MC.options.pickItemKey.setPressed(false);
    }
}
