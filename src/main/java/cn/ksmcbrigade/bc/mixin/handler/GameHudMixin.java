package cn.ksmcbrigade.bc.mixin.handler;

import cn.ksmcbrigade.bc.BlueClient;
import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Hack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.util.math.Vec2f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Mixin(InGameHud.class)
public abstract class GameHudMixin {

    @Shadow public abstract TextRenderer getTextRenderer();

    @Shadow public abstract int getTicks();

    @Unique
    private Map<Vec2f, Hack> hackListScreenSco = new HashMap<>();

    @Unique
    private Map<Vec2f, Category> typeListScreenSco = new HashMap<>();

    @Inject(method = "<init>",at = @At("TAIL"))
    public void init(MinecraftClient client, ItemRenderer itemRenderer, CallbackInfo ci){
        int x = 2;
        int y = 0;
        for (Category category : Category.values()) {
            Vec2f position = new Vec2f(x, y);
            typeListScreenSco.put(position, category);
            for (Hack hack : category.get()) {
                hackListScreenSco.put(new Vec2f(x, y + 10), hack);
                y += 10;
            }
            int longest = longest(category.get());
            x += longest==-1?getTextRenderer().getWidth(category.name()): Math.max(longest, getTextRenderer().getWidth(category.name()));
            x += 5;
            y = 0;
        }
    }

    @Inject(method = "tick()V",at = @At("HEAD"))
    public void tick(CallbackInfo ci) throws Exception {
        BlueClient.config.getEnables().forEach(h -> {
            try {
                h.render();
            } catch (Exception e) {
                BlueClient.LOGGER.error("error in render tick",e);
            }
        });
        if(BlueClient.temp.renderList && BlueClient.temp.click!=null){
            this.click(BlueClient.temp.click);
            BlueClient.temp.click = null;
        }
    }

    @Inject(method = "render",at = @At("HEAD"))
    public void render(DrawContext context, float tickDelta, CallbackInfo ci){
        BlueClient.config.getEnables().forEach(h -> {
            try {
                h.renderGame(context,tickDelta);
            } catch (Exception e) {
                BlueClient.LOGGER.error("error in render game tick",e);
            }
        });
        if(BlueClient.temp.renderList){
            for (Map.Entry<Vec2f, Category> entry : typeListScreenSco.entrySet()) {
                Vec2f position = entry.getKey();
                Category category = entry.getValue();
                int longest = longest(category.get());

                context.fillGradient((int) (position.x-2), (int) position.y, (int) (position.x+(longest==-1?getTextRenderer().getWidth(category.name()): Math.max(longest, getTextRenderer().getWidth(category.name())))), (int) (position.y+10),0x440000FF,0x440000FF);
                context.drawTextWithShadow(this.getTextRenderer(),category.name(), (int) position.x, (int) position.y+2, 0xFFFFFF);
                for (Map.Entry<Vec2f, Hack> hackEntry : hackListScreenSco.entrySet()) {
                    if (hackEntry.getValue().type == category) {
                        Vec2f hackPosition = hackEntry.getKey();
                        Hack hack = hackEntry.getValue();
                        int color = hack.enabled ? Color.blue.getRGB() : 0x0066CC;
                        context.fillGradient((int) (hackPosition.x-2),(int) (5+hackPosition.y),(int) (hackPosition.x+getTextRenderer().getWidth(hack.getName())),(int) (5+hackPosition.y+getTextRenderer().fontHeight),color,color);
                        context.drawTextWithShadow(this.getTextRenderer(),hack.getName(), (int) hackPosition.x, (int) (5+hackPosition.y), 0xFFFFFF);
                    }
                }
            }
        }
    }

    @Unique
    public void click(Vec2f point) throws Exception {
        for (Map.Entry<Vec2f, Hack> entry : hackListScreenSco.entrySet()) {
            Vec2f hackPosition = entry.getKey();
            Hack hack = entry.getValue();
            if (point.x >= (hackPosition.x-2) && point.x <= (hackPosition.x-2) + getTextRenderer().getWidth(hack.getName()) &&
                    point.y >= hackPosition.y && point.y <= hackPosition.y + getTextRenderer().fontHeight) {
                hack.setEnabled(!hack.enabled);
            }
        }
    }

    @Unique
    public int longest(ArrayList<Hack> hacks){
        if(hacks.isEmpty()) return -1;
        ArrayList<String> strings = new ArrayList<>();
        for (Hack hack : hacks) {
            strings.add(hack.getEnName());
        }
        int index=0;
        for(int i=1;i<=strings.size()-1;i++) {
            if(strings.get(i).length()>strings.get(index).length()) {
                index=i;
            }
        }
        return this.getTextRenderer().getWidth(strings.get(index));
    }
}
