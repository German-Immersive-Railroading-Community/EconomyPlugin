package economy.menus;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import economy.config.Config;
import serversystem.utilities.PlayerInventory;

public class BankMenu extends PlayerInventory {

	public BankMenu(Player player) {
		super(player, 27, "Bank");
		setItemOption(ItemOption.FIXED);
		setItem(10, createItem(ChatColor.GOLD + "Balance: " + Config.getPlayerBalance(player) + " coins", Material.GOLD_BLOCK));
		setItem(12, createItem(ChatColor.GOLD + "Stocks", Material.PAPER, getStocksLore()));
		setItem(16, createItem(ChatColor.GOLD + "" + ChatColor.BOLD + "Info", Material.BOOK, getInfoLore()));
		open();
	}
	
	public List<String> getStocksLore() {
		List<String> string = new ArrayList<>();
		string.add(ChatColor.RED + "Coming soon!");
		return string;
	}
	
	public List<String> getInfoLore() {
		List<String> string = new ArrayList<>();
		string.add("");
		string.add(ChatColor.GOLD + "/money " + ChatColor.GRAY + "Open the bank menu");
		string.add("");
		string.add(ChatColor.GOLD + "/money transfer " + ChatColor.GRAY + "Transfer money to other player");
		string.add(ChatColor.DARK_GRAY + "/money transfer <player> <amont>");
		string.add("");
		return string;
	}

}
