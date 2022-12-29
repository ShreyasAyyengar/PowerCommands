package me.woodsmc.powercommands;

import me.woodsmc.powercommands.actionslib.actions.Action;
import me.woodsmc.powercommands.actionslib.ActionYML;
import me.woodsmc.powercommands.commands.PowerCommandsCommand;
import me.woodsmc.powercommands.inventory.InventoryManager;
import me.woodsmc.powercommands.messages.StringManager;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public final class PowerCommands extends JavaPlugin {

    private ActionYML actionYML;
    private Action action;

    @Override
    public void onEnable() {
        PluginManager pM = getServer().getPluginManager();
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        //files
        actionYML = new ActionYML(this);
        File file = new File(this.getDataFolder(), "commandmanager/README.txt");
        if(!file.exists()){
            try {
                file.createNewFile();
                BufferedWriter out = new BufferedWriter(new FileWriter(file));
                out.write("me.woodsmc.powercommands.plugins.commandmanager.README.txt\n" +
                        "\n" +
                        "Please do not change anything in any of these files in these files!\n" +
                        "If you do change any of these files Created commands will NOT work!\n" +
                        "\n" +
                        "\n" +
                        "HOW TO FIX IF YOU CHANGED THEM\n" +
                        "Go into the files and change 'registered' to false, IN ALL FILES!\n" +
                        "If this doesn't work please contact developer!\n" +
                        "\n" +
                        "WAYS TO CONTACT:\n" +
                        "Discord: https://discord.com/invite/5rJeEN8V7H\n" +
                        "Discord DM: WoodsGeorgeJr#2995\n" +
                        "SpigotMc Conversation: https://www.spigotmc.org/members/woodsgeorgejr.1084884/");
                out.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        reloadConfig();
        //action
        action = new Action();
        action.RegisterActions();

        //commands
        getCommand("powercommands").setExecutor(new PowerCommandsCommand());
        getCommand("powercommands").setTabCompleter(new PowerCommandsCommand());

        //listeners
        pM.registerEvents(new InventoryManager(), this);

        //enable meesage
        getServer().getConsoleSender().sendMessage(StringManager.getPrefix() + " §dHas enabled successfully!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public ActionYML getActionYML() {
        return actionYML;
    }
}
