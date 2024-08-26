package cn.ksmcbrigade.bc.hacks;

import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Config;
import cn.ksmcbrigade.bc.hack.Hack;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

public class BoatFly extends Hack {
    public BoatFly() throws IOException, NoSuchFieldException, IllegalAccessException {
        super(Category.MOVE, BoatFly.class.getSimpleName(),false,-1,new Config("BoatFly",get()));
    }

    public static JsonObject get(){
        JsonObject object = new JsonObject();
        object.addProperty("block",0.3D);
        return object;
    }

    @Override
    public void playerTick(MinecraftClient MC, @Nullable PlayerEntity player) {
        if(player==null){
            return;
        }
        if(player.getVehicle()==null){
            return;
        }
        Entity vehicle = player.getVehicle();
        if(!vehicle.getType().equals(EntityType.BOAT)){
            return;
        }
        if(!MC.options.jumpKey.isPressed()){
            return;
        }
        JsonElement dm = getConfig().get("block");
        vehicle.setVelocity(new Vec3d(0,dm==null?0.3D:dm.getAsDouble(),0));
    }
}
