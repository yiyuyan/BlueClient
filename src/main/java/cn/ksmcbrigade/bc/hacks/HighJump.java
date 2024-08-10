package cn.ksmcbrigade.bc.hacks;

import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Config;
import cn.ksmcbrigade.bc.hack.Hack;
import com.google.gson.JsonObject;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

public class HighJump extends Hack {

    public HighJump() throws IOException, NoSuchFieldException, IllegalAccessException {
        super(Category.MOVE, HighJump.class.getSimpleName(),false,-1,get());
    }

    public static Config get() throws IOException {
        JsonObject object = new JsonObject();
        object.addProperty("block",6F);
        return new Config("HighJump",object);
    }
}
