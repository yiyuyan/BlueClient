package cn.ksmcbrigade.bc.hacks;

import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Hack;
import org.lwjgl.glfw.GLFW;

import java.io.IOException;

public class NoPumpkinOverlay extends Hack {
    public NoPumpkinOverlay() throws IOException, NoSuchFieldException, IllegalAccessException {
        super(Category.RENDER, NoPumpkinOverlay.class.getSimpleName(),false);
    }
}
