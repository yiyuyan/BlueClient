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

public class HackConfigScreen extends Screen {

    public Hack hack;

    public HackConfigScreen(Hack hack) {
        super(Text.of("HackConfigScreen"));
        this.hack = hack;
        BlueClient.temp.renderList = false;
        BlueClient.temp.renderConfig = true;
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
        context.drawTexture(new Identifier(BlueClient.MOD_ID,"gui/title/title.png"),context.getScaledWindowWidth()-(240/3)-2,context.getScaledWindowHeight()-(150/3)-2,0,0,240/4,150/3,240/3,150/3);
    }

    @Override
    public void close() {
        BlueClient.temp.renderConfig = false;
        super.close();
    }
}
