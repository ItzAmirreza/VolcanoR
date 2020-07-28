package Tasks;

import me.deadlight.volcanor.Utils;
import me.deadlight.volcanor.VolcanoR;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Mob;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class VolcanoTask {

    public static List<String> materials = (VolcanoR.getInstance().getConfig().getStringList("specified-blocks"));
    public static List<String> mobs = VolcanoR.getInstance().getConfig().getStringList("specified-mobs");
    public static List<Material> matList = new ArrayList<>();
    public static List<Material> mobList = new ArrayList<>();
    public static Random random = new Random();

    public static void StartVolcano() {

        /**
        for (String mat : materials) {
            matList.add(Material.getMaterial(Arrays.asList(mat.split(":")).get(0)));
        }

        for (String mob : mobs) {
            mobList.add(Material.getMaterial(Arrays.asList(mob.split(":")).get(0)));
        }
**/

        Bukkit.getScheduler().scheduleSyncRepeatingTask(VolcanoR.getInstance(), new Runnable() {


            @Override
            public void run() {

                if (Utils.isActive()) {
                 //Only when it is Active ! / not going to stop the scheduler

                    launchBlocks();
                    spawnMobs();

                }
            }
            //I guess 3 seconds for each launch is good...
        }, 20, 20 * 10);

    }

    public static void launchBlocks() {

        Location vLoc = Utils.convertStringToLoc(VolcanoR.getInstance().getConfig().getString("volcano-location"));
        vLoc.setY(vLoc.getBlockY() + 1);

        int num = 0;

        while (num <= Utils.BlocksPerWave) {

            String matString = materials.get(random.nextInt(materials.size()));

            boolean active = false;
            while (!active) {

                //Percentage System
                int rPercent = random.nextInt(100) + 1;
                Material realMaterial = Material.matchMaterial(Arrays.asList(matString.split(":")).get(0));
                int percentage = Integer.parseInt(Arrays.asList(matString.split(":")).get(1));
                if (rPercent <= percentage) {

                    FallingBlock block = vLoc.getWorld().spawnFallingBlock(vLoc, realMaterial.createBlockData());
                    float x = (float) -1 + (float) (Math.random() * ((1 - -1) + 1));
                    float y = (float) -5 + (float)(Math.random() * ((5 - -5) + 1));
                    float z = (float) -0.3 + (float)(Math.random() * ((0.3 - -0.3) + 1));
                    block.setVelocity(new Vector(x,y,z));
                    active = true;
                }

            }
            num++;
        }

    }

    public static void spawnMobs() {

        Location vLoc = Utils.convertStringToLoc(VolcanoR.getInstance().getConfig().getString("volcano-location"));
        vLoc.setY(vLoc.getBlockY() + 1);

        int num = 0;

        while (num <= Utils.MobsPerWave) {

            String mobString = mobs.get(random.nextInt(mobs.size()));

            boolean active = false;
            while (!active) {

                //Percentage System
                int rPercent = random.nextInt(100) + 1;
                EntityType realMob = EntityType.valueOf(Arrays.asList(mobString.split(":")).get(0));
                int percentage = Integer.parseInt(Arrays.asList(mobString.split(":")).get(1));
                if (rPercent <= percentage) {

                    Entity entity = vLoc.getWorld().spawnEntity(vLoc, realMob);
                    float x = (float) -1 + (float) (Math.random() * ((1 - -1) + 1));
                    float y = (float) -5 + (float)(Math.random() * ((5 - -5) + 1));
                    float z = (float) -0.3 + (float)(Math.random() * ((0.3 - -0.3) + 1));
                    entity.setVelocity(new Vector(x,y,z));
                    active = true;
                }

            }
            num++;
        }


    }
}
