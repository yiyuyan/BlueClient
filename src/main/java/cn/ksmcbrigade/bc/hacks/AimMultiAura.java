package cn.ksmcbrigade.bc.hacks;

import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Config;
import cn.ksmcbrigade.bc.hack.Hack;
import com.google.gson.JsonObject;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.math.Box;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;

import java.io.IOException;

public class AimMultiAura extends Hack {

    private boolean sneak = false;

    public AimMultiAura() throws IOException, NoSuchFieldException, IllegalAccessException {
        super(Category.COMBAT, AimMultiAura.class.getSimpleName(),false, GLFW.GLFW_KEY_K,get());
    }

    @Override
    public void playerTick(MinecraftClient MC, @Nullable PlayerEntity player) {
        if(player==null) return;
        if(player.getWorld()==null) return;
        if(MC.interactionManager==null) return;
        player.getWorld().getEntitiesByClass(Entity.class,new Box(player.getPos(),player.getPos()).expand(this.getConfig().data.get("reach").getAsInt()),Entity::isAlive).stream()
                .filter(e -> e.getId()!=player.getId())
                .filter(Entity::isAttackable)
                .toList()
                .forEach(e -> {
                    float[] yp = getAngleToEntity(e,player);
                    player.setYaw(yp[0]);
                    player.setPitch(yp[1]);
                    if(MC.getNetworkHandler()!=null) MC.getNetworkHandler().sendPacket(new PlayerMoveC2SPacket.LookAndOnGround(yp[0],yp[1],player.isOnGround()));
                    MC.interactionManager.attackEntity(player,e);
                });
    }

    public static Config get() throws IOException {
        JsonObject object = new JsonObject();
        object.addProperty("reach",5D);
        return new Config("AimMultiAura",object);
    }

    public static float[] getAngleToEntity(Entity targetEntity, PlayerEntity player) {
        double dX = targetEntity.getX() - player.getX();
        double dY = targetEntity.getY() - player.getY();
        double dZ = targetEntity.getZ() - player.getZ();
        double distanceXZ = Math.sqrt(dX * dX + dZ * dZ);
        float yaw = (float) Math.toDegrees(Math.atan2(dZ, dX)) - 90.0F;
        float pitch = (float) -Math.toDegrees(Math.atan2(dY, distanceXZ));
        return new float[] {yaw, pitch};
    }
}
