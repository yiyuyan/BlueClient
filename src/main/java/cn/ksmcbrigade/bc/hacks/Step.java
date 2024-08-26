package cn.ksmcbrigade.bc.hacks;

import cn.ksmcbrigade.bc.BlueClient;
import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Config;
import cn.ksmcbrigade.bc.hack.Hack;
import com.google.gson.JsonObject;
import net.minecraft.client.MinecraftClient;

import java.io.IOException;
import java.util.Objects;

public class Step extends Hack {

    public float step;

    public Step() throws IOException, NoSuchFieldException, IllegalAccessException {
        super(Category.MOVE, Step.class.getSimpleName(),false,-1,new Config(Step.class.getSimpleName(),get()));
    }

    public static JsonObject get(){
        JsonObject object = new JsonObject();
        object.addProperty("step",2F);
        return object;
    }

    @Override
    public void enabled(MinecraftClient MC) throws Exception {
        try {
            step = MC.player.getStepHeight();
            MC.player.setStepHeight(Objects.requireNonNull(getConfig().get("step")).getAsFloat());
        } catch (Exception e) {
            BlueClient.LOGGER.error("error in enabled the step hack.",e);
            this.enabled = false;
        }
    }

    @Override
    public void disabled(MinecraftClient MC) throws Exception {
        try {
            MC.player.setStepHeight(step);
        } catch (Exception e) {
            BlueClient.LOGGER.error("error in disabled the step hack.",e);
        }
    }
}
