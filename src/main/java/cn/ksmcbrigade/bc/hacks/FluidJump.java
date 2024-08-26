package cn.ksmcbrigade.bc.hacks;

import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Hack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;

import java.io.IOException;

public class FluidJump extends Hack {

    public int tick = 10;

    public FluidJump() throws IOException, NoSuchFieldException, IllegalAccessException {
        super(Category.MOVE,FluidJump.class.getSimpleName(),false, GLFW.GLFW_KEY_J);
    }

    @Override
    public void playerTick(MinecraftClient MC, @Nullable PlayerEntity player) {
        if(MC.options.sneakKey.isPressed()) return;
        if(player==null) return;
        if(player.isTouchingWater()){
            Vec3d vec3 = player.getVelocity();
            player.setVelocity(0,0.11,0);
            tick = 0;
            return;
        }
        Vec3d vec3 = player.getVelocity();
        if(tick==0){
            player.setVelocity(0,0.3,0);
        }
        else if(tick==1){
            player.setVelocity(0,0,0);
        }
        tick++;
    }
}
