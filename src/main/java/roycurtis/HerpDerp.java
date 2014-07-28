package roycurtis;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

@Mod(modid = HerpDerp.MODID, version = HerpDerp.VERSION, name = HerpDerp.MODID)
@SideOnly(Side.CLIENT)
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
