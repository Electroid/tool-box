package me.electroid.toolbox.filter;

import com.google.common.base.Preconditions;
import me.electroid.toolbox.IFilter;
import org.bukkit.command.CommandSender;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ElectroidFilms on 7/21/15.
 */
public abstract class FilterEntry<T> implements IFilter<T> {

    private static final String DIVIDER = "(:|=)";
    private static final String SPACE = "(| {1,})";
    private static final String VALUE = "[a-zA-Z0-9_.]{1,}";

    private final String[] names;
    /**
     * Creates a new FilterEntry.
     * Try to find its key and subsequent value.
     * @param names The array of acceptable keys.
     */
    public FilterEntry(String... names) {
        this.names = Preconditions.checkNotNull(names, "names cannot be null");
        Preconditions.checkArgument(names.length >= 1, "names cannot be an empty array");
    }

    public final String[] getNames() {
        return this.names;
    }

    @Override
    public FilterResult evaluate(T object, CommandSender sender, String filter) {
        String regex = "";
        for (String key : names) {
            regex += "(" + key + SPACE + DIVIDER + SPACE + VALUE + ")|";
        }
        Matcher matcher = Pattern.compile(regex.substring(0, regex.lastIndexOf("|")), Pattern.CASE_INSENSITIVE).matcher(filter);
        if (matcher.find()) {
            String value = matcher.group(0);
            value = value.replaceAll(VALUE + SPACE + DIVIDER, "").trim();
            return verify(object, sender, value);
        }
        return FilterResult.DENY;
    }

    /**
     * Verify the object successfully evaluates given the value.
     * @param object The object to evaluate.
     * @param sender The command sender for the filter.
     * @param value The value to this filter.
     * @return Whether the object meets the evaluation requirements.
     */
    public abstract FilterResult verify(T object, CommandSender sender, String value);

}
