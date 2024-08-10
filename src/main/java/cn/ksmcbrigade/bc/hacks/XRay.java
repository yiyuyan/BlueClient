package cn.ksmcbrigade.bc.hacks;

import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Config;
import cn.ksmcbrigade.bc.hack.Hack;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

import java.io.IOException;

public class XRay extends Hack {
    public XRay() throws IOException, NoSuchFieldException, IllegalAccessException {
        super(Category.RENDER, "X-Ray",false, GLFW.GLFW_KEY_X,get());
    }

    private static Config get() throws IOException {
        JsonObject obj = new JsonObject();
        JsonArray object = new JsonArray();

        object.add("minecraft:diamond_ore");
        object.add("minecraft:deepslate_diamond_ore");
        object.add("minecraft:diamond_block");

        object.add("minecraft:gold_ore");
        object.add("minecraft:deepslate_gold_ore");
        object.add("minecraft:minecraft:nether_gold_ore");
        object.add("minecraft:gold_block");
        object.add("minecraft:raw_gold_block");

        object.add("minecraft:coal_ore");
        object.add("minecraft:deepslate_coal_ore");
        object.add("minecraft:coal_block");

        object.add("minecraft:iron_ore");
        object.add("minecraft:deepslate_iron_ore");
        object.add("minecraft:iron_block");
        object.add("minecraft:raw_iron_block");

        object.add("minecraft:copper_ore");
        object.add("minecraft:deepslate_copper_ore");
        object.add("minecraft:copper_block");
        object.add("minecraft:raw_copper_block");

        object.add("minecraft:redstone_ore");
        object.add("minecraft:deepslate_redstone_ore");
        object.add("minecraft:redstone_block");

        object.add("minecraft:lapis_ore");
        object.add("minecraft:deepslate_lapis_ore");
        object.add("minecraft:lapis_block");

        object.add("minecraft:emerald_ore");
        object.add("minecraft:deepslate_emerald_ore");
        object.add("minecraft:emerald_block");

        object.add("minecraft:quartz_ore");
        object.add("minecraft:ancient_debris");

        object.add("minecraft:netherite_block");
        object.add("minecraft:quartz_block");

        object.add("minecraft:tnt");

        object.add("minecraft:chest");
        object.add("minecraft:ender_chest");
        object.add("minecraft:shulker_box");

        object.add("minecraft:furnace");
        object.add("minecraft:smoker");
        object.add("minecraft:blast_furnace");
        
        obj.add("blocks",object);
        return new Config("X-Ray",obj);
    }

    @Override
    public void enabled(MinecraftClient MC) {
        MC.worldRenderer.reload();
    }

    @Override
    public void disabled(MinecraftClient MC) {
        MC.worldRenderer.reload();
    }
}
