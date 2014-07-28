package roycurtis;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

@Mod(modid = HerpDerp.MODID, version = HerpDerp.VERSION)
public class HerpDerp
{
    // Global mod constants / references
    static final Minecraft MC      = Minecraft.getMinecraft();
    static final String    MODID   = "HerpDerp";
    static final String    VERSION = "1.0.0";
    static final Logger    LOGGER  = LogManager.getFormatterLogger(MODID);
    static final Boolean   DEV     = Boolean.parseBoolean( System.getProperty("development", "false") );

    // Mod IO
    static File          BaseDir;
    static Configuration Config;

    // Mod components
    static HerpDerpCommand   Command   = new HerpDerpCommand();
    static HerpDerpProcessor Processor = new HerpDerpProcessor();

    // Settings
    static Boolean Enabled = false;

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
        MinecraftForge.EVENT_BUS.register(Processor);
        FMLCommonHandler.instance().bus().register(Processor);
        ClientCommandHandler.instance.registerCommand(Command);
        LOGGER.debug("Registered events");

        LOGGER.info("Loaded version %s", VERSION);
    }
}
