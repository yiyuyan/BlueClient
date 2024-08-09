package cn.ksmcbrigade.bc.hack;

import java.util.ArrayList;

public enum Category {

    UI("UI"),
    OTHER("OTHER"),
    COMBAT("COMBAT"),
    RENDER("RENDER"),
    MOVE("MOVE"),
    CHAT("CHAT"),
    FUN("FUN");

    final String name;
    final ArrayList<Hack> hacks = new ArrayList<>();

    Category(String name){
        this.name = name;
    }

    public ArrayList<Hack> get(){
        return this.hacks;
    }
}
