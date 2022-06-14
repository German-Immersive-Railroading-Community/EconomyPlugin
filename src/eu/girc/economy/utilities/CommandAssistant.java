package eu.girc.economy.utilities;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.girc.economy.utilities.ChatUtil.ErrorMessage;

public class CommandAssistant {
	
	private CommandSender sender;
	
	public CommandAssistant(CommandSender sender) {
		this.sender = sender;
	}
	
	public boolean isSenderInstanceOfPlayer() {
		if(sender instanceof Player) {
			return true;
		}
		ChatUtil.sendServerErrorMessage(sender, ErrorMessage.ONLYPLAYER);
		return false;	
	}
	
	public boolean isSenderInstanceOfPlayer(boolean notenougharguments) {
		if(sender instanceof Player) {
			return true;
		}
		if(notenougharguments) {
			ChatUtil.sendServerErrorMessage(sender, ErrorMessage.NOTENOUGHARGUMENTS);
		} else {
			ChatUtil.sendServerErrorMessage(sender, ErrorMessage.ONLYPLAYER);
		}
		return false;
	}
	
	public boolean isSenderNotInstanceOfPlayer() {
		if(!(sender instanceof Player)) {
			return true;
		} 
		ChatUtil.sendServerErrorMessage(sender, ErrorMessage.ONLYCONSOLE);
		return false;
	}
	
	public boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException exception) {
			ChatUtil.sendServerErrorMessage(sender, "The value " + value + " is not a valid number!");
			return false;
		}
	}
	
	public boolean isLong(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException exception) {
			ChatUtil.sendServerErrorMessage(sender, "The value " + value + " is not a valid number!");
			return false;
		}
	}
	
	public boolean isWorld(String world) {
		if(Bukkit.getWorld(world) != null) {
			return true;
		}
		ChatUtil.sendServerErrorMessage(sender, "The world " + world + " does not exist!");
		return false;
	}
	
	public boolean isPlayer(String player) {
		if(Bukkit.getPlayer(player) != null) {
			return true;
		}
		ChatUtil.sendServerErrorMessage(sender, "The player " + player + " is not online!");
		return false;
	}
	
	public boolean hasPlayerPermission(Player player, String permission) {
		if(player.hasPermission(permission)) {
			return true;
		} else {
			ChatUtil.sendServerErrorMessage(sender, ErrorMessage.NOPERMISSION);
			return false;
		}
	}
	
	public boolean hasSenderPermission(String permission) {
		if(sender.hasPermission(permission)) {
			return true;
		} else {
			ChatUtil.sendServerErrorMessage(sender, ErrorMessage.NOPERMISSION);
			return false;
		}
	}
	
	public boolean hasPermission(String permission) {
		if(sender.hasPermission(permission)) {
			return true;
		} 
		ChatUtil.sendServerErrorMessage(sender, ErrorMessage.NOPERMISSION);
		return false;
	}
	
	public boolean hasPermissionOrIsConsole(String permission) {
		if(!(sender instanceof Player) || sender.hasPermission(permission)) {
			return true;
		} 
		ChatUtil.sendServerErrorMessage(sender, ErrorMessage.NOPERMISSION);
		return false;
	}
	
	public boolean isGameMode(String gamemode) {
		for(GameMode gm : GameMode.values()) {
			if(gm.toString().equalsIgnoreCase(gamemode)) {
				return true;
			}
		}
		ChatUtil.sendServerErrorMessage(sender, gamemode + " is not a valid gamemode!");
		return false;
	}
	
	public boolean isMaterial(String material) {
		if(material.startsWith("minecraft:")) {
			material = material.substring(10);
		}
		for(Material mt : Material.values()) {
			if(mt.toString().equalsIgnoreCase(material)) {
				return true;
			}
		}
		ChatUtil.sendServerErrorMessage(sender, material + " is not a valid material!");
		return false;
	}
	
	public boolean isBoolean(String bool) {
		if(bool.equalsIgnoreCase("true") || bool.equalsIgnoreCase("false")) {
			return true;
		}
		ChatUtil.sendServerErrorMessage(sender, bool + " is not a valid boolean!");
		return false;
	}
	
	public boolean hasMinArguments(int min, String args[]) {
		if(args.length >= min) {
			return true;
		}
		ChatUtil.sendServerErrorMessage(sender, ErrorMessage.NOTENOUGHARGUMENTS);
		return false;
	}
	
	public boolean hasMaxArguments(int min, String args[]) {
		if(args.length <= min) {
			return true;
		}
		return false;
	}
	
	public boolean isPath(int position, String path, int min, String args[]) {
		if(args.length >= min && args[position].equals(path)) {
			return true;
		}
		return false;
	}
	
	public boolean isPath(int position, String path, int min, int max, String args[]) {
		if(args.length >= min && args.length <= max && args[position].equals(path)) {
			return true;
		}
		return false;
	}
	
	public List<String> cutArguments(String args[], List<String> commands) {
		String command = args[args.length - 1];
		List<String> output = new ArrayList<>();
		if(!command.isEmpty() && !command.equals("")) {
			for (String string : commands) {
				if(string.startsWith(command)) {
					output.add(string);
				}
			}
			return output;
		} else {
			return commands;
		}
	}

}
