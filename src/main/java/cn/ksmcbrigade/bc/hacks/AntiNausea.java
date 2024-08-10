package cn.ksmcbrigade.bc.hacks;

import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Hack;

import java.io.IOException;

public class AntiNausea extends Hack {
    public AntiNausea() throws IOException, NoSuchFieldException, IllegalAccessException {
        super(Category.RENDER, AntiNausea.class.getSimpleName(),false);
    }
}
