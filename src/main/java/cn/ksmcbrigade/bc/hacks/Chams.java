package cn.ksmcbrigade.bc.hacks;

import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Hack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.OutlineVertexConsumerProvider;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Box;

import java.io.IOException;

public class Chams extends Hack {
    public Chams() throws IOException, NoSuchFieldException, IllegalAccessException {
        super(Category.RENDER, Chams.class.getSimpleName(),false);
    }

    @Override
    public void renderGame(DrawContext context, float ticks) {
        MinecraftClient MC = MinecraftClient.getInstance();
        if(MC.player!=null && MC.world!=null && MC.cameraEntity!=null){
            MC.world.getEntitiesByClass(Entity.class,new Box(MC.player.getBlockPos(),MC.player.getBlockPos()).expand(MC.options.getViewDistance().getValue()*16),Entity::isAlive).forEach(entity->{
                if(entity.getId()!=MC.player.getId()){
                    Object vertexConsumerProvider;
                    OutlineVertexConsumerProvider outlineVertexConsumerProvider = MC.worldRenderer.bufferBuilders.getOutlineVertexConsumers();
                    vertexConsumerProvider = outlineVertexConsumerProvider;
                    MC.worldRenderer.renderEntity(entity,MC.cameraEntity.prevX,MC.cameraEntity.prevY,MC.cameraEntity.prevZ,ticks,context.getMatrices(), (VertexConsumerProvider) vertexConsumerProvider);
                }
            });
        }
    }
}
