package cn.ksmcbrigade.bc.hacks;

import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Hack;
import org.lwjgl.glfw.GLFW;

import java.io.IOException;

public class NoSpyglassOverlay extends Hack {
    public NoSpyglassOverlay() throws IOException, NoSuchFieldException, IllegalAccessException {
        super(Category.RENDER, NoSpyglassOverlay.class.getSimpleName(),false);
    }
}
