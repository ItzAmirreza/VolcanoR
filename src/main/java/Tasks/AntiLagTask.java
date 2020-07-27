package Tasks;

import me.deadlight.volcanor.Utils;
import me.deadlight.volcanor.VolcanoR;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class AntiLagTask {


    public static void antiLagMechanism() {

        List<Entity> listOfMobs = Utils.laggyMobs;

        Bukkit.getScheduler().scheduleSyncRepeatingTask(VolcanoR.getInstance(), new Runnable() {
            @Override
            public void run() {

                if (VolcanoR.getInstance().getConfig().getBoolean("antilag-mechanism") && Utils.isActive()) {

                    int num = 0;
                    while (num <= Utils.MobsPerWave) {
                        num++;
                        try {
                            Utils.laggyMobs.forEach(mob -> {
                                mob.remove();
                                Utils.laggyMobs.remove(mob);

                            });
                        } catch (Exception e) {


                        }
                    }

                }

            }
        }, 20, 20 * Utils.eachWaveTime);


        Bukkit.getScheduler().scheduleSyncRepeatingTask(VolcanoR.getInstance(), new Runnable() {
            @Override
            public void run() {

                if (VolcanoR.getInstance().getConfig().getBoolean("antilag-mechanism") && Utils.isActive()) {

                    Location loc = Utils.convertStringToLoc(VolcanoR.getInstance().getConfig().getString("volcano-location"));

                    Collection<Entity> eColl = loc.getWorld().getNearbyEntities(loc, Utils.activationDistance, Utils.activationDistance, Utils.activationDistance);
                    List<Entity> eList = new ArrayList<>(eColl);
                    List<Entity> dropeedItems = new ArrayList<>();
                    List<String> bList = VolcanoR.getInstance().getConfig().getStringList("specified-blocks");
                    List<Material> materials = new ArrayList<>();
                    for (String i : bList) {

                        materials.add(Material.matchMaterial(Arrays.asList(i.split(":")).get(0)));

                    }


                    eList.forEach(entity -> {
                        if (entity instanceof Item) {

                            if (materials.contains(((Item) entity).getItemStack().getType())) {
                                entity.remove();
                            }
                        }
                    });



                }


            }
        }, 20, 20 * 30);


    }


}
