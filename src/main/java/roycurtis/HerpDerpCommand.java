package roycurtis;

import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentTranslation;

import java.util.List;

public class HerpDerpCommand implements ICommand
{
    static HerpDerpCommand instance = new HerpDerpCommand();

    Minecraft mc = Minecraft.getMinecraft();

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
        HerpDerp.Enabled = !HerpDerp.Enabled;
        mc.ingameGUI.getChatGUI().printChatMessage( new ChatComponentTranslation("Herp Derp is %s", HerpDerp.Enabled ? "enabled" : "disabled") );
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender var1) {
        return false;
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
