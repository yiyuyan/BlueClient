package cn.ksmcbrigade.bc.hacks;

import cn.ksmcbrigade.bc.BlueClient;
import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Config;
import cn.ksmcbrigade.bc.hack.Hack;
import com.google.gson.JsonObject;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.Objects;

public class Spider extends Hack {

    public Spider() throws IOException, NoSuchFieldException, IllegalAccessException {
        super(Category.MOVE, Spider.class.getSimpleName(),false);
    }

    @Override
    public void playerTick(MinecraftClient MC, @Nullable PlayerEntity player) throws Exception {
        if(player==null) return;
        if(!player.horizontalCollision) return;
        Vec3d velocity = player.getVelocity();
        if(velocity.y >= 0.2) return;
        player.setVelocity(velocity.x,0.2,velocity.z);
    }
}
