package me.electroid.toolbox;

import me.electroid.toolbox.filter.FilterResult;
import org.bukkit.command.CommandSender;

/**
 * Created by ElectroidFilms on 7/21/15.
 */
public interface IFilter<T> {

    /**
     * Evaluate whether an object passes the filter.
     * @param object The object to check.
     * @param sender The command sender of the filter.
     * @param filter The filter in string-form.
     * @return The result of the object passing through the filter.
     */
    FilterResult evaluate(T object, CommandSender sender, String filter);

}
