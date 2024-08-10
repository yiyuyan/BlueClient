package cn.ksmcbrigade.bc.hacks;

import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Hack;

import java.io.IOException;

public class InvPortal extends Hack {
    public InvPortal() throws IOException, NoSuchFieldException, IllegalAccessException {
        super(Category.RENDER, InvPortal.class.getSimpleName(),false);
    }
}
