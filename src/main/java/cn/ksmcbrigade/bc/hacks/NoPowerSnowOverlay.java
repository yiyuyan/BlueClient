package cn.ksmcbrigade.bc.hacks;

import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Hack;
import org.lwjgl.glfw.GLFW;

import java.io.IOException;

public class NoPowerSnowOverlay extends Hack {
    public NoPowerSnowOverlay() throws IOException, NoSuchFieldException, IllegalAccessException {
        super(Category.RENDER, NoPowerSnowOverlay.class.getSimpleName(),false);
    }
}
