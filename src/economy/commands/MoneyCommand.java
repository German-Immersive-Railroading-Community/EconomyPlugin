package economy.commands;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import economy.config.Config;
import economy.menus.BankMenu;
import economy.objects.EconomyPlayer;
import serversystem.handler.ChatHandler;
import serversystem.handler.ChatHandler.ErrorMessage;
import serversystem.utilities.CommandAssistant;

public class MoneyCommand implements CommandExecutor, TabCompleter{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		CommandAssistant assistant = new CommandAssistant(sender);
		if(assistant.isSenderInstanceOfPlayer()) {
			if(args.length >= 1 && args[0].equals("transfer")) {
				if(args.length < 3) {
					ChatHandler.sendServerErrorMessage(sender, ErrorMessage.NOTENOUGHARGUMENTS);
				} else if(EconomyPlayer.getPlayerNames().contains(args[1]) && assistant.isLong(args[2])) {
					EconomyPlayer player = EconomyPlayer.getPlayerByBucketPlayer((Player) sender);
					EconomyPlayer target = EconomyPlayer.getPlayerByName(args[1]);
					long value = Long.parseLong(args[2]);
					if (value > 0) {
						if (player.getBalance() >= value) {
							player.tranferMoney(target, value);
							ChatHandler.sendServerMessage(sender, "You successfully transfered " + args[2] + " coins to " + args[1] + "!");
							ChatHandler.sendServerMessage(sender, "You have " + player.getBalance() + " coins now!");
							if (target.isOnline()) {
								ChatHandler.sendServerMessage(target.getPlayer(), player.getPlayer().getName() + " send you " + args[2] + " coins,  you have " + target.getBalance() + " coins now!");
							}
						} else {
							ChatHandler.sendServerErrorMessage(sender, "You don't have enough coins!");
						}
					} else {
						ChatHandler.sendServerErrorMessage(sender, "You can not send less than 1 coin!");
					}
				} else {
					ChatHandler.sendServerErrorMessage(sender, "An account for the player " + args[1] + " does not exist!");
				}
				return true;
			} else {
				new BankMenu((Player)sender);
			}
		}
		return true;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		List<String> commands = new ArrayList<String>();
		if(args.length == 1) {
			commands.add("transfer");
		} else if(args.length == 2) {
			commands = Config.getPlayers();
		}
		new CommandAssistant(sender).cutArguments(args, commands);
		return commands;
	}
	
}
