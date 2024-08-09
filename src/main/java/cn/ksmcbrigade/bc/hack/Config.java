package cn.ksmcbrigade.bc.hack;

import cn.ksmcbrigade.bc.BlueClient;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Config {

    public static final File configDir = new File("blue/config/");

    public File file;
    public JsonObject data;

    public Hack hack;

    public Config(Hack hack,JsonObject def) throws IOException {
        this.hack = hack;
        this.data = def;
        this.save(false);
    }

    public void save(boolean t) throws IOException {
        BlueClient.config.saveHack(this.hack.getEnName(),this);
    }

    @Nullable
    public JsonElement get(String key){
        if(!data.has(key)){
            return null;
        }
        else{
            return data.get(key);
        }
    }

    public void setData(JsonObject newData) throws IOException{
        this.data = newData;
        this.save(true);
    }

    public void set(String key,JsonElement value) throws IOException{
        if(data.has(key)){
            data.add(key,value);
            save(true);
        }
    }
}
