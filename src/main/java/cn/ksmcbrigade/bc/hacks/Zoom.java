package cn.ksmcbrigade.bc.hacks;

import cn.ksmcbrigade.bc.BlueClient;
import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Config;
import cn.ksmcbrigade.bc.hack.Hack;
import com.google.gson.JsonObject;
import org.lwjgl.glfw.GLFW;

import java.io.IOException;

public class Zoom extends Hack {
    public Zoom() throws IOException, NoSuchFieldException, IllegalAccessException {
        super(Category.RENDER, Zoom.class.getSimpleName(),false,-1,get());
    }

    public static Config get() throws IOException {
        JsonObject object = new JsonObject();
        object.addProperty("fov",10F);
        return new Config("HighJump",object);
    }

    @Override
    public void keyInput(int key, boolean screen) {
        if(screen) return;
        if(key== GLFW.GLFW_KEY_V){
            BlueClient.temp.zoom = !BlueClient.temp.zoom;
        }
    }
}
