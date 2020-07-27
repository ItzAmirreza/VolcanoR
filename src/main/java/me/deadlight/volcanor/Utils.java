package me.deadlight.volcanor;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Utils {


    public static String color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }


    public static String prefix = color("&8[&cVolcano&aR&8] ");


    public static void sendToConsole(String s) {
        VolcanoR.getInstance().getServer().getConsoleSender().sendMessage(prefix + s);
    }


    public static HashMap<String, Boolean> vStatus = new HashMap<>();

    public static Boolean isActive() {
        if (!vStatus.get("status")) {
            return false;
        } else {
            return true;
        }
    }

    public static String convertLocToString(Location location) {
        String world = location.getWorld().getName();
        String xc = Integer.toString(location.getBlockX());
        String yc = Integer.toString(location.getBlockY());
        String zc = Integer.toString(location.getBlockZ());
        return world + ":" + xc + ":" + yc + ":" + zc;
    }

    public static Location convertStringToLoc(String location) {
        List<String> loclist = Arrays.asList(location.split(":"));
        World world = Bukkit.getWorld(loclist.get(0));
        int x = Integer.parseInt(loclist.get(1));
        int y = Integer.parseInt(loclist.get(1));
        int z = Integer.parseInt(loclist.get(1));
        Location finalLoc = new Location(world, x,y,z);
        return finalLoc;
    }

    public static int activationDistance = VolcanoR.getInstance().getConfig().getInt("activation-distance");



}
