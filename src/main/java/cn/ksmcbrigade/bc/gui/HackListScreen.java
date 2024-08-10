package cn.ksmcbrigade.bc.gui;

import cn.ksmcbrigade.bc.BlueClient;
import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Hack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec2f;

import java.util.ArrayList;

public class HackListScreen extends Screen {

    public ArrayList<Hack> hacks = new ArrayList<>();

    public HackListScreen() {
        super(Text.of("HackListScreen"));
        for (Category category : Category.values()) {
            hacks.addAll(category.get());
        }
        BlueClient.temp.renderList = true;
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        try {
            if(button==MinecraftClient.getInstance().options.attackKey.boundKey.getCode()) BlueClient.temp.click = new Vec2f((float) mouseX, (float) mouseY);
        } catch (Exception e) {
            BlueClient.LOGGER.error("Can't click in hack list screen.",e);
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.drawTexture(new Identifier(BlueClient.MOD_ID,"gui/title/title.png"),context.getScaledWindowWidth()-(240/3)-2,context.getScaledWindowHeight()-(150/3)-2,0,0,240/3,150/3,240/3,150/3);
    }

    @Override
    public void close() {
        BlueClient.temp.renderList = false;
        super.close();
    }
}
