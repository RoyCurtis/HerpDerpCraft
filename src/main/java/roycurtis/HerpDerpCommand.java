package roycurtis;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentTranslation;

import java.util.List;

public class HerpDerpCommand implements ICommand
{
    @Override
    public String getCommandName() {
        return "herpderp";
    }

    @Override
    public String getCommandUsage(ICommandSender var1) {
        return "/herpderp";
    }

    @Override
    public List getCommandAliases() {
        return null;
    }

    @Override
    public void processCommand(ICommandSender var1, String[] var2)
    {
        boolean newState = HerpDerp.Config.ToggleEnabled();
        HerpDerp.MC.ingameGUI.getChatGUI().printChatMessage( new ChatComponentTranslation("[HerpDerp] %s", newState ? "Enabled!" : "Disabled!") );
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender var1) {
        return true;
    }

    @Override
    public List addTabCompletionOptions(ICommandSender var1, String[] var2) {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] var1, int var2) {
        return false;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
