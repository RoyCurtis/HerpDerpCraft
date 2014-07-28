package roycurtis;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.io.File;

public class HerpDerpConfig
{
    static final String[] DEFAULT_FILTERS = new String[]
        {
            "^(?<prefix>(\\[[a-z0-9-_]+\\])?<%format%%player%%format%> %format%)(?<msg>.+)$",
            "^(?<prefix>(\\[[a-z0-9-_]+\\])?%format%<%player%%format%(@%format%[a-z]+%format%)?> )(?<msg>.+)$",
            "^(?<prefix>%format%\\[G(lobal)?\\] %format%%player%%format%: )(?<msg>.+)$",
            "^(?<prefix>%format%\\* %format%%player%%format% )(?<msg>.+?)$",
            "^(?<prefix>%format%\\[G(lobal)?\\] \\* %format%%player%%format% )(?<msg>.+?)$",
        };

    public String[] Filters;

    Property enabled;
    Property filters;

    Configuration forgeConfig;

    public HerpDerpConfig(File path)
    {
        forgeConfig = new Configuration(path);
        forgeConfig.load();

        enabled = forgeConfig.get(Configuration.CATEGORY_GENERAL, "enabled", false, "Whether HerpDerp is enabled for chat");
        filters = forgeConfig.get(Configuration.CATEGORY_GENERAL, "filters", DEFAULT_FILTERS, "List of case-insensitive regex patterns to match against chat. Each pattern must at least have a 'prefix', 'player' and 'msg' subcapture named group. Use %player% and %format% to match player names and formatting codes.");
        Filters = filters.getStringList();

        forgeConfig.save();
    }

    public boolean ToggleEnabled()
    {
        enabled.set( !enabled.getBoolean() );
        forgeConfig.save();

        return enabled.getBoolean();
    }

    public boolean IsEnabled()
    {
        return enabled.getBoolean();
    }
}
