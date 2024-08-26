package cn.ksmcbrigade.bc.hacks;

import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Hack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.Objects;

import static net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket.Mode.START_FALL_FLYING;

public class ElytraFly extends Hack {

    private int timer;
    private final MinecraftClient MC = MinecraftClient.getInstance();

    public ElytraFly() throws IOException, NoSuchFieldException, IllegalAccessException {
        super(Category.MOVE, ElytraFly.class.getSimpleName());
    }

    @Override
    public void playerTick(MinecraftClient MC, @Nullable PlayerEntity player) throws Exception {
        if(player==null){
            return;
        }

        if(timer > 0)
            timer--;
        ItemStack chest = player.getEquippedStack(EquipmentSlot.CHEST);
        if(chest.getItem() != Items.ELYTRA)
            return;
        if(player.isFallFlying())
        {
            if(player.isTouchingWater())
            {
                sendStartStopPacket();
                return;
            }
            controlSpeed();
            controlHeight();
            return;
        }
        if(ElytraItem.isUsable(chest) && MC.options.jumpKey.isPressed())
            doInstantFly();
    }

    private void sendStartStopPacket()
    {
        ClientCommandC2SPacket packet;
        if (MC.player != null) {
            packet = new ClientCommandC2SPacket(MC.player,START_FALL_FLYING);
            Objects.requireNonNull(MC.getNetworkHandler()).getConnection().send(packet);
        }
    }

    private void controlHeight()
    {

        Vec3d v = null;
        if (MC.player != null) {
            v = MC.player.getVelocity();
        }

        if(MC.player!=null){
            if(MC.options.jumpKey.isPressed())
                MC.player.setVelocity(v.x, v.y + 0.08, v.z);
            else if(MC.options.sneakKey.isPressed())
                MC.player.setVelocity(v.x, v.y - 0.04, v.z);
        }
    }

    private void controlSpeed()
    {

        float yaw = 0;
        if (MC.player != null) {
            yaw = (float)Math.toRadians(MC.player.getYaw(0));
        }
        Vec3d forward = new Vec3d(-Math.sin(yaw) * 0.05, 0,
                Math.cos(yaw) * 0.05);

        Vec3d v = MC.player.getVelocity();

        if(MC.options.forwardKey.isPressed())
            MC.player.setVelocity(v.add(forward));
        else if(MC.options.sneakKey.isPressed())
            MC.player.setVelocity(v.subtract(forward));
    }

    private void doInstantFly()
    {

        if(timer <= 0)
        {
            timer = 20;
            if (MC.player != null) {
                MC.player.setJumping(false);
                MC.player.setSprinting(true);
                MC.player.jump();
            }
        }

        sendStartStopPacket();
    }

}
