package roycurtis;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

import java.util.Collections;
import java.util.List;

import static roycurtis.HerpDerp.CLIENT;
import static roycurtis.HerpDerp.CONFIG;

public class HerpDerpCommand implements ICommand
{
    private final static String NAME = "herpderp";

    private final static List<String> ALIASES = Collections.singletonList(NAME);

    @MethodsReturnNonnullByDefault
    public String getCommandName() { return NAME; }

    @MethodsReturnNonnullByDefault
    public String getCommandUsage(ICommandSender iCommandSender)
    {
        return "/herpderp - Toggles HerpDerpCraft state";
    }

    public List<String> getCommandAliases() { return ALIASES; }

    public void execute(MinecraftServer server, ICommandSender who, String[] args)
    throws CommandException
    {
        boolean state = CONFIG.toggleEnabled();
        String  msg   = "[HerpDerp] " + (state ? "Enabled!" : "Disabled!");

        CLIENT.ingameGUI.getChatGUI().printChatMessage( new TextComponentString(msg) );
    }

    public boolean checkPermission(MinecraftServer server, ICommandSender who)
    {
        return true;
    }

    public List<String> getTabCompletionOptions
    (MinecraftServer server, ICommandSender who, String[] args, BlockPos pos)
    {
        return CommandBase.getListOfStringsMatchingLastWord(
            args, server.getAllUsernames() );
    }

    public boolean isUsernameIndex(String[] args, int i) { return true; }

    public int compareTo(ICommand o) { return NAME.compareTo( o.getCommandName() ); }
}
