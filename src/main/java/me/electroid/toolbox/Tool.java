package me.electroid.toolbox;

/**
 * Created by ElectroidFilms on 7/21/15.
 */
public class Tool {

    private final boolean command;
    private final boolean listener;

    /**
     * Creates a new tool.
     * A class-extension for handling command/listener registration.
     * @param command Whether this tool is a command.
     * @param listener Whether this tool is a listener.
     */
    public Tool(boolean command, boolean listener) {
        this.command = command;
        this.listener = listener;
    }

    /**
     * Get whether this tool is a command.
     * @return Whether this tool is a command.
     */
    public final boolean isCommand() {
        return this.command;
    }

    /**
     * Get whether this tool is a listener.
     * @return Whether this tool is a listener.
     */
    public final boolean isListener() {
        return this.listener;
    }

}
