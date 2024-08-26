package cn.ksmcbrigade.bc.mixin.feature;

import cn.ksmcbrigade.bc.BlueClient;
import cn.ksmcbrigade.bc.hacks.Blink;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ClientConnection.class)
public abstract class NetworkHandlerMixin {

    @ModifyVariable(method = "sendInternal",at = @At("HEAD"),argsOnly = true)
    public Packet<?> send(Packet<?> value){
        Blink hack = (Blink) BlueClient.config.getHack("Blink");
        if(hack==null) return value;
        if(MinecraftClient.getInstance().player!=null) hack.heal = Math.max(hack.heal,MinecraftClient.getInstance().player.getHealth());
        if((value instanceof PlayerMoveC2SPacket) && hack.enabled && MinecraftClient.getInstance().player!=null && MinecraftClient.getInstance().player.getHealth()<hack.heal){
            hack.heal = MinecraftClient.getInstance().player.getHealth();
            hack.pos = MinecraftClient.getInstance().player.getPos();
            MinecraftClient.getInstance().player.sendMessage(Text.literal("[Blink] rest the original position because the other entities' attack."),true);
        }
        if((value instanceof PlayerMoveC2SPacket packet) && hack.enabled && hack.pos!=null && MinecraftClient.getInstance().player!=null && hack.pos.distanceTo(MinecraftClient.getInstance().player.getPos())<=8.5D){
            return new PlayerMoveC2SPacket.Full(hack.pos.x,hack.pos.y,hack.pos.z,packet.getPitch(0),packet.getYaw(0),packet.isOnGround());
        }
        else if((value instanceof PlayerMoveC2SPacket) && hack.enabled && hack.pos!=null && MinecraftClient.getInstance().player!=null && hack.pos.distanceTo(MinecraftClient.getInstance().player.getPos())>8.5D){
            hack.pos = MinecraftClient.getInstance().player.getPos();
            MinecraftClient.getInstance().player.sendMessage(Text.literal("[Blink] rest the original position because of the distance."),true);
        }
        return value;
    }
}
