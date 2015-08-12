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
 * Created by ElectroidFilms on 7/21/15.
 */
public class BroadcastTool extends Tool {

    public BroadcastTool() {
        super(true, false);
    }

    @Command(
            aliases = {"broadcast", "alert"},
            desc = "Broadcast a message to a group of players.",
            max = -1,
            min = 0,
            usage = "<message> [filter]"
    )
    @CommandPermissions("toolbox.broadcast")
    @Console
    public static void list(final CommandContext args, final CommandSender sender) {
        // TODO
    }

}
