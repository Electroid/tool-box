package me.electroid.toolbox.filter;

import me.electroid.toolbox.IFilter;
import org.bukkit.command.CommandSender;

import java.util.*;

/**
 * Created by ElectroidFilms on 7/21/15.
 */
public class Filter<T> implements IFilter<T> {

    private List<FilterEntry<T>> filters;

    /**
     * Create a new parent filter.
     * @param filters The array of filter entries for this filter.
     */
    public Filter(FilterEntry<T>... filters) {
        this.filters = new ArrayList<FilterEntry<T>>();
        for (FilterEntry<T> entry : filters) {
            this.filters.add(entry);
        }
    }

    /**
     * Add a new filter entry to this filter.
     * @param filter The filter entry to add.
     */
    public void addFilter(FilterEntry<T> filter) {
        this.filters.add(filter);
    }

    @Override
    public FilterResult evaluate(T object, CommandSender sender, String filter) {
        FilterResult passes = FilterResult.ALLOW;
        for (FilterEntry<T> entry : filters) {
            for (String name : entry.getNames()) {
                if (filter.contains(name)) {
                    FilterResult result = entry.evaluate(object, sender, filter);
                    if (result.equals(FilterResult.DENY)) {
                        passes = result;
                    }
                    break;
                }
            }

        }
        return passes;
    }

    /**
     * Iterate through a collection and remove any items that don't pass any of the filters.
     * @param objects The collection of objects.
     * @param sender The command sender of the filter.
     * @param filter The string-based filter.
     * @return A new collection with items that only pass the filters.
     */
    public Collection<T> evaluate(Collection<T> objects, CommandSender sender, String filter) {
        Collection<T> collection = new ArrayList<T>();
        for (T object : objects) {
            if (evaluate(object, sender, filter).equals(FilterResult.ALLOW)) {
                collection.add(object);
            }
        }
        return collection;
    }

}
