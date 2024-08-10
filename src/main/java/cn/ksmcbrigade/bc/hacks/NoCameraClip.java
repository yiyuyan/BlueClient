package cn.ksmcbrigade.bc.hacks;

import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Hack;

import java.io.IOException;

public class NoCameraClip extends Hack {
    public NoCameraClip() throws IOException, NoSuchFieldException, IllegalAccessException {
        super(Category.RENDER, NoCameraClip.class.getSimpleName(),false);
    }
}
