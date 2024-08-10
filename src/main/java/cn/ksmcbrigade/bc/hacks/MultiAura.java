package cn.ksmcbrigade.bc.hacks;

import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Config;
import cn.ksmcbrigade.bc.hack.Hack;
import com.google.gson.JsonObject;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;

import java.io.IOException;

public class MultiAura extends Hack {

    private boolean sneak = false;

    public MultiAura() throws IOException, NoSuchFieldException, IllegalAccessException {
        super(Category.COMBAT, MultiAura.class.getSimpleName(),false, GLFW.GLFW_KEY_R,get());
    }

    @Override
    public void playerTick(MinecraftClient MC, @Nullable PlayerEntity player) {
        if(player==null) return;
        if(player.getWorld()==null) return;
        if(MC.interactionManager==null) return;
        player.getWorld().getEntitiesByClass(Entity.class,new Box(player.getPos(),player.getPos()).expand(this.getConfig().data.get("reach").getAsDouble()),Entity::isAlive).stream()
                .filter(e -> e.getId()!=player.getId())
                .filter(Entity::isAttackable)
                .toList()
                .forEach(e -> MC.interactionManager.attackEntity(player,e));
    }

    public static Config get() throws IOException {
        JsonObject object = new JsonObject();
        object.addProperty("reach",5D);
        return new Config("MultiAura",object);
    }
}
