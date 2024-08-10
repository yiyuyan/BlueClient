package cn.ksmcbrigade.bc.hacks;

import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Config;
import cn.ksmcbrigade.bc.hack.Hack;
import com.google.gson.JsonObject;
import org.lwjgl.glfw.GLFW;

import java.io.IOException;

public class Timer extends Hack {
    public Timer() throws IOException, NoSuchFieldException, IllegalAccessException {
        super(Category.MOVE, "Timer",false, GLFW.GLFW_KEY_Y,get());
    }

    public static Config get() throws IOException {
        JsonObject object = new JsonObject();
        object.addProperty("timer",2.1F);
        return new Config("Timer",object);
    }
}
