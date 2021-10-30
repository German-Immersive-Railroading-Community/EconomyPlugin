package economy.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import economy.config.Config;
import economy.menus.ProfileMenu;
import serversystem.utilities.CommandAssistant;

public class ProfileCommand implements CommandExecutor, TabCompleter {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		CommandAssistant assistant = new CommandAssistant(sender);
		if(assistant.isSenderInstanceOfPlayer()) {
			if(args.length == 0) {
				new ProfileMenu((Player)sender);
			} else {
				if (assistant.isPlayer(args[0])) {
					new ProfileMenu(Bukkit.getPlayer(args[0]));
				}
			}
			
		} 
		return true;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		List<String> commands = new ArrayList<String>();
		if(args.length == 1 && sender.hasPermission("economy.profile.inspect")) {
			commands = Config.getPlayers();
		}
		new CommandAssistant(sender).cutArguments(args, commands);
		return commands;
	}

}
