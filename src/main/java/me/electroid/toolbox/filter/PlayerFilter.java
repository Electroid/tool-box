package me.electroid.toolbox.filter;

import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by ElectroidFilms on 7/21/15.
 */
public class PlayerFilter extends Filter<Player> {

    public PlayerFilter() {
        super();
        addFilter(new FilterEntry<Player>("name") {
            @Override
            public FilterResult verify(Player target, CommandSender sender, String value) {
                if (target.getDisplayName(sender).contains(value)) {
                    return FilterResult.ABSTAIN;
                }
                return FilterResult.DENY;
            }
        });
        // TODO FIXME
        addFilter(new FilterEntry<Player>("color", "team") {
            @Override
            public FilterResult verify(Player target, CommandSender sender, String value) {
                if (sender instanceof Player) {
                    return FilterResult.ABSTAIN;
                }
                return FilterResult.ERROR;
            }
        });
        addFilter(new FilterEntry<Player>("radius", "around", "near") {
            @Override
            public FilterResult verify(Player target, CommandSender sender, String value) {
                if (sender instanceof Player) {
                    double distance;
                    try {
                        distance = Double.parseDouble(value);
                    } catch (Exception e) {
                        return FilterResult.ERROR;
                    }
                    if (target.getLocation().distanceSquared(((Player) sender).getLocation()) <= distance) {
                        return FilterResult.ABSTAIN;
                    }
                }
                return FilterResult.ERROR;
            }
        });
        addFilter(new FilterEntry<Player>("metadata", "meta", "data") {
            @Override
            public FilterResult verify(Player target, CommandSender sender, String value) {
                if (target.hasMetadata(value)) {
                    return FilterResult.ABSTAIN;
                }
                return FilterResult.DENY;
            }
        });
        addFilter(new FilterEntry<Player>("permission", "perms", "perm") {
            @Override
            public FilterResult verify(Player target, CommandSender sender, String value) {
                if (target.hasPermission(value)) {
                    return FilterResult.ABSTAIN;
                }
                return FilterResult.DENY;
            }
        });
        addFilter(new FilterEntry<Player>("operator", "op") {
            @Override
            public FilterResult verify(Player target, CommandSender sender, String value) {
                if (target.isOp() && Boolean.parseBoolean(value)) {
                    return FilterResult.ABSTAIN;
                }
                return FilterResult.DENY;
            }
        });
        addFilter(new FilterEntry<Player>("gamemode", "mode") {
            @Override
            public FilterResult verify(Player target, CommandSender sender, String value) {
                if (target.getGameMode().equals(GameMode.valueOf(value))) {
                    return FilterResult.ABSTAIN;
                }
                return FilterResult.DENY;
            }
        });
        addFilter(new FilterEntry<Player>("health", "hearts") {
            @Override
            public FilterResult verify(Player target, CommandSender sender, String value) {
                double health;
                try {
                    health = Double.parseDouble(value);
                } catch (Exception e) {
                    return FilterResult.ERROR;
                }
                if (target.getHealth() >= health) {
                    return FilterResult.ABSTAIN;
                }
                return FilterResult.DENY;
            }
        });
        addFilter(new FilterEntry<Player>("world") {
            @Override
            public FilterResult verify(Player target, CommandSender sender, String value) {
                if (sender instanceof Player) {
                    if (target.getWorld().equals(((Player) sender).getWorld())) {
                        return FilterResult.ABSTAIN;
                    }
                }
                return FilterResult.ERROR;
            }
        });
    }

}
