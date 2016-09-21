package roycurtis;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

import java.util.List;

public class HerpDerpCommand implements ICommand
{
    private final static String NAME = "/herpderp";

    @MethodsReturnNonnullByDefault
    public String getCommandName() { return NAME; }

    @MethodsReturnNonnullByDefault
    public String getCommandUsage(ICommandSender iCommandSender)
    {
        return "/herpderp - Toggles HerpDerpCraft state";
    }

    public List<String> getCommandAliases() { return null; }

    public void execute(MinecraftServer server, ICommandSender who, String[] args)
    throws CommandException
    {
        boolean newState = HerpDerp.Config.ToggleEnabled();
        HerpDerp.MC.ingameGUI.getChatGUI().printChatMessage(
                new TextComponentString( "[HerpDerp] " + (newState ? "Enabled!" : "Disabled!") )
        );
    }

    public boolean checkPermission(MinecraftServer server, ICommandSender who)
    {
        return false;
    }

    public List<String> getTabCompletionOptions(MinecraftServer server, ICommandSender who,
                                                String[] args, BlockPos pos)
    {
        return null;
    }

    public boolean isUsernameIndex(String[] args, int i) { return true; }

    public int compareTo(ICommand o) { return NAME.compareTo( o.getCommandName() ); }
}
