package cn.ksmcbrigade.bc.hacks;

import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Hack;
import org.lwjgl.glfw.GLFW;

import java.io.IOException;

public class NoPortalOverlay extends Hack {
    public NoPortalOverlay() throws IOException, NoSuchFieldException, IllegalAccessException {
        super(Category.RENDER, NoPortalOverlay.class.getSimpleName(),false);
    }
}
