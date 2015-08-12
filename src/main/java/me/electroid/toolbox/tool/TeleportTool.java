package me.electroid.toolbox.tool;

import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import com.sk89q.minecraft.util.commands.CommandPermissions;
import com.sk89q.minecraft.util.commands.Console;
import me.electroid.toolbox.Tool;
import me.electroid.toolbox.filter.PlayerFilter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by ElectroidFilms on 7/29/15.
 */
public class TeleportTool extends Tool {

    public TeleportTool() {
        super(true, false);
    }

    @Command(
            aliases = {"teleport", "tp"},
            desc = "Lists players that are on the server.",
            max = -1,
            min = 0,
            usage = "[filter]"
    )
    @CommandPermissions("toolbox.list")
    @Console
    public static void list(final CommandContext args, final CommandSender sender) {
        // TODO
    }
}
