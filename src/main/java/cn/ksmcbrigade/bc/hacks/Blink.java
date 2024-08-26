package cn.ksmcbrigade.bc.hacks;

import cn.ksmcbrigade.bc.BlueClient;
import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Hack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;

import java.io.IOException;

public class Blink extends Hack {

    public Vec3d pos = Vec3d.ZERO;
    public float heal = 20.0F;

    public Blink() throws IOException, NoSuchFieldException, IllegalAccessException {
        super(Category.MOVE, Blink.class.getSimpleName(),false, GLFW.GLFW_KEY_H);
    }

    @Override
    public void enabled(MinecraftClient MC) throws Exception {
        try {
            this.pos = MC.player.getPos();
            this.heal = MC.player.getHealth();
        }
        catch (Exception e){
            BlueClient.LOGGER.error("error in enabling the blink hack.",e);
            this.setEnabled(false);
        }
    }
}
