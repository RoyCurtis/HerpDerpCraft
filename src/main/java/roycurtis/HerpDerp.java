package roycurtis;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Mod(modid = HerpDerp.MODID, version = HerpDerp.VERSION)
public class HerpDerp
{
    static final Minecraft MC = Minecraft.getMinecraft();

    static final String  MODID   = "HerpDerp";
    static final String  VERSION = "1.0.0";
    static final Logger  LOGGER  = LogManager.getFormatterLogger(MODID);
    static final Boolean DEV     = Boolean.parseBoolean( System.getProperty("development", "false") );

    static File          BaseDir;
    static Configuration Config;
    static Boolean       Enabled = false;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        BaseDir = new File(event.getModConfigurationDirectory(), MODID);
        Config  = new Configuration( event.getSuggestedConfigurationFile() );

        if ( !BaseDir.exists() )
            BaseDir.mkdir();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // Need events? Uncomment these:
        MinecraftForge.EVENT_BUS.register(this);
        FMLCommonHandler.instance().bus().register(this);
        LOGGER.debug("Registered events");

        LOGGER.info("Loaded version %s", VERSION);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {

    }

    static final Pattern rgxChatMessage = Pattern.compile("^(?<prefix>(\\[[a-z0-9-_]+\\])?<[a-z0-9-_]> )(?<msg>.+)$", Pattern.CASE_INSENSITIVE);

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event)
    {
        if (!Enabled)
            return;

        Matcher match = rgxChatMessage.matcher( event.message.getUnformattedText() );

        if ( !match.matches() )
            return;

        String prefix = match.group("prefix");
        String msg    = match.group("msg");

        event.message = new ChatComponentText(prefix + "Herp derp");
    }
}
