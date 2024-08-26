package cn.ksmcbrigade.bc.mixin.feature;

import cn.ksmcbrigade.bc.BlueClient;
import cn.ksmcbrigade.bc.hack.Hack;
import com.google.gson.JsonElement;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.registry.Registries;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public class BlockMixin {
    @Inject(method = "shouldDrawSide",at = @At("RETURN"), cancellable = true)
    private static void xray(BlockState state, BlockView world, BlockPos pos, Direction side, BlockPos otherPos, CallbackInfoReturnable<Boolean> cir){
        Hack nbbo = BlueClient.config.getHack("X-Ray");
        if(nbbo!=null && nbbo.enabled && in(state))
        {
            cir.setReturnValue(true);
        }
        else if(nbbo!=null && nbbo.enabled){
            cir.setReturnValue(false);
        }
    }

    @Unique
    private static boolean in(BlockState blockState){
        String id;
        try {
            id = blockState.getBlock().getRegistryEntry().getKey().get().getValue().toString();
        }
        catch (Exception e){
            id = Registries.BLOCK.getId(blockState.getBlock()).toString();
        }
        for (JsonElement blocks : BlueClient.config.getHack("X-Ray").getConfig().data.getAsJsonArray("blocks").asList()) {
            if(id.equalsIgnoreCase(blocks.getAsString())) return true;
        }
        return false;
    }
}
