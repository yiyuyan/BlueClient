package cn.ksmcbrigade.bc.hacks;

import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Hack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

public class AutoWalk extends Hack {

    public Vec3d pos = Vec3d.ZERO;
    public float heal = 20.0F;

    public AutoWalk() throws IOException, NoSuchFieldException, IllegalAccessException {
        super(Category.MOVE, AutoWalk.class.getSimpleName());
    }

    @Override
    public void playerTick(MinecraftClient MC, @Nullable PlayerEntity player) throws Exception {
        MC.options.forwardKey.setPressed(true);
    }
}
