package roycurtis;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.io.File;

import static roycurtis.HerpDerp.LOGGER;

public class Config
{
    private static final String GENERAL = "General";

    private static final String[] DEFAULT_FILTERS = new String[]
    {
        // [tag] <player> Hello world!
        "^(?<prefix>(\\[[a-z0-9-_]+\\])?<%format%%player%%format%> %format%)(?<msg>.+)$",
        // [tag] <player@tag> Hello world!
        "^(?<prefix>(\\[[a-z0-9-_]+\\])?%format%<%player%%format%(@%format%[a-z]+%format%)?> )(?<msg>.+)$",
        // [G] player: Hello world!
        "^(?<prefix>%format%\\[G(lobal)?\\] %format%%player%%format%: )(?<msg>.+)$",
        // * player does something
        "^(?<prefix>%format%\\* %format%%player%%format% )(?<msg>.+?)$",
        // [G] * player does something
        "^(?<prefix>%format%\\[G(lobal)?\\] \\* %format%%player%%format% )(?<msg>.+?)$",
    };

    private Configuration config;

    private Property propEnabled;
    private Property propFilters;

    public Config(File path)
    {
        config = new Configuration(path);

        propEnabled = config.get(GENERAL, "enabled", false,
            "Whether HerpDerp is enabled for chat");

        propFilters = config.get(GENERAL, "filters", DEFAULT_FILTERS,
            "List of case-insensitive regex patterns to match against chat. Each\n"  +
            "pattern must at least have a 'prefix', 'player' and 'msg' subcapture\n" +
            "named group. Use %player% and %format% to match player names and\n"     +
            "formatting codes."
        );

        check();
        config.save();
    }

    public boolean toggleEnabled()
    {
        boolean enabled = !propEnabled.getBoolean();

        propEnabled.set(enabled);
        config.save();
        return enabled;
    }

    public boolean isEnabled() { return propEnabled.getBoolean(); }

    public String[] getFilters() { return propFilters.getStringList(); }

    /** Checks the loaded configuration and makes adjustments based on other config */
    void check()
    {
        String[] filters = propFilters.getStringList();

        // Check if filters list is empty
        if (filters.length == 0)
        {
            LOGGER.warn("The list of chat filters is empty! Please fix this in the " +
                "config. The default list of filters will be used.");
            propFilters.set(DEFAULT_FILTERS);
        }
    }
}
