package Tasks;

import me.deadlight.volcanor.Utils;
import me.deadlight.volcanor.VolcanoR;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DistanceTask {

    private static VolcanoR volcanoR = VolcanoR.getInstance();

    public static void checkForDistance() {


        String loc = volcanoR.getConfig().getString("volcano-location");

        Bukkit.getScheduler().scheduleSyncRepeatingTask(VolcanoR.getInstance(), new Runnable() {
            @Override
            public void run() {
                //just a normal check to prevent errors
                if (!loc.equalsIgnoreCase("none")) {
                    Location vLoc = Utils.convertStringToLoc(volcanoR.getConfig().getString("volcano-location"));
                    Collection<Entity> nEntityList = vLoc.getWorld().getNearbyEntities(vLoc, Utils.activationDistance, Utils.activationDistance, Utils.activationDistance);
                    List<Entity> entityListArr = new ArrayList<>(nEntityList);
                    List<Player> playersInRange = new ArrayList<>();
                    for (Entity entity : entityListArr) {
                        //Lets also check for NPCs
                        if (entity instanceof Player && !entity.hasMetadata("NPC")) {
                            Player player = (Player) entity;
                            playersInRange.add(player);
                        }
                    }
                    if (playersInRange.size() != 0) {
                        if (!Utils.isActive()) {
                            Utils.vStatus.replace("status", true);

                        }
                    } else {

                        Utils.vStatus.replace("status", false);

                    }
                }
            }
            ///Checks every 3 secods \ why not using PlayerMoveEvent? After researches, the best of finding distance nd less performance is using Scheduler
        }, 20, 20 * 3);

    }

}
