package me.electroid.toolbox;

import com.sk89q.bukkit.util.BukkitCommandsManager;
import com.sk89q.bukkit.util.CommandsManagerRegistration;
import com.sk89q.minecraft.util.commands.*;
import me.electroid.toolbox.tool.ListTool;
import me.electroid.toolbox.tool.TeleportTool;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;


/**
 * Created by ElectroidFilms on 6/15/15.
 */
public class ToolBox extends JavaPlugin {

    private static ToolBox instance;

    public Tool[] tools;
    public CommandsManager commands;
    public CommandsManagerRegistration commandsRegistry;

    /**
     * Get the plugin instance statically.
     * @return The plugin instance.
     */
    public static ToolBox get() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        this.commands = new BukkitCommandsManager();
        this.commandsRegistry = new CommandsManagerRegistration(this, this.commands);
        tools = new Tool[] {
                new ListTool(),
                new TeleportTool()
        };
        for (Tool tool : tools) {
            if (tool.isCommand()) {
                this.commandsRegistry.register(this.getClass());
            }
            if (tool.isListener()) {
                this.getServer().getPluginManager().registerEvents((Listener) this, this);
            }
        }
    }

    @Override
    public void onDisable() {
        instance = null;
        tools = null;
        this.commands = null;
        this.commandsRegistry = null;
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String commandLabel, String[] args) {
        try {
            this.commands.execute(cmd.getName(), args, sender, sender);
        } catch (CommandPermissionsException e) {
            sender.sendMessage(ChatColor.RED + "You don't have permission.");
        } catch (MissingNestedCommandException e) {
            sender.sendMessage(ChatColor.RED + e.getUsage());
        } catch (CommandUsageException e) {
            sender.sendMessage(ChatColor.RED + e.getMessage());
            sender.sendMessage(ChatColor.RED + "Usage: " + e.getUsage());
        } catch (WrappedCommandException e) {
            sender.sendMessage(ChatColor.RED + "An unknown error has occurred. Please notify an administrator.");
            e.printStackTrace();
        } catch (CommandException e) {
            sender.sendMessage(ChatColor.RED + e.getMessage());
        }
        return true;
    }

}
