package roycurtis;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

@Mod(
    modid   = HerpDerp.MODID,
    name    = HerpDerp.MODID,
    version = HerpDerp.VERSION,

    acceptableRemoteVersions = "*",
    acceptableSaveVersions   = ""
)
public class HerpDerp
{
    // Global mod constants / references
    static final Minecraft MC      = Minecraft.getMinecraft();
    static final String    MODID   = "HerpDerp";
    static final String    VERSION = "0.0.1";
    static final Logger    LOGGER  = LogManager.getFormatterLogger(MODID);
    static final Boolean   DEV     = Boolean.parseBoolean( System.getProperty("development", "false") );
    static final Random    RANDOM  = new Random();

    // Mod components
    static HerpDerpCommand   Command;
    static HerpDerpProcessor Processor;
    static HerpDerpConfig    Config;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        Config = new HerpDerpConfig( event.getSuggestedConfigurationFile() );
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        Command   = new HerpDerpCommand();
        Processor = new HerpDerpProcessor( Config.Filters );

        MinecraftForge.EVENT_BUS.register(Processor);
        FMLCommonHandler.instance().bus().register(Processor);
        ClientCommandHandler.instance.registerCommand(Command);
        LOGGER.debug("Registered events");

        LOGGER.info("Loaded version %s", VERSION);
    }
}
