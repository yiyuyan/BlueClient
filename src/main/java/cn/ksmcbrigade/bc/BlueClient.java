package cn.ksmcbrigade.bc;

import cn.ksmcbrigade.bc.config.Config;
import cn.ksmcbrigade.bc.config.TempVars;
import cn.ksmcbrigade.bc.utils.InitUtils;
import com.mojang.logging.LogUtils;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class BlueClient implements ClientModInitializer {

    public static final String MOD_ID = "bc";

    public static final String NAME = "BlueClient";
    public static final String VERSION = "Dev-1.0";

    public static final Identifier background = new Identifier(BlueClient.MOD_ID,"gui/background/title_background.png");

    public static final Logger LOGGER = LogUtils.getLogger();

    public static final Config config;
    public static final TempVars temp = new TempVars();

    static {
        SystemToast.Type.UNSECURE_SERVER_WARNING.displayDuration = 550L;
        try {
            config = new Config();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onInitializeClient() {
        LOGGER.info("Hello Blue Client!");

        try {
            InitUtils.initHacks();
            LOGGER.info("Initialized the hacks.");
            InitUtils.initEnables();
            LOGGER.info("Initialized the hacks enabled.");
        } catch (Exception e) {
            LOGGER.error("Can't init the hacks.",e);
            throw new RuntimeException(e);
        }
    }

    public static void sendToast(String context){
        if(temp.MC.getToastManager()==null) return;
        SystemToast toast = new SystemToast(SystemToast.Type.UNSECURE_SERVER_WARNING, Text.of("BlueClient"),Text.of(context));
        temp.MC.getToastManager().add(toast);
    }
}
