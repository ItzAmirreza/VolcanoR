package me.deadlight.volcanor;

import Tasks.AntiLagTask;
import Tasks.DistanceTask;
import Tasks.VolcanoTask;
import commands.MainCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;



public final class VolcanoR extends JavaPlugin {

    public static VolcanoR instance;
    public static VolcanoR getInstance() {
        return instance;
    }


    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        Utils.vStatus.put("status", false);
        DistanceTask.checkForDistance();
        VolcanoTask.StartVolcano();
        AntiLagTask.antiLagMechanism();
        getServer().getPluginCommand("volcanor").setExecutor(new MainCommand());
        Utils.sendToConsole("&eVolcanoR has been enabled. By Dead_Light");



    }

    @Override
    public void onDisable() {
        Utils.sendToConsole("&eVolcanoR has been disabled. By Dead_Light");

    }

}
