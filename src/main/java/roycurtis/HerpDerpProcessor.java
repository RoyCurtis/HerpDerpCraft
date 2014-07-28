package roycurtis;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.event.ClientChatReceivedEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HerpDerpProcessor
{
    static final String TOKEN_PLAYER = "(?<player>[A-Za-z0-9-_]+)";
    static final String TOKEN_FORMAT = "(§[0-9a-fklmnorA-FKLMNOR])*";

    static final Pattern REGEX_WORD   = Pattern.compile("(?<word>\\S+?)(?<tail>[,.:;!?]+ *|\\s|$)", Pattern.CASE_INSENSITIVE);
    static final Pattern REGEX_FORMAT = Pattern.compile("§[0-9a-fklmnor]", Pattern.CASE_INSENSITIVE);

    static final String[] HERPS = new String[] { "herp", "Herp", "HERP" };
    static final String[] DERPS = new String[] { "derp", "Derp", "DERP" };

    Pattern[] filters;

    public HerpDerpProcessor(String[] rawFilters)
    {
        filters = new Pattern[rawFilters.length];

        for (int idx = 0; idx < rawFilters.length; idx++)
        {
            String rawFilter = rawFilters[idx];
            rawFilter = rawFilter.replaceAll("%player%", TOKEN_PLAYER);
            rawFilter = rawFilter.replaceAll("%format%", TOKEN_FORMAT);

            filters[idx] = Pattern.compile(rawFilter, Pattern.CASE_INSENSITIVE);
        }
    }

    @SubscribeEvent
    public void onJoin(FMLNetworkEvent.ClientConnectedToServerEvent event)
    {
        if ( HerpDerp.Config.IsEnabled() )
            HerpDerp.MC.ingameGUI.getChatGUI().printChatMessage( new ChatComponentText("[HerpDerp] Enabled!") );
    }

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event)
    {
        if ( !HerpDerp.Config.IsEnabled() )
            return;

        // First, match against configured server chat patterns
        Matcher match = null;
        for (int i = 0; i < filters.length; i++)
        {
            match = filters[i].matcher( event.message.getFormattedText() );

            if ( match.matches() )
                break;
            else if ( i == filters.length - 1 )
                return;
        }

        // Next, replace each word with herpderp
        assert match != null;
        String  prefix    = match.group("prefix");
        String  msg       = REGEX_FORMAT.matcher( match.group("msg") ).replaceAll("");
        String  finalMsg  = "";
        Matcher wordMatch = REGEX_WORD.matcher(msg);

        while ( wordMatch.find() )
        {
            String word  = wordMatch.group("word");
            String tail  = wordMatch.group("tail");
            int    index = 0;

            // Skip single-letter words
            if ( word.length() <= 1 )
                continue;

            // Pick caps level (default 0; lowercase)
            if ( Character.isUpperCase( word.charAt(0) ) )
                index = Character.isUpperCase( word.charAt(1) )
                    ? 2 : 1;

            // Random choice
            finalMsg += herpderp(index);
            finalMsg += tail;
        }

        if ( StringUtils.isNullOrEmpty( finalMsg.trim() ) )
            finalMsg = herpderp(0);

        HerpDerp.LOGGER.info( "Original message: %s", event.message.getFormattedText() );
        event.message = new ChatComponentText(prefix + finalMsg);
    }

    public String herpderp(int idx)
    {
        return HerpDerp.RANDOM.nextBoolean()
            ? HERPS[idx]
            : DERPS[idx];
    }
}
