package Tasks;

import me.deadlight.volcanor.Utils;
import me.deadlight.volcanor.VolcanoR;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

public class AlertingTask {

    public static boolean alerting = Utils.config.getBoolean("warning-message");
    public static String alertTitle = Utils.config.getString("warning-title");
    public static String alertSubtitle = Utils.config.getString("warning-subtitle");

    public static void AlertAllInRange() {

        Bukkit.getScheduler().scheduleSyncRepeatingTask(VolcanoR.getInstance(), new Runnable() {
            @Override
            public void run() {

                if (alerting) {

                    List<Player> players = Utils.playersinrange;
                    for (Player player : players) {

                        if (Utils.alerted.containsKey(player)) {

                            if (!Utils.alerted.get(player)) {
                                player.sendTitle(Utils.color(alertTitle), Utils.color(alertSubtitle), 2 * 20 , 2* 20, 2* 20);
                                Utils.alerted.put(player, true);
                            }
                        } else {

                            player.sendTitle(Utils.color(alertTitle), Utils.color(alertSubtitle), 2 * 20, 2* 20, 2* 20);
                            Utils.alerted.put(player, true);

                        }
                    }



                }

            }
        } , 20 , 20 * 3);




    }


}
