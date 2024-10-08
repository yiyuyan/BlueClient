package cn.ksmcbrigade.bc.hacks;

import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Config;
import cn.ksmcbrigade.bc.hack.Hack;
import com.google.gson.JsonObject;

import java.io.IOException;

public class Reach extends Hack {

    public Reach() throws IOException, NoSuchFieldException, IllegalAccessException {
        super(Category.COMBAT, Reach.class.getSimpleName(),false,-1,get());
    }

    public static Config get() throws IOException {
        JsonObject object = new JsonObject();
        object.addProperty("reach",5F);
        return new Config("Reach",object);
    }
}
