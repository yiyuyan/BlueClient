package cn.ksmcbrigade.bc.hacks;

import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Hack;

import java.io.IOException;

public class AntiDarkness extends Hack {
    public AntiDarkness() throws IOException, NoSuchFieldException, IllegalAccessException {
        super(Category.RENDER, AntiDarkness.class.getSimpleName(),false);
    }
}
