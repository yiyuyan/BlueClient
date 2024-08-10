package cn.ksmcbrigade.bc.hacks;

import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Hack;
import org.lwjgl.glfw.GLFW;

import java.io.IOException;

public class AntiDemo extends Hack {
    public AntiDemo() throws IOException, NoSuchFieldException, IllegalAccessException {
        super(Category.OTHER, AntiDemo.class.getSimpleName(),false);
    }
}
