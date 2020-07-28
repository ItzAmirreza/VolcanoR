package me.deadlight.volcanor;

import Tasks.AlertingTask;
import Tasks.DistanceTask;
import Tasks.VolcanoTask;

import java.io.File;

public class ConfigManager {


    public static void reloadTheConfig() {
        try {
            Utils.config.save(VolcanoR.getInstance().getDataFolder() + File.separator + "config.yml");
        } catch (Exception e) {
            Utils.sendToConsole("&cCould not save the configuration file.");
        }
        VolcanoR.getInstance().reloadConfig();
        Utils.config = VolcanoR.getInstance().getConfig();
        VolcanoTask.materials = Utils.config.getStringList("specified-blocks");
        VolcanoTask.mobs = Utils.config.getStringList("specified-mobs");
        DistanceTask.loc = Utils.config.getString("volcano-location");
        AlertingTask.alerting = Utils.config.getBoolean("warning-message");
        AlertingTask.alertTitle = Utils.config.getString("warning-title");
        AlertingTask.alertSubtitle = Utils.config.getString("warning-subtitle");

    }

    //specifially created for reload command
    public static void reloadCommand() {
        VolcanoR.getInstance().reloadConfig();
        Utils.config = VolcanoR.getInstance().getConfig();
        VolcanoTask.materials = Utils.config.getStringList("specified-blocks");
        VolcanoTask.mobs = Utils.config.getStringList("specified-mobs");
        DistanceTask.loc = Utils.config.getString("volcano-location");
        AlertingTask.alerting = Utils.config.getBoolean("warning-message");
        AlertingTask.alertTitle = Utils.config.getString("warning-title");
        AlertingTask.alertSubtitle = Utils.config.getString("warning-subtitle");

    }


}
