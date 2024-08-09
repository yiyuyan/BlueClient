package cn.ksmcbrigade.bc.config;

import cn.ksmcbrigade.bc.hack.Category;
import cn.ksmcbrigade.bc.hack.Hack;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Config {
    public static final File config = new File("blueClient.json");

    public JsonArray enables = new JsonArray();
    public JsonObject keys = new JsonObject();
    public JsonArray hacks = new JsonArray();

    public Config() throws IOException {
        if(!config.exists()){
            save(false);
        }
        JsonObject object = JsonParser.parseString(FileUtils.readFileToString(config)).getAsJsonObject();
        this.enables = object.getAsJsonArray("enables");
        this.keys = object.getAsJsonObject("keys");
        this.hacks = object.getAsJsonArray("hacks");
    }

    public void save(boolean e) throws IOException{
        if(!config.exists() || e){
            JsonObject object = new JsonObject();
            object.add("enables",enables);
            object.add("keys",keys);
            object.add("hacks",hacks);
            FileUtils.writeStringToFile(config,object.toString());
        }
    }

    public void saveHack(String name, @Nullable cn.ksmcbrigade.bc.hack.Config config) throws IOException {
        int hack = indexOfHack(name);
        JsonObject data = config==null?new JsonObject():config.data;
        if(hack==-1){
            JsonObject object = new JsonObject();
            object.addProperty("name",name);
            object.add("config",data);
            this.hacks.add(object);
        }
        else{
            this.hacks.get(hack).getAsJsonObject().add("config",data);
        }
        this.save(true);
    }

    public void saveEnables() throws IOException {
        JsonArray array = new JsonArray();
        for (Hack enable : getEnables()) {
            array.add(enable.getEnName());
        }
        this.enables = array;
        save(true);
    }

    public void saveKeys() throws IOException {
        JsonObject object = new JsonObject();
        for (Hack hack : this.getHacks()) {
            object.addProperty(hack.getEnName(),hack.key);
        }
        this.keys = object;
        save(true);
    }

    public ArrayList<Hack> getHacks(){
        ArrayList<Hack> hacks1 = new ArrayList<>();
        for (Category value : Category.values()) {
            hacks1.addAll(value.get());
        }
        return hacks1;
    }

    public ArrayList<Hack> getEnables(){
        ArrayList<Hack> hacks1 = new ArrayList<>();
        for (Category value : Category.values()) {
            hacks1.addAll(value.get().stream().filter(h -> h.enabled).toList());
        }
        return hacks1;
    }

    public Hack getHack(String name){
        for(Hack hack:getHacks()){
            if(hack.getEnName().equalsIgnoreCase(name)){
                return hack;
            }
        }
        return null;
    }

    public int indexOfHack(String name){
        for (int i=0;i<this.hacks.asList().size();i++) {
            JsonElement hack = this.hacks.get(i);
            if(hack.isJsonObject() && hack.getAsJsonObject().get("name").getAsString().equalsIgnoreCase(name)){
                return i;
            }
        }
        return -1;
    }
}
