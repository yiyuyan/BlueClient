package cn.ksmcbrigade.bc.hacks;

import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Hack;

import java.io.IOException;

public class TpClick extends Hack {

    public TpClick() throws IOException, NoSuchFieldException, IllegalAccessException {
        super(Category.COMBAT, TpClick.class.getSimpleName(),false);
    }
}
