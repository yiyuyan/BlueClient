package cn.ksmcbrigade.bc.hacks;

import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Hack;
import org.lwjgl.glfw.GLFW;

import java.io.IOException;

public class NoBossBarOverlay extends Hack {
    public NoBossBarOverlay() throws IOException, NoSuchFieldException, IllegalAccessException {
        super(Category.RENDER, NoBossBarOverlay.class.getSimpleName(),false);
    }
}
