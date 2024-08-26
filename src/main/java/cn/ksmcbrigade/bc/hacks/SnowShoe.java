package cn.ksmcbrigade.bc.hacks;

import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Hack;

import java.io.IOException;

public class SnowShoe extends Hack {

    public SnowShoe() throws IOException, NoSuchFieldException, IllegalAccessException {
        super(Category.MOVE, SnowShoe.class.getSimpleName(),false);
    }
}
