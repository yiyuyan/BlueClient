package cn.ksmcbrigade.bc.hacks;

import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Config;
import cn.ksmcbrigade.bc.hack.Hack;
import com.google.gson.JsonObject;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

public class AutoLog extends Hack {

    public AutoLog() throws IOException, NoSuchFieldException, IllegalAccessException {
        super(Category.COMBAT, AutoLog.class.getSimpleName(),false,-1,get());
    }

    public static Config get() throws IOException {
        JsonObject object = new JsonObject();
        object.addProperty("heal",4F);
        return new Config("AutoLog",object);
    }

    @Override
    public void playerTick(MinecraftClient MC, @Nullable PlayerEntity player) throws Exception {
        if(player!=null && player.getHealth()<=getConfig().get("heal").getAsFloat()){
            MC.getNetworkHandler().getConnection().disconnect(Text.of("AutoLog"));
            this.setEnabled(false);
        }
    }
}
