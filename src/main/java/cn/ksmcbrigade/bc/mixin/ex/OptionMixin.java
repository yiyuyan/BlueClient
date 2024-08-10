package cn.ksmcbrigade.bc.mixin.ex;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import net.minecraft.SharedConstants;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.function.Function;

@Mixin(GameOptions.class)
public abstract class OptionMixin {
    @Shadow protected MinecraftClient client;

    @Shadow protected abstract void accept(GameOptions.Visitor visitor);

    @Shadow @Final
    static Logger LOGGER;

    @Shadow public abstract void sendClientSettings();

    @Shadow @Final private File optionsFile;

    /**
     * @author KSmc_brigade
     * @reason make can save more options values.
     */
    @Overwrite
    public void write() {
        try {
            final PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(this.optionsFile), StandardCharsets.UTF_8));

            try {
                printWriter.println("version:" + SharedConstants.getGameVersion().getSaveVersion().getId());
                this.accept(new GameOptions.Visitor() {
                    public void print(String key) {
                        printWriter.print(key);
                        printWriter.print(':');
                    }

                    public <T> void accept(String key, SimpleOption<T> option) {
                        DataResult<JsonElement> dataResult = option.getCodec().encodeStart(JsonOps.INSTANCE, option.getValue());
                        this.print(key);
                        printWriter.println(new Gson().toJson(dataResult.getOrThrow(true,(t)->{})));
                    }

                    public int visitInt(String key, int current) {
                        this.print(key);
                        printWriter.println(current);
                        return current;
                    }

                    public boolean visitBoolean(String key, boolean current) {
                        this.print(key);
                        printWriter.println(current);
                        return current;
                    }

                    public String visitString(String key, String current) {
                        this.print(key);
                        printWriter.println(current);
                        return current;
                    }

                    public float visitFloat(String key, float current) {
                        this.print(key);
                        printWriter.println(current);
                        return current;
                    }

                    public <T> T visitObject(String key, T current, Function<String, T> decoder, Function<T, String> encoder) {
                        this.print(key);
                        printWriter.println(encoder.apply(current));
                        return current;
                    }
                });
                if (this.client.getWindow().getVideoMode().isPresent()) {
                    printWriter.println("fullscreenResolution:" + this.client.getWindow().getVideoMode().get().asString());
                }
            } catch (Throwable var5) {
                try {
                    printWriter.close();
                } catch (Throwable var4) {
                    var5.addSuppressed(var4);
                }

                throw var5;
            }

            printWriter.close();
        } catch (Exception var6) {
            Exception exception = var6;
            LOGGER.error("Failed to save options", exception);
        }

        this.sendClientSettings();
    }
}
