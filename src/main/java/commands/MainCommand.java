package commands;

import me.deadlight.volcanor.Utils;
import me.deadlight.volcanor.VolcanoR;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class MainCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 0) {

                player.sendMessage(Utils.prefix + Utils.color("&cDo you want help? /volcanor help"));

            } else if (args.length > 0) {

                if (args[0].equalsIgnoreCase("help")) {

                    player.sendMessage(Utils.prefix + Utils.color("Help page: \n &7- /volcanor setlocation - Set the location of volcano \n &7- /volcanor addmob/removemob mob percentage - Adding/Removing a mob \n &7- /volcanor addblock/removeblock block percentage - Adding/Removing a block"));

                } else if (args[0].equalsIgnoreCase("setlocation")) {

                    VolcanoR.getInstance().getConfig().set("volcano-location", Utils.convertLocToString(player.getLocation()));
                    VolcanoR.getInstance().saveConfig();
                    player.sendMessage(Utils.prefix + Utils.color("&cLocation successfully set."));
                } else if (args[0].equalsIgnoreCase("addmob")) {

                    if (args.length >= 3) {

                        try {

                            EntityType.valueOf(args[1]);
                            int num = Integer.parseInt(args[2]);
                            if ((1 <= num && num >= 100)) {

                                if (VolcanoR.getInstance().getConfig().getStringList("specified-mobs").contains(args[1])) {
                                    player.sendMessage(Utils.prefix + Utils.color("&cThis item already exists."));
                                } else {

                                    List<String> list = VolcanoR.getInstance().getConfig().getStringList("specified-mobs");
                                    list.add(args[1] + ":" + args[2]);
                                    VolcanoR.getInstance().getConfig().set("specified-mobs", list);
                                    VolcanoR.getInstance().saveConfig();
                                    player.sendMessage(Utils.prefix + Utils.color("&aItem successfully added to the list."));
                                }
                            } else {

                                player.sendMessage(Utils.prefix + Utils.color("&cPut the percentage between 1 & 100"));

                            }

                        } catch (Exception ex) {

                            player.sendMessage(Utils.prefix + Utils.color("&cWrong input! Try again."));
                        }


                    } else {

                        player.sendMessage(Utils.prefix + "&cYou missed an argument for sure!");

                    }

                } else if (args[0].equalsIgnoreCase("addblock")) {

                    if (args.length >= 3) {

                        try {

                            Material matt = Material.matchMaterial(args[1]);
                            int num = Integer.parseInt(args[2]);
                            if ((1 <= num && num >= 100) || matt != null) {

                                if (VolcanoR.getInstance().getConfig().getStringList("specified-blocks").contains(args[1])) {
                                    player.sendMessage(Utils.prefix + Utils.color("&cThis item already exists."));
                                } else {

                                    List<String> list = VolcanoR.getInstance().getConfig().getStringList("specified-blocks");
                                    list.add(args[1] + ":" + args[2]);
                                    VolcanoR.getInstance().getConfig().set("specified-blocks", list);
                                    VolcanoR.getInstance().saveConfig();
                                    player.sendMessage(Utils.prefix + Utils.color("&aItem successfully added to the list."));
                                }
                            } else {

                                player.sendMessage(Utils.prefix + Utils.color("&cThere is a problem with block name or percentage!"));

                            }

                        } catch (Exception ex) {

                            player.sendMessage(Utils.prefix + Utils.color("&cWrong input! Try again."));
                        }


                    } else {

                        player.sendMessage(Utils.prefix + "&cYou missed an argument for sure!");

                    }

                } else if (args[0].equalsIgnoreCase("removeblock")) {

                    if (args.length >= 2) {


                        Material matt = Material.matchMaterial(args[1]);
                        if (matt != null) {

                            List<String> list = VolcanoR.getInstance().getConfig().getStringList("specified-blocks");
                            String found = null;
                            for (String i : list) {

                                if (args[1].equalsIgnoreCase(Arrays.asList(i.split(":")).get(0))) {

                                    found = i;
                                }
                            }
                            if (found != null) {

                                list.remove(found);

                                VolcanoR.getInstance().getConfig().set("specified-blocks", list);
                                VolcanoR.getInstance().saveConfig();
                                player.sendMessage(Utils.prefix + Utils.color("&aItem successfully removed from the list."));

                            } else {
                                player.sendMessage(Utils.prefix + Utils.color("&cThis item does not exists."));

                            }


                        } else {

                            player.sendMessage(Utils.prefix + Utils.color("&cThere is a problem with block name!"));

                        }


                    } else {

                        player.sendMessage(Utils.prefix + "&cYou missed an argument for sure!");

                    }

                } else if (args[0].equalsIgnoreCase("removemob")) {

                    if (args.length >= 2) {


                        try {

                            EntityType mob = EntityType.valueOf(args[1]);
                            List<String> list = VolcanoR.getInstance().getConfig().getStringList("specified-mobs");
                            String found = null;
                            for (String i : list) {

                                if (args[1].equalsIgnoreCase(Arrays.asList(i.split(":")).get(0))) {

                                    found = i;
                                }
                            }
                            if (found != null) {

                                list.remove(found);

                                VolcanoR.getInstance().getConfig().set("specified-mobs", list);
                                VolcanoR.getInstance().saveConfig();
                                player.sendMessage(Utils.prefix + Utils.color("&aItem successfully removed from the list."));

                            } else {
                                player.sendMessage(Utils.prefix + Utils.color("&cThis item does not exists."));

                            }


                        } catch (Exception ex){

                            player.sendMessage(Utils.prefix + Utils.color("&cThere is a problem with mob name!"));

                        }


                    } else {

                        player.sendMessage(Utils.prefix + "&cYou missed an argument for sure!");

                    }

                }
            }  else {

                player.sendMessage(Utils.prefix + Utils.color("&cDo you want help? /volcanor help"));

            }

        } else {

            Utils.sendToConsole("&eSorry, but no console command for now");
        }

        return false;
    }
}
