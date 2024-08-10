package cn.ksmcbrigade.bc.mixin.feature;

import cn.ksmcbrigade.bc.BlueClient;
import cn.ksmcbrigade.bc.hack.Hack;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.class)
public abstract class BlockStateBaseMixin {
    @Shadow @Deprecated public abstract FluidState getFluidState(BlockState state);

    @Inject(method = {"getCollisionShape","getOutlineShape"},at = @At("RETURN"), cancellable = true)
    public void shape(BlockState state, BlockView world, BlockPos pos, ShapeContext context, CallbackInfoReturnable<VoxelShape> cir){
        if(getFluidState(state).isEmpty()) return;
        MinecraftClient MC = MinecraftClient.getInstance();
        Hack nbbo = BlueClient.config.getHack("FluidJump");
        if((nbbo!=null && nbbo.enabled) && MC.player != null && MC.player.fallDistance <= 3
                && !MC.options.sneakKey.isPressed() && !MC.player.isTouchingWater()
                && !MC.player.isInLava()){
            cir.setReturnValue(VoxelShapes.fullCube());
        }
    }
}
