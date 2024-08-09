package cn.ksmcbrigade.bc.gui;

import cn.ksmcbrigade.bc.gui.entry.HackCategoryEntry;
import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Hack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.awt.*;
import java.util.ArrayList;

public class HackListScreen extends Screen {

    public HackCategoryEntry list = new HackCategoryEntry();

    public MinecraftClient MC = MinecraftClient.getInstance();

    public ArrayList<Hack> hacks = new ArrayList<>();

    public HackListScreen() {
        super(Text.of("HackListScreen"));
        for (Category category : Category.values()) {
            hacks.addAll(category.get());
        }
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        int y = 4;
        int x = 6;
        for (int i = 0; i < hacks.size(); i++) {
            if ((i + 1) % 4 == 0) {
                y+=55;
            }
            context.fillGradient(x,y,x+150,y+50,11010084,11010084);  // RGB(173, 216, 230)  background
            System.out.println(context.drawTextWithShadow(MC.textRenderer,hacks.get(i).getName(),(x/2)-100,y+25, Color.WHITE.getRGB()));
        }
    }
}
