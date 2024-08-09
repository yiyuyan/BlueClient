package cn.ksmcbrigade.bc;

import cn.ksmcbrigade.bc.config.Config;
import cn.ksmcbrigade.bc.config.TempVars;
import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Hack;
import com.mojang.logging.LogUtils;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;

import java.io.IOException;

public class BlueClient implements ClientModInitializer {

    public static final String MOD_ID = "bc";

    public static final String NAME = "BlueClient";
    public static final String VERSION = "Dev-1.0";

    public static final Identifier background = new Identifier(BlueClient.MOD_ID,"gui/background/title_background.png");

    public static final Logger LOGGER = LogUtils.getLogger();

    public static final Config config;
    public static final TempVars temp = new TempVars();

    static {
        try {
            config = new Config();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onInitializeClient() {
        LOGGER.info("Hello Blue Client!");

        //TEST
        for(int i=0;i<5;i++){
            try {
                new Hack(Category.OTHER,"Test-"+i);
                System.out.println("added");
            } catch (IOException | NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        for(int i=6;i<10;i++){
            try {
                new Hack(Category.FUN,"Test-"+i);
                System.out.println("added");
            } catch (IOException | NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        for(int i=11;i<25;i++){
            try {
                new Hack(Category.UI,"Test-"+i);
                System.out.println("added");
            } catch (IOException | NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        for(int i=26;i<30;i++){
            try {
                new Hack(Category.MOVE,"Test-"+i);
                System.out.println("added");
            } catch (IOException | NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
