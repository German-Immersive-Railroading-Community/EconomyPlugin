package economy.menus;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import economy.config.Config;
import serversystem.utilities.PlayerInventory;

public class ProfileMenu extends PlayerInventory {

	public ProfileMenu(Player player) {
		super(player, 18, "Profile");
		setItemOption(ItemOption.FIXED);
		setItem(0, createItem(ChatColor.BOLD +  "Passport", Material.BOOK, getInfoLore(player)));
		open();
	}
	
	public List<String> getInfoLore(Player player) {
		List<String> string = new ArrayList<>();
		string.add("");
		string.add(ChatColor.DARK_GRAY + "ID");
		string.add(ChatColor.WHITE + player.getUniqueId().toString());
		string.add("");
		string.add(ChatColor.DARK_GRAY + "Name");
		string.add(ChatColor.WHITE + player.getName());
		string.add("");
		string.add(ChatColor.DARK_GRAY + "Member since");
		string.add(ChatColor.WHITE + "" + Config.getPlayerYear(player) + "." + Config.getPlayerMonth(player) + "." + Config.getPlayerDay(player));
		string.add("");
		return string;
	}

}
