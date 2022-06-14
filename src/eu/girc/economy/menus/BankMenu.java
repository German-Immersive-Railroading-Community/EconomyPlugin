package eu.girc.economy.menus;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import eu.girc.economy.utilities.EconomyPlayer;
import eu.girc.economy.utilities.ExtendedItemStack;
import eu.girc.economy.utilities.PlayerInventory;

public class BankMenu extends PlayerInventory {

	public BankMenu(Player player) {
		super(player, 3, "Bank");
		setFixed(true);
		setItem(10, new ExtendedItemStack(ChatColor.GOLD + "Balance: " + EconomyPlayer.getPlayerByBucketPlayer(player).getBalance() + " coins", Material.GOLD_BLOCK));
		setItem(12, new ExtendedItemStack(ChatColor.GOLD + "Stocks", Material.PAPER).setLore(getStocksLore()));
		setItem(16, new ExtendedItemStack(ChatColor.GOLD + "" + ChatColor.BOLD + "Info", Material.BOOK).setLore(getInfoLore()));
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
