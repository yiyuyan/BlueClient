package cn.ksmcbrigade.bc.hacks;

import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Hack;

import java.io.IOException;

public class NoUnderOverlay extends Hack {
    public NoUnderOverlay() throws IOException, NoSuchFieldException, IllegalAccessException {
        super(Category.RENDER, NoUnderOverlay.class.getSimpleName(),false);
    }
}
