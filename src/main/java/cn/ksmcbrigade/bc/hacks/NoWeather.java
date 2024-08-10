package cn.ksmcbrigade.bc.hacks;

import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Hack;

import java.io.IOException;

public class NoWeather extends Hack {
    public NoWeather() throws IOException, NoSuchFieldException, IllegalAccessException {
        super(Category.RENDER, NoWeather.class.getSimpleName(),false);
    }
}
