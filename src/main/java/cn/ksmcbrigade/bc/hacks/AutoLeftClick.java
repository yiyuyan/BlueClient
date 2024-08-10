package cn.ksmcbrigade.bc.hacks;

import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Hack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import java.io.IOException;

public class AutoLeftClick extends Hack {

    private int timer = 6;

    public AutoLeftClick() throws IOException, NoSuchFieldException, IllegalAccessException {
        super(Category.COMBAT, AutoLeftClick.class.getSimpleName(),false);
    }

    @Override
    public void playerTick(MinecraftClient MC, @Nullable PlayerEntity player) {
        if(timer==0 && MC.interactionManager!=null){
            MC.options.attackKey.setPressed(!MC.options.attackKey.isPressed());
            timer = 6;
        }
        else{
            timer--;
        }
    }

    @Override
    public void disabled(MinecraftClient MC) {
        MC.options.attackKey.setPressed(false);
    }
}
