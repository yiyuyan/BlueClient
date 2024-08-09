package cn.ksmcbrigade.bc.config;


import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.util.math.Vec2f;

public class TempVars {
    public MinecraftClient MC = net.minecraft.client.MinecraftClient.getInstance();

    public boolean renderList = false;
    public Vec2f click;

    public ServerInfo lastServer;
}
