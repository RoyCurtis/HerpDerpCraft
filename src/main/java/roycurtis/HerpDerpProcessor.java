package roycurtis;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.ClientChatReceivedEvent;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HerpDerpProcessor
{
    final Pattern rgxChat  = Pattern.compile("^(?<prefix>(\\[[a-z0-9-_]+\\])?<[a-z0-9-_]+> )(?<msg>.+)$", Pattern.CASE_INSENSITIVE);
    final Pattern rgxChat2 = Pattern.compile("^(?<prefix>(\\[G(lobal)?\\]) [a-z0-9-_]+: )(?<msg>.+)$", Pattern.CASE_INSENSITIVE);
    final Pattern rgxWord  = Pattern.compile("(\\S+?)([.!?]+ *|\\s|$)", Pattern.CASE_INSENSITIVE);

    final String[] herp = new String[] { "herp", "Herp", "HERP" };
    final String[] derp = new String[] { "derp", "Derp", "DERP" };

    final Random random = new Random();

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event)
    {
        if (!HerpDerp.Enabled)
            return;

        // First, match against known server chat patterns
        Matcher match = rgxChat.matcher( event.message.getUnformattedText() );

        if ( !match.matches() )
            match = rgxChat2.matcher(event.message.getUnformattedText());

        if ( !match.matches() )
            return;

        // Next, replace each word with herpderp
        String  msg       = match.group("msg");
        String  finalMsg  = "";
        Matcher wordMatch = rgxWord.matcher(msg);

        while ( wordMatch.find() )
        {
            String word  = wordMatch.group(1);
            String tail  = wordMatch.group(2);
            int    index = 0;

            // Skip single-letter words
            if ( word.length() <= 1 )
                continue;

            // Pick caps level (default 0; lowercase)
            if ( Character.isUpperCase( word.charAt(0) ) )
                index = Character.isUpperCase( word.charAt(1) )
                        ? 2 : 1;

            // Random choice
            finalMsg += random.nextBoolean()
                ? herp[index]
                : derp[index];
            finalMsg += tail;
        }

        HerpDerp.LOGGER.info( "Original message: %s", event.message.getUnformattedText() );
        event.message = new ChatComponentText( event.message.getFormattedText().replace(msg, finalMsg) );
    }
}
