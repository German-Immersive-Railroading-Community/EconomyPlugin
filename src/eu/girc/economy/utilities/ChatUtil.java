package eu.girc.economy.utilities;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
public class ChatUtil implements Listener {
	
	private static ChatColor messagecolor = ChatColor.GOLD;
	private static ChatColor errorcolor = ChatColor.RED;
	private static String prefix = ChatColor.GOLD + "[Economy]";
	
	public static enum ErrorMessage{ONLYCONSOLE, ONLYPLAYER, NOPERMISSION, NOTENOUGHARGUMENTS}
	public static enum TitleType{TITLE, SUBTITLE, ACTIONBAR}
	
	public static void sendServerMessage(Player player, String message) {
		player.sendMessage(prefix + messagecolor + " " + message);
	}
	
	public static void sendServerMessage(CommandSender sender, String message) {
		sender.sendMessage(prefix + messagecolor + " " + message);
	}
	
	public static void sendServerErrorMessage(Player player, String message) {
		player.sendMessage(prefix + errorcolor + " " + message);
	}
	
	public static void sendServerErrorMessage(CommandSender sender, String message) {
		sender.sendMessage(prefix + errorcolor + " " + message);
	}
	
	public static void sendServerErrorMessage(Player player, ErrorMessage errormessage) {
		switch (errormessage) {
		case ONLYCONSOLE: player.sendMessage(prefix + errorcolor + " This command can only be used by the console!"); break;
		case ONLYPLAYER: player.sendMessage(prefix + errorcolor + " This command can only be used by players!"); break;
		case NOTENOUGHARGUMENTS: player.sendMessage(prefix + errorcolor + " Not enough arguments!"); break;
		case NOPERMISSION: player.sendMessage(prefix + errorcolor + " You have no permission to do that!"); break;
		default: break;
		}
	}
	
	public static void sendServerErrorMessage(CommandSender sender, ErrorMessage errormessage) {
		switch (errormessage) {
		case ONLYCONSOLE: sender.sendMessage(prefix + errorcolor + " This command can only be used by the console!"); break;
		case ONLYPLAYER: sender.sendMessage(prefix + errorcolor + " This command can only be used by players!"); break;
		case NOTENOUGHARGUMENTS: sender.sendMessage(prefix + errorcolor + " Not enough arguments!"); break;
		case NOPERMISSION: sender.sendMessage(prefix + errorcolor + " You have no permission to do that!"); break;
		default: break;
		}
	}
	
	public static void sendTitle(Player player, String title, String subtitle) {
		player.sendTitle(title, subtitle, 10, 100, 10);
	}
	
	public static ChatColor parseColor(String color) {
		try {
			color = color.toUpperCase();
			return ChatColor.valueOf(color);
		} catch (IllegalArgumentException exception) {
			return ChatColor.WHITE;
		}
		
	}
	
	public static Material parseMaterial(String material) {
		try {
			if(material.startsWith("minecraft:")) material = material.substring(10);
			material = material.toUpperCase();
			return Material.valueOf(material);
		} catch (IllegalArgumentException exception) {
			return Material.BARRIER;
		}
		
	}
	
	public static Boolean parseBoolean(String bool) {
		return bool.equalsIgnoreCase("true");
	}

}
