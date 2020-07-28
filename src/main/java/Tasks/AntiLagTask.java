package Tasks;

import me.deadlight.volcanor.Utils;
import me.deadlight.volcanor.VolcanoR;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;

import java.util.*;

public class AntiLagTask {

    public static Random random = new Random();

    public static void antiLagMechanism() {


        Bukkit.getScheduler().scheduleSyncRepeatingTask(VolcanoR.getInstance(), new Runnable() {
            @Override
            public void run() {

                if (VolcanoR.getInstance().getConfig().getBoolean("antilag-mechanism") && Utils.isActive()) {

                    Location loc = Utils.convertStringToLoc(VolcanoR.getInstance().getConfig().getString("volcano-location"));
                    Collection<Entity> eColl = loc.getWorld().getNearbyEntities(loc, Utils.activationDistance, Utils.activationDistance, Utils.activationDistance);
                    List<Entity> eList = new ArrayList<>(eColl);
                    List<String> bList = VolcanoR.getInstance().getConfig().getStringList("specified-mobs");
                    List<EntityType> mobs = new ArrayList<>();
                    for (String i : bList) {

                        mobs.add(EntityType.valueOf(Arrays.asList(i.split(":")).get(0)));

                    }

                    int count = 0;

                    for (Entity entity : eList) {

                        if (mobs.contains(entity.getType())) {

                            count++;
                        }

                    }


                    if (count >= 30) {

                        for (Entity entity : eList) {

                            if (mobs.contains(entity.getType())) {

                                int rPercent = random.nextInt(100) + 1;
                                if (rPercent >= 30) {

                                    entity.remove();

                                }


                            }

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
