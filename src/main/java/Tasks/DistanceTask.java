package Tasks;

import me.deadlight.volcanor.Utils;
import me.deadlight.volcanor.VolcanoR;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.Collection;

public class DistanceTask {

    private static VolcanoR volcanoR = VolcanoR.getInstance();

    public static void checkForDistance() {

        Location vLoc = Utils.convertStringToLoc(volcanoR.getConfig().getString("volcano-location"));

        Bukkit.getScheduler().scheduleSyncDelayedTask(VolcanoR.getInstance(), new Runnable() {
            @Override
            public void run() {
                //just a normal check to prevent errors
                if (!volcanoR.getConfig().getString("volcano-location").equalsIgnoreCase("none")) {
                    vLoc.getWorld().getNearbyEntities(vLoc, Utils.activationDistance, Utils.activationDistance, Utils.activationDistance);

                }
            }
            ///Checks every 3 secods \ why not using PlayerMoveEvent? After researches, the best of finding distance nd less performance is using Scheduler
        }, 20 * 3);

    }

}
