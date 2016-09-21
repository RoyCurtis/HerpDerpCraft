package roycurtis;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Main class and mod definition of HerpDerpCraft. Holds references to its singleton
 * instance and management objects. Should only ever be created by Forge.
 */
@Mod(
    modid   = HerpDerp.MODID,
    name    = HerpDerp.MODID,
    version = HerpDerp.VERSION,

    acceptableRemoteVersions = "*",
    acceptableSaveVersions   = ""
)
public class HerpDerp
{
    /** Frozen at 0.0.1 to prevent misleading world save error */
    static final String VERSION = "0.0.1";
    static final String MODID   = "HerpDerp";
    static final Logger LOGGER  = LogManager.getFormatterLogger(MODID);

    /** Singleton instance of HerpDerp, created by Forge */
    public static HerpDerp  INSTANCE = null;
    /** Singleton instance of HerpDerp's config */
    public static Config    CONFIG   = null;
    /** Shortcut reference to vanilla client instance */
    public static Minecraft CLIENT   = null;

    @EventHandler
    @SideOnly(Side.SERVER)
    public void serverPreInit(FMLPreInitializationEvent event)
    {
        LOGGER.error("This mod is intended only for use on clients");
        LOGGER.error("Please consider removing this mod from your server");
    }

    @EventHandler
    @SideOnly(Side.CLIENT)
    public void clientPreInit(FMLPreInitializationEvent event)
    {
        INSTANCE = this;
        CONFIG   = new Config( event.getSuggestedConfigurationFile() );
        CLIENT   = Minecraft.getMinecraft();
    }

    @EventHandler
    @SideOnly(Side.CLIENT)
    public void clientInit(FMLInitializationEvent event)
    {
        HerpDerpCommand command   = new HerpDerpCommand();
        ChatListener    processor = new ChatListener();

        MinecraftForge.EVENT_BUS.register(processor);
        ClientCommandHandler.instance.registerCommand(command);
        LOGGER.info("[HerpDerp] Loaded version %s", VERSION);
    }
}
