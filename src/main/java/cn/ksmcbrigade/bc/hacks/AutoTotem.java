package cn.ksmcbrigade.bc.hacks;

import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Hack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.PickFromInventoryC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.Objects;

import static net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket.Action.SWAP_ITEM_WITH_OFFHAND;

public class AutoTotem extends Hack {

    public int last = -1;

    public AutoTotem() throws IOException, NoSuchFieldException, IllegalAccessException {
        super(Category.COMBAT, AutoTotem.class.getSimpleName());
    }

    @Override
    public void playerTick(MinecraftClient MC, @Nullable PlayerEntity player) throws Exception {
        if(player!=null){
            if(last!=-1){
                Objects.requireNonNull(MC.getNetworkHandler()).getConnection().send(new PickFromInventoryC2SPacket(last));
                last = -1;
            }
            if(player.getOffHandStack().getItem().equals(Items.TOTEM_OF_UNDYING)){
                return;
            }
            if(!player.getOffHandStack().isEmpty()){
                return;
            }
            for(int i=0;i<player.getInventory().getMaxCountPerStack();i++){
                if(player.getInventory().getStack(i).getItem().equals(Items.TOTEM_OF_UNDYING)){
                    if(i<9){
                        final int last = player.getInventory().selectedSlot;
                        player.getInventory().selectedSlot = i;
                        Objects.requireNonNull(MC.getNetworkHandler()).getConnection().send(new PlayerActionC2SPacket(SWAP_ITEM_WITH_OFFHAND, new BlockPos(BlockPos.ZERO), player.getMovementDirection()));
                        player.getInventory().selectedSlot = last;
                        //MC.getConnection().getConnection().send(new ServerboundSetCarriedItemPacket(last));
                    }
                    else{
                        Objects.requireNonNull(MC.getNetworkHandler()).getConnection().send(new PlayerActionC2SPacket(SWAP_ITEM_WITH_OFFHAND, new BlockPos(BlockPos.ZERO), player.getMovementDirection()));
                        MC.getNetworkHandler().getConnection().send(new PickFromInventoryC2SPacket(i));
                        MC.getNetworkHandler().getConnection().send(new PlayerActionC2SPacket(SWAP_ITEM_WITH_OFFHAND, new BlockPos(BlockPos.ZERO), player.getMovementDirection()));
                    }
                    last = i;
                    break;
                }
            }
        }
    }
}
