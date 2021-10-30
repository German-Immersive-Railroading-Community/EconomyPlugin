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
import economy.menus.BankMenu;
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
				} else if(assistant.isPlayer(args[1]) && assistant.isLong(args[2])) {
					Player player = (Player) sender;
					Player target = Bukkit.getPlayer(args[1]);
					long value = Long.parseLong(args[2]);
					if(!Config.doesPlayerExist(player)) {
						ChatHandler.sendServerErrorMessage(sender, "The bank account for the player " + player.getName() + " does not exist!");
					} else if(!Config.doesPlayerExist(target)) {
						ChatHandler.sendServerErrorMessage(sender, "The bank account for the player " + target.getName() + " does not exist!");
					} else if(value == 0) {
						ChatHandler.sendServerErrorMessage(sender, "You can not send 0 coins!");
					} else if(Config.getPlayerBalance(player) < value) {
						ChatHandler.sendServerErrorMessage(sender, "You have not enough money to transfer " + args[2] + " coins!");
					} else {
						Config.setPlayerBalance(player, Config.getPlayerBalance(player) - value);
						Config.setPlayerBalance(target, Config.getPlayerBalance(target) + value);
						ChatHandler.sendServerMessage(sender, "You successfully transfered " + args[2] + " coins to " + target.getName() + "!");
						ChatHandler.sendServerMessage(sender, "You have " + Config.getPlayerBalance(player) + " coins now!");
						ChatHandler.sendServerMessage(target, player.getName() + " send you " + args[2] + " coins,  you have " + Config.getPlayerBalance(target) + " coins now!");
					}
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
