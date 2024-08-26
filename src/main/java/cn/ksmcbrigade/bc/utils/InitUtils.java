package cn.ksmcbrigade.bc.utils;

import cn.ksmcbrigade.bc.BlueClient;
import cn.ksmcbrigade.bc.hack.Hack;
import cn.ksmcbrigade.bc.hacks.NoFall;
import com.google.gson.JsonElement;
import org.apache.commons.io.IOUtils;
import org.lwjgl.system.linux.liburing.IOURing;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class InitUtils {

    private static boolean hacks = false;
    private static boolean enables = false;

    public static void initHacks() {
        if(!hacks){

            try {
                ClassLoader classLoader = InitUtils.class.getClassLoader();
                Enumeration<URL> resources = classLoader.getResources("cn/ksmcbrigade/bc/hacks");
                while (resources.hasMoreElements()) {
                    URL url = resources.nextElement();
                    BlueClient.LOGGER.info("Running Property: "+url.getProtocol());
                    if (url.getProtocol().equals("jar")) {
                        String jarPath = URLDecoder.decode(url.getPath().substring(5, url.getPath().indexOf("!")), StandardCharsets.UTF_8);
                        JarFile jarFile = new JarFile(jarPath);
                        Enumeration<JarEntry> entries = jarFile.entries();
                        while (entries.hasMoreElements()) {
                            JarEntry entry = entries.nextElement();
                            if (entry.getName().startsWith("cn/ksmcbrigade/bc/hacks") && entry.getName().endsWith(".class")) {
                                String className = entry.getName().replace("/", ".")
                                        .replace(".class", "");
                                Class<?> clazz = classLoader.loadClass(className);
                                if (Hack.class.isAssignableFrom(clazz) && !clazz.equals(Hack.class)) {
                                    clazz.getDeclaredConstructor().newInstance();
                                    BlueClient.LOGGER.info("Init a hack: "+clazz.getSimpleName());
                                }
                            }
                        }
                        jarFile.close();
                    } else {
                        File file = new File(url.getFile());
                        if (file.isDirectory()) {
                            File[] classFiles = file.listFiles((dir, name) -> name.endsWith(".class"));
                            for (File classFile : classFiles) {
                                String className = classFile.getName().replace(".class", "");
                                Class<?> clazz = classLoader.loadClass("cn.ksmcbrigade.bc.hacks." + className);
                                if (Hack.class.isAssignableFrom(clazz) && !clazz.equals(Hack.class)) {
                                    clazz.getDeclaredConstructor().newInstance();
                                    BlueClient.LOGGER.info("Init a hack: "+clazz.getSimpleName());
                                }
                            }
                        }
                    }
                }
            } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException |
                     NoSuchMethodException | SecurityException | InvocationTargetException e) {
                e.printStackTrace();
            }

            hacks = true;
        }
    }

    public static void initEnables() {
        if(!enables){

            for (JsonElement enable : BlueClient.config.enables) {
                try {
                    BlueClient.config.getHack(enable.getAsString()).setEnabled(true);
                    BlueClient.LOGGER.info("Init a hack to enabled: "+enable.getAsString());
                }
                catch (Exception e){
                    BlueClient.LOGGER.error("error in set a hack to enabled.",e);
                }
            }

            enables = true;
        }
    }
}
