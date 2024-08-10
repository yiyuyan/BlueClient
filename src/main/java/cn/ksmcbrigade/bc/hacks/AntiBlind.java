package cn.ksmcbrigade.bc.hacks;

import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Hack;
import org.lwjgl.glfw.GLFW;

import java.io.IOException;

public class AntiBlind extends Hack {
    public AntiBlind() throws IOException, NoSuchFieldException, IllegalAccessException {
        super(Category.RENDER, AntiBlind.class.getSimpleName(),false);
    }
}
