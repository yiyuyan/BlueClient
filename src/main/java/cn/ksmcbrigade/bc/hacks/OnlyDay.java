package cn.ksmcbrigade.bc.hacks;

import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Hack;

import java.io.IOException;

public class OnlyDay extends Hack {
    public OnlyDay() throws IOException, NoSuchFieldException, IllegalAccessException {
        super(Category.RENDER, OnlyDay.class.getSimpleName(),false);
    }
}
